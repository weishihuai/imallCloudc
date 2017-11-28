package com.imall.iportal.core.index.service.impl;

import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.commons.dicts.QueueExecuteStateCodeEnum;
import com.imall.iportal.core.elasticsearch.ESUtils;
import com.imall.iportal.core.elasticsearch.IndexBuilder;
import com.imall.iportal.core.elasticsearch.IndexTypeMappingUtils;
import com.imall.iportal.core.index.entity.EsIndexQueue;
import com.imall.iportal.core.index.repository.EsIndexQueueRepository;
import com.imall.iportal.core.index.service.EsIndexQueueService;
import com.imall.iportal.core.main.commons.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/*import com.imall.iportal.core.quartz.service.QrtzRunningTriggersService;*/


@Service
public class EsIndexQueueServiceImpl extends AbstractBaseService<EsIndexQueue, Long> implements EsIndexQueueService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

   /* @Autowired
    private QrtzRunningTriggersService qrtzRunningTriggersService;*/

    @SuppressWarnings("unused")
    private EsIndexQueueRepository getSysIndexQueueRepository() {
        return (EsIndexQueueRepository) baseRepository;
    }

    @Override
    @Transactional
    public List<Long> findByObjectTypeAndTop(List<Long> indexQueueIdList, IndexTypeCodeEnum typeCodeEnum, String requesterIP , int topNum){
        // 获取队列
        List<EsIndexQueue> indexQueueList = getSysIndexQueueRepository().findByObjectTypeAndTop(typeCodeEnum,topNum);

        // 获取队列ID，以及对象ID
        List<Long> indexObjectIdList = new ArrayList<>();
        for(EsIndexQueue indexQueue : indexQueueList){
            indexQueueIdList.add(indexQueue.getId());
            indexObjectIdList.add(indexQueue.getObjectId());
        }

        // 批量更新索引状态
        getSysIndexQueueRepository().batchUpdateIndexQueue(requesterIP, QueueExecuteStateCodeEnum.PROCESSING,indexQueueIdList);
        return indexObjectIdList;
    }

    @Override
    @Transactional
    public void deleteQueueByBatch(String performerIp, QueueExecuteStateCodeEnum stateCodeEnum, IndexTypeCodeEnum typeCodeEnum){
        getSysIndexQueueRepository().deleteQueueByBatch(performerIp, stateCodeEnum, typeCodeEnum);
    }

    @Override
    @Transactional
    public Integer rebuildIndex(IndexTypeCodeEnum indexTypeCode) {
        //删除索引服务器数据
        ESUtils.deleteIndex(indexTypeCode.toCode());

        // 删除并重建映射
        if(ESUtils.isExistsIndex(indexTypeCode.toCode())){
            ESUtils.deleteIndex(indexTypeCode.toCode());
        }
        IndexTypeMappingUtils.createIndexTypeMapping(indexTypeCode);


        Integer countNum = 0;
        switch (indexTypeCode){
            case SYS_DOC:
                //清空索引队列
                deleteQueueByBatch(null,null, IndexTypeCodeEnum.SYS_DOC);
                //查询文档数据，并插入队列
                countNum = ServiceManager.sysDocService.queryDocToQueue();
                break;
            case GOODS:
                //清空索引队列
                deleteQueueByBatch(null,null, IndexTypeCodeEnum.GOODS);
                //查询商品数据，并插入队列
                countNum = ServiceManager.goodsService.queryGoodsToQueue();
                break;
            case OUT_IN_STOCK_LOG:
                //清空索引队列
                deleteQueueByBatch(null,null, IndexTypeCodeEnum.OUT_IN_STOCK_LOG);
                //查询文档数据，并插入队列
                countNum = ServiceManager.outInStockLogService.queryOutInStockLogToQueue();
                break;
            case ORDER:
                //清空索引队列
                deleteQueueByBatch(null,null, IndexTypeCodeEnum.ORDER);
                //查询订单数据，并插入队列
                countNum = ServiceManager.orderService.queryOrderToQueue();
                break;
            case SELL_RETURNED_PURCHASE_ORDER:
                //清空索引队列
                deleteQueueByBatch(null,null, IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER);
                //查询销售退货订单数据，并插入队列
                countNum = ServiceManager.sellReturnedPurchaseOrderService.queryOrderToQueue();
                break;
            case GOODS_BATCH:
                //清空索引队列
                deleteQueueByBatch(null,null, IndexTypeCodeEnum.GOODS_BATCH);
                //查询销售退货订单数据，并插入队列
                countNum = ServiceManager.goodsBatchService.queryGoodsBatchToQueue();
                break;
            case WE_SHOP:
                //清空索引队列
                deleteQueueByBatch(null,null, IndexTypeCodeEnum.WE_SHOP);
                //查询微门店数据，并插入队列
                countNum = ServiceManager.weShopService.queryWeShopToQueue();
                break;
            default:break;
        }
        return countNum;
    }

    @Override
    @Transactional
    public Boolean executeQueue(IndexTypeCodeEnum indexTypeCode) {
        //获得本机IP
        String requesterIP = null;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            requesterIP = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("获得本机IP错误 = "+e.getMessage());
        }
        List<Long> indexQueueIdList = new ArrayList<>();
        //向索引应用拉取2000条状态为未处理的队列
        List<Long> indexQueueList = findByObjectTypeAndTop(indexQueueIdList, indexTypeCode,requesterIP,2000);
        if(indexQueueList.isEmpty()){
            return false;
        }
        IndexBuilder.commit(indexQueueList, indexTypeCode);

        //删除已建索引的队列
        delete(indexQueueIdList);
//        deleteQueueByBatch(requesterIP, QueueExecuteStateCodeEnum.PROCESSING,indexTypeCode);

        //通知索引管理
        Integer count = getSysIndexQueueRepository().findByTypeAndExecuteState(indexTypeCode.toCode(), QueueExecuteStateCodeEnum.UN_PROCESSED.toCode());
        ServiceManager.esIndexManageService.updateManageRemainAndState(indexTypeCode.toCode(),count);
        return true;
    }

    @Override
    @Transactional
    public Long saveQueue(String indexTypeCodeEnum, Long objectId, String performerIp) {
        EsIndexQueue esIndexQueue = new EsIndexQueue();
        esIndexQueue.setExecuteState(QueueExecuteStateCodeEnum.UN_PROCESSED.toCode());
        esIndexQueue.setObjectId(objectId);
        esIndexQueue.setPerformerIp(performerIp);
        esIndexQueue.setIndexTypeCode(indexTypeCodeEnum);
        return this.save(esIndexQueue).getId();
    }

    /**
     * 实现调度的默认接口，调度HTTP调用（契约）
     * @param jobName 工作名称
     * @param jobGroup 工作组
     * @param qrtzLogId  调度日志ID
     */
  /*  @Transactional
    @Override
    public Boolean quartzJobExecute(String jobName, String jobGroup, String executeAddress, Integer qrtzLogId) {
        executeQueue(IndexTypeCodeEnum.SYS_DOC);
        qrtzRunningTriggersService.deleteByJobNameAndJobGroupAndExcuteAddress(jobName, jobGroup, executeAddress);
        return true;
    }*/
}
