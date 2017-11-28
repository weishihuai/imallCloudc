create table T_SHP_DRUG_COMBINATION
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   DRUG_COMBINATION_CATEGORY_ID bigint not null comment '联合用药 分类 ID',
   DISEASE              varchar(64) not null comment '病症',
   SYMPTOM              varchar(256) not null comment '症状',
   COMMON_SENSE         varchar(256) not null comment '常识 判断',
   DRUG_USE_PRINCIPLE   varchar(256) comment '用药 原则',
   LEADING_DRUG         varchar(256) not null comment '主导 用药',
   DRUG_COMBINATION     varchar(256) comment '联合 用药',
   MAJOR_CONCERN        varchar(256) comment '专业 关怀',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
