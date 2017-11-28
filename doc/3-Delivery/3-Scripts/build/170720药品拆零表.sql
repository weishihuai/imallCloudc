create table T_SHP_GOODS_SPLIT_ZERO
(
   ID                   bigint not null auto_increment comment '主键ID',
   SHOP_ID              bigint not null comment '门店 ID',
   GOODS_ID             bigint not null comment '商品 ID',
   SKU_ID               bigint not null comment 'SKU ID',
   GOODS_BATCH_ID       bigint not null comment '商品 批次 ID',
   SPLIT_ZERO_QUANTITY  bigint not null comment '拆零 数量',
   SPLIT_SMALL_PACKAGE_QUANTITY bigint not null comment '拆后 小 包装 数量',
   USAGE_TEXT              varchar(64) comment '用法',
   DOSAGE               varchar(32) comment '用量',
   OPERATOR             varchar(32) not null comment '经办人',
   GOODS_NM             varchar(128)  not null comment '商品 名称',
   GOODS_COMMON_NM      varchar(64)  not null comment '商品 通用 名称',
   GOODS_CODE           varchar(32)  not null comment '商品 编码',
   PINYIN               varchar(32)  not null comment '拼音',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);
