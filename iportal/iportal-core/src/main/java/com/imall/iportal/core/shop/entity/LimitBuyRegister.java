
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
@Table(name = "t_shp_limit_buy_register" )
public class LimitBuyRegister extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String ORDER_ID = "orderId";
	public static final String SELL_ORDER_CODE = "sellOrderCode";
	public static final String MEMBER_CARD_NUM = "memberCardNum";
	public static final String PATIENT_NM = "patientNm";
	public static final String IDENTITY_CARD = "identityCard";
	public static final String SEX_CODE = "sexCode";
	public static final String MOBILE = "mobile";
	public static final String ADDR = "addr";
	public static final String REGISTER_DATE = "registerDate";
	public static final String REMARK = "remark";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** ORDER_ID - 订单 ID */
    @Column(name = "ORDER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long orderId;
    /** SELL_ORDER_CODE - 销售 订单 编码 */
    @Column(name = "SELL_ORDER_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String sellOrderCode;
    /** MEMBER_CARD_NUM - 会员 卡 号码 */
    @Column(name = "MEMBER_CARD_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String memberCardNum;
    /** PATIENT_NM - 患者 名称 */
    @Column(name = "PATIENT_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String patientNm;
    /** IDENTITY_CARD - 身份证 */
    @Column(name = "IDENTITY_CARD", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String identityCard;
    /** SEX_CODE - 性别 代码 */
    @Column(name = "SEX_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String sexCode;
    /** MOBILE - 手机 */
    @Column(name = "MOBILE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String mobile;
    /** ADDR - 地址 */
    @Column(name = "ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String addr;
    /** REGISTER_DATE - 登记 日期 */
    @Column(name = "REGISTER_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date registerDate;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String remark;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setOrderId(Long value) {
		this.orderId = value;
	}

    public Long getOrderId() {
		return this.orderId;
	}

    public void setSellOrderCode(String value) {
		this.sellOrderCode = value;
	}

    public String getSellOrderCode() {
		return this.sellOrderCode;
	}

    public void setMemberCardNum(String value) {
		this.memberCardNum = value;
	}

    public String getMemberCardNum() {
		return this.memberCardNum;
	}

    public void setPatientNm(String value) {
		this.patientNm = value;
	}

    public String getPatientNm() {
		return this.patientNm;
	}

    public void setIdentityCard(String value) {
		this.identityCard = value;
	}

    public String getIdentityCard() {
		return this.identityCard;
	}

    public void setSexCode(String value) {
		this.sexCode = value;
	}

    public String getSexCode() {
		return this.sexCode;
	}

    public void setMobile(String value) {
		this.mobile = value;
	}

    public String getMobile() {
		return this.mobile;
	}

    public void setAddr(String value) {
		this.addr = value;
	}

    public String getAddr() {
		return this.addr;
	}

    public void setRegisterDate(java.util.Date value) {
		this.registerDate = value;
	}

    public java.util.Date getRegisterDate() {
		return this.registerDate;
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
			.append("ORDER_ID",getOrderId())
			.append("SELL_ORDER_CODE",getSellOrderCode())
			.append("MEMBER_CARD_NUM",getMemberCardNum())
			.append("PATIENT_NM",getPatientNm())
			.append("IDENTITY_CARD",getIdentityCard())
			.append("SEX_CODE",getSexCode())
			.append("MOBILE",getMobile())
			.append("ADDR",getAddr())
			.append("REGISTER_DATE",getRegisterDate())
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
		if(!(obj instanceof LimitBuyRegister)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		LimitBuyRegister other = (LimitBuyRegister)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

