package com.imall.commons.dicts;

public enum MsgTypeEnum implements ICodeEnum {
    /**
     * 返回消息类型：文本
     */
    TEXT("text", "返回消息类型：文本"),

    /**
     * 请求消息类型：图片
     */
    IMAGE("image", "请求消息类型：图片"),

    /**
     * 请求消息类型：语音
     */
    VOICE("voice", "请求消息类型：语音"),

    /**
     * 请求消息类型：视频
     */
    VIDEO("video", "请求消息类型：视频"),

    /**
     * 返回消息类型：音乐
     */
    MUSIC("music", "返回消息类型：音乐"),

    /**
     * 返回消息类型：图文
     */
    NEWS("news", "返回消息类型：图文"),

    /**
     * 请求消息类型：链接
     */
    LINK("link", "请求消息类型：链接"),

    /**
     * 请求消息类型：地理位置
     */
    LOCATION("location", "请求消息类型：地理位置"),

    /**
     * 请求消息类型：推送
     */
    EVENT("event", "请求消息类型：推送");

    private String code;
    private String name;

    private MsgTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static MsgTypeEnum fromCode(String code) {
        for (MsgTypeEnum msgTypeEnum : values()) {
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
