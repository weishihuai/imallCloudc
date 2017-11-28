package com.imall.iportal.core.shop.vo;

import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.shop.entity.SalesCategory;

/**
 * Created by frt on 2017/7/6.
 */
public class SalesCategoryPageVo extends SalesCategory {
    public String getIsFrontendShowString(){
        return BoolCodeEnum.fromCode(this.getIsFrontendShow()).toName();
    }

    public String getIsEnableString(){
        if(BoolCodeEnum.YES==BoolCodeEnum.fromCode(this.getIsEnable())){
            return "启用";
        }else{
            return "禁用";
        }
    }
}
