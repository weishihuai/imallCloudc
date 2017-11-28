package com.imall.iportal.core.elasticsearch;

import com.google.common.collect.Maps;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.entity.*;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class IndexTypeMappingUtils {

    private static Map<String, String> settings;
    private static final String TYPE = "type";
    private static final String STORE = "store";
    private static final String YES = "yes";
    private static final String NO = "no";
    private static final String INDEX = "index";
    private static final String NOT_ANALYZED = "not_analyzed";
    private static final String LONG = "long";
    private static final String STRING = "string";
    private static final String TEXT = "text";
    private static final String IK_MAX_WORD = "ik_max_word";
    private static final String SEARCH_ANALYZER = "search_analyzer";
    private static final String DATE = "date";
    private static final String DOUBLE = "double";
    private static final String PROPERTIES = "properties";
    private static final String _ALL = "_all";
    private static final String ENABLED = "enabled";
    private static final String ANALYZER = "analyzer";
    private static final String NGRAM_TOKENIZER = "ngram_tokenizer";
    private static final String GEO_POINT = "geo_point";

    static {
        settings = Maps.newHashMap();
        settings.put("number_of_shards", "1");//设置一个索引的碎片数量
        settings.put("number_of_replicas", "0");//设置一个索引可被复制的数量
        settings.put("index.store.preload", "true");
        settings.put("index.store.type", "niofs"); //防止出现搜索线程超长时间执行的问题
        settings.put("index.refresh_interval", "30s");
        settings.put("index.merge.policy.floor_segment", "5mb");
        settings.put("index.translog.durability", "async");
        settings.put("index.translog.flush_threshold_size", "1g");//一旦你的事务日志文件的大小（translog）达到设置的这个值，则开始执行flush操作，默认值500mb


        //配置ngram tokenizer
        settings.put("analysis.analyzer.ngram_tokenizer.tokenizer", "ngram_tokenizer");
        settings.put("analysis.tokenizer.ngram_tokenizer.type", "ngram");
        settings.put("analysis.tokenizer.ngram_tokenizer.min_gram", "1");
        settings.put("analysis.tokenizer.ngram_tokenizer.max_gram", "20");
        settings.put("analysis.tokenizer.ngram_tokenizer.token_chars.0", "letter");
        settings.put("analysis.tokenizer.ngram_tokenizer.token_chars.1", "digit");
    }

    public static void createIndexTypeMapping(IndexTypeCodeEnum codeEnum){
        switch (codeEnum){
            case SYS_DOC:
                createDocIndexTypeMapping();
                break;
            case GOODS:
                createGoodsIndexTypeMapping();
                break;
            case OUT_IN_STOCK_LOG:
                createOutInStockLogIndexTypeMapping();
                break;
            case ORDER:
                createOrderIndexTypeMapping();
                break;
            case SELL_RETURNED_PURCHASE_ORDER:
                createReturnedPurchaseOrderIndexTypeMapping();
                break;
            case GOODS_BATCH:
                createGoodsBatchIndexTypeMapping();
                break;
            case WE_SHOP:
                createWeShopIndexTypeMapping();
                break;
            default:
                    break;
        }
    }

    private static void createDocIndexTypeMapping() {
        try {
            XContentBuilder mapBuilder = XContentFactory.jsonBuilder();
            mapBuilder
                    .startObject()
                    .startObject(IndexTypeCodeEnum.SYS_DOC.toCode())
                    .startObject("_all").field("enabled",false).endObject() //Index中默认会有_all的域，这个会给查询带来方便，但是会增加索引时间和索引尺寸
                    .startObject("properties")
                    .startObject("id").field("type", "long").field("store", "yes").field("index", "not_analyzed").endObject()
                    .startObject("title").field("type", "text").field("store", "yes").field("analyzer","ik_max_word").field("search_analyzer","ik_max_word").endObject()
                    .startObject("summary").field("type", "text").field("store", "yes").field("index", "not_analyzed").endObject()
                    .startObject("link").field("type", "text").field("store", "yes").field("index", "not_analyzed").endObject()
                    .startObject("releaseVersion").field("type", "text").field("store", "yes").field("index", "not_analyzed").endObject()
                    .startObject("isAvailable").field("type", "text").field("store", "yes").field("index", "not_analyzed").endObject()
                    .startObject("orderby").field("type", "long").field("store", "no").field("index", "not_analyzed").endObject()
                    .endObject()
                    .endObject()
                    .endObject();


            ActionFuture<CreateIndexResponse> response = ESUtils.getClient().admin().indices()
                    .create(Requests.createIndexRequest(IndexTypeCodeEnum.SYS_DOC.toCode())
                            .settings(settings)
                            .mapping(IndexTypeCodeEnum.SYS_DOC.toCode(), mapBuilder));
            System.out.println("isAcknowledged: " + response.actionGet().isAcknowledged());

        } catch (IOException e) {
            e.printStackTrace();
        }/*finally {
            ESUtils.getClient().close();
        }*/
    }

    private static void createGoodsIndexTypeMapping() {
        try {
            XContentBuilder mapBuilder = XContentFactory.jsonBuilder();
            mapBuilder
                    .startObject()
                    .startObject(IndexTypeCodeEnum.GOODS.toCode())
                    .startObject(_ALL).field(ENABLED,false).endObject() //Index中默认会有_all的域，这个会给查询带来方便，但是会增加索引时间和索引尺寸
                    .startObject(PROPERTIES)
                    .startObject(GoodsEntity.ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.SPLIT_ZERO_SOURCE_GOODS_ID).field(TYPE, LONG).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.GOODS_NM).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(GoodsEntity.GOODS_CODE).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(GoodsEntity.BRAND_NM).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(GoodsEntity.BAR_CODE).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(GoodsEntity.GOODS_TYPE_CODE).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.COMMON_NM).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(GoodsEntity.SHOP_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.STORAGE_SPACE_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.PRODUCE_MANUFACTURER).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(GoodsEntity.IS_DELETE).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.DISPLAY_POSITION).field(TYPE, LONG).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.PINYIN).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(GoodsEntity.APPROVE_STATE_CODE).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.GOODS_SPLIT_ZERO_IDS).field(TYPE, LONG).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.PRESCRIPTION_DRUGS_TYPE_CODE).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.IS_EPHEDRINE).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.CREATE_DATE).field(TYPE, DATE).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.STORAGE_SPACE_ID).field(TYPE, LONG).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.IS_ENABLE).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsEntity.SALES_CATEGORY_IDS).field(TYPE, LONG).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .endObject()
                    .endObject()
                    .endObject();

            ActionFuture<CreateIndexResponse> response = ESUtils.getClient().admin().indices()
                    .create(Requests.createIndexRequest(IndexTypeCodeEnum.GOODS.toCode())
                            .settings(settings)
                            .mapping(IndexTypeCodeEnum.GOODS.toCode(), mapBuilder));
            System.out.println("isAcknowledged: " + response.actionGet().isAcknowledged());

        } catch (IOException e) {
            e.printStackTrace();
        }/*finally {
            ESUtils.getClient().close();
        }*/
    }

    private static void createOutInStockLogIndexTypeMapping() {
        try {
            XContentBuilder mapBuilder = XContentFactory.jsonBuilder();
            mapBuilder
                    .startObject()
                    .startObject(IndexTypeCodeEnum.OUT_IN_STOCK_LOG.toCode())
                    .startObject(_ALL).field(ENABLED,false).endObject() //Index中默认会有_all的域，这个会给查询带来方便，但是会增加索引时间和索引尺寸
                    .startObject(PROPERTIES)
                    .startObject(OutInStockLogEntity.ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.SHOP_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.GOODS_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.TYPE_CODE).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.GOODS_BATCH_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.STORAGE_SPACE_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.GOODS_BATCH_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.UNIT_PRICE).field(TYPE, DOUBLE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.IN_STOCK_QUANTITY).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.OUT_STOCK_QUANTITY).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.IN_STOCK_AMOUNT).field(TYPE, DOUBLE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.OUT_STOCK_AMOUNT).field(TYPE, DOUBLE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.CLEARING_PREV_QUANTITY).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.CLEARING_PREV_AMOUNT).field(TYPE, DOUBLE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.LOG_SOURCE_TYPE_CODE).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OutInStockLogEntity.GOODS_NM).field(TYPE, STRING).field(STORE, YES).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OutInStockLogEntity.GOODS_CODE).field(TYPE, STRING).field(STORE, YES).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OutInStockLogEntity.COMMON_NM).field(TYPE, STRING).field(STORE, YES).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OutInStockLogEntity.BRAND_NM).field(TYPE, STRING).field(STORE, YES).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OutInStockLogEntity.PINYIN).field(TYPE, STRING).field(STORE, YES).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OutInStockLogEntity.BATCH).field(TYPE, STRING).field(STORE, YES).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OutInStockLogEntity.CREATE_DATE).field(TYPE, DATE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .endObject()
                    .endObject()
                    .endObject();

            ActionFuture<CreateIndexResponse> response = ESUtils.getClient().admin().indices()
                    .create(Requests.createIndexRequest(IndexTypeCodeEnum.OUT_IN_STOCK_LOG.toCode())
                            .settings(settings)
                            .mapping(IndexTypeCodeEnum.OUT_IN_STOCK_LOG.toCode(), mapBuilder));
            System.out.println("isAcknowledged: " + response.actionGet().isAcknowledged());

        } catch (IOException e) {
            e.printStackTrace();
        }/*finally {
            ESUtils.getClient().close();
        }*/
    }

    private static void createOrderIndexTypeMapping() {
        try {
            XContentBuilder mapBuilder = XContentFactory.jsonBuilder();
            mapBuilder
                    .startObject()
                    .startObject(IndexTypeCodeEnum.ORDER.toCode())
                    .startObject(_ALL).field(ENABLED,false).endObject() //Index中默认会有_all的域，这个会给查询带来方便，但是会增加索引时间和索引尺寸
                    .startObject(PROPERTIES)
                    .startObject(OrderEntity.ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.WE_SHOP_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.WECHAT_USER_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.ORDER_SOURCE_CODE).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.ORDER_NUM).field(TYPE, STRING).field(STORE, YES).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OrderEntity.OPEN_ORDER_MAN_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.MEMBER_CARD_NUM).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.NAME).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.MEMBER_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.PAYMENT_WAY_CODE).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.ORDER_STATE_CODE).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.IS_PAYED).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.IS_COD).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.FINISH_TIME).field(TYPE, DATE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.IS_EPHEDRINE_ORDER).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.IS_PRESCRIPTION_ORDER).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.CREATE_DATE).field(TYPE, DATE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.IS_HAS_RETURNED_PURCHASE).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.ORDER_TOTAL_AMOUNT).field(TYPE, DOUBLE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.REAL_GEVE_CASH_AMOUNT).field(TYPE, DOUBLE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.RETURN_SMALL).field(TYPE, DOUBLE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.MEDICAL_INSURANCE_PAYMENT_AMOUNT).field(TYPE, DOUBLE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.RECEIVER_NAME).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OrderEntity.CONTACT_TEL).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.BOOK_DELIVERY_TIME_START).field(TYPE, DATE).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderEntity.BOOK_DELIVERY_TIME_END).field(TYPE, DATE).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    //订单项
                    .startObject(OrderItemEntity.GOODS_NM).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OrderItemEntity.COMMON_NM).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OrderItemEntity.GOODS_PINYIN).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OrderItemEntity.PRODUCE_MANUFACTURER).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OrderItemEntity.GOODS_CODING).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OrderItemEntity.BAR_CODE).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(OrderItemEntity.BATCH).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderItemEntity.SPEC).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderItemEntity.UNIT).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderItemEntity.ORDER_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderItemEntity.GOODS_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderItemEntity.SKU_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderItemEntity.QUANTITY).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderItemEntity.SELLER).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderItemEntity.GOODS_UNIT_PRICE).field(TYPE, DOUBLE).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderItemEntity.GOODS_COST_TOTAL_AMOUNT).field(TYPE, DOUBLE).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(OrderItemEntity.GOODS_TOTAL_AMOUNT).field(TYPE, DOUBLE).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .endObject()
                    .endObject()
                    .endObject();

            ActionFuture<CreateIndexResponse> response = ESUtils.getClient().admin().indices()
                    .create(Requests.createIndexRequest(IndexTypeCodeEnum.ORDER.toCode())
                            .settings(settings)
                            .mapping(IndexTypeCodeEnum.ORDER.toCode(), mapBuilder));
            System.out.println("isAcknowledged: " + response.actionGet().isAcknowledged());

        } catch (IOException e) {
            e.printStackTrace();
        }/*finally {
            ESUtils.getClient().close();
        }*/
    }

    private static void createReturnedPurchaseOrderIndexTypeMapping() {
        try {
            XContentBuilder mapBuilder = XContentFactory.jsonBuilder();
            mapBuilder
                    .startObject()
                    .startObject(IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER.toCode())
                    .startObject(_ALL).field(ENABLED,false).endObject() //Index中默认会有_all的域，这个会给查询带来方便，但是会增加索引时间和索引尺寸
                    .startObject(PROPERTIES)
                    .startObject(SellReturnedPurchaseOrderEntity.ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.ORDER_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.SELL_RETURNED_PURCHASE_ORDER_NUM).field(TYPE, STRING).field(STORE, YES).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.SHOP_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.REFUND_TOTAL_AMOUNT).field(TYPE, DOUBLE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.REAL_RETURN_CASH_AMOUNT).field(TYPE, DOUBLE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.RETURN_SMALL).field(TYPE, DOUBLE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.IS_OVERALL_RETURNED_PURCHASE).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.CASHIER_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.APPROVE_MAN_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.REMARK).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.RETURNED_PURCHASE_TIME).field(TYPE, DATE).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.MEMBER_CARD_NUM).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(SellReturnedPurchaseOrderEntity.NAME).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    //销售退货订单项
                    .startObject(SellReturnedPurchaseOrderItemEntity.GOODS_NM).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(SellReturnedPurchaseOrderItemEntity.COMMON_NM).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(SellReturnedPurchaseOrderItemEntity.GOODS_PINYIN).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(SellReturnedPurchaseOrderItemEntity.BAR_CODE).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(SellReturnedPurchaseOrderItemEntity.GOODS_CODING).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .endObject()
                    .endObject()
                    .endObject();

            ActionFuture<CreateIndexResponse> response = ESUtils.getClient().admin().indices()
                    .create(Requests.createIndexRequest(IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER.toCode())
                            .settings(settings)
                            .mapping(IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER.toCode(), mapBuilder));
            System.out.println("isAcknowledged: " + response.actionGet().isAcknowledged());

        } catch (IOException e) {
            e.printStackTrace();
        }/*finally {
            ESUtils.getClient().close();
        }*/
    }

    private static void createGoodsBatchIndexTypeMapping() {
        try {
            XContentBuilder mapBuilder = XContentFactory.jsonBuilder();
            mapBuilder
                    .startObject()
                    .startObject(IndexTypeCodeEnum.GOODS_BATCH.toCode())
                    .startObject(_ALL).field(ENABLED,false).endObject() //Index中默认会有_all的域，这个会给查询带来方便，但是会增加索引时间和索引尺寸
                    .startObject(PROPERTIES)
                    .startObject(GoodsBatchEntity.ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.GOODS_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.SHOP_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.GOODS_NM).field(TYPE, STRING).field(STORE, YES).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(GoodsBatchEntity.GOODS_CODE).field(TYPE, STRING).field(STORE, YES).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(GoodsBatchEntity.COMMON_NM).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(GoodsBatchEntity.PINYIN).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(GoodsBatchEntity.GOODS_TYPE_CODE).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.PRODUCE_MANUFACTURER).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.IS_EPHEDRINE).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.GOODS_BATCH).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.BATCH_STATE).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.IS_BUILT_IN).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.IS_VIRTUAL_WAREHOUSE).field(TYPE, STRING).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.PRESCRIPTION_DRUGS_TYPE_CODE).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.STORAGE_SPACE_ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.STORAGE_SPACE_TYPE).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.HAS_STOCK).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.IS_SPLIT_ZERO).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.CREATE_DATE).field(TYPE, DATE).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.IN_STOCK_TIME).field(TYPE, DATE).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(GoodsBatchEntity.VALID_DATE).field(TYPE, DATE).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .endObject()
                    .endObject()
                    .endObject();

            ActionFuture<CreateIndexResponse> response = ESUtils.getClient().admin().indices()
                    .create(Requests.createIndexRequest(IndexTypeCodeEnum.GOODS_BATCH.toCode())
                            .settings(settings)
                            .mapping(IndexTypeCodeEnum.GOODS_BATCH.toCode(), mapBuilder));
            System.out.println("isAcknowledged: " + response.actionGet().isAcknowledged());

        } catch (IOException e) {
            e.printStackTrace();
        }/*finally {
            ESUtils.getClient().close();
        }*/
    }

    private static void createWeShopIndexTypeMapping() {
        try {
            XContentBuilder mapBuilder = XContentFactory.jsonBuilder();
            mapBuilder
                    .startObject()
                    .startObject(IndexTypeCodeEnum.WE_SHOP.toCode())
                    .startObject(_ALL).field(ENABLED,false).endObject() //Index中默认会有_all的域，这个会给查询带来方便，但是会增加索引时间和索引尺寸
                    .startObject(PROPERTIES)
                    .startObject(WeShopEntity.ID).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(WeShopEntity.SHOP_NM).field(TYPE, STRING).field(STORE, NO).field(ANALYZER, NGRAM_TOKENIZER).field(SEARCH_ANALYZER, NGRAM_TOKENIZER).endObject()
                    .startObject(WeShopEntity.SHOP_ZONE).field(TYPE, LONG).field(STORE, YES).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(WeShopEntity.SHOP_LAT).field(TYPE, DOUBLE).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(WeShopEntity.SHOP_LNG).field(TYPE, DOUBLE).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(WeShopEntity.DELIVERY_RANGE).field(TYPE, LONG).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(WeShopEntity.CONTACT_TEL).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(WeShopEntity.IS_NORMAL_SALES).field(TYPE, STRING).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .startObject(WeShopEntity.LOCATION).field(TYPE, GEO_POINT).field(STORE, NO).field(INDEX, NOT_ANALYZED).endObject()
                    .endObject()
                    .endObject()
                    .endObject();

            ActionFuture<CreateIndexResponse> response = ESUtils.getClient().admin().indices()
                    .create(Requests.createIndexRequest(IndexTypeCodeEnum.WE_SHOP.toCode())
                            .settings(settings)
                            .mapping(IndexTypeCodeEnum.WE_SHOP.toCode(), mapBuilder));
            System.out.println("isAcknowledged: " + response.actionGet().isAcknowledged());

        } catch (IOException e) {
            e.printStackTrace();
        }/*finally {
            ESUtils.getClient().close();
        }*/
    }

    public static void updateSetting(){
        settings = Maps.newHashMap();
        settings.put("index.refresh_interval", "30s");
        settings.put("index.number_of_replicas", "0");
        try {
            ActionFuture<UpdateSettingsResponse> response = ESUtils.getClient().admin().indices().updateSettings(new UpdateSettingsRequest().settings(settings));
            System.out.println("isAcknowledged: " + response.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}