create table T_SHP_SALES_CATEGORY
(
   ID                   bigint not null auto_increment comment '主键ID',
   CATEGORY_NAME        varchar(64) not null comment '分类 名称',
   IS_FRONTEND_SHOW     char(1) not null comment '是否 前台 展示',
   DISPALY_POSITION     bigint not null comment '显示 排序',
   IS_ENABLE            char(1) not null comment '是否 启用',
   SHOP_ID              bigint not null comment '门店 ID',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);

alter table T_SHP_SALES_CATEGORY comment '销售 分类';