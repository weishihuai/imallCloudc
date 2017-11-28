create table t_shp_sell_returned_purchase_order
(
   ID                   bigint not null auto_increment comment '退货 订单 ID',
   ORDER_ID             bigint not null comment '订单 ID',
   SHOP_ID              bigint not null comment '子公司 ID',
   REFUND_TOTAL_AMOUNT  double not null comment '应退 总 金额',
   REAL_RETURN_CASH_AMOUNT double not null comment '实退 现金 金额',
   RETURN_SMALL         double not null comment '找零',
   CASHIER_ID           bigint not null comment '收款员',
   IS_OVERALL_RETURNED_PURCHASE char(1) not null comment '是否 整单 退货',
   APPROVE_MAN_ID       bigint not null comment '审核 人 ID',
   REMARK               varchar(128) comment '备注',
   RETURNED_PURCHASE_TIME timestamp not null comment '退货 时间',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

alter table t_shp_sell_returned_purchase_order comment '销售 退货 订单';


create table t_shp_sell_returned_purchase_order_item
(
   ID                   bigint not null auto_increment comment '退货 订单 项 ID',
   ORDER_ITEM_ID        bigint not null comment '订单 项 ID',
   RETURNED_PURCHASE_ORDER_ID bigint not null comment '退货 订单 ID',
   RETURNED_PURCHASE_QUANTITY bigint not null comment '退货 数量',
   UNIT_PRICE           double not null comment '单位 价格',
   TOTAL_AMOUNT         double not null comment '总 金额',
   BATCH_NUM            varchar(32) not null comment '批号',
   MOD_PRICE_REASON     varchar(256) comment '修改 价格 原因',
   MOD_QUANTITY_REASON  varchar(256) comment '修改 数量 原因',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

alter table t_shp_sell_returned_purchase_order_item comment '销售 退货 订单 项';