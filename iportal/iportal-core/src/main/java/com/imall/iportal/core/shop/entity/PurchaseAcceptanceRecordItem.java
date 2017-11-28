
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
@Table(name = "t_shp_purchase_acceptance_record_item" )
public class PurchaseAcceptanceRecordItem extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String PURCHASE_ACCEPTANCE_RECORD_ID = "purchaseAcceptanceRecordId";
	public static final String GOODS_ID = "goodsId";
	public static final String GOODS_BATCH_SKU_ID = "goodsBatchSkuId";
	public static final String GOODS_NM = "goodsNm";
	public static final String GOODS_CODE = "goodsCode";
	public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
	public static final String GOODS_ARRIVAL_QUANTITY = "goodsArrivalQuantity";
	public static final String REJECTION_QUANTITY = "rejectionQuantity";
	public static final String QUALIFIED_QUANTITY = "qualifiedQuantity";
	public static final String IN_STORAGE_QUANTITY = "inStorageQuantity";
	public static final String SAMPLE_QUANTITY = "sampleQuantity";
	public static final String STERILIZATION_BATCH = "sterilizationBatch";
	public static final String ACCEPTANCE_REP = "acceptanceRep";
	public static final String PURCHASE_UNIT_PRICE = "purchaseUnitPrice";
	public static final String ACCEPTANCE_QUALIFIED_AMOUNT = "acceptanceQualifiedAmount";
	public static final String TOTAL_AMOUNT = "totalAmount";
	public static final String RETAIL_PRICE = "retailPrice";
	public static final String GOODS_BATCH = "goodsBatch";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String PRODUCTION_DATE = "productionDate";
	public static final String VALIDITY = "validity";
	public static final String GOODS_ALLOCATION = "goodsAllocation";
	public static final String ACCEPTANCE_CONCLUSION = "acceptanceConclusion";
	public static final String STORAGE_SPACE_ID = "storageSpaceId";
	public static final String RETURNABLE_QUANTITY = "returnableQuantity";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** SUPPLIER_ID - 供应商 ID */
    @Column(name = "SUPPLIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long supplierId;
    /** PURCHASE_ACCEPTANCE_RECORD_ID - 采购 验收 记录 ID */
    @Column(name = "PURCHASE_ACCEPTANCE_RECORD_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long purchaseAcceptanceRecordId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;
	/** GOODS_CODE - 商品 编码 */
	@Column(name = "GOODS_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
	private String goodsCode;
	/** GOODS_NM - 商品 名称 */
	@Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
	private String goodsNm;
	/** PRODUCE_MANUFACTURER - 生产 厂商 */
	@Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	private String produceManufacturer;
	/** GOODS_ARRIVAL_QUANTITY - 商品 到货 数量 */
    @Column(name = "GOODS_ARRIVAL_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsArrivalQuantity;
    /** REJECTION_QUANTITY - 拒收 数量 */
    @Column(name = "REJECTION_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long rejectionQuantity;
    /** QUALIFIED_QUANTITY - 合格 数量 */
    @Column(name = "QUALIFIED_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long qualifiedQuantity;
    /** IN_STORAGE_QUANTITY - 入库 数量 */
    @Column(name = "IN_STORAGE_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long inStorageQuantity;
    /** SAMPLE_QUANTITY - 抽样 数量 */
    @Column(name = "SAMPLE_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long sampleQuantity;
    /** STERILIZATION_BATCH - 灭菌 批次 */
    @Column(name = "STERILIZATION_BATCH", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String sterilizationBatch;
    /** ACCEPTANCE_REP - 验收 报告 */
    @Column(name = "ACCEPTANCE_REP", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String acceptanceRep;
    /** PURCHASE_UNIT_PRICE - 采购 单位 价格 */
    @Column(name = "PURCHASE_UNIT_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double purchaseUnitPrice;
    /** ACCEPTANCE_QUALIFIED_AMOUNT - 验收 合格 金额 */
    @Column(name = "ACCEPTANCE_QUALIFIED_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Double acceptanceQualifiedAmount;
    /** TOTAL_AMOUNT - 总 金额 */
    @Column(name = "TOTAL_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Double totalAmount;
    /** RETAIL_PRICE - 零售价 */
    @Column(name = "RETAIL_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double retailPrice;
    /** GOODS_BATCH - 商品 批次 */
    @Column(name = "GOODS_BATCH", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String goodsBatch;
	/** GOODS_BATCH_ID - 商品 批次 ID */
	@Column(name = "GOODS_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long goodsBatchId;
    /** PRODUCTION_DATE - 生产 日期 */
    @Column(name = "PRODUCTION_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date productionDate;
    /** VALIDITY - 有效期 */
    @Column(name = "VALIDITY", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date validity;
    /** GOODS_ALLOCATION - 货位 */
    @Column(name = "GOODS_ALLOCATION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String goodsAllocation;
    /** ACCEPTANCE_CONCLUSION - 验收 结论 */
    @Column(name = "ACCEPTANCE_CONCLUSION", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String acceptanceConclusion;
	/** STORAGE_SPACE_ID - 货位 ID */
	@Column(name = "STORAGE_SPACE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long storageSpaceId;
	/** RETURNABLE_QUANTITY - 可退 数量 */
	@Column(name = "RETURNABLE_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long returnableQuantity;


    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setSupplierId(Long value) {
		this.supplierId = value;
	}

    public Long getSupplierId() {
		return this.supplierId;
	}

    public void setPurchaseAcceptanceRecordId(Long value) {
		this.purchaseAcceptanceRecordId = value;
	}

    public Long getPurchaseAcceptanceRecordId() {
		return this.purchaseAcceptanceRecordId;
	}

    public void setGoodsId(Long value) {
		this.goodsId = value;
	}

    public Long getGoodsId() {
		return this.goodsId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsNm() {
		return goodsNm;
	}

	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}

	public String getProduceManufacturer() {
		return produceManufacturer;
	}

	public void setProduceManufacturer(String produceManufacturer) {
		this.produceManufacturer = produceManufacturer;
	}

	public void setGoodsArrivalQuantity(Long value) {
		this.goodsArrivalQuantity = value;
	}

    public Long getGoodsArrivalQuantity() {
		return this.goodsArrivalQuantity;
	}

    public void setRejectionQuantity(Long value) {
		this.rejectionQuantity = value;
	}

    public Long getRejectionQuantity() {
		return this.rejectionQuantity;
	}

    public void setQualifiedQuantity(Long value) {
		this.qualifiedQuantity = value;
	}

    public Long getQualifiedQuantity() {
		return this.qualifiedQuantity;
	}

    public void setInStorageQuantity(Long value) {
		this.inStorageQuantity = value;
	}

    public Long getInStorageQuantity() {
		return this.inStorageQuantity;
	}

    public void setSampleQuantity(Long value) {
		this.sampleQuantity = value;
	}

    public Long getSampleQuantity() {
		return this.sampleQuantity;
	}

    public void setSterilizationBatch(String value) {
		this.sterilizationBatch = value;
	}

    public String getSterilizationBatch() {
		return this.sterilizationBatch;
	}

    public void setAcceptanceRep(String value) {
		this.acceptanceRep = value;
	}

    public String getAcceptanceRep() {
		return this.acceptanceRep;
	}

    public void setPurchaseUnitPrice(Double value) {
		this.purchaseUnitPrice = value;
	}

    public Double getPurchaseUnitPrice() {
		return this.purchaseUnitPrice;
	}

    public void setAcceptanceQualifiedAmount(Double value) {
		this.acceptanceQualifiedAmount = value;
	}

    public Double getAcceptanceQualifiedAmount() {
		return this.acceptanceQualifiedAmount;
	}

    public void setTotalAmount(Double value) {
		this.totalAmount = value;
	}

    public Double getTotalAmount() {
		return this.totalAmount;
	}

    public void setRetailPrice(Double value) {
		this.retailPrice = value;
	}

    public Double getRetailPrice() {
		return this.retailPrice;
	}

    public void setGoodsBatch(String value) {
		this.goodsBatch = value;
	}

    public String getGoodsBatch() {
		return this.goodsBatch;
	}

	public Long getGoodsBatchId() {
		return goodsBatchId;
	}

	public void setGoodsBatchId(Long goodsBatchId) {
		this.goodsBatchId = goodsBatchId;
	}

	public void setProductionDate(java.util.Date value) {
		this.productionDate = value;
	}

	public void setProductionDateString(String value) throws ParseException {
		this.productionDate = DateTimeUtils.convertStringToDate(value);
	}

    public java.util.Date getProductionDate() {
		return this.productionDate;
	}

	public String getProductionDateString() {
		return DateTimeUtils.convertDateToString(productionDate);
	}

    public void setValidity(java.util.Date value) {
		this.validity = value;
	}

	public void setValidityString(String value) throws ParseException {
		this.validity = DateTimeUtils.convertStringToDate(value);
	}

    public java.util.Date getValidity() {
		return this.validity;
	}

	public String getValidityString() {
		return DateTimeUtils.convertDateToString(validity);
	}

    public void setGoodsAllocation(String value) {
		this.goodsAllocation = value;
	}

    public String getGoodsAllocation() {
		return this.goodsAllocation;
	}

    public void setAcceptanceConclusion(String value) {
		this.acceptanceConclusion = value;
	}

    public String getAcceptanceConclusion() {
		return this.acceptanceConclusion;
	}

	public Long getStorageSpaceId() {
		return storageSpaceId;
	}

	public void setStorageSpaceId(Long storageSpaceId) {
		this.storageSpaceId = storageSpaceId;
	}

	public Long getReturnableQuantity() {
		return returnableQuantity;
	}

	public void setReturnableQuantity(Long returnableQuantity) {
		this.returnableQuantity = returnableQuantity;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("SUPPLIER_ID",getSupplierId())
			.append("PURCHASE_ACCEPTANCE_RECORD_ID",getPurchaseAcceptanceRecordId())
			.append("GOODS_ID",getGoodsId())
			.append("GOODS_CODE",getGoodsCode())
			.append("GOODS_NM",getGoodsNm())
			.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
			.append("GOODS_ARRIVAL_QUANTITY",getGoodsArrivalQuantity())
			.append("REJECTION_QUANTITY",getRejectionQuantity())
			.append("QUALIFIED_QUANTITY",getQualifiedQuantity())
			.append("IN_STORAGE_QUANTITY",getInStorageQuantity())
			.append("SAMPLE_QUANTITY",getSampleQuantity())
			.append("STERILIZATION_BATCH",getSterilizationBatch())
			.append("ACCEPTANCE_REP",getAcceptanceRep())
			.append("PURCHASE_UNIT_PRICE",getPurchaseUnitPrice())
			.append("ACCEPTANCE_QUALIFIED_AMOUNT",getAcceptanceQualifiedAmount())
			.append("TOTAL_AMOUNT",getTotalAmount())
			.append("RETAIL_PRICE",getRetailPrice())
			.append("GOODS_BATCH",getGoodsBatch())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("PRODUCTION_DATE",getProductionDate())
			.append("VALIDITY",getValidity())
			.append("GOODS_ALLOCATION",getGoodsAllocation())
			.append("ACCEPTANCE_CONCLUSION",getAcceptanceConclusion())
			.append("STORAGE_SPACE_ID", getStorageSpaceId())
			.append("RETURNABLE_QUANTITY", getReturnableQuantity())
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
		if(!(obj instanceof PurchaseAcceptanceRecordItem)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		PurchaseAcceptanceRecordItem other = (PurchaseAcceptanceRecordItem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

