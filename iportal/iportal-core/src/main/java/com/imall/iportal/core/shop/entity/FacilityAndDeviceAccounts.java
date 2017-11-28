
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
@Table(name = "t_shp_facility_and_device_accounts" )
public class FacilityAndDeviceAccounts extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String DEVICE_TYPE_CODE = "deviceTypeCode";
	public static final String DEVICE_NUM = "deviceNum";
	public static final String DEVICE_NM = "deviceNm";
	public static final String MODEL = "model";
	public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
	public static final String RESPONSE_MAN = "responseMan";
	public static final String PURCHASE_PRICE = "purchasePrice";
	public static final String PURCHASE_DATE = "purchaseDate";
	public static final String ENABLE_TIME = "enableTime";
	public static final String PURCHASE_PLACE = "purchasePlace";
	public static final String APPLICATION = "application";
	public static final String SERVICE_LIFE = "serviceLife";
	public static final String REMARK = "remark";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** DEVICE_TYPE_CODE - 设备 类型 代码 */
    @Column(name = "DEVICE_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String deviceTypeCode;
    /** DEVICE_NUM - 设备 编号 */
    @Column(name = "DEVICE_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String deviceNum;
    /** DEVICE_NM - 设备 名称 */
    @Column(name = "DEVICE_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String deviceNm;
    /** MODEL - 型号 */
    @Column(name = "MODEL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String model;
    /** PRODUCE_MANUFACTURER - 生产厂商 */
    @Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String produceManufacturer;
    /** RESPONSE_MAN - 负责 人 */
    @Column(name = "RESPONSE_MAN", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String responseMan;
    /** PURCHASE_PRICE - 购置 价格 */
    @Column(name = "PURCHASE_PRICE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Double purchasePrice;
    /** PURCHASE_DATE - 购买 日期 */
    @Column(name = "PURCHASE_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date purchaseDate;
    /** ENABLE_TIME - 启用 时间 */
    @Column(name = "ENABLE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date enableTime;
    /** PURCHASE_PLACE - 购置 地点 */
    @Column(name = "PURCHASE_PLACE", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String purchasePlace;
    /** APPLICATION - 用途 */
    @Column(name = "APPLICATION", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String application;
    /** SERVICE_LIFE - 使用 年限 */
    @Column(name = "SERVICE_LIFE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    private Integer serviceLife;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String remark;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setDeviceTypeCode(String value) {
		this.deviceTypeCode = value;
	}

    public String getDeviceTypeCode() {
		return this.deviceTypeCode;
	}

    public void setDeviceNum(String value) {
		this.deviceNum = value;
	}

    public String getDeviceNum() {
		return this.deviceNum;
	}

    public void setDeviceNm(String value) {
		this.deviceNm = value;
	}

    public String getDeviceNm() {
		return this.deviceNm;
	}

    public void setModel(String value) {
		this.model = value;
	}

    public String getModel() {
		return this.model;
	}

    public void setProduceManufacturer(String value) {
		this.produceManufacturer = value;
	}

    public String getProduceManufacturer() {
		return this.produceManufacturer;
	}

    public void setResponseMan(String value) {
		this.responseMan = value;
	}

    public String getResponseMan() {
		return this.responseMan;
	}

    public void setPurchasePrice(Double value) {
		this.purchasePrice = value;
	}

    public Double getPurchasePrice() {
		return this.purchasePrice;
	}

    public void setPurchaseDate(java.util.Date value) {
		this.purchaseDate = value;
	}

    public java.util.Date getPurchaseDate() {
		return this.purchaseDate;
	}

    public void setEnableTime(java.util.Date value) {
		this.enableTime = value;
	}

    public java.util.Date getEnableTime() {
		return this.enableTime;
	}

    public void setPurchasePlace(String value) {
		this.purchasePlace = value;
	}

    public String getPurchasePlace() {
		return this.purchasePlace;
	}

    public void setApplication(String value) {
		this.application = value;
	}

    public String getApplication() {
		return this.application;
	}

    public void setServiceLife(Integer value) {
		this.serviceLife = value;
	}

    public Integer getServiceLife() {
		return this.serviceLife;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("DEVICE_TYPE_CODE",getDeviceTypeCode())
			.append("DEVICE_NUM",getDeviceNum())
			.append("DEVICE_NM",getDeviceNm())
			.append("MODEL",getModel())
			.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
			.append("RESPONSE_MAN",getResponseMan())
			.append("PURCHASE_PRICE",getPurchasePrice())
			.append("PURCHASE_DATE",getPurchaseDate())
			.append("ENABLE_TIME",getEnableTime())
			.append("PURCHASE_PLACE",getPurchasePlace())
			.append("APPLICATION",getApplication())
			.append("SERVICE_LIFE",getServiceLife())
			.append("REMARK",getRemark())
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
		if(!(obj instanceof FacilityAndDeviceAccounts)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		FacilityAndDeviceAccounts other = (FacilityAndDeviceAccounts)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

