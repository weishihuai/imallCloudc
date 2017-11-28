
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
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_drug_stop_sale_notice" )
public class DrugStopSaleNotice extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String STOP_SALE_NUM = "stopSaleNum";
	public static final String DOC_MAKER_NM = "docMakerNm";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String MAKE_TIME = "makeTime";
	public static final String STOP_SALE_DATE = "stopSaleDate";
	public static final String QUALITY_STATE = "qualityState";
	public static final String STOP_SALE_SUGGEST = "stopSaleSuggest";

	/** SHOP_ID - 门店 ID */
	@Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long shopId;
	/** STOP_SALE_NUM - 停售 单号 */
	@Column(name = "STOP_SALE_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String stopSaleNum;
	/** DOC_MAKER_NM - 制单人 姓名 */
	@Column(name = "DOC_MAKER_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String docMakerNm;
	/** APPROVE_MAN_ID - 复核人 ID */
	@Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long approveManId;
	/** MAKE_TIME - 制单 时间 */
	@Column(name = "MAKE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date makeTime;
	/** STOP_SALE_DATE - 停售 日期 */
	@Column(name = "STOP_SALE_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date stopSaleDate;
	/** QUALITY_STATE - 质量 状况 */
	@Column(name = "QUALITY_STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	private java.lang.String qualityState;
	/** STOP_SALE_SUGGEST - 停售 意见 */
	@Column(name = "STOP_SALE_SUGGEST", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	private java.lang.String stopSaleSuggest;

	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

	public java.lang.Long getShopId() {
		return this.shopId;
	}

	public void setStopSaleNum(java.lang.String value) {
		this.stopSaleNum = value;
	}

	public java.lang.String getStopSaleNum() {
		return this.stopSaleNum;
	}

	public void setDocMakerNm(java.lang.String value) {
		this.docMakerNm = value;
	}

	public java.lang.String getDocMakerNm() {
		return this.docMakerNm;
	}

	public void setApproveManId(java.lang.Long value) {
		this.approveManId = value;
	}

	public java.lang.Long getApproveManId() {
		return this.approveManId;
	}

	public void setMakeTime(java.util.Date value) {
		this.makeTime = value;
	}

	public java.util.Date getMakeTime() {
		return this.makeTime;
	}

	public void setStopSaleDate(java.util.Date value) {
		this.stopSaleDate = value;
	}

	public java.util.Date getStopSaleDate() {
		return this.stopSaleDate;
	}

	public void setQualityState(java.lang.String value) {
		this.qualityState = value;
	}

	public java.lang.String getQualityState() {
		return this.qualityState;
	}

	public void setStopSaleSuggest(java.lang.String value) {
		this.stopSaleSuggest = value;
	}

	public java.lang.String getStopSaleSuggest() {
		return this.stopSaleSuggest;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("ID",getId())
				.append("SHOP_ID",getShopId())
				.append("STOP_SALE_NUM",getStopSaleNum())
				.append("DOC_MAKER_NM",getDocMakerNm())
				.append("APPROVE_MAN_ID",getApproveManId())
				.append("MAKE_TIME",getMakeTime())
				.append("STOP_SALE_DATE",getStopSaleDate())
				.append("QUALITY_STATE",getQualityState())
				.append("STOP_SALE_SUGGEST",getStopSaleSuggest())
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
		if(!(obj instanceof DrugStopSaleNotice)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DrugStopSaleNotice other = (DrugStopSaleNotice)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}

	public String getStopSaleDateStr() {
		return DateTimeUtils.convertDateToString(getStopSaleDate());
	}
	public void setStopSaleDateStr(String value) throws ParseException {
		setStopSaleDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getMakeTimeStr() {
		return DateTimeUtils.convertDateToString(getMakeTime());
	}
	public void setMakeTimeStr(String value) throws ParseException {
		setMakeTime(DateTimeUtils.convertStringToDate(value));
	}
}

