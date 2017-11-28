package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.Pinyin4jUtil;
import com.imall.commons.base.util.SevenZipUtils;
import com.imall.commons.base.util.excel.BaseExcelReader;
import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.commons.base.util.excel.ListExcelWriter;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.elasticsearch.ESUtils;
import com.imall.iportal.core.elasticsearch.IndexBuilder;
import com.imall.iportal.core.elasticsearch.entity.GoodsEntity;
import com.imall.iportal.core.elasticsearch.utils.SearchUtils;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysFileLib;
import com.imall.iportal.core.main.vo.FileMngVo;
import com.imall.iportal.core.platform.vo.GoodsExcelVo;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.GoodsRepository;
import com.imall.iportal.core.shop.service.GoodsService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

/**
 * 商品(服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsServiceImpl extends AbstractBaseService<Goods, Long> implements GoodsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static int goodsCodeSeed =1;

    @SuppressWarnings("unused")
    private GoodsRepository getGoodsRepository() {
        return (GoodsRepository) baseRepository;
    }

    @Override
    public Page<GoodsCommonComponentPageVo> queryGoodsCommonComponent(Pageable pageable, GoodsCommonComponentSearchParam goodsCommonComponentSearchParam, Long shopId) {

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        //去掉已删除的
        queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.IS_DELETE, BoolCodeEnum.NO.toCode()));
        queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.IS_ENABLE, BoolCodeEnum.YES.toCode()));
        queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.APPROVE_STATE_CODE, GoodsApproveStateCodeEnum.PASS_APPROVE.toCode()));

        queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.SHOP_ID, shopId));

        //过滤拆零后的商品
        queryBuilder.mustNot(QueryBuilders.existsQuery(GoodsEntity.SPLIT_ZERO_SOURCE_GOODS_ID));


        //拼音/商品编码/商品名称
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getKeyWords())) {
            BoolQueryBuilder keywordBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.termQuery(GoodsEntity.PINYIN, goodsCommonComponentSearchParam.getKeyWords()))
                    .should(QueryBuilders.termQuery(GoodsEntity.GOODS_NM, goodsCommonComponentSearchParam.getKeyWords()))
                    .should(QueryBuilders.termQuery(GoodsEntity.COMMON_NM, goodsCommonComponentSearchParam.getKeyWords()))
                    .should(QueryBuilders.termQuery(GoodsEntity.BAR_CODE, goodsCommonComponentSearchParam.getKeyWords()));
            queryBuilder.must(keywordBuilder);
        }


        //商品编码
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getGoodsCode())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.GOODS_CODE, goodsCommonComponentSearchParam.getGoodsCode()));
        }

        //生产厂家
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getProduceManufacturer())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.PRODUCE_MANUFACTURER, goodsCommonComponentSearchParam.getProduceManufacturer()));
        }

        //处方药 类型
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getPrescriptionDrugsTypeCode())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.PRESCRIPTION_DRUGS_TYPE_CODE, PrescriptionDrugsTypeCodeEnum.fromCode(goodsCommonComponentSearchParam.getPrescriptionDrugsTypeCode()).toCode()));
        }

        //麻黄碱
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getIsEphedrine())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.IS_EPHEDRINE, goodsCommonComponentSearchParam.getIsEphedrine()));
        }


        SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
                .prepareSearch(IndexTypeCodeEnum.GOODS.toCode())
                .setTypes(IndexTypeCodeEnum.GOODS.toCode())
                .setSearchType(SearchType.DEFAULT)
                .setQuery(queryBuilder)
                .addSort(GoodsEntity.DISPLAY_POSITION, SortOrder.ASC)
                .addStoredField(GoodsEntity.ID)
                .setFrom(pageable.getPageNumber() * pageable.getPageSize())
                .setSize(pageable.getPageSize())
                .setExplain(false);
        SearchResponse sr = searchRequestBuilder.get();
        List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
        List<GoodsCommonComponentPageVo> voList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Long id = (Long) map.get(GoodsEntity.ID);
            voList.add(buildCommonComponentPageVo(findOne(id)));
        }
        return new PageImpl<GoodsCommonComponentPageVo>(voList, pageable, sr.getHits().getTotalHits());
    }

    @Override
    public Page<WeChatGoodsPageVo> queryForWeChat(Pageable pageable, WeChatGoodsListSearchParam weChatGoodsListSearchParam, Long shopId) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        //去掉已删除的
        queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.IS_DELETE, BoolCodeEnum.NO.toCode()));
        queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.APPROVE_STATE_CODE, GoodsApproveStateCodeEnum.PASS_APPROVE.toCode()));
        queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.SHOP_ID, shopId));

        if (weChatGoodsListSearchParam.getCategoryId() != null){
            queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.SALES_CATEGORY_IDS, weChatGoodsListSearchParam.getCategoryId()));
        }

        //拼音/商品编码/商品名称
        if (StringUtils.isNotBlank(weChatGoodsListSearchParam.getKeywords())) {
            BoolQueryBuilder keywordBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.termQuery(GoodsEntity.PINYIN, weChatGoodsListSearchParam.getKeywords()))
                    .should(QueryBuilders.termQuery(GoodsEntity.GOODS_NM, weChatGoodsListSearchParam.getKeywords()))
                    .should(QueryBuilders.termQuery(GoodsEntity.COMMON_NM, weChatGoodsListSearchParam.getKeywords()));
            queryBuilder.must(keywordBuilder);
        }

        SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
                .prepareSearch(IndexTypeCodeEnum.GOODS.toCode())
                .setTypes(IndexTypeCodeEnum.GOODS.toCode())
                .setSearchType(SearchType.DEFAULT)
                .setQuery(queryBuilder)
                .addStoredField(GoodsEntity.ID)
                .setFrom(pageable.getPageNumber() * pageable.getPageSize())
                .setSize(pageable.getPageSize())
                .setExplain(false);
        SortOrder sortOrder = SortOrder.ASC;
        if (StringUtils.isNotBlank(weChatGoodsListSearchParam.getOrderBy())) {
            if (weChatGoodsListSearchParam.getSortOrder().equals("down")) {
                sortOrder = SortOrder.DESC;
            }
        }
        if (StringUtils.isBlank(weChatGoodsListSearchParam.getOrderBy())) {
            searchRequestBuilder.addSort(GoodsEntity.ID, SortOrder.ASC);
        } else if (weChatGoodsListSearchParam.getOrderBy().equals(Sku.SALES_VOLUME)) {
            searchRequestBuilder.addSort(GoodsEntity.SALES_VOLUME, SortOrder.DESC);
        } else if (weChatGoodsListSearchParam.getOrderBy().equals(Sku.RETAIL_PRICE)) {
            searchRequestBuilder.addSort(GoodsEntity.RETAIL_PRICE, sortOrder);
        } else {
            searchRequestBuilder.addSort(GoodsEntity.ID, SortOrder.ASC);
        }
        SearchResponse sr = searchRequestBuilder.get();
        List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
        List<WeChatGoodsPageVo> voList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Long id = (Long) map.get(GoodsEntity.ID);
            voList.add(buildWeChatGoodsPageVo(findOne(id)));
        }
        return new PageImpl<WeChatGoodsPageVo>(voList, pageable, sr.getHits().getTotalHits());
    }

    private WeChatGoodsPageVo buildWeChatGoodsPageVo(Goods goods) {
        WeChatGoodsPageVo weChatGoodsPageVo = CommonUtil.copyProperties(goods, new WeChatGoodsPageVo());

        if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
            GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
            if (PrescriptionDrugsTypeCodeEnum.fromCode(goodsDrug.getPrescriptionDrugsTypeCode()) == PrescriptionDrugsTypeCodeEnum.RX) {
                weChatGoodsPageVo.setIsPrescriptionDrugs(BoolCodeEnum.YES.toCode());
            }
        }
        if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
            GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
            if (PrescriptionDrugsTypeCodeEnum.fromCode(goodsChineseMedicinePieces.getPrescriptionDrugsTypeCode()) == PrescriptionDrugsTypeCodeEnum.RX) {
                weChatGoodsPageVo.setIsPrescriptionDrugs(BoolCodeEnum.YES.toCode());
            }
        }

        Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());
        weChatGoodsPageVo.setSkuId(sku.getId());
        weChatGoodsPageVo.setRetailPrice(sku.getRetailPrice());
        weChatGoodsPageVo.setMemberPrice(sku.getMemberPrice());
        weChatGoodsPageVo.setSkuId(sku.getId());
        List<FileMng> pictMngMsgList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_PICT, goods.getId());
        if (!CollectionUtils.isEmpty(pictMngMsgList)) {
            for (FileMng fileMng : pictMngMsgList) {
                if (FileTypeCodeEnum.fromCode(fileMng.getFileTypeCode()) == FileTypeCodeEnum.IMAGE) {
                    weChatGoodsPageVo.setImgUrl(FileSystemEngine.getFileSystem().getUrl(fileMng.getFileId()));
                    break;
                }
            }
        }
        return weChatGoodsPageVo;
    }

    @Override
    public List<GoodsPostPageVo> listGoodsByPos(Long shopId, String searchFields) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.SHOP_ID, shopId))
                .must(QueryBuilders.termQuery(GoodsEntity.IS_DELETE, BoolCodeEnum.NO.toCode()))
                .must(QueryBuilders.termQuery(GoodsEntity.APPROVE_STATE_CODE, GoodsApproveStateCodeEnum.PASS_APPROVE.toCode()))
                .must(QueryBuilders.termQuery(GoodsEntity.IS_ENABLE, BoolCodeEnum.YES.toCode()));

        //拼音/商品编码/商品名称
        if (StringUtils.isNotBlank(searchFields)) {
            BoolQueryBuilder keywordBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.termQuery(GoodsEntity.PINYIN, searchFields))
                    .should(QueryBuilders.termQuery(GoodsEntity.GOODS_CODE, searchFields))
                    .should(QueryBuilders.termQuery(GoodsEntity.GOODS_NM, searchFields))
                    .should(QueryBuilders.termQuery(GoodsEntity.COMMON_NM, searchFields))
                    .should(QueryBuilders.termQuery(GoodsEntity.BAR_CODE, searchFields));
            queryBuilder.must(keywordBuilder);
        }

        SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
                .prepareSearch(IndexTypeCodeEnum.GOODS.toCode())
                .setTypes(IndexTypeCodeEnum.GOODS.toCode())
                .setSearchType(SearchType.DEFAULT)
                .setQuery(queryBuilder)
                .addSort(GoodsEntity.DISPLAY_POSITION, SortOrder.ASC)
                .addStoredField(GoodsEntity.ID)
                .setExplain(false);

        SearchResponse sr = searchRequestBuilder.get();
        List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
        List<GoodsPostPageVo> voList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Long id = (Long) map.get(GoodsEntity.ID);
            GoodsPostPageVo pageVo = findHasStockGoods(findOne(id));
            if (pageVo == null) {
                continue;
            }
            voList.add(pageVo);
        }

        return voList;

    }

    private GoodsPostPageVo findHasStockGoods(Goods goods) {
        GoodsPostPageVo pageVo = new GoodsPostPageVo();
        Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());
        if (sku == null || sku.getCurrentStock() <= sku.getSecurityStock()) {
            return null;
        }
        List<GoodsBatch> batchList = ServiceManager.goodsBatchService.findHasStockGoodsByGoodsId(goods.getId());

        if (CollectionUtils.isEmpty(batchList)) {
            return null;
        }

        pageVo.setId(goods.getId());
        pageVo.setSkuId(sku.getId());
        pageVo.setGoodsCode(goods.getGoodsCode());
        pageVo.setCommonNm(goods.getCommonNm());
        pageVo.setGoodsNm(goods.getGoodsNm());
        pageVo.setProduceManufacturer(goods.getProduceManufacturer());
        pageVo.setSpec(goods.getSpec());
        pageVo.setUnit(goods.getUnit());
        pageVo.setRetailPrice(sku.getRetailPrice());
        pageVo.setMemberPrice(sku.getMemberPrice() <= 0 ? sku.getRetailPrice() : sku.getMemberPrice());
        pageVo.setStock(ServiceManager.skuService.getStockQuantity(sku.getId()));

        Map<String, GoodsBatchPosPageVo> batchPosPageVoMap = new HashedMap();
        Long securityStock = sku.getSecurityStock();
        for (GoodsBatch goodsBatch : batchList) {
            //过滤停售的商品批次
            if (GoodsBatchStateCodeEnum.fromCode(goodsBatch.getBatchState()) != GoodsBatchStateCodeEnum.NORMAL) {
                continue;
            }

            //当前批次扣减掉当前所需安全库存
            if(securityStock > goodsBatch.getCurrentStock()) {
                securityStock -= goodsBatch.getCurrentStock();
                continue;
            }

            //批号相同且货位相同的批次，库存合并展示
            GoodsBatchPosPageVo batchPosPageVo = batchPosPageVoMap.get(goodsBatch.getBatch() + "_" + goodsBatch.getStorageSpaceId());
            if(batchPosPageVo == null || !goodsBatch.getStorageSpaceId().equals(batchPosPageVo.getStorageSpaceId())) {
                batchPosPageVo = new GoodsBatchPosPageVo();
                StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
                batchPosPageVo.setId(goodsBatch.getId());
                batchPosPageVo.setBatch(goodsBatch.getBatch());
                batchPosPageVo.setValidDateString(goodsBatch.getValidDateString());
                batchPosPageVo.setStock(goodsBatch.getCurrentStock() - securityStock);
                batchPosPageVo.setStorageSpaceId(storageSpace != null ? storageSpace.getId() : null);
                batchPosPageVo.setStorageSpaceNm(storageSpace != null ? storageSpace.getStorageSpaceNm() : "");
            } else {
                batchPosPageVo.setStock(goodsBatch.getCurrentStock() + batchPosPageVo.getStock());
                batchPosPageVo.setId(goodsBatch.getId());
            }
            securityStock = 0L;

            batchPosPageVoMap.put(goodsBatch.getBatch() + "_" + goodsBatch.getStorageSpaceId(), batchPosPageVo);
        }
        pageVo.setGoodsBatchList(new ArrayList<>(batchPosPageVoMap.values()));

        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case DRUG:
                GoodsDrug drug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (drug != null) {
                    pageVo.setIsEphedrine(drug.getIsEphedrine());
                    boolean isPrescription = PrescriptionDrugsTypeCodeEnum.fromCode(drug.getPrescriptionDrugsTypeCode()) == PrescriptionDrugsTypeCodeEnum.RX;
                    pageVo.setPrescriptionDrugs(BoolCodeEnum.toCode(isPrescription));
                }
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsChineseMedicinePieces pieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
                if(pieces != null) {
                    pageVo.setIsEphedrine(pieces.getIsEphedrine());
                    boolean isPrescription = PrescriptionDrugsTypeCodeEnum.fromCode(pieces.getPrescriptionDrugsTypeCode()) == PrescriptionDrugsTypeCodeEnum.RX;
                    pageVo.setPrescriptionDrugs(BoolCodeEnum.toCode(isPrescription));
                }
                break;
        }

        return pageVo;
    }

    @Override
    public Page<GoodsListPageVo> query(Pageable pageable, GoodsListSearchParam goodsListSearchParam, Long shopId) {
        BoolQueryBuilder queryBuilder = queryBuilder(goodsListSearchParam, shopId);

        SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
                .prepareSearch(IndexTypeCodeEnum.GOODS.toCode())
                .setTypes(IndexTypeCodeEnum.GOODS.toCode())
                .setSearchType(SearchType.DEFAULT)
                .setQuery(queryBuilder)
                .addSort(GoodsEntity.CREATE_DATE, SortOrder.DESC)
                .addStoredField(GoodsEntity.ID)
                .setFrom(pageable.getPageNumber() * pageable.getPageSize())
                .setSize(pageable.getPageSize())
                .setExplain(false);
        SearchResponse sr = searchRequestBuilder.get();
        List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
        List<GoodsListPageVo> voList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Long id = (Long) map.get(GoodsEntity.ID);
            Goods goods = findOne(id);
            if (goods != null) {
                voList.add(buildGoodsList(goods));
            }
        }
        return new PageImpl<>(voList, pageable, sr.getHits().getTotalHits());
    }

    private List<GoodsListPageVo> listAll(GoodsListSearchParam goodsListSearchParam, Long shopId) {
        BoolQueryBuilder queryBuilder = queryBuilder(goodsListSearchParam, shopId);

        SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
                .prepareSearch(IndexTypeCodeEnum.GOODS.toCode())
                .setTypes(IndexTypeCodeEnum.GOODS.toCode())
                .setSearchType(SearchType.DEFAULT)
                .setQuery(queryBuilder)
                .addSort(GoodsEntity.CREATE_DATE, SortOrder.DESC)
                .addStoredField(GoodsEntity.ID)
                .setExplain(false);
        SearchResponse sr = searchRequestBuilder.get();
        List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
        List<GoodsListPageVo> voList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Long id = (Long) map.get(GoodsEntity.ID);
            Goods goods = findOne(id);
            if (goods != null) {
                voList.add(buildGoodsList(goods));
            }
        }
        return voList;
    }

    private BoolQueryBuilder queryBuilder(GoodsListSearchParam goodsListSearchParam, Long shopId) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        //去掉已删除的
        queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.IS_DELETE, BoolCodeEnum.NO.toCode()));

        //门店id
        queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.SHOP_ID, shopId));

        //审核状态代码
        if (StringUtils.isNotBlank(goodsListSearchParam.getApproveStateCode())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.APPROVE_STATE_CODE, goodsListSearchParam.getApproveStateCode()));
        }
        //过滤拆零后的商品
        queryBuilder.mustNot(QueryBuilders.existsQuery(GoodsEntity.SPLIT_ZERO_SOURCE_GOODS_ID));
//        queryBuilder.filter(QueryBuilders.missingQuery(GoodsEntity.SPLIT_ZERO_SOURCE_GOODS_ID));

        //是否启用
        if (StringUtils.isNotBlank(goodsListSearchParam.getIsEnable())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.IS_ENABLE, goodsListSearchParam.getIsEnable()));
        }

        //货位ID
        if (goodsListSearchParam.getStorageSpaceId() != null) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.STORAGE_SPACE_ID, goodsListSearchParam.getStorageSpaceId()));
        }

        //创建时间
        if(goodsListSearchParam.getFromDate()!=null){
            queryBuilder.must(QueryBuilders.rangeQuery(GoodsEntity.CREATE_DATE).gt(goodsListSearchParam.getFromDate().getTime()));
        }
        if(goodsListSearchParam.getToDate()!=null){
            queryBuilder.must(QueryBuilders.rangeQuery(GoodsEntity.CREATE_DATE).lt(goodsListSearchParam.getToDate().getTime()));
        }

        //拼音/商品编码/商品名称
        if (StringUtils.isNotBlank(goodsListSearchParam.getKeyWords())) {
            BoolQueryBuilder keywordBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.termQuery(GoodsEntity.PINYIN, goodsListSearchParam.getKeyWords()))
                    .should(QueryBuilders.termQuery(GoodsEntity.GOODS_CODE, goodsListSearchParam.getKeyWords()))
                    .should(QueryBuilders.termQuery(GoodsEntity.GOODS_NM, goodsListSearchParam.getKeyWords()));
            queryBuilder.must(keywordBuilder);
        }
        return queryBuilder;
    }

    private GoodsListPageVo buildGoodsList(Goods goods) {
        GoodsListPageVo goodsListPageVo = CommonUtil.copyProperties(goods, new GoodsListPageVo());

        //剂型:只有药品/中药饮片        //产地:只有中药饮片
        if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
            GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
            SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
            goodsListPageVo.setDosageForm(sysDictItem.getDictItemNm());
        }
        if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
            GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
            goodsListPageVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
            SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
            goodsListPageVo.setDosageForm(sysDictItem.getDictItemNm());
        }
        //库存//零售价/会员价:直接从SKU拿
        Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());
        goodsListPageVo.setCurrentStock(sku.getCurrentStock());
        goodsListPageVo.setRetailPrice(sku.getRetailPrice());
        goodsListPageVo.setMemberPrice(sku.getMemberPrice());
        return goodsListPageVo;
    }

    private GoodsCommonComponentPageVo buildCommonComponentPageVo(Goods goods) {
        GoodsCommonComponentPageVo goodsCommonComponentPageVo = CommonUtil.copyProperties(goods, new GoodsCommonComponentPageVo());
        goodsCommonComponentPageVo.setGoodsTypeCode(GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()).toCode());
        //药品
        if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
            GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
            goodsCommonComponentPageVo.setApprovalNumber(goodDrug.getApprovalNumber());
            SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
            goodsCommonComponentPageVo.setDosageFormName(sysDictItem.getDictItemNm());
            goodsCommonComponentPageVo.setDosageForm(goodDrug.getDosageForm());
            //中药饮片
        } else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
            GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
            goodsCommonComponentPageVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
            SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
            goodsCommonComponentPageVo.setDosageFormName(sysDictItem.getDictItemNm());
            goodsCommonComponentPageVo.setDosageForm(goodsChineseMedicinePieces.getDosageForm());
            goodsCommonComponentPageVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
        }else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.OTHER) {
            GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
            goodsCommonComponentPageVo.setApprovalNumber(goodsOther.getApprovalNumber());
        }else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DAILY_NECESSITIES) {
            GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
            goodsCommonComponentPageVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
        }else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.COSMETIC) {
            GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
            goodsCommonComponentPageVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
        }



        Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());
        goodsCommonComponentPageVo.setRetailPrice(sku.getRetailPrice());
        goodsCommonComponentPageVo.setMemberPrice(sku.getMemberPrice());
        goodsCommonComponentPageVo.setCurrentStock(sku.getCurrentStock());
        return goodsCommonComponentPageVo;
    }

    @Transactional
    @Override
    public Long saveGoods(GoodsSaveVo goodsSaveVo) {
        Goods goods = CommonUtil.copyProperties(goodsSaveVo, new Goods());
        goods.setApproveStateCode(BoolCodeEnum.fromCode(goodsSaveVo.getIsFirstSell()) == BoolCodeEnum.YES?GoodsApproveStateCodeEnum.WAIT_APPROVE.toCode():GoodsApproveStateCodeEnum.PASS_APPROVE.toCode());
        goods.setGoodsDocId(goodsSaveVo.getGoodsDocId());
        goods.setSellCategoryIds(goodsSaveVo.getSellCategoryIds().toString().replace("[", "").replace("]", "").replace(" ", ""));
        goods.setGoodsTypeCode(GoodsTypeCodeEnum.fromCode(goodsSaveVo.getGoodsTypeCode()).toCode());
        goods.setIsDelete(BoolCodeEnum.NO.toCode());
        goods.setToxicologyCode(ToxicologyTypeCodeEnum.fromCode(goodsSaveVo.getToxicologyCode()).toCode());
        goods.setStorageCondition(StorageConditionTypeCodeEnum.fromCode(goodsSaveVo.getStorageCondition()).toCode());
        goods.setDisplayPosition(1L);
        goods.setPinyin(Pinyin4jUtil.getPinYinHeadChar(goodsSaveVo.getCommonNm()));
        goods.setInstructionsStr(goodsSaveVo.getInstructions());
        goods.setMedicationGuideStr(goodsSaveVo.getMedicationGuide());

        //如果是首营 状态为待审核 否则状态为已审核
        goods.setApproveStateCode(BoolCodeEnum.fromCode(goodsSaveVo.getIsFirstSell()) == BoolCodeEnum.YES?GoodsApproveStateCodeEnum.WAIT_APPROVE.toCode():GoodsApproveStateCodeEnum.PASS_APPROVE.toCode());
        //保存商品
        save(goods);
        //如果是首营 生成一条审核首营记录
        if (BoolCodeEnum.fromCode(goodsSaveVo.getIsFirstSell()) == BoolCodeEnum.YES) {
            saveFirstApproveGoods(goodsSaveVo, goods);
        }
        //保存商品类型关联表
        saveGoodsType(goodsSaveVo, goods.getId());
        //保存Sku表
        saveGoodsSku(goodsSaveVo, goods.getId());
        //保存图片
        if (!CollectionUtils.isEmpty(goodsSaveVo.getPictFileList())) {
            savePict(FileObjectTypeCodeEnum.GOODS_PICT, goods.getId(), goodsSaveVo.getPictFileList());
        }
        //保存附件
        if (!CollectionUtils.isEmpty(goodsSaveVo.getOtherFileList())) {
            savePict(FileObjectTypeCodeEnum.GOODS_ATTACHMENT, goods.getId(), goodsSaveVo.getOtherFileList());
        }
        //索引立即提交
        IndexBuilder.commitImmediately(goods.getId(), IndexTypeCodeEnum.GOODS);
        return goods.getId();
    }

    private void saveGoodsSku(GoodsSaveVo goodsSaveVo, Long goodsId) {
        Sku sku = CommonUtil.copyProperties(goodsSaveVo, new Sku());
        sku.setGoodsId(goodsId);
        sku.setSalesVolume(0L);
        if (BoolCodeEnum.fromCode(goodsSaveVo.getIsSplitZero()) == BoolCodeEnum.YES
                && goodsSaveVo.getSplitZeroQuantity() != null
                && StringUtils.isNotBlank(goodsSaveVo.getSplitZeroSpec())
                && goodsSaveVo.getSplitZeroUnit() != null
                && goodsSaveVo.getSplitZeroRetailPrice() != null
                && goodsSaveVo.getSplitZeroMemberPrice() != null){
            sku.setSplitZeroSpec(goodsSaveVo.getSplitZeroSpec());
            sku.setSplitZeroQuantity(goodsSaveVo.getSplitZeroQuantity());
            sku.setSplitZeroUnit(goodsSaveVo.getSplitZeroUnit());
            sku.setSplitZeroRetailPrice(goodsSaveVo.getSplitZeroRetailPrice());
            sku.setSplitZeroMemberPrice(goodsSaveVo.getSplitZeroMemberPrice());
        }

        //以下为默认值方便加减统计
        if (goodsSaveVo.getMemberPrice() == null) {
            //如果没设置会员价,默认设为领零售价
            sku.setMemberPrice(goodsSaveVo.getRetailPrice());
        }
        if (goodsSaveVo.getMarketPrice() == null) {
            sku.setMarketPrice(0D);
        }
        if (goodsSaveVo.getCostPrice() == null) {
            sku.setCostPrice(0D);
        }
        if (goodsSaveVo.getCurrentStock() == null) {
            sku.setCurrentStock(0L);
        }
        if (goodsSaveVo.getSecurityStock() == null) {
            sku.setSecurityStock(0L);
        }
        sku.setLockStockQuantity(0L);
        ServiceManager.skuService.save(sku);
    }

    private void saveGoodsType(GoodsSaveVo goodsSaveVo,Long goodsId) {
        switch (GoodsTypeCodeEnum.fromCode(goodsSaveVo.getGoodsTypeCode())) {
            case DRUG://药品
                if (StringUtils.isBlank(goodsSaveVo.getApprovalNumber()) || StringUtils.isBlank(goodsSaveVo.getApprovalNumberTermString()) || StringUtils.isBlank(goodsSaveVo.getDosageForm())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsDrug goodsDrug = CommonUtil.copyProperties(goodsSaveVo, new GoodsDrug());
                goodsDrug.setPrescriptionDrugsTypeCode(PrescriptionDrugsTypeCodeEnum.fromCode(goodsSaveVo.getPrescriptionDrugsTypeCode()).toCode());
                goodsDrug.setGoodsId(goodsId);
                if (BoolCodeEnum.fromCode(goodsSaveVo.getIsMedicalInsuranceGoods()) == BoolCodeEnum.YES
                        && goodsSaveVo.getMedicalInsuranceNum() != null) {
                    goodsDrug.setMedicalInsuranceNum(goodsSaveVo.getMedicalInsuranceNum());
                }
                ServiceManager.goodsDrugService.save(goodsDrug);
                break;
            case CHINESE_MEDICINE_PIECES://中药饮片
                if (StringUtils.isBlank(goodsSaveVo.getProductionPlace()) || StringUtils.isBlank(goodsSaveVo.getDosageForm())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = CommonUtil.copyProperties(goodsSaveVo, new GoodsChineseMedicinePieces());
                goodsChineseMedicinePieces.setPrescriptionDrugsTypeCode(PrescriptionDrugsTypeCodeEnum.fromCode(goodsSaveVo.getPrescriptionDrugsTypeCode()).toCode());
                goodsChineseMedicinePieces.setGoodsId(goodsId);
                if (BoolCodeEnum.fromCode(goodsSaveVo.getIsMedicalInsuranceGoods()) == BoolCodeEnum.YES
                        && goodsSaveVo.getMedicalInsuranceNum() != null) {
                    goodsChineseMedicinePieces.setMedicalInsuranceNum(goodsSaveVo.getMedicalInsuranceNum());
                }
                ServiceManager.goodsChineseMedicinePiecesService.save(goodsChineseMedicinePieces);
                break;
            case OTHER://其他
                if (StringUtils.isBlank(goodsSaveVo.getApprovalNumber())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsOther goodsOther = CommonUtil.copyProperties(goodsSaveVo, new GoodsOther());
                goodsOther.setGoodsId(goodsId);
                ServiceManager.goodsOtherService.save(goodsOther);
                break;
            case FOOD_HEALTH://保健品
                if (StringUtils.isBlank(goodsSaveVo.getFoodHygieneLicenceNum()) || StringUtils.isBlank(goodsSaveVo.getProductionDateString()) || StringUtils.isBlank(goodsSaveVo.getExpirationDateString()) || StringUtils.isBlank(goodsSaveVo.getHealthCareFunc())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsFoodHealth goodsFoodHealth = CommonUtil.copyProperties(goodsSaveVo, new GoodsFoodHealth());
                goodsFoodHealth.setGoodsId(goodsId);
                ServiceManager.goodsFoodHealthService.save(goodsFoodHealth);
                break;
            case DAILY_NECESSITIES://日用品
                if (StringUtils.isBlank(goodsSaveVo.getApprovalNumber())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsDailyNecessities goodsDailyNecessities = CommonUtil.copyProperties(goodsSaveVo, new GoodsDailyNecessities());
                goodsDailyNecessities.setGoodsId(goodsId);
                ServiceManager.goodsDailyNecessitiesService.save(goodsDailyNecessities);
                break;
            case MEDICAL_INSTRUMENTS://医疗机械
                if (StringUtils.isBlank(goodsSaveVo.getRegNum()) || StringUtils.isBlank(goodsSaveVo.getRegRegistrationFormNum())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsMedicalInstruments goodsMedicalInstruments = CommonUtil.copyProperties(goodsSaveVo, new GoodsMedicalInstruments());
                goodsMedicalInstruments.setGoodsId(goodsId);
                ServiceManager.goodsMedicalInstrumentsService.save(goodsMedicalInstruments);
                break;
            case COSMETIC://化妆品
                if (StringUtils.isBlank(goodsSaveVo.getApprovalNumber())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsCosmetic goodsCosmetic = CommonUtil.copyProperties(goodsSaveVo, new GoodsCosmetic());
                goodsCosmetic.setGoodsId(goodsId);
                ServiceManager.goodsCosmeticService.save(goodsCosmetic);
                break;
        }
    }

    private void saveFirstApproveGoods(GoodsSaveVo goodsSaveVo, Goods goods) {
        FirstManageDrugQualityApprove firstManageDrugQualityApprove = new FirstManageDrugQualityApprove();
        firstManageDrugQualityApprove.setShopId(goodsSaveVo.getShopId());
        firstManageDrugQualityApprove.setGoodsId(goods.getId());
        firstManageDrugQualityApprove.setGoodsCode(goodsSaveVo.getGoodsCode());
        firstManageDrugQualityApprove.setGoodsNm(goodsSaveVo.getGoodsNm());
        firstManageDrugQualityApprove.setCommonNmFirstSpell(goods.getPinyin());
        firstManageDrugQualityApprove.setCommonNm(goodsSaveVo.getCommonNm());
        firstManageDrugQualityApprove.setDosageForm(goodsSaveVo.getDosageForm());
        firstManageDrugQualityApprove.setUnit(goodsSaveVo.getUnit());
        firstManageDrugQualityApprove.setSpec(goodsSaveVo.getSpec());
        firstManageDrugQualityApprove.setProduceManufacturer(goodsSaveVo.getProduceManufacturer());
        firstManageDrugQualityApprove.setApprovalNumber(goodsSaveVo.getApprovalNumber());
        firstManageDrugQualityApprove.setApproveStateCode(ApproveStateCodeEnum.WAIT_APPROVE.toCode());
        firstManageDrugQualityApprove.setApplyManName(goods.getCreateBy());
        firstManageDrugQualityApprove.setSubmitAdvice(goods.getSubmitIdea());
        firstManageDrugQualityApprove.setApplyRemark(goods.getRemark());
        ServiceManager.firstManageDrugQualityApproveService.save(firstManageDrugQualityApprove);
    }

    private void savePict(FileObjectTypeCodeEnum fileObjectTypeCodeEnum, Long id, List<FileMng> fileMngs) {
        List<FileMng> fileMnglist = new ArrayList<>();
        for (FileMng fileMng : fileMngs) {
            if (StringUtils.isNotBlank(fileMng.getFileId()) && fileMng.getSysFileLibId() != null) {
                fileMng.setId(null);
                fileMnglist.add(fileMng);
            }
        }
        ServiceManager.fileMngService.saveList(fileObjectTypeCodeEnum, id, fileMnglist);
    }

    private void updatePict(FileObjectTypeCodeEnum fileObjectTypeCodeEnum, Long id, List<FileMng> fileMngs) {
        List<FileMng> fileMnglist = new ArrayList<>();
        for (FileMng fileMng : fileMngs) {
            if (StringUtils.isNotBlank(fileMng.getFileId()) && fileMng.getSysFileLibId() != null) {
                fileMnglist.add(fileMng);
            }
        }
        ServiceManager.fileMngService.saveListAndDeleteOld(fileObjectTypeCodeEnum, id, fileMnglist);
    }

    @Transactional
    @Override
    public Integer queryGoodsToQueue() {
        return getGoodsRepository().queryGoodsToQueue();
    }

    @Override
    public GoodsDetailVo findDetail(Long id, Long shopId) {
        Goods goods = findOne(id);
        if (goods == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        if (!shopId.equals(goods.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setGoodsCode(goods.getGoodsCode());
        goodsDetailVo.setId(goods.getId());
        goodsDetailVo.setGoodsDocId(goods.getGoodsDocId());
        goodsDetailVo.setGoodsNm(goods.getGoodsNm());
        goodsDetailVo.setBrandNm(goods.getBrandNm());
        goodsDetailVo.setBarCode(goods.getBarCode());
        goodsDetailVo.setGoodsTypeCode(goods.getGoodsTypeCode());
        goodsDetailVo.setGoodsTypeName(GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()).toName());
        goodsDetailVo.setToxicologyName(ToxicologyTypeCodeEnum.fromCode(goods.getToxicologyCode()).toName());
        goodsDetailVo.setStorageConditionName(StorageConditionTypeCodeEnum.fromCode(goods.getStorageCondition()).toName());
        goodsDetailVo.setCommonNm(goods.getCommonNm());
        goodsDetailVo.setSpec(goods.getSpec());
        goodsDetailVo.setUnit(goods.getUnit());
        goodsDetailVo.setToxicologyCode(goods.getToxicologyCode());
        goodsDetailVo.setStorageCondition(goods.getStorageCondition());
        goodsDetailVo.setProduceManufacturer(goods.getProduceManufacturer());
        goodsDetailVo.setInstructions(goods.getInstructionsStr());
        goodsDetailVo.setMedicationGuide(goods.getMedicationGuideStr());
        goodsDetailVo.setIsEnable(goods.getIsEnable());
        goodsDetailVo.setIsFirstSell(goods.getIsFirstSell());
        goodsDetailVo.setSubmitIdea(goods.getSubmitIdea());
        goodsDetailVo.setRemark(goods.getRemark());
        goodsDetailVo.setPurchaseTaxRate(goods.getPurchaseTaxRate());
        goodsDetailVo.setSellTaxRate(goods.getSellTaxRate());
        List<String> sellCategoryList = new ArrayList<>();
        if (goods.getSellCategoryIds().contains(",")) {
            for (String category : goods.getSellCategoryIds().split(",")) {
                sellCategoryList.add(category);
            }
        } else {
            sellCategoryList.add(goods.getSellCategoryIds());
        }
        goodsDetailVo.setSellCategoryIds(sellCategoryList);
        StringBuffer sellsCategoryStr = new StringBuffer();
        sellsCategoryStr.append("");
        for (String categoryId : sellCategoryList) {
            SalesCategory salesCategory = ServiceManager.salesCategoryService.findOne(Long.parseLong(categoryId));
            sellsCategoryStr.append(salesCategory.getCategoryName());
            sellsCategoryStr.append(",");
        }
        sellsCategoryStr.deleteCharAt(sellsCategoryStr.length() - 1);
        goodsDetailVo.setSellsCategoryStr(sellsCategoryStr.toString());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case DRUG:
                GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                goodsDetailVo.setApprovalNumber(goodsDrug.getApprovalNumber());
                goodsDetailVo.setApproveDateString(goodsDrug.getApproveDateString());
                goodsDetailVo.setIsImportGoods(goodsDrug.getIsImportGoods());
                goodsDetailVo.setIsChineseMedicineProtect(goodsDrug.getIsChineseMedicineProtect());
                goodsDetailVo.setApprovalNumberTermString(goodsDrug.getApprovalNumberTermString());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
                goodsDetailVo.setDosageFormName(sysDictItem.getDictItemNm());
                goodsDetailVo.setDosageForm(goodsDrug.getDosageForm());
                goodsDetailVo.setPrescriptionDrugsTypeCode(goodsDrug.getPrescriptionDrugsTypeCode());
                goodsDetailVo.setIsEphedrine(goodsDrug.getIsEphedrine());
                goodsDetailVo.setIsKeyCuring(goodsDrug.getIsKeyCuring());
                goodsDetailVo.setIsMedicalInsuranceGoods(goodsDrug.getIsMedicalInsuranceGoods());
                goodsDetailVo.setMedicalInsuranceNum(goodsDrug.getMedicalInsuranceNum());
                break;
            case OTHER:
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                goodsDetailVo.setApprovalNumber(goodsOther.getApprovalNumber());
                goodsDetailVo.setManufacturerAddr(goodsOther.getManufacturerAddr());
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
                goodsDetailVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                goodsDetailVo.setApproveDateString(goodsChineseMedicinePieces.getApproveDateString());
                goodsDetailVo.setIsImportGoods(goodsChineseMedicinePieces.getIsImportGoods());
                goodsDetailVo.setIsChineseMedicineProtect(goodsChineseMedicinePieces.getIsChineseMedicineProtect());
                goodsDetailVo.setApprovalNumberTermString(goodsChineseMedicinePieces.getApprovalNumberTermString());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                goodsDetailVo.setDosageFormName(sysDictItem2.getDictItemNm());
                goodsDetailVo.setDosageForm(goodsChineseMedicinePieces.getDosageForm());
                goodsDetailVo.setPrescriptionDrugsTypeCode(goodsChineseMedicinePieces.getPrescriptionDrugsTypeCode());
                goodsDetailVo.setIsEphedrine(goodsChineseMedicinePieces.getIsEphedrine());
                goodsDetailVo.setIsKeyCuring(goodsChineseMedicinePieces.getIsKeyCuring());
                goodsDetailVo.setIsMedicalInsuranceGoods(goodsChineseMedicinePieces.getIsMedicalInsuranceGoods());
                goodsDetailVo.setMedicalInsuranceNum(goodsChineseMedicinePieces.getMedicalInsuranceNum());
                goodsDetailVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                goodsDetailVo.setEffect(goodsChineseMedicinePieces.getEffect());
                break;
            case FOOD_HEALTH:
                GoodsFoodHealth goodsFoodHealth = ServiceManager.goodsFoodHealthService.findByGoodsId(goods.getId());
                goodsDetailVo.setFoodHygieneLicenceNum(goodsFoodHealth.getFoodHygieneLicenceNum());
                goodsDetailVo.setProductionDateString(goodsFoodHealth.getProductionDateString());
                goodsDetailVo.setExpirationDateString(goodsFoodHealth.getExpirationDateString());
                goodsDetailVo.setHealthCareFunc(goodsFoodHealth.getHealthCareFunc());
                goodsDetailVo.setAppropriateCrowd(goodsFoodHealth.getAppropriateCrowd());
                goodsDetailVo.setNotAppropriateCrowd(goodsFoodHealth.getNotAppropriateCrowd());
                goodsDetailVo.setEdibleMethodAndDosage(goodsFoodHealth.getEdibleMethodAndDosage());
                goodsDetailVo.setStorageMethod(goodsFoodHealth.getStorageMethod());
                goodsDetailVo.setExecStandard(goodsFoodHealth.getExecStandard());
                goodsDetailVo.setEffectComposition(goodsFoodHealth.getEffectComposition());
                goodsDetailVo.setNotice(goodsFoodHealth.getNotice());
                break;
            case DAILY_NECESSITIES:
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                goodsDetailVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                goodsDetailVo.setManufacturerAddr(goodsDailyNecessities.getManufacturerAddr());
                break;
            case MEDICAL_INSTRUMENTS:
                GoodsMedicalInstruments goodsMedicalInstruments = ServiceManager.goodsMedicalInstrumentsService.findByGoodsId(goods.getId());
                goodsDetailVo.setRegNum(goodsMedicalInstruments.getRegNum());
                goodsDetailVo.setRegRegistrationFormNum(goodsMedicalInstruments.getRegRegistrationFormNum());
                goodsDetailVo.setManufacturerAddr(goodsMedicalInstruments.getManufacturerAddr());
                goodsDetailVo.setApplyRange(goodsMedicalInstruments.getApplyRange());
                goodsDetailVo.setProductStandardNum(goodsMedicalInstruments.getProductStandardNum());
                break;
            case COSMETIC:
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                goodsDetailVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                goodsDetailVo.setManufacturerAddr(goodsCosmetic.getManufacturerAddr());
                break;
        }

        Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());
        goodsDetailVo.setRetailPrice(sku.getRetailPrice());
        goodsDetailVo.setMemberPrice(sku.getMemberPrice());
        goodsDetailVo.setMarketPrice(sku.getMarketPrice());
        goodsDetailVo.setCostPrice(sku.getCostPrice());
        goodsDetailVo.setCurrentStock(sku.getCurrentStock());
        goodsDetailVo.setSecurityStock(sku.getSecurityStock());
        goodsDetailVo.setIsSpecialPriceGoods(sku.getIsSpecialPriceGoods());
        goodsDetailVo.setIsSplitZero(sku.getIsSplitZero());
        goodsDetailVo.setSplitZeroQuantity(sku.getSplitZeroQuantity());
        goodsDetailVo.setSplitZeroSpec(sku.getSplitZeroSpec());
        goodsDetailVo.setSplitZeroUnit(sku.getSplitZeroUnit());
        goodsDetailVo.setSplitZeroRetailPrice(sku.getSplitZeroRetailPrice());
        goodsDetailVo.setSplitZeroMemberPrice(sku.getSplitZeroMemberPrice());

        //获取附件
        List<FileMng> fileMngMsgList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_ATTACHMENT, id);
        List<FileMngVo> fileMngVos = new ArrayList<>();
        for (FileMng fileMng : fileMngMsgList) {
            FileMngVo fileMngVo= CommonUtil.copyProperties(fileMng,new FileMngVo());
            SysFileLib fileLib = ServiceManager.sysFileLibService.findOne(fileMng.getSysFileLibId());
            fileMngVo.setFileNm(fileLib != null?fileLib.getFileNm():"");
            fileMngVos.add(fileMngVo);
        }
        goodsDetailVo.setOtherFileVoList(fileMngVos);

        //获取图片
        List<FileMng> pictMngMsgList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_PICT, id);
        List<FileMngVo> pictMngVos = new ArrayList<>();
        for (FileMng pictMng : pictMngMsgList) {
            FileMngVo fileMngVo= CommonUtil.copyProperties(pictMng,new FileMngVo());
            SysFileLib fileLib = ServiceManager.sysFileLibService.findOne(pictMng.getSysFileLibId());
            fileMngVo.setFileNm(fileLib != null?fileLib.getFileNm():"");
            pictMngVos.add(fileMngVo);
        }
        goodsDetailVo.setPictFileVoList(pictMngVos);

        return goodsDetailVo;
    }

    @Override
    public WeChatGoodsDetailVo findDetailForWeChat(Long id) {
        Goods goods = findOne(id);
        if (goods == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"商品"}));
        }
        WeChatGoodsDetailVo weChatGoodsDetailVo = new WeChatGoodsDetailVo();
        weChatGoodsDetailVo.setId(goods.getId());
        weChatGoodsDetailVo.setShopId(goods.getShopId());
        weChatGoodsDetailVo.setProduceManufacturer(goods.getProduceManufacturer());
        weChatGoodsDetailVo.setCommonNm(goods.getCommonNm());
        weChatGoodsDetailVo.setGoodsNm(goods.getGoodsNm());
        weChatGoodsDetailVo.setSpec(goods.getSpec());
        weChatGoodsDetailVo.setInstructions(goods.getInstructionsStr());
        if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
            GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
            if (PrescriptionDrugsTypeCodeEnum.fromCode(goodsDrug.getPrescriptionDrugsTypeCode()) == PrescriptionDrugsTypeCodeEnum.RX) {
                weChatGoodsDetailVo.setIsPrescriptionDrugs(BoolCodeEnum.YES.toCode());
            }
        }
        if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
            GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
            if (PrescriptionDrugsTypeCodeEnum.fromCode(goodsChineseMedicinePieces.getPrescriptionDrugsTypeCode()) == PrescriptionDrugsTypeCodeEnum.RX) {
                weChatGoodsDetailVo.setIsPrescriptionDrugs(BoolCodeEnum.YES.toCode());
            }
        }

        List<String> imgUrl = new ArrayList<>();
        List<FileMng> pictMngMsgList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_PICT, goods.getId());
        if (!CollectionUtils.isEmpty(pictMngMsgList)) {
            for (FileMng fileMng : pictMngMsgList) {
                if (FileTypeCodeEnum.fromCode(fileMng.getFileTypeCode()) == FileTypeCodeEnum.IMAGE) {
                    imgUrl.add(FileSystemEngine.getFileSystem().getUrl(fileMng.getFileId(),"640X640"));
                }
            }
        }
        weChatGoodsDetailVo.setImgUrlList(imgUrl);
        Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());
        weChatGoodsDetailVo.setSkuId(sku.getId());
        weChatGoodsDetailVo.setRetailPrice(sku.getRetailPrice());
        weChatGoodsDetailVo.setMarketPrice(sku.getMarketPrice());
        WeShop weShop = ServiceManager.weShopService.findByShopId(goods.getShopId());
        weChatGoodsDetailVo.setWeShopId(weShop.getId());
        weChatGoodsDetailVo.setShopNm(weShop.getShopNm());
        weChatGoodsDetailVo.setDetailLocation(weShop.getDetailLocation());
        weChatGoodsDetailVo.setShopLat(weShop.getShopLat());
        weChatGoodsDetailVo.setContactTel(weShop.getContactTel());
        weChatGoodsDetailVo.setShopLng(weShop.getShopLng());
        weChatGoodsDetailVo.setIsSellTime(checkTime(weShop.getSellStartTime(), weShop.getSellEndTime()) ? BoolCodeEnum.YES.toCode() : BoolCodeEnum.NO.toCode());
        weChatGoodsDetailVo.setNormalSales(BoolCodeEnum.fromCode(weShop.getIsNormalSales()).boolValue());
        return weChatGoodsDetailVo;
    }

    private Boolean checkTime(String startTime, String endTime) {
        String[] startTimeList = startTime.split(":");
        String[] endTimeList = endTime.split(":");
        String[] nowTimeList = DateTimeUtils.convertMmTimeToString(new Date()).split(":");
        if (Long.parseLong(nowTimeList[0]) >= Long.parseLong(startTimeList[0])
                && Long.parseLong(nowTimeList[0]) <= Long.parseLong(endTimeList[0])
                && Long.parseLong(nowTimeList[1]) >= Long.parseLong(startTimeList[1])
                && Long.parseLong(nowTimeList[1]) <= Long.parseLong(endTimeList[1])
                ) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Long updateGoods(GoodsUpdateVo goodsUpdateVo) throws ParseException {
        Goods goods = findOne(goodsUpdateVo.getId());
        if (goods == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        if (!goodsUpdateVo.getShopId().equals(goods.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        goods.setGoodsCode(goodsUpdateVo.getGoodsCode());
        goods.setGoodsNm(goodsUpdateVo.getGoodsNm());
        goods.setSellCategoryIds(goodsUpdateVo.getSellCategoryIds().toString().replace("[", "").replace("]", "").replace(" ", ""));
        goods.setBrandNm(goodsUpdateVo.getBrandNm());
        goods.setBarCode(goodsUpdateVo.getBarCode());
        goods.setCommonNm(goodsUpdateVo.getCommonNm());
        goods.setSpec(goodsUpdateVo.getSpec());
        goods.setUnit(goodsUpdateVo.getUnit());
        goods.setToxicologyCode(ToxicologyTypeCodeEnum.fromCode(goodsUpdateVo.getToxicologyCode()).toCode());
        goods.setStorageCondition(StorageConditionTypeCodeEnum.fromCode(goodsUpdateVo.getStorageCondition()).toCode());
        goods.setProduceManufacturer(goodsUpdateVo.getProduceManufacturer());
        goods.setInstructionsStr(goodsUpdateVo.getInstructions());
        goods.setMedicationGuideStr(goodsUpdateVo.getMedicationGuide());
        goods.setIsFirstSell(goodsUpdateVo.getIsFirstSell());
        goods.setSubmitIdea(goodsUpdateVo.getSubmitIdea());
        goods.setRemark(goodsUpdateVo.getRemark());
        goods.setPurchaseTaxRate(goodsUpdateVo.getPurchaseTaxRate());
        goods.setSellTaxRate(goodsUpdateVo.getSellTaxRate());
        goods.setPinyin(Pinyin4jUtil.getPinYinHeadChar(goodsUpdateVo.getCommonNm()));
        goods.setIsEnable(goodsUpdateVo.getIsEnable());

        //如果商品类型一样,直接更新
        if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.fromCode(goodsUpdateVo.getGoodsTypeCode())) {
            updateGoodsType(goodsUpdateVo, goods.getGoodsTypeCode(),goods.getId());
        } else {
            //先删除旧的商品关联表数据
            deleteOldGoodsType(goods.getGoodsTypeCode(),goods.getId());
            //创建新的商品类型关联表,并且更新数据
            createNewGoodsTypeAndUpdate(goodsUpdateVo);
            //更新商品类型
            goods.setGoodsTypeCode(GoodsTypeCodeEnum.fromCode(goodsUpdateVo.getGoodsTypeCode()).toCode());
        }
        //更新Sku和购物车
        updateSkuAndNormalShopping(goodsUpdateVo);
        //编辑图片
        updatePict(FileObjectTypeCodeEnum.GOODS_PICT, goods.getId(), goodsUpdateVo.getPictFileList());
        //编辑附件
        updatePict(FileObjectTypeCodeEnum.GOODS_ATTACHMENT, goods.getId(), goodsUpdateVo.getOtherFileList());
        //更新并立即提交到索引
        save(goods);
        IndexBuilder.commitImmediately(goods.getId(), IndexTypeCodeEnum.GOODS);
        return goodsUpdateVo.getId();
    }

    private void updateSkuAndNormalShopping(GoodsUpdateVo goodsUpdateVo) {
        Sku sku = ServiceManager.skuService.findByGoodsId(goodsUpdateVo.getId());
        //如果零售价/会员价更新了，则需要更新购物车
        boolean priceUpdate = false;
        if(!sku.getRetailPrice().equals(goodsUpdateVo.getRetailPrice()) || (goodsUpdateVo.getMemberPrice() != null && !goodsUpdateVo.getMemberPrice().equals(sku.getMemberPrice()))){
            priceUpdate = true;
        }
        sku.setRetailPrice(goodsUpdateVo.getRetailPrice());

        //以下为默认值方便加减统计
        sku.setMemberPrice(goodsUpdateVo.getMemberPrice() == null?goodsUpdateVo.getRetailPrice():goodsUpdateVo.getMemberPrice());

        sku.setMarketPrice(goodsUpdateVo.getMarketPrice() == null?0D:goodsUpdateVo.getMarketPrice());

        sku.setCostPrice(goodsUpdateVo.getCostPrice() == null?0D:goodsUpdateVo.getCostPrice());

        sku.setCurrentStock(goodsUpdateVo.getCurrentStock() == null?0L:goodsUpdateVo.getCurrentStock());

        sku.setSecurityStock(goodsUpdateVo.getSecurityStock() == null?0L:goodsUpdateVo.getSecurityStock());

        sku.setIsSpecialPriceGoods(goodsUpdateVo.getIsSpecialPriceGoods());

        sku.setIsSplitZero(goodsUpdateVo.getIsSplitZero());

        if (BoolCodeEnum.fromCode(goodsUpdateVo.getIsSplitZero()) == BoolCodeEnum.YES
                && goodsUpdateVo.getSplitZeroQuantity() != null
                && StringUtils.isNotBlank(goodsUpdateVo.getSplitZeroSpec())
                && goodsUpdateVo.getSplitZeroUnit() != null
                && goodsUpdateVo.getSplitZeroRetailPrice() != null
                && goodsUpdateVo.getSplitZeroMemberPrice() != null){
            sku.setSplitZeroSpec(goodsUpdateVo.getSplitZeroSpec());
            sku.setSplitZeroQuantity(goodsUpdateVo.getSplitZeroQuantity());
            sku.setSplitZeroUnit(goodsUpdateVo.getSplitZeroUnit());
            sku.setSplitZeroRetailPrice(goodsUpdateVo.getSplitZeroRetailPrice());
            sku.setSplitZeroMemberPrice(goodsUpdateVo.getSplitZeroMemberPrice());
        }

        ServiceManager.skuService.save(sku);
        if (priceUpdate){
            ServiceManager.normalShoppingFlowService.updateCartAfterPriceChange(sku);
        }
    }

    private void createNewGoodsTypeAndUpdate(GoodsUpdateVo goodsUpdateVo) throws ParseException {
        switch (GoodsTypeCodeEnum.fromCode(goodsUpdateVo.getGoodsTypeCode())) {
            case DRUG:
                GoodsDrug goodsDrugNew = new GoodsDrug();
                goodsDrugNew.setApprovalNumber(goodsUpdateVo.getApprovalNumber());
                goodsDrugNew.setApproveDateString(goodsUpdateVo.getApproveDateString());
                goodsDrugNew.setApprovalNumberTermString(goodsUpdateVo.getApprovalNumberTermString());
                goodsDrugNew.setIsImportGoods(goodsUpdateVo.getIsImportGoods());
                goodsDrugNew.setIsChineseMedicineProtect(goodsUpdateVo.getIsChineseMedicineProtect());
                goodsDrugNew.setDosageForm(goodsUpdateVo.getDosageForm());
                goodsDrugNew.setPrescriptionDrugsTypeCode(goodsUpdateVo.getPrescriptionDrugsTypeCode());
                goodsDrugNew.setIsEphedrine(goodsUpdateVo.getIsEphedrine());
                goodsDrugNew.setIsKeyCuring(goodsUpdateVo.getIsKeyCuring());
                goodsDrugNew.setIsMedicalInsuranceGoods(goodsUpdateVo.getIsMedicalInsuranceGoods());
                goodsDrugNew.setMedicalInsuranceNum(goodsUpdateVo.getMedicalInsuranceNum());
                goodsDrugNew.setGoodsId(goodsUpdateVo.getId());
                ServiceManager.goodsDrugService.save(goodsDrugNew);
                break;
            case OTHER:
                GoodsOther goodsOtherNew = new GoodsOther();
                goodsOtherNew.setApprovalNumber(goodsUpdateVo.getApprovalNumber());
                goodsOtherNew.setManufacturerAddr(goodsUpdateVo.getManufacturerAddr());
                goodsOtherNew.setGoodsId(goodsUpdateVo.getId());
                ServiceManager.goodsOtherService.save(goodsOtherNew);
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsChineseMedicinePieces goodsChineseMedicinePiecesNew = new GoodsChineseMedicinePieces();
                goodsChineseMedicinePiecesNew.setApprovalNumber(goodsUpdateVo.getApprovalNumber());
                goodsChineseMedicinePiecesNew.setApproveDateString(goodsUpdateVo.getApproveDateString());
                goodsChineseMedicinePiecesNew.setIsImportGoods(goodsUpdateVo.getIsImportGoods());
                goodsChineseMedicinePiecesNew.setIsChineseMedicineProtect(goodsUpdateVo.getIsChineseMedicineProtect());
                goodsChineseMedicinePiecesNew.setApproveDateString(goodsUpdateVo.getApproveDateString());
                goodsChineseMedicinePiecesNew.setDosageForm(goodsUpdateVo.getDosageForm());
                goodsChineseMedicinePiecesNew.setPrescriptionDrugsTypeCode(goodsUpdateVo.getPrescriptionDrugsTypeCode());
                goodsChineseMedicinePiecesNew.setIsEphedrine(goodsUpdateVo.getIsEphedrine());
                goodsChineseMedicinePiecesNew.setIsKeyCuring(goodsUpdateVo.getIsKeyCuring());
                goodsChineseMedicinePiecesNew.setIsMedicalInsuranceGoods(goodsUpdateVo.getIsMedicalInsuranceGoods());
                goodsChineseMedicinePiecesNew.setMedicalInsuranceNum(goodsUpdateVo.getMedicalInsuranceNum());
                goodsChineseMedicinePiecesNew.setProductionPlace(goodsUpdateVo.getProductionPlace());
                goodsChineseMedicinePiecesNew.setEffect(goodsUpdateVo.getEffect());
                goodsChineseMedicinePiecesNew.setGoodsId(goodsUpdateVo.getId());
                ServiceManager.goodsChineseMedicinePiecesService.save(goodsChineseMedicinePiecesNew);
                break;
            case FOOD_HEALTH:
                GoodsFoodHealth goodsFoodHealthNew = new GoodsFoodHealth();
                goodsFoodHealthNew.setFoodHygieneLicenceNum(goodsUpdateVo.getFoodHygieneLicenceNum());
                goodsFoodHealthNew.setProductionDateString(goodsUpdateVo.getProductionDateString());
                goodsFoodHealthNew.setExpirationDateString(goodsUpdateVo.getExpirationDateString());
                goodsFoodHealthNew.setHealthCareFunc(goodsUpdateVo.getHealthCareFunc());
                goodsFoodHealthNew.setAppropriateCrowd(goodsUpdateVo.getAppropriateCrowd());
                goodsFoodHealthNew.setNotAppropriateCrowd(goodsUpdateVo.getNotAppropriateCrowd());
                goodsFoodHealthNew.setEdibleMethodAndDosage(goodsUpdateVo.getEdibleMethodAndDosage());
                goodsFoodHealthNew.setStorageMethod(goodsUpdateVo.getStorageMethod());
                goodsFoodHealthNew.setExecStandard(goodsUpdateVo.getExecStandard());
                goodsFoodHealthNew.setEffectComposition(goodsUpdateVo.getEffectComposition());
                goodsFoodHealthNew.setNotice(goodsUpdateVo.getNotice());
                goodsFoodHealthNew.setGoodsId(goodsUpdateVo.getId());
                ServiceManager.goodsFoodHealthService.save(goodsFoodHealthNew);
                break;
            case DAILY_NECESSITIES:
                GoodsDailyNecessities goodsDailyNecessitiesNew = new GoodsDailyNecessities();
                goodsDailyNecessitiesNew.setApprovalNumber(goodsUpdateVo.getApprovalNumber());
                goodsDailyNecessitiesNew.setManufacturerAddr(goodsUpdateVo.getManufacturerAddr());
                goodsDailyNecessitiesNew.setGoodsId(goodsUpdateVo.getId());
                ServiceManager.goodsDailyNecessitiesService.save(goodsDailyNecessitiesNew);
                break;
            case MEDICAL_INSTRUMENTS:
                GoodsMedicalInstruments goodsMedicalInstrumentsNew = new GoodsMedicalInstruments();
                goodsMedicalInstrumentsNew.setRegRegistrationFormNum(goodsUpdateVo.getRegRegistrationFormNum());
                goodsMedicalInstrumentsNew.setRegNum(goodsUpdateVo.getRegNum());
                goodsMedicalInstrumentsNew.setManufacturerAddr(goodsUpdateVo.getManufacturerAddr());
                goodsMedicalInstrumentsNew.setApplyRange(goodsUpdateVo.getApplyRange());
                goodsMedicalInstrumentsNew.setProductStandardNum(goodsUpdateVo.getProductStandardNum());
                goodsMedicalInstrumentsNew.setGoodsId(goodsUpdateVo.getId());
                ServiceManager.goodsMedicalInstrumentsService.save(goodsMedicalInstrumentsNew);
                break;
            case COSMETIC:
                GoodsCosmetic goodsCosmeticNew = new GoodsCosmetic();
                goodsCosmeticNew.setApprovalNumber(goodsUpdateVo.getApprovalNumber());
                goodsCosmeticNew.setManufacturerAddr(goodsUpdateVo.getManufacturerAddr());
                goodsCosmeticNew.setGoodsId(goodsUpdateVo.getId());
                ServiceManager.goodsCosmeticService.save(goodsCosmeticNew);
                break;
        }
    }

    private void deleteOldGoodsType(String goodsTypeCode,Long goodsId) {
        switch (GoodsTypeCodeEnum.fromCode(goodsTypeCode)) {
            case DRUG:
                GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsId);
                ServiceManager.goodsDrugService.delete(goodsDrug.getId());
                break;
            case OTHER:
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodsId);
                ServiceManager.goodsOtherService.delete(goodsOther.getId());
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsId);
                ServiceManager.goodsChineseMedicinePiecesService.delete(goodsChineseMedicinePieces.getId());
                break;
            case FOOD_HEALTH:
                GoodsFoodHealth goodsFoodHealth = ServiceManager.goodsFoodHealthService.findByGoodsId(goodsId);
                ServiceManager.goodsFoodHealthService.delete(goodsFoodHealth.getId());
                break;
            case DAILY_NECESSITIES:
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodsId);
                ServiceManager.goodsDailyNecessitiesService.delete(goodsDailyNecessities.getId());
                break;
            case MEDICAL_INSTRUMENTS:
                GoodsMedicalInstruments goodsMedicalInstruments = ServiceManager.goodsMedicalInstrumentsService.findByGoodsId(goodsId);
                ServiceManager.goodsMedicalInstrumentsService.delete(goodsMedicalInstruments.getId());
                break;
            case COSMETIC:
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodsId);
                ServiceManager.goodsCosmeticService.delete(goodsCosmetic.getId());
                break;
        }
    }

    private void updateGoodsType(GoodsUpdateVo goodsUpdateVo, String goodsTypeCode,Long goodsId) throws ParseException {
        switch (GoodsTypeCodeEnum.fromCode(goodsTypeCode)) {
            case DRUG:
                GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsId);
                goodsDrug.setApprovalNumber(goodsUpdateVo.getApprovalNumber());
                goodsDrug.setApproveDateString(goodsUpdateVo.getApproveDateString());
                goodsDrug.setApprovalNumberTermString(goodsUpdateVo.getApprovalNumberTermString());
                goodsDrug.setIsImportGoods(goodsUpdateVo.getIsImportGoods());
                goodsDrug.setIsChineseMedicineProtect(goodsUpdateVo.getIsChineseMedicineProtect());
                goodsDrug.setDosageForm(goodsUpdateVo.getDosageForm());
                goodsDrug.setPrescriptionDrugsTypeCode(goodsUpdateVo.getPrescriptionDrugsTypeCode());
                goodsDrug.setIsEphedrine(goodsUpdateVo.getIsEphedrine());
                goodsDrug.setIsKeyCuring(goodsUpdateVo.getIsKeyCuring());
                goodsDrug.setIsMedicalInsuranceGoods(goodsUpdateVo.getIsMedicalInsuranceGoods());
                goodsDrug.setMedicalInsuranceNum(BoolCodeEnum.fromCode(goodsUpdateVo.getIsMedicalInsuranceGoods())==BoolCodeEnum.YES?goodsUpdateVo.getMedicalInsuranceNum():"");
                ServiceManager.goodsDrugService.save(goodsDrug);
                break;
            case OTHER:
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodsId);
                goodsOther.setApprovalNumber(goodsUpdateVo.getApprovalNumber());
                goodsOther.setManufacturerAddr(goodsUpdateVo.getManufacturerAddr());
                ServiceManager.goodsOtherService.save(goodsOther);
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsId);
                goodsChineseMedicinePieces.setApprovalNumber(goodsUpdateVo.getApprovalNumber());
                goodsChineseMedicinePieces.setApprovalNumberTermString(goodsUpdateVo.getApprovalNumberTermString());
                goodsChineseMedicinePieces.setIsImportGoods(goodsUpdateVo.getIsImportGoods());
                goodsChineseMedicinePieces.setIsChineseMedicineProtect(goodsUpdateVo.getIsChineseMedicineProtect());
                goodsChineseMedicinePieces.setApproveDateString(goodsUpdateVo.getApproveDateString());
                goodsChineseMedicinePieces.setDosageForm(goodsUpdateVo.getDosageForm());
                goodsChineseMedicinePieces.setPrescriptionDrugsTypeCode(goodsUpdateVo.getPrescriptionDrugsTypeCode());
                goodsChineseMedicinePieces.setIsEphedrine(goodsUpdateVo.getIsEphedrine());
                goodsChineseMedicinePieces.setIsKeyCuring(goodsUpdateVo.getIsKeyCuring());
                goodsChineseMedicinePieces.setIsMedicalInsuranceGoods(goodsUpdateVo.getIsMedicalInsuranceGoods());
                goodsChineseMedicinePieces.setMedicalInsuranceNum(BoolCodeEnum.fromCode(goodsUpdateVo.getIsMedicalInsuranceGoods())==BoolCodeEnum.YES?goodsUpdateVo.getMedicalInsuranceNum():"");
                goodsChineseMedicinePieces.setProductionPlace(goodsUpdateVo.getProductionPlace());
                goodsChineseMedicinePieces.setEffect(goodsUpdateVo.getEffect());
                ServiceManager.goodsChineseMedicinePiecesService.save(goodsChineseMedicinePieces);
                break;
            case FOOD_HEALTH:
                GoodsFoodHealth goodsFoodHealth = ServiceManager.goodsFoodHealthService.findByGoodsId(goodsId);
                goodsFoodHealth.setFoodHygieneLicenceNum(goodsUpdateVo.getFoodHygieneLicenceNum());
                goodsFoodHealth.setProductionDateString(goodsUpdateVo.getProductionDateString());
                goodsFoodHealth.setExpirationDateString(goodsUpdateVo.getExpirationDateString());
                goodsFoodHealth.setHealthCareFunc(goodsUpdateVo.getHealthCareFunc());
                goodsFoodHealth.setAppropriateCrowd(goodsUpdateVo.getAppropriateCrowd());
                goodsFoodHealth.setNotAppropriateCrowd(goodsUpdateVo.getNotAppropriateCrowd());
                goodsFoodHealth.setEdibleMethodAndDosage(goodsUpdateVo.getEdibleMethodAndDosage());
                goodsFoodHealth.setStorageMethod(goodsUpdateVo.getStorageMethod());
                goodsFoodHealth.setExecStandard(goodsUpdateVo.getExecStandard());
                goodsFoodHealth.setEffectComposition(goodsUpdateVo.getEffectComposition());
                goodsFoodHealth.setNotice(goodsUpdateVo.getNotice());
                ServiceManager.goodsFoodHealthService.save(goodsFoodHealth);
                break;
            case DAILY_NECESSITIES:
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodsId);
                goodsDailyNecessities.setApprovalNumber(goodsUpdateVo.getApprovalNumber());
                goodsDailyNecessities.setManufacturerAddr(goodsUpdateVo.getManufacturerAddr());
                ServiceManager.goodsDailyNecessitiesService.save(goodsDailyNecessities);
                break;
            case MEDICAL_INSTRUMENTS:
                GoodsMedicalInstruments goodsMedicalInstruments = ServiceManager.goodsMedicalInstrumentsService.findByGoodsId(goodsId);
                goodsMedicalInstruments.setRegNum(goodsUpdateVo.getRegNum());
                goodsMedicalInstruments.setRegRegistrationFormNum(goodsUpdateVo.getRegRegistrationFormNum());
                goodsMedicalInstruments.setManufacturerAddr(goodsUpdateVo.getManufacturerAddr());
                goodsMedicalInstruments.setApplyRange(goodsUpdateVo.getApplyRange());
                goodsMedicalInstruments.setProductStandardNum(goodsUpdateVo.getProductStandardNum());
                ServiceManager.goodsMedicalInstrumentsService.save(goodsMedicalInstruments);
                break;
            case COSMETIC:
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodsId);
                goodsCosmetic.setApprovalNumber(goodsUpdateVo.getApprovalNumber());
                goodsCosmetic.setManufacturerAddr(goodsUpdateVo.getManufacturerAddr());
                ServiceManager.goodsCosmeticService.save(goodsCosmetic);
                break;
        }
    }

    @Override
    public Boolean findGoodsCodeExist(String goodsCode, Long id, Long shopId) {
        Goods goods = getGoodsRepository().findByGoodsCodeAndShopId(goodsCode, shopId);
        return (goods == null && id == null) || goods == null || goods.getId().equals(id);
    }

    @Override
    public String getDosageFormByGoodsIdAndGoodsTypeCode(Long goodsId, String goodsTypeCode) {
        switch (GoodsTypeCodeEnum.fromCode(goodsTypeCode)) {
            case DRUG:
                GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsId);
                return goodsDrug == null ? "" : goodsDrug.getDosageForm();
            case CHINESE_MEDICINE_PIECES:
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsId);
                return goodsChineseMedicinePieces == null ? "" : goodsChineseMedicinePieces.getDosageForm();
            default:
                return "";
        }
    }

    @Override
    public PurchaseOrderGoodsVo findPurchaseOrderGoodsVoById(Long goodsId) {
        Goods goods = this.findOne(goodsId);
        PurchaseOrderGoodsVo goodsVo = CommonUtil.copyProperties(goods, new PurchaseOrderGoodsVo());
        this.setGoodsInfByGoodsTypeCode(goodsVo, goods);
        return goodsVo;
    }

    private void setGoodsInfByGoodsTypeCode(PurchaseOrderGoodsVo goodsVo, Goods goods) {
        Long goodsId = goods.getId();
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case DRUG:
                GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsId);
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
                goodsVo.setDosageForm(sysDictItem.getDictItemNm());
                goodsVo.setApprovalNumber(goodsDrug.getApprovalNumber());
                goodsVo.setPrescriptionDrugsTypeCode(goodsDrug.getPrescriptionDrugsTypeCode());
                goodsVo.setIsEphedrine(goodsDrug.getIsEphedrine());
                break;
            case OTHER:
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodsId);
                goodsVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsId);
                goodsVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                goodsVo.setDosageForm(sysDictItem2.getDictItemNm());
                goodsVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                break;
            case FOOD_HEALTH:
                GoodsFoodHealth foodHealth = ServiceManager.goodsFoodHealthService.findByGoodsId(goodsId);
                goodsVo.setApprovalNumber(foodHealth.getFoodHygieneLicenceNum());
                break;
            case DAILY_NECESSITIES:
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodsId);
                goodsVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case MEDICAL_INSTRUMENTS:
                GoodsMedicalInstruments medicalInstruments = ServiceManager.goodsMedicalInstrumentsService.findByGoodsId(goodsId);
                goodsVo.setApprovalNumber(medicalInstruments.getRegNum());
                break;
            case COSMETIC:
                GoodsCosmetic cosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodsId);
                goodsVo.setApprovalNumber(cosmetic.getApprovalNumber());
                break;
            default:
                break;
        }
    }

    @Transactional
    @Override
    public Boolean updateStartUsing(GoodsUpdateIsEnableParam goodsUpdateIsEnableParam) {
        Goods goods = findOne(goodsUpdateIsEnableParam.getId());
        if (goods == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        if (!goodsUpdateIsEnableParam.getShopId().equals(goods.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        if (BoolCodeEnum.fromCode(goodsUpdateIsEnableParam.getOperationState()) == BoolCodeEnum.NO) {
            goods.setIsEnable(BoolCodeEnum.YES.toCode());
            if(goods.getSplitZeroSourceGoodsId()!=null){//如果这个商品被拆零过
                Goods spiltGoods = getGoodsRepository().findBySplitZeroSourceGoodsId(goods.getId());
                spiltGoods.setIsEnable(BoolCodeEnum.YES.toCode());
                save(spiltGoods);
            }
            save(goods);
            IndexBuilder.commitImmediately(goods.getId(), IndexTypeCodeEnum.GOODS);
            //创建一条修改记录
            GoodsEnableRecord goodsEnableRecord = new GoodsEnableRecord();
            goodsEnableRecord.setGoodsId(goods.getId());
            goodsEnableRecord.setShopId(goodsUpdateIsEnableParam.getShopId());
            goodsEnableRecord.setOperationState(OperationStateCodeEnum.ENABLE.toCode());
            goodsEnableRecord.setReason(goodsUpdateIsEnableParam.getReason());
            ServiceManager.goodsEnableRecordService.save(goodsEnableRecord);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Boolean updateBlockUp(GoodsUpdateIsEnableParam goodsUpdateIsEnableParam) {
        Goods goods = findOne(goodsUpdateIsEnableParam.getId());
        if (goods == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        if (!goodsUpdateIsEnableParam.getShopId().equals(goods.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        if (BoolCodeEnum.fromCode(goodsUpdateIsEnableParam.getOperationState()) == BoolCodeEnum.YES) {
            goods.setIsEnable(BoolCodeEnum.NO.toCode());
            if(goods.getSplitZeroSourceGoodsId()!=null){//如果这个商品被拆零过
                Goods spiltGoods = getGoodsRepository().findBySplitZeroSourceGoodsId(goods.getId());
                spiltGoods.setIsEnable(BoolCodeEnum.NO.toCode());
                save(spiltGoods);
            }
            save(goods);
            IndexBuilder.commitImmediately(goods.getId(), IndexTypeCodeEnum.GOODS);
            //创建一条修改记录
            GoodsEnableRecord goodsEnableRecord = new GoodsEnableRecord();
            goodsEnableRecord.setGoodsId(goods.getId());
            goodsEnableRecord.setOperationState(OperationStateCodeEnum.DISABLE.toCode());
            goodsEnableRecord.setReason(goodsUpdateIsEnableParam.getReason());
            goodsEnableRecord.setShopId(goodsUpdateIsEnableParam.getShopId());
            ServiceManager.goodsEnableRecordService.save(goodsEnableRecord);
            return true;
        }
        return false;
    }

    @Override
    public Page<OutOfStockWarningGoodsVo> queryOutOfStockWarning(Pageable pageable, StockWarningSearchParam stockWarningSearchParam) {
        Page<Goods> outOfStockWarningPage = getGoodsRepository().queryOutOfStockWarningPage(pageable, stockWarningSearchParam);
        List<OutOfStockWarningGoodsVo> outOfStockWarningGoodsVoList = new ArrayList<>();
        for (Goods goods : outOfStockWarningPage.getContent()) {
            outOfStockWarningGoodsVoList.add(buildOutOfStockWarningGoodsVo(goods));
        }
        return new PageImpl<>(outOfStockWarningGoodsVoList, new PageRequest(outOfStockWarningPage.getNumber(),outOfStockWarningPage.getSize()), outOfStockWarningPage.getTotalElements());
    }

    /**
     * 构建缺货预警对象
     *
     * @param goods
     * @return
     */
    private OutOfStockWarningGoodsVo buildOutOfStockWarningGoodsVo(Goods goods) {
        OutOfStockWarningGoodsVo outOfStockWarningGoodsVo = new OutOfStockWarningGoodsVo();
        outOfStockWarningGoodsVo.setGoodsCode(goods.getGoodsCode());
        outOfStockWarningGoodsVo.setGoodsNm(goods.getGoodsNm());
        outOfStockWarningGoodsVo.setCommonNm(goods.getCommonNm());
        outOfStockWarningGoodsVo.setSpec(goods.getSpec());
        outOfStockWarningGoodsVo.setUnit(goods.getUnit());
        outOfStockWarningGoodsVo.setProduceManufacturer(goods.getProduceManufacturer());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:  //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
                if (goodsChineseMedicinePieces != null) {
                    outOfStockWarningGoodsVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                    SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                    outOfStockWarningGoodsVo.setDosageForm(sysDictItem2.getDictItemNm());
                    outOfStockWarningGoodsVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                }
                break;
            case DRUG:     //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (goodDrug != null) {
                    outOfStockWarningGoodsVo.setApprovalNumber(goodDrug.getApprovalNumber());
                    SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                    outOfStockWarningGoodsVo.setDosageForm(sysDictItem.getDictItemNm());
                }
                break;
            case DAILY_NECESSITIES:  //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                if (goodsDailyNecessities != null) {
                    outOfStockWarningGoodsVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                }
                break;
            case COSMETIC:   //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                if (goodsCosmetic != null) {
                    outOfStockWarningGoodsVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                }
                break;
            case OTHER:  //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                if (goodsOther != null) {
                    outOfStockWarningGoodsVo.setApprovalNumber(goodsOther.getApprovalNumber());
                }
                break;
        }
        //缺货数量
        Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());
        Long outOfStockQuantity = sku.getSecurityStock() - sku.getCurrentStock();
        outOfStockWarningGoodsVo.setOutOfStockQuantity(outOfStockQuantity);
        outOfStockWarningGoodsVo.setStockMoney(ServiceManager.goodsBatchService.findTotalGoodsAmountByGoodsId(goods.getId()));
        outOfStockWarningGoodsVo.setSecurityStock(sku.getSecurityStock());
        outOfStockWarningGoodsVo.setCurrentStock(ServiceManager.goodsBatchService.findCurrentStockByGoodsId(goods.getId()));
        return outOfStockWarningGoodsVo;
    }

    @Override
    public Page<GoodsActualStockPageVo> queryGoodsActualStock(Pageable pageable, Long shopId, String searchFields) {
        Page<Goods> goodsPage = getGoodsRepository().query(pageable, shopId, searchFields);
        List<GoodsActualStockPageVo> result = new ArrayList<>();
        for (Goods goods : goodsPage.getContent()) {

            GoodsActualStockPageVo stockPageVo = new GoodsActualStockPageVo();
            Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());
            stockPageVo.setGoodsCode(goods.getGoodsCode());
            stockPageVo.setGoodsNm(goods.getGoodsNm());
            stockPageVo.setCommonNm(goods.getCommonNm());
            stockPageVo.setSpec(goods.getSpec());
            stockPageVo.setUnit(goods.getUnit());
            stockPageVo.setCurrentStock(sku.getCurrentStock());
            stockPageVo.setRetailPrice(sku.getRetailPrice());
            stockPageVo.setProduceManufacturer(goods.getProduceManufacturer());

            if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
                GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (goodsDrug != null) {
                    SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
                    stockPageVo.setDosageForm(sysDictItem.getDictItemNm());
                    stockPageVo.setApprovalNumber(goodsDrug.getApprovalNumber());
                }
            } else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
                if (goodsChineseMedicinePieces != null) {
                    SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                    stockPageVo.setDosageForm(sysDictItem.getDictItemNm());
                    stockPageVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                    stockPageVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                }
            }else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.COSMETIC) {
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                stockPageVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
            }else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.OTHER) {
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                stockPageVo.setApprovalNumber(goodsOther.getApprovalNumber());
            }else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DAILY_NECESSITIES) {
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                stockPageVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
            }
            result.add(stockPageVo);
        }
        return new PageImpl<>(result, pageable, goodsPage.getTotalElements());
    }


    @Transactional
    @Override
    public Goods saveSpiltGoods(Long goodsId,Long shopId) {
        Goods goods = getGoodsRepository().findBySplitZeroSourceGoodsId(goodsId);
        //已经拆零过,直接返回
        if (goods != null) {
            return goods;
        }
        //未拆零过,创建拆零商品
        Goods sourceGoods = findOne(goodsId);
        Goods splitGoods = CommonUtil.copyProperties(sourceGoods,new Goods());
        splitGoods.setId(null);
        while (true){
            String goodsCode = getSpiltGoodsCode(sourceGoods.getGoodsCode());
            Goods checkGoods = getGoodsRepository().findByGoodsCodeAndShopId(goodsCode,shopId);
            if(checkGoods == null){
                splitGoods.setGoodsCode(goodsCode);
                break;
            }
        }

        splitGoods.setCommonNm("(拆零)"+sourceGoods.getCommonNm());
        splitGoods.setGoodsNm("(拆零)"+sourceGoods.getGoodsNm());


        //创建Sku
        Sku sourceSku = ServiceManager.skuService.findByGoodsId(sourceGoods.getId());
        splitGoods.setSplitZeroSourceGoodsId(goodsId);
        Long id = save(splitGoods).getId();
        splitGoods.setSpec(sourceSku.getSplitZeroSpec());
        splitGoods.setUnit(sourceSku.getSplitZeroUnit());
        save(sourceGoods);
        Sku splitSku = new Sku();
        splitSku.setGoodsId(id);
        splitSku.setSalesVolume(sourceSku.getSalesVolume());
        splitSku.setRetailPrice(sourceSku.getSplitZeroRetailPrice());
        splitSku.setMemberPrice(sourceSku.getSplitZeroMemberPrice());
        splitSku.setMarketPrice(sourceSku.getMarketPrice());
        splitSku.setCostPrice(sourceSku.getCostPrice());
        splitSku.setCurrentStock(0L);
        splitSku.setSecurityStock(0L);
        splitSku.setLockStockQuantity(0L);
        splitSku.setIsSpecialPriceGoods(sourceSku.getIsSpecialPriceGoods());
        splitSku.setIsSplitZero(BoolCodeEnum.NO.toCode());
        ServiceManager.skuService.save(splitSku);
        switch (GoodsTypeCodeEnum.fromCode(sourceGoods.getGoodsTypeCode())){
            case DRUG:
                GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsId);
                GoodsDrug newGoodsDrug = CommonUtil.copyProperties(goodsDrug,new GoodsDrug());
                newGoodsDrug.setId(null);
                newGoodsDrug.setGoodsId(id);
                ServiceManager.goodsDrugService.save(newGoodsDrug);
                break;
            case OTHER:
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodsId);
                GoodsOther newGoodsOther = CommonUtil.copyProperties(goodsOther,new GoodsOther());
                newGoodsOther.setGoodsId(id);
                newGoodsOther.setId(null);
                ServiceManager.goodsOtherService.save(newGoodsOther);
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsId);
                GoodsChineseMedicinePieces newGoodsChineseMedicinePieces = CommonUtil.copyProperties(goodsChineseMedicinePieces,new GoodsChineseMedicinePieces());
                newGoodsChineseMedicinePieces.setGoodsId(id);
                newGoodsChineseMedicinePieces.setId(null);
                ServiceManager.goodsChineseMedicinePiecesService.save(newGoodsChineseMedicinePieces);
                break;
            case FOOD_HEALTH:
                GoodsFoodHealth foodHealth = ServiceManager.goodsFoodHealthService.findByGoodsId(goodsId);
                GoodsFoodHealth newFoodHealth = CommonUtil.copyProperties(foodHealth,new GoodsFoodHealth());
                newFoodHealth.setGoodsId(id);
                newFoodHealth.setId(null);
                ServiceManager.goodsFoodHealthService.save(newFoodHealth);
                break;
            case DAILY_NECESSITIES:
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodsId);
                GoodsDailyNecessities newGoodsDailyNecessities = CommonUtil.copyProperties(goodsDailyNecessities,new GoodsDailyNecessities());
                newGoodsDailyNecessities.setGoodsId(id);
                newGoodsDailyNecessities.setId(null);
                ServiceManager.goodsDailyNecessitiesService.save(newGoodsDailyNecessities);
                break;
            case MEDICAL_INSTRUMENTS:
                GoodsMedicalInstruments medicalInstruments = ServiceManager.goodsMedicalInstrumentsService.findByGoodsId(goodsId);
                GoodsMedicalInstruments goodsMedicalInstruments = CommonUtil.copyProperties(medicalInstruments,new GoodsMedicalInstruments());
                goodsMedicalInstruments.setGoodsId(id);
                goodsMedicalInstruments.setId(null);
                ServiceManager.goodsMedicalInstrumentsService.save(goodsMedicalInstruments);
                break;
            case COSMETIC:
                GoodsCosmetic cosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodsId);
                GoodsCosmetic newCosmetic = CommonUtil.copyProperties(cosmetic,new GoodsCosmetic());
                newCosmetic.setGoodsId(id);
                newCosmetic.setId(null);
                ServiceManager.goodsCosmeticService.save(newCosmetic);
                break;
            default:
                break;
        }
        //拷贝一份图片
        List<FileMng> pictFileList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_PICT, goodsId);
        if(!CollectionUtils.isEmpty(pictFileList)){
            List<FileMng> list = new ArrayList<>();
            for(FileMng fileMng:pictFileList){
                FileMng temp = CommonUtil.copyProperties(fileMng, new FileMng());
                temp.setId(null);
                list.add(temp);
            }
            savePict(FileObjectTypeCodeEnum.GOODS_PICT, id, list);
        }

        //拷贝一份附件
        List<FileMng> otherFileList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_ATTACHMENT, goodsId);
        if(!CollectionUtils.isEmpty(otherFileList)){
            List<FileMng> list = new ArrayList<>();
            for(FileMng fileMng:otherFileList){
                FileMng temp = CommonUtil.copyProperties(fileMng, new FileMng());
                temp.setId(null);
                list.add(temp);
            }
            savePict(FileObjectTypeCodeEnum.GOODS_ATTACHMENT, id, list);
        }
        IndexBuilder.commitImmediately(sourceGoods.getId(), IndexTypeCodeEnum.GOODS);
        IndexBuilder.commitImmediately(splitGoods.getId(), IndexTypeCodeEnum.GOODS);
        return splitGoods;
    }

    @Override
    public Page<DecorationRecommendGoodsVo> queryDecorationRecommendGoods(Pageable pageable, Long groupId, Long shopId, Long salesCategoryId, String keyword) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
        .must(QueryBuilders.termQuery(GoodsEntity.IS_DELETE, BoolCodeEnum.NO.toCode()))
        .must(QueryBuilders.termQuery(GoodsEntity.SHOP_ID, shopId))
        .must(QueryBuilders.termQuery(GoodsEntity.IS_ENABLE, BoolCodeEnum.YES.toCode()))
        .must(QueryBuilders.termQuery(GoodsEntity.APPROVE_STATE_CODE, ApproveStateCodeEnum.PASS_APPROVE.toCode()));

        if (salesCategoryId != null){
             queryBuilder.must(QueryBuilders.termQuery(GoodsEntity.SALES_CATEGORY_IDS, salesCategoryId));
        }
        if (StringUtils.isNotBlank(keyword)) {
            BoolQueryBuilder subBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.termQuery(GoodsEntity.GOODS_NM, keyword))
                    .should(QueryBuilders.termQuery(GoodsEntity.COMMON_NM, keyword))
                    .should(QueryBuilders.termQuery(GoodsEntity.PINYIN, keyword));
            queryBuilder.must(subBuilder);
        }

        SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
                .prepareSearch(IndexTypeCodeEnum.GOODS.toCode())
                .setTypes(IndexTypeCodeEnum.GOODS.toCode())
                .setSearchType(SearchType.DEFAULT)
                .setQuery(queryBuilder)
                .addSort(GoodsEntity.DISPLAY_POSITION, SortOrder.ASC)
                .addStoredField(GoodsEntity.ID)
                .setFrom(pageable.getPageNumber() * pageable.getPageSize())
                .setSize(pageable.getPageSize())
                .setExplain(false);
        SearchResponse sr = searchRequestBuilder.get();
        List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
        List<DecorationRecommendGoodsVo> voList = new ArrayList<>();
        for (Map<String, Object> map : list){
            Long id = (Long) map.get(GoodsEntity.ID);
            voList.add(this.buildDecorationRecommendGoodsVo(id, groupId));
        }
        return new PageImpl<DecorationRecommendGoodsVo>(voList, pageable, sr.getHits().getTotalHits());
    }

    private DecorationRecommendGoodsVo buildDecorationRecommendGoodsVo(Long goodsId, Long groupId){
        Goods goods = this.findOne(goodsId);
        DecorationRecommendGoodsVo vo = CommonUtil.copyProperties(goods, new DecorationRecommendGoodsVo());
        Sku sku = ServiceManager.skuService.findByGoodsId(goodsId);
        vo.setGoodsId(goodsId);
        vo.setCurrentStock(sku.getCurrentStock());
        vo.setSkuId(sku.getId());
        vo.setRetailPrice(sku.getRetailPrice());
        vo.setRecommend(ServiceManager.decorationRecommendService.checkIsRecommend(goodsId, groupId));
        List<FileMng> list = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_PICT, goodsId);
        if (!CollectionUtils.isEmpty(list)){
            vo.setPicUrl(FileSystemEngine.getFileSystem().getUrl(list.get(0).getFileId()));
        }
        return vo;
    }

    /**
     *  拿到拆零后的商品编码
     * @param goodCode 源商品的商品编码
     * @return
     */
    private synchronized String getSpiltGoodsCode(String goodCode) {
        if(goodsCodeSeed<1000){ //防止在高并发的情况下出现多个相同的商品编码
            goodsCodeSeed++;
        }else {
            goodsCodeSeed = 0;
        }
        DecimalFormat format = new DecimalFormat("00");
        return goodCode + "_"+format.format(goodsCodeSeed);
    }


    @Override
    public Boolean exportExcel(String webRealPath, String xlsTemplatePath, String xlsOutputPath, GoodsListSearchParam goodsListSearchParam, Long shopId) {

        List<GoodsExcelVo> drugResultList = new ArrayList<>();
        List<GoodsExcelVo> otherResultList = new ArrayList<>();
        List<GoodsExcelVo> cmpResultList = new ArrayList<>();
        List<GoodsExcelVo> healthResultList = new ArrayList<>();
        List<GoodsExcelVo> dailyResultList = new ArrayList<>();
        List<GoodsExcelVo> miResultList = new ArrayList<>();
        List<GoodsExcelVo> cosmeticResultList = new ArrayList<>();

        List<GoodsListPageVo> resultList = listAll(goodsListSearchParam, shopId);
        for(GoodsListPageVo pageVo : resultList){

            GoodsExcelVo exportVo = buildGoodsExcelVo(pageVo);

            switch (GoodsTypeCodeEnum.fromCode(exportVo.getGoodsTypeCode())) {
                case DRUG:
                    setGoodsDrugExportInfo(exportVo);
                    drugResultList.add(exportVo);
                    break;
                case OTHER:
                    setGoodsOtherExportInfo(exportVo);
                    otherResultList.add(exportVo);
                    break;
                case CHINESE_MEDICINE_PIECES:
                    setGoodsChineseMedicinePiecesExportInfo(exportVo);
                    cmpResultList.add(exportVo);
                    break;
                case FOOD_HEALTH:
                    setGoodsFoodHealthExportInfo(exportVo);
                    healthResultList.add(exportVo);
                    break;
                case DAILY_NECESSITIES:
                    setGoodsDailyNecessitiesExportInfo(exportVo);
                    dailyResultList.add(exportVo);
                    break;
                case MEDICAL_INSTRUMENTS:
                    setGoodsMedicalInstrumentsExportInfo(exportVo);
                    miResultList.add(exportVo);
                    break;
                case COSMETIC:
                    setGoodsCosmeticExportInfo(exportVo);
                    cosmeticResultList.add(exportVo);
                    break;
                default:
                    break;
            }
        }

        String fileMngTempPath = webRealPath + "xlsoutput/Goods/" + getUUIDPath();
        File file = new File(fileMngTempPath);
        file.mkdir();
        try {
            if(!drugResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/drug.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsDrug.xls");
                listExcelWriter.fillToFile(drugResultList, xlsOutputExcelPath);
            }
            if(!otherResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/other.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsOther.xls");
                listExcelWriter.fillToFile(otherResultList, xlsOutputExcelPath);
            }
            if(!cmpResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/cmp.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsChineseMedicinePieces.xls");
                listExcelWriter.fillToFile(cmpResultList, xlsOutputExcelPath);
            }
            if(!healthResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/health.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsFoodHealth.xls");
                listExcelWriter.fillToFile(healthResultList, xlsOutputExcelPath);
            }
            if(!dailyResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/daily.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsDailyNecessities.xls");
                listExcelWriter.fillToFile(dailyResultList, xlsOutputExcelPath);
            }
            if(!miResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/instruments.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsMedicalInstruments.xls");
                listExcelWriter.fillToFile(miResultList, xlsOutputExcelPath);
            }
            if(!cosmeticResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/cosmetic.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsCosmetic.xls");
                listExcelWriter.fillToFile(cosmeticResultList, xlsOutputExcelPath);
            }
            zipFiles(fileMngTempPath, xlsOutputPath);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private GoodsExcelVo buildGoodsExcelVo(GoodsListPageVo pageVo) {
        Goods goods = getGoodsRepository().findOne(pageVo.getId());
        GoodsExcelVo exportVo = CommonUtil.copyProperties(goods, new GoodsExcelVo());
        if(goods.getSellCategoryIds()!=null){
            StringBuffer sellsCategoryStr = new StringBuffer();
            String[] categoryNames = goods.getSellCategoryIds().split(",");
            for(int i=0; i< categoryNames.length; i++){
                String categoryIdStr = categoryNames[i];
                SalesCategory salesCategory = ServiceManager.salesCategoryService.findOne(Long.parseLong(categoryIdStr));
                sellsCategoryStr.append(salesCategory.getCategoryName());
                if( i< categoryNames.length-1){
                    sellsCategoryStr.append(",");
                }
            }
            exportVo.setSellCategoryNames(sellsCategoryStr.toString());
        }
        if(StringUtils.isNotBlank(goods.getGoodsTypeCode())){
            exportVo.setGoodsTypeName(GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()).toName());
        }
        if(StringUtils.isNotBlank(goods.getToxicologyCode())){
            exportVo.setToxicologyName(ToxicologyTypeCodeEnum.fromCode(goods.getToxicologyCode()).toName());
        }
        if(StringUtils.isNotBlank(goods.getStorageCondition())){
            exportVo.setStorageConditionName(StorageConditionTypeCodeEnum.fromCode(goods.getStorageCondition()).toName());
        }
        if(StringUtils.isNotBlank(goods.getApproveStateCode())){
            exportVo.setApproveStateName(GoodsApproveStateCodeEnum.fromCode(goods.getApproveStateCode()).toName());
        }
        if(StringUtils.isNotBlank(goods.getIsEnable())){
            exportVo.setIsEnableName(BoolCodeEnum.fromCode(goods.getIsEnable()).toName());
        }
        if(StringUtils.isNotBlank(goods.getIsFirstSell())){
            exportVo.setIsFirstSellName(BoolCodeEnum.fromCode(goods.getIsFirstSell()).toName());
        }

        Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());
        exportVo.setRetailPrice(sku.getRetailPrice());
        exportVo.setMemberPrice(sku.getMemberPrice());
        exportVo.setMarketPrice(sku.getMarketPrice());
        exportVo.setCostPrice(sku.getCostPrice());
        return exportVo;
    }

    private void setGoodsDrugExportInfo(GoodsExcelVo exportVo) {
        GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(exportVo.getId());

        if(goodsDrug == null) {
            return;
        }
        exportVo.setApprovalNumber(goodsDrug.getApprovalNumber());
        exportVo.setApprovalNumberTerm(goodsDrug.getApprovalNumberTermString());
        exportVo.setIsImportGoodsName(BoolCodeEnum.fromCode(goodsDrug.getIsImportGoods()).toName());
        exportVo.setIsChineseMedicineProtectName(BoolCodeEnum.fromCode(goodsDrug.getIsImportGoods()).toName());
        exportVo.setApproveDate(goodsDrug.getApproveDateString());
        SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
        if(sysDictItem != null) {
            exportVo.setDosageForm(sysDictItem.getDictItemNm());
        }
        exportVo.setPrescriptionDrugsTypeCode(PrescriptionDrugsTypeCodeEnum.fromCode(goodsDrug.getPrescriptionDrugsTypeCode()).toName());
        exportVo.setIsEphedrineName(BoolCodeEnum.fromCode(goodsDrug.getIsEphedrine()).toName());
        exportVo.setIsKeyCuringName(BoolCodeEnum.fromCode(goodsDrug.getIsKeyCuring()).toName());
        exportVo.setIsMedicalInsuranceGoodsName(BoolCodeEnum.fromCode(goodsDrug.getIsMedicalInsuranceGoods()).toName());
        if(StringUtils.isNotBlank(goodsDrug.getMedicalInsuranceNum())) {
            exportVo.setMedicalInsuranceNum(goodsDrug.getMedicalInsuranceNum());
        }
    }

    private void setGoodsOtherExportInfo(GoodsExcelVo exportVo) {
        GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(exportVo.getId());
        if(goodsOther == null) {
            return;
        }

        exportVo.setApprovalNumber(goodsOther.getApprovalNumber());
        exportVo.setManufacturerAddr(goodsOther.getManufacturerAddr());
    }

    private void setGoodsChineseMedicinePiecesExportInfo(GoodsExcelVo exportVo) {
        GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(exportVo.getId());
        if(goodsChineseMedicinePieces == null) {
            return;
        }
        exportVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
        exportVo.setApprovalNumberTerm(goodsChineseMedicinePieces.getApprovalNumberTermString());
        exportVo.setIsImportGoodsName(BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsImportGoods()).toName());
        exportVo.setIsChineseMedicineProtectName(BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsImportGoods()).toName());
        exportVo.setApproveDate(goodsChineseMedicinePieces.getApproveDateString());
        SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
        if(sysDictItem != null) {
            exportVo.setDosageForm(sysDictItem.getDictItemNm());
        }
        exportVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
        exportVo.setEffect(goodsChineseMedicinePieces.getEffect());
        exportVo.setPrescriptionDrugsTypeCode(PrescriptionDrugsTypeCodeEnum.fromCode(goodsChineseMedicinePieces.getPrescriptionDrugsTypeCode()).toName());
        exportVo.setIsEphedrineName(BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsEphedrine()).toName());
        exportVo.setIsKeyCuringName(BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsKeyCuring()).toName());
        exportVo.setIsMedicalInsuranceGoodsName(BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsMedicalInsuranceGoods()).toName());
        exportVo.setMedicalInsuranceNum(goodsChineseMedicinePieces.getMedicalInsuranceNum());
    }

    private void setGoodsFoodHealthExportInfo(GoodsExcelVo exportVo) {
        GoodsFoodHealth goodsFoodHealth = ServiceManager.goodsFoodHealthService.findByGoodsId(exportVo.getId());
        if(goodsFoodHealth == null) {
            return;
        }

        exportVo.setFoodHygieneLicenceNum(goodsFoodHealth.getFoodHygieneLicenceNum());
        exportVo.setProductionDate(goodsFoodHealth.getProductionDateString());
        exportVo.setExpirationDate(goodsFoodHealth.getExpirationDateString());
        exportVo.setHealthCareFunc(goodsFoodHealth.getHealthCareFunc());
        exportVo.setAppropriateCrowd(goodsFoodHealth.getAppropriateCrowd());
        exportVo.setEdibleMethodAndDosage(goodsFoodHealth.getEdibleMethodAndDosage());
        exportVo.setStorageMethod(goodsFoodHealth.getStorageMethod());
        exportVo.setExecStandard(goodsFoodHealth.getExecStandard());
        exportVo.setEffectComposition(goodsFoodHealth.getEffectComposition());
        exportVo.setNotice(goodsFoodHealth.getNotice());
        exportVo.setNotAppropriateCrowd(goodsFoodHealth.getNotAppropriateCrowd());
    }

    private void setGoodsDailyNecessitiesExportInfo(GoodsExcelVo exportVo) {
        GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(exportVo.getId());
        if(goodsDailyNecessities == null) {
            return;
        }
        exportVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
        exportVo.setManufacturerAddr(goodsDailyNecessities.getManufacturerAddr());

    }

    private void setGoodsMedicalInstrumentsExportInfo(GoodsExcelVo exportVo) {
        GoodsMedicalInstruments medicalInstruments = ServiceManager.goodsMedicalInstrumentsService.findByGoodsId(exportVo.getId());
        if(medicalInstruments == null) {
            return;
        }

        exportVo.setRegNum(medicalInstruments.getRegNum());
        exportVo.setRegRegistrationFormNum(medicalInstruments.getRegRegistrationFormNum());
        exportVo.setManufacturerAddr(medicalInstruments.getManufacturerAddr());
        exportVo.setApplyRange(medicalInstruments.getApplyRange());
        exportVo.setProductStandardNum(medicalInstruments.getProductStandardNum());
    }

    private void setGoodsCosmeticExportInfo(GoodsExcelVo exportVo) {
        GoodsCosmetic cosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(exportVo.getId());
        if(cosmetic == null) {
            return;
        }
        exportVo.setApprovalNumber(cosmetic.getApprovalNumber());
        exportVo.setManufacturerAddr(cosmetic.getManufacturerAddr());
    }

    /**
     * 压缩文件
     * @param zipFile 需要压缩的文件
     * @param zipFilePath 压缩文件保存路径
     *
     */
    private void zipFiles(String zipFile, String zipFilePath){
        SevenZipUtils.zipFiles(new String[]{zipFile}, zipFilePath, new SevenZipUtils.ZipInterceptor(){

            @Override
            //过滤压缩文件return false
            public boolean beforeEachZip(File file) {
                return true;
            }

            @Override
            public void afterEachZip(SevenZArchiveEntry zipEntry) {
            }
        });
    }

    @Autowired
    private JpaTransactionManager jpaTransactionManager;

    @Transactional(propagation = Propagation.NOT_SUPPORTED) //以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
    public List<ErrorLog> importData(String localFileId, Long shopId) {
        List<SysDictItem> sysDictItemList = ServiceManager.sysDictItemService.findByDataDictId(Global.DICT_DOSAGE_FORM_ID);
        final Map<String, String> sysDictItemMap = new HashedMap();
        for(SysDictItem sysDictItem : sysDictItemList) {
            sysDictItemMap.put(sysDictItem.getDictItemNm(), sysDictItem.getDictItemCode());
        }

        List<SalesCategorySimpleVo> salesCategoryList = ServiceManager.salesCategoryService.listForGoodsAdd(shopId);
        final Map<String, Long> salesCategoryMap = new HashedMap();

        for(SalesCategorySimpleVo categorySimpleVo : salesCategoryList) {
            salesCategoryMap.put(categorySimpleVo.getCategoryName(), categorySimpleVo.getId());
        }

        BaseExcelReader<GoodsExcelVo> excelReader = new BaseExcelReader<GoodsExcelVo>() {

            @Override
            protected Map<String, Object> convert(Map<String, Object> valueMap) {

                return valueMap;
            }

            @Override
            public boolean verifyExt(int lineNumber, Collection<String> protocolList, Map<String, Object> valueMap, List<ErrorLog> errorLogList) {

                String sellCategoryNames = (String) valueMap.get("sellCategoryNames");
                if(sellCategoryNames != null){
                    List<Long> sellCategoryIds = new ArrayList<>();
                    String[] sellCategoryList = sellCategoryNames.split(",|，");
                    for(String sellCategoryName : sellCategoryList) {
                        Long categoryId = salesCategoryMap.get(sellCategoryName);
                        if (categoryId == null) {
                            errorLogList.add(new ErrorLog(lineNumber, "销售分类[" + sellCategoryNames + "]不存在..."));
                            return false;
                        }
                        sellCategoryIds.add(categoryId);
                    }
                    valueMap.put(Goods.SELL_CATEGORY_IDS, StringUtils.join(sellCategoryIds, ","));
                }

                String goodsTypeName = (String) valueMap.get("goodsTypeName");
                if(goodsTypeName!=null){
                    boolean isFalse = true;
                    for (GoodsTypeCodeEnum codeEnum : GoodsTypeCodeEnum.values()) {
                        if (codeEnum.toName().equals(goodsTypeName)) {
                            valueMap.put(Goods.GOODS_TYPE_CODE, codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "商品类型[" + goodsTypeName + "]不存在..."));
                        return false;
                    }
                }

                String toxicologyName = (String) valueMap.get("toxicologyName");
                if(toxicologyName!=null){
                    boolean isFalse = true;
                    for (ToxicologyTypeCodeEnum codeEnum : ToxicologyTypeCodeEnum.values()) {
                        if (codeEnum.toName().equals(toxicologyName)) {
                            valueMap.put(Goods.TOXICOLOGY_CODE, codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "毒理[" + toxicologyName + "]不存在..."));
                        return false;
                    }
                }

                String storageConditionName = (String) valueMap.get("storageConditionName");
                if(storageConditionName!=null){
                    boolean isFalse = true;
                    for (StorageConditionTypeCodeEnum codeEnum : StorageConditionTypeCodeEnum.values()) {
                        if (codeEnum.toName().equals(storageConditionName)) {
                            valueMap.put(Goods.STORAGE_CONDITION, codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "储存条件[" + storageConditionName + "]不存在..."));
                        return false;
                    }
                }

                String isImportGoodsName = (String) valueMap.get("isImportGoodsName");
                if(isImportGoodsName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isImportGoodsName)) {
                            valueMap.put("isImportGoodsName", codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否进口药品[" + isImportGoodsName + "]不存在..."));
                        return false;
                    }
                }

                String isChineseMedicineProtectName = (String) valueMap.get("isChineseMedicineProtectName");
                if(isChineseMedicineProtectName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isChineseMedicineProtectName)) {
                            valueMap.put("isChineseMedicineProtectName", codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否中药保护[" + isChineseMedicineProtectName + "]不存在..."));
                        return false;
                    }
                }


                String dosageForm = (String) valueMap.get("dosageForm");
                if(StringUtils.isNotBlank(dosageForm)) {
                    String dictItemCode = sysDictItemMap.get(dosageForm.trim());
                    if (StringUtils.isBlank(dictItemCode)) {
                        errorLogList.add(new ErrorLog(lineNumber, "剂型[" + dosageForm + "]不存在..."));
                        return false;
                    } else {
                        valueMap.put("dosageForm", dictItemCode);
                    }
                }

                String prescriptionDrugsTypeCode = (String) valueMap.get("prescriptionDrugsTypeCode");
                if(prescriptionDrugsTypeCode!=null){
                    boolean isFalse = true;
                    for (PrescriptionDrugsTypeCodeEnum codeEnum : PrescriptionDrugsTypeCodeEnum.values()) {
                        if (codeEnum.toName().equals(prescriptionDrugsTypeCode)) {
                            valueMap.put("prescriptionDrugsTypeCode", codeEnum.toCode());
                            isFalse = false;
                            break;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "处方药类型[" + prescriptionDrugsTypeCode + "]不存在..."));
                        return false;
                    }
                }

                String isEphedrineName = (String) valueMap.get("isEphedrineName");
                if(isEphedrineName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isEphedrineName)) {
                            valueMap.put("isEphedrineName", codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否麻黄碱[" + isEphedrineName + "]不存在..."));
                        return false;
                    }

                }

                String isKeyCuringName = (String) valueMap.get("isKeyCuringName");
                if(isKeyCuringName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isKeyCuringName)) {
                            valueMap.put("isKeyCuringName", codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否重点养护[" + isKeyCuringName + "]不存在..."));
                        return false;
                    }

                }

                String isMedicalInsuranceGoodsName = (String) valueMap.get("isMedicalInsuranceGoodsName");
                if(isMedicalInsuranceGoodsName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isMedicalInsuranceGoodsName)) {
                            valueMap.put("isMedicalInsuranceGoodsName", codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否医保商品[" + isMedicalInsuranceGoodsName + "]不存在..."));
                        return false;
                    }
                }

                String isEnableName = (String) valueMap.get("isEnableName");
                if(isEnableName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isEnableName)) {
                            valueMap.put(Goods.IS_ENABLE, codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否启用[" + isMedicalInsuranceGoodsName + "]不存在..."));
                        return false;
                    }
                }

                String isFirstSellName = (String) valueMap.get("isFirstSellName");
                if(isFirstSellName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isFirstSellName)) {
                            valueMap.put(Goods.IS_FIRST_SELL, codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否首营[" + isMedicalInsuranceGoodsName + "]不存在..."));
                        return false;
                    }

                }

                return true;
            }

            @Override
            protected Class getBeanClass() {
                return GoodsExcelVo.class;
            }
        };

        List<ErrorLog> errorLogs = new ArrayList<>();
        List<GoodsExcelVo> inputList = excelReader.importData(localFileId, errorLogs);
        int index = 1;
        for (GoodsExcelVo bean : inputList) {
            DefaultTransactionDefinition td = new DefaultTransactionDefinition();
            td.setName("Process Import Goods " + index);
            index ++;
            //使用底层数据库默认隔离级别。
            td.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
            //支持现有的事务，如果没有则新建一个事务。
            td.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus txs = jpaTransactionManager.getTransaction(td);
            try {
                //提交事务
                Goods dbGoods = getGoodsRepository().findByGoodsCodeAndShopId(bean.getGoodsCode(), shopId);
                if(dbGoods == null){
                    dbGoods = new Goods();
                }

                dbGoods.setShopId(shopId);
                dbGoods.setSellCategoryIds(bean.getSellCategoryIds());
                dbGoods.setGoodsNm(bean.getGoodsNm());
                dbGoods.setGoodsCode(bean.getGoodsCode());
                dbGoods.setBrandNm(bean.getBrandNm());
                dbGoods.setBarCode(bean.getBarCode());
                dbGoods.setGoodsTypeCode(bean.getGoodsTypeCode());
                dbGoods.setCommonNm(bean.getCommonNm());
                dbGoods.setSpec(bean.getSpec());
                dbGoods.setUnit(bean.getUnit());
                dbGoods.setToxicologyCode(bean.getToxicologyCode());
                dbGoods.setStorageCondition(bean.getStorageCondition());
                dbGoods.setProduceManufacturer(bean.getProduceManufacturer());
                dbGoods.setIsDelete(bean.getIsDelete());
                dbGoods.setDisplayPosition(bean.getDisplayPosition());
                dbGoods.setApproveStateCode(bean.getApproveStateCode());
                dbGoods.setIsEnable(bean.getIsEnable());
                dbGoods.setSubmitIdea(bean.getSubmitIdea());
                dbGoods.setPurchaseTaxRate(bean.getPurchaseTaxRate());
                dbGoods.setSellTaxRate(bean.getSellTaxRate());
                dbGoods.setRemark(bean.getRemark());
                dbGoods.setIsFirstSell(bean.getIsFirstSell());
                dbGoods.setDisplayPosition(1L);
                dbGoods.setIsDelete(BoolCodeEnum.NO.toCode());
                dbGoods.setApproveStateCode(GoodsApproveStateCodeEnum.PASS_APPROVE.toCode());
                dbGoods.setPinyin(StringUtils.isNotBlank(bean.getCommonNm())?Pinyin4jUtil.getPinYin(bean.getCommonNm()):"");
                save(dbGoods);

                //导入保存商品不立即返回索引创建信息
                ServiceManager.esIndexQueueService.saveQueue(IndexTypeCodeEnum.GOODS.toCode(), dbGoods.getId(), null);

                if(BoolCodeEnum.YES == BoolCodeEnum.fromCode(bean.getIsFirstSell())) {
                    setGoodsFirstSellInfo(dbGoods, bean);
                }
                setGoodsSkuImportInfo(dbGoods.getId(), bean);

                switch (GoodsTypeCodeEnum.fromCode(bean.getGoodsTypeCode())) {
                    case DRUG:
                        setGoodsDrugImportInfo(dbGoods.getId(), bean);
                        break;
                    case OTHER:
                        setGoodsOtherImportInfo(dbGoods.getId(), bean);
                        break;
                    case CHINESE_MEDICINE_PIECES:
                        setGoodsChineseMedicinePiecesImportInfo(dbGoods.getId(), bean);
                        break;
                    case FOOD_HEALTH:
                        setGoodsFoodHealthImportInfo(dbGoods.getId(), bean);
                        break;
                    case DAILY_NECESSITIES:
                        setGoodsDailyNecessitiesImportInfo(dbGoods.getId(), bean);
                        break;
                    case MEDICAL_INSTRUMENTS:
                        setGoodsMedicalInstrumentsImportInfo(dbGoods.getId(), bean);
                        break;
                    case COSMETIC:
                        setGoodsCosmeticImportInfo(dbGoods.getId(), bean);
                        break;
                    default:
                        break;
                }

                jpaTransactionManager.commit(txs);
            } catch(DataIntegrityViolationException dive) {
                if (!txs.isCompleted()) {
                    //回滚事务
                    jpaTransactionManager.rollback(txs);
                }
                throw new BusinessException(ResGlobal.COMMON_ERROR, dive);
            }
        }
        return errorLogs;
    }

    @Transactional
    private void setGoodsFirstSellInfo(Goods goods, GoodsExcelVo bean) {
        FirstManageDrugQualityApprove firstManageDrugQualityApprove =  ServiceManager.firstManageDrugQualityApproveService.findByGoodsId(goods.getId());
        if(firstManageDrugQualityApprove == null) {
            firstManageDrugQualityApprove = new FirstManageDrugQualityApprove();
        }
        firstManageDrugQualityApprove.setShopId(goods.getShopId());
        firstManageDrugQualityApprove.setGoodsId(goods.getId());
        firstManageDrugQualityApprove.setGoodsCode(goods.getGoodsCode());
        firstManageDrugQualityApprove.setGoodsNm(goods.getGoodsNm());
        firstManageDrugQualityApprove.setCommonNmFirstSpell(goods.getPinyin());
        firstManageDrugQualityApprove.setCommonNm(goods.getCommonNm());
        firstManageDrugQualityApprove.setDosageForm(bean.getDosageForm());
        firstManageDrugQualityApprove.setUnit(goods.getUnit());
        firstManageDrugQualityApprove.setSpec(goods.getSpec());
        firstManageDrugQualityApprove.setProduceManufacturer(goods.getProduceManufacturer());
        firstManageDrugQualityApprove.setApprovalNumber(bean.getApprovalNumber());
        firstManageDrugQualityApprove.setApproveStateCode(ApproveStateCodeEnum.WAIT_APPROVE.toCode());
        firstManageDrugQualityApprove.setApplyManName(goods.getCreateBy());
        firstManageDrugQualityApprove.setSubmitAdvice(goods.getSubmitIdea());
        firstManageDrugQualityApprove.setApplyRemark(goods.getRemark());
        ServiceManager.firstManageDrugQualityApproveService.save(firstManageDrugQualityApprove);
    }


    @Transactional
    private void setGoodsSkuImportInfo(Long goodsId, GoodsExcelVo bean) {
        Sku sku = ServiceManager.skuService.findByGoodsId(goodsId);
        if(sku == null) {
            sku = new Sku();
            sku.setGoodsId(goodsId);
            sku.setSalesVolume(0L);
            sku.setCurrentStock(0L);
            sku.setSecurityStock(0L);
            sku.setLockStockQuantity(0L);
        }
        sku.setRetailPrice(bean.getRetailPrice());
        sku.setMemberPrice(bean.getMemberPrice());
        sku.setMarketPrice(bean.getMarketPrice());
        sku.setCostPrice(bean.getCostPrice());
        ServiceManager.skuService.save(sku);
    }

    @Transactional
    private void setGoodsDrugImportInfo(Long goodsId, GoodsExcelVo docExportVo) {
        GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsId);

        if(goodsDrug == null) {
            goodsDrug = new GoodsDrug();
        }
        goodsDrug.setApprovalNumber(docExportVo.getApprovalNumber());
        try {
            if(StringUtils.isNotBlank(docExportVo.getApprovalNumberTerm())) {
                goodsDrug.setApprovalNumberTermString(docExportVo.getApprovalNumberTerm());
            }
            if(StringUtils.isNotBlank(docExportVo.getApproveDate())) {
                goodsDrug.setApproveDateString(docExportVo.getApproveDate());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        goodsDrug.setIsImportGoods(docExportVo.getIsImportGoodsName());
        goodsDrug.setIsChineseMedicineProtect(docExportVo.getIsImportGoodsName());
        goodsDrug.setDosageForm(docExportVo.getDosageForm());
        goodsDrug.setPrescriptionDrugsTypeCode(docExportVo.getPrescriptionDrugsTypeCode());
        goodsDrug.setIsEphedrine(docExportVo.getIsEphedrineName());
        goodsDrug.setIsKeyCuring(docExportVo.getIsKeyCuringName());
        goodsDrug.setIsMedicalInsuranceGoods(docExportVo.getIsMedicalInsuranceGoodsName());
        goodsDrug.setMedicalInsuranceNum(docExportVo.getMedicalInsuranceNum());
        goodsDrug.setGoodsId(goodsId);
        ServiceManager.goodsDrugService.save(goodsDrug);
    }

    @Transactional
    private void setGoodsOtherImportInfo(Long goodsId, GoodsExcelVo docExportVo) {
        GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodsId);
        if(goodsOther == null) {
            goodsOther = new GoodsOther();
        }

        goodsOther.setApprovalNumber(docExportVo.getApprovalNumber());
        goodsOther.setManufacturerAddr(docExportVo.getManufacturerAddr());
        goodsOther.setGoodsId(goodsId);
        ServiceManager.goodsOtherService.save(goodsOther);
    }

    @Transactional
    private void setGoodsChineseMedicinePiecesImportInfo(Long goodsId, GoodsExcelVo docExportVo) {
        GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsId);
        if(goodsChineseMedicinePieces == null) {
            goodsChineseMedicinePieces = new GoodsChineseMedicinePieces();
        }
        goodsChineseMedicinePieces.setApprovalNumber(docExportVo.getApprovalNumber());
        try {
            if(StringUtils.isNotBlank(docExportVo.getApprovalNumberTerm())) {
                goodsChineseMedicinePieces.setApprovalNumberTermString(docExportVo.getApprovalNumberTerm());
            }
            if(StringUtils.isNotBlank(docExportVo.getApproveDate())) {
                goodsChineseMedicinePieces.setApproveDateString(docExportVo.getApproveDate());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        goodsChineseMedicinePieces.setIsImportGoods(docExportVo.getIsImportGoodsName());
        goodsChineseMedicinePieces.setIsChineseMedicineProtect(docExportVo.getIsImportGoodsName());
        goodsChineseMedicinePieces.setDosageForm(docExportVo.getDosageForm());
        goodsChineseMedicinePieces.setProductionPlace(docExportVo.getProductionPlace());
        goodsChineseMedicinePieces.setEffect(docExportVo.getEffect());
        goodsChineseMedicinePieces.setPrescriptionDrugsTypeCode(docExportVo.getPrescriptionDrugsTypeCode());
        goodsChineseMedicinePieces.setIsEphedrine(docExportVo.getIsEphedrineName());
        goodsChineseMedicinePieces.setIsKeyCuring(docExportVo.getIsKeyCuringName());
        goodsChineseMedicinePieces.setIsMedicalInsuranceGoods(docExportVo.getIsMedicalInsuranceGoodsName());
        goodsChineseMedicinePieces.setMedicalInsuranceNum(docExportVo.getMedicalInsuranceNum());
        goodsChineseMedicinePieces.setGoodsId(goodsId);
        ServiceManager.goodsChineseMedicinePiecesService.save(goodsChineseMedicinePieces);
    }

    @Transactional
    private void setGoodsFoodHealthImportInfo(Long goodsId, GoodsExcelVo docExportVo) {
        GoodsFoodHealth goodsFoodHealth = ServiceManager.goodsFoodHealthService.findByGoodsId(goodsId);
        if(goodsFoodHealth == null) {
            goodsFoodHealth = new GoodsFoodHealth();
        }

        try {
            goodsFoodHealth.setProductionDateString(docExportVo.getProductionDate());
            goodsFoodHealth.setExpirationDateString(docExportVo.getExpirationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        goodsFoodHealth.setFoodHygieneLicenceNum(docExportVo.getFoodHygieneLicenceNum());
        goodsFoodHealth.setHealthCareFunc(docExportVo.getHealthCareFunc());
        goodsFoodHealth.setAppropriateCrowd(docExportVo.getAppropriateCrowd());
        goodsFoodHealth.setEdibleMethodAndDosage(docExportVo.getEdibleMethodAndDosage());
        goodsFoodHealth.setStorageMethod(docExportVo.getStorageMethod());
        goodsFoodHealth.setExecStandard(docExportVo.getExecStandard());
        goodsFoodHealth.setEffectComposition(docExportVo.getEffectComposition());
        goodsFoodHealth.setNotice(docExportVo.getNotice());
        goodsFoodHealth.setNotAppropriateCrowd(docExportVo.getNotAppropriateCrowd());
        goodsFoodHealth.setGoodsId(goodsId);
        ServiceManager.goodsFoodHealthService.save(goodsFoodHealth);
    }

    @Transactional
    private void setGoodsDailyNecessitiesImportInfo(Long goodsId, GoodsExcelVo docExportVo) {
        GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodsId);
        if(goodsDailyNecessities == null) {
            goodsDailyNecessities = new GoodsDailyNecessities();
        }
        goodsDailyNecessities.setApprovalNumber(docExportVo.getApprovalNumber());
        goodsDailyNecessities.setManufacturerAddr(docExportVo.getManufacturerAddr());
        goodsDailyNecessities.setGoodsId(goodsId);
        ServiceManager.goodsDailyNecessitiesService.save(goodsDailyNecessities);
    }

    @Transactional
    private void setGoodsMedicalInstrumentsImportInfo(Long goodsDocId, GoodsExcelVo docExportVo) {
        GoodsMedicalInstruments medicalInstruments = ServiceManager.goodsMedicalInstrumentsService.findByGoodsId(goodsDocId);
        if(medicalInstruments == null) {
            medicalInstruments = new GoodsMedicalInstruments();
        }

        medicalInstruments.setRegNum(docExportVo.getRegNum());
        medicalInstruments.setRegRegistrationFormNum(docExportVo.getRegRegistrationFormNum());
        medicalInstruments.setManufacturerAddr(docExportVo.getManufacturerAddr());
        medicalInstruments.setApplyRange(docExportVo.getApplyRange());
        medicalInstruments.setProductStandardNum(docExportVo.getProductStandardNum());
        medicalInstruments.setGoodsId(goodsDocId);
        ServiceManager.goodsMedicalInstrumentsService.save(medicalInstruments);
    }

    @Transactional
    private void setGoodsCosmeticImportInfo(Long goodsId, GoodsExcelVo docExportVo) {
        GoodsCosmetic cosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodsId);
        if(cosmetic == null) {
            cosmetic = new GoodsCosmetic();
        }
        cosmetic.setApprovalNumber(docExportVo.getApprovalNumber());
        cosmetic.setManufacturerAddr(docExportVo.getManufacturerAddr());
        cosmetic.setGoodsId(goodsId);
        ServiceManager.goodsCosmeticService.save(cosmetic);
    }

    private String getUUIDPath() {
        StringBuilder builder = new StringBuilder();
        builder.append("goods").append("_");
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        String uuid = UUID.randomUUID().toString();
        builder.append(cal.get(Calendar.YEAR))
                .append("_").append(cal.get(Calendar.MONTH) + 1)
                .append("_").append(cal.get(Calendar.DAY_OF_MONTH))
                .append("_").append(uuid);
        return builder.toString();
    }

}