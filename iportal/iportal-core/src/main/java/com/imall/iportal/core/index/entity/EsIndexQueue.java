
package com.imall.iportal.core.index.entity;

import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "t_pt_es_index_queue")
public class EsIndexQueue extends BaseEntity<Long> {
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String OBJECT_ID = "objectId";
	public static final String INDEX_TYPE_CODE = "indexTypeCode";
	public static final String EXECUTE_STATE = "executeState";
	public static final String PERFORMER_IP = "performerIp";

	/**
	 * sysObjectId	   对象ID
	 */
	@Column(name = "OBJECT_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	private Long objectId;
	/**
	 * indexTypeCode	  索引类型
	 */
	@Column(name = "INDEX_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 24)
	private String indexTypeCode;
	/**
	 * executeState	  执行状态
	 */
	@Column(name = "EXECUTE_STATE", unique = false, nullable = false, insertable = true, updatable = true, length = 24)
	private String executeState;
	/**
	 * performerIp	  执行者IP
	 */
	@Column(name = "PERFORMER_IP", unique = false, nullable = true, insertable = true, updatable = true, length = 24)
	private String performerIp;


	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getIndexTypeCode() {
		return indexTypeCode;
	}

	public void setIndexTypeCode(String indexTypeCode) {
		this.indexTypeCode = indexTypeCode;
	}

	public String getExecuteState() {
		return executeState;
	}

	public void setExecuteState(String executeState) {
		this.executeState = executeState;
	}

	public String getPerformerIp() {
		return performerIp;
	}

	public void setPerformerIp(String performerIp) {
		this.performerIp = performerIp;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("ID",getId())
				.append("INDEX_TYPE_CODE",getIndexTypeCode())
				.append("OBJECT_ID",getObjectId())
				.append("EXECUTE_STATE",getExecuteState())
				.append("PERFORMER_IP",getPerformerIp())
				.toString();
	}

	public int hashCode() {
		return new org.apache.commons.lang3.builder.HashCodeBuilder()
				.append(getId())
				.toHashCode();
	}

	public boolean equals(Object obj) {
		if(this.getId() == null){
			return false;
		}
		if(!(obj instanceof EsIndexQueue)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		EsIndexQueue other = (EsIndexQueue)obj;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}

