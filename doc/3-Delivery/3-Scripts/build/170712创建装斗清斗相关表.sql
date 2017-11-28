
/*==============================================================*/
/* Table: DRUG_IN_BUCKET 装斗表                                       */
/*==============================================================*/
create table T_SHP_DRUG_IN_BUCKET
(
   ID                   bigint not null auto_increment comment '主键ID',
   SHOP_ID              bigint not null comment '门店 ID',
   GOODS_ID             bigint not null comment '商品 ID',
   SKU_ID               bigint not null comment 'SKU ID',
   BATCH_ID             bigint not null comment 'BATCH ID',
   IN_BUCKET_MAN_NAME   varchar(32) not null comment '装斗 人 姓名',
   IN_BUCKET_TIME       timestamp not null comment '装斗 时间',
   QUANTITY             bigint not null comment '数量',
   STORAGE_SPACE_ID     bigint not null comment '货位 ID',
   APPROVE_MAN_ID       bigint not null comment '审核 人 ID',
   IS_QUALITY_QUALIFIED char(1) not null comment '是否 质量 合格',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);

/*==============================================================*/
/* Table: DRUG_CLEAR_BUCKET 清斗表                                       */
/*==============================================================*/
create table T_SHP_DRUG_CLEAR_BUCKET
(
   ID                   bigint not null auto_increment comment '主键ID',
   SHOP_ID              bigint not null comment '门店 ID',
   GOODS_ID             bigint not null comment '商品 ID',
   SKU_ID               bigint not null comment 'SKU ID',
   BATCH_ID             bigint not null comment 'BATCH ID',
   CLEAR_BUCKET_MAN_NAME varchar(32) not null comment '清斗 人 姓名',
   CLEAR_BUCKET_TIME    timestamp not null comment '清斗 时间',
   QUANTITY             bigint not null comment '数量',
   STORAGE_SPACE_ID     bigint not null comment '货位 ID',
   APPROVE_MAN_ID       bigint not null comment '审核 人 ID',
   IS_QUALITY_QUALIFIED char(1) not null comment '是否 质量 合格',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);
