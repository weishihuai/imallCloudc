
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
@Table(name = "t_shp_we_chat_user" )
public class WeChatUser extends BaseEntity<Long>{

	public static final String NICK_NAME = "nickName";
	public static final String USER_NAME = "userName";
	public static final String OPEN_ID = "openId";
	public static final String MOBILE = "mobile";
	public static final String IS_SUBSCRIBE = "isSubscribe";
	public static final String USER_HEAD_PICT = "userHeadPict";

    /** NICK_NAME - 昵称 */
    @Column(name = "NICK_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String nickName;
	/** NICK_NAME - 用户 名称 */
	@Column(name = "USER_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private String userName;
    /** OPEN_ID - OPEN ID */
    @Column(name = "OPEN_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
    private String openId;
	/** MOBILE - 手机 */
	@Column(name = "MOBILE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private String mobile;
	/** IS_SUBSCRIBE - 是否 关注 公众号 */
	@Column(name = "IS_SUBSCRIBE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private String isSubscribe;
	/** USER_HEAD_PICT - 用户 头像 */
	@Column(name = "USER_HEAD_PICT", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private String userHeadPict;

    public void setNickName(String value) {
		this.nickName = value;
	}

    public String getNickName() {
		return this.nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setOpenId(String value) {
		this.openId = value;
	}

    public String getOpenId() {
		return this.openId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getUserHeadPict() {
		return userHeadPict;
	}

	public void setUserHeadPict(String userHeadPict) {
		this.userHeadPict = userHeadPict;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("NICK_NAME",getNickName())
			.append("OPEN_ID",getOpenId())
			.append("MOBILE",getMobile())
			.append("IS_SUBSCRIBE",getIsSubscribe())
			.append("USER_HEAD_PICT",getUserHeadPict())
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
		if(!(obj instanceof WeChatUser)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		WeChatUser other = (WeChatUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

