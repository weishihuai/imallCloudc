package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不合格药品记录 单据类型Enum
 */
public enum DocumentTypeCodeEnum implements ICodeEnum{

    DRUG_LOCK_DEAL("DRUG_LOCK_DEAL", "药品处理"),
    DRUG_LOCK_DESTROY("DRUG_LOCK_DESTROY", "药品销毁"),
    PURCHASE_RETURNED("PURCHASE_RETURNED", "采购退货"),
    PURCHASE_IN_STOCK("PURCHASE_IN_STOCK", "采购入库");

    private String code;
    private String name;

    DocumentTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DocumentTypeCodeEnum fromCode(String code) {
        for (DocumentTypeCodeEnum codeEnum : DocumentTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(DocumentTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
