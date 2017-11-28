
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
 * 收货 地址实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_receiver_addr" )
public class ReceiverAddr extends BaseEntity<Long>{

	public static final String WE_CHAT_USER_ID = "weChatUserId";
	public static final String OPEN_ID = "openId";
	public static final String CITY_NAME = "cityName";
	public static final String RECEIVER_NAME = "receiverName";
	public static final String CONTACT_TEL = "contactTel";
	public static final String DELIVERY_ADDR = "deliveryAddr";
	public static final String POSITION_NAME = "positionName";
	public static final String DETAIL_ADDR = "detailAddr";
	public static final String ADDR_LAT = "addrLat";
	public static final String ADDR_LNG = "addrLng";
	public static final String IS_DEFAULT = "isDefault";

    /** WE_CHAT_USER_ID - 微信 用户 ID */
    @Column(name = "WE_CHAT_USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long weChatUserId;
	/** OPEN_ID - 微信 ID */
	@Column(name = "OPEN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String openId;
	/** CITY_NAME - 城市 名称 */
	@Column(name = "CITY_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
	private java.lang.String cityName;
    /** RECEIVER_NAME - 收货人 姓名 */
    @Column(name = "RECEIVER_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String receiverName;
    /** CONTACT_TEL - 联系 电话 */
    @Column(name = "CONTACT_TEL", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String contactTel;
    /** DELIVERY_ADDR - 配送 地址 */
    @Column(name = "DELIVERY_ADDR", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private java.lang.String deliveryAddr;
    /** POSITION_NAME - 位置名称 */
    @Column(name = "POSITION_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private java.lang.String positionName;
    /** DETAIL_ADDR - 详细 地址 */
    @Column(name = "DETAIL_ADDR", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String detailAddr;
    /** ADDR_LAT - 地址 纬度 */
    @Column(name = "ADDR_LAT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double addrLat;
    /** ADDR_LNG - 地址 经度 */
    @Column(name = "ADDR_LNG", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double addrLng;
    /** IS_DEFAULT - 是否 默认 */
    @Column(name = "IS_DEFAULT", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String isDefault;

	public Long getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(Long weChatUserId) {
		this.weChatUserId = weChatUserId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setReceiverName(java.lang.String value) {
		this.receiverName = value;
	}

    public java.lang.String getReceiverName() {
		return this.receiverName;
	}

    public void setContactTel(java.lang.String value) {
		this.contactTel = value;
	}

    public java.lang.String getContactTel() {
		return this.contactTel;
	}

    public void setDeliveryAddr(java.lang.String value) {
		this.deliveryAddr = value;
	}

    public java.lang.String getDeliveryAddr() {
		return this.deliveryAddr;
	}

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public void setDetailAddr(java.lang.String value) {
		this.detailAddr = value;
	}

    public java.lang.String getDetailAddr() {
		return this.detailAddr;
	}

	public Double getAddrLat() {
		return addrLat;
	}

	public void setAddrLat(Double addrLat) {
		this.addrLat = addrLat;
	}

	public Double getAddrLng() {
		return addrLng;
	}

	public void setAddrLng(Double addrLng) {
		this.addrLng = addrLng;
	}

	public void setIsDefault(java.lang.String value) {
		this.isDefault = value;
	}

    public java.lang.String getIsDefault() {
		return this.isDefault;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("WE_CHAT_USER_ID",getWeChatUserId())
			.append("OPEN_ID",getOpenId())
			.append("CITY_NAME",getCityName())
			.append("RECEIVER_NAME",getReceiverName())
			.append("CONTACT_TEL",getContactTel())
			.append("DELIVERY_ADDR",getDeliveryAddr())
			.append("POSITION_NAME",getPositionName())
			.append("POSITION_NAME",getPositionName())
			.append("DETAIL_ADDR",getDetailAddr())
			.append("ADDR_LAT",getAddrLat())
			.append("ADDR_LNG",getAddrLng())
			.append("IS_DEFAULT",getIsDefault())
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
		if(!(obj instanceof ReceiverAddr)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ReceiverAddr other = (ReceiverAddr)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

