ALTER TABLE `t_shp_goods_batch` DROP COLUMN CURRENT_STOCK;
ALTER TABLE `t_shp_goods_batch` DROP COLUMN SECURITY_STOCK;
ALTER TABLE `t_shp_goods_batch` DROP COLUMN APPROVE_MAN_ID;
ALTER TABLE `t_shp_goods_batch` DROP COLUMN SUPPLIER_ID;
ALTER TABLE `t_shp_goods_batch` DROP COLUMN PURCHASE_PRICE;

DROP TABLE IF EXISTS T_SHP_GOODS_BATCH_SKU;

/*==============================================================*/
/* Table: T_SHP_GOODS_BATCH_SKU                                 */
/*==============================================================*/
CREATE TABLE T_SHP_GOODS_BATCH_SKU
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品 批次 SKU ID',
   GOODS_BATCH_ID       BIGINT NOT NULL COMMENT '商品 批次 ID',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   GOODS_ID             BIGINT NOT NULL COMMENT '商品 ID',
   SKU_ID               BIGINT NOT NULL COMMENT 'SKU ID',
   SUPPLIER_ID          BIGINT NOT NULL COMMENT '供应商 ID',
   APPROVE_MAN_ID       BIGINT NOT NULL COMMENT '审核 人 ID',
   PURCHASE_PRICE       DOUBLE NOT NULL COMMENT '采购 价格',
   CURRENT_STOCK        BIGINT NOT NULL COMMENT '当前 库存',
   CREATE_DATE          TIMESTAMP COMMENT '创建日期',
   CREATE_BY            VARCHAR(32) COMMENT '创建人',
   last_modified_date   TIMESTAMP COMMENT '最后更新日期',
   last_modified_by     VARCHAR(32) COMMENT '最后更新人标识，记录用户的ID',
   VERSION              BIGINT NOT NULL COMMENT '版本管理标志',
   PRIMARY KEY (ID)
);
