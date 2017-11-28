
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
@Table(name = "t_shp_complain_record" )
public class ComplainRecord extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String CUSTOMER_NAME = "customerName";
	public static final String COMPLAIN_DATE = "complainDate";
	public static final String MOBILE = "mobile";
	public static final String ADDR = "addr";
	public static final String COMPLAIN_CONT = "complainCont";
	public static final String GOODS_CODE = "goodsCode";
	public static final String GOODS_NM = "goodsNm";
	public static final String GOODS_PINYIN = "goodsPinyin";
	public static final String SPEC = "spec";
	public static final String DOSAGE_FORM = "dosageForm";
	public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
	public static final String APPROVAL_NUMBER = "approvalNumber";
	public static final String BATCH = "batch";
	public static final String VALID_DATE = "validDate";
	public static final String QUANTITY = "quantity";
	public static final String SURVEY_CONDITION = "surveyCondition";
	public static final String SURVEY_MAN_NAME = "surveyManName";
	public static final String SURVEY_DATE = "surveyDate";
	public static final String SUGGEST = "suggest";
	public static final String PROCESS_MEASURE = "processMeasure";
	public static final String PROCESS_MAN_NAME = "processManName";
	public static final String PROCESS_DATE = "processDate";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** CUSTOMER_NAME - 顾客 姓名 */
    @Column(name = "CUSTOMER_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String customerName;
    /** COMPLAIN_DATE - 投诉 日期 */
    @Column(name = "COMPLAIN_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date complainDate;
    /** MOBILE - 手机 */
    @Column(name = "MOBILE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String mobile;
    /** ADDR - 地址 */
    @Column(name = "ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String addr;
    /** COMPLAIN_CONT - 投诉 内容 */
    @Column(name = "COMPLAIN_CONT", unique = false, nullable = false, insertable = true, updatable = true, length = 256)
    private java.lang.String complainCont;
    /** GOODS_CODE - 商品 编码 */
    @Column(name = "GOODS_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String goodsCode;
    /** GOODS_NM - 商品 名称 */
    @Column(name = "GOODS_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private java.lang.String goodsNm;
    /** GOODS_PINYIN - 商品 拼音 */
    @Column(name = "GOODS_PINYIN", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String goodsPinyin;
    /** SPEC - 规格 */
    @Column(name = "SPEC", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String spec;
    /** DOSAGE_FORM - 剂型 */
    @Column(name = "DOSAGE_FORM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String dosageForm;
    /** PRODUCE_MANUFACTURER - 生产厂商 */
    @Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String produceManufacturer;
    /** APPROVAL_NUMBER - 批准文号 */
    @Column(name = "APPROVAL_NUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String approvalNumber;
    /** BATCH - 批号 */
    @Column(name = "BATCH", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String batch;
    /** VALID_DATE - 有效期至 */
    @Column(name = "VALID_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date validDate;
    /** QUANTITY - 数量 */
    @Column(name = "QUANTITY", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    private java.lang.Integer quantity;
    /** SURVEY_CONDITION - 调查 情况 */
    @Column(name = "SURVEY_CONDITION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String surveyCondition;
    /** SURVEY_MAN_NAME - 调查 人 姓名 */
    @Column(name = "SURVEY_MAN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String surveyManName;
    /** SURVEY_DATE - 调查 日期 */
    @Column(name = "SURVEY_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date surveyDate;
    /** SUGGEST - 建议 */
    @Column(name = "SUGGEST", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String suggest;
    /** PROCESS_MEASURE - 处理 措施 */
    @Column(name = "PROCESS_MEASURE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String processMeasure;
    /** PROCESS_MAN_NAME - 处理 人 姓名 */
    @Column(name = "PROCESS_MAN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String processManName;
    /** PROCESS_DATE - 处理 日期 */
    @Column(name = "PROCESS_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date processDate;

    public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

    public void setCustomerName(java.lang.String value) {
		this.customerName = value;
	}

    public java.lang.String getCustomerName() {
		return this.customerName;
	}

    public void setComplainDate(java.util.Date value) {
		this.complainDate = value;
	}

    public java.util.Date getComplainDate() {
		return this.complainDate;
	}

    public void setMobile(java.lang.String value) {
		this.mobile = value;
	}

    public java.lang.String getMobile() {
		return this.mobile;
	}

    public void setAddr(java.lang.String value) {
		this.addr = value;
	}

    public java.lang.String getAddr() {
		return this.addr;
	}

    public void setComplainCont(java.lang.String value) {
		this.complainCont = value;
	}

    public java.lang.String getComplainCont() {
		return this.complainCont;
	}

    public void setGoodsCode(java.lang.String value) {
		this.goodsCode = value;
	}

    public java.lang.String getGoodsCode() {
		return this.goodsCode;
	}

    public void setGoodsNm(java.lang.String value) {
		this.goodsNm = value;
	}

    public java.lang.String getGoodsNm() {
		return this.goodsNm;
	}

    public void setGoodsPinyin(java.lang.String value) {
		this.goodsPinyin = value;
	}

    public java.lang.String getGoodsPinyin() {
		return this.goodsPinyin;
	}

    public void setSpec(java.lang.String value) {
		this.spec = value;
	}

    public java.lang.String getSpec() {
		return this.spec;
	}

    public void setDosageForm(java.lang.String value) {
		this.dosageForm = value;
	}

    public java.lang.String getDosageForm() {
		return this.dosageForm;
	}

    public void setProduceManufacturer(java.lang.String value) {
		this.produceManufacturer = value;
	}

    public java.lang.String getProduceManufacturer() {
		return this.produceManufacturer;
	}

    public void setApprovalNumber(java.lang.String value) {
		this.approvalNumber = value;
	}

    public java.lang.String getApprovalNumber() {
		return this.approvalNumber;
	}

    public void setBatch(java.lang.String value) {
		this.batch = value;
	}

    public java.lang.String getBatch() {
		return this.batch;
	}

    public void setValidDate(java.util.Date value) {
		this.validDate = value;
	}

    public java.util.Date getValidDate() {
		return this.validDate;
	}

    public void setQuantity(java.lang.Integer value) {
		this.quantity = value;
	}

    public java.lang.Integer getQuantity() {
		return this.quantity;
	}

    public void setSurveyCondition(java.lang.String value) {
		this.surveyCondition = value;
	}

    public java.lang.String getSurveyCondition() {
		return this.surveyCondition;
	}

    public void setSurveyManName(java.lang.String value) {
		this.surveyManName = value;
	}

    public java.lang.String getSurveyManName() {
		return this.surveyManName;
	}

    public void setSurveyDate(java.util.Date value) {
		this.surveyDate = value;
	}

    public java.util.Date getSurveyDate() {
		return this.surveyDate;
	}

    public void setSuggest(java.lang.String value) {
		this.suggest = value;
	}

    public java.lang.String getSuggest() {
		return this.suggest;
	}

    public void setProcessMeasure(java.lang.String value) {
		this.processMeasure = value;
	}

    public java.lang.String getProcessMeasure() {
		return this.processMeasure;
	}

    public void setProcessManName(java.lang.String value) {
		this.processManName = value;
	}

    public java.lang.String getProcessManName() {
		return this.processManName;
	}

    public void setProcessDate(java.util.Date value) {
		this.processDate = value;
	}

    public java.util.Date getProcessDate() {
		return this.processDate;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("CUSTOMER_NAME",getCustomerName())
			.append("COMPLAIN_DATE",getComplainDate())
			.append("MOBILE",getMobile())
			.append("ADDR",getAddr())
			.append("COMPLAIN_CONT",getComplainCont())
			.append("GOODS_CODE",getGoodsCode())
			.append("GOODS_NM",getGoodsNm())
			.append("GOODS_PINYIN",getGoodsPinyin())
			.append("SPEC",getSpec())
			.append("DOSAGE_FORM",getDosageForm())
			.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
			.append("APPROVAL_NUMBER",getApprovalNumber())
			.append("BATCH",getBatch())
			.append("VALID_DATE",getValidDate())
			.append("QUANTITY",getQuantity())
			.append("SURVEY_CONDITION",getSurveyCondition())
			.append("SURVEY_MAN_NAME",getSurveyManName())
			.append("SURVEY_DATE",getSurveyDate())
			.append("SUGGEST",getSuggest())
			.append("PROCESS_MEASURE",getProcessMeasure())
			.append("PROCESS_MAN_NAME",getProcessManName())
			.append("PROCESS_DATE",getProcessDate())
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
		if(!(obj instanceof ComplainRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ComplainRecord other = (ComplainRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getComplainDateString() {
		return DateTimeUtils.convertDateToString(getComplainDate());
	}

	public void setComplainDateString(String value) throws ParseException {
		setComplainDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getValidDateString() {
		return DateTimeUtils.convertDateToString(getValidDate());
	}

	public void setValidDateString(String value) throws ParseException {
		setValidDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getSurveyDateString() {
		return DateTimeUtils.convertDateToString(getSurveyDate());
	}

	public void setSurveyDateString(String value) throws ParseException {
		setSurveyDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getProcessDateString() {
		return DateTimeUtils.convertDateToString(getProcessDate());
	}

	public void setProcessDateString(String value) throws ParseException {
		setProcessDate(DateTimeUtils.convertStringToDate(value));
	}

}

