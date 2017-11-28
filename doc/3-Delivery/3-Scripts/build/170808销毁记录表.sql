/*==============================================================*/
/* Table: T_SHP_DESTROY_RECORD                                  */
/*==============================================================*/
CREATE TABLE T_SHP_DESTROY_RECORD
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   GOODS_CODE           VARCHAR(32) NOT NULL COMMENT '商品 编码',
   GOODS_NM             VARCHAR(128) NOT NULL COMMENT '商品 名称',
   GOODS_PINYIN         VARCHAR(64) NOT NULL COMMENT '商品 拼音',
   COMMON_NM            VARCHAR(64) NOT NULL COMMENT '通用 名称',
   SPEC                 VARCHAR(32) NOT NULL COMMENT '规格',
   DOSAGE_FORM          VARCHAR(32) COMMENT '剂型',
   UNIT                 VARCHAR(32) NOT NULL COMMENT '单位',
   PRODUCE_MANUFACTURER VARCHAR(64) NOT NULL COMMENT '生产厂商',
   PRODUCTION_PLACE     VARCHAR(32) COMMENT '产地',
   APPROVAL_NUMBER      VARCHAR(64) NOT NULL COMMENT '批准文号',
   SUPPLIER_NM          VARCHAR(32) NOT NULL COMMENT '供应商 名称',
   BATCH                VARCHAR(32) NOT NULL COMMENT '批号',
   STOCK_QUANTITY       BIGINT NOT NULL COMMENT '库存 数量',
   LOCK_QUANTITY        BIGINT NOT NULL COMMENT '锁定 数量',
   LOCK_REASON          VARCHAR(32) NOT NULL COMMENT '锁定 原因',
   PRODUCE_DATE         DATE NOT NULL COMMENT '生产 时间',
   VALID_DATE           DATE NOT NULL COMMENT '有效期至',
   STORAGE_SPACE_NM     VARCHAR(32) NOT NULL COMMENT '货位',
   DESTROY_PLACE        VARCHAR(32) COMMENT '销毁 地点',
   DESTROY_MAN          VARCHAR(32) NOT NULL COMMENT '销毁 人',
   DESTROY_TIME         DATETIME NOT NULL COMMENT '销毁 时间',
   KEEPER               VARCHAR(32) COMMENT '保管员',
   DESTROY_REASON       VARCHAR(128) COMMENT '销毁 原因',
   APPROVE_MAN_ID       BIGINT NOT NULL COMMENT '审核 人 ID',
   REVIEWER_ID          BIGINT COMMENT '复核人 ID',
   CREATE_DATE          TIMESTAMP NOT NULL COMMENT '创建时间',
   CREATE_BY            VARCHAR(32) NOT NULL COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '更新时间',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '更新用户',
   VERSION              BIGINT NOT NULL COMMENT '版本',
   PRIMARY KEY (ID)
);
