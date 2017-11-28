package com.imall.commons.dicts;

/**
 * 商品 批次 状态 Enum
 * Created by HWJ on 2017/8/8
 */
public enum GoodsBatchStateCodeEnum implements ICodeEnum {
    /**
     * 正常
     */
    NORMAL("NORMAL", "正常"),
    /**
     * 锁定
     */
    LOCK("LOCK", "锁定"),
    /**
     * 待销毁
     */
    WAIT_DESTROY("WAIT_DESTROY", "待销毁"),
    /**
     * 停售
     */
    STOP_SALE("STOP_SALE", "停售"),
    /**
     * 过期
     */
    OVERDUE("OVERDUE", "过期"),
    /**
     * 已销毁
     */
    DELETE("DELETE", "已销毁");

    private String code;
    private String name;

    GoodsBatchStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static GoodsBatchStateCodeEnum fromCode(String code) {
        for (GoodsBatchStateCodeEnum goodsBatchStateCodeEnum : GoodsBatchStateCodeEnum.values()) {
            if (goodsBatchStateCodeEnum.code.equals(code)) {
                return goodsBatchStateCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(GoodsBatchStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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