
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.Member;
import com.imall.iportal.core.shop.repository.MemberRepositoryCustom;
import com.imall.iportal.core.shop.vo.MemberSearchParam;
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
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<Member> query(Pageable pageable, MemberSearchParam memberSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_member a where 1=1 ");

        //门店ID
        if (memberSearchParam.getShopId() != null) {
            sql.append(" and SHOP_ID =:shopId");
            paramMap.put(Member.SHOP_ID, memberSearchParam.getShopId());
        }

        //手机号码 、会员卡号
        if (StringUtils.isNotBlank(memberSearchParam.getSearchFields())) {
            sql.append(" and (MOBILE like:searchFields1 or MEMBER_CARD_NUM like:searchFields2) ");
            paramMap.put("searchFields1","%" +  memberSearchParam.getSearchFields() + "%");
            paramMap.put("searchFields2","%" +  memberSearchParam.getSearchFields() + "%");
        }
        //会员姓名
        if (StringUtils.isNotBlank(memberSearchParam.getName())) {
            sql.append(" and a.NAME like:name");
            paramMap.put(Member.NAME, "%" + memberSearchParam.getName() + "%");
        }
        //创建时间范围
        if (StringUtils.isNotBlank(memberSearchParam.getCreateDateBeginString())) {
            sql.append(" and a.CREATE_DATE >= :fromDate ");
            paramMap.put("fromDate", DateTimeUtils.getDayStartTime(memberSearchParam.getCreateDateBeginString()));
        }

        if (StringUtils.isNotBlank(memberSearchParam.getCreateDateEndString())) {
            sql.append(" and a.CREATE_DATE <= :toDate ");
            paramMap.put("toDate", DateTimeUtils.getDayEndTime(memberSearchParam.getCreateDateEndString()));
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

        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr, Member.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<Member>(resultQ.getResultList(), pageable, total);
    }

}
