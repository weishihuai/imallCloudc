
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
@Table(name = "t_shp_purchase_receive_record" )
public class PurchaseReceiveRecord extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String PURCHASE_ORDER_ID = "purchaseOrderId";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String RECEIVE_ORDER_NUM = "receiveOrderNum";
	public static final String RECEIVER = "receiver";
	public static final String RECEIVE_TIME = "receiveTime";
	public static final String REMARK = "remark";
	public static final String IS_ALL_ACCEPTANCE = "isAllAcceptance";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** PURCHASE_ORDER_ID - 采购 订单 ID */
    @Column(name = "PURCHASE_ORDER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long purchaseOrderId;
    /** SUPPLIER_ID - 供应商 ID */
    @Column(name = "SUPPLIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long supplierId;
    /** RECEIVE_ORDER_NUM - 收货单 编号 */
    @Column(name = "RECEIVE_ORDER_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String receiveOrderNum;
    /** RECEIVER - 收货人 */
    @Column(name = "RECEIVER", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String receiver;
    /** RECEIVE_TIME - 收货 时间 */
    @Column(name = "RECEIVE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date receiveTime;
	/** REMARK - 备注 */
	@Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String remark;
	/** IS_ALL_ACCEPTANCE - 是否 已全部 验收 */
	@Column(name = "IS_ALL_ACCEPTANCE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private String isAllAcceptance;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setPurchaseOrderId(Long value) {
		this.purchaseOrderId = value;
	}

    public Long getPurchaseOrderId() {
		return this.purchaseOrderId;
	}

    public void setSupplierId(Long value) {
		this.supplierId = value;
	}

    public Long getSupplierId() {
		return this.supplierId;
	}

    public void setReceiveOrderNum(String value) {
		this.receiveOrderNum = value;
	}

    public String getReceiveOrderNum() {
		return this.receiveOrderNum;
	}

    public void setReceiver(String value) {
		this.receiver = value;
	}

    public String getReceiver() {
		return this.receiver;
	}

    public void setReceiveTime(java.util.Date value) {
		this.receiveTime = value;
	}

	public void setReceiveTimeString(String value) throws ParseException {
		this.receiveTime = DateTimeUtils.convertStringToDate(value);
	}

    public java.util.Date getReceiveTime() {
		return this.receiveTime;
	}

	public String getReceiveTimeString() {
		return DateTimeUtils.convertDateToString(this.receiveTime);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsAllAcceptance() {
		return isAllAcceptance;
	}

	public void setIsAllAcceptance(String isAllAcceptance) {
		this.isAllAcceptance = isAllAcceptance;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("PURCHASE_ORDER_ID",getPurchaseOrderId())
			.append("SUPPLIER_ID",getSupplierId())
			.append("RECEIVE_ORDER_NUM",getReceiveOrderNum())
			.append("RECEIVER",getReceiver())
			.append("RECEIVE_TIME",getReceiveTime())
			.append("REMARK	",getRemark())
			.append("IS_ALL_ACCEPTANCE",getIsAllAcceptance())
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
		if(!(obj instanceof PurchaseReceiveRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		PurchaseReceiveRecord other = (PurchaseReceiveRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

