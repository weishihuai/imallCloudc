package com.imall.commons.dicts;

/**
 * Created by ygw on 2017/7/7.
 * 购物车类型Enum
 * 销售POS端、微信
 */
public enum CartTypeCodeEnum implements ICodeEnum {
    /**
     * 销售POS端
     */
    SALES_POS("PS", "销售POS端"),
    /**
    * 微信
   */
    WEIXIN("WE", "微信");

    private String code;
    private String name;

    CartTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CartTypeCodeEnum fromCode(String code) {
        for (CartTypeCodeEnum orderSourceCodeEnum : CartTypeCodeEnum.values()) {
            if (orderSourceCodeEnum.code.equals(code)) {
                return orderSourceCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(CartTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
