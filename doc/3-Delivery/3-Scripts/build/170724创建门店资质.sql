/*==============================================================*/
/* Table: SHOP_CERTIFICATES_FILE                                */
/*==============================================================*/
CREATE TABLE  T_PTFM_SHOP_CERTIFICATES_FILE
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '资质 文件 ID',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   CERTIFICATES_TYPE    VARCHAR(32) NOT NULL COMMENT '资质 类型',
   CERTIFICATES_NUM     VARCHAR(32) NOT NULL COMMENT '资质 编号',
   CERTIFICATES_VALIDITY TIMESTAMP NOT NULL COMMENT '资质 有效期',
   CREATE_DATE          TIMESTAMP NOT NULL COMMENT '创建时间',
   CREATE_BY            VARCHAR(32) NOT NULL COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '更新时间',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '更新用户',
   VERSION              BIGINT NOT NULL COMMENT '版本',
   PRIMARY KEY (ID)
);
