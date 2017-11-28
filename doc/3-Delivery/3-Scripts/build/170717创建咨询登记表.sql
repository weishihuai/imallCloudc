drop table if exists T_SHP_CONSULT_SERVICE;

/*==============================================================*/
/* Table: T_SHP_CONSULT_SERVICE                                 */
/*==============================================================*/
create table T_SHP_CONSULT_SERVICE
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   MEMBER_CARD_NUM      varchar(32) comment '会员 卡号',
   PATIENT_NAME         varchar(32) not null comment '患者 姓名',
   AGE                  int comment '年龄',
   SEX                  varchar(32) comment '性别',
   MOBILE               varchar(32) comment '手机',
   IDENTITY_CARD        varchar(32) comment '身份证',
   HEIGHT               double comment '身高',
   WEIGHT               double comment '体重',
   ADDR                 varchar(128) comment '地址',
   REBAK_FUNCTION       varchar(64) comment '肾功能',
   IS_PREGNANT          char(1) comment '是否 怀孕',
   PREV_MEDICAL_HISTORY varchar(128) comment '过往 病史',
   CONSULT_PHARMACIST   varchar(32) comment '咨询 药师',
   CONSULT_TIME         date comment '咨询 时间',
   QUESTION_DESCR       varchar(128) comment '问题 描述',
   EXPERT_ANSWER        varchar(128) comment '药师 解答',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

DROP TABLE IF EXISTS T_SHP_CONSULT_GOODS_INF;

/*==============================================================*/
/* Table: T_SHP_CONSULT_GOODS_INF                               */
/*==============================================================*/
CREATE TABLE T_SHP_CONSULT_GOODS_INF
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
   CONSULT_SERVICE_ID   BIGINT NOT NULL COMMENT '咨询 服务 ID',
   GOODS_ID             BIGINT NOT NULL COMMENT '商品 ID',
   CREATE_DATE          TIMESTAMP NOT NULL COMMENT '创建时间',
   CREATE_BY            VARCHAR(32) NOT NULL COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '更新时间',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '更新用户',
   VERSION              BIGINT NOT NULL COMMENT '版本',
   PRIMARY KEY (ID)
);
