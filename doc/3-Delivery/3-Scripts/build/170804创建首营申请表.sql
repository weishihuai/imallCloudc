
/*==============================================================*/
/* Table: FIRST_MANAGE_SUPPLIER_QUALITY_APPROVE                 */
/*==============================================================*/
CREATE TABLE T_SHP_FIRST_MANAGE_SUPPLIER_QUALITY_APPROVE
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   SUPPLIER_ID          BIGINT NOT NULL COMMENT '供应商 ID',
   APPROVE_STATE_CODE   VARCHAR(32) NOT NULL COMMENT '审核 状态 代码',
   QUALITY_APPROVE_TIME DATETIME COMMENT '质量 审核 时间',
   APPLY_MAN_NAME       VARCHAR(32) NOT NULL COMMENT '申请 人 姓名',
   SUBMIT_ADVICE        VARCHAR(64) COMMENT '提交 意见',
   ENT_REMARK           VARCHAR(128) COMMENT '企业 备注',
   CREATE_DATE          TIMESTAMP NOT NULL COMMENT '创建时间',
   CREATE_BY            VARCHAR(32) NOT NULL COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '更新时间',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '更新用户',
   VERSION              BIGINT NOT NULL COMMENT '版本',
   PRIMARY KEY (ID)
);


