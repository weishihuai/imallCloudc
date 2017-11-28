
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.entity.StaffHealthDoc;
import com.imall.iportal.core.shop.repository.StaffHealthDocRepositoryCustom;
import com.imall.iportal.core.shop.vo.StaffHealthDocSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class StaffHealthDocRepositoryImpl implements StaffHealthDocRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<StaffHealthDoc> query(Pageable pageable, StaffHealthDocSearchParam staffHealthDocSearchParam) throws ParseException {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_staff_health_doc t  where 1=1 ");

        if(staffHealthDocSearchParam.getShopId()!=null){
            sql.append(" and t.SHOP_ID =:shopId ");
            paramMap.put(StaffHealthDoc.SHOP_ID, staffHealthDocSearchParam.getShopId());
        }
        if(StringUtils.isNotBlank(staffHealthDocSearchParam.getUserName())){
            sql.append(" AND t.STAFF_ID IN (select s.id from t_pt_sys_user s where s.USER_NAME like:userName) ");
            paramMap.put(SysUser.USER_NAME, "%"+staffHealthDocSearchParam.getUserName()+"%");
        }
        if(StringUtils.isNotBlank(staffHealthDocSearchParam.getState())){
            sql.append(" AND t.STAFF_ID IN (select s.id from t_pt_sys_user s where s.IS_ENABLE =:isEnable) ");
            paramMap.put(SysUser.IS_ENABLE, staffHealthDocSearchParam.getState());
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, StaffHealthDoc.class)
                .setFirstResult(firstIdx)              .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<StaffHealthDoc>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
