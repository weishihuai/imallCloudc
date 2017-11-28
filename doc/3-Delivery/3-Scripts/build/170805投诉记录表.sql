DROP TABLE IF EXISTS T_SHP_COMPLAIN_RECORD;

/*==============================================================*/
/* Table: T_SHP_COMPLAIN_RECORD                                 */
/*==============================================================*/
CREATE TABLE T_SHP_COMPLAIN_RECORD
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   CUSTOMER_NAME        VARCHAR(32) NOT NULL COMMENT '顾客 姓名',
   COMPLAIN_DATE        DATE NOT NULL COMMENT '投诉 日期',
   MOBILE               VARCHAR(32) COMMENT '手机',
   ADDR                 VARCHAR(64) COMMENT '地址',
   COMPLAIN_CONT        VARCHAR(256) NOT NULL COMMENT '投诉 内容',
   GOODS_CODE           VARCHAR(32) COMMENT '商品 编码',
   GOODS_NM             VARCHAR(128) COMMENT '商品 名称',
   GOODS_PINYIN         VARCHAR(64) COMMENT '商品 拼音',
   GOODS_COMMON_NM      VARCHAR(64) COMMENT '商品 通用 名称',
   SPEC                 VARCHAR(32) COMMENT '规格',
   DOSAGE_FORM          VARCHAR(32) COMMENT '剂型',
   PRODUCE_MANUFACTURER VARCHAR(64) COMMENT '生产厂商',
   APPROVAL_NUMBER      VARCHAR(64) COMMENT '批准文号',
   BATCH                VARCHAR(32) COMMENT '批号',
   VALID_DATE           DATE COMMENT '有效期至',
   QUANTITY             INT COMMENT '数量',
   SURVEY_CONDITION     VARCHAR(32) COMMENT '调查 情况',
   SURVEY_MAN_NAME      VARCHAR(32) COMMENT '调查 人 姓名',
   SURVEY_DATE          DATE COMMENT '调查 日期',
   SUGGEST              VARCHAR(32) COMMENT '建议',
   PROCESS_MEASURE      VARCHAR(32) COMMENT '处理 措施',
   PROCESS_MAN_NAME     VARCHAR(32) COMMENT '处理 人 姓名',
   PROCESS_DATE         DATE COMMENT '处理 日期',
   CREATE_DATE          TIMESTAMP NOT NULL COMMENT '创建时间',
   CREATE_BY            VARCHAR(32) NOT NULL COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '更新时间',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '更新用户',
   VERSION              BIGINT NOT NULL COMMENT '版本',
   PRIMARY KEY (ID)
);
