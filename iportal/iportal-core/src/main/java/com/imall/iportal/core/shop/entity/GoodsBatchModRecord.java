
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
@Table(name = "t_shp_goods_batch_mod_record" )
public class GoodsBatchModRecord extends BaseEntity<Long>{

	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String NEW_BATCH = "newBatch";
	public static final String OLD_BATCH = "oldBatch";
	public static final String NEW_PRODUCE_DATE = "newProduceDate";
	public static final String OLD_PRODUCE_DATE = "oldProduceDate";
	public static final String NEW_VALID_DATE = "newValidDate";
	public static final String OLD_VALID_DATE = "oldValidDate";
	public static final String APPROVE_MAN_ID = "approveManId";

    /** GOODS_BATCH_ID - 商品 批次 ID */
    @Column(name = "GOODS_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsBatchId;
    /** NEW_BATCH - 新 批号 */
    @Column(name = "NEW_BATCH", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String newBatch;
    /** OLD_BATCH - 旧 批号 */
    @Column(name = "OLD_BATCH", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String oldBatch;
    /** NEW_PRODUCE_DATE - 新 生产 日期 */
    @Column(name = "NEW_PRODUCE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date newProduceDate;
    /** OLD_PRODUCE_DATE - 旧 生产 日期 */
    @Column(name = "OLD_PRODUCE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date oldProduceDate;
    /** NEW_VALID_DATE - 新 有效 日期 */
    @Column(name = "NEW_VALID_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date newValidDate;
    /** OLD_VALID_DATE - 旧 有效 日期 */
    @Column(name = "OLD_VALID_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date oldValidDate;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long approveManId;

    public void setGoodsBatchId(Long value) {
		this.goodsBatchId = value;
	}

    public Long getGoodsBatchId() {
		return this.goodsBatchId;
	}

    public void setNewBatch(String value) {
		this.newBatch = value;
	}

    public String getNewBatch() {
		return this.newBatch;
	}

    public void setOldBatch(String value) {
		this.oldBatch = value;
	}

    public String getOldBatch() {
		return this.oldBatch;
	}

    public void setNewProduceDate(java.util.Date value) {
		this.newProduceDate = value;
	}

    public java.util.Date getNewProduceDate() {
		return this.newProduceDate;
	}

    public void setOldProduceDate(java.util.Date value) {
		this.oldProduceDate = value;
	}

    public java.util.Date getOldProduceDate() {
		return this.oldProduceDate;
	}

    public void setNewValidDate(java.util.Date value) {
		this.newValidDate = value;
	}

    public java.util.Date getNewValidDate() {
		return this.newValidDate;
	}

    public void setOldValidDate(java.util.Date value) {
		this.oldValidDate = value;
	}

    public java.util.Date getOldValidDate() {
		return this.oldValidDate;
	}

    public void setApproveManId(Long value) {
		this.approveManId = value;
	}

    public Long getApproveManId() {
		return this.approveManId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("NEW_BATCH",getNewBatch())
			.append("OLD_BATCH",getOldBatch())
			.append("NEW_PRODUCE_DATE",getNewProduceDate())
			.append("OLD_PRODUCE_DATE",getOldProduceDate())
			.append("NEW_VALID_DATE",getNewValidDate())
			.append("OLD_VALID_DATE",getOldValidDate())
			.append("APPROVE_MAN_ID",getApproveManId())
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
		if(!(obj instanceof GoodsBatchModRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		GoodsBatchModRecord other = (GoodsBatchModRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getNewProduceDateString() {
		return DateTimeUtils.convertDateToString(this.getNewProduceDate());
	}

	public void setNewProduceDateString(String value) throws ParseException {
		this.setNewProduceDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getOldProduceDateString() {
		return DateTimeUtils.convertDateToString(this.getOldProduceDate());
	}

	public void setOldProduceDateString(String value) throws ParseException {
		this.setOldProduceDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getNewValidDateString() {
		return DateTimeUtils.convertDateToString(this.getNewValidDate());
	}

	public void setNewValidDateString(String value) throws ParseException {
		this.setNewValidDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getOldValidDateString() {
		return DateTimeUtils.convertDateToString(this.getOldValidDate());
	}

	public void setOldValidDateString(String value) throws ParseException {
		this.setOldValidDate(DateTimeUtils.convertStringToDate(value));
	}

}

