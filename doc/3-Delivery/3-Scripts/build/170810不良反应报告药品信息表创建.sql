/*==============================================================*/
/* Table: T_SHP_BAD_REACTION_DRUG_INF                           */
/*==============================================================*/
CREATE TABLE T_SHP_BAD_REACTION_DRUG_INF
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
   BAD_REACTION_REP_ID  BIGINT NOT NULL COMMENT '不良 反应 报告 ID',
   DRUG_INF_TYPE_CODE   VARCHAR(32) NOT NULL COMMENT '药品 信息 类型 代码',
   APPROVAL_NUMBER      VARCHAR(64) COMMENT '批准文号',
   GOODS_NM             VARCHAR(128) COMMENT '商品 名称',
   COMMON_NM            VARCHAR(64) COMMENT '通用 名称',
   PRODUCE_MANUFACTURER VARCHAR(64) COMMENT '生产厂家',
   BATCH                VARCHAR(32) COMMENT '生产 批号',
   USAGE_AND_DOSAGE     VARCHAR(128) COMMENT '用法用量',
   DRUG_USE_TIME        VARCHAR(32) COMMENT '用药 起止 时间',
   DRUG_USE_REASON      VARCHAR(32) COMMENT '用药 原因',
   CREATE_DATE          TIMESTAMP NOT NULL COMMENT '创建时间',
   CREATE_BY            VARCHAR(32) NOT NULL COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '更新时间',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '更新用户',
   VERSION              BIGINT NOT NULL COMMENT '版本',
   PRIMARY KEY (ID)
);
