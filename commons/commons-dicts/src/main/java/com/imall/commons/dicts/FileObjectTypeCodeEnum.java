package com.imall.commons.dicts;

/**
 * Created by Donie on 2015/9/24.
 */
public enum FileObjectTypeCodeEnum implements ICodeEnum{
    SALES_CATEGORY_PICT("SALES_CATEGORY_PICT", "销售分类图片"),
    SHOP_MGR_WE_CHAT_PICT("SHOP_MGR_WE_CHAT_PICT", "门店店长微信二维码图片"),
    SHOP_LOGO("SHOP_LOGO", "门店图像图片"),
    SUPPLIER_PICT("SUPPLIER_PICT", "供应商文件图片"),
    SHOP_PICT("SHOP_PICT", "门店图片"),
    PRESCRIPTION_REGISTER_ANNEX("PRESCRIPTION_REGISTER_ANNEX", "处方登记附件"),
    GOODS_PICT("GOODS_PICT", "商品图片"),
    GOODS_DOC_PICT("GOODS_DOC_PICT", "商品档案图片"),
    GOODS_DOC_ATTACHMENT("GOODS_DOC_ATTACHMENT", "商品档案附件"),
    GOODS_ATTACHMENT("GOODS_ATTACHMENT", "商品附件");

    private String code;
    private String name;

    FileObjectTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static FileObjectTypeCodeEnum fromCode(String code) {
        for (FileObjectTypeCodeEnum codeEnum : FileObjectTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(FileObjectTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
