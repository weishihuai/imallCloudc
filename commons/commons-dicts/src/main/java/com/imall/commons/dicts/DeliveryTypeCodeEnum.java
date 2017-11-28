package com.imall.commons.dicts;

/**
 * Created by ygw on 2017/7/7.
 * 配送费用
 * 免配送费、收费配送、满额免费
 */
public enum DeliveryTypeCodeEnum implements ICodeEnum {
    /**
     * 免配送费,不需要支付
     */
    NEVER_PAY("NEVER_PAY", "免配送费"),
    /**
     * 收费配送,需要支付
     */
    NEED_PAY("NEED_PAY", "收费配送"),
    /**
     * 满额免费
     */
    FULL_AMOUNT_NOT_PAY("FULL_AMOUNT_NOT_PAY", "满额免费");

    private String code;
    private String name;

    DeliveryTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DeliveryTypeCodeEnum fromCode(String code) {
        for (DeliveryTypeCodeEnum deliveryTypeEnum : DeliveryTypeCodeEnum.values()) {
            if (deliveryTypeEnum.code.equals(code)) {
                return deliveryTypeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(DeliveryTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
