
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
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_json_object_engine" )
public class JsonObjectEngine extends BaseEntity<Long> {

	public static final String JSON_OBJECT_TYPE_CODE = "jsonObjectTypeCode";
	public static final String JSON_OBJECT_ID = "jsonObjectId";
	public static final String JSON_OBJECT_VALUE = "jsonObjectValue";

    /** JSON_OBJECT_TYPE_CODE - jsonObjectTypeCode */
    @Column(name = "JSON_OBJECT_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String jsonObjectTypeCode;
    /** JSON_OBJECT_ID - jsonObjectId */
    @Column(name = "JSON_OBJECT_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long jsonObjectId;
    /** JSON_OBJECT_VALUE - jsonObjectValue */
    @Column(name = "JSON_OBJECT_VALUE", unique = false, nullable = false, insertable = true, updatable = true, length = 2147483647)
    private byte[] jsonObjectValue;

    public void setJsonObjectTypeCode(String value) {
		this.jsonObjectTypeCode = value;
	}

    public String getJsonObjectTypeCode() {
		return this.jsonObjectTypeCode;
	}

    public void setJsonObjectId(Long value) {
		this.jsonObjectId = value;
	}

    public Long getJsonObjectId() {
		return this.jsonObjectId;
	}

    public void setJsonObjectValue(byte[] value) {
		this.jsonObjectValue = value;
	}

    public byte[] getJsonObjectValue() {
		return this.jsonObjectValue;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("CREATE_BY",getCreateBy())
			.append("CREATE_DATE",getCreateDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("VERSION",getVersion())
			.append("JSON_OBJECT_TYPE_CODE",getJsonObjectTypeCode())
			.append("JSON_OBJECT_ID",getJsonObjectId())
			.append("JSON_OBJECT_VALUE",getJsonObjectValue())
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
		if(!(obj instanceof JsonObjectEngine)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		JsonObjectEngine other = (JsonObjectEngine)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public void setJsonObjectValueStr(String value) {
		if(value == null){
			this.jsonObjectValue = null;
		}else{
			try {
				this.jsonObjectValue = value.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace(); //NOSONAR
			}
		}
	}

	public String getJsonObjectValueStr() {
		try {
			return this.jsonObjectValue == null ? "" : new String(this.jsonObjectValue, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); //NOSONAR
			return "";
		}
	}
}

