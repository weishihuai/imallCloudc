package com.imall.commons.dicts;

/**
 * 事件类型
 */
public enum EventTypeCodeEnum implements ICodeEnum {

    EVENT_TYPE("EVENT_TYPE", "事件类型"),

    WEIXIN_TEMPLATE_MSG("WEIXIN_TEMPLATE_MSG","微信模版消息");

    private String code;
    private String name;

    EventTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EventTypeCodeEnum fromCode(String code) {
        for (EventTypeCodeEnum codeEnum : EventTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(EventTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
