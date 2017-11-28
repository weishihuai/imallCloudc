package com.imall.iportal.core.index.service;

import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.commons.dicts.QueueExecuteStateCodeEnum;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface EsIndexQueueService{

    /**
     * 获取索引队列的ObjectID
     * @param indexTypeCode 索引类型
     * @param requesterIP 执行者IP
     * @param topNum 获取条数
     */
    List<Long> findByObjectTypeAndTop(@NotNull List<Long> indexQueueIdList, @NotNull IndexTypeCodeEnum indexTypeCode, @NotBlank String requesterIP, int topNum);

    /**
     * 批量删除索引队列
     * @param performerIp 执行者IP
     * @param stateCodeEnum 处理状态
     * @param indexTypeCode 索引类型
     */
    void deleteQueueByBatch(@NotBlank String performerIp, @NotNull  QueueExecuteStateCodeEnum stateCodeEnum, @NotNull  IndexTypeCodeEnum indexTypeCode);

    /**
     * 重建索引
     * @param indexTypeCode 索引类型
     */
    Integer rebuildIndex(@NotNull IndexTypeCodeEnum indexTypeCode);

    /**
     * 建立索引
     * @param indexTypeCode 索引类型
     * @return true 表示已经成功执行索引，false表示没有可执行的索引
     */
    Boolean executeQueue(@NotNull IndexTypeCodeEnum indexTypeCode);

    /**
     * 新增索引队列
     * @param indexTypeCodeEnum 索引类型代码，必填
     * @param objectId 对象ID，必填
     * @param performerIp 执行者IP，可选
     * @return
     */
    Long saveQueue(String indexTypeCodeEnum, Long objectId, String performerIp);
    /**
     * 实现调度的默认接口，调度HTTP调用（契约）
     * @param jobName 工作名称
     * @param jobGroup 工作组
     * @param qrtzLogId  调度日志ID
     */
    /*Boolean quartzJobExecute(String jobName, String jobGroup, String executeAddress, Integer qrtzLogId);*/
}
