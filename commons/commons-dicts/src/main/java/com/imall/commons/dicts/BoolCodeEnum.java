package com.imall.commons.dicts;


/**
 * 是否值代码
 *
 */
public enum BoolCodeEnum implements ICodeEnum {

    BOOL_CODE("BOOL_CODE", "是否可用"),
    /**
     * 是
     */
    YES("Y", "是"),
    /**
     * 否
     */
    NO("N", "否");

    private String code;
    private String name;

    private BoolCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BoolCodeEnum fromCode(String code) {
        for (BoolCodeEnum boolCodeEnum : BoolCodeEnum.values()) {
            if (boolCodeEnum.code.equals(code)) {
                return boolCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BoolCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    public static BoolCodeEnum fromValue(boolean value) {
        if (value) {
            return YES;
        }
        return NO;
    }

    public static String toCode(boolean value) {
        if (value) {
            return YES.toCode();
        }
        return NO.toCode();
    }

    @Override
    public String toCode() {
        return code;
    }


    @Override
    public String toName(){
        return name;
    }

    public boolean boolValue() {
        return this.ordinal() == 1;
    }
}
