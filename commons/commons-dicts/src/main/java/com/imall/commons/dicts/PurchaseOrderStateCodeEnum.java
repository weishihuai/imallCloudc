package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/7.
 * 采购订单状态Enum
 */
public enum PurchaseOrderStateCodeEnum implements ICodeEnum {
    /**
     * 待收货
     */
    WAIT_RECEIVE("WAIT_RECEIVE", "待收货"),
    /**
     * 待验收
     */
    WAIT_ACCEPTANCE("WAIT_ACCEPTANCE", "待验收"),
    /**
     * 已清
     */
    CLEAR("CLEAR", "已清"),
    /**
     * 已取消
     */
    CANCEL("CANCEL", "已取消");

    private String code;
    private String name;

    PurchaseOrderStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PurchaseOrderStateCodeEnum fromCode(String code) {
        for (PurchaseOrderStateCodeEnum purchaseOrderStateCodeEnum : PurchaseOrderStateCodeEnum.values()) {
            if (purchaseOrderStateCodeEnum.code.equals(code)) {
                return purchaseOrderStateCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(PurchaseOrderStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
