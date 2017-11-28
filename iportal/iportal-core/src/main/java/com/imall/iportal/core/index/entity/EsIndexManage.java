
package com.imall.iportal.core.index.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * T_PT_ES_INDEX_MANAGE【索引管理】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_es_index_manage" )
public class EsIndexManage extends BaseEntity<Long>{

	public static final String INDEX_TYPE_CODE = "indexTypeCode";
	public static final String STATE = "state";
	public static final String INDEX_NAME = "indexName";
	public static final String REMAIN_NUM = "remainNum";
	public static final String REMOTE_URL = "remoteUrl";

    /** INDEX_TYPE_CODE - indexTypeCode */
    @Column(name = "INDEX_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 24)
    private String indexTypeCode;
    /** STATE - state */
    @Column(name = "STATE", unique = false, nullable = false, insertable = true, updatable = true, length = 24)
    private String state;
    /** INDEX_NAME - indexName */
    @Column(name = "INDEX_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String indexName;
    /** REMAIN_NUM - remainNum */
    @Column(name = "REMAIN_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long remainNum;
    /** REMOTE_URL - remoteUrl */
    @Column(name = "REMOTE_URL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
    private String remoteUrl;

	/** APP_NAME - appName */
	@Column(name = "APP_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	private String appName;
	/** APP_KEY - appKey */
	@Column(name = "APP_KEY", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	private String appKey;
	/** APP_SECRET - appSecret */
	@Column(name = "APP_SECRET", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	private String appSecret;

    public void setIndexTypeCode(String value) {
		this.indexTypeCode = value;
	}

    public String getIndexTypeCode() {
		return this.indexTypeCode;
	}

    public void setState(String value) {
		this.state = value;
	}

    public String getState() {
		return this.state;
	}

    public void setIndexName(String value) {
		this.indexName = value;
	}

    public String getIndexName() {
		return this.indexName;
	}

    public void setRemainNum(Long value) {
		this.remainNum = value;
	}

    public Long getRemainNum() {
		return this.remainNum;
	}

    public void setRemoteUrl(String value) {
		this.remoteUrl = value;
	}

    public String getRemoteUrl() {
		return this.remoteUrl;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("INDEX_TYPE_CODE",getIndexTypeCode())
			.append("STATE",getState())
			.append("INDEX_NAME",getIndexName())
			.append("REMAIN_NUM",getRemainNum())
			.append("REMOTE_URL",getRemoteUrl())
			.append("CREATE_DATE",getCreateDate())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("VERSION",getVersion())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("CREATE_BY",getCreateBy())
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
		if(!(obj instanceof EsIndexManage)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		EsIndexManage other = (EsIndexManage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

