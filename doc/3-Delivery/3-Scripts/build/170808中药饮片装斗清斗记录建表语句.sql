drop table if exists T_SHP_CHINESE_MEDICINE_PIECES_CLEANING_BUCKET_RECORD;

/*==============================================================*/
/* Table: T_SHP_CHINESE_MEDICINE_PIECES_CLEANING_BUCKET_RECORD  */
/*==============================================================*/
create table T_SHP_CHINESE_MEDICINE_PIECES_CLEANING_BUCKET_RECORD
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   CLEANING_BUCKET_DATE datetime not null comment '清斗 日期',
   CLEANING_BUCKET_QUANTITY bigint not null comment '清斗 数量',
   GOODS_CODE           varchar(32) not null comment '商品 编码',
   GOODS_NM             varchar(128) not null comment '商品 名称',
   GOODS_PINYIN         varchar(64) not null comment '商品 拼音',
   COMMON_NM            varchar(64) not null comment '通用 名称',
   SPEC                 varchar(32) not null comment '规格',
   DOSAGE_FORM          varchar(32) comment '剂型',
   UNIT                 varchar(32) not null comment '单位',
   PRODUCE_MANUFACTURER varchar(64) not null comment '生产 厂商',
   APPROVAL_NUMBER      varchar(64) not null comment '批准文号',
   PRODUCTION_PLACE     varchar(32) comment '产地',
   BATCH                varchar(32) not null comment '批号',
   PRODUCE_TIME         date not null comment '生产 时间',
   VALID_DATE           date not null comment '有效期至',
   STORAGE_SPACE_NM     varchar(32) not null comment '货位',
   CLEANING_BUCKET_MAN_NM varchar(32) not null comment '清斗 人 姓名',
   APPROVE_MAN_ID       bigint not null comment '审核 人 ID',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
drop table if exists T_SHP_CHINESE_MEDICINE_PIECES_LOADING_BUCKET_RECORD;

/*==============================================================*/
/* Table: T_SHP_CHINESE_MEDICINE_PIECES_LOADING_BUCKET_RECORD   */
/*==============================================================*/
create table T_SHP_CHINESE_MEDICINE_PIECES_LOADING_BUCKET_RECORD
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   LOADING_BUCKET_DATE  datetime not null comment '装斗 日期',
   LOADING_BUCKET_MAN_NM  varchar(32) not null comment '装斗 人 姓名',
   APPROVE_MAN_ID       bigint not null comment '审核 人 ID',
   GOODS_NM             varchar(128) not null comment '商品 名称',
   GOODS_PINYIN         varchar(64) not null comment '商品 拼音',
   COMMON_NM            varchar(64) not null comment '通用 名称',
   SPEC                 varchar(32) not null comment '规格',
   PRODUCE_MANUFACTURER varchar(64) not null comment '生产厂商',
   PRODUCTION_PLACE     varchar(32) comment '产地',
   BATCH                varchar(32) not null comment '批号',
   PRODUCE_DATE         date not null comment '生产 时间',
   VALID_DATE           date not null comment '有效期至',
   ORIGINAL_STORAGE_SPACE_NM varchar(32) not null comment '原货位',
   TARGET_STORAGE_SPACE_NM varchar(32) not null comment '目标 货位',
   LOADING_BUCKET_QUANTITY bigint not null comment '装斗 数量',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
