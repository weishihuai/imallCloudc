
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
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
@Table(name = "t_shp_staff_health_doc_inf" )
public class StaffHealthDocInf extends BaseEntity<Long>{

	public static final String STAFF_HEALTH_DOC_ID = "staffHealthDocId";
	public static final String CHECK_NUM = "checkNum";
	public static final String CHECK_DATE = "checkDate";
	public static final String CHECK_ORG = "checkOrg";
	public static final String CHECK_PRJ = "checkPrj";
	public static final String CHECK_RESULT = "checkResult";
	public static final String TAKE_MEASURES = "takeMeasures";
	public static final String REMARK = "remark";

    /** STAFF_HEALTH_DOC_ID - 员工 健康 档案 ID */
    @Column(name = "STAFF_HEALTH_DOC_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long staffHealthDocId;
    /** CHECK_NUM - 检查 编号 */
    @Column(name = "CHECK_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String checkNum;
    /** CHECK_DATE - 检查 日期 */
    @Column(name = "CHECK_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date checkDate;
    /** CHECK_ORG - 检查 机构 */
    @Column(name = "CHECK_ORG", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String checkOrg;
    /** CHECK_PRJ - 检查 项目 */
    @Column(name = "CHECK_PRJ", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String checkPrj;
    /** CHECK_RESULT - 检查 结果 */
    @Column(name = "CHECK_RESULT", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String checkResult;
    /** TAKE_MEASURES - 采取 措施 */
    @Column(name = "TAKE_MEASURES", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String takeMeasures;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String remark;

    public void setStaffHealthDocId(java.lang.Long value) {
		this.staffHealthDocId = value;
	}

    public java.lang.Long getStaffHealthDocId() {
		return this.staffHealthDocId;
	}

    public void setCheckNum(java.lang.String value) {
		this.checkNum = value;
	}

    public java.lang.String getCheckNum() {
		return this.checkNum;
	}

    public void setCheckDate(java.util.Date value) throws ParseException {
		this.checkDate = value;
	}
    public void setCheckDateString(String value) throws ParseException {
		this.checkDate = StringUtils.isNotBlank(value)? DateTimeUtils.convertStringToDate(value):null;
	}

    public java.util.Date getCheckDate() {
		return this.checkDate;
	}
    public String getCheckDateString() {
		return this.checkDate!=null?DateTimeUtils.convertDateToString(this.checkDate):null;
	}

    public void setCheckOrg(java.lang.String value) {
		this.checkOrg = value;
	}

    public java.lang.String getCheckOrg() {
		return this.checkOrg;
	}

    public void setCheckPrj(java.lang.String value) {
		this.checkPrj = value;
	}

    public java.lang.String getCheckPrj() {
		return this.checkPrj;
	}

    public void setCheckResult(java.lang.String value) {
		this.checkResult = value;
	}

    public java.lang.String getCheckResult() {
		return this.checkResult;
	}

    public void setTakeMeasures(java.lang.String value) {
		this.takeMeasures = value;
	}

    public java.lang.String getTakeMeasures() {
		return this.takeMeasures;
	}

    public void setRemark(java.lang.String value) {
		this.remark = value;
	}

    public java.lang.String getRemark() {
		return this.remark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("STAFF_HEALTH_DOC_ID",getStaffHealthDocId())
			.append("CHECK_NUM",getCheckNum())
			.append("CHECK_DATE",getCheckDate())
			.append("CHECK_ORG",getCheckOrg())
			.append("CHECK_PRJ",getCheckPrj())
			.append("CHECK_RESULT",getCheckResult())
			.append("TAKE_MEASURES",getTakeMeasures())
			.append("REMARK",getRemark())
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
		if(!(obj instanceof StaffHealthDocInf)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		StaffHealthDocInf other = (StaffHealthDocInf)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

