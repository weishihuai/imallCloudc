/*==============================================================*/
/* Table: ORDER_ITEM       订单项                               */
/*==============================================================*/
create table T_SHP_ORDER_ITEM
(
   ID                   bigint not null auto_increment comment '订单 项 ID',
   ORDER_ID             bigint not null comment '订单 ID',
   PRODUCT_ID           bigint not null comment '产品 ID',
   GOODS_ID             bigint not null comment '商品 ID',
   SKU_ID               bigint not null comment 'SKU ID',
   GOODS_CODING         varchar(64) not null comment '商品 编码',
   COMMON_NM            varchar(64) comment '通用 名称',
   GOODS_NM             varchar(128) not null comment '商品 名称',
   MANUFACTURER_FACTORY varchar(64) comment '生产 厂家',
   SPAN                 varchar(32) comment '规格',
   UNIT                 varchar(32) comment '单位',
   BATCH                varchar(32) comment '批号',
   QUANTITY             int not null comment '数量',
   GOODS_UNIT_PRICE     double not null comment '商品 单位 价格',
   GOODS_COST_UNIT_PRICE double not null comment '商品 成本 价格',
   GOODS_TOTAL_AMOUNT   double not null comment '商品 总 金额',
   REMARK               varchar(128) comment '备注',
   SELLER               bigint not null comment '营业员',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);




