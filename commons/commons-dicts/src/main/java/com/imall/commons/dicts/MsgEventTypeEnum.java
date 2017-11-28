package com.imall.commons.dicts;

public enum MsgEventTypeEnum implements ICodeEnum {

    /**
     * 上报地理位置事件
     */
    LOCATION("LOCATION", "上报地理位置事件"),

    /**
     * 事件类型：subscribe(订阅)  关注事件 和 扫描带参数二维码事件(未关注时)
     */
    SUBSCRIBE("subscribe", "订阅"),

    /**
     * 扫描带参数二维码事件(已关注时)
     */
    SCAN("SCAN", "扫描带参数二维码事件(已关注时)"),

    /**
     * 事件类型：unsubscribe(取消订阅)  取消关注事件
     */
    UNSUBSCRIBE("unsubscribe", "取消订阅"),

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    CLICK("CLICK", "自定义菜单点击事件"),

    /**
     * 点击菜单跳转链接时的事件推送
     */
    VIEW("VIEW", "点击菜单跳转链接时的事件推送");

    private String code;
    private String name;

    private MsgEventTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static MsgEventTypeEnum fromCode(String code) {
        for (MsgEventTypeEnum msgTypeEnum : values()) {
            if (msgTypeEnum.code.equals(code)) {
                return msgTypeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(StockCheckStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    public String toCode() {
        return code;
    }

    public String toName() {
        return name;
    }
}
