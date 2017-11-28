
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
@Table(name = "t_shp_storage_space_move" )
public class StorageSpaceMove extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String BATCH_ID = "batchId";
	public static final String GOODS_ID = "goodsId";
	public static final String SKU_ID = "skuId";
	public static final String MOVE_ORDER_NUM = "moveOrderNum";
	public static final String MOVE_TIME = "moveTime";
	public static final String MOVE_MAN_NAME = "moveManName";
	public static final String QUANTITY = "quantity";
	public static final String SOURCE_STORAGE_SPACE_ID = "sourceStorageSpaceId";
	public static final String TARGET_STORAGE_SPACE_ID = "targetStorageSpaceId";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String REMARK = "remark";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** BATCH_ID - 批次 ID */
    @Column(name = "BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long batchId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;
    /** SKU_ID - SKU ID */
    @Column(name = "SKU_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long skuId;
    /** MOVE_ORDER_NUM - 移动 单号 */
    @Column(name = "MOVE_ORDER_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String moveOrderNum;
    /** MOVE_TIME - 移动 时间 */
    @Column(name = "MOVE_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date moveTime;
    /** MOVE_MAN_NAME - 移动 人 姓名 */
    @Column(name = "MOVE_MAN_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String moveManName;
    /** QUANTITY - 数量 */
    @Column(name = "QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long quantity;
    /** SOURCE_STORAGE_SPACE_ID - 原 货位 ID */
    @Column(name = "SOURCE_STORAGE_SPACE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long sourceStorageSpaceId;
    /** TARGET_STORAGE_SPACE_ID - 目标 货位 ID */
    @Column(name = "TARGET_STORAGE_SPACE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long targetStorageSpaceId;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long approveManId;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String remark;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setBatchId(Long value) {
		this.batchId = value;
	}

    public Long getBatchId() {
		return this.batchId;
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

    public void setMoveOrderNum(String value) {
		this.moveOrderNum = value;
	}

    public String getMoveOrderNum() {
		return this.moveOrderNum;
	}

    public void setMoveTime(java.util.Date value) {
		this.moveTime = value;
	}

    public java.util.Date getMoveTime() {
		return this.moveTime;
	}

    public void setMoveManName(String value) {
		this.moveManName = value;
	}

    public String getMoveManName() {
		return this.moveManName;
	}

    public void setQuantity(Long value) {
		this.quantity = value;
	}

    public Long getQuantity() {
		return this.quantity;
	}

    public void setSourceStorageSpaceId(Long value) {
		this.sourceStorageSpaceId = value;
	}

    public Long getSourceStorageSpaceId() {
		return this.sourceStorageSpaceId;
	}

    public void setTargetStorageSpaceId(Long value) {
		this.targetStorageSpaceId = value;
	}

    public Long getTargetStorageSpaceId() {
		return this.targetStorageSpaceId;
	}

    public void setApproveManId(Long value) {
		this.approveManId = value;
	}

    public Long getApproveManId() {
		return this.approveManId;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("BATCH_ID",getBatchId())
			.append("GOODS_ID",getGoodsId())
			.append("SKU_ID",getSkuId())
			.append("MOVE_ORDER_NUM",getMoveOrderNum())
			.append("MOVE_TIME",getMoveTime())
			.append("MOVE_MAN_NAME",getMoveManName())
			.append("QUANTITY",getQuantity())
			.append("SOURCE_STORAGE_SPACE_ID",getSourceStorageSpaceId())
			.append("TARGET_STORAGE_SPACE_ID",getTargetStorageSpaceId())
			.append("APPROVE_MAN_ID",getApproveManId())
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
		if(!(obj instanceof StorageSpaceMove)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		StorageSpaceMove other = (StorageSpaceMove)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getMoveTimeString() {
		return DateTimeUtils.convertDateToString(this.getMoveTime());
	}

	public void setMoveTimeString(String value) throws ParseException {
		this.setMoveTime(DateTimeUtils.convertStringToDate(value));
	}

}

