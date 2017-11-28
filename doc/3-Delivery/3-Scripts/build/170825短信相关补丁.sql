insert into `t_pt_sys_param_inf` (`create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `INNER_CODE`, `PARAM_DESCR`, `PARAM_GROUP_TYPE_CODE`, `PARAM_NM`, `PARAM_TYPE_CODE`, `PARAM_VALUE`, `SYS_ORG_ID`) values('admin','2017-08-25 10:52:25','admin','2017-08-25 10:49:52','0','WEB_NAME','网站名称','USER_DEFINED','网站名称','TEXT','智慧药店','2');
insert into `t_pt_sys_param_inf` (`create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `INNER_CODE`, `PARAM_DESCR`, `PARAM_GROUP_TYPE_CODE`, `PARAM_NM`, `PARAM_TYPE_CODE`, `PARAM_VALUE`, `SYS_ORG_ID`) values('admin','2017-08-25 10:52:27','admin','2017-08-25 10:49:52','0','MOBIL_RECEIVE_VALID_SMS_LIMIT_NUM','同一手机号每天发送短信限制条数','USER_DEFINED','同一手机号每天发送短信限制条数','NUMBER','20','2');
insert into `t_pt_sys_param_inf` (`create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `INNER_CODE`, `PARAM_DESCR`, `PARAM_GROUP_TYPE_CODE`, `PARAM_NM`, `PARAM_TYPE_CODE`, `PARAM_VALUE`, `SYS_ORG_ID`) values('admin','2017-08-25 10:53:00','admin','2017-08-25 10:52:57','0','IP_RECEIVE_VALID_SMS_LIMIT_NUM','同一IP每天发送短信限制条数','USER_DEFINED','同一IP每天发送短信限制条数','NUMBER','20','2');
insert into `t_pt_sys_param_inf` (`create_by`, `create_date`, `last_modified_by`, `last_modified_date`, `version`, `INNER_CODE`, `PARAM_DESCR`, `PARAM_GROUP_TYPE_CODE`, `PARAM_NM`, `PARAM_TYPE_CODE`, `PARAM_VALUE`, `SYS_ORG_ID`) values('admin','2017-08-25 10:54:13','admin','2017-08-25 10:54:08','0','SEND_SMS_FREQUENCY','发送验证码的频率（秒）','USER_DEFINED','发送验证码的频率（秒）','NUMBER','120','2');

DROP TABLE IF EXISTS T_PT_JSON_OBJECT_ENGINE;

CREATE TABLE T_PT_JSON_OBJECT_ENGINE
(
   JSON_OBJECT_ENGINE_ID BIGINT NOT NULL,
   JSON_OBJECT_TYPE_CODE VARCHAR(32) NOT NULL,
   JSON_OBJECT_ID       BIGINT NOT NULL,
   JSON_OBJECT_VALUE    LONGBLOB NOT NULL,
   CREATE_DATE          TIMESTAMP COMMENT '创建日期',
   CREATE_BY            VARCHAR(32) COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '最后更新日期',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '最后更新人标识，记录用户的ID',
   VERSION              BIGINT NOT NULL COMMENT '版本管理标志',
   PRIMARY KEY (JSON_OBJECT_ENGINE_ID)
);

drop table if exists T_PT_SMS_QUEUE;

/*==============================================================*/
/* Table: SMS_QUEUE                                             */
/*==============================================================*/
create table T_PT_SMS_QUEUE
(
   ID                   bigint not null auto_increment comment '短信 队列 ID',
   SYS_ORG_ID           bigint not null comment '组织 ID',
   RECEIVER_MOBILE      varchar(32) not null comment '接收人 号码',
   CONT                 varchar(256) not null comment '内容',
   SEND_STAT_CODE       varchar(32) not null comment '发送 状态 代码',
   OBJECT_ID            bigint not null comment '对象 ID',
   POSITION             bigint not null comment '排序',
   SEND_TIME            timestamp comment '发送 时间',
   ERROR_LOG            varchar(256) comment '失败 日志',
   ERROR_TIME           timestamp comment '发送 失败 时间',
   SMS_TYPE             varchar(32) not null comment '短信 类型',
   REQ_IP               varchar(32) not null comment '请求 IP',
   CREATE_DATE          timestamp comment '创建时间',
   CREATE_BY            varchar(32) comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);


DROP TABLE IF EXISTS T_PT_VALIDATE_CODE_LOG;

CREATE TABLE T_PT_VALIDATE_CODE_LOG
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
   SYS_ORG_ID           BIGINT NOT NULL COMMENT '组织 ID',
   ACCOUNT              VARCHAR(32) NOT NULL COMMENT '接收 账号',
   VALIDATE_CODE        VARCHAR(32) NOT NULL COMMENT '验证码',
   OBJECT_ID            BIGINT NOT NULL COMMENT '对象 ID',
   TYPE_CODE            VARCHAR(32) NOT NULL COMMENT '验证码 类型 代码',
   INVALID_TIME         TIMESTAMP NOT NULL COMMENT '失效 时间',
   CREATE_DATE          TIMESTAMP COMMENT '创建时间',
   CREATE_BY            VARCHAR(32) COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '更新时间',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '更新用户',
   VERSION              BIGINT NOT NULL COMMENT '版本',
   PRIMARY KEY (ID)
);


