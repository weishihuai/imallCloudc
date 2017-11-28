
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.InStockTypeCodeEnum;
import com.imall.iportal.core.shop.entity.OtherInStock;
import com.imall.iportal.core.shop.repository.OtherInStockRepositoryCustom;
import com.imall.iportal.core.shop.vo.OtherInStockSearchParam;
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
public class OtherInStockRepositoryImpl implements OtherInStockRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<OtherInStock> query(Pageable pageable, OtherInStockSearchParam otherInStockSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_other_in_stock b where 1=1 ");
        //门店ID
        if (otherInStockSearchParam.getShopId() != null) {
            sql.append(" and SHOP_ID =:shopId");
            paramMap.put(OtherInStock.SHOP_ID, otherInStockSearchParam.getShopId());
        }

        //入库单号
        if (StringUtils.isNotBlank(otherInStockSearchParam.getInStockCode())) {
            sql.append(" and IN_STOCK_CODE like:inStockCode");
            paramMap.put(OtherInStock.IN_STOCK_CODE,"%" + otherInStockSearchParam.getInStockCode() + "%");
        }

        //出库类型
        if (StringUtils.isNotBlank(otherInStockSearchParam.getTypeCode())) {
            sql.append(" and TYPE_CODE =:typeCode");
            paramMap.put(OtherInStock.TYPE_CODE, InStockTypeCodeEnum.fromCode(otherInStockSearchParam.getTypeCode()).toCode());
        }

        //入库人
        if (StringUtils.isNotBlank(otherInStockSearchParam.getSearchFields())) {
            sql.append(" and OPERATION_MAN_ID in (SELECT ID FROM t_pt_sys_user g WHERE g.REAL_NAME like:searchFields)");
            paramMap.put("searchFields", "%" + otherInStockSearchParam.getSearchFields() + "%");
        }

        //入库时间
        if (StringUtils.isNotBlank(otherInStockSearchParam.getInStockTimeStartString())) {
            sql.append(" and IN_STOCK_TIME >= :fromDate");
            paramMap.put("fromDate", DateTimeUtils.getDayStartTime(otherInStockSearchParam.getInStockTimeStartString()));
        }

        if (StringUtils.isNotBlank(otherInStockSearchParam.getInStockTimeEndString())) {
            sql.append(" and IN_STOCK_TIME <= :toDate ");
            paramMap.put("toDate", DateTimeUtils.getDayEndTime(otherInStockSearchParam.getInStockTimeEndString()));
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(DISTINCT IN_STOCK_CODE ) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        int total = ((BigInteger)resultTotal.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select b.* " + sqlStr + " group by IN_STOCK_CODE", OtherInStock.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<OtherInStock>(resultQ.getResultList(), pageable,total);
    }
}
