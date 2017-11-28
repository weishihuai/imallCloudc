
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
@Table(name = "t_shp_temperature_moisture_monitor_record" )
public class TemperatureMoistureMonitorRecord extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String STORAGE_SPACE_ID = "storageSpaceId";
	public static final String MONITOR_DATE = "monitorDate";
	public static final String RECORD_MAN = "recordMan";
	public static final String MONITOR_TIME = "monitorTime";
	public static final String TEMPERATURE = "temperature";
	public static final String MOISTURE = "moisture";
	public static final String CONTROL_MEASURE = "controlMeasure";
	public static final String TIME_AFTER_CONTROL = "timeAfterControl";
	public static final String TEMPERATURE_AFTER_CONTROL = "temperatureAfterControl";
	public static final String MOISTURE_AFTER_CONTROL = "moistureAfterControl";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
	/** STORAGE_SPACE_ID - 货位 ID */
	@Column(name = "STORAGE_SPACE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long storageSpaceId;
    /** MONITOR_DATE - 监控 日期 */
    @Column(name = "MONITOR_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date monitorDate;
    /** RECORD_MAN - 记录 人 */
    @Column(name = "RECORD_MAN", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String recordMan;
    /** MONITOR_TIME - 监控 时间 */
    @Column(name = "MONITOR_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String monitorTime;
    /** TEMPERATURE - 温度 */
    @Column(name = "TEMPERATURE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double temperature;
    /** MOISTURE - 湿度 */
    @Column(name = "MOISTURE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double moisture;
    /** CONTROL_MEASURE - 调控 措施 */
    @Column(name = "CONTROL_MEASURE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String controlMeasure;
    /** TIME_AFTER_CONTROL - 调控后 时间 */
    @Column(name = "TIME_AFTER_CONTROL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String timeAfterControl;
    /** TEMPERATURE_AFTER_CONTROL - 调控后 温度 */
    @Column(name = "TEMPERATURE_AFTER_CONTROL", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Double temperatureAfterControl;
    /** MOISTURE_AFTER_CONTROL - 调控后 湿度 */
    @Column(name = "MOISTURE_AFTER_CONTROL", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Double moistureAfterControl;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setMonitorDate(java.util.Date value) {
		this.monitorDate = value;
	}

    public java.util.Date getMonitorDate() {
		return this.monitorDate;
	}

    public void setRecordMan(String value) {
		this.recordMan = value;
	}

    public String getRecordMan() {
		return this.recordMan;
	}

    public void setMonitorTime(String value) {
		this.monitorTime = value;
	}

    public String getMonitorTime() {
		return this.monitorTime;
	}

    public void setTemperature(Double value) {
		this.temperature = value;
	}

    public Double getTemperature() {
		return this.temperature;
	}

    public void setMoisture(Double value) {
		this.moisture = value;
	}

    public Double getMoisture() {
		return this.moisture;
	}

    public void setControlMeasure(String value) {
		this.controlMeasure = value;
	}

    public String getControlMeasure() {
		return this.controlMeasure;
	}

    public void setTimeAfterControl(String value) {
		this.timeAfterControl = value;
	}

    public String getTimeAfterControl() {
		return this.timeAfterControl;
	}

    public void setTemperatureAfterControl(Double value) {
		this.temperatureAfterControl = value;
	}

    public Double getTemperatureAfterControl() {
		return this.temperatureAfterControl;
	}

    public void setMoistureAfterControl(Double value) {
		this.moistureAfterControl = value;
	}

    public Double getMoistureAfterControl() {
		return this.moistureAfterControl;
	}

    public void setStorageSpaceId(Long value) {
		this.storageSpaceId = value;
	}

    public Long getStorageSpaceId() {
		return this.storageSpaceId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("MONITOR_DATE",getMonitorDate())
			.append("RECORD_MAN",getRecordMan())
			.append("MONITOR_TIME",getMonitorTime())
			.append("TEMPERATURE",getTemperature())
			.append("MOISTURE",getMoisture())
			.append("CONTROL_MEASURE",getControlMeasure())
			.append("TIME_AFTER_CONTROL",getTimeAfterControl())
			.append("TEMPERATURE_AFTER_CONTROL",getTemperatureAfterControl())
			.append("MOISTURE_AFTER_CONTROL",getMoistureAfterControl())
			.append("CREATE_DATE",getCreateDate())
			.append("CREATE_BY",getCreateBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("VERSION",getVersion())
			.append("STORAGE_SPACE_ID",getStorageSpaceId())
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
		if(!(obj instanceof TemperatureMoistureMonitorRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		TemperatureMoistureMonitorRecord other = (TemperatureMoistureMonitorRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

