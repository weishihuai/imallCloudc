DROP TABLE IF EXISTS t_shp_first_manage_drug_quality_approve;

/*==============================================================*/
/* Table: t_shp_first_manage_drug_quality_approve                                 */
/*==============================================================*/
CREATE TABLE `t_shp_first_manage_drug_quality_approve` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SHOP_ID` bigint(20) NOT NULL COMMENT '门店 ID',
  `GOODS_ID` bigint(20) NOT NULL COMMENT '商品 ID',
  `GOODS_CODE` varchar(128) NOT NULL COMMENT '商品 编码',
  `GOODS_NM` varchar(128) NOT NULL COMMENT '商品 名称',
  `COMMON_NM_FIRST_SPELL` varchar(128) NOT NULL COMMENT '通用 名称 首拼',
  `COMMON_NM` varchar(64) NOT NULL COMMENT '通用 名称',
  `SPEC` varchar(32) NOT NULL COMMENT '规格',
  `DOSAGE_FORM` varchar(32) DEFAULT NULL COMMENT '剂型',
  `UNIT` varchar(32) NOT NULL COMMENT '单位',
  `PRODUCE_MANUFACTURER` varchar(64) NOT NULL COMMENT '生产厂商',
  `APPROVAL_NUMBER` varchar(64) DEFAULT NULL COMMENT '批准文号',
  `APPROVE_STATE_CODE` varchar(32) NOT NULL COMMENT '审核 状态 代码',
  `QUALITY_APPROVE_TIME` datetime DEFAULT NULL COMMENT '质量 审核 时间',
  `APPLY_MAN_NAME` varchar(32) NOT NULL COMMENT '申请 人 姓名',
  `SUBMIT_ADVICE` varchar(64) DEFAULT NULL COMMENT '提交 意见',
  `APPLY_REMARK` varchar(128) DEFAULT NULL COMMENT '申请 备注',
  `CREATE_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `CREATE_BY` varchar(32) NOT NULL COMMENT '创建人',
  `LAST_MODIFIED_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `LAST_MODIFIED_BY` varchar(32) DEFAULT NULL COMMENT '更新用户',
  `VERSION` bigint(20) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t_shp_first_manage_drug_quality_approve_inf;

CREATE TABLE `t_shp_first_manage_drug_quality_approve_inf` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `FIRST_MANAGE_DRUG_QUALITY_APPROVE_ID` bigint(20) NOT NULL COMMENT '首营 药品 质量 审核 ID',
  `SHOP_ID` bigint(20) NOT NULL COMMENT '门店 ID',
  `GOODS_ID` bigint(20) NOT NULL COMMENT '商品 ID',
  `APPROVE_TYPE_CODE` varchar(32) NOT NULL COMMENT '审核 类型 代码',
  `APPROVE_MAN_ID` bigint(20) NOT NULL COMMENT '审核 人 ID',
  `APPROVE_STATE_CODE` varchar(32) NOT NULL COMMENT '审核 状态 代码',
  `APPROVE_REMARK` varchar(128) DEFAULT NULL COMMENT '审核 备注',
  `APPROVE_TIME` datetime DEFAULT NULL COMMENT '审核 时间',
  `CREATE_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `CREATE_BY` varchar(32) NOT NULL COMMENT '创建人',
  `LAST_MODIFIED_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `LAST_MODIFIED_BY` varchar(32) DEFAULT NULL COMMENT '更新用户',
  `VERSION` bigint(20) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

