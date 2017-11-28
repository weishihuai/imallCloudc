
package com.imall.iportal.core.shop.entity;

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
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_purchase_order" )
public class PurchaseOrder extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String PURCHASE_MAN = "purchaseMan";
	public static final String PURCHASE_ORDER_NUM = "purchaseOrderNum";
	public static final String PURCHASE_ORDER_TYPE = "purchaseOrderType";
	public static final String PURCHASE_ORDER_STATE = "purchaseOrderState";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String SELL_MAN = "sellMan";
	public static final String CONTACT_TEL = "contactTel";
	public static final String EXPECTED_ARRIVAL_TIME = "expectedArrivalTime";
	public static final String PURCHASE_TOTAL_AMOUNT = "purchaseTotalAmount";
	public static final String REMARK = "remark";
	public static final String CLEARING_TIME = "clearingTime";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** PURCHASE_MAN - 采购 人 */
    @Column(name = "PURCHASE_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String purchaseMan;
    /** PURCHASE_ORDER_NUM - 采购 订单 编号 */
    @Column(name = "PURCHASE_ORDER_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String purchaseOrderNum;
    /** PURCHASE_ORDER_TYPE - 采购 订单 类型 */
    @Column(name = "PURCHASE_ORDER_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String purchaseOrderType;
    /** PURCHASE_ORDER_STAT - 采购 订单 状态 */
    @Column(name = "PURCHASE_ORDER_STATE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String purchaseOrderState;
    /** SUPPLIER_ID - 供应商 ID */
    @Column(name = "SUPPLIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long supplierId;
    /** SELL_MAN - 销售 人 */
    @Column(name = "SELL_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String sellMan;
    /** CONTACT_TEL - 联系 电话 */
    @Column(name = "CONTACT_TEL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String contactTel;
    /** EXPECTED_ARRIVAL_TIME - 预计 到货 时间 */
    @Column(name = "EXPECTED_ARRIVAL_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date expectedArrivalTime;
    /** PURCHASE_TOTAL_AMOUNT - 采购 总 金额 */
    @Column(name = "PURCHASE_TOTAL_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double purchaseTotalAmount;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String remark;
    /** CLEARING_TIME - 已清 时间 */
    @Column(name = "CLEARING_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date clearingTime;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setPurchaseMan(String value) {
		this.purchaseMan = value;
	}

    public String getPurchaseMan() {
		return this.purchaseMan;
	}

    public void setPurchaseOrderNum(String value) {
		this.purchaseOrderNum = value;
	}

    public String getPurchaseOrderNum() {
		return this.purchaseOrderNum;
	}

    public void setPurchaseOrderType(String value) {
		this.purchaseOrderType = value;
	}

    public String getPurchaseOrderType() {
		return this.purchaseOrderType;
	}

	public String getPurchaseOrderState() {
		return purchaseOrderState;
	}

	public void setPurchaseOrderState(String purchaseOrderState) {
		this.purchaseOrderState = purchaseOrderState;
	}

	public void setSupplierId(Long value) {
		this.supplierId = value;
	}

    public Long getSupplierId() {
		return this.supplierId;
	}

    public void setSellMan(String value) {
		this.sellMan = value;
	}

    public String getSellMan() {
		return this.sellMan;
	}

    public void setContactTel(String value) {
		this.contactTel = value;
	}

    public String getContactTel() {
		return this.contactTel;
	}

    public void setExpectedArrivalTime(java.util.Date value) {
		this.expectedArrivalTime = value;
	}

	public void setExpectedArrivalTimeString(String value) throws ParseException {
		this.expectedArrivalTime = DateTimeUtils.convertStringToDate(value);
	}

    public java.util.Date getExpectedArrivalTime() {
		return this.expectedArrivalTime;
	}

	public String getExpectedArrivalTimeString() {
		return DateTimeUtils.convertDateToString(this.expectedArrivalTime);
	}

    public void setPurchaseTotalAmount(Double value) {
		this.purchaseTotalAmount = value;
	}

    public Double getPurchaseTotalAmount() {
		return this.purchaseTotalAmount;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

    public void setClearingTime(java.util.Date value) {
		this.clearingTime = value;
	}

	public void setClearingTimeString(String value) throws ParseException {
		this.clearingTime = DateTimeUtils.convertStringToDateTime(value);
	}

    public java.util.Date getClearingTime() {
		return this.clearingTime;
	}

	public String getClearingTimeString() {
		return DateTimeUtils.convertDateTimeToString(this.clearingTime);
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("PURCHASE_MAN",getPurchaseMan())
			.append("PURCHASE_ORDER_NUM",getPurchaseOrderNum())
			.append("PURCHASE_ORDER_TYPE",getPurchaseOrderType())
			.append("PURCHASE_ORDER_STATE",getPurchaseOrderState())
			.append("SUPPLIER_ID",getSupplierId())
			.append("SELL_MAN",getSellMan())
			.append("CONTACT_TEL",getContactTel())
			.append("EXPECTED_ARRIVAL_TIME",getExpectedArrivalTime())
			.append("PURCHASE_TOTAL_AMOUNT",getPurchaseTotalAmount())
			.append("REMARK",getRemark())
			.append("CLEARING_TIME",getClearingTime())
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
		if(!(obj instanceof PurchaseOrder)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		PurchaseOrder other = (PurchaseOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

