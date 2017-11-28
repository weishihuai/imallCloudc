
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
@Table(name = "t_shp_drug_lock" )
public class DrugLock extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String GOODS_ID = "goodsId";
	public static final String SKU_ID = "skuId";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String GOODS_NM = "goodsNm";
	public static final String GOODS_COMMON_NM = "goodsCommonNm";
	public static final String GOODS_CODE = "goodsCode";
	public static final String PINYIN = "pinyin";
	public static final String LOCK_QUANTITY = "lockQuantity";
	public static final String LOCK_REASON = "lockReason";
	public static final String REMARK = "remark";
	public static final String LOCK_MAN_NAME = "lockManName";
	public static final String LOCK_TIME = "lockTime";
	public static final String LOCK_STATE_CODE = "lockStateCode";
	public static final String PROCESS_RESULT_CODE = "processResultCode";
	public static final String PROCESS_REASON = "processReason";
	public static final String PROCESS_TIME = "processTime";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String LOCK_BATCH_ID = "lockBatchId";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;
    /** SKU_ID - SKU ID */
    @Column(name = "SKU_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long skuId;
    /** GOODS_BATCH_ID - 商品 批次 ID */
    @Column(name = "GOODS_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsBatchId;
    /** GOODS_NM - 商品 名称 */
    @Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String goodsNm;
    /** GOODS_COMMON_NM - 商品 通用 名称 */
    @Column(name = "GOODS_COMMON_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String goodsCommonNm;
    /** GOODS_CODE - 商品 编码 */
    @Column(name = "GOODS_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String goodsCode;
    /** PINYIN - 拼音 */
    @Column(name = "PINYIN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String pinyin;
    /** LOCK_QUANTITY - 锁定 数量 */
    @Column(name = "LOCK_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long lockQuantity;
    /** LOCK_REASON - 锁定 原因 */
    @Column(name = "LOCK_REASON", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String lockReason;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String remark;
    /** LOCK_MAN_NAME - 锁定 人 姓名 */
    @Column(name = "LOCK_MAN_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String lockManName;
    /** LOCK_TIME - 锁定 时间 */
    @Column(name = "LOCK_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date lockTime;
    /** LOCK_STATE_CODE - 锁定 状态 代码 */
    @Column(name = "LOCK_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String lockStateCode;
    /** PROCESS_RESULT_CODE - 处理 结果 代码 */
    @Column(name = "PROCESS_RESULT_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String processResultCode;
    /** PROCESS_REASON - 处理 原因 */
    @Column(name = "PROCESS_REASON", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String processReason;
    /** PROCESS_TIME - 处理 时间 */
    @Column(name = "PROCESS_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date processTime;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long approveManId;
	/** LOCK_BATCH_ID - 锁定 批次 ID */
	@Column(name = "LOCK_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long lockBatchId;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setGoodsId(Long value) {
		this.goodsId = value;
	}

    public Long getGoodsId() {
		return this.goodsId;
	}

    public void setSkuId(Long value) {
		this.skuId = value;
	}

    public Long getSkuId() {
		return this.skuId;
	}

    public void setGoodsBatchId(Long value) {
		this.goodsBatchId = value;
	}

    public Long getGoodsBatchId() {
		return this.goodsBatchId;
	}

    public void setGoodsNm(String value) {
		this.goodsNm = value;
	}

    public String getGoodsNm() {
		return this.goodsNm;
	}

    public void setGoodsCommonNm(String value) {
		this.goodsCommonNm = value;
	}

    public String getGoodsCommonNm() {
		return this.goodsCommonNm;
	}

    public void setGoodsCode(String value) {
		this.goodsCode = value;
	}

    public String getGoodsCode() {
		return this.goodsCode;
	}

    public void setPinyin(String value) {
		this.pinyin = value;
	}

    public String getPinyin() {
		return this.pinyin;
	}

    public void setLockQuantity(Long value) {
		this.lockQuantity = value;
	}

    public Long getLockQuantity() {
		return this.lockQuantity;
	}

    public void setLockReason(String value) {
		this.lockReason = value;
	}

    public String getLockReason() {
		return this.lockReason;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

    public void setLockManName(String value) {
		this.lockManName = value;
	}

    public String getLockManName() {
		return this.lockManName;
	}

    public void setLockTime(java.util.Date value) {
		this.lockTime = value;
	}

    public java.util.Date getLockTime() {
		return this.lockTime;
	}

    public void setLockStateCode(String value) {
		this.lockStateCode = value;
	}

    public String getLockStateCode() {
		return this.lockStateCode;
	}

    public void setProcessResultCode(String value) {
		this.processResultCode = value;
	}

    public String getProcessResultCode() {
		return this.processResultCode;
	}

    public void setProcessReason(String value) {
		this.processReason = value;
	}

    public String getProcessReason() {
		return this.processReason;
	}

    public void setProcessTime(java.util.Date value) {
		this.processTime = value;
	}

    public java.util.Date getProcessTime() {
		return this.processTime;
	}

    public void setApproveManId(Long value) {
		this.approveManId = value;
	}

    public Long getApproveManId() {
		return this.approveManId;
	}

	public Long getLockBatchId() {
		return lockBatchId;
	}

	public void setLockBatchId(Long lockBatchId) {
		this.lockBatchId = lockBatchId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("GOODS_ID",getGoodsId())
			.append("SKU_ID",getSkuId())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("GOODS_NM",getGoodsNm())
			.append("GOODS_COMMON_NM",getGoodsCommonNm())
			.append("GOODS_CODE",getGoodsCode())
			.append("PINYIN",getPinyin())
			.append("LOCK_QUANTITY",getLockQuantity())
			.append("LOCK_REASON",getLockReason())
			.append("REMARK",getRemark())
			.append("LOCK_MAN_NAME",getLockManName())
			.append("LOCK_TIME",getLockTime())
			.append("LOCK_STATE_CODE",getLockStateCode())
			.append("PROCESS_RESULT_CODE",getProcessResultCode())
			.append("PROCESS_REASON",getProcessReason())
			.append("PROCESS_TIME",getProcessTime())
			.append("APPROVE_MAN_ID",getApproveManId())
			.append("CREATE_DATE",getCreateDate())
			.append("CREATE_BY",getCreateBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("VERSION",getVersion())
			.append("LOCK_BATCH_ID",getLockBatchId())
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
		if(!(obj instanceof DrugLock)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DrugLock other = (DrugLock)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getLockTimeString() {
		return DateTimeUtils.convertDateToString(this.getLockTime());
	}

	public void setLockTimeString(String value) throws ParseException {
		this.setLockTime(DateTimeUtils.convertStringToDate(value));
	}
}

