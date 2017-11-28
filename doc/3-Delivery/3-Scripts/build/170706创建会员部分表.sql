/*==============================================================*/
/* Table: MEMBER                                                */
/*==============================================================*/
create table T_SHP_MEMBER
(
   ID                   bigint not null comment '主键',
   NAME                 varchar(32) not null comment '姓名',
   MOBILE               varchar(32) not null comment '手机',
   SEX_CODE             varchar(32) not null comment '性别 代码',
   HOME_ADDR            varchar(128) comment '住址',
   PROFESSION           varchar(32) comment '职业',
   BIRTHDAY             timestamp comment '生日',
   DISEASE_HISTORY      varchar(512) comment '病史',
   COMMONLY_USED_DRUGS  varchar(512) comment '常用药',
   REMARK               varchar(512) comment '备注',
   IS_GIVE_CARD         char(2) not null comment '是否 发卡',
   MEMBER_CARD_NUM      varchar(32) comment '会员 卡 号码',
   IS_MEDICAL_INSURANCE_CARD char(2) comment '是否 医保卡',
   CARD_USE_STAT_CODE   varchar(32) comment '卡 使用 状态 代码',
   GIVE_CARD_MAN        bigint comment '发 卡 人',
   GIVE_CARD_TIME       timestamp comment '发卡 时间',
   EFFECT_TIME          timestamp comment '生效 时间',
   EXPIRE_TIME          timestamp comment '失效 时间',
   MEMBER_STAT_CODE     varchar(32) not null comment '会员 状态 代码',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
