
package com.imall.iportal.core.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.text.ParseException;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_drug_release_notice" )
public class DrugReleaseNotice extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String RELEASE_NUM = "releaseNum";
	public static final String DOC_MAKER_NM = "docMakerNm";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String DOC_MAKE_TIME = "docMakeTime";
	public static final String RELEASE_DATE = "releaseDate";
	public static final String QUALITY_STATE = "qualityState";
	public static final String RELEASE_SUGGEST = "releaseSuggest";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** RELEASE_NUM - 解停 单号 */
    @Column(name = "RELEASE_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String releaseNum;
    /** DOC_MAKER_ID - 制单人 名称*/
    @Column(name = "DOC_MAKER_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String docMakerNm;
    /** APPROVE_MAN_ID - 审核人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long approveManId;
    /** DOC_MAKE_TIME - 制单 时间 */
    @Column(name = "DOC_MAKE_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date docMakeTime;
    /** RELEASE_DATE - 解停 日期 */
    @Column(name = "RELEASE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date releaseDate;
    /** QUALITY_STAT - 质量 状况 */
    @Column(name = "QUALITY_STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String qualityState;
    /** RELEASE_SUGGEST - 解停 意见 */
    @Column(name = "RELEASE_SUGGEST", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String releaseSuggest;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setReleaseNum(String value) {
		this.releaseNum = value;
	}

    public String getReleaseNum() {
		return this.releaseNum;
	}

    public void setDocMakerNm(String value) {
		this.docMakerNm = value;
	}

    public String getDocMakerNm() {
		return this.docMakerNm;
	}

    public void setApproveManId(Long value) {
		this.approveManId = value;
	}

    public Long getApproveManId() {
		return this.approveManId;
	}

    public void setDocMakeTime(java.util.Date value) {
		this.docMakeTime = value;
	}

    public java.util.Date getDocMakeTime() {
		return this.docMakeTime;
	}

    public void setReleaseDate(java.util.Date value) {
		this.releaseDate = value;
	}

    public java.util.Date getReleaseDate() {
		return this.releaseDate;
	}

	public String getQualityState() {
		return qualityState;
	}

	public void setQualityState(String qualityState) {
		this.qualityState = qualityState;
	}

	public void setReleaseSuggest(String value) {
		this.releaseSuggest = value;
	}

    public String getReleaseSuggest() {
		return this.releaseSuggest;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("RELEASE_NUM",getReleaseNum())
			.append("DOC_MAKER_NM", getDocMakerNm())
			.append("APPROVE_MAN_ID",getApproveManId())
			.append("DOC_MAKE_TIME",getDocMakeTime())
			.append("RELEASE_DATE",getReleaseDate())
			.append("QUALITY_STATE",getQualityState())
			.append("RELEASE_SUGGEST",getReleaseSuggest())
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
		if(!(obj instanceof DrugReleaseNotice)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DrugReleaseNotice other = (DrugReleaseNotice)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getReleaseDateStr() {
		return DateTimeUtils.convertDateToString(getReleaseDate());
	}
	public void setReleaseDateStr(String value) throws ParseException {
		setReleaseDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getDocMakeTimeStr() {
		return DateTimeUtils.convertDateToString(getDocMakeTime());
	}
	public void setDocMakeTimeStr(String value) throws ParseException {
		setDocMakeTime(DateTimeUtils.convertStringToDate(value));
	}
}

