
package com.imall.iportal.core.weshop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.weshop.entity.Fans;
import com.imall.iportal.core.weshop.repository.FansRepositoryCustom;
import com.imall.iportal.core.weshop.vo.FansSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
public class FansRepositoryImpl implements FansRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<Fans> query(Pageable pageable, FansSearchParam fansSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_we_fans f where 1=1 ");

        if (fansSearchParam.getShopId() != null) {
            sql.append(" and f.SHOP_ID =:shopId ");
            paramMap.put(Fans.SHOP_ID, fansSearchParam.getShopId());
        }

        //粉丝姓名
        if (StringUtils.isNotBlank(fansSearchParam.getFansName())) {
            sql.append(" and f.FANS_NAME like:fansName");
            paramMap.put(Fans.FANS_NAME, "%" + fansSearchParam.getFansName() + "%");
        }

        //手机号码
        if (StringUtils.isNotBlank(fansSearchParam.getMobile())) {
            sql.append(" and f.MOBILE like:mobile ");
            paramMap.put(Fans.MOBILE, "%" + fansSearchParam.getMobile() + "%");
        }

        //微信昵称
        if (StringUtils.isNotBlank(fansSearchParam.getNickName())) {
            sql.append(" and f.NICK_NAME like:nickName");
            paramMap.put(Fans.NICK_NAME, "%" + fansSearchParam.getNickName() + "%");
        }

        //粉丝来源
        if (StringUtils.isNotBlank(fansSearchParam.getFansSourceCode())) {
            sql.append(" and f.FANS_SOURCE_CODE =:fansSourceCode ");
            paramMap.put(Fans.FANS_SOURCE_CODE, fansSearchParam.getFansSourceCode());
        }

        //购买次数
        if (fansSearchParam.getBuyTimes() != null) {
            if (fansSearchParam.getBuyTimes() == 0) {
                sql.append(" and f.BUY_TIMES = :buyTimes ");
                paramMap.put(Fans.BUY_TIMES, fansSearchParam.getBuyTimes());
            } else {
                sql.append(" and f.BUY_TIMES >= :buyTimes ");
                paramMap.put(Fans.BUY_TIMES, fansSearchParam.getBuyTimes());
            }
        }

        //粉丝身份
        if (StringUtils.isNotBlank(fansSearchParam.getIsMember())) {
            sql.append(" and f.IS_MEMBER = :isMember ");
            paramMap.put(Fans.IS_MEMBER, fansSearchParam.getIsMember());
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select f.*" + sqlStr, Fans.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<Fans>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
