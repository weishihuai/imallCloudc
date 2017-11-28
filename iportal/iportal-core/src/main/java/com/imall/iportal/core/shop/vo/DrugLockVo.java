package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * Created by ou on 2017/7/10.
 */
public class DrugLockVo {

    /**
     *  锁定 人 姓名
     */
    @NotBlank
    @Length(max=32)
    private String lockManName;
    /**
     * 锁定 时间
     */
    @NotBlank
    private String lockTimeString;

    /**
     * 药品锁定对象
     */
    @NotEmpty
    List<DrugLockSaveVo> drugLockSaveVoList;

    public String getLockManName() {
        return lockManName;
    }

    public void setLockManName(String lockManName) {
        this.lockManName = lockManName;
    }

    public String getLockTimeString() {
        return lockTimeString;
    }

    public void setLockTimeString(String lockTimeString) {
        this.lockTimeString = lockTimeString;
    }

    public List<DrugLockSaveVo> getDrugLockSaveVoList() {
        return drugLockSaveVoList;
    }

    public void setDrugLockSaveVoList(List<DrugLockSaveVo> drugLockSaveVoList) {
        this.drugLockSaveVoList = drugLockSaveVoList;
    }
}
