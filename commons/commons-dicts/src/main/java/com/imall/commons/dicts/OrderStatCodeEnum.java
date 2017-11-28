package com.imall.commons.dicts;

/**
 * Created by ygw on 2017/7/7.
 * 订单状态Enum
 * 待发货、已发货、已完成、已关闭。
 */
public enum OrderStatCodeEnum implements ICodeEnum {
    /**
     * 待发货：订单提交，更新订单创建时间
     */
    WAIT_SEND("WAIT_SEND", "待发货"),
    /**
     * 已发货：立即发货，更新订单确认时间
     */
    ALREADY_SENDED("ALREADY_SENDED", "已发货"),
    /**
     * 已完成：确认送达，更新订单完成时间
     */
    FINISH("FINISH", "已完成"),
    /**
     * 已关闭：买家拒收：更新订单关闭时间和备注
     */
    CLOSE("CLOSE", "已关闭");

    private String code;
    private String name;

    OrderStatCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static OrderStatCodeEnum fromCode(String code) {
        for (OrderStatCodeEnum orderStatCodeEnum : OrderStatCodeEnum.values()) {
            if (orderStatCodeEnum.code.equals(code)) {
                return orderStatCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(OrderStatCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
