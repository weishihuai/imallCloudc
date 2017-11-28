package com.imall.commons.dicts;

/**
 * 证件类型
 */
public enum CertTypeCodeEnum implements ICodeEnum{
    IDENTIFICATION("IDENTIFICATION", "身份证"),
    CERTIFICATE_OF_OFFICERS("CERTIFICATE_OF_OFFICERS", "军官证"),
    PASSPORT("PASSPORT", "护照");

    private String code;
    private String name;

    CertTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CertTypeCodeEnum fromCode(String code) {
        for (CertTypeCodeEnum storageSpaceTypeCodeEnum : CertTypeCodeEnum.values()) {
            if (storageSpaceTypeCodeEnum.code.equals(code)) {
                return storageSpaceTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(CertTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
