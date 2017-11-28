create table T_SHP_DRUG_COMBINATION_CATEGORY
(
   ID                   bigint not null auto_increment,
   SHOP_ID              bigint not null comment '门店 ID',
   CATEGORY_NM          varchar(32) not null,
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);