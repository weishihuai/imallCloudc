create table T_SHP_USE_RECORD
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   FACILITY_AND_DEVICE_ACCOUNTS_ID bigint not null comment '设施 设备 台账 ID',
   PURPOSES             varchar(32) not null comment '使用 目的',
   USE_DATE             date not null comment '使用 日期',
   STOP_TIME            date comment '停止 时间',
   RECORD_DATE          date comment '记录 日期',
   SERVICE_CONDITION    varchar(32) comment '使用 情况',
   OPERATION_MAN        varchar(32) comment '操作 人',
   REMARK               varchar(32) comment '备注',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
