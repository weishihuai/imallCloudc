/*==============================================================*/
/* Table: OTHER_OUT_STOCK                                       */
/*==============================================================*/
CREATE TABLE T_SHP_OTHER_OUT_STOCK
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   GOODS_ID             BIGINT NOT NULL COMMENT '商品 ID',
   SKU_ID               BIGINT NOT NULL COMMENT 'SKU ID',
   OUT_STOCK_CODE       VARCHAR(32) NOT NULL COMMENT '出库 单号',
   OUT_STOCK_TIME       TIMESTAMP NOT NULL COMMENT '出库 时间',
   TYPE_CODE            VARCHAR(32) NOT NULL COMMENT '类型 代码',
   STORAGE_SPACE_ID     BIGINT NOT NULL COMMENT '货位 ID',
   GOODS_BATCH_ID       BIGINT NOT NULL COMMENT '商品 批次 ID',
   QUANTITY             BIGINT NOT NULL COMMENT '数量',
   UNIT_PRICE           DOUBLE NOT NULL COMMENT '单位 价格',
   REAL_CHECK_AMOUNT    DOUBLE NOT NULL COMMENT '金额',
   APPROVE_MAN_ID       BIGINT NOT NULL COMMENT '审核 人 ID',
   OPERATION_MAN_ID     BIGINT NOT NULL COMMENT '出库人',
   REMARK               VARCHAR(256) COMMENT '备注',
   CREATE_DATE          TIMESTAMP COMMENT '创建日期',
   CREATE_BY            VARCHAR(32) COMMENT '创建人',
   last_modified_date   TIMESTAMP COMMENT '最后更新日期',
   last_modified_by     VARCHAR(32) COMMENT '最后更新人标识，记录用户的ID',
   VERSION              BIGINT NOT NULL COMMENT '版本管理标志',
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: OTHER_IN_STOCK                                        */
/*==============================================================*/
CREATE TABLE T_SHP_OTHER_IN_STOCK
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   GOODS_ID             BIGINT NOT NULL COMMENT '商品 ID',
   SKU_ID               BIGINT NOT NULL COMMENT 'SKU ID',
   IN_STOCK_CODE        VARCHAR(32) NOT NULL COMMENT '入库 单号',
   IN_STOCK_TIME        TIMESTAMP NOT NULL COMMENT '入库 时间',
   TYPE_CODE            VARCHAR(32) NOT NULL COMMENT '类型 代码',
   STORAGE_SPACE_ID     BIGINT NOT NULL COMMENT '货位 ID',
   SUPPLIER_ID          BIGINT NOT NULL COMMENT '供应商 ID',
   GOODS_BATCH_ID       VARCHAR(32) NOT NULL COMMENT '商品 批次 ID',
   QUANTITY             BIGINT NOT NULL COMMENT '数量',
   UNIT_PRICE           DOUBLE NOT NULL COMMENT '单位 价格',
   REAL_CHECK_AMOUNT    DOUBLE NOT NULL COMMENT '金额',
   PRODUCE_DATE         TIMESTAMP NOT NULL COMMENT '生产 日期',
   VALID_DATE           TIMESTAMP NOT NULL COMMENT '有效 日期',
   APPROVE_MAN_ID       BIGINT NOT NULL COMMENT '审核 人 ID',
   OPERATION_MAN_ID     BIGINT NOT NULL COMMENT '入库人',
   REMARK               VARCHAR(256) COMMENT '备注',
   CREATE_DATE          TIMESTAMP COMMENT '创建日期',
   CREATE_BY            VARCHAR(32) COMMENT '创建人',
   last_modified_date   TIMESTAMP COMMENT '最后更新日期',
   last_modified_by     VARCHAR(32) COMMENT '最后更新人标识，记录用户的ID',
   VERSION              BIGINT NOT NULL COMMENT '版本管理标志',
   PRIMARY KEY (ID)
);
