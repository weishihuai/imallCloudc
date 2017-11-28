
package com.imall.iportal.core.main.entity;

import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_validate_code_log" )
public class ValidateCodeLog extends BaseEntity<Long> {

    public static final String SYS_ORG_ID = "sysOrgId";
    public static final String ACCOUNT = "account";
    public static final String VALIDATE_CODE = "validateCode";
    public static final String TYPE_CODE = "typeCode";
    public static final String INVALID_TIME = "invalidTime";
    public static final String OBJECT_ID = "objectId";

    /** SYS_ORG_ID - 组织 ID */
    @Column(name = "SYS_ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long sysOrgId;
    /** ACCOUNT - 接收账户 */
    @Column(name = "ACCOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String account;
    /** VALIDATE_CODE - 验证码 */
    @Column(name = "VALIDATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String validateCode;
    /** TYPE_CODE - 验证码 类型 代码 */
    @Column(name = "TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String typeCode;
    /** INVALID_TIME - 失效时间 */
    @Column(name = "INVALID_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private Date invalidTime;
    /** OBJECT_ID - 对象 ID */
    @Column(name = "OBJECT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long objectId;

    public Long getSysOrgId() {
        return sysOrgId;
    }

    public void setSysOrgId(Long sysOrgId) {
        this.sysOrgId = sysOrgId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("ID",getId())
                .append("SYS_ORG_ID",getSysOrgId())
                .append("ACCOUNT",getAccount())
                .append("VALIDATE_CODE",getValidateCode())
                .append("TYPE_CODE",getTypeCode())
                .append("INVALID_TIME",getInvalidTime())
                .append("OBJECT_ID",getObjectId())
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
        if(!(obj instanceof ValidateCodeLog)){
            return false;
        }
        if(this == obj) {
            return true;
        }
        ValidateCodeLog other = (ValidateCodeLog)obj;
        return new EqualsBuilder()
                .append(getId(),other.getId())
                .isEquals();
    }
}

