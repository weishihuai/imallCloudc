
package com.imall.iportal.core.main.entity;

import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.UnsupportedEncodingException;

/**
 * 系统 日志 数据实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_log_data" )
public class SysLogData extends BaseEntity<Long>{

	public static final String LOG_INNER_CODE = "logInnerCode";
	public static final String TABLE_KEY = "tableKey";
	public static final String OBJECT_ID = "objectId";
	public static final String OPERATION_TIME = "operationTime";
	public static final String DATA_OPERATION_TYPE_CODE = "dataOperationTypeCode";
	public static final String BEFORE_OPERATION_DATA = "beforeOperationData";
	public static final String AFTER_OPERATION_DATA = "afterOperationData";

    /** LOG_INNER_CODE - 日志 内部 编码 */
    @Column(name = "LOG_INNER_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String logInnerCode;
    /** TABLE_KEY - 表键 */
    @Column(name = "TABLE_KEY", unique = false, nullable = false, insertable = true, updatable = true, length = 256)
    private String tableKey;
	/** OBJECT_ID - 对象ID */
	@Column(name = "OBJECT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	private java.lang.Long objectId;
    /** OPERATION_TIME - 操作 时间 */
    @Column(name = "OPERATION_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date operationTime;
    /** DATA_OPERATION_TYPE_CODE - 数据 操作 类型 代码 */
    @Column(name = "DATA_OPERATION_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String dataOperationTypeCode;
    /** BEFORE_OPERATION_DATA - 之前 操作 数据 */
    @Column(name = "BEFORE_OPERATION_DATA", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
    private byte[] beforeOperationData;
    /** AFTER_OPERATION_DATA - 之后 操作 数据 */
    @Column(name = "AFTER_OPERATION_DATA", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
    private byte[] afterOperationData;

    public void setLogInnerCode(String value) {
		this.logInnerCode = value;
	}

    public String getLogInnerCode() {
		return this.logInnerCode;
	}

    public void setTableKey(String value) {
		this.tableKey = value;
	}

    public String getTableKey() {
		return this.tableKey;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public void setOperationTime(java.util.Date value) {
		this.operationTime = value;
	}

    public java.util.Date getOperationTime() {
		return this.operationTime;
	}

    public void setDataOperationTypeCode(String value) {
		this.dataOperationTypeCode = value;
	}

    public String getDataOperationTypeCode() {
		return this.dataOperationTypeCode;
	}

    public void setBeforeOperationData(byte[] value) {
		this.beforeOperationData = value;
	}

    public byte[] getBeforeOperationData() {
		return this.beforeOperationData;
	}

    public void setAfterOperationData(byte[] value) {
		this.afterOperationData = value;
	}

    public byte[] getAfterOperationData() {
		return this.afterOperationData;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("LOG_INNER_CODE",getLogInnerCode())
			.append("TABLE_KEY",getTableKey())
			.append("OBJECT_ID", getObjectId())
			.append("OPERATION_TIME",getOperationTime())
			.append("DATA_OPERATION_TYPE_CODE",getDataOperationTypeCode())
			.append("BEFORE_OPERATION_DATA",getBeforeOperationData())
			.append("AFTER_OPERATION_DATA",getAfterOperationData())
			.append("CREATE_DATE",getCreateDate())
			.append("CREATE_BY",getCreateBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("VERSION",getVersion())
			.toString();
	}

	public void setBeforeOperationDataStr(String value) {
		if(value == null){
			this.beforeOperationData = null;
		}else{
			try {
				this.beforeOperationData = value.getBytes(UTF8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	public String getBeforeOperationDataStr() {
		try {
			return this.beforeOperationData == null ? "" : new String(this.beforeOperationData,UTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); //NOSONAR
			return "";
		}
	}

	public void setAfterOperationDataStr(String value) {
		if(value == null){
			this.afterOperationData = null;
		}else{
			try {
				this.afterOperationData = value.getBytes(UTF8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	public String getAfterOperationDataStr() {
		try {
			return this.afterOperationData == null ? "" : new String(this.afterOperationData,UTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); //NOSONAR
			return "";
		}
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
		if(!(obj instanceof SysLogData)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysLogData other = (SysLogData)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

