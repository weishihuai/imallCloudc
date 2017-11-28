package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.ESUtils;
import com.imall.iportal.core.elasticsearch.entity.OutInStockLogEntity;
import com.imall.iportal.core.elasticsearch.utils.SearchUtils;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.OutInStockLogRepository;
import com.imall.iportal.core.shop.service.OutInStockLogService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class OutInStockLogServiceImpl extends AbstractBaseService<OutInStockLog, Long> implements OutInStockLogService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private OutInStockLogRepository getOutInStockLogRepository() {
		return (OutInStockLogRepository) baseRepository;
	}

	@Override
	public Page<OutInStockLogPageVo> query(Pageable pageable, OutInStockLogSearchParam searchParam) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

		queryBuilder.must(QueryBuilders.termQuery(OutInStockLogEntity.SHOP_ID, searchParam.getShopId()));
		if(StringUtils.isNotBlank(searchParam.getSearchFields())) {
			BoolQueryBuilder keywordBuilder = QueryBuilders.boolQuery()
					.should(QueryBuilders.termQuery(OutInStockLogEntity.GOODS_NM, searchParam.getSearchFields()))
					.should(QueryBuilders.termQuery(OutInStockLogEntity.GOODS_CODE, searchParam.getSearchFields()))
					.should(QueryBuilders.termQuery(OutInStockLogEntity.PINYIN, searchParam.getSearchFields()));
			queryBuilder.must(keywordBuilder);
		}

		if(StringUtils.isNotBlank(searchParam.getBatch())) {
			queryBuilder.must(QueryBuilders.termQuery(OutInStockLogEntity.BATCH, searchParam.getBatch()));
		}

		if(searchParam.getFromDate() != null) {
			queryBuilder.must(QueryBuilders.rangeQuery(OutInStockLogEntity.CREATE_DATE).gt(searchParam.getFromDate().getTime()));
		}

		if(searchParam.getToDate() != null) {
			queryBuilder.must(QueryBuilders.rangeQuery(OutInStockLogEntity.CREATE_DATE).lt(searchParam.getToDate().getTime()));
		}
		if(StringUtils.isNotBlank(searchParam.getLogSourceTypeCode())) {
			queryBuilder.must(QueryBuilders.termQuery(OutInStockLogEntity.LOG_SOURCE_TYPE_CODE, searchParam.getLogSourceTypeCode()));
		}

		SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.OUT_IN_STOCK_LOG.toCode())
				.setTypes(IndexTypeCodeEnum.OUT_IN_STOCK_LOG.toCode())
				.setSearchType(SearchType.DEFAULT)
				.setQuery(queryBuilder)
				.addSort(OutInStockLogEntity.CREATE_DATE, SortOrder.DESC)
				.addStoredField(OutInStockLogEntity.ID)
				.setFrom(pageable.getPageNumber()*pageable.getPageSize())
				.setSize(pageable.getPageSize())
				.setExplain(false);
		SearchResponse response = searchRequestBuilder.get();
		List<Map<String, Object>> list = SearchUtils.getSearchResult(response);
		List<OutInStockLogPageVo> voList = new ArrayList<>();
		if(list != null) {
			for (Map<String, Object> map : list) {
				Long id = (Long) map.get(OutInStockLogEntity.ID);
                OutInStockLog stockLog = findOne(id);
                if(stockLog == null) {
                    continue;
                }
				voList.add(build(stockLog));
			}
		}

		return new PageImpl<>(voList, pageable, response.getHits().getTotalHits());
	}

	private OutInStockLogPageVo build(OutInStockLog outInStockLog) {
		OutInStockLogPageVo pageVo = CommonUtil.copyProperties(outInStockLog, new OutInStockLogPageVo());
		Goods goods = ServiceManager.goodsService.findOne(outInStockLog.getGoodsId());
		pageVo.setGoodsNm(goods.getGoodsNm());
		pageVo.setGoodsCode(goods.getGoodsCode());
		pageVo.setCommonNm(goods.getCommonNm());
		pageVo.setSpec(goods.getSpec());
		pageVo.setUnit(goods.getUnit());
		pageVo.setProduceManufacturer(goods.getProduceManufacturer());

		GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(outInStockLog.getGoodsBatchId());
		StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(outInStockLog.getStorageSpaceId());

		pageVo.setBatch(goodsBatch.getBatch());
		pageVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
		pageVo.setUnitPrice(goodsBatch.getPurchasePrice());
		switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
			case DRUG:
				GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
				if (goodDrug != null) {
					pageVo.setApprovalNumber(goodDrug.getApprovalNumber());
					SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
					pageVo.setDosageForm(sysDictItem.getDictItemNm());
				}
				break;

			case CHINESE_MEDICINE_PIECES:
				GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
				if (goodsChineseMedicinePieces != null) {
					pageVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
					SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
					pageVo.setDosageForm(sysDictItem.getDictItemNm());
					pageVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
				}
				break;
			case COSMETIC:   //化妆品
				GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
				if (goodsCosmetic != null) {
					pageVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
				}
				break;
			case OTHER:  //其他
				GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
				if (goodsOther != null) {
					pageVo.setApprovalNumber(goodsOther.getApprovalNumber());
				}
				break;
			case DAILY_NECESSITIES:  //日用品
				GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
				if (goodsDailyNecessities != null) {
					pageVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
				}
				break;
		}

		return pageVo;
	}

	@Override
	public Page<OutInStockLogStatisticsPageVo> queryOutInStockStatistics(Pageable pageable, OutInStockStatisticsSearchParam searchParam) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

		queryBuilder.must(QueryBuilders.termQuery(OutInStockLogEntity.SHOP_ID, searchParam.getShopId()));

		if(searchParam.getFromDate() != null) {
			queryBuilder.must(QueryBuilders.rangeQuery(OutInStockLogEntity.CREATE_DATE).gt(searchParam.getFromDate().getTime()));
		}

		if(searchParam.getToDate() != null) {
			queryBuilder.must(QueryBuilders.rangeQuery(OutInStockLogEntity.CREATE_DATE).lt(searchParam.getToDate().getTime()));
		}

		if(StringUtils.isNotBlank(searchParam.getSearchFields())) {
			BoolQueryBuilder keywordBuilder = QueryBuilders.boolQuery()
					.should(QueryBuilders.termQuery(OutInStockLogEntity.GOODS_NM, searchParam.getSearchFields()))
					.should(QueryBuilders.termQuery(OutInStockLogEntity.GOODS_CODE, searchParam.getSearchFields()))
					.should(QueryBuilders.termQuery(OutInStockLogEntity.COMMON_NM, searchParam.getSearchFields()))
					.should(QueryBuilders.termQuery(OutInStockLogEntity.PINYIN, searchParam.getSearchFields()));
			queryBuilder.must(keywordBuilder);
		}

		TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms(OutInStockLogEntity.GOODS_ID)
				.field(OutInStockLogEntity.GOODS_ID)
				.subAggregation(AggregationBuilders.sum(OutInStockLogEntity.IN_STOCK_QUANTITY).field(OutInStockLogEntity.IN_STOCK_QUANTITY))
				.subAggregation(AggregationBuilders.sum(OutInStockLogEntity.OUT_STOCK_QUANTITY).field(OutInStockLogEntity.OUT_STOCK_QUANTITY))
				.subAggregation(AggregationBuilders.sum(OutInStockLogEntity.IN_STOCK_AMOUNT).field(OutInStockLogEntity.IN_STOCK_AMOUNT))
				.subAggregation(AggregationBuilders.sum(OutInStockLogEntity.OUT_STOCK_AMOUNT).field(OutInStockLogEntity.OUT_STOCK_AMOUNT));

		SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.OUT_IN_STOCK_LOG.toCode())
				.setTypes(IndexTypeCodeEnum.OUT_IN_STOCK_LOG.toCode())
				.setSearchType(SearchType.DEFAULT)
				.setQuery(queryBuilder)
				.setExplain(false)
				.addAggregation(aggregationBuilder);
		searchRequestBuilder.storedFields(OutInStockLogEntity.GOODS_ID);

		Terms terms = searchRequestBuilder.get().getAggregations().get(OutInStockLogEntity.GOODS_ID);
		List<Terms.Bucket> buckets = terms.getBuckets();

		List<OutInStockLogStatisticsPageVo> pageVoList = new ArrayList<>();
		for (Terms.Bucket bucket : buckets) {
			Long goodsId = (Long) bucket.getKey();
			OutInStockLogStatisticsPageVo statisticsVo = buildGoodsInfo(goodsId);

			InternalSum inStockQuantity = bucket.getAggregations().get(OutInStockLogEntity.IN_STOCK_QUANTITY);
			InternalSum outStockQuantity = bucket.getAggregations().get(OutInStockLogEntity.OUT_STOCK_QUANTITY);
			InternalSum inStockAmount = bucket.getAggregations().get(OutInStockLogEntity.IN_STOCK_AMOUNT);
			InternalSum outStockAmount = bucket.getAggregations().get(OutInStockLogEntity.OUT_STOCK_AMOUNT);

			statisticsVo.setCurInStockQuantity(Double.valueOf(inStockQuantity.getValue()).longValue());
			statisticsVo.setCurOutStockQuantity(Double.valueOf(outStockQuantity.getValue()).longValue());
			statisticsVo.setCurInStockAmount(inStockAmount.getValue());
			statisticsVo.setCurOutStockAmount(outStockAmount.getValue());

			if(searchParam.getFromDate() == null) {
				statisticsVo.setClearingPrevAmount(0D);
				statisticsVo.setClearingPrevQuantity(0L);
			} else {
				OutInStockLog afterStartDateLog = getOutInStockLogRepository().findFirstLogAfterDate(searchParam.getShopId(), goodsId, searchParam.getFromDate());
				if(afterStartDateLog == null) {
					statisticsVo.setClearingPrevAmount(0D);
					statisticsVo.setClearingPrevQuantity(0L);
				} else {
					statisticsVo.setClearingPrevAmount(afterStartDateLog.getClearingPrevAmount());
					statisticsVo.setClearingPrevQuantity(afterStartDateLog.getClearingPrevQuantity());
				}
			}

			if(searchParam.getToDate() == null) {
				GoodsBatchStockInfo stockInfo = ServiceManager.goodsBatchService.findCurStockInfo(goodsId);
				statisticsVo.setCurStockAmount(stockInfo.getTotalAmount());
				statisticsVo.setCurStockQuantity(stockInfo.getCurrentStock());
			} else {
				OutInStockLog afterEndDateLog = getOutInStockLogRepository().findFirstLogAfterDate(searchParam.getShopId(), goodsId, searchParam.getToDate());
				if(afterEndDateLog == null) {
					GoodsBatchStockInfo stockInfo = ServiceManager.goodsBatchService.findCurStockInfo(goodsId);
					statisticsVo.setCurStockAmount(stockInfo.getTotalAmount());
					statisticsVo.setCurStockQuantity(stockInfo.getCurrentStock());
				} else {
					statisticsVo.setCurStockAmount(afterEndDateLog.getClearingPrevAmount());
					statisticsVo.setCurStockQuantity(afterEndDateLog.getClearingPrevQuantity());
				}
			}


			pageVoList.add(statisticsVo);
		}

		return new PageImpl<>(pageVoList, pageable, buckets.size());
	}

	private OutInStockLogStatisticsPageVo buildGoodsInfo(Long goodsId) {
		OutInStockLogStatisticsPageVo statisticsVo = new OutInStockLogStatisticsPageVo();

		Goods goods = ServiceManager.goodsService.findOne(goodsId);
		statisticsVo.setGoodsNm(goods.getGoodsNm());
		statisticsVo.setGoodsCode(goods.getGoodsCode());
		statisticsVo.setCommonNm(goods.getCommonNm());
		statisticsVo.setSpec(goods.getSpec());
		statisticsVo.setUnit(goods.getUnit());
		statisticsVo.setProduceManufacturer(goods.getProduceManufacturer());
		switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
			case DRUG:
				GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
				if (goodDrug != null) {
					statisticsVo.setApprovalNumber(goodDrug.getApprovalNumber());
					SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
					statisticsVo.setDosageForm(sysDictItem.getDictItemNm());
				}
				break;

			case CHINESE_MEDICINE_PIECES:
				GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
				if (goodsChineseMedicinePieces != null) {
					statisticsVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
					SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
					statisticsVo.setDosageForm(sysDictItem.getDictItemNm());
					statisticsVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
				}
				break;
			case COSMETIC:   //化妆品
				GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
				if (goodsCosmetic != null) {
					statisticsVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
				}
				break;
			case OTHER:  //其他
				GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
				if (goodsOther != null) {
					statisticsVo.setApprovalNumber(goodsOther.getApprovalNumber());
				}
				break;
			case DAILY_NECESSITIES:  //日用品
				GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
				if (goodsDailyNecessities != null) {
					statisticsVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
				}
				break;
		}

		return statisticsVo;
	}

    public Integer queryOutInStockLogToQueue(){
        return getOutInStockLogRepository().queryOutInStockLogToQueue();
    }
}