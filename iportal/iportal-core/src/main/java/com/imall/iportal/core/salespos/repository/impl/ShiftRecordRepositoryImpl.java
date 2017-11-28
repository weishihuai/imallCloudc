
package com.imall.iportal.core.salespos.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.salespos.entity.ShiftRecord;
import com.imall.iportal.core.salespos.repository.ShiftRecordRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.annotation.Resource;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交班 记录(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class ShiftRecordRepositoryImpl implements ShiftRecordRepositoryCustom{

    @Resource
    private EntityManager entityManager;


    public Page<ShiftRecord> query(Pageable pageable, Long shopId, String posManName, Date formCreateDate, Date toCreateDate)  {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" and a.SHOP_ID = :shopId AND IS_HAS_SHIFT=:isHasShift");
        paramMap.put(ShiftRecord.SHOP_ID, shopId);
        paramMap.put(ShiftRecord.IS_HAS_SHIFT, BoolCodeEnum.YES.toCode());

        //交班时间范围
        if (formCreateDate != null ) {
            sql.append(" and SHIFT_TIME >=:startTimeString ");
            paramMap.put("startTimeString", DateTimeUtils.getDayStartTime(formCreateDate));
        }
        //交班时间范围
        if (toCreateDate != null) {
            sql.append(" and SHIFT_TIME <=:endTimeString ");
            paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(toCreateDate));
        }

        if (StringUtils.isNotBlank(posManName)) {
            sql.append(" and POS_MAN in (SELECT ID FROM t_pt_sys_user g WHERE g.REAL_NAME like:posManName)");
            paramMap.put("posManName", "%" + posManName + "%");
        }

        Query query = entityManager.createNativeQuery("SELECT * FROM T_SLPS_SHIFT_RECORD a where 1=1" + sql, ShiftRecord.class);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<ShiftRecord> resultList = query.getResultList();

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM T_SLPS_SHIFT_RECORD a WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<ShiftRecord>(resultList, pageable, total);
    }

}
