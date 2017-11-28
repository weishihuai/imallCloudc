
package com.imall.iportal.core.main.entity;

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
@Table(name = "t_pt_sys_exception_code" )
public class SysExceptionCode extends BaseEntity<Long>{

	public static final String TABLE_ALIAS = "SysExceptionCode";
	public static final String CODE = "code";
	public static final String EXCEPTION_MSG = "exceptionMsg";
	public static final String REMARK = "remark";

    /** CODE - 异常编码 */
    @Column(name = "CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 120)
    private String code;
    /** EXCEPTION_MSG - 异常提示 */
    @Column(name = "EXCEPTION_MSG", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
    private String exceptionMsg;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
    private String remark;

    public void setCode(String value) {
		this.code = value;
	}

    public String getCode() {
		return this.code;
	}

    public void setExceptionMsg(String value) {
		this.exceptionMsg = value;
	}

    public String getExceptionMsg() {
		return this.exceptionMsg;
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
			.append("CODE",getCode())
			.append("EXCEPTION_MSG",getExceptionMsg())
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
		if(!(obj instanceof SysExceptionCode)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysExceptionCode other = (SysExceptionCode)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

