
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
@Table(name = "t_shp_drug_curing_record" )
public class DrugCuringRecord extends BaseEntity<Long>{

	public static final String APPROVAL_NUMBER = "approvalNumber";
	public static final String BATCH = "batch";
	public static final String COMMON_NM = "commonNm";
	public static final String CURING_DATE = "curingDate";
	public static final String CURING_MAN_ID = "curingManId";
	public static final String CURING_PLAN_NUM = "curingPlanNum";
	public static final String CURING_PRJ = "curingPrj";
	public static final String CURING_QUANTITY = "curingQuantity";
	public static final String DISQUALIFICATION_QUANTITY = "disqualificationQuantity";
	public static final String DOSAGE_FORM = "dosageForm";
	public static final String GOODS_CODE = "goodsCode";
	public static final String GOODS_NM = "goodsNm";
	public static final String GOODS_NM_FIRST_SPELL = "goodsNmFirstSpell";
	public static final String MANUFACTURE = "manufacture";
	public static final String PROCESS_SUGGEST = "processSuggest";
	public static final String PRODUCE_TIME = "produceTime";
	public static final String PRODUCTION_PLACE = "productionPlace";
	public static final String REMARK = "remark";
	public static final String SHOP_ID = "shopId";
	public static final String SPEC = "spec";
	public static final String STOCK_QUANTITY = "stockQuantity";
	public static final String STORAGE_SPACE_NM = "storageSpaceNm";
	public static final String UNIT = "unit";
	public static final String VALID_DATE = "validDate";
	public static final String VERDICT = "verdict";

    /** APPROVAL_NUMBER - 批准文号 */
    @Column(name = "APPROVAL_NUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String approvalNumber;
    /** BATCH - batch */
    @Column(name = "BATCH", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String batch;
    /** COMMON_NM - commonNm */
    @Column(name = "COMMON_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String commonNm;
    /** CURING_DATE - curingDate */
    @Column(name = "CURING_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date curingDate;
    /** CURING_MAN_ID - curingManId */
    @Column(name = "CURING_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long curingManId;
    /** CURING_PLAN_NUM - curingPlanNum */
    @Column(name = "CURING_PLAN_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String curingPlanNum;
    /** CURING_PRJ - 养护 项目 */
    @Column(name = "CURING_PRJ", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String curingPrj;
    /** CURING_QUANTITY - curingQuantity */
    @Column(name = "CURING_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long curingQuantity;
    /** DISQUALIFICATION_QUANTITY - disqualificationQuantity */
    @Column(name = "DISQUALIFICATION_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long disqualificationQuantity;
    /** DOSAGE_FORM - dosageForm */
    @Column(name = "DOSAGE_FORM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String dosageForm;
    /** GOODS_CODE - goodsCode */
    @Column(name = "GOODS_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String goodsCode;
    /** GOODS_NM - goodsNm */
    @Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String goodsNm;
    /** GOODS_NM_FIRST_SPELL - goodsNmFirstSpell */
    @Column(name = "GOODS_NM_FIRST_SPELL", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String goodsNmFirstSpell;
    /** MANUFACTURE - manufacture */
    @Column(name = "MANUFACTURE", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String manufacture;
    /** PROCESS_SUGGEST - processSuggest */
    @Column(name = "PROCESS_SUGGEST", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String processSuggest;
    /** PRODUCE_TIME - produceTime */
    @Column(name = "PRODUCE_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date produceTime;
    /** PRODUCTION_PLACE - productionPlace */
    @Column(name = "PRODUCTION_PLACE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String productionPlace;
    /** REMARK - remark */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String remark;
    /** SHOP_ID - shopId */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** SPEC - spec */
    @Column(name = "SPEC", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String spec;
    /** STOCK_QUANTITY - stockQuantity */
    @Column(name = "STOCK_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long stockQuantity;
    /** STORAGE_SPACE_NM - storageSpaceNm */
    @Column(name = "STORAGE_SPACE_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String storageSpaceNm;
    /** UNIT - unit */
    @Column(name = "UNIT", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String unit;
    /** VALID_DATE - validDate */
    @Column(name = "VALID_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date validDate;
    /** VERDICT - verdict */
    @Column(name = "VERDICT", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String verdict;

    public void setApprovalNumber(String value) {
		this.approvalNumber = value;
	}

    public String getApprovalNumber() {
		return this.approvalNumber;
	}

    public void setBatch(String value) {
		this.batch = value;
	}

    public String getBatch() {
		return this.batch;
	}

    public void setCommonNm(String value) {
		this.commonNm = value;
	}

    public String getCommonNm() {
		return this.commonNm;
	}

    public void setCuringDate(java.util.Date value) {
		this.curingDate = value;
	}

    public java.util.Date getCuringDate() {
		return this.curingDate;
	}

    public void setCuringManId(Long value) {
		this.curingManId = value;
	}

    public Long getCuringManId() {
		return this.curingManId;
	}

    public void setCuringPlanNum(String value) {
		this.curingPlanNum = value;
	}

    public String getCuringPlanNum() {
		return this.curingPlanNum;
	}

    public void setCuringPrj(String value) {
		this.curingPrj = value;
	}

    public String getCuringPrj() {
		return this.curingPrj;
	}

    public void setCuringQuantity(Long value) {
		this.curingQuantity = value;
	}

    public Long getCuringQuantity() {
		return this.curingQuantity;
	}

    public void setDisqualificationQuantity(Long value) {
		this.disqualificationQuantity = value;
	}

    public Long getDisqualificationQuantity() {
		return this.disqualificationQuantity;
	}

    public void setDosageForm(String value) {
		this.dosageForm = value;
	}

    public String getDosageForm() {
		return this.dosageForm;
	}

    public void setGoodsCode(String value) {
		this.goodsCode = value;
	}

    public String getGoodsCode() {
		return this.goodsCode;
	}

    public void setGoodsNm(String value) {
		this.goodsNm = value;
	}

    public String getGoodsNm() {
		return this.goodsNm;
	}

    public void setGoodsNmFirstSpell(String value) {
		this.goodsNmFirstSpell = value;
	}

    public String getGoodsNmFirstSpell() {
		return this.goodsNmFirstSpell;
	}

    public void setManufacture(String value) {
		this.manufacture = value;
	}

    public String getManufacture() {
		return this.manufacture;
	}

    public void setProcessSuggest(String value) {
		this.processSuggest = value;
	}

    public String getProcessSuggest() {
		return this.processSuggest;
	}

    public void setProduceTime(java.util.Date value) {
		this.produceTime = value;
	}

    public java.util.Date getProduceTime() {
		return this.produceTime;
	}

    public void setProductionPlace(String value) {
		this.productionPlace = value;
	}

    public String getProductionPlace() {
		return this.productionPlace;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setSpec(String value) {
		this.spec = value;
	}

    public String getSpec() {
		return this.spec;
	}

    public void setStockQuantity(Long value) {
		this.stockQuantity = value;
	}

    public Long getStockQuantity() {
		return this.stockQuantity;
	}

    public void setStorageSpaceNm(String value) {
		this.storageSpaceNm = value;
	}

    public String getStorageSpaceNm() {
		return this.storageSpaceNm;
	}

    public void setUnit(String value) {
		this.unit = value;
	}

    public String getUnit() {
		return this.unit;
	}

    public void setValidDate(java.util.Date value) {
		this.validDate = value;
	}

    public java.util.Date getValidDate() {
		return this.validDate;
	}

    public void setVerdict(String value) {
		this.verdict = value;
	}

    public String getVerdict() {
		return this.verdict;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("CREATE_BY",getCreateBy())
			.append("CREATE_DATE",getCreateDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("VERSION",getVersion())
			.append("APPROVAL_NUMBER",getApprovalNumber())
			.append("BATCH",getBatch())
			.append("COMMON_NM",getCommonNm())
			.append("CURING_DATE",getCuringDate())
			.append("CURING_MAN_ID",getCuringManId())
			.append("CURING_PLAN_NUM",getCuringPlanNum())
			.append("CURING_PRJ",getCuringPrj())
			.append("CURING_QUANTITY",getCuringQuantity())
			.append("DISQUALIFICATION_QUANTITY",getDisqualificationQuantity())
			.append("DOSAGE_FORM",getDosageForm())
			.append("GOODS_CODE",getGoodsCode())
			.append("GOODS_NM",getGoodsNm())
			.append("GOODS_NM_FIRST_SPELL",getGoodsNmFirstSpell())
			.append("MANUFACTURE",getManufacture())
			.append("PROCESS_SUGGEST",getProcessSuggest())
			.append("PRODUCE_TIME",getProduceTime())
			.append("PRODUCTION_PLACE",getProductionPlace())
			.append("REMARK",getRemark())
			.append("SHOP_ID",getShopId())
			.append("SPEC",getSpec())
			.append("STOCK_QUANTITY",getStockQuantity())
			.append("STORAGE_SPACE_NM",getStorageSpaceNm())
			.append("UNIT",getUnit())
			.append("VALID_DATE",getValidDate())
			.append("VERDICT",getVerdict())
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
		if(!(obj instanceof DrugCuringRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DrugCuringRecord other = (DrugCuringRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

