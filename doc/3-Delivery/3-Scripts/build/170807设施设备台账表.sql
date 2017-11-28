create table T_SHP_FACILITY_AND_DEVICE_ACCOUNTS
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   DEVICE_TYPE_CODE     varchar(32) not null comment '设备 类型 代码',
   DEVICE_NUM           varchar(32) not null comment '设备 编号',
   DEVICE_NM            varchar(32) not null comment '设备 名称',
   MODEL                varchar(32) comment '型号',
   PRODUCE_MANUFACTURER varchar(64) comment '生产厂商',
   RESPONSE_MAN         varchar(32) not null comment '负责 人',
   PURCHASE_PRICE       double comment '购置 价格',
   PURCHASE_DATE        date comment '购买 日期',
   ENABLE_TIME          date comment '启用 时间',
   PURCHASE_PLACE       varchar(32) comment '购置 地点',
   APPLICATION          varchar(32) comment '用途',
   SERVICE_LIFE         int comment '使用 年限',
   REMARK               varchar(32) comment '备注',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);