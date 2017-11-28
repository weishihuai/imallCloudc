
create table t_pt_sys_log
(
   ID                   bigint not null auto_increment comment 'ID',
   LOG_INNER_CODE       varchar(64) not null comment '日志 内部 编码',
   LOG_TIME             timestamp not null comment '日志 时间',
   LOG_TYPE_CODE        varchar(32) not null comment '日志 类型 代码',
   NAV                  varchar(64) comment '导航',
   LOG_TITLE            varchar(64) comment '日志 标题',
   LOG_CONTENT          longblob comment '日志 内容',
   CREATE_DATE          timestamp comment '创建时间',
   CREATE_BY            varchar(32) comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

alter table t_pt_sys_log comment '系统 日志';

create table t_pt_sys_log_data
(
   ID                   bigint not null auto_increment comment 'ID',
   LOG_INNER_CODE       varchar(64) not null comment '日志 内部 编码',
   TABLE_KEY            varchar(256) not null comment '表键',
   OBJECT_ID            bigint comment '对象 ID',
   OPERATION_TIME       timestamp not null comment '操作 时间',
   DATA_OPERATION_TYPE_CODE varchar(32) not null comment '数据 操作 类型 代码',
   BEFORE_OPERATION_DATA longblob comment '之前 操作 数据',
   AFTER_OPERATION_DATA longblob comment '之后 操作 数据',
   CREATE_DATE          timestamp comment '创建时间',
   CREATE_BY            varchar(32) comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

alter table t_pt_sys_log_data comment '系统 日志 数据';
