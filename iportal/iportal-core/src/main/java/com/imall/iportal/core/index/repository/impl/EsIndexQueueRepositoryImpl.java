package com.imall.iportal.core.index.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.commons.dicts.QueueExecuteStateCodeEnum;
import com.imall.iportal.core.index.entity.EsIndexQueue;
import com.imall.iportal.core.index.repository.EsIndexQueueRepositoryCustom;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EsIndexQueueRepositoryImpl implements EsIndexQueueRepositoryCustom {

    @Resource
    EntityManager entityManager;

    @Override
    public List<EsIndexQueue> findByObjectTypeAndTop(IndexTypeCodeEnum typeCodeEnum, int topNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String sql = "SELECT * from t_pt_es_index_queue q where 1=1";

        if (typeCodeEnum != null) {
            sql +=" and q.INDEX_TYPE_CODE=:indexTypeCode";
            paramMap.put(EsIndexQueue.INDEX_TYPE_CODE, typeCodeEnum.toCode());
        }

        sql+= " ORDER BY q.id DESC LIMIT "+ topNum;

        Query query = entityManager.createNativeQuery(sql, EsIndexQueue.class);
        CommonUtil.formatQueryParameter(query, paramMap);
        return query.getResultList();
    }

    @Override
    public void batchUpdateIndexQueue(String performerIp, QueueExecuteStateCodeEnum stateCodeEnum, List<Long> indexQueueIdList){
        if(indexQueueIdList.isEmpty()){
            return;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(EsIndexQueue.PERFORMER_IP,performerIp);
        paramMap.put(EsIndexQueue.EXECUTE_STATE,stateCodeEnum.toCode());
        paramMap.put("indexQueueIdList", indexQueueIdList);

        String sql = "update t_pt_es_index_queue set performer_ip =:performerIp ,execute_state =:executeState where id in (:indexQueueIdList)";
        Query query = entityManager.createNativeQuery(sql);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.executeUpdate();
    }

    @Override
    public void deleteQueueByBatch(String performerIp, QueueExecuteStateCodeEnum stateCodeEnum, IndexTypeCodeEnum typeCodeEnum){
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String sql = "delete from t_pt_es_index_queue where 1=1 ";
        if (typeCodeEnum != null) {
            sql += " AND index_type_code =:indexTypeCode ";
            paramMap.put(EsIndexQueue.INDEX_TYPE_CODE, typeCodeEnum.toCode());
        }
        if (stateCodeEnum != null) {
            sql += " AND execute_state =:executeState ";
            paramMap.put(EsIndexQueue.EXECUTE_STATE, stateCodeEnum.toCode());
        }
        if (StringUtils.isNotBlank(performerIp)) {
            sql += " AND performer_ip =:performerIp ";
            paramMap.put(EsIndexQueue.PERFORMER_IP,performerIp);
        }
        Query query = entityManager.createNativeQuery(sql);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.executeUpdate();
    }

    @Override
    public Integer findByTypeAndExecuteState(String indexTypeCode, String executeState) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String sql = "SELECT * from t_pt_es_index_queue q where 1=1";

        if ( StringUtils.isNotBlank(indexTypeCode)) {
            sql += " and q.INDEX_TYPE_CODE=:indexTypeCode";
            paramMap.put(EsIndexQueue.INDEX_TYPE_CODE, indexTypeCode);
        }

        if ( StringUtils.isNotBlank(executeState)) {
            sql += " and q.EXECUTE_STATE=:executeState";
            paramMap.put(EsIndexQueue.EXECUTE_STATE, executeState);
        }

        Query query = entityManager.createNativeQuery(sql, EsIndexQueue.class);
        CommonUtil.formatQueryParameter(query, paramMap);
        return query.getResultList().size();
    }
}
