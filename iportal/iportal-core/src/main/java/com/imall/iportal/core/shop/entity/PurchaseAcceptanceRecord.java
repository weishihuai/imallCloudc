
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
@Table(name = "t_shp_purchase_acceptance_record" )
public class PurchaseAcceptanceRecord extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String PURCHASE_RECEIVE_RECORD_ID = "purchaseReceiveRecordId";
	public static final String PURCHASE_ORDER_ID = "purchaseOrderId";
	public static final String ACCEPTANCE_ORDER_NUM = "acceptanceOrderNum";
	public static final String DOC_MAKER = "docMaker";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String ACCEPTANCE_TIME = "acceptanceTime";
	public static final String ACCEPTANCE_MAN = "acceptanceMan";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String IS_PAYED = "isPayed";
	public static final String PAYED_TIME = "payedTime";
	public static final String REMARK = "remark";
	public static final String ACCEPTANCE_TOTAL_AMOUNT = "acceptanceTotalAmount";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
	/** PURCHASE_ORDER_ID - 采购 收货 记录 ID */
	@Column(name = "PURCHASE_RECEIVE_RECORD_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long purchaseReceiveRecordId;
    /** PURCHASE_ORDER_ID - 采购 订单 ID */
    @Column(name = "PURCHASE_ORDER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long purchaseOrderId;
    /** ACCEPTANCE_ORDER_NUM - 验收 单 编号 */
    @Column(name = "ACCEPTANCE_ORDER_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String acceptanceOrderNum;
    /** DOC_MAKER - 制单人 */
    @Column(name = "DOC_MAKER", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String docMaker;
    /** SUPPLIER_ID - 供应商 ID */
    @Column(name = "SUPPLIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long supplierId;
    /** ACCEPTANCE_TIME - 验收 时间 */
    @Column(name = "ACCEPTANCE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date acceptanceTime;
    /** ACCEPTANCE_MAN - 验收 员 */
    @Column(name = "ACCEPTANCE_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String acceptanceMan;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long approveManId;
    /** IS_PAYED - 是否 已结款 */
    @Column(name = "IS_PAYED", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isPayed;
    /** PAYED_TIME - 结款 时间 */
    @Column(name = "PAYED_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date payedTime;
	/** REMARK - 备注 */
	@Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String remark;
	/** ACCEPTANCE_TOTAL_AMOUNT - 验收 总 金额 */
	@Column(name = "ACCEPTANCE_TOTAL_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private Double acceptanceTotalAmount;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

	public Long getPurchaseReceiveRecordId() {
		return purchaseReceiveRecordId;
	}

	public void setPurchaseReceiveRecordId(Long purchaseReceiveRecordId) {
		this.purchaseReceiveRecordId = purchaseReceiveRecordId;
	}

	public void setPurchaseOrderId(Long value) {
		this.purchaseOrderId = value;
	}

    public Long getPurchaseOrderId() {
		return this.purchaseOrderId;
	}

    public void setAcceptanceOrderNum(String value) {
		this.acceptanceOrderNum = value;
	}

    public String getAcceptanceOrderNum() {
		return this.acceptanceOrderNum;
	}

    public void setDocMaker(String value) {
		this.docMaker = value;
	}

    public String getDocMaker() {
		return this.docMaker;
	}

    public void setSupplierId(Long value) {
		this.supplierId = value;
	}

    public Long getSupplierId() {
		return this.supplierId;
	}

    public void setAcceptanceTime(java.util.Date value) {
		this.acceptanceTime = value;
	}

	public void setAcceptanceTimeString(String value) throws ParseException {
		this.acceptanceTime = DateTimeUtils.convertStringToDate(value);
	}

    public java.util.Date getAcceptanceTime() {
		return this.acceptanceTime;
	}

	public String getAcceptanceTimeString() {
		return DateTimeUtils.convertDateToString(this.acceptanceTime);
	}

    public void setAcceptanceMan(String value) {
		this.acceptanceMan = value;
	}

    public String getAcceptanceMan() {
		return this.acceptanceMan;
	}

    public void setApproveManId(Long value) {
		this.approveManId = value;
	}

    public Long getApproveManId() {
		return this.approveManId;
	}

    public void setIsPayed(String value) {
		this.isPayed = value;
	}

    public String getIsPayed() {
		return this.isPayed;
	}

    public void setPayedTime(java.util.Date value) {
		this.payedTime = value;
	}

	public void setPayedTimeString(String value) throws ParseException {
		this.payedTime = DateTimeUtils.convertStringToDateTime(value);
	}

    public java.util.Date getPayedTime() {
		return this.payedTime;
	}

	public String getPayedTimeString() {
		return DateTimeUtils.convertDateTimeToString(this.payedTime);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getAcceptanceTotalAmount() {
		return acceptanceTotalAmount;
	}

	public void setAcceptanceTotalAmount(Double acceptanceTotalAmount) {
		this.acceptanceTotalAmount = acceptanceTotalAmount;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("PURCHASE_RECEIVE_RECORD_ID",getPurchaseReceiveRecordId())
			.append("PURCHASE_ORDER_ID",getPurchaseOrderId())
			.append("ACCEPTANCE_ORDER_NUM",getAcceptanceOrderNum())
			.append("DOC_MAKER",getDocMaker())
			.append("SUPPLIER_ID",getSupplierId())
			.append("ACCEPTANCE_TIME",getAcceptanceTime())
			.append("ACCEPTANCE_MAN",getAcceptanceMan())
			.append("APPROVE_MAN_ID",getApproveManId())
			.append("IS_PAYED",getIsPayed())
			.append("PAYED_TIME",getPayedTime())
			.append("REMARK	",getRemark())
			.append("ACCEPTANCE_TOTAL_AMOUNT	",getAcceptanceTotalAmount())
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
		if(!(obj instanceof PurchaseAcceptanceRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		PurchaseAcceptanceRecord other = (PurchaseAcceptanceRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

