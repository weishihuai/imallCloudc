
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_order" )
public class Order extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String ORDER_SOURCE_CODE = "orderSourceCode";
	public static final String ORDER_NUM = "orderNum";
	public static final String GOODS_TOTAL_AMOUNT = "goodsTotalAmount";
	public static final String COST_TOTAL_AMOUNT = "costTotalAmount";
	public static final String ORDER_TOTAL_AMOUNT = "orderTotalAmount";
	public static final String GROSS_MARGIN = "grossMargin";
	public static final String OPEN_ORDER_MAN_ID = "openOrderManId";
	public static final String MEMBER_CARD_NUM = "memberCardNum";
	public static final String MEMBER_ID = "memberId";
	public static final String REMARK = "remark";
	public static final String ADMIN_REMARK = "adminRemark";
	public static final String FREIGHT_AMOUNT = "freightAmount";
	public static final String PAYMENT_WAY_CODE = "paymentWayCode";
	public static final String ORDER_STATE_CODE = "orderStateCode";
	public static final String IS_PAYED = "isPayed";
	public static final String IS_COD = "isCod";
	public static final String ORDER_CONFIRM_TIME = "orderConfirmTime";
	public static final String FINISH_TIME = "finishTime";
	public static final String MEDICAL_INSURANCE_PAYMENT_AMOUNT = "medicalInsurancePaymentAmount";
	public static final String REAL_GEVE_CASH_AMOUNT = "realGeveCashAmount";
	public static final String RETURN_SMALL = "returnSmall";
	public static final String BOOK_DELIVERY_TIME_START = "bookDeliveryTimeStart";
	public static final String BOOK_DELIVERY_TIME_END = "bookDeliveryTimeEnd";
	public static final String ORDER_CLOSE_TIME = "orderCloseTime";
	public static final String RECEIVER_NAME = "receiverName";
	public static final String CONTACT_TEL = "contactTel";
	public static final String DELIVERY_ADDR = "deliveryAddr";
	public static final String DETAIL_ADDR = "detailAddr";
	public static final String COORDINATE_X = "coordinateX";
	public static final String COORDINATE_Y = "coordinateY";
	public static final String WE_SHOP_ID = "weShopId";
	public static final String DELIVERY_TYPE_CODE = "deliveryTypeCode";
	public static final String DELIVERY_MIN_ORDER_AMOUNT = "deliveryMinOrderAmount";
	public static final String IS_HAS_RETURNED_PURCHASE = "isHasReturnedPurchase";
	public static final String WECHAT_ID = "wechatId";
	public static final String WECHAT_USER_ID = "wechatUserId";
	public static final String CANCEL_ORDER_REASON = "cancelOrderReason";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** ORDER_SOURCE_CODE - 订单 来源 代码 */
    @Column(name = "ORDER_SOURCE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String orderSourceCode;
    /** ORDER_NUM - 订单 编号 */
    @Column(name = "ORDER_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String orderNum;
    /** GOODS_TOTAL_AMOUNT - 商品 总 金额 */
    @Column(name = "GOODS_TOTAL_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double goodsTotalAmount;
    /** COST_TOTAL_AMOUNT - 成本 总 金额 */
    @Column(name = "COST_TOTAL_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double costTotalAmount;
    /** ORDER_TOTAL_AMOUNT - 订单 总 金额 */
    @Column(name = "ORDER_TOTAL_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double orderTotalAmount;
    /** GROSS_MARGIN - 毛利 */
    @Column(name = "GROSS_MARGIN", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double grossMargin;
    /** OPEN_ORDER_MAN_ID - 开单 人 ID */
    @Column(name = "OPEN_ORDER_MAN_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long openOrderManId;
    /** MEMBER_CARD_NUM - 会员 卡 号码 */
    @Column(name = "MEMBER_CARD_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String memberCardNum;
    /** MEMBER_ID - 会员 ID */
    @Column(name = "MEMBER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long memberId;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
    private String remark;
	/** ADMIN_REMARK - 管理员 备注 */
	@Column(name = "ADMIN_REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	private String adminRemark;
    /** FREIGHT_AMOUNT - 运费 */
    @Column(name = "FREIGHT_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double freightAmount;
    /** PAYMENT_WAY_CODE - 支付 方式 代码 */
    @Column(name = "PAYMENT_WAY_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String paymentWayCode;
    /** ORDER_STATE_CODE - 订单 状态 代码 */
    @Column(name = "ORDER_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String orderStateCode;
    /** IS_PAYED - 是否 已支付 */
    @Column(name = "IS_PAYED", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String isPayed;
    /** IS_COD - 是否 货到 付款 */
    @Column(name = "IS_COD", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String isCod;
    /** ORDER_CONFIRM_TIME - 订单 确认 时间 */
    @Column(name = "ORDER_CONFIRM_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date orderConfirmTime;
    /** FINISH_TIME - 订单 完成 时间 */
    @Column(name = "FINISH_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date finishTime;
    /** MEDICAL_INSURANCE_PAYMENT_AMOUNT - 医保 支付 金额 */
    @Column(name = "MEDICAL_INSURANCE_PAYMENT_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double medicalInsurancePaymentAmount;
    /** REAL_GEVE_CASH_AMOUNT - 实收 现金 金额 */
    @Column(name = "REAL_GEVE_CASH_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double realGeveCashAmount;
    /** RETURN_SMALL - 找零 */
    @Column(name = "RETURN_SMALL", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double returnSmall;
    /** BOOK_DELIVERY_TIME_START - 预约 送达 时间 开始 */
    @Column(name = "BOOK_DELIVERY_TIME_START", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date bookDeliveryTimeStart;
    /** BOOK_DELIVERY_TIME_END - 预约 送达 时间 结束 */
    @Column(name = "BOOK_DELIVERY_TIME_END", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date bookDeliveryTimeEnd;
    /** ORDER_CLOSE_TIME - 订单 关闭 时间 */
    @Column(name = "ORDER_CLOSE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date orderCloseTime;
    /** RECEIVER_NAME - 收货人 姓名 */
    @Column(name = "RECEIVER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String receiverName;
    /** CONTACT_TEL - 联系 电话 */
    @Column(name = "CONTACT_TEL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String contactTel;
    /** DELIVERY_ADDR - 配送 地址 */
    @Column(name = "DELIVERY_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String deliveryAddr;
    /** DETAIL_ADDR - 详细 地址 */
    @Column(name = "DETAIL_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String detailAddr;
    /** COORDINATE_X - 坐标 X */
    @Column(name = "COORDINATE_X", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double coordinateX;
    /** COORDINATE_Y - 坐标 Y */
    @Column(name = "COORDINATE_Y", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double coordinateY;
    /** WE_SHOP_ID - 微门店ID */
    @Column(name = "WE_SHOP_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long weShopId;
    /** DELIVERY_TYPE_CODE - 配送 类型 代码 */
    @Column(name = "DELIVERY_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String deliveryTypeCode;
    /** DELIVERY_MIN_ORDER_AMOUNT - 配送 最小 订单 金额（满额必送） */
    @Column(name = "DELIVERY_MIN_ORDER_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double deliveryMinOrderAmount;
    /** IS_HAS_RETURNED_PURCHASE - 是否已退完货 */
    @Column(name = "IS_HAS_RETURNED_PURCHASE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isHasReturnedPurchase;
    /** WECHAT_ID - 微信 ID/微信openID */
    @Column(name = "WECHAT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String wechatId;
    /** WECHAT_USER_ID - 微信 用户 ID */
    @Column(name = "WECHAT_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long wechatUserId;
    /** CANCEL_ORDER_REASON - 取消 订单 原因 */
    @Column(name = "CANCEL_ORDER_REASON", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String cancelOrderReason;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setOrderSourceCode(String value) {
		this.orderSourceCode = value;
	}

    public String getOrderSourceCode() {
		return this.orderSourceCode;
	}

    public void setOrderNum(String value) {
		this.orderNum = value;
	}

    public String getOrderNum() {
		return this.orderNum;
	}

    public void setGoodsTotalAmount(Double value) {
		this.goodsTotalAmount = value;
	}

    public Double getGoodsTotalAmount() {
		return this.goodsTotalAmount;
	}

    public void setCostTotalAmount(Double value) {
		this.costTotalAmount = value;
	}

    public Double getCostTotalAmount() {
		return this.costTotalAmount;
	}

    public void setOrderTotalAmount(Double value) {
		this.orderTotalAmount = value;
	}

    public Double getOrderTotalAmount() {
		return this.orderTotalAmount;
	}

    public void setGrossMargin(Double value) {
		this.grossMargin = value;
	}

    public Double getGrossMargin() {
		return this.grossMargin;
	}

    public void setOpenOrderManId(Long value) {
		this.openOrderManId = value;
	}

    public Long getOpenOrderManId() {
		return this.openOrderManId;
	}

    public void setMemberCardNum(String value) {
		this.memberCardNum = value;
	}

    public String getMemberCardNum() {
		return this.memberCardNum;
	}

    public void setMemberId(Long value) {
		this.memberId = value;
	}

    public Long getMemberId() {
		return this.memberId;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

	public String getAdminRemark() {
		return adminRemark;
	}

	public void setAdminRemark(String adminRemark) {
		this.adminRemark = adminRemark;
	}

	public void setFreightAmount(Double value) {
		this.freightAmount = value;
	}

    public Double getFreightAmount() {
		return this.freightAmount;
	}

    public void setPaymentWayCode(String value) {
		this.paymentWayCode = value;
	}

    public String getPaymentWayCode() {
		return this.paymentWayCode;
	}

    public void setOrderStateCode(String value) {
		this.orderStateCode = value;
	}

    public String getOrderStateCode() {
		return this.orderStateCode;
	}

    public void setIsPayed(String value) {
		this.isPayed = value;
	}

    public String getIsPayed() {
		return this.isPayed;
	}

    public void setIsCod(String value) {
		this.isCod = value;
	}

    public String getIsCod() {
		return this.isCod;
	}

    public void setOrderConfirmTime(java.util.Date value) {
		this.orderConfirmTime = value;
	}

    public java.util.Date getOrderConfirmTime() {
		return this.orderConfirmTime;
	}

    public void setFinishTime(java.util.Date value) {
		this.finishTime = value;
	}

    public java.util.Date getFinishTime() {
		return this.finishTime;
	}

    public void setMedicalInsurancePaymentAmount(Double value) {
		this.medicalInsurancePaymentAmount = value;
	}

    public Double getMedicalInsurancePaymentAmount() {
		return this.medicalInsurancePaymentAmount;
	}

    public void setRealGeveCashAmount(Double value) {
		this.realGeveCashAmount = value;
	}

    public Double getRealGeveCashAmount() {
		return this.realGeveCashAmount;
	}

    public void setReturnSmall(Double value) {
		this.returnSmall = value;
	}

    public Double getReturnSmall() {
		return this.returnSmall;
	}

    public void setBookDeliveryTimeStart(java.util.Date value) {
		this.bookDeliveryTimeStart = value;
	}

    public java.util.Date getBookDeliveryTimeStart() {
		return this.bookDeliveryTimeStart;
	}

    public void setBookDeliveryTimeEnd(java.util.Date value) {
		this.bookDeliveryTimeEnd = value;
	}

    public java.util.Date getBookDeliveryTimeEnd() {
		return this.bookDeliveryTimeEnd;
	}

    public void setOrderCloseTime(java.util.Date value) {
		this.orderCloseTime = value;
	}

    public java.util.Date getOrderCloseTime() {
		return this.orderCloseTime;
	}

    public void setReceiverName(String value) {
		this.receiverName = value;
	}

    public String getReceiverName() {
		return this.receiverName;
	}

    public void setContactTel(String value) {
		this.contactTel = value;
	}

    public String getContactTel() {
		return this.contactTel;
	}

    public void setDeliveryAddr(String value) {
		this.deliveryAddr = value;
	}

    public String getDeliveryAddr() {
		return this.deliveryAddr;
	}

    public void setDetailAddr(String value) {
		this.detailAddr = value;
	}

    public String getDetailAddr() {
		return this.detailAddr;
	}

    public void setCoordinateX(Double value) {
		this.coordinateX = value;
	}

    public Double getCoordinateX() {
		return this.coordinateX;
	}

    public void setCoordinateY(Double value) {
		this.coordinateY = value;
	}

    public Double getCoordinateY() {
		return this.coordinateY;
	}

    public void setWeShopId(Long value) {
		this.weShopId = value;
	}

    public Long getWeShopId() {
		return this.weShopId;
	}

    public void setDeliveryTypeCode(String value) {
		this.deliveryTypeCode = value;
	}

    public String getDeliveryTypeCode() {
		return this.deliveryTypeCode;
	}

    public void setDeliveryMinOrderAmount(Double value) {
		this.deliveryMinOrderAmount = value;
	}

    public Double getDeliveryMinOrderAmount() {
		return this.deliveryMinOrderAmount;
	}

    public void setIsHasReturnedPurchase(String value) {
		this.isHasReturnedPurchase = value;
	}

    public String getIsHasReturnedPurchase() {
		return this.isHasReturnedPurchase;
	}

    public void setWechatId(String value) {
		this.wechatId = value;
	}

    public String getWechatId() {
		return this.wechatId;
	}

    public void setWechatUserId(Long value) {
		this.wechatUserId = value;
	}

    public Long getWechatUserId() {
		return this.wechatUserId;
	}

    public void setCancelOrderReason(String value) {
		this.cancelOrderReason = value;
	}

    public String getCancelOrderReason() {
		return this.cancelOrderReason;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("ORDER_SOURCE_CODE",getOrderSourceCode())
			.append("ORDER_NUM",getOrderNum())
			.append("GOODS_TOTAL_AMOUNT",getGoodsTotalAmount())
			.append("COST_TOTAL_AMOUNT",getCostTotalAmount())
			.append("ORDER_TOTAL_AMOUNT",getOrderTotalAmount())
			.append("GROSS_MARGIN",getGrossMargin())
			.append("OPEN_ORDER_MAN_ID",getOpenOrderManId())
			.append("MEMBER_CARD_NUM",getMemberCardNum())
			.append("MEMBER_ID",getMemberId())
			.append("REMARK",getRemark())
			.append("ADMIN_REMARK",getAdminRemark())
			.append("FREIGHT_AMOUNT",getFreightAmount())
			.append("PAYMENT_WAY_CODE",getPaymentWayCode())
			.append("ORDER_STATE_CODE",getOrderStateCode())
			.append("IS_PAYED",getIsPayed())
			.append("IS_COD",getIsCod())
			.append("ORDER_CONFIRM_TIME",getOrderConfirmTime())
			.append("FINISH_TIME",getFinishTime())
			.append("MEDICAL_INSURANCE_PAYMENT_AMOUNT",getMedicalInsurancePaymentAmount())
			.append("REAL_GEVE_CASH_AMOUNT",getRealGeveCashAmount())
			.append("RETURN_SMALL",getReturnSmall())
			.append("BOOK_DELIVERY_TIME_START",getBookDeliveryTimeStart())
			.append("BOOK_DELIVERY_TIME_END",getBookDeliveryTimeEnd())
			.append("ORDER_CLOSE_TIME",getOrderCloseTime())
			.append("RECEIVER_NAME",getReceiverName())
			.append("CONTACT_TEL",getContactTel())
			.append("DELIVERY_ADDR",getDeliveryAddr())
			.append("DETAIL_ADDR",getDetailAddr())
			.append("CREATE_DATE",getCreateDate())
			.append("CREATE_BY",getCreateBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("VERSION",getVersion())
			.append("COORDINATE_X",getCoordinateX())
			.append("COORDINATE_Y",getCoordinateY())
			.append("WE_SHOP_ID",getWeShopId())
			.append("DELIVERY_TYPE_CODE",getDeliveryTypeCode())
			.append("DELIVERY_MIN_ORDER_AMOUNT",getDeliveryMinOrderAmount())
			.append("IS_HAS_RETURNED_PURCHASE",getIsHasReturnedPurchase())
			.append("WECHAT_ID",getWechatId())
			.append("WECHAT_USER_ID",getWechatUserId())
			.append("CANCEL_ORDER_REASON",getCancelOrderReason())
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
		if(!(obj instanceof Order)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		Order other = (Order)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

