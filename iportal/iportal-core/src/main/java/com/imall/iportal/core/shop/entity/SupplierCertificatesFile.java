
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.ParseException;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_supplier_certificates_file" )
public class SupplierCertificatesFile extends BaseEntity<Long>{

	public static final String SUPPLIER_ID = "supplierId";
	public static final String CERTIFICATES_TYPE = "certificatesType";
	public static final String CERTIFICATES_NUM = "certificatesNum";
	public static final String CERTIFICATES_VALIDITY = "certificatesValidity";

    /** SUPPLIER_ID - 供应商 ID */
    @Column(name = "SUPPLIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long supplierId;
    /** CERTIFICATES_TYPE - 资质 类型 */
    @Column(name = "CERTIFICATES_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String certificatesType;
    /** CERTIFICATES_NUM - 资质 编号 */
    @Column(name = "CERTIFICATES_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String certificatesNum;
    /** CERTIFICATES_VALIDITY - 资质 有效期 */
    @Column(name = "CERTIFICATES_VALIDITY", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date certificatesValidity;

    public void setSupplierId(Long value) {
		this.supplierId = value;
	}

    public Long getSupplierId() {
		return this.supplierId;
	}

    public void setCertificatesType(String value) {
		this.certificatesType = value;
	}

    public String getCertificatesType() {
		return this.certificatesType;
	}

    public void setCertificatesNum(String value) {
		this.certificatesNum = value;
	}

    public String getCertificatesNum() {
		return this.certificatesNum;
	}

    public void setCertificatesValidity(java.util.Date value) {
		this.certificatesValidity = value;
	}

    public java.util.Date getCertificatesValidity() {
		return this.certificatesValidity;
	}

	public String getCertificatesValidityString() {
		return DateTimeUtils.convertDateToString(this.getCertificatesValidity());
	}

	public void setCertificatesValidityString(String value) {
		try {
			this.setCertificatesValidity(DateTimeUtils.convertStringToDate(value));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SUPPLIER_ID",getSupplierId())
			.append("CERTIFICATES_TYPE",getCertificatesType())
			.append("CERTIFICATES_NUM",getCertificatesNum())
			.append("CERTIFICATES_VALIDITY",getCertificatesValidity())
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
		if(!(obj instanceof SupplierCertificatesFile)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SupplierCertificatesFile other = (SupplierCertificatesFile)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

