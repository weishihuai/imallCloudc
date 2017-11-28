create table T_SHP_TEMPERATURE_MOISTURE_MONITOR_RECORD
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   STORAGE_SPACE_NM     varchar(32) not null comment '货位',
   MONITOR_DATE         timestamp not null comment '监控 日期',
   RECORD_MAN           varchar(32) not null comment '记录 人',
   MONITOR_TIME         varchar(32) not null comment '监控 时间',
   TEMPERATURE          double not null comment '温度',
   MOISTURE             double not null comment '湿度',
   CONTROL_MEASURE      varchar(32) comment '调控 措施',
   TIME_AFTER_CONTROL   varchar(32) comment '调控后 时间',
   TEMPERATURE_AFTER_CONTROL double comment '调控后 温度',
   MOISTURE_AFTER_CONTROL double comment '调控后 湿度',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
