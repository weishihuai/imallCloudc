create table T_SHP_DRUG_CURING
(
   ID                   bigint not null auto_increment comment 'ID',
   SHOP_ID              bigint not null comment '门店 ID',
   CURING_DOCUMENT_NUM  varchar(32) not null comment '养护 单据 号码',
   PLAN_CURING_TIME     timestamp comment '计划 养护 时间',
   CURING_TYPE_CODE     varchar(32) not null comment '养护 类型 代码',
   CURING_MAN_ID        bigint comment '审核 人 ID',
   CURING_STATE_CODE    varchar(32) not null comment '养护 状态 代码',
   CURING_FINISH_TIME   datetime comment '养护 完成 时间',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);

create table T_SHP_DRUG_CURING_ITEM
(
   ID                   bigint not null auto_increment comment 'ID',
   DRUG_CURING_ID       bigint not null comment '药品 养护 ID',
   SHOP_ID              bigint not null comment '门店 ID',
   GOODS_ID             bigint not null comment '商品 ID',
   GOODS_BATCH_ID       bigint not null comment '商品 批次 ID',
   CURING_QUANTITY      bigint comment '养护 数量',
   CURING_PRJ           varchar(128) comment '养护 项目',
   NOT_QUALIFIED_QUANTITY bigint comment '不 合格 数量',
   PROCESS_OPINION      varchar(128) comment '处理 意见',
   CONCLUSION           varchar(128) comment '结论',
   REMARK               varchar(256) comment '备注',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);
