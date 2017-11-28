
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
@Table(name = "t_shp_drug_curing_item" )
public class DrugCuringItem extends BaseEntity<Long>{

	public static final String DRUG_CURING_ID = "drugCuringId";
	public static final String SHOP_ID = "shopId";
	public static final String GOODS_ID = "goodsId";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String CURING_QUANTITY = "curingQuantity";
	public static final String CURING_PRJ = "curingPrj";
	public static final String NOT_QUALIFIED_QUANTITY = "notQualifiedQuantity";
	public static final String PROCESS_OPINION = "processOpinion";
	public static final String CONCLUSION = "conclusion";
	public static final String REMARK = "remark";

    /** DRUG_CURING_ID - 药品 养护 ID */
    @Column(name = "DRUG_CURING_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long drugCuringId;
    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;
    /** GOODS_BATCH_ID - 商品 批次 ID */
    @Column(name = "GOODS_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsBatchId;
    /** CURING_QUANTITY - 养护 数量 */
    @Column(name = "CURING_QUANTITY", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long curingQuantity;
    /** CURING_PRJ - 养护 项目 */
    @Column(name = "CURING_PRJ", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String curingPrj;
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

    public void setDrugCuringId(Long value) {
		this.drugCuringId = value;
	}

    public Long getDrugCuringId() {
		return this.drugCuringId;
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

    public void setCuringQuantity(Long value) {
		this.curingQuantity = value;
	}

    public Long getCuringQuantity() {
		return this.curingQuantity;
	}

    public void setCuringPrj(String value) {
		this.curingPrj = value;
	}

    public String getCuringPrj() {
		return this.curingPrj;
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
			.append("DRUG_CURING_ID",getDrugCuringId())
			.append("SHOP_ID",getShopId())
			.append("GOODS_ID",getGoodsId())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("CURING_QUANTITY",getCuringQuantity())
			.append("CURING_PRJ",getCuringPrj())
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
		if(!(obj instanceof DrugCuringItem)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DrugCuringItem other = (DrugCuringItem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

