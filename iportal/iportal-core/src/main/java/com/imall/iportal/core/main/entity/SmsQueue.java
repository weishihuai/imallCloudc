
package com.imall.iportal.core.main.entity;

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
@Table(name = "t_pt_sms_queue" )
public class SmsQueue extends BaseEntity<Long>{

    public static final String SYS_ORG_ID = "sysOrgId";
    public static final String RECEIVER_MOBILE = "receiverMobile";
    public static final String CONT = "cont";
    public static final String SEND_STAT_CODE = "sendStatCode";
    public static final String OBJECT_ID = "objectId";
    public static final String POSITION = "position";
    public static final String SEND_TIME = "sendTime";
    public static final String ERROR_LOG = "errorLog";
    public static final String ERROR_TIME = "errorTime";
    public static final String SMS_TYPE = "smsType";
    public static final String REQ_IP = "reqIp";

    /** SYS_ORG_ID - 组织 ID */
    @Column(name = "SYS_ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long sysOrgId;
    /** RECEIVER_MOBILE - 接收人 号码 */
    @Column(name = "RECEIVER_MOBILE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String receiverMobile;
    /** CONT - 内容 */
    @Column(name = "CONT", unique = false, nullable = false, insertable = true, updatable = true, length = 256)
    private String cont;
    /** SEND_STAT_CODE - 发送 状态 代码 */
    @Column(name = "SEND_STAT_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String sendStatCode;
    /** OBJECT_ID - 对象 ID */
    @Column(name = "OBJECT_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long objectId;
    /** POSITION - 排序 */
    @Column(name = "POSITION", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long position;
    /** SEND_TIME - 发送 时间 */
    @Column(name = "SEND_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date sendTime;
    /** ERROR_LOG - 失败 日志 */
    @Column(name = "ERROR_LOG", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String errorLog;
    /** ERROR_TIME - 发送 失败 时间 */
    @Column(name = "ERROR_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date errorTime;
    /** SMS_TYPE - 消息 类型 */
    @Column(name = "SMS_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String smsType;
    /** REQ_IP - 请求IP */
    @Column(name = "REQ_IP", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String reqIp;

    public void setSysOrgId(Long value) {
        this.sysOrgId = value;
    }

    public Long getSysOrgId() {
        return this.sysOrgId;
    }

    public void setReceiverMobile(String value) {
        this.receiverMobile = value;
    }

    public String getReceiverMobile() {
        return this.receiverMobile;
    }

    public void setCont(String value) {
        this.cont = value;
    }

    public String getCont() {
        return this.cont;
    }

    public void setSendStatCode(String value) {
        this.sendStatCode = value;
    }

    public String getSendStatCode() {
        return this.sendStatCode;
    }

    public void setObjectId(Long value) {
        this.objectId = value;
    }

    public Long getObjectId() {
        return this.objectId;
    }

    public void setPosition(Long value) {
        this.position = value;
    }

    public Long getPosition() {
        return this.position;
    }

    public void setSendTime(java.util.Date value) {
        this.sendTime = value;
    }

    public java.util.Date getSendTime() {
        return this.sendTime;
    }

    public String getSendTimeString() {
        return DateTimeUtils.convertDateTimeToString(this.getSendTime());
    }

    public void setSendTimeString(String value) {
        try {
            this.setSendTime(DateTimeUtils.convertStringToDateTime(value));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setErrorLog(String value) {
        this.errorLog = value;
    }

    public String getErrorLog() {
        return this.errorLog;
    }

    public void setErrorTime(java.util.Date value) {
        this.errorTime = value;
    }

    public java.util.Date getErrorTime() {
        return this.errorTime;
    }

    public String getErrorTimeString() {
        return DateTimeUtils.convertDateTimeToString(this.getErrorTime());
    }

    public void setErrorTimeString(String value) {
        try {
            this.setErrorTime(DateTimeUtils.convertStringToDateTime(value));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp;
    }

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("ID",getId())
                .append("SYS_ORG_ID",getSysOrgId())
                .append("RECEIVER_MOBILE",getReceiverMobile())
                .append("CONT",getCont())
                .append("SEND_STAT_CODE",getSendStatCode())
                .append("OBJECT_ID",getObjectId())
                .append("POSITION",getPosition())
                .append("SEND_TIME",getSendTime())
                .append("ERROR_LOG",getErrorLog())
                .append("ERROR_TIME",getErrorTime())
                .append("SMS_TYPE",getSmsType())
                .append("REQ_IP",getReqIp())
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
        if(!(obj instanceof SmsQueue)){
            return false;
        }
        if(this == obj) {
            return true;
        }
        SmsQueue other = (SmsQueue)obj;
        return new EqualsBuilder()
                .append(getId(),other.getId())
                .isEquals();
    }
}

