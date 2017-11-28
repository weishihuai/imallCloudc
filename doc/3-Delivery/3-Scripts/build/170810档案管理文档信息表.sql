create table T_SHP_DOC_INF
(
   ID                   bigint not null auto_increment comment '主键',
   SEQNUM               double not null comment '序号',
   TABLE_NM             varchar(32) not null comment '表格 名称',
   DOWNLOAD_ADDR        varchar(256) not null comment '下载 地址',
   DOC_TYPE             varchar(32) not null comment '文档 类型',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);

