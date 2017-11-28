
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.StockCheckStateCodeEnum;
import com.imall.iportal.core.shop.entity.StockCheck;
import com.imall.iportal.core.shop.repository.StockCheckRepositoryCustom;
import com.imall.iportal.core.shop.vo.StockCheckSearchParam;
import com.imall.iportal.core.shop.vo.StockPostingSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class StockCheckRepositoryImpl implements StockCheckRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<StockCheck> query(Pageable pageable, StockCheckSearchParam stockCheckSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_stock_check b where 1=1 ");

        if (stockCheckSearchParam.getShopId() != null) {
            sql.append(" and SHOP_ID =:shopId");
            paramMap.put(StockCheck.SHOP_ID, stockCheckSearchParam.getShopId());
        }

        //盘点单号
        if (StringUtils.isNotBlank(stockCheckSearchParam.getCheckOrderNum())) {
            sql.append(" and CHECK_ORDER_NUM like:checkOrderNum");
            paramMap.put(StockCheck.CHECK_ORDER_NUM,"%" + stockCheckSearchParam.getCheckOrderNum() + "%");
        }

        //是否已盘点
        if (StringUtils.isNotBlank(stockCheckSearchParam.getCheckedStateCode())) {
            sql.append(" and CHECKED_STATE_CODE =:checkedStateCode");
            paramMap.put(StockCheck.CHECKED_STATE_CODE, StockCheckStateCodeEnum.fromCode(stockCheckSearchParam.getCheckedStateCode()).toCode());
        }

        //创建时间
        if (StringUtils.isNotBlank(stockCheckSearchParam.getCreateDateBeginString())) {
            sql.append(" and OPERATION_TIME >= :fromDate");
            paramMap.put("fromDate", DateTimeUtils.getDayStartTime(stockCheckSearchParam.getCreateDateBeginString()));
        }

        if (StringUtils.isNotBlank(stockCheckSearchParam.getCreateDateEndString())) {
            sql.append(" and OPERATION_TIME <= :toDate ");
            paramMap.put("toDate", DateTimeUtils.getDayEndTime(stockCheckSearchParam.getCreateDateEndString()));
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(DISTINCT CHECK_ORDER_NUM ) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        BigInteger total = (BigInteger) resultTotal.getSingleResult();
        if (firstIdx >= total.intValue()) {
            int intValue = total.intValue() != 0 ? total.intValue() - 1 : total.intValue();
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total.intValue() - 1) / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select b.* " + sqlStr + " group by b.CHECK_ORDER_NUM", StockCheck.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<StockCheck>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }

    @Override
    public Page<StockCheck> queryStockPosting(Pageable pageable, StockPostingSearchParam stockPostingSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_stock_check b where 1=1 ");

        if (stockPostingSearchParam.getShopId() != null) {
            sql.append(" and SHOP_ID =:shopId");
            paramMap.put(StockCheck.SHOP_ID, stockPostingSearchParam.getShopId());
        }

        //盘点单号
        if (StringUtils.isNotBlank(stockPostingSearchParam.getCheckOrderNum())) {
            sql.append(" and CHECK_ORDER_NUM like:checkOrderNum");
            paramMap.put(StockCheck.CHECK_ORDER_NUM,"%" + stockPostingSearchParam.getCheckOrderNum() + "%");
        }

        //商品名称、拼音码、编码
        if (StringUtils.isNotBlank(stockPostingSearchParam.getSearchFields())) {
            sql.append(" and (GOODS_CODE =:code or GOODS_NM like:goodsNm or PINYIN like:pinyin or GOODS_COMMON_NM like :searchFields4)");
            paramMap.put("code", stockPostingSearchParam.getSearchFields());
            paramMap.put("goodsNm", "%" + stockPostingSearchParam.getSearchFields() + "%");
            paramMap.put("pinyin", "%" + stockPostingSearchParam.getSearchFields() + "%");
            paramMap.put("searchFields4", "%" + stockPostingSearchParam.getSearchFields() + "%");
        }

        sql.append(" and CHECKED_STATE_CODE =:checkedStateCode ");
        paramMap.put(StockCheck.CHECKED_STATE_CODE,StockCheckStateCodeEnum.UN_POST.toCode());

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        BigInteger total = (BigInteger) resultTotal.getSingleResult();
        if (firstIdx >= total.intValue()) {
            int intValue = total.intValue() != 0 ? total.intValue() - 1 : total.intValue();
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest(intValue / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select b.*" + sqlStr, StockCheck.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<StockCheck>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
