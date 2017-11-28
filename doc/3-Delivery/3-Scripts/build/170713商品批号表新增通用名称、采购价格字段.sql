DROP TABLE IF EXISTS T_SHP_OUT_IN_STOCK_LOG;

/*==============================================================*/
/* Table: OUT_IN_STOCK_LOG                                      */
/*==============================================================*/
CREATE TABLE T_SHP_OUT_IN_STOCK_LOG
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   GOODS_ID             BIGINT NOT NULL COMMENT '商品 ID',
   SKU_ID               BIGINT NOT NULL COMMENT 'SKU ID',
   TYPE_CODE            VARCHAR(32) NOT NULL COMMENT '出入库 类型 代码',
   GOODS_BATCH_ID       BIGINT NOT NULL COMMENT '商品 批次 ID',
   STORAGE_SPACE_ID     BIGINT NOT NULL COMMENT '货位 ID',
   UNIT_PRICE           DOUBLE NOT NULL COMMENT '单位 价格',
   QUANTITY             BIGINT NOT NULL COMMENT '数量',
   AMOUNT               DOUBLE NOT NULL COMMENT '金额',
   CLEARING_PREV_QUANTITY BIGINT NOT NULL COMMENT '结算 前 数量',
   CLEARING_PREV_AMOUNT DOUBLE NOT NULL COMMENT '结算 前 金额',
   LOG_SOURCE_TYPE_CODE VARCHAR(32) NOT NULL COMMENT '日志 来源 类型 代码',
   LOG_SOURCE_OBJECT_ID BIGINT NOT NULL COMMENT '日志 来源 对象 ID',
   CREATE_DATE          TIMESTAMP COMMENT '创建日期',
   CREATE_BY            VARCHAR(32) COMMENT '创建人',
   last_modified_date   TIMESTAMP COMMENT '最后更新日期',
   last_modified_by     VARCHAR(32) COMMENT '最后更新人标识，记录用户的ID',
   VERSION              BIGINT NOT NULL COMMENT '版本管理标志',
   PRIMARY KEY (ID)
);
