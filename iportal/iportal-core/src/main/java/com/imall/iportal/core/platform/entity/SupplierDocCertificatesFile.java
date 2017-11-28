
package com.imall.iportal.core.platform.entity;

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
@Table(name = "t_ptfm_supplier_doc_certificates_file" )
public class SupplierDocCertificatesFile extends BaseEntity<Long>{

	public static final String SUPPLIER_DOC_ID = "supplierDocId";
	public static final String CERTIFICATES_TYPE = "certificatesType";
	public static final String CERTIFICATES_NUM = "certificatesNum";
	public static final String CERTIFICATES_VALIDITY = "certificatesValidity";

    /** SUPPLIER_DOC_ID - 供应商 档案 ID */
    @Column(name = "SUPPLIER_DOC_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long supplierDocId;
    /** CERTIFICATES_TYPE - 资质 类型 */
    @Column(name = "CERTIFICATES_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String certificatesType;
    /** CERTIFICATES_NUM - 资质 编号 */
    @Column(name = "CERTIFICATES_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String certificatesNum;
    /** CERTIFICATES_VALIDITY - 资质 有效期 */
    @Column(name = "CERTIFICATES_VALIDITY", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date certificatesValidity;

    public void setSupplierDocId(java.lang.Long value) {
		this.supplierDocId = value;
	}

    public java.lang.Long getSupplierDocId() {
		return this.supplierDocId;
	}

    public void setCertificatesType(java.lang.String value) {
		this.certificatesType = value;
	}

    public java.lang.String getCertificatesType() {
		return this.certificatesType;
	}

    public void setCertificatesNum(java.lang.String value) {
		this.certificatesNum = value;
	}

    public java.lang.String getCertificatesNum() {
		return this.certificatesNum;
	}

    public void setCertificatesValidity(java.util.Date value) {
		this.certificatesValidity = value;
	}

    public java.util.Date getCertificatesValidity() {
		return this.certificatesValidity;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SUPPLIER_DOC_ID",getSupplierDocId())
			.append("CERTIFICATES_TYPE",getCertificatesType())
			.append("CERTIFICATES_NUM",getCertificatesNum())
			.append("CERTIFICATES_VALIDITY",getCertificatesValidity())
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
		if(!(obj instanceof SupplierDocCertificatesFile)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SupplierDocCertificatesFile other = (SupplierDocCertificatesFile)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

