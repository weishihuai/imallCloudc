
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.StorageSpaceMove;
import com.imall.iportal.core.shop.repository.StorageSpaceMoveRepositoryCustom;
import com.imall.iportal.core.shop.vo.StorageSpaceMoveSearchParam;
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
 * @author by imall core generator
 * @version 1.0.0
 */
public class StorageSpaceMoveRepositoryImpl implements StorageSpaceMoveRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<StorageSpaceMove> query(Pageable pageable, StorageSpaceMoveSearchParam storageSpaceMoveSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_storage_space_move b where 1=1 ");

        if (storageSpaceMoveSearchParam.getShopId() != null) {
            sql.append(" and SHOP_ID =:shopId");
            paramMap.put(StorageSpaceMove.SHOP_ID, storageSpaceMoveSearchParam.getShopId());
        }
        //移动单号
        if (StringUtils.isNotBlank(storageSpaceMoveSearchParam.getMoveOrderNum())) {
            sql.append(" and MOVE_ORDER_NUM like:moveOrderNum");
            paramMap.put(StorageSpaceMove.MOVE_ORDER_NUM,"%" + storageSpaceMoveSearchParam.getMoveOrderNum() + "%");
        }
        //移动人
        if (StringUtils.isNotBlank(storageSpaceMoveSearchParam.getMoveManName())) {
            sql.append(" and MOVE_MAN_NAME like:moveManName");
            paramMap.put(StorageSpaceMove.MOVE_MAN_NAME, "%" +  storageSpaceMoveSearchParam.getMoveManName() + "%");
        }
        //移动时间
        if (StringUtils.isNotBlank(storageSpaceMoveSearchParam.getMoveStartTimeString())) {
            sql.append(" and MOVE_TIME >= :fromDate");
            paramMap.put("fromDate", DateTimeUtils.getDayStartTime(storageSpaceMoveSearchParam.getMoveStartTimeString()));
        }
        if (StringUtils.isNotBlank(storageSpaceMoveSearchParam.getMoveEndTimeString())){
            sql.append(" and MOVE_TIME <= :toDate ");
            paramMap.put("toDate", DateTimeUtils.getDayEndTime(storageSpaceMoveSearchParam.getMoveEndTimeString()));
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(DISTINCT MOVE_ORDER_NUM ) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        BigInteger total = (BigInteger) resultTotal.getSingleResult();
        if (firstIdx >= total.intValue()) {
            int intValue = total.intValue() != 0 ? total.intValue() - 1 : total.intValue();
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total.intValue() - 1) / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select b.*" + sqlStr + " group by MOVE_ORDER_NUM ", StorageSpaceMove.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<StorageSpaceMove>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
