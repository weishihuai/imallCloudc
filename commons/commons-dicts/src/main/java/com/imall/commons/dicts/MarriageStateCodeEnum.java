package com.imall.commons.dicts;

/**
 * Created by caidapao on 2017/7/6.
 * 审核状态Enum
 */
public enum MarriageStateCodeEnum implements ICodeEnum {
    /**
     * 未婚
     */
    SINGLE("SINGLE", "未婚"),
    /**
     * 已婚
     */
    UNMARRIED("UNMARRIED", "已婚"),
    /**
     * 离异
     */
    DIVORCED("DIVORCED", "离异"),
    /**
     * 保密
     */
    SECRECY("SECRECY", "保密");


    private String code;
    private String name;

    MarriageStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static MarriageStateCodeEnum fromCode(String code) {
        for (MarriageStateCodeEnum goodsTypeCodeEnum : MarriageStateCodeEnum.values()) {
            if (goodsTypeCodeEnum.code.equals(code)) {
                return goodsTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(MarriageStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
