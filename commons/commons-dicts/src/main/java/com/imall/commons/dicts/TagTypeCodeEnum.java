package com.imall.commons.dicts;

/**
 * Created by tgx on 2016/11/28.
 */
public enum TagTypeCodeEnum implements ICodeEnum{
    PRODUCT_TAG("PRODUCT_TAG", "产品标签"),
    GOODS_TAG("GOODS_TAG", "商品标签"),
    BUYERS_TAG("BUYERS_TAG", "采购商标签");


    private String code;
    private String name;

    TagTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TagTypeCodeEnum fromCode(String code) {
        for (TagTypeCodeEnum codeEnum : TagTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(TagTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
