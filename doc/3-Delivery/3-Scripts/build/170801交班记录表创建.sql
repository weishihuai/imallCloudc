CREATE TABLE t_slps_shift_record
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
   SUCCEED_WHO_ID       BIGINT COMMENT '接谁的班',
   SHOP_ID              BIGINT NOT NULL COMMENT '门店 ID',
   POS_MAN              BIGINT NOT NULL COMMENT '收款 员 ID',
   SUCCEED_TIME         TIMESTAMP NOT NULL COMMENT '接班 时间',
   SHIFT_TIME           TIMESTAMP NULL COMMENT '交班 时间',
   RECEIPT_AMOUNT       DOUBLE NOT NULL COMMENT '收款 金额',
   RETURNED_PURCHASE_AMOUNT DOUBLE NOT NULL COMMENT '退货 金额',
   ADD_UP_AMOUNT        DOUBLE NOT NULL COMMENT '合计 金额',
   CASH_AMOUNT          DOUBLE NOT NULL COMMENT '现金 金额',
   BANK_CARD_AMOUNT     DOUBLE NOT NULL COMMENT '银行 卡 金额',
   WECHAT_AMOUNT        DOUBLE NOT NULL COMMENT '微信 金额',
   ALIPAY_AMOUNT        DOUBLE NOT NULL COMMENT '支付宝 金额',
   MEDICAL_INSURANCE_AMOUNT DOUBLE NOT NULL COMMENT '医保 金额',
   ORDER_QUANTITY       BIGINT NOT NULL COMMENT '订单 数量',
   SUCCEED_READY_AMOUNT DOUBLE NOT NULL COMMENT '接班时 备用 金额',
   KEEP_READY_AMOUNT    DOUBLE NOT NULL COMMENT '留用 备用 金额',
   HANDOVER_CASH_AMOUNT DOUBLE NOT NULL COMMENT '交接 现金 金额',
   IS_HAS_SHIFT         CHAR(1) NOT NULL COMMENT '是否 已 交班',
   CREATE_DATE          TIMESTAMP NOT NULL COMMENT '创建时间',
   CREATE_BY            VARCHAR(32) NOT NULL COMMENT '创建人',
   LAST_MODIFIED_DATE   TIMESTAMP COMMENT '更新时间',
   LAST_MODIFIED_BY     VARCHAR(32) COMMENT '更新用户',
   VERSION              BIGINT NOT NULL COMMENT '版本',
   PRIMARY KEY (ID)
);
alter table t_slps_shift_record comment '交班 记录';
