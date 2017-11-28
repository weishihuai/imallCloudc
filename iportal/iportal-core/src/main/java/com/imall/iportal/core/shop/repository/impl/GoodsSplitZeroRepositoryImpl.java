
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.GoodsSplitZero;
import com.imall.iportal.core.shop.repository.GoodsSplitZeroRepositoryCustom;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroPageVo;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroSearchParam;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class GoodsSplitZeroRepositoryImpl implements GoodsSplitZeroRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<GoodsSplitZeroPageVo> query(Pageable pageable, GoodsSplitZeroSearchParam goodsSplitZeroSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" AND Z.SHOP_ID = :shopId ");
        paramMap.put(GoodsSplitZero.SHOP_ID, goodsSplitZeroSearchParam.getShopId());

        if(StringUtils.isNotBlank(goodsSplitZeroSearchParam.getKeyword())){
            sql.append(" AND (Z.PINYIN LIKE :pinyin OR Z.GOODS_NM LIKE :goodsNm OR Z.GOODS_CODE LIKE :goodsCode OR Z.GOODS_COMMON_NM LIKE :goodsCommonNm) ");
            paramMap.put(GoodsSplitZero.PINYIN, "%" + goodsSplitZeroSearchParam.getKeyword() + "%");
            paramMap.put(GoodsSplitZero.GOODS_NM, "%" + goodsSplitZeroSearchParam.getKeyword() + "%");
            paramMap.put(GoodsSplitZero.GOODS_COMMON_NM, "%" + goodsSplitZeroSearchParam.getKeyword() + "%");
            paramMap.put(GoodsSplitZero.GOODS_CODE, "%" + goodsSplitZeroSearchParam.getKeyword() + "%");
        }

        if(StringUtils.isNotBlank(goodsSplitZeroSearchParam.getStartTimeString())){
            sql.append(" AND Z.CREATE_DATE >= :startTime ");
            paramMap.put("startTime", goodsSplitZeroSearchParam.getStartTime());
        }

        if(StringUtils.isNotBlank(goodsSplitZeroSearchParam.getEndTimeString())){
            sql.append(" AND Z.CREATE_DATE <= :endTime ");
            paramMap.put("endTime", goodsSplitZeroSearchParam.getEndTime());
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_GOODS_SPLIT_ZERO Z WHERE 1=1 " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query query = entityManager.createNativeQuery("SELECT Z.* FROM T_SHP_GOODS_SPLIT_ZERO Z WHERE 1=1 " + sql + " ORDER BY Z.CREATE_DATE DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(firstIdx);
        query.setMaxResults(pageSize);

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();
        List<GoodsSplitZeroPageVo> goodsSplitZeroPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            goodsSplitZeroPageVoList.add(CommonUtil.copyFromDbMap(objectMap, new GoodsSplitZeroPageVo()));
        }
        return new PageImpl<GoodsSplitZeroPageVo>(goodsSplitZeroPageVoList, pageable, total);
    }
}
