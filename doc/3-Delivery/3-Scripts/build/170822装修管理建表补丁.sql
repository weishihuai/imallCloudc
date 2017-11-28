drop table if exists T_SHP_DECORATION_RECOMMEND;
create table T_SHP_DECORATION_RECOMMEND
(
   ID                   bigint not null auto_increment comment '主键ID',
   SHOP_ID              bigint not null comment '门店 ID',
   DECORATION_GROUP_ID  bigint not null comment '装修 分组 ID',
   GOODS_ID             bigint not null comment '商品 ID',
   SKU_ID               bigint not null comment 'SKU ID',
   DISPLAY_POSITION     bigint not null comment '显示 排序',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);
alter table T_SHP_DECORATION_RECOMMEND comment '装修 推荐';

drop table if exists T_SHP_DECORATION_RECOMMEND_GROUP;
create table T_SHP_DECORATION_RECOMMEND_GROUP
(
   ID                   bigint not null auto_increment comment '主键ID',
   SHOP_ID              bigint not null comment '门店 ID',
   GROUP_NM             varchar(32) not null comment '分组 名称',
   DISPLAY_POSITION     bigint not null comment '显示 排序',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);
alter table T_SHP_DECORATION_RECOMMEND_GROUP comment '装修 推荐 分组';



