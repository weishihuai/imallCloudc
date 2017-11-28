package com.imall.commons.dicts;

/**
 * Created by ygw on 2017/7/7.
 * 订单来源Enum
 * 销售POS端、微信
 */
public enum OrderSourceCodeEnum implements ICodeEnum {
    /**
     * 销售POS端
     */
    SALES_POS("SALES_POS", "销售POS端"),
    /**
    * 微信
   */
    WEIXIN("WEIXIN", "微信");

    private String code;
    private String name;

    OrderSourceCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static OrderSourceCodeEnum fromCode(String code) {
        for (OrderSourceCodeEnum orderSourceCodeEnum : OrderSourceCodeEnum.values()) {
            if (orderSourceCodeEnum.code.equals(code)) {
                return orderSourceCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(OrderSourceCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
