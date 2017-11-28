create table T_SHP_MAINTAINING_RECORD
(
   ID                   bigint not null auto_increment comment '主键',
   SHOP_ID              bigint not null comment '门店 ID',
   FACILITY_AND_DEVICE_ACCOUNTS_ID bigint not null comment '设施 设备 台账 ID',
   MAINTAIN_DATE        date not null comment '维护 日期',
   MAINTAIN_CONT        varchar(32) not null comment '维护 内容',
   MAINTAIN_RESULT      varchar(32) comment '维护 结果',
   WORK_STATE           varchar(32) comment '工作 状况',
   MAINTAIN_RESPONSE_MAN varchar(32) comment '检修 负责 人',
   APPROVER_NM          varchar(32) comment '审批 人 名称',
   REMARK               varchar(32) comment '备注',
   APPROVE_MAN_ID       bigint not null comment '审核 人 ID',
   CREATE_DATE          timestamp not null comment '创建时间',
   CREATE_BY            varchar(32) not null comment '创建人',
   LAST_MODIFIED_DATE   timestamp comment '更新时间',
   LAST_MODIFIED_BY     varchar(32) comment '更新用户',
   VERSION              bigint not null comment '版本',
   primary key (ID)
);
