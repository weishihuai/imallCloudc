
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.ParseException;

/**
 * 实体类
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_measuring_device_accounts")
public class MeasuringDeviceAccounts extends BaseEntity<Long> {

    public static final String SHOP_ID = "shopId";
    public static final String MEASURING_DEVICE_NUM = "measuringDeviceNum";
    public static final String MANUFACTURING_NUM = "manufacturingNum";
    public static final String DEVICE_NM = "deviceNm";
    public static final String MODEL = "model";
    public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
    public static final String CATEGORY_NUM = "categoryNum";
    public static final String MEASURE_RANGE = "measureRange";
    public static final String PRECISION_LEVEL = "precisionLevel";
    public static final String RESPONSE_MAN = "responseMan";
    public static final String MEASURE_PERIOD = "measurePeriod";
    public static final String PURCHASE_PRICE = "purchasePrice";
    public static final String PURCHASE_DATE = "purchaseDate";
    public static final String ENABLE_TIME = "enableTime";
    public static final String PURCHASE_PLACE = "purchasePlace";
    public static final String APPLICATION = "application";
    public static final String SERVICE_LIFE = "serviceLife";
    public static final String USE_STATE_CODE = "useStateCode";
    public static final String REMARK = "remark";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** MEASURING_DEVICE_NUM - 计量 器具 编号 */
    @Column(name = "MEASURING_DEVICE_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String measuringDeviceNum;
    /** MANUFACTURING_NUM - 出厂 编号 */
    @Column(name = "MANUFACTURING_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String manufacturingNum;
    /** DEVICE_NM - 器具 名称 */
    @Column(name = "DEVICE_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String deviceNm;
    /** MODEL - 型号 */
    @Column(name = "MODEL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String model;
    /** PRODUCE_MANUFACTURER - 生产厂商 */
    @Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String produceManufacturer;
    /** CATEGORY_NUM - 分类 编号 */
    @Column(name = "CATEGORY_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String categoryNum;
    /** MEASURE_RANGE - 测量 范围 */
    @Column(name = "MEASURE_RANGE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String measureRange;
    /** PRECISION_LEVEL - 精度 等级 */
    @Column(name = "PRECISION_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String precisionLevel;
    /** RESPONSE_MAN - 负责 人 */
    @Column(name = "RESPONSE_MAN", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String responseMan;
    /** MEASURE_PERIOD - 检测 周期 */
    @Column(name = "MEASURE_PERIOD", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    private java.lang.Integer measurePeriod;
    /** PURCHASE_PRICE - 购置 价格 */
    @Column(name = "PURCHASE_PRICE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private java.lang.Double purchasePrice;
    /** PURCHASE_DATE - 购买 日期 */
    @Column(name = "PURCHASE_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date purchaseDate;
    /** ENABLE_TIME - 启用 时间 */
    @Column(name = "ENABLE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date enableTime;
    /** PURCHASE_PLACE - 购置 地点 */
    @Column(name = "PURCHASE_PLACE", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private java.lang.String purchasePlace;
    /** APPLICATION - 用途 */
    @Column(name = "APPLICATION", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private java.lang.String application;
    /** SERVICE_LIFE - 使用 年限 */
    @Column(name = "SERVICE_LIFE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    private java.lang.Integer serviceLife;
    /** USE_STATE_CODE - 使用 状态 代码 */
    @Column(name = "USE_STATE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String useStateCode;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private java.lang.String remark;

    public void setShopId(Long value) {
        this.shopId = value;
    }

    public Long getShopId() {
        return this.shopId;
    }

    public void setMeasuringDeviceNum(String value) {
        this.measuringDeviceNum = value;
    }

    public String getMeasuringDeviceNum() {
        return this.measuringDeviceNum;
    }

    public void setManufacturingNum(String value) {
        this.manufacturingNum = value;
    }

    public String getManufacturingNum() {
        return this.manufacturingNum;
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

    public void setCategoryNum(String value) {
        this.categoryNum = value;
    }

    public String getCategoryNum() {
        return this.categoryNum;
    }

    public void setMeasureRange(String value) {
        this.measureRange = value;
    }

    public String getMeasureRange() {
        return this.measureRange;
    }

    public void setPrecisionLevel(String value) {
        this.precisionLevel = value;
    }

    public String getPrecisionLevel() {
        return this.precisionLevel;
    }

    public void setResponseMan(String value) {
        this.responseMan = value;
    }

    public String getResponseMan() {
        return this.responseMan;
    }

    public void setMeasurePeriod(Integer value) {
        this.measurePeriod = value;
    }

    public Integer getMeasurePeriod() {
        return this.measurePeriod;
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

    public void setUseStateCode(String value) {
        this.useStateCode = value;
    }

    public String getUseStateCode() {
        return this.useStateCode;
    }

    public void setRemark(String value) {
        this.remark = value;
    }

    public String getRemark() {
        return this.remark;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ID", getId())
                .append("SHOP_ID", getShopId())
                .append("MEASURING_DEVICE_NUM", getMeasuringDeviceNum())
                .append("MANUFACTURING_NUM", getManufacturingNum())
                .append("DEVICE_NM", getDeviceNm())
                .append("MODEL", getModel())
                .append("PRODUCE_MANUFACTURER", getProduceManufacturer())
                .append("CATEGORY_NUM", getCategoryNum())
                .append("MEASURE_RANGE", getMeasureRange())
                .append("PRECISION_LEVEL", getPrecisionLevel())
                .append("RESPONSE_MAN", getResponseMan())
                .append("MEASURE_PERIOD", getMeasurePeriod())
                .append("PURCHASE_PRICE", getPurchasePrice())
                .append("PURCHASE_DATE", getPurchaseDate())
                .append("ENABLE_TIME", getEnableTime())
                .append("PURCHASE_PLACE", getPurchasePlace())
                .append("APPLICATION", getApplication())
                .append("SERVICE_LIFE", getServiceLife())
                .append("USE_STATE_CODE", getUseStateCode())
                .append("REMARK", getRemark())
                .append("CREATE_DATE", getCreateDate())
                .append("CREATE_BY", getCreateBy())
                .append("LAST_MODIFIED_DATE", getLastModifiedDate())
                .append("LAST_MODIFIED_BY", getLastModifiedBy())
                .append("VERSION", getVersion())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (this.getId() == null) {
            return false;
        }
        if (!(obj instanceof MeasuringDeviceAccounts)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        MeasuringDeviceAccounts other = (MeasuringDeviceAccounts) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    public String getPurchaseDateString() {
        return DateTimeUtils.convertDateToString(this.getPurchaseDate());
    }

    public void sePurchaseDateString(String value) throws ParseException {
        this.setPurchaseDate(DateTimeUtils.convertStringToDate(value));
    }

    public String getEnableTimeString() {
        return DateTimeUtils.convertDateToString(this.getEnableTime());
    }

    public void setEnableTimeString(String value) throws ParseException {
        this.setEnableTime(DateTimeUtils.convertStringToDate(value));
    }

}

