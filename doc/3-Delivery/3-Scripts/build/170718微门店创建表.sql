drop table if exists T_SHP_WE_SHOP;

/*==============================================================*/
/* Table: WE_SHOP                                               */
/*==============================================================*/
create table T_SHP_WE_SHOP
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   SHOP_NM              varchar(32) not null comment '门店 名称',
   SHOP_BRIEF           varchar(256) comment '门店 简介',
   SHOP_ZONE            bigint not null comment '门店 区域',
   SHOP_ZONE_PARENT       bigint not null comment '门店 区域 父节点',
   SHOP_ZONE_PARENT_NAME  varchar(64) not null comment '门店 区域 父节点 名称',
   DETAIL_LOCATION      varchar(256) not null comment '详细 位置',
   SHOP_LAT             double not null comment '门店 纬度',
   SHOP_LNG             double not null comment '门店 经度',
   DELIVERY_RANGE       bigint not null comment '配送 范围',
   CONTACT_TEL          varchar(32) not null comment '联系 电话',
   SHOP_PROMISE_SEND_TIME int comment '门店 承诺 送货 时间',
   PLACARD_INF          varchar(256) comment '公告 信息',
   SELL_START_TIME      varchar(32) not null comment '营业 开始 时间',
   SELL_END_TIME        varchar(32) not null comment '营业 结束 时间',
   IS_NORMAL_SALES      char(2) not null comment '是否 正常 营业',
   DELIVERY_TYPE_CODE   char(32) not null comment '配送 类型 代码',
   DELIVERY_AMOUNT      double not null comment '配送 金额',
   DELIVERY_MIN_ORDER_AMOUNT double not null comment '配送 最小 订单 金额（满额必送）',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

alter table T_SHP_WE_SHOP comment '微门店';
