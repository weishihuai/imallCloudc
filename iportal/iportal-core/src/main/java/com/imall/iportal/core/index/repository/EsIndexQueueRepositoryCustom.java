package com.imall.iportal.core.index.repository;


import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.commons.dicts.QueueExecuteStateCodeEnum;
import com.imall.iportal.core.index.entity.EsIndexQueue;

import java.util.List;

/**
 * Created by ZJC on 2016/8/30.
 */
public interface EsIndexQueueRepositoryCustom {

    List<EsIndexQueue> findByObjectTypeAndTop(IndexTypeCodeEnum typeCodeEnum, int topNum);

    void batchUpdateIndexQueue(String performerIpt, QueueExecuteStateCodeEnum stateCodeEnum, List<Long> indexQueueIdLis);

    void deleteQueueByBatch(String performerIp, QueueExecuteStateCodeEnum stateCodeEnum, IndexTypeCodeEnum typeCodeEnum);

    Integer findByTypeAndExecuteState(String indexTypeCode, String executeState);
}
