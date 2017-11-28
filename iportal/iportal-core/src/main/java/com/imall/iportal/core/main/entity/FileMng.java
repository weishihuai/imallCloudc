
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
@Table(name = "t_pt_file_mng" )
public class FileMng extends BaseEntity<Long>{

	public static final String OBJECT_TYPE_CODE = "objectTypeCode";
	public static final String OBJECT_ID = "objectId";
	public static final String FILE_TYPE_CODE = "fileTypeCode";
	public static final String CUSTOM_TYPE_CODE = "customTypeCode";
	public static final String SYS_FILE_LIB_ID = "sysFileLibId";
	public static final String FILE_ID = "fileId";

	/** OBJECT_TYPE_CODE - 对象 类型 代码 */
    @Column(name = "OBJECT_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String objectTypeCode;
    /** OBJECT_ID - 对象 ID */
    @Column(name = "OBJECT_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long objectId;
    /** FILE_TYPE_CODE - 文件 类型 代码 */
    @Column(name = "FILE_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String fileTypeCode;
    /** CUSTOM_TYPE_CODE - 自定义 类型 代码 */
    @Column(name = "CUSTOM_TYPE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String customTypeCode;
    /** SYS_FILE_LIB_ID - 系统 文件 库 ID */
    @Column(name = "SYS_FILE_LIB_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long sysFileLibId;
    /** FILE_ID - 文件 ID */
    @Column(name = "FILE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String fileId;

	public void setObjectTypeCode(java.lang.String value) {
		this.objectTypeCode = value;
	}

    public java.lang.String getObjectTypeCode() {
		return this.objectTypeCode;
	}

    public void setObjectId(java.lang.Long value) {
		this.objectId = value;
	}

    public java.lang.Long getObjectId() {
		return this.objectId;
	}

    public void setFileTypeCode(java.lang.String value) {
		this.fileTypeCode = value;
	}

    public java.lang.String getFileTypeCode() {
		return this.fileTypeCode;
	}

    public void setCustomTypeCode(java.lang.String value) {
		this.customTypeCode = value;
	}

    public java.lang.String getCustomTypeCode() {
		return this.customTypeCode;
	}

    public void setSysFileLibId(java.lang.Long value) {
		this.sysFileLibId = value;
	}

    public java.lang.Long getSysFileLibId() {
		return this.sysFileLibId;
	}

    public void setFileId(java.lang.String value) {
		this.fileId = value;
	}

    public java.lang.String getFileId() {
		return this.fileId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("OBJECT_TYPE_CODE",getObjectTypeCode())
			.append("OBJECT_ID",getObjectId())
			.append("FILE_TYPE_CODE",getFileTypeCode())
			.append("CUSTOM_TYPE_CODE",getCustomTypeCode())
			.append("SYS_FILE_LIB_ID",getSysFileLibId())
			.append("FILE_ID",getFileId())
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
		if(!(obj instanceof FileMng)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		FileMng other = (FileMng)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

