/*==============================================================*/
/* Table: T_SHP_DISQUALIFICATION_DRUG_PROCESS_RECORD            */
/*==============================================================*/
CREATE TABLE T_SHP_DISQUALIFICATION_DRUG_PROCESS_RECORD
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   RECORD_DATE          DATETIME NOT NULL COMMENT '记录 日期',
   DOCUMENT_TYPE        VARCHAR(32) NOT NULL COMMENT '单据 类型',
   GOODS_CODE           VARCHAR(32) NOT NULL COMMENT '商品 编码',
   GOODS_NM             VARCHAR(128) NOT NULL COMMENT '商品 名称',
   GOODS_PINYIN         VARCHAR(64) NOT NULL COMMENT '商品 拼音',
   COMMON_NM            VARCHAR(64) NOT NULL COMMENT '通用 名称',
   SPEC                 VARCHAR(32) NOT NULL COMMENT '规格',
   DOSAGE_FORM          VARCHAR(32) COMMENT '剂型',
   UNIT                 VARCHAR(32) NOT NULL COMMENT '单位',
   PRODUCE_MANUFACTURER VARCHAR(64) NOT NULL COMMENT '生产厂商',
   APPROVAL_NUMBER      VARCHAR(64) NOT NULL COMMENT '批准文号',
   PRODUCTION_PLACE     VARCHAR(32) COMMENT '产地',
   BATCH                VARCHAR(32) NOT NULL COMMENT '批号',
   VALID_DATE           DATE NOT NULL COMMENT '有效期至',
   QUANTITY             bigint NOT NULL COMMENT '数量',
   PROCESS_MEASURE      VARCHAR(32) NOT NULL COMMENT '处理 措施',
   REMARK               VARCHAR(128) COMMENT '备注',
   CREATE_DATE          TIMESTAMP NOT NULL COMMENT '创建时间',
   CREATE_BY            VARCHAR(32) NOT NULL COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '更新时间',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '更新用户',
   VERSION              BIGINT NOT NULL COMMENT '版本',
   PRIMARY KEY (ID)
);
