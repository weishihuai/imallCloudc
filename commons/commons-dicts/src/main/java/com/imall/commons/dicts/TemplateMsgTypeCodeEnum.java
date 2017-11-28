package com.imall.commons.dicts;

/**
 * 微信消息模板
 */
public enum TemplateMsgTypeCodeEnum implements ICodeEnum {

    /**
     * {{first.DATA}}
     订单号：{{keyword1.DATA}}
     支付时间：{{keyword2.DATA}}
     支付金额：{{keyword3.DATA}}
     支付方式：{{keyword4.DATA}}
     {{remark.DATA}}
     * */
    ORDER_PAY("a7dbWDbCaVQvaRMCMk74YcKWW7Fk0qzK13J7vZpbT1Q","订单付款成功通知"),

    /**
     * {{first.DATA}}			——您好，您的订单已经配送
     订单号：{{keyword1.DATA}}
     订单时间：{{keyword2.DATA}}
     总价：{{keyword3.DATA}}
     收件人：{{keyword4.DATA}}
     收件地址：{{keyword5.DATA}}
     {{remark.DATA}}
     * */
    ORDER_SEND("kJ1JBUqyap3ODFIFJNZWYvgKHhGv5PEGGP1YmPerwUo","商品配送通知"),

    /**
     * {{first.DATA}}
     充值时间：{{keyword1.DATA}}
     充值金额：{{keyword2.DATA}}
     充值方式：{{keyword3.DATA}}
     当前余额：{{keyword4.DATA}}
     {{remark.DATA}}
     * */
    MEMBER_RECHARGE("QmC4ZeXk6bYROGJ6X-1mn1Dq18Nao7sEyVvjj1SsDkg","会员充值通知"),
    /**
     * {{first.DATA}}		——根据您的消费记录，立即评价订单，即可获得XX元返现。
     商品名称：{{keyword1.DATA}}  ——填写订单号即可
     评价时间：{{keyword2.DATA}}  ——发送消息的当前时间
     获得返现：{{keyword3.DATA}}
     {{remark.DATA}}		——点击本消息详情，进入订单评价
     * */
    COMMENT_CASH_BACK_NOTICE("8xSVwFHVJ9mYYqacMfxBWyK2kksk82SpBqKKBzkx7rg","评价返现提醒"),
    /**
     * {{first.DATA}}
     业务类型：{{keyword1.DATA}}
     开通时间：{{keyword2.DATA}}
     截至时间：{{keyword3.DATA}}
     {{remark.DATA}}
     * */
    CARD_OPEN("oU1W4uTwxPirhEjby8cV5PCKbk1Zsq3R3hjtjrzk49o","包月卡开通"),
    /**
     * {{first.DATA}}
     服务项目：{{keyword1.DATA}}
     到期时间：{{keyword2.DATA}}
     {{remark.DATA}}
     */
    RESIDUE_DEGRE("pYI-gb6J4mMSl8iySbJsUnI7lp0zD5wotJpSCe-4vc4","包月剩余次数提醒");


    private String code;
    private String name;

    TemplateMsgTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TemplateMsgTypeCodeEnum fromCode(String code) {
        for (TemplateMsgTypeCodeEnum codeEnum : TemplateMsgTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(TemplateMsgTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
