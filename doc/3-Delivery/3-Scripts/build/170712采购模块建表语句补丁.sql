drop table if exists T_SHP_PURCHASE_ORDER;
create table T_SHP_PURCHASE_ORDER
(
   ID                   bigint not null auto_increment comment '采购 订单 ID',
   SHOP_ID              bigint not null comment '门店 ID',
   PURCHASE_MAN         varchar(32) comment '采购 人',
   PURCHASE_ORDER_NUM   varchar(32) not null comment '采购 订单 编号',
   PURCHASE_ORDER_TYPE  varchar(32) not null comment '采购 订单 类型',
   PURCHASE_ORDER_STATE varchar(32) not null comment '采购 订单 状态',
   SUPPLIER_ID          bigint not null comment '供应商 ID',
   SELL_MAN             varchar(32) comment '销售 人',
   CONTACT_TEL          varchar(32) comment '联系 电话',
   EXPECTED_ARRIVAL_TIME timestamp not null comment '预计 到货 时间',
   PURCHASE_TOTAL_AMOUNT double not null comment '采购 总 金额',
   REMARK               varchar(32) comment '备注',
   CLEARING_TIME        timestamp NULL comment '已清 时间',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

drop table if exists T_SHP_PURCHASE_ORDER_ITEM;
create table T_SHP_PURCHASE_ORDER_ITEM
(
   ID                   bigint not null auto_increment comment '采购 订单 项 ID',
   SHOP_ID              bigint not null comment '门店 ID',
   PURCHASE_ORDER_ID    bigint not null comment '采购 订单 ID',
   GOODS_ID             bigint not null comment '商品 ID',
   SUPPLIER_ID          bigint not null comment '供应商 ID',
   PURCHASE_QUANTITY    bigint not null comment '采购 数量',
   PURCHASE_UNIT_PRICE  double not null comment '采购 单位 价格',
   IS_RECEIVE           char(1) not null comment '是否 已收货',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

drop table if exists T_SHP_PURCHASE_RECEIVE_RECORD;
create table T_SHP_PURCHASE_RECEIVE_RECORD
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   PURCHASE_ORDER_ID    bigint not null comment '采购 订单 ID',
   SUPPLIER_ID          bigint not null comment '供应商 ID',
   RECEIVE_ORDER_NUM    varchar(32) not null comment '收货单 编号',
   RECEIVER             varchar(32) comment '收货人',
   RECEIVE_TIME         date comment '收货 时间',
   REMARK               varchar(32) comment '备注',
   IS_ALL_ACCEPTANCE    char(1) not null comment '是否 全部 已验收',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

drop table if exists T_SHP_PURCHASE_RECEIVE_RECORD_ITEM;
create table T_SHP_PURCHASE_RECEIVE_RECORD_ITEM
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   PURCHASE_RECEIVE_RECORD_ID bigint not null comment '采购 收货 记录 ID',
   PURCHASE_ORDER_ID    bigint not null comment '采购 订单 ID',
   GOODS_ID             bigint not null comment '商品 ID',
   SUPPLIER_ID          bigint not null comment '供应商 ID',
   PURCHASE_UNIT_PRICE  double not null comment '采购 单位 价格',
   RECEIVE_QUANTITY     bigint not null comment '收货 数量',
   REJECTION_REASON     varchar(128) comment '拒收 原因',
   PURCHASE_QUANTITY    bigint not null comment '采购 数量',
   IS_ACCEPTANCE        char(1) not null comment '是否 已验收',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

drop table if exists T_SHP_PURCHASE_ACCEPTANCE_RECORD;
create table T_SHP_PURCHASE_ACCEPTANCE_RECORD
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   PURCHASE_RECEIVE_RECORD_ID bigint not null comment '采购 收货 记录 ID',
   PURCHASE_ORDER_ID    bigint not null comment '采购 订单 ID',
   ACCEPTANCE_ORDER_NUM varchar(32) not null comment '验收 单 编号',
   DOC_MAKER            varchar(32) comment '制单人',
   SUPPLIER_ID          bigint not null comment '供应商 ID',
   ACCEPTANCE_TIME      timestamp NULL comment '验收 时间',
   ACCEPTANCE_MAN       varchar(32) comment '验收 员',
   APPROVE_MAN_ID       bigint not null comment '审核 人 ID',
   IS_PAYED             char(1) not null comment '是否 已结款',
   PAYED_TIME           timestamp NULL comment '结款 时间',
   REMARK               varchar(32) comment '备注',
   ACCEPTANCE_TOTAL_AMOUNT double not null comment '验收 总 金额',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

drop table if exists T_SHP_PURCHASE_ACCEPTANCE_RECORD_ITEM;
create table T_SHP_PURCHASE_ACCEPTANCE_RECORD_ITEM
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   SUPPLIER_ID          bigint not null comment '供应商 ID',
   PURCHASE_ACCEPTANCE_RECORD_ID bigint not null comment '采购 验收 记录 ID',
   GOODS_ID             bigint not null comment '商品 ID',
   GOODS_NM             varchar(128) not null comment '商品 名称',
   GOODS_CODE           varchar(128) not null comment '商品 编码',
   PRODUCE_MANUFACTURER varchar(64) not null comment '生产厂商',
   GOODS_ARRIVAL_QUANTITY bigint not null comment '商品 到货 数量',
   REJECTION_QUANTITY   bigint not null comment '拒收 数量',
   QUALIFIED_QUANTITY   bigint not null comment '合格 数量',
   IN_STORAGE_QUANTITY  bigint not null comment '入库 数量',
   SAMPLE_QUANTITY      bigint not null comment '抽样 数量',
   STERILIZATION_BATCH  varchar(32) comment '灭菌 批次',
   ACCEPTANCE_REP       varchar(128) comment '验收 报告',
   PURCHASE_UNIT_PRICE  double not null comment '采购 单位 价格',
   ACCEPTANCE_QUALIFIED_AMOUNT double comment '验收 合格 金额',
   TOTAL_AMOUNT         double comment '总 金额',
   RETAIL_PRICE         double not null comment '零售价',
   GOODS_BATCH          varchar(32) not null comment '商品 批次',
   GOODS_BATCH_ID       bigint not null comment '商品 批次 ID',
   PRODUCTION_DATE      date not null comment '生产 日期',
   VALIDITY             date not null comment '有效期',
   STORAGE_SPACE_ID     bigint not null comment '货位 ID',
   GOODS_ALLOCATION     varchar(32) comment '货位',
   ACCEPTANCE_CONCLUSION varchar(128) comment '验收 结论',
   RETURNABLE_QUANTITY bigint not null comment '可退 数量',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

drop table if exists T_SHP_RETURNED_PURCHASE_ORDER;
create table T_SHP_RETURNED_PURCHASE_ORDER
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   SUPPLIER_ID          bigint not null comment '供应商 ID',
   PURCHASE_MAN         varchar(32) comment '采购 员',
   OUT_STORAGE_MAN      varchar(32),
   APPROVE_MAN_ID       bigint not null comment '审核 人 ID',
   RETURNED_PURCHASE_TOTAL_AMOUNT double not null comment '退货 总 金额',
   RETURNED_PURCHASE_ORDER_NUM varchar(32) not null comment '退货 单 编号',
   RETURNED_PURCHASE_TYPE varchar(32) not null comment '退货 类型',
   RETURNED_PURCHASE_REASON varchar(32) not null comment '退货 原因',
   REMARK               varchar(64) comment '备注',
   IS_PAYED             char(1) not null comment '是否 已结款',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

drop table if exists T_SHP_RETURNED_PURCHASE_ORDER_ITEM;
create table T_SHP_RETURNED_PURCHASE_ORDER_ITEM
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   RETURNED_PURCHASE_ORDER_ID bigint not null comment '购进 退出 订单 ID',
   GOODS_ID             bigint not null comment '商品 ID',
   SUPPLIER_ID          bigint not null comment '供应商 ID',
   GOODS_BATCH          varchar(32) not null comment '商品 批次',
   GOODS_BATCH_ID       bigint not null comment '商品 批次 ID',
   PRODUCTION_DATE      date not null comment '生产 日期',
   VALIDITY             date not null comment '有效期',
   RETURNED_PURCHASE_QUANTITY bigint not null comment '退货 数量',
   RETURNABLE_QUANTITY  bigint not null comment '可退 数量',
   PURCHASE_UNIT_PRICE  double not null comment '采购 单位 价格',
   AMOUNT               double not null comment '金额',
   PURCHASE_ACCEPTANCE_RECORD_ITEM_ID  bigint not null comment '采购 验收 记录 项 ID',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
