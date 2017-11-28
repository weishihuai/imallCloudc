/*==============================================================*/
/* Table: STORAGE_SPACE_MOVE                                    */
/*==============================================================*/
create table T_SHP_STORAGE_SPACE_MOVE
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   BATCH_ID             BIGINT NOT NULL COMMENT '批次 ID',
   GOODS_ID             BIGINT NOT NULL COMMENT '商品 ID',
   SKU_ID               BIGINT NOT NULL COMMENT 'SKU ID',
   MOVE_ORDER_NUM       VARCHAR(32) NOT NULL COMMENT '移动 单号',
   MOVE_TIME            TIMESTAMP NOT NULL COMMENT '移动 时间',
   MOVE_MAN_NAME        VARCHAR(32) NOT NULL COMMENT '移动 人 姓名',
   QUANTITY             BIGINT NOT NULL COMMENT '数量',
   SOURCE_STORAGE_SPACE_ID BIGINT NOT NULL COMMENT '原 货位 ID',
   TARGET_STORAGE_SPACE_ID BIGINT NOT NULL COMMENT '目标 货位 ID',
   APPROVE_MAN_ID       BIGINT NOT NULL COMMENT '审核 人 ID',
   REMARK               VARCHAR(256) NULL COMMENT '备注',
   CREATE_DATE          TIMESTAMP COMMENT '创建日期',
   CREATE_BY            VARCHAR(32) COMMENT '创建人',
   last_modified_date   TIMESTAMP COMMENT '最后更新日期',
   last_modified_by     VARCHAR(32) COMMENT '最后更新人标识，记录用户的ID',
   VERSION              BIGINT NOT NULL COMMENT '版本管理标志',
   PRIMARY KEY (ID)
);