DROP TABLE IF EXISTS T_PT_FILE_MNG;

/*==============================================================*/
/* Table: T_PT_FILE_MNG                                         */
/*==============================================================*/
CREATE TABLE T_PT_FILE_MNG
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '文件 管理 ID',
   OBJECT_TYPE_CODE     VARCHAR(32) NOT NULL COMMENT '对象 类型 代码',
   OBJECT_ID            BIGINT NOT NULL COMMENT '对象 ID',
   FILE_TYPE_CODE       VARCHAR(32) NOT NULL COMMENT '文件 类型 代码',
   CUSTOM_TYPE_CODE     VARCHAR(32) COMMENT '自定义 类型 代码',
   SYS_FILE_LIB_ID      BIGINT NOT NULL COMMENT '系统 文件 库 ID',
   FILE_ID              VARCHAR(128) NOT NULL COMMENT '文件 ID',
   CREATE_DATE          TIMESTAMP COMMENT '创建日期',
   CREATE_BY            VARCHAR(32) COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '最后更新日期',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '最后更新人标识，记录用户的ID',
   VERSION              BIGINT NOT NULL COMMENT '版本管理标志',
   PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS T_PT_PICT_MNG;