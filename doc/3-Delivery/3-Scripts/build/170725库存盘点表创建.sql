/*==============================================================*/
/* Table: STOCK_CHECK                                           */
/*==============================================================*/
create table T_SHP_STOCK_CHECK
(
   ID                   bigint not null auto_increment comment '主键ID',
   SHOP_ID              bigint not null comment '门店 ID',
   GOODS_ID             bigint not null comment '商品 ID',
   SKU_ID               bigint not null comment 'SKU ID',
   GOODS_BATCH_ID       bigint not null comment '商品 批次 ID',
   CHECK_ORDER_NUM      varchar(32) not null comment '盘点 单号',
   CURRENT_STOCK        bigint not null comment '当前 库存',
   UNIT_PRICE           double not null comment '单位 价格',
   REAL_CHECK_QUANTITY  bigint comment '实盘 数量',
   REAL_CHECK_AMOUNT    double comment '实盘 金额',
   LOSS_QUANTITY        bigint comment '损益 数量',
   LOSS_AMOUNT          double comment '损益 金额',
   CHECKED_STATE_CODE   varchar(32) not null comment '盘点 状态 代码',
   IS_POSTING           varchar(1) not null comment '是否 已过帐',
   OPERATION_TIME       timestamp comment '操作 时间',
   OPERATOR_ID          bigint comment '操作人 ID',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);
