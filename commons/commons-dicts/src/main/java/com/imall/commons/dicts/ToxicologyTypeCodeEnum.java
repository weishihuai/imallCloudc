package com.imall.commons.dicts;

/**
 * Created by caidapao on 2017/7/6.
 * 商品毒理代码Enum
 */
public enum ToxicologyTypeCodeEnum implements ICodeEnum {
    /**
     * 有毒
     */
    DELETERIOUS("DELETERIOUS", "有毒"),
    /**
     * 无毒
     */
    NON_TOXIC("NON_TOXIC", "无毒");

    private String code;
    private String name;

    ToxicologyTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ToxicologyTypeCodeEnum fromCode(String code) {
        for (ToxicologyTypeCodeEnum goodsTypeCodeEnum : ToxicologyTypeCodeEnum.values()) {
            if (goodsTypeCodeEnum.code.equals(code)) {
                return goodsTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(ToxicologyTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
