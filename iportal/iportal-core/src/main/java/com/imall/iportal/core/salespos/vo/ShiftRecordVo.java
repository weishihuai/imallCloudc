package com.imall.iportal.core.salespos.vo;

import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.salespos.entity.ShiftRecord;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by ygw on 2017/7/31.
 */
public class ShiftRecordVo extends ShiftRecord {

    private String succeedWhoName;

    private String posManName;

    public String getSucceedWhoName() {
        return succeedWhoName;
    }

    public void setSucceedWhoName(String succeedWhoName) {
        this.succeedWhoName = succeedWhoName;
    }

    public String getPosManName() {
        return posManName;
    }

    public void setPosManName(String posManName) {
        this.posManName = posManName;
    }


    public java.lang.String getIsHasShiftString() {
        return BoolCodeEnum.fromCode(getIsHasShift()).toName();
    }
}
