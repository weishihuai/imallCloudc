
package com.imall.iportal.core.salespos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * 交班 记录实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_slps_shift_record" )
public class ShiftRecord extends BaseEntity<Long>{

	public static final String SUCCEED_WHO_ID = "succeedWhoId";
	public static final String SHOP_ID = "shopId";
	public static final String POS_MAN = "posMan";
	public static final String SUCCEED_TIME = "succeedTime";
	public static final String SHIFT_TIME = "shiftTime";
	public static final String RECEIPT_AMOUNT = "receiptAmount";
	public static final String RETURNED_PURCHASE_AMOUNT = "returnedPurchaseAmount";
	public static final String ADD_UP_AMOUNT = "addUpAmount";
	public static final String CASH_AMOUNT = "cashAmount";
	public static final String BANK_CARD_AMOUNT = "bankCardAmount";
	public static final String WECHAT_AMOUNT = "wechatAmount";
	public static final String ALIPAY_AMOUNT = "alipayAmount";
	public static final String MEDICAL_INSURANCE_AMOUNT = "medicalInsuranceAmount";
	public static final String ORDER_QUANTITY = "orderQuantity";
	public static final String SUCCEED_READY_AMOUNT = "succeedReadyAmount";
	public static final String KEEP_READY_AMOUNT = "keepReadyAmount";
	public static final String HANDOVER_CASH_AMOUNT = "handoverCashAmount";
	public static final String IS_HAS_SHIFT = "isHasShift";

	/** SUCCEED_WHO_ID - 接谁的班 */
	@Column(name = "SUCCEED_WHO_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	private java.lang.Long succeedWhoId;
	/** SHOP_ID - 门店 ID */
	@Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long shopId;
	/** POS_MAN - 收款 员 ID */
	@Column(name = "POS_MAN", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long posMan;
	/** SUCCEED_TIME - 接班 时间 */
	@Column(name = "SUCCEED_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	private java.util.Date succeedTime;
	/** SHIFT_TIME - 交班 时间 */
	@Column(name = "SHIFT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date shiftTime;
	/** RECEIPT_AMOUNT - 收款 金额 */
	@Column(name = "RECEIPT_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Double receiptAmount;
	/** RETURNED_PURCHASE_AMOUNT - 退货 金额 */
	@Column(name = "RETURNED_PURCHASE_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Double returnedPurchaseAmount;
	/** ADD_UP_AMOUNT - 合计 金额 */
	@Column(name = "ADD_UP_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Double addUpAmount;
	/** CASH_AMOUNT - 现金 金额 */
	@Column(name = "CASH_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Double cashAmount;
	/** BANK_CARD_AMOUNT - 银行 卡 金额 */
	@Column(name = "BANK_CARD_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Double bankCardAmount;
	/** WECHAT_AMOUNT - 微信 金额 */
	@Column(name = "WECHAT_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Double wechatAmount;
	/** ALIPAY_AMOUNT - 支付宝 金额 */
	@Column(name = "ALIPAY_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Double alipayAmount;
	/** MEDICAL_INSURANCE_AMOUNT - 医保 金额 */
	@Column(name = "MEDICAL_INSURANCE_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Double medicalInsuranceAmount;
	/** ORDER_QUANTITY - 订单 数量 */
	@Column(name = "ORDER_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	private java.lang.Long orderQuantity;
	/** SUCCEED_READY_AMOUNT - 接班时 备用 金额 */
	@Column(name = "SUCCEED_READY_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Double succeedReadyAmount;
	/** KEEP_READY_AMOUNT - 留用 备用 金额 */
	@Column(name = "KEEP_READY_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Double keepReadyAmount;
	/** HANDOVER_CASH_AMOUNT - 交接 现金 金额 */
	@Column(name = "HANDOVER_CASH_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Double handoverCashAmount;
	/** IS_HAS_SHIFT - 是否 已 交班 */
	@Column(name = "IS_HAS_SHIFT", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private java.lang.String isHasShift;

	public void setSucceedWhoId(java.lang.Long value) {
		this.succeedWhoId = value;
	}

	public java.lang.Long getSucceedWhoId() {
		return this.succeedWhoId;
	}

	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

	public java.lang.Long getShopId() {
		return this.shopId;
	}

	public void setPosMan(java.lang.Long value) {
		this.posMan = value;
	}

	public java.lang.Long getPosMan() {
		return this.posMan;
	}

	public void setSucceedTime(java.util.Date value) {
		this.succeedTime = value;
	}

	public java.util.Date getSucceedTime() {
		return this.succeedTime;
	}

	public void setShiftTime(java.util.Date value) {
		this.shiftTime = value;
	}

	public java.util.Date getShiftTime() {
		return this.shiftTime;
	}

	public void setReceiptAmount(java.lang.Double value) {
		this.receiptAmount = value;
	}

	public java.lang.Double getReceiptAmount() {
		return this.receiptAmount;
	}

	public void setReturnedPurchaseAmount(java.lang.Double value) {
		this.returnedPurchaseAmount = value;
	}

	public java.lang.Double getReturnedPurchaseAmount() {
		return this.returnedPurchaseAmount;
	}

	public void setAddUpAmount(java.lang.Double value) {
		this.addUpAmount = value;
	}

	public java.lang.Double getAddUpAmount() {
		return this.addUpAmount;
	}

	public void setCashAmount(java.lang.Double value) {
		this.cashAmount = value;
	}

	public java.lang.Double getCashAmount() {
		return this.cashAmount;
	}

	public void setBankCardAmount(java.lang.Double value) {
		this.bankCardAmount = value;
	}

	public java.lang.Double getBankCardAmount() {
		return this.bankCardAmount;
	}

	public void setWechatAmount(java.lang.Double value) {
		this.wechatAmount = value;
	}

	public java.lang.Double getWechatAmount() {
		return this.wechatAmount;
	}

	public void setAlipayAmount(java.lang.Double value) {
		this.alipayAmount = value;
	}

	public java.lang.Double getAlipayAmount() {
		return this.alipayAmount;
	}

	public void setMedicalInsuranceAmount(java.lang.Double value) {
		this.medicalInsuranceAmount = value;
	}

	public java.lang.Double getMedicalInsuranceAmount() {
		return this.medicalInsuranceAmount;
	}

	public void setOrderQuantity(java.lang.Long value) {
		this.orderQuantity = value;
	}

	public java.lang.Long getOrderQuantity() {
		return this.orderQuantity;
	}

	public void setSucceedReadyAmount(java.lang.Double value) {
		this.succeedReadyAmount = value;
	}

	public java.lang.Double getSucceedReadyAmount() {
		return this.succeedReadyAmount;
	}

	public void setKeepReadyAmount(java.lang.Double value) {
		this.keepReadyAmount = value;
	}

	public java.lang.Double getKeepReadyAmount() {
		return this.keepReadyAmount;
	}

	public void setHandoverCashAmount(java.lang.Double value) {
		this.handoverCashAmount = value;
	}

	public java.lang.Double getHandoverCashAmount() {
		return this.handoverCashAmount;
	}

	public void setIsHasShift(java.lang.String value) {
		this.isHasShift = value;
	}

	public java.lang.String getIsHasShift() {
		return this.isHasShift;
	}

	public String getSucceedTimeString() {
		return DateTimeUtils.convertDateTimeToString(getSucceedTime());
	}
	public void setSucceedTimeString(String value) throws ParseException {
		setSucceedTime(DateTimeUtils.convertStringToDateTime(value));
	}

	public String getShiftTimeString() {
		return DateTimeUtils.convertDateTimeToString(getShiftTime());
	}
	public void setShiftTimeString(String value) throws ParseException {
		setShiftTime(DateTimeUtils.convertStringToDateTime(value));
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("ID",getId())
				.append("SUCCEED_WHO_ID",getSucceedWhoId())
				.append("SHOP_ID",getShopId())
				.append("POS_MAN",getPosMan())
				.append("SUCCEED_TIME",getSucceedTime())
				.append("SHIFT_TIME",getShiftTime())
				.append("RECEIPT_AMOUNT",getReceiptAmount())
				.append("RETURNED_PURCHASE_AMOUNT",getReturnedPurchaseAmount())
				.append("ADD_UP_AMOUNT",getAddUpAmount())
				.append("CASH_AMOUNT",getCashAmount())
				.append("BANK_CARD_AMOUNT",getBankCardAmount())
				.append("WECHAT_AMOUNT",getWechatAmount())
				.append("ALIPAY_AMOUNT",getAlipayAmount())
				.append("MEDICAL_INSURANCE_AMOUNT",getMedicalInsuranceAmount())
				.append("ORDER_QUANTITY",getOrderQuantity())
				.append("SUCCEED_READY_AMOUNT",getSucceedReadyAmount())
				.append("KEEP_READY_AMOUNT",getKeepReadyAmount())
				.append("HANDOVER_CASH_AMOUNT",getHandoverCashAmount())
				.append("IS_HAS_SHIFT",getIsHasShift())
				.append("CREATE_DATE",getCreateDate())
				.append("CREATE_BY",getCreateBy())
				.append("LAST_MODIFIED_DATE",getLastModifiedDate())
				.append("LAST_MODIFIED_BY",getLastModifiedBy())
				.append("VERSION",getVersion())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
				.append(getId())
				.toHashCode();
	}

	public boolean equals(Object obj) {
		if(this.getId() == null){
			return false;
		}
		if(!(obj instanceof ShiftRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ShiftRecord other = (ShiftRecord)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}

