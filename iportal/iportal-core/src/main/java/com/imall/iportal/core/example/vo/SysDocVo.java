package com.imall.iportal.core.example.vo;


import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.example.entity.SysDoc;

/**
 * TODO(Vo)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysDocVo extends SysDoc{

    public SysDocVo(){

    }

    public SysDocVo(SysDoc sysDoc){
        CommonUtil.copyProperties(this, sysDoc);
    }

}
