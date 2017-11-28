package com.imall.commons.dicts;

/**
 * Created by ou on 2017/7/5.
 * 药品 供应商 审核 状态Enum
 */
public enum FirstApproveTypeCodeEnum implements ICodeEnum {

    PURCHASE_APPROVE("PURCHASE_APPROVE", "采购审核"),
    QUALITY_APPROVE("QUALITY_APPROVE", "质量审核"),
    ENT_APPROVE("ENT_APPROVE", "企业审核");

    private String code;
    private String name;

    FirstApproveTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static FirstApproveTypeCodeEnum fromCode(String code) {
        for (FirstApproveTypeCodeEnum storageSpaceTypeCodeEnum : FirstApproveTypeCodeEnum.values()) {
            if (storageSpaceTypeCodeEnum.code.equals(code)) {
                return storageSpaceTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(FirstApproveTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
