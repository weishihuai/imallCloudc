drop table if exists RECEIVER_ADDR;

/*==============================================================*/
/* Table: RECEIVER_ADDR                                         */
/*==============================================================*/
create table RECEIVER_ADDR
(
   ID                   bigint not null auto_increment comment '主键ID',
   WE_CHAT_USER_ID      bigint not null comment '微信 用户 ID',
   OPEN_ID              varchar(32) not null comment '微信 ID',
   RECEIVER_NAME        varchar(32) not null comment '收货人 姓名',
   CONTACT_TEL          varchar(32) not null comment '联系 电话',
   DELIVERY_ADDR        varchar(64) not null comment '配送 地址',
   DETAIL_ADDR          varchar(128) not null comment '详细 地址',
   ADDR_LAT             double not null comment '坐标 X',
   ADDR_LNG             double not null comment '坐标 Y',
   IS_DEFAULT           char(1) not null comment '是否 默认',
   POSITION_NAME        varchar(64) not null comment '位置 名称',
   CITY_NAME            varchar(64) not null comment '城市 名称',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);

alter table RECEIVER_ADDR comment '收货 地址';
