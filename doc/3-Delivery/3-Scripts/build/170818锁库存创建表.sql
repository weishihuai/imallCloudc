CREATE TABLE t_shp_sku_lock_stock
(
    ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
    SKU_ID               BIGINT COMMENT 'SKU ID',
    QUANTITY             BIGINT NOT NULL COMMENT '数量',
    LOCK_TIME            TIMESTAMP NOT NULL COMMENT '上锁 时间',
    LOCK_STATE_CODE      LONGTEXT NOT NULL COMMENT '锁 状态 代码',
    MEMBER_ID            BIGINT NOT NULL COMMENT '会员 ID',
    ORDER_NUM            VARCHAR(32) NOT NULL COMMENT '订单 编号',
    ORDER_SOURCE_CODE   VARCHAR(32) NOT NULL COMMENT '订单 来源 代码',
    CREATE_DATE          TIMESTAMP COMMENT '创建日期',
    CREATE_BY            VARCHAR(32) COMMENT '创建人',
    last_modified_date   TIMESTAMP COMMENT '最后更新日期',
    last_modified_by     VARCHAR(32) COMMENT '最后更新人标识，记录用户的ID',
    VERSION              BIGINT NOT NULL COMMENT '版本管理标志',
    PRIMARY KEY (ID)
);

ALTER TABLE t_shp_sku_lock_stock COMMENT 'SKU_锁_库存';
