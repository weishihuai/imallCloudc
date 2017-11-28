/*==============================================================*/
/* Table: GOODS                                                 */
/*==============================================================*/
create table t_shp_GOODS
(
   ID                   bigint not null auto_increment comment '主键ID',
   GOODS_DOC_ID         bigint not null comment '商品 档案 ID',
   GOODS_CATEGORY_ID    bigint not null comment '商品 分类 ID',
   GOODS_CODE           varchar(128) not null comment '商品 编码',
   GOODS_NM             varchar(128) not null comment '商品 名称',
   SELL_CATEGORY_IDS    varchar(1024) not null comment '销售 分类 IDS',
   BRAND_ID             bigint comment '品牌 ID',
   GOODS_TYPE_CODE      varchar(32) not null comment '商品 类型 代码',
   COMMON_NM            varchar(64) not null comment '通用 名称',
   SPEC                 varchar(32) not null comment '规格',
   UNIT                 varchar(32) not null comment '单位',
   TOXICOLOGY_CODE      varchar(32) not null comment '毒理 代码',
   STORAGE_CONDITION    varchar(32) not null comment '储存 条件 代码',
   PRODUCE_MANUFACTURER varchar(64) not null comment '生产 厂商',
   INSTRUCTIONS         long binary comment '说明书',
   MEDICATION_GUIDE     long binary comment '用药 指导',
   SHOP_ID              bigint not null comment '门店 ID',
   IS_DELETE            char(1) not null comment '是否 删除',
   DISPLAY_POSITION     bigint not null comment '排序',
   APPROVE_STAT_CODE    varchar(32) not null comment '审核 状态 代码',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);

/*==============================================================*/
/* Table: GOODS_DRUG                                            */
/*==============================================================*/
create table t_shp_GOODS_DRUG
(
   ID                   bigint not null auto_increment comment '主键ID',
   GOODS_ID             bigint not null comment '商品 ID',
   APPROVAL_NUMBER      varchar(64) not null comment '批准文号',
   APPROVAL_NUMBER_TERM timestamp not null comment '批准文号 期限',
   IS_IMPORT_GOODS      char(1) not null comment '是否 进口 商品',
   IS_CHINESE_MEDICINE_PROTECT char(1) not null comment '是否 中药 保护',
   APPROVE_DATE         timestamp comment '批准 日期',
   DOSAGE_FORM          varchar(64) not null comment '剂型',
   PRESCRIPTION_DRUGS_TYPE_CODE varchar(32) not null comment '处方药 类型 代码',
   IS_EPHEDRINE         char(1) not null comment '是否 麻黄碱',
   IS_KEY_CURING        char(1) not null comment '是否 重点 养护',
   IS_MEDICAL_INSURANCE_GOODS char(1) not null comment '是否 医保 商品',
   MEDICAL_INSURANCE_NUM varchar(32) comment '医保 号码',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);

/*==============================================================*/
/* Table: GOODS_CHINESE_MEDICINE_PIECES                         */
/*==============================================================*/
create table GOODS_CHINESE_MEDICINE_PIECES
(
   ID                   bigint not null auto_increment comment '主键ID',
   GOODS_ID             bigint not null comment '商品 ID',
   APPROVAL_NUMBER      varchar(64) not null comment '批准文号',
   APPROVAL_NUMBER_TERM timestamp not null comment '批准文号 期限',
   IS_IMPORT_GOODS      char(1) not null comment '是否 进口 商品',
   IS_CHINESE_MEDICINE_PROTECT char(1) not null comment '是否 中药 保护',
   APPROVE_DATE         timestamp comment '批准 日期',
   DOSAGE_FORM          varchar(64) not null comment '剂型',
   PRODUCTION_PLACE     varchar(32) not null comment '产地',
   EFFECT               varchar(512) comment '功效',
   PRESCRIPTION_DRUGS_TYPE_CODE varchar(32) not null comment '处方药 类型 代码',
   IS_EPHEDRINE         char(1) not null comment '是否 麻黄碱',
   IS_KEY_CURING        char(1) not null comment '是否 重点 养护',
   IS_MEDICAL_INSURANCE_GOODS char(1) not null comment '是否 医保 商品',
   MEDICAL_INSURANCE_NUM varchar(32) comment '医保 号码',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);


/*==============================================================*/
/* Table: GOODS_MEDICAL_INSTRUMENTS                             */
/*==============================================================*/
create table t_shp_GOODS_MEDICAL_INSTRUMENTS
(
   ID                   bigint not null auto_increment comment '主键ID',
   GOODS_ID             bigint not null comment '商品 ID',
   REG_NUM              varchar(64) not null comment '注册 号码',
   REG_REGISTRATION_FORM_NUM varchar(64) not null comment '注册 登记表 号码',
   MANUFACTURER_ADDR    varchar(32) not null comment '厂家 地址',
   APPLY_RANGE          varchar(512) comment '适用 范围',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   PRODUCT_STANDARD_NUM varchar(32) comment '产品 标准 号码',
   primary key (ID)
);


/*==============================================================*/
/* Table: GOODS_COSMETIC                                        */
/*==============================================================*/
create table t_shp_GOODS_COSMETIC
(
   ID                   bigint not null auto_increment comment '主键ID',
   GOODS_ID             bigint not null comment '商品 ID',
   APPROVAL_NUMBER      varchar(64) not null comment '批准文号',
   MANUFACTURER_ADDR    varchar(128) comment '厂家 地址',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);


/*==============================================================*/
/* Table: GOODS_DAILY_NECESSITIES                               */
/*==============================================================*/
create table t_shp_GOODS_DAILY_NECESSITIES
(
   ID                   bigint not null auto_increment comment '主键ID',
   GOODS_ID             bigint not null comment '商品 ID',
   APPROVAL_NUMBER      varchar(64) not null comment '批准文号',
   MANUFACTURER_ADDR    varchar(128) comment '厂家 地址',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);



/*==============================================================*/
/* Table: GOODS_FOOD_HEALTH                                     */
/*==============================================================*/
create table t_shp_GOODS_FOOD_HEALTH
(
   ID                   bigint not null auto_increment comment '主键ID',
   GOODS_ID             bigint not null comment '商品 ID',
   FOOD_HYGIENE_LICENCE_NUM varchar(64) not null comment '食品 卫生 许可证 号码',
   PRODUCTION_DATE      varchar(128) not null comment '生产 日期',
   EXPIRATION_DATE      timestamp not null comment '保质期',
   HEALT_CARE_FUNC      varchar(128) not null comment '保健 功能',
   APPROPRIATE_CROWD    varchar(64) comment '适宜 人群',
   EDIBL_METHOD_AND_DOSAGE varchar(64) comment '食用 方法 及 用量',
   STORAGE_METHOD       varchar(64) comment '贮藏 方法',
   EXEC_STANDARD        varchar(64) comment '执行 标准',
   EFFECT_COMPOSITION   varchar(64) comment '功效 成分',
   NOTICE               varchar(512) comment '注意 事项',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);



/*==============================================================*/
/* Table: GOODS_OTHER                                           */
/*==============================================================*/
create table t_shp_GOODS_OTHER
(
   ID                   bigint not null auto_increment comment '主键ID',
   GOODS_ID             bigint not null comment '商品 ID',
   APPROVAL_NUMBER      varchar(64) not null comment '批准文号',
   MANUFACTURER_ADDR    varchar(128) comment '厂家 地址',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);


/*==============================================================*/
/* Table: SKU                                                   */
/*==============================================================*/
CREATE TABLE t_shp_SKU
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   GOODS_ID             BIGINT NOT NULL COMMENT '商品 ID',
   RETAIL_PRICE         DOUBLE NOT NULL COMMENT '零售 价格',
   MEMBER_PRICE         DOUBLE NOT NULL COMMENT '会员 价格',
   MARKET_PRICE         DOUBLE NOT NULL COMMENT '市场 价格',
   COST_PRICE           DOUBLE NOT NULL COMMENT '成本 价格',
   CURRENT_STOCK        BIGINT NOT NULL COMMENT '当前 库存',
   SAFETY_STOCK         BIGINT NOT NULL COMMENT '安全 库存',
   PURCHASE_TAX_RATE    DOUBLE NOT NULL COMMENT '采购 税率',
   SELL_TAX_RATE        DOUBLE COMMENT '销售 税率',
   IS_special_price_GOODS CHAR(1) NOT NULL COMMENT '是否 特价 商品',
   IS_SPLIT_ZERO        CHAR(1) COMMENT '是否 拆零',
   SPLIT_ZERO_UNIT      VARCHAR(32) COMMENT '拆零 单位',
   SPLIT_ZERO_QUANTITY  BIGINT COMMENT '拆零 数量',
   SPLIT_ZERO_SPEC      VARCHAR(32) COMMENT '拆零 规格',
   CREATE_DATE          TIMESTAMP COMMENT '创建日期',
   CREATE_BY            VARCHAR(32) COMMENT '创建人',
   last_modified_date   TIMESTAMP COMMENT '最后更新日期',
   last_modified_by     VARCHAR(32) COMMENT '最后更新人标识，记录用户的ID',
   VERSION              BIGINT NOT NULL COMMENT '版本管理标志',
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: GOODS_BATCH                                           */
/*==============================================================*/
CREATE TABLE t_shp_GOODS_BATCH
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   GOODS_ID             BIGINT NOT NULL COMMENT '商品 ID',
   BATCH                VARCHAR(64) NOT NULL COMMENT '批号',
   PRODUCE_DATE         TIMESTAMP NOT NULL COMMENT '生产 日期',
   VALID_DATE           TIMESTAMP NOT NULL COMMENT '有效 日期',
   CURRENT_STOCK        BIGINT NOT NULL COMMENT '当前 库存',
   SECURITY_STOCK       BIGINT NOT NULL COMMENT '安全 库存',
   STORAGE_SPACE_ID     BIGINT NOT NULL COMMENT '货位_ID',
   SUPPLIER_ID          bigint not null comment '供应商 ID',
   APPROVE_MAN_ID       BIGINT COMMENT '审核 人 ID',
   CREATE_DATE          TIMESTAMP COMMENT '创建日期',
   CREATE_BY            VARCHAR(32) COMMENT '创建人',
   last_modified_date   TIMESTAMP COMMENT '最后更新日期',
   last_modified_by     VARCHAR(32) COMMENT '最后更新人标识，记录用户的ID',
   VERSION              BIGINT NOT NULL COMMENT '版本管理标志',
   PRIMARY KEY (ID)
);

create table t_shp_STORAGE_SPACE
(
   ID                   bigint not null auto_increment comment '主键ID',
   STORAGE_SPACE_NM     varchar(32) not null comment '货位 名称',
   STORAGE_SPACE_TYPE   varchar(32) not null comment '货位 类型',
   ENABLE_STAT_CODE     char(1) not null comment '启用 状态 代码',
   SHOP_ID              bigint not null comment '门店 ID',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);

/*==============================================================*/
/* Table: GOODS_BATCH_MOD_RECORD   商品批号修改记录             */
/*==============================================================*/
create table T_SHP_GOODS_BATCH_MOD_RECORD
(
   ID                   bigint not null auto_increment comment '主键ID',
   GOODS_BATCH_ID       bigint not null comment '商品 批次 ID',
   NEW_BATCH            varchar(32) not null comment '新 批号',
   OLD_BATCH            varchar(32) not null comment '旧 批号',
   NEW_PRODUCE_DATE     timestamp not null comment '新 生产 日期',
   OLD_PRODUCE_DATE     timestamp not null comment '旧 生产 日期',
   NEW_VALID_DATE       timestamp not null comment '新 有效 日期',
   OLD_VALID_DATE       timestamp not null comment '旧 有效 日期',
   APPROVE_MAN_ID       bigint comment '审核 人 ID',
   CREATE_DATE          timestamp comment '创建日期',
   CREATE_BY            varchar(32) comment '创建人',
   last_modified_date   timestamp comment '最后更新日期',
   last_modified_by     varchar(32) comment '最后更新人标识，记录用户的ID',
   VERSION              bigint not null comment '版本管理标志',
   primary key (ID)
);


