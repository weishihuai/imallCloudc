
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
@Table(name = "t_shp_drug_check_item" )
public class DrugCheckItem extends BaseEntity<Long>{

	public static final String DRUG_CHECK_ID = "drugCheckId";
	public static final String SHOP_ID = "shopId";
	public static final String GOODS_ID = "goodsId";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String CHECK_QUANTITY = "checkQuantity";
	public static final String CHECK_PRJ = "checkPrj";
	public static final String NOT_QUALIFIED_QUANTITY = "notQualifiedQuantity";
	public static final String PROCESS_OPINION = "processOpinion";
	public static final String CONCLUSION = "conclusion";
	public static final String REMARK = "remark";

    /** DRUG_CHECK_ID - 药品 检查 ID */
    @Column(name = "DRUG_CHECK_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long drugCheckId;
    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;
    /** GOODS_BATCH_ID - 商品 批次 ID */
    @Column(name = "GOODS_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsBatchId;
    /** CHECK_QUANTITY - 检查 数量 */
    @Column(name = "CHECK_QUANTITY", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long checkQuantity;
    /** CHECK_PRJ - 检查 项目 */
    @Column(name = "CHECK_PRJ", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String checkPrj;
    /** NOT_QUALIFIED_QUANTITY - 不 合格 数量 */
    @Column(name = "NOT_QUALIFIED_QUANTITY", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long notQualifiedQuantity;
    /** PROCESS_OPINION - 处理 意见 */
    @Column(name = "PROCESS_OPINION", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String processOpinion;
    /** CONCLUSION - 结论 */
    @Column(name = "CONCLUSION", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String conclusion;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String remark;

    public void setDrugCheckId(Long value) {
		this.drugCheckId = value;
	}

    public Long getDrugCheckId() {
		return this.drugCheckId;
	}

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

    public void setGoodsBatchId(Long value) {
		this.goodsBatchId = value;
	}

    public Long getGoodsBatchId() {
		return this.goodsBatchId;
	}

    public void setCheckQuantity(Long value) {
		this.checkQuantity = value;
	}

    public Long getCheckQuantity() {
		return this.checkQuantity;
	}

    public void setCheckPrj(String value) {
		this.checkPrj = value;
	}

    public String getCheckPrj() {
		return this.checkPrj;
	}

    public void setNotQualifiedQuantity(Long value) {
		this.notQualifiedQuantity = value;
	}

    public Long getNotQualifiedQuantity() {
		return this.notQualifiedQuantity;
	}

    public void setProcessOpinion(String value) {
		this.processOpinion = value;
	}

    public String getProcessOpinion() {
		return this.processOpinion;
	}

    public void setConclusion(String value) {
		this.conclusion = value;
	}

    public String getConclusion() {
		return this.conclusion;
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
			.append("DRUG_CHECK_ID",getDrugCheckId())
			.append("SHOP_ID",getShopId())
			.append("GOODS_ID",getGoodsId())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("CHECK_QUANTITY",getCheckQuantity())
			.append("CHECK_PRJ",getCheckPrj())
			.append("NOT_QUALIFIED_QUANTITY",getNotQualifiedQuantity())
			.append("PROCESS_OPINION",getProcessOpinion())
			.append("CONCLUSION",getConclusion())
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
		if(!(obj instanceof DrugCheckItem)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DrugCheckItem other = (DrugCheckItem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

