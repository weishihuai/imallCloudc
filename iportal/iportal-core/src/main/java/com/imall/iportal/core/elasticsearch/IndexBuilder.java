package com.imall.iportal.core.elasticsearch;

import com.imall.commons.base.util.JsonBinder;
import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.provider.*;
import org.apache.log4j.Logger;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.Requests;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class IndexBuilder {

    private static Logger logger = Logger.getLogger(IndexBuilder.class.getName());
    private static HashMap<IndexTypeCodeEnum, IIndexProvider> providerMap;

    static {
        //使用getBeansOfType会卡死，原因不明
        providerMap = new HashMap<>();
        providerMap.put(IndexTypeCodeEnum.SYS_DOC,(SysDocIndexProvider) SpringContextHolder.getBean("sysDocIndexProvider"));
        providerMap.put(IndexTypeCodeEnum.GOODS,(GoodsIndexProvider) SpringContextHolder.getBean("goodsIndexProvider"));
        providerMap.put(IndexTypeCodeEnum.OUT_IN_STOCK_LOG,(OutInStockLogIndexProvider) SpringContextHolder.getBean("outInStockLogIndexProvider"));
        providerMap.put(IndexTypeCodeEnum.ORDER,(OrderIndexProvider) SpringContextHolder.getBean("orderIndexProvider"));
        providerMap.put(IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER,(SellReturnedPurchaseOrderIndexProvider) SpringContextHolder.getBean("sellReturnedPurchaseOrderIndexProvider"));
        providerMap.put(IndexTypeCodeEnum.GOODS_BATCH,(GoodsBatchIndexProvider) SpringContextHolder.getBean("goodsBatchIndexProvider"));
        providerMap.put(IndexTypeCodeEnum.WE_SHOP,(WeShopIndexProvider) SpringContextHolder.getBean("weShopIndexProvider"));
    }

    public static void commit(List<Long> ids, IndexTypeCodeEnum core) {
        if(ids.isEmpty()||core==null){
            return;
        }

        BulkRequest request = Requests.bulkRequest();
        IIndexProvider provider = providerMap.get(core);

        for(Long id : ids){
            Object object = provider.getEntity(id);

            if(object!=null){
                request.add(Requests.indexRequest(core.toCode())
                        .type(core.toCode())
                        .id(String.valueOf(id))
                        .source(JsonBinder.toJson(object)));
            }else{
                request.add(Requests.deleteRequest(core.toCode())
                        .type(core.toCode()).id(String.valueOf(id)));
            }
        }

        ActionFuture<BulkResponse> response = ESUtils.getClient().bulk(request);
        try {
            logger.info("took time: " + response.get().getTookInMillis());
            if (ids.size() > 0) {
                logger.info("成功建立"+core.toCode()+"索引数据，size=" + ids.size());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void commit(Long id, IndexTypeCodeEnum core) {
        if(id==null||core==null){
            return;
        }

        IIndexProvider provider = providerMap.get(core);
        Object object = provider.getEntity(id);

        if (object != null) {
            IndexResponse response = ESUtils.getClient()
                    .prepareIndex(core.toCode(), core.toCode())
                    .setId(String.valueOf(id))
                    .setSource(JsonBinder.toJson(object)).get();

            logger.info("index:" + response.getIndex()
                    + " insert doc id:" + response.getId()
                    + " result:" + response.getResult().getLowercase());

        } else {
            DeleteResponse response = ESUtils.getClient()
                    .prepareDelete(core.toCode(), core.toCode(), String.valueOf(id)).get();

            logger.info("index:" + response.getIndex()
                    + " insert doc id:" + response.getId()
                    + " result:" + response.getResult().getLowercase());
        }
    }

    /**
     * 提交索引，并立即刷新
     * @param id
     * @param core
     */
    public static void commitImmediately(Long id, IndexTypeCodeEnum core) {
        if(id==null||core==null){
            return;
        }

        IIndexProvider provider = providerMap.get(core);
        Object object = provider.getEntity(id);

        if (object != null) {
            IndexResponse response = ESUtils.getClient()
                    .prepareIndex(core.toCode(), core.toCode())
                    .setId(String.valueOf(id))
                    .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)//Needed when the query shall be available immediately
                    .setSource(JsonBinder.toJson(object)).get();
        } else {
            ESUtils.getClient().prepareDelete(core.toCode(), core.toCode(), String.valueOf(id)).get();
        }
    }

    public static void commitImmediately(List<Long> ids, IndexTypeCodeEnum core) {
        if(ids.isEmpty()||core==null){
            return;
        }

        BulkRequest request = Requests.bulkRequest().setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
        IIndexProvider provider = providerMap.get(core);

        for(Long id : ids){
            Object object = provider.getEntity(id);

            if(object!=null){
                request.add(Requests.indexRequest(core.toCode())
                        .type(core.toCode())
                        .id(String.valueOf(id))
                        .source(JsonBinder.toJson(object)));
            }else{
                request.add(Requests.deleteRequest(core.toCode())
                        .type(core.toCode()).id(String.valueOf(id)));
            }
        }

        ActionFuture<BulkResponse> response = ESUtils.getClient().bulk(request);
        try {
            logger.info("took time: " + response.get().getTookInMillis());
            if (ids.size() > 0) {
                logger.info("成功建立"+core.toCode()+"索引数据，size=" + ids.size());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
