
package com.imall.iportal.core.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.math.BigDecimal;
/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_returned_purchase_order" )
public class ReturnedPurchaseOrder extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String PURCHASE_MAN = "purchaseMan";
	public static final String OUT_STORAGE_MAN = "outStorageMan";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String RETURNED_PURCHASE_TOTAL_AMOUNT = "returnedPurchaseTotalAmount";
	public static final String RETURNED_PURCHASE_ORDER_NUM = "returnedPurchaseOrderNum";
	public static final String RETURNED_PURCHASE_TYPE = "returnedPurchaseType";
	public static final String RETURNED_PURCHASE_REASON = "returnedPurchaseReason";
	public static final String REMARK = "remark";
	public static final String IS_PAYED = "isPayed";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** SUPPLIER_ID - 供应商 ID */
    @Column(name = "SUPPLIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long supplierId;
    /** PURCHASE_MAN - 采购 员 */
    @Column(name = "PURCHASE_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String purchaseMan;
    /** OUT_STORAGE_MAN - outStorageMan */
    @Column(name = "OUT_STORAGE_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String outStorageMan;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long approveManId;
    /** RETURNED_PURCHASE_TOTAL_AMOUNT - 退货 总 金额 */
    @Column(name = "RETURNED_PURCHASE_TOTAL_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double returnedPurchaseTotalAmount;
    /** RETURNED_PURCHASE_ORDER_NUM - 退货 单 编号 */
    @Column(name = "RETURNED_PURCHASE_ORDER_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String returnedPurchaseOrderNum;
    /** RETURNED_PURCHASE_TYPE - 退货 类型 */
    @Column(name = "RETURNED_PURCHASE_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String returnedPurchaseType;
    /** RETURNED_PURCHASE_REASON - 退货 原因 */
    @Column(name = "RETURNED_PURCHASE_REASON", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String returnedPurchaseReason;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String remark;
    /** IS_PAYED - 是否 已结款 */
    @Column(name = "IS_PAYED", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isPayed;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setSupplierId(Long value) {
		this.supplierId = value;
	}

    public Long getSupplierId() {
		return this.supplierId;
	}

    public void setPurchaseMan(String value) {
		this.purchaseMan = value;
	}

    public String getPurchaseMan() {
		return this.purchaseMan;
	}

    public void setOutStorageMan(String value) {
		this.outStorageMan = value;
	}

    public String getOutStorageMan() {
		return this.outStorageMan;
	}

    public void setApproveManId(Long value) {
		this.approveManId = value;
	}

    public Long getApproveManId() {
		return this.approveManId;
	}

    public void setReturnedPurchaseTotalAmount(Double value) {
		this.returnedPurchaseTotalAmount = value;
	}

    public Double getReturnedPurchaseTotalAmount() {
		return this.returnedPurchaseTotalAmount;
	}

    public void setReturnedPurchaseOrderNum(String value) {
		this.returnedPurchaseOrderNum = value;
	}

    public String getReturnedPurchaseOrderNum() {
		return this.returnedPurchaseOrderNum;
	}

    public void setReturnedPurchaseType(String value) {
		this.returnedPurchaseType = value;
	}

    public String getReturnedPurchaseType() {
		return this.returnedPurchaseType;
	}

    public void setReturnedPurchaseReason(String value) {
		this.returnedPurchaseReason = value;
	}

    public String getReturnedPurchaseReason() {
		return this.returnedPurchaseReason;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

    public void setIsPayed(String value) {
		this.isPayed = value;
	}

    public String getIsPayed() {
		return this.isPayed;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("SUPPLIER_ID",getSupplierId())
			.append("PURCHASE_MAN",getPurchaseMan())
			.append("OUT_STORAGE_MAN",getOutStorageMan())
			.append("APPROVE_MAN_ID",getApproveManId())
			.append("RETURNED_PURCHASE_TOTAL_AMOUNT",getReturnedPurchaseTotalAmount())
			.append("RETURNED_PURCHASE_ORDER_NUM",getReturnedPurchaseOrderNum())
			.append("RETURNED_PURCHASE_TYPE",getReturnedPurchaseType())
			.append("RETURNED_PURCHASE_REASON",getReturnedPurchaseReason())
			.append("REMARK",getRemark())
			.append("IS_PAYED",getIsPayed())
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
		if(!(obj instanceof ReturnedPurchaseOrder)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ReturnedPurchaseOrder other = (ReturnedPurchaseOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

