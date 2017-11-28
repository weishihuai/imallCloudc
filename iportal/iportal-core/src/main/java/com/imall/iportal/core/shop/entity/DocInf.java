
package com.imall.iportal.core.shop.entity;

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
@Table(name = "t_shp_doc_inf" )
public class DocInf extends BaseEntity<Long>{

	public static final String SEQNUM = "seqnum";
	public static final String TABLE_NM = "tableNm";
	public static final String DOWNLOAD_ADDR = "downloadAddr";
	public static final String DOC_TYPE = "docType";

    /** SEQNUM - 序号 */
    @Column(name = "SEQNUM", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double seqnum;
    /** TABLE_NM - 表格 名称 */
    @Column(name = "TABLE_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String tableNm;
    /** DOWNLOAD_ADDR - 下载 地址 */
    @Column(name = "DOWNLOAD_ADDR", unique = false, nullable = false, insertable = true, updatable = true, length = 256)
    private String downloadAddr;
    /** DOC_TYPE - 文档 类型 */
    @Column(name = "DOC_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String docType;

    public void setSeqnum(Double value) {
		this.seqnum = value;
	}

    public Double getSeqnum() {
		return this.seqnum;
	}

    public void setTableNm(String value) {
		this.tableNm = value;
	}

    public String getTableNm() {
		return this.tableNm;
	}

    public void setDownloadAddr(String value) {
		this.downloadAddr = value;
	}

    public String getDownloadAddr() {
		return this.downloadAddr;
	}

    public void setDocType(String value) {
		this.docType = value;
	}

    public String getDocType() {
		return this.docType;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SEQNUM",getSeqnum())
			.append("TABLE_NM",getTableNm())
			.append("DOWNLOAD_ADDR",getDownloadAddr())
			.append("DOC_TYPE",getDocType())
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
		if(!(obj instanceof DocInf)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DocInf other = (DocInf)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

