
package com.imall.iportal.core.platform.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.core.platform.repository.ShopRepositoryCustom;
import com.imall.iportal.core.platform.vo.ShopSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
public class ShopRepositoryImpl implements ShopRepositoryCustom{

    @Resource
    private EntityManager entityManager;
    @Override
    public Page<Shop> query(Pageable pageable, ShopSearchParam shopSearchParam) throws ParseException {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from T_PTFM_SHOP  where 1=1 ");
        //状态
        if (StringUtils.isNotBlank(shopSearchParam.getIsEnable())) {
            sql.append("and IS_ENABLE=:isEnable ");
            paramMap.put(Shop.IS_ENABLE, BoolCodeEnum.fromCode(shopSearchParam.getIsEnable()).toCode());
        }

        //创建时间范围
        if (StringUtils.isNotBlank(shopSearchParam.getStartTimeString())) {
            sql.append(" and CREATE_DATE >=:startTimeString ");
            paramMap.put("startTimeString", DateTimeUtils.convertStringToDate(shopSearchParam.getStartTimeString()));
        }
        //创建时间范围
        if (StringUtils.isNotBlank(shopSearchParam.getEndTimeString())) {
            sql.append(" and CREATE_DATE <=:endTimeString ");
            paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(shopSearchParam.getEndTimeString()));
        }

        //企业 名称
        if (StringUtils.isNotBlank(shopSearchParam.getEntNm())) {
            sql.append("and ENT_NM like:entNm ");
            paramMap.put(Shop.ENT_NM, "%" + shopSearchParam.getEntNm() + "%");
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);

        int total = ((BigInteger)resultTotal.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, Shop.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<Shop>(resultQ.getResultList(), pageable, total);
    }}