package com.imall.commons.dicts;

/**
 * 退货类型Enum
 */
public enum ReturnedPurchaseTypeCodeEnum implements ICodeEnum {
    /**
     * 正常退货
     */
    NORMAL_RETURNED("NORMAL_RETURNED", "正常退货"),
    /**
     * 不合格退货
     */
    NOT_QUALIFIED_RETURNED("NOT_QUALIFIED_RETURNED", "不合格退货");

    private String code;
    private String name;

    ReturnedPurchaseTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ReturnedPurchaseTypeCodeEnum fromCode(String code) {
        for (ReturnedPurchaseTypeCodeEnum goodsTypeCodeEnum : ReturnedPurchaseTypeCodeEnum.values()) {
            if (goodsTypeCodeEnum.code.equals(code)) {
                return goodsTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(ReturnedPurchaseTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
