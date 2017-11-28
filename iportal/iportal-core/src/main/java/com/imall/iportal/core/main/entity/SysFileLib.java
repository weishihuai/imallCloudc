
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
 * T_PT_SYS_FILE_LIB【系统 文件 库】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_file_lib" )
public class SysFileLib extends BaseEntity<Long>{

	public static final String ORG_ID = "orgId";
	public static final String SYS_FILE_ID = "sysFileId";
	public static final String FILE_NM = "fileNm";
	public static final String FILE_TYPE_CODE = "fileTypeCode";
	public static final String FILE_SIZE = "fileSize";
	public static final String FILE_DATE = "fileDate";
	public static final String FILE_CATEGORY_ID = "fileCategoryId";
	public static final String IS_DELETE = "isDelete";

    /** ORG_ID - orgId */
    @Column(name = "ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long orgId;
    /** SYS_FILE_ID - sysFileId */
    @Column(name = "SYS_FILE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 60)
    private String sysFileId;
    /** FILE_NM - fileNm */
    @Column(name = "FILE_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String fileNm;
    /** FILE_TYPE_CODE - fileTypeCode */
    @Column(name = "FILE_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String fileTypeCode;
    /** FILE_SIZE - fileSize */
    @Column(name = "FILE_SIZE", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long fileSize;
    /** FILE_DATE - fileDate */
    @Column(name = "FILE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date fileDate;
    /** FILE_CATEGORY_ID - fileCategoryId */
    @Column(name = "FILE_CATEGORY_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long fileCategoryId;
    /** IS_DELETE - isDelete */
    @Column(name = "IS_DELETE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isDelete;

    public void setOrgId(Long value) {
		this.orgId = value;
	}

    public Long getOrgId() {
		return this.orgId;
	}

    public void setSysFileId(String value) {
		this.sysFileId = value;
	}

    public String getSysFileId() {
		return this.sysFileId;
	}

    public void setFileNm(String value) {
		this.fileNm = value;
	}

    public String getFileNm() {
		return this.fileNm;
	}

    public void setFileTypeCode(String value) {
		this.fileTypeCode = value;
	}

    public String getFileTypeCode() {
		return this.fileTypeCode;
	}

    public void setFileSize(Long value) {
		this.fileSize = value;
	}

    public Long getFileSize() {
		return this.fileSize;
	}

    public void setFileDate(java.util.Date value) {
		this.fileDate = value;
	}

    public java.util.Date getFileDate() {
		return this.fileDate;
	}

    public void setFileCategoryId(Long value) {
		this.fileCategoryId = value;
	}

    public Long getFileCategoryId() {
		return this.fileCategoryId;
	}

    public void setIsDelete(String value) {
		this.isDelete = value;
	}

    public String getIsDelete() {
		return this.isDelete;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("ORG_ID",getOrgId())
			.append("SYS_FILE_ID",getSysFileId())
			.append("FILE_NM",getFileNm())
			.append("FILE_TYPE_CODE",getFileTypeCode())
			.append("FILE_SIZE",getFileSize())
			.append("FILE_DATE",getFileDate())
			.append("FILE_CATEGORY_ID",getFileCategoryId())
			.append("IS_DELETE",getIsDelete())
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
		if(!(obj instanceof SysFileLib)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysFileLib other = (SysFileLib)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

