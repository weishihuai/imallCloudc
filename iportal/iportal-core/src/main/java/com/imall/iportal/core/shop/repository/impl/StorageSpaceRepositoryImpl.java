
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.StorageSpace;
import com.imall.iportal.core.shop.repository.StorageSpaceRepositoryCustom;
import com.imall.iportal.core.shop.vo.StorageSpaceSearchParam;
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
public class StorageSpaceRepositoryImpl implements StorageSpaceRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<StorageSpace> query(Pageable pageable, StorageSpaceSearchParam storageSpaceSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_storage_space a where 1=1 ");

        if (storageSpaceSearchParam.getShopId() != null) {
            sql.append(" and a.SHOP_ID =:shopId");
            paramMap.put(StorageSpace.SHOP_ID, storageSpaceSearchParam.getShopId());
        }

        //货位名称
        if (StringUtils.isNotBlank(storageSpaceSearchParam.getStorageSpaceNm())) {
            sql.append(" and a.STORAGE_SPACE_NM like:storageSpaceNm");
            paramMap.put(StorageSpace.STORAGE_SPACE_NM, "%" + storageSpaceSearchParam.getStorageSpaceNm() + "%");
        }
        //货位类型
        if (StringUtils.isNotBlank(storageSpaceSearchParam.getStorageSpaceType())) {
            sql.append(" and a.STORAGE_SPACE_TYPE =:storageSpaceType");
            paramMap.put(StorageSpace.STORAGE_SPACE_TYPE, storageSpaceSearchParam.getStorageSpaceType());
        }
        //货位是否启用状态
        if (StringUtils.isNotBlank(storageSpaceSearchParam.getEnableStateCode())) {
            sql.append(" and a.IS_ENABLE =:isEnable");
            paramMap.put(StorageSpace.IS_ENABLE, storageSpaceSearchParam.getEnableStateCode());
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
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr + " ORDER BY A.IS_ENABLE DESC, A.ID DESC", StorageSpace.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<StorageSpace>(resultQ.getResultList(), pageable, total);
    }
}
