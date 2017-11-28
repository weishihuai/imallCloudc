CREATE TABLE t_shp_shopping_cart_store
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
   CART_TYPE_CODE       VARCHAR(32) NOT NULL COMMENT '购物 车 类型 代码',
   CART_UNIQUE_KEY      VARCHAR(32) NOT NULL COMMENT '购物 车 唯一 Key',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   MEMBER_ID            BIGINT NOT NULL COMMENT '会员 ID',
   GOODS_ID             BIGINT NOT NULL COMMENT '商品 ID',
   SKU_ID               BIGINT NOT NULL COMMENT 'SKU ID',
   GOODS_BATCH_ID       BIGINT NOT NULL COMMENT '批次 ID',
   QUANTITY             BIGINT NOT NULL COMMENT '数量',
   SALES_MAN_ID         BIGINT NOT NULL COMMENT '营业员 ID',
   CREATE_DATE          TIMESTAMP NOT NULL COMMENT '创建时间',
   CREATE_BY            VARCHAR(32) NOT NULL COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '更新时间',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '更新用户',
   VERSION              BIGINT NOT NULL COMMENT '版本',
   PRIMARY KEY (ID)
);

alter table t_shp_shopping_cart_store comment '购物车 持久化';