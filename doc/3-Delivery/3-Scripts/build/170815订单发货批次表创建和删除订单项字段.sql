ALTER TABLE `t_shp_order_item` DROP COLUMN `GOODS_BATCH_ID`;
ALTER TABLE `t_shp_order_item` DROP COLUMN `BATCH`;

create table T_SHP_ORDER_SEND_OUT_BATCH
(
   ID                   bigint not null auto_increment comment '订单 发货 批次',
   SHOP_ID              bigint not null comment '门店 ID',
   WE_SHOP_ID           bigint not null comment '微门店 ID',
   ORDER_ID             bigint not null comment '订单 ID',
   ORDER_ITEM_ID        bigint not null comment '订单 项 ID',
   GOODS_BATCH_ID       bigint not null comment '商品 批次 ID',
   BATCH                varchar(32) not null comment '批号',
   QUANTITY             bigint not null comment '数量',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

alter table T_SHP_ORDER_SEND_OUT_BATCH comment '订单 发货 批次';



