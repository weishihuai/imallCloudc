/*==============================================================*/
/* Table: SUPPLIER                                              */
/*==============================================================*/
create table T_SHP_SUPPLIER
(
   ID                   bigint not null auto_increment comment '供应商 ID',
   SHOP_ID              bigint not null comment '门店 ID',
   SUPPLIER_DOC_ID      bigint comment '供应商 档案 ID',
   SUPPLIER_NM          varchar(32) not null comment '供应商 名称',
   PINYIN               varchar(32) comment '拼音',
   SUPPLIER_CODE        varchar(32) not null comment '供应商 编码',
   UNIT_NATURE          varchar(32) comment '单位 性质',
   LEGAL_REPRESENTATIVE varchar(32) comment '法定代表人',
   QUALITY_RESPONSE_MAN_NAME varchar(32) comment '质量 负责 人 姓名',
   BUSINESS_RESPONSE_MAN_NAME varchar(32) comment '业务 负责 人 姓名',
   BUSINESS_RESPONSE_MAN_TEL varchar(32) not null comment '业务 负责 人 电话',
   BUSINESS_RESPONSE_MAN_EMAIL varchar(128) comment '业务 负责 人 邮箱',
   REG_CAPITAL          double comment '注册 资本',
   REG_ADDR             varchar(128) comment '注册 地址',
   FAX                  varchar(32) comment '传真',
   RETURNED_PURCHASE_ADDR varchar(128) comment '退货 地址',
   BUSINESS_CATEGORY    varchar(128) not null comment '经营 分类',
   BUSINESS_RANGE       varchar(128) not null comment '经营 范围',
   IS_FIRST_CHECK       varchar(32) not null comment '是否 首营',
   APPLY_MAN            varchar(32) comment '申请 人',
   APPLY_DATE           timestamp comment '申请 日期',
   SHOP_BUSINESS_RESPONSE_MAN varchar(32) comment '门店 业务 负责 人',
   SHOP_BUSINESS_RESPONSE_MAN_TEL varchar(32) comment '门店 业务 负责 人 电话',
   STATE                varchar(32) comment '状态',
   SUBMIT_OPINION       varchar(128) comment '提交 意见',
   QUALITY_MNG_SYSTEM_EVALUATE varchar(128) comment '质量 管理 体系 评价',
   REMARK               varchar(256) comment '备注',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);