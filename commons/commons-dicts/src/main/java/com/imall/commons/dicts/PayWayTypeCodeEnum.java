package com.imall.commons.dicts;

/**
 * Created by ZJC on 2016/1/18.
 */
public enum PayWayTypeCodeEnum implements ICodeEnum{

    PAY_WAY_TYPE("PAY_WAY_TYPE", "支付方式"),
    WEBCHAT_PAY("WEBCHAT_PAY", "微信支付"),
    RECHARGE_PAY("RECHARGE_PAY", "预存款支付"),
    ADMIN_PAY("ADMIN_PAY", "管理员支付"),
    ALIPAY_PAY("ALIPAY_PAY", "支付宝支付"),
    BANK_CARD_PAY("BANK_CARD_PAY", "银行卡支付"),
    CASH_PAY("CASH_PAY", "现金支付");


    private String code;
    private String name;

    PayWayTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PayWayTypeCodeEnum fromCode(String code) {
        for (PayWayTypeCodeEnum codeEnum : PayWayTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(PayWayTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    @Override
    public String toCode() {
        return code;
    }

    @Override
    public String toName(){
        return name;
    }
}
