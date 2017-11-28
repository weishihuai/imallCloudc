
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.SalesCategory;
import com.imall.iportal.core.shop.repository.SalesCategoryRepositoryCustom;
import com.imall.iportal.core.shop.vo.SalesCategoryPageVo;
import com.imall.iportal.core.shop.vo.SalesCategorySearchParam;
import org.apache.commons.lang3.StringUtils;
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
 * 销售 分类(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SalesCategoryRepositoryImpl implements SalesCategoryRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SalesCategoryPageVo> query(Pageable pageable, SalesCategorySearchParam salesCategorySearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(StringUtils.isNoneBlank(salesCategorySearchParam.getCategoryName())){
            sql.append(" AND C.CATEGORY_NAME LIKE :categoryName ");
            paramMap.put(SalesCategory.CATEGORY_NAME, "%" + salesCategorySearchParam.getCategoryName() + "%");
        }

        if(salesCategorySearchParam.getShopId()!=null){
            sql.append(" AND C.SHOP_ID = :shopId ");
            paramMap.put(SalesCategory.SHOP_ID, salesCategorySearchParam.getShopId());
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_SALES_CATEGORY C WHERE 1=1 " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query query = entityManager.createNativeQuery("SELECT C.* FROM T_SHP_SALES_CATEGORY C WHERE 1=1 " + sql + " ORDER BY C.IS_ENABLE DESC, C.DISPALY_POSITION ASC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(firstIdx);
        query.setMaxResults(pageSize);

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<SalesCategoryPageVo> salesCategoryPageVoList = new ArrayList<SalesCategoryPageVo>();
        for (Map<String, Object> objectMap : mapList) {
            salesCategoryPageVoList.add(CommonUtil.copyFromDbMap(objectMap, new SalesCategoryPageVo()));
        }
        return new PageImpl<SalesCategoryPageVo>(salesCategoryPageVoList, pageable, total);
    }

    @Override
    public List<SalesCategory> listSalesCategory(Long shopId, String isEnable, String isFrontendShow) {
        Map<String, Object> map = new HashMap<>();
        map.put(SalesCategory.SHOP_ID, shopId);
        StringBuilder sql = new StringBuilder();
        sql.append(" from t_shp_sales_category t where t.SHOP_ID=:shopId");
        if (StringUtils.isNotBlank(isEnable)){
            sql.append(" and t.IS_ENABLE=:isEnable");
            map.put(SalesCategory.IS_ENABLE, isEnable);
        }
        if (StringUtils.isNotBlank(isEnable)){
            sql.append(" and t.IS_FRONTEND_SHOW=:isFrontendShow");
            map.put(SalesCategory.IS_FRONTEND_SHOW, isFrontendShow);
        }
        Query query = entityManager.createNativeQuery("select t.*" + sql.toString(), SalesCategory.class);
        CommonUtil.formatQueryParameter(query, map);
        return query.getResultList();
    }
}
