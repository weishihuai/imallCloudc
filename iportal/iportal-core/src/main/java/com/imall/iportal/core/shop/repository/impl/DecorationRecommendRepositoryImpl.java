
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.DecorationRecommend;
import com.imall.iportal.core.shop.repository.DecorationRecommendRepositoryCustom;
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
 * @author by imall core generator
 * @version 1.0.0
 */
public class DecorationRecommendRepositoryImpl implements DecorationRecommendRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DecorationRecommend> query(Pageable pageable, Long shopId, Long groupId) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        map.put(DecorationRecommend.SHOP_ID, shopId);
        map.put(DecorationRecommend.DECORATION_GROUP_ID, groupId);
        sql.append(" from t_shp_decoration_recommend r, t_shp_goods g where r.SHOP_ID = :shopId and r.DECORATION_GROUP_ID = :decorationGroupId and g.ID = r.GOODS_ID")
                .append(" and g.IS_DELETE = 'N' and g.IS_ENABLE = 'Y' and g.APPROVE_STATE_CODE = 'PASS_APPROVE'");
        sql.append(" order by r.DISPLAY_POSITION desc");
        String sqlStr = sql.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sqlStr);
        CommonUtil.formatQueryParameter(totalQ, map);
        Long total = ((BigInteger)totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select r.*" + sqlStr, DecorationRecommend.class)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, map);
        return new PageImpl<DecorationRecommend>(resultQ.getResultList(), pageable, total);
    }
}
