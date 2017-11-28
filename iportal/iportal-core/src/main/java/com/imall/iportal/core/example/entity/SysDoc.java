
package com.imall.iportal.core.example.entity;

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
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_doc" )
public class SysDoc extends BaseEntity<Long>{

	public static final String TABLE_ALIAS = "SysDoc";
	public static final String TITLE = "title";
	public static final String SUMMARY = "summary";
	public static final String LINK = "link";
	public static final String RELEASE_VERSION = "releaseVersion";
	public static final String CONT = "cont";
	public static final String ORDERBY = "orderby";
	public static final String IS_AVAILABLE = "isAvailable";

    /** TITLE - 标题 */
    @Column(name = "TITLE", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String title;
    /** SUMMARY - 摘要 */
    @Column(name = "SUMMARY", unique = false, nullable = false, insertable = true, updatable = true, length = 512)
    private String summary;
    /** LINK - 外连 */
    @Column(name = "LINK", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String link;
    /** RELEASE_VERSION - release 版本 */
    @Column(name = "RELEASE_VERSION", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String releaseVersion;
    /** CONT - 内容 */
    @Column(name = "CONT", unique = false, nullable = false, insertable = true, updatable = true, length = 2147483647)
    private byte[] cont;
	/** IS_AVAILABLE - 是否可用 */
	@Column(name = "IS_AVAILABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private String isAvailable;
	/** ORDERBY - 顺序 */
	@Column(name = "ORDERBY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long orderby;

    public void setTitle(String value) {
		this.title = value;
	}

    public String getTitle() {
		return this.title;
	}

    public void setSummary(String value) {
		this.summary = value;
	}

    public String getSummary() {
		return this.summary;
	}

    public void setLink(String value) {
		this.link = value;
	}

    public String getLink() {
		return this.link;
	}

    public void setReleaseVersion(String value) {
		this.releaseVersion = value;
	}

    public String getReleaseVersion() {
		return this.releaseVersion;
	}

    public void setCont(byte[] value) {
		this.cont = value;
	}

    public byte[] getCont() {
		return this.cont;
	}

	public void setIsAvailable(String value) {
		this.isAvailable = value;
	}

	public String getIsAvailable() {
		return this.isAvailable;
	}

	public void setOrderby(Long value) {
		this.orderby = value;
	}

	public Long getOrderby() {
		return this.orderby;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("TITLE", getTitle())
			.append("SUMMARY",getSummary())
			.append("LINK",getLink())
			.append("RELEASE_VERSION",getReleaseVersion())
			.append("CONT",getCont())
				.append("IS_AVAILABLE", getIsAvailable())
				.append("ORDERBY", getOrderby())
				.append("CREATE_DATE", getCreateDate())
			.append("CREATE_BY",getCreateBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("VERSION",getVersion())
			.toString();
	}

	public void setContStr(String value) {
		if(value == null){
			this.cont = null;
		}else{
			try {
				this.cont = value.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace(); //NOSONAR
			}
		}
	}

	public String getContStr() {
		try {
			return this.cont == null ? "" : new String(this.cont, "UTF-8");
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
		if(!(obj instanceof SysDoc)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysDoc other = (SysDoc)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

