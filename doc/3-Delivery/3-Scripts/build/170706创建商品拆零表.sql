DROP TABLE IF EXISTS GOODS_SPLIT_ZERO;

/*==============================================================*/
/* Table: GOODS_SPLIT_ZERO                                      */
/*==============================================================*/
CREATE TABLE GOODS_SPLIT_ZERO
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   GOODS_ID             BIGINT NOT NULL COMMENT '商品 ID',
   SKU_ID               BIGINT NOT NULL COMMENT 'SKU ID',
   GOODS_BATCH_ID       BIGINT NOT NULL COMMENT '商品 批次 ID',
   SPLIT_ZERO_QUANTITY  BIGINT NOT NULL COMMENT '拆零 数量',
   SPLIT_SMALL_PACKAGE_QUANTITY BIGINT NOT NULL COMMENT '拆后 小 包装 数量',
   USAGE_TEXT              VARCHAR(64) COMMENT '用法',
   DOSAGE               VARCHAR(32) COMMENT '用量',
   OPERATOR             VARCHAR(32) NOT NULL COMMENT '经办人',
   GOODS_NM             VARCHAR(128)  NOT NULL COMMENT '商品 名称',
   GOODS_COMMON_NM      VARCHAR(64)  NOT NULL COMMENT '商品 通用 名称',
   GOODS_CODE           VARCHAR(32)  NOT NULL COMMENT '商品 编码',
   PINYIN               VARCHAR(64)  NOT NULL COMMENT '拼音',
   CREATE_DATE          TIMESTAMP COMMENT '创建日期',
   CREATE_BY            VARCHAR(32) COMMENT '创建人',
   last_modified_date   TIMESTAMP COMMENT '最后更新日期',
   last_modified_by     VARCHAR(32) COMMENT '最后更新人标识，记录用户的ID',
   VERSION              BIGINT NOT NULL COMMENT '版本管理标志',
   PRIMARY KEY (ID)
);