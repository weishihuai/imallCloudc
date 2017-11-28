
package com.imall.iportal.core.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.math.BigDecimal;
/**
 * 微门店实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_we_shop" )
public class WeShop extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String SHOP_NM = "shopNm";
	public static final String SHOP_BRIEF = "shopBrief";
	public static final String SHOP_ZONE = "shopZone";
	public static final String SHOP_ZONE_PARENT = "shopZoneParent";
	public static final String SHOP_ZONE_PARENT_NAME = "shopZoneParentName";
	public static final String DETAIL_LOCATION = "detailLocation";
	public static final String SHOP_LAT = "shopLat";
	public static final String SHOP_LNG = "shopLng";
	public static final String DELIVERY_RANGE = "deliveryRange";
	public static final String CONTACT_TEL = "contactTel";
	public static final String SHOP_PROMISE_SEND_TIME = "shopPromiseSendTime";
	public static final String PLACARD_INF = "placardInf";
	public static final String SELL_START_TIME = "sellStartTime";
	public static final String SELL_END_TIME = "sellEndTime";
	public static final String IS_NORMAL_SALES = "isNormalSales";
	public static final String DELIVERY_TYPE_CODE = "deliveryTypeCode";
	public static final String DELIVERY_AMOUNT = "deliveryAmount";
	public static final String DELIVERY_MIN_ORDER_AMOUNT = "deliveryMinOrderAmount";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** SHOP_NM - 门店 名称 */
    @Column(name = "SHOP_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String shopNm;
    /** SHOP_BRIEF - 门店 简介 */
    @Column(name = "SHOP_BRIEF", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private java.lang.String shopBrief;
    /** SHOP_ZONE - 门店 区域 */
    @Column(name = "SHOP_ZONE", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopZone;
	/** SHOP_ZONE - 门店 区域 父节点*/
	@Column(name = "SHOP_ZONE_PARENT", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long shopZoneParent;
	/** SHOP_ZONE - 门店 区域 父节点 名称*/
	@Column(name = "SHOP_ZONE_PARENT_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
	private java.lang.String shopZoneParentName;
    /** DETAIL_LOCATION - 详细 位置 */
    @Column(name = "DETAIL_LOCATION", unique = false, nullable = false, insertable = true, updatable = true, length = 256)
    private java.lang.String detailLocation;
	/** SHOP_LAT - 门店 纬度 */
	@Column(name = "SHOP_LAT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private Double shopLat;
	/** SHOP_LNG - 门店 经度 */
	@Column(name = "SHOP_LNG", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private Double shopLng;
    /** DELIVERY_RANGE - 配送 范围 */
    @Column(name = "DELIVERY_RANGE", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long deliveryRange;
    /** CONTACT_TEL - 联系 电话 */
    @Column(name = "CONTACT_TEL", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String contactTel;
    /** SHOP_PROMISE_SEND_TIME - 门店 承诺 送货 时间 */
    @Column(name = "SHOP_PROMISE_SEND_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    private java.lang.Integer shopPromiseSendTime;
    /** PLACARD_INF - 公告 信息 */
    @Column(name = "PLACARD_INF", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private java.lang.String placardInf;
    /** SELL_START_TIME - 营业 开始 时间 */
    @Column(name = "SELL_START_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String sellStartTime;
    /** SELL_END_TIME - 营业 结束 时间 */
    @Column(name = "SELL_END_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String sellEndTime;
    /** IS_NORMAL_SALES - 是否 正常 营业 */
    @Column(name = "IS_NORMAL_SALES", unique = false, nullable = false, insertable = true, updatable = true, length = 2)
    private java.lang.String isNormalSales;
    /** DELIVERY_TYPE_CODE - 配送 类型 代码 */
    @Column(name = "DELIVERY_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String deliveryTypeCode;
    /** DELIVERY_AMOUNT - 配送 金额 */
    @Column(name = "DELIVERY_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double deliveryAmount;
    /** DELIVERY_MIN_ORDER_AMOUNT - 配送 最小 订单 金额（满额必送） */
    @Column(name = "DELIVERY_MIN_ORDER_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double deliveryMinOrderAmount;

    public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

    public void setShopNm(java.lang.String value) {
		this.shopNm = value;
	}

    public java.lang.String getShopNm() {
		return this.shopNm;
	}

    public void setShopBrief(java.lang.String value) {
		this.shopBrief = value;
	}

    public java.lang.String getShopBrief() {
		return this.shopBrief;
	}

    public void setShopZone(java.lang.Long value) {
		this.shopZone = value;
	}

    public java.lang.Long getShopZone() {
		return this.shopZone;
	}

	public Long getShopZoneParent() {
		return shopZoneParent;
	}

	public void setShopZoneParent(Long shopZoneParent) {
		this.shopZoneParent = shopZoneParent;
	}

	public String getShopZoneParentName() {
		return shopZoneParentName;
	}

	public void setShopZoneParentName(String shopZoneParentName) {
		this.shopZoneParentName = shopZoneParentName;
	}

	public void setDetailLocation(java.lang.String value) {
		this.detailLocation = value;
	}

    public java.lang.String getDetailLocation() {
		return this.detailLocation;
	}

	public Double getShopLat() {
		return shopLat;
	}

	public void setShopLat(Double shopLat) {
		this.shopLat = shopLat;
	}

	public Double getShopLng() {
		return shopLng;
	}

	public void setShopLng(Double shopLng) {
		this.shopLng = shopLng;
	}

	public void setDeliveryRange(java.lang.Long value) {
		this.deliveryRange = value;
	}

    public java.lang.Long getDeliveryRange() {
		return this.deliveryRange;
	}

    public void setContactTel(java.lang.String value) {
		this.contactTel = value;
	}

    public java.lang.String getContactTel() {
		return this.contactTel;
	}

    public void setShopPromiseSendTime(java.lang.Integer value) {
		this.shopPromiseSendTime = value;
	}

    public java.lang.Integer getShopPromiseSendTime() {
		return this.shopPromiseSendTime;
	}

    public void setPlacardInf(java.lang.String value) {
		this.placardInf = value;
	}

    public java.lang.String getPlacardInf() {
		return this.placardInf;
	}

	public String getSellStartTime() {
		return sellStartTime;
	}

	public void setSellStartTime(String sellStartTime) {
		this.sellStartTime = sellStartTime;
	}

	public String getSellEndTime() {
		return sellEndTime;
	}

	public void setSellEndTime(String sellEndTime) {
		this.sellEndTime = sellEndTime;
	}

	public String getIsNormalSales() {
		return isNormalSales;
	}

	public void setIsNormalSales(String isNormalSales) {
		this.isNormalSales = isNormalSales;
	}

	public void setDeliveryTypeCode(java.lang.String value) {
		this.deliveryTypeCode = value;
	}

    public java.lang.String getDeliveryTypeCode() {
		return this.deliveryTypeCode;
	}

    public void setDeliveryAmount(java.lang.Double value) {
		this.deliveryAmount = value;
	}

    public java.lang.Double getDeliveryAmount() {
		return this.deliveryAmount;
	}

    public void setDeliveryMinOrderAmount(java.lang.Double value) {
		this.deliveryMinOrderAmount = value;
	}

    public java.lang.Double getDeliveryMinOrderAmount() {
		return this.deliveryMinOrderAmount;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("SHOP_NM",getShopNm())
			.append("SHOP_BRIEF",getShopBrief())
			.append("SHOP_ZONE",getShopZone())
			.append("SHOP_ZONE_PARENT",getShopZoneParent())
			.append("DETAIL_LOCATION",getDetailLocation())
			.append("SHOP_LAT",getShopLat())
			.append("SHOP_LNG",getShopLng())
			.append("DELIVERY_RANGE",getDeliveryRange())
			.append("CONTACT_TEL",getContactTel())
			.append("SHOP_PROMISE_SEND_TIME",getShopPromiseSendTime())
			.append("PLACARD_INF",getPlacardInf())
			.append("SELL_START_TIME",getSellStartTime())
			.append("SELL_END_TIME",getSellEndTime())
			.append("IS_NORMAL_SALES",getIsNormalSales())
			.append("DELIVERY_TYPE_CODE",getDeliveryTypeCode())
			.append("DELIVERY_AMOUNT",getDeliveryAmount())
			.append("DELIVERY_MIN_ORDER_AMOUNT",getDeliveryMinOrderAmount())
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
		if(!(obj instanceof WeShop)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		WeShop other = (WeShop)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

