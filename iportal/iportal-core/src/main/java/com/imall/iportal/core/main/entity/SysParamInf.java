
package com.imall.iportal.core.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.UnsupportedEncodingException;

/**
 * T_PT_SYS_PARAM_INF【系统参数】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_param_inf" )
public class SysParamInf extends BaseEntity<Long>{

	public static final String PARAM_GROUP_TYPE_CODE = "paramGroupTypeCode";
	public static final String PARAM_TYPE_CODE = "paramTypeCode";
	public static final String INNER_CODE = "innerCode";
	public static final String PARAM_NM = "paramNm";
	public static final String PARAM_DESCR = "paramDescr";
	public static final String PARAM_VALUE = "paramValue";
	public static final String SYS_ORG_ID = "sysOrgId";

    /** PARAM_GROUP_TYPE_CODE - 参数 分组 类型 代码 */
    @Column(name = "PARAM_GROUP_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String paramGroupTypeCode;
    /** PARAM_TYPE_CODE - 参数 类型 代码 */
    @Column(name = "PARAM_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String paramTypeCode;
    /** INNER_CODE - 内部 编码 */
    @Column(name = "INNER_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String innerCode;
    /** PARAM_NM - 参数 名称 */
    @Column(name = "PARAM_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private java.lang.String paramNm;
    /** PARAM_DESCR - 参数 描述 */
    @Column(name = "PARAM_DESCR", unique = false, nullable = false, insertable = true, updatable = true, length = 512)
    private java.lang.String paramDescr;
    /** PARAM_VALUE - 参数值 */
	@Lob
    @Column(name = "PARAM_VALUE", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
    private byte[] paramValue;
    /** SYS_ORG_ID - 机构 ID */
    @Column(name = "SYS_ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long sysOrgId;

    public void setParamGroupTypeCode(java.lang.String value) {
		this.paramGroupTypeCode = value;
	}

    public java.lang.String getParamGroupTypeCode() {
		return this.paramGroupTypeCode;
	}

    public void setParamTypeCode(java.lang.String value) {
		this.paramTypeCode = value;
	}

    public java.lang.String getParamTypeCode() {
		return this.paramTypeCode;
	}

    public void setInnerCode(java.lang.String value) {
		this.innerCode = value;
	}

    public java.lang.String getInnerCode() {
		return this.innerCode;
	}

    public void setParamNm(java.lang.String value) {
		this.paramNm = value;
	}

    public java.lang.String getParamNm() {
		return this.paramNm;
	}

    public void setParamDescr(java.lang.String value) {
		this.paramDescr = value;
	}

    public java.lang.String getParamDescr() {
		return this.paramDescr;
	}

    public void setParamValue(byte[] value) {
		this.paramValue = value;
	}

    public byte[] getParamValue() {
		return this.paramValue;
	}

    public void setSysOrgId(java.lang.Long value) {
		this.sysOrgId = value;
	}

    public java.lang.Long getSysOrgId() {
		return this.sysOrgId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("PARAM_GROUP_TYPE_CODE",getParamGroupTypeCode())
			.append("PARAM_TYPE_CODE",getParamTypeCode())
			.append("INNER_CODE",getInnerCode())
			.append("PARAM_NM",getParamNm())
			.append("PARAM_DESCR",getParamDescr())
			.append("PARAM_VALUE",getParamValue())
			.append("SYS_ORG_ID",getSysOrgId())
			.append("CREATE_BY",getCreateBy())
			.append("CREATE_DATE",getCreateDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
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
		if(!(obj instanceof SysParamInf)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysParamInf other = (SysParamInf)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public void setParamValueStr(String value) {
		if(value == null){
			this.paramValue = null;
		}else{
			try {
				this.paramValue = value.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace(); //NOSONAR
			}
		}
	}

	public String getParamValueStr() {
		try {
			return this.paramValue == null ? "" : new String(this.paramValue, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); //NOSONAR
			return "";
		}
	}
}

