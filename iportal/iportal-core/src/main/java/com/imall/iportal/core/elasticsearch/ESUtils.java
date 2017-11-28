package com.imall.iportal.core.elasticsearch;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.cache.clear.ClearIndicesCacheRequest;
import org.elasticsearch.action.admin.indices.cache.clear.ClearIndicesCacheResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeRequest;
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * elasticsearch工具类
 */
public class ESUtils {
    private static Logger logger = LoggerFactory.getLogger(ESUtils.class);
    private static String esHosts[];
    private static String clusterName;
    private static int esHostsPort;
    private static TransportClient client = null;

    public ESUtils(String clusterName,String esHosts, int esHostsPort) {
        ESUtils.clusterName = clusterName;
        ESUtils.esHostsPort = esHostsPort;
        ESUtils.esHosts = esHosts.split(",");
        client = createRemoteClient();
    }

    /**
     * 获取Elasticsearch客户端
     */
    public static TransportClient getClient() {
        return client;
    }

    /**
     * 判断索引是否存在
     */
    public static boolean isExistsIndex(String... indices) {
        ActionFuture<IndicesExistsResponse> response = getClient().admin().indices().exists(Requests.indicesExistsRequest(indices));
        logger.info("isExists: " + response.actionGet().isExists());
        return response.actionGet().isExists();
    }

    /**
     * 判断指定的索引的类型是否存在
     */
    public static boolean isExistsType(String indexName, String indexType) {
        TypesExistsResponse response =
                getClient().admin().indices()
                        .typesExists(new TypesExistsRequest(new String[]{indexName}, indexType)
                        ).actionGet();
        return response.isExists();
    }

    /**
     * 删除索引
     *
     * @param indices 多个索引
     */
    public static void deleteIndex(String... indices) {
        for (String index : indices) {
            if (isExistsIndex(index)) {
                ActionFuture<DeleteIndexResponse> response = getClient().admin().indices().delete(Requests.deleteIndexRequest(index));
                logger.info("isAcknowledged: " + response.actionGet().isAcknowledged());
            }
        }
    }

    /**
     * 删除文档
     */
    public static void deleteDocument(String indexName, String indexType, String id) throws ExecutionException, InterruptedException {
        logger.info("delete_index......");
        ActionFuture<DeleteResponse> response = getClient().delete(Requests.deleteRequest(indexName).type(indexType).id(id));
        logger.info("getStatus: " + response.get().status().getStatus());
    }


    /**
     * 清除索引缓存
     *
     * @param indexName
     */
    public static boolean clearIndicesCache(String indexName) {
        ClearIndicesCacheResponse response = null;
        try {
            response = getClient().admin().indices()
                    .clearCache(new ClearIndicesCacheRequest(indexName)
                            .fieldDataCache(true)
                            .queryCache(true)
                            .requestCache(true)
                    ).actionGet();
            return response.getFailedShards() <= 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * <pre>
     * 索引优化方法
     * optimize API允许通过API优化1个或多个索引
     * 优化过程的操作可以优化索引搜索速度<br>
     * （涉及到Lucene索引内保存每个碎片的段数）
     * 优化操作合并Lucene段数和物理删除带删除标记的记录
     *
     * @param indexName      优化索引名 ,传null则对所有的索引进行归并
     * @param maxNumSegments 最大合并segment数
     * @return 是否优化成功，false:失败 true：成功
     * <pre>
     */
    public static boolean indexOptimize(int maxNumSegments, String... indexName) {
        logger.info("ES索引开始优化，索引名为:" + Arrays.toString(indexName));
        try {
            ForceMergeResponse response =
                    getClient().admin().indices().forceMerge(
                            new ForceMergeRequest(indexName)
                                    .flush(true)//是否合并完成后金进行flush操作，默认为true
                                    .maxNumSegments(maxNumSegments)//最大合并segment数
                                    .onlyExpungeDeletes(true)//是否只删除已做删除标记的记录
                    ).actionGet();
            if (response.getShardFailures().length == response.getTotalShards()) {
                logger.info("ES索引优化失败" + Arrays.toString(response.getShardFailures()));
                return false;
            } else if (response.getShardFailures().length > 0) {
                logger.info("ES索引优化部分分片失败" + Arrays.toString(response.getShardFailures()));
            }
            logger.info("ES索引优化成功");
            return true;
        } catch (Exception e) {
            logger.error("ES优化失败", e);
            return false;
        }
    }

    private static TransportClient createRemoteClient() {
        TransportClient client = null;
        try {
            //设置client.transport.sniff为true来使客户端去嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中
            client = new PreBuiltTransportClient(Settings.builder()
                    .put("cluster.name", ESUtils.clusterName)
                    .put("client.transport.sniff", true)
                    .build());
            for (String host : ESUtils.esHosts) {
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), ESUtils.esHostsPort));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
}
