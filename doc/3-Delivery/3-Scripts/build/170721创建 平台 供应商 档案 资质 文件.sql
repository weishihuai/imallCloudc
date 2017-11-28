/*==============================================================*/
/* Table: SUPPLIER_DOC_CERTIFICATES_FILE                        */
/*==============================================================*/
create table T_PTFM_SUPPLIER_DOC_CERTIFICATES_FILE
(
   ID                   bigint not null auto_increment comment '资质 档案 文件 ID',
   SUPPLIER_DOC_ID      bigint not null comment '供应商 档案 ID',
   CERTIFICATES_TYPE    varchar(32) not null comment '资质 类型',
   CERTIFICATES_NUM     varchar(32)  null comment '资质 编号',
   CERTIFICATES_VALIDITY timestamp  null comment '资质 有效期',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
