package com.imall.iportal.core.main.valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by ygw on 2016/5/17.
 */
public class SysParamInfBatchUpdateValid implements Cloneable, java.io.Serializable  {

    public static final long serialVersionUID = -15263788L;

    @NotBlank
    @Length(max = 128)
    private String innerCode;

    @NotBlank
    private String paramValueStr;

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public String getParamValueStr() {
        return paramValueStr;
    }

    public void setParamValueStr(String paramValueStr) {
        this.paramValueStr = paramValueStr;
    }
}
