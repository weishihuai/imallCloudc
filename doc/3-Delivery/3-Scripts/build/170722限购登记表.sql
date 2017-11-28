create table T_SHP_LIMIT_BUY_REGISTER
(
   ID                   bigint not null auto_increment comment 'ID',
   SHOP_ID              bigint not null comment '门店 ID',
   ORDER_ID             bigint not null comment '订单 ID',
   SELL_ORDER_CODE      varchar(32) not null comment '销售 订单 编码',
   MEMBER_CARD_NUM      varchar(32) comment '会员 卡 号码',
   PATIENT_NM           varchar(32) not null comment '患者 名称',
   IDENTITY_CARD        varchar(32) not null comment '身份证',
   SEX_CODE             varchar(32) comment '性别 代码',
   MOBILE               varchar(32) comment '手机',
   ADDR                 varchar(128) comment '地址',
   REGISTER_DATE        datetime comment '登记 日期',
   REMARK               varchar(128) comment '备注',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
