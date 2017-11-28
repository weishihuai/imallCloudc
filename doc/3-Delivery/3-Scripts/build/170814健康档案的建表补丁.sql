/*==============================================================*/
/* Table: T_SHP_STAFF_HEALTH_DOC_INF                            */
/*==============================================================*/
create table T_SHP_STAFF_HEALTH_DOC_INF
(
   ID                   bigint not null auto_increment comment '主键',
   STAFF_HEALTH_DOC_ID  bigint not null comment '员工 健康 档案 ID',
   CHECK_NUM            varchar(32) not null comment '检查 编号',
   CHECK_DATE           date not null comment '检查 日期',
   CHECK_ORG            varchar(32) not null comment '检查 机构',
   CHECK_PRJ            varchar(32) not null comment '检查 项目',
   CHECK_RESULT         varchar(32) not null comment '检查 结果',
   TAKE_MEASURES        varchar(32) comment '采取 措施',
   REMARK               varchar(32) comment '备注',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);


/*==============================================================*/
/* Table: T_SHP_STAFF_HEALTH_DOC                                */
/*==============================================================*/
create table T_SHP_STAFF_HEALTH_DOC
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   STAFF_ID             bigint not null comment '员工 ID',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
