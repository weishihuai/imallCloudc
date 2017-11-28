package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/7.
 * 采购订单类型
 */
public enum PurchaseOrderTypeCodeEnum implements ICodeEnum {
    ON_LINE_ORDER("ON_LINE_ORDER", "线上订单"),

    OFF_LINE_ORDER("OFF_LINE_ORDER", "线下订单");

    private String code;
    private String name;

    PurchaseOrderTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PurchaseOrderTypeCodeEnum fromCode(String code) {
        for (PurchaseOrderTypeCodeEnum purchaseOrderTypeCodeEnum : PurchaseOrderTypeCodeEnum.values()) {
            if (purchaseOrderTypeCodeEnum.code.equals(code)) {
                return purchaseOrderTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(PurchaseOrderTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    @Override
    public String toCode() {
        return code;
    }

    @Override
    public String toName() {
        return name;
    }
}
