
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
 * 系统 日志实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_log" )
public class SysLog extends BaseEntity<Long>{

	public static final String LOG_INNER_CODE = "logInnerCode";
	public static final String LOG_TIME = "logTime";
	public static final String LOG_TYPE_CODE = "logTypeCode";
	public static final String NAV = "nav";
	public static final String LOG_TITLE = "logTitle";
	public static final String LOG_CONTENT = "logContent";

    /** LOG_INNER_CODE - 日志 内部 编码 */
    @Column(name = "LOG_INNER_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String logInnerCode;
    /** LOG_TIME - 日志 时间 */
    @Column(name = "LOG_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date logTime;
    /** LOG_TYPE_CODE - 日志 类型 代码 */
    @Column(name = "LOG_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String logTypeCode;
    /** NAV - 导航 */
    @Column(name = "NAV", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String nav;
    /** LOG_TITLE - 日志 标题 */
    @Column(name = "LOG_TITLE", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String logTitle;
    /** LOG_CONTENT - 日志 内容 */
    @Column(name = "LOG_CONTENT", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
    private byte[] logContent;

    public void setLogInnerCode(String value) {
		this.logInnerCode = value;
	}

    public String getLogInnerCode() {
		return this.logInnerCode;
	}

    public void setLogTime(java.util.Date value) {
		this.logTime = value;
	}

    public java.util.Date getLogTime() {
		return this.logTime;
	}

    public void setLogTypeCode(String value) {
		this.logTypeCode = value;
	}

    public String getLogTypeCode() {
		return this.logTypeCode;
	}

    public void setNav(String value) {
		this.nav = value;
	}

    public String getNav() {
		return this.nav;
	}

    public void setLogTitle(String value) {
		this.logTitle = value;
	}

    public String getLogTitle() {
		return this.logTitle;
	}

    public void setLogContent(byte[] value) {
		this.logContent = value;
	}

    public byte[] getLogContent() {
		return this.logContent;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("LOG_INNER_CODE",getLogInnerCode())
			.append("LOG_TIME",getLogTime())
			.append("LOG_TYPE_CODE",getLogTypeCode())
			.append("NAV",getNav())
			.append("LOG_TITLE",getLogTitle())
			.append("LOG_CONTENT",getLogContent())
			.append("CREATE_DATE",getCreateDate())
			.append("CREATE_BY",getCreateBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("VERSION",getVersion())
			.toString();
	}

	public void setLogContentStr(String value) {
		if(value == null){
			this.logContent = null;
		}else{
			try {
				this.logContent = value.getBytes(UTF8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	public String getLogContentStr() {
		try {
			return this.logContent == null ? "" : new String(this.logContent,UTF8);
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
		if(!(obj instanceof SysLog)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysLog other = (SysLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

