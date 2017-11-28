/*==============================================================*/
/* Table: MEASURING_DEVICE_ACCOUNTS                             */
/*==============================================================*/
create table T_SHP_MEASURING_DEVICE_ACCOUNTS
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   MEASURING_DEVICE_NUM varchar(32) not null comment '计量 器具 编号',
   MANUFACTURING_NUM    varchar(32) comment '出厂 编号',
   DEVICE_NM            varchar(128) not null comment '器具 名称',
   MODEL                varchar(32) comment '型号',
   PRODUCE_MANUFACTURER varchar(64) comment '生产厂商',
   CATEGORY_NUM         varchar(32) comment '分类 编号',
   MEASURE_RANGE        varchar(32) comment '测量 范围',
   PRECISION_LEVEL      varchar(32) comment '精度 等级',
   RESPONSE_MAN         varchar(32) not null comment '负责 人',
   MEASURE_PERIOD       int not null comment '检测 周期',
   PURCHASE_PRICE       double comment '购置 价格',
   PURCHASE_DATE        date comment '购买 日期',
   ENABLE_TIME          date comment '启用 时间',
   PURCHASE_PLACE       varchar(128) comment '购置 地点',
   APPLICATION          varchar(128) comment '用途',
   SERVICE_LIFE         int comment '使用 年限',
   USE_STATE_CODE       varchar(32) comment '使用 状态 代码',
   REMARK               varchar(128) comment '备注',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);


/*==============================================================*/
/* Table: T_SHP_MEASURE_RECORD                                  */
/*==============================================================*/
create table T_SHP_MEASURE_RECORD
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   MEASURING_DEVICE_ACCOUNTS_ID bigint not null comment '计量 器具 台账 ID',
   MEASURE_DATE         date not null comment '检测 日期',
   START_TIME           date not null comment '开始 时间',
   END_TIME             date not null comment '结束 时间',
   IDENTIFY_PRJ         varchar(32) comment '鉴定 项目',
   SKILL_REQUIREMENT    varchar(32) comment '技术 要求',
   MEASURE_METHOD       varchar(32) comment '检测 方法',
   IDENTIFY_CONCLUSION  varchar(32) comment '鉴定 结论',
   MEASURE_MAN          varchar(32) comment '检测 人',
   REVIEW_MAN           varchar(32) comment '复检人 名称',
   REMARK               varchar(32) comment '备注',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
