
package com.imall.iportal.core.weshop.entity;

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
@Table(name = "t_shp_we_fans" )
public class Fans extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String WE_CHAT_USER_ID = "weChatUserId";
	public static final String FANS_NAME = "fansName";
	public static final String MOBILE = "mobile";
	public static final String OPEN_ID = "openId";
	public static final String NICK_NAME = "nickName";
	public static final String FANS_SOURCE_CODE = "fansSourceCode";
	public static final String IS_MEMBER = "isMember";
	public static final String MEMBER_ID = "memberId";
	public static final String BUY_TIMES = "buyTimes";
	public static final String REMARK = "remark";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
	/** WE_CHAT_USER_ID - 微信 用户 ID */
	@Column(name = "WE_CHAT_USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long weChatUserId;
	/** FANS_NAME - 粉丝 姓名 */
	@Column(name = "FANS_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String fansName;
    /** MOBILE - 手机 */
    @Column(name = "MOBILE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String mobile;
    /** WECHAT_ID - 微信 ID */
    @Column(name = "OPEN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String openId;
    /** WECHAT_NAME - 微信 称昵 */
    @Column(name = "NICK_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String nickName;
    /** FANS_SOURCE_CODE - 粉丝 来源 代码 */
    @Column(name = "FANS_SOURCE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String fansSourceCode;
    /** IS_MEMBER - 是否 会员 */
    @Column(name = "IS_MEMBER", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isMember;
    /** MEMBER_ID - 会员 ID */
    @Column(name = "MEMBER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long memberId;
    /** BUY_TIMES - 购买 次数 */
    @Column(name = "BUY_TIMES", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long buyTimes;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String remark;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

	public Long getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(Long weChatUserId) {
		this.weChatUserId = weChatUserId;
	}

	public String getFansName() {
		return fansName;
	}

	public void setFansName(String fansName) {
		this.fansName = fansName;
	}

	public void setMobile(String value) {
		this.mobile = value;
	}

    public String getMobile() {
		return this.mobile;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setFansSourceCode(String value) {
		this.fansSourceCode = value;
	}

    public String getFansSourceCode() {
		return this.fansSourceCode;
	}

    public void setIsMember(String value) {
		this.isMember = value;
	}

    public String getIsMember() {
		return this.isMember;
	}

    public void setMemberId(Long value) {
		this.memberId = value;
	}

    public Long getMemberId() {
		return this.memberId;
	}

    public void setBuyTimes(Long value) {
		this.buyTimes = value;
	}

    public Long getBuyTimes() {
		return this.buyTimes;
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
			.append("FANS_NAME",getFansName())
			.append("MOBILE",getMobile())
			.append("OPEN_ID",getOpenId())
			.append("NICK_NAME",getNickName())
			.append("FANS_SOURCE_CODE",getFansSourceCode())
			.append("IS_MEMBER",getIsMember())
			.append("MEMBER_ID",getMemberId())
			.append("BUY_TIMES",getBuyTimes())
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
		if(!(obj instanceof Fans)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		Fans other = (Fans)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

