
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.OutStockTypeCodeEnum;
import com.imall.iportal.core.shop.entity.OtherOutStock;
import com.imall.iportal.core.shop.repository.OtherOutStockRepositoryCustom;
import com.imall.iportal.core.shop.vo.OtherOutStockSearchParam;
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
public class OtherOutStockRepositoryImpl implements OtherOutStockRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<OtherOutStock> query(Pageable pageable, OtherOutStockSearchParam otherOutStockSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_other_out_stock b where 1=1 ");
        //门店ID
        if (otherOutStockSearchParam.getShopId() != null) {
            sql.append(" and SHOP_ID =:shopId");
            paramMap.put(OtherOutStock.SHOP_ID, otherOutStockSearchParam.getShopId());
        }

        //出库单号
        if (StringUtils.isNotBlank(otherOutStockSearchParam.getOutStockCode())) {
            sql.append(" and OUT_STOCK_CODE like:outStockCode");
            paramMap.put(OtherOutStock.OUT_STOCK_CODE,"%" + otherOutStockSearchParam.getOutStockCode() + "%");
        }

        //出库类型
        if (StringUtils.isNotBlank(otherOutStockSearchParam.getTypeCode())) {
            sql.append(" and TYPE_CODE =:typeCode");
            paramMap.put(OtherOutStock.TYPE_CODE, OutStockTypeCodeEnum.fromCode(otherOutStockSearchParam.getTypeCode()).toCode());
        }

        //出库人
        if (StringUtils.isNotBlank(otherOutStockSearchParam.getSearchFields())) {
            sql.append(" and OPERATION_MAN_ID in (SELECT ID FROM t_pt_sys_user g WHERE g.REAL_NAME like:searchFields)");
            paramMap.put("searchFields", "%" + otherOutStockSearchParam.getSearchFields() + "%");
        }

        //出库时间
        if (StringUtils.isNotBlank(otherOutStockSearchParam.getOutStockTimeStartString())) {
            sql.append(" and OUT_STOCK_TIME >= :fromDate");
            paramMap.put("fromDate", DateTimeUtils.getDayStartTime(otherOutStockSearchParam.getOutStockTimeStartString()));
        }

        if (StringUtils.isNotBlank(otherOutStockSearchParam.getOutStockTimeEndString())) {
            sql.append(" and OUT_STOCK_TIME <= :toDate ");
            paramMap.put("toDate", DateTimeUtils.getDayEndTime(otherOutStockSearchParam.getOutStockTimeEndString()));
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(DISTINCT OUT_STOCK_CODE ) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        BigInteger total = (BigInteger) resultTotal.getSingleResult();
        if (firstIdx >= total.intValue()) {
            int intValue = total.intValue() != 0 ? total.intValue() - 1 : total.intValue();
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total.intValue() - 1) / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select b.* " + sqlStr + " group by OUT_STOCK_CODE", OtherOutStock.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);

        return new PageImpl<OtherOutStock>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
