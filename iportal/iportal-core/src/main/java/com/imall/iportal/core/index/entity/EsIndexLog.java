
package com.imall.iportal.core.index.entity;

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
 * T_PT_ES_INDEX_LOG【索引日志】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_es_index_log" )
public class EsIndexLog extends BaseEntity<Long>{

	public static final String LOG_CONT = "logCont";
	public static final String INDEX_MANAGE_ID = "indexManageId";

    /** LOG_CONT - logCont */
	@Lob
    @Column(name = "LOG_CONT", unique = false, nullable = false, insertable = true, updatable = true, length = 2147483647)
    private byte[] logCont;
    /** INDEX_MANAGE_ID - indexManageId */
    @Column(name = "INDEX_MANAGE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long indexManageId;

    public void setLogCont(byte[] value) {
		this.logCont = value;
	}

    public byte[] getLogCont() {
		return this.logCont;
	}

    public void setIndexManageId(Long value) {
		this.indexManageId = value;
	}

    public Long getIndexManageId() {
		return this.indexManageId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("LOG_CONT",getLogCont())
			.append("INDEX_MANAGE_ID",getIndexManageId())
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
		if(!(obj instanceof EsIndexLog)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		EsIndexLog other = (EsIndexLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public void setLogContString(String value) {
		if(value == null){
			this.logCont = null;
		}else{
			try {
				this.logCont = value.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	public String getLogContString() {
		try {
			return this.logCont == null ? "" : new String(this.logCont, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
}

