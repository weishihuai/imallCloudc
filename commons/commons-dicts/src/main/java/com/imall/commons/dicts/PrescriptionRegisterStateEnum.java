package com.imall.commons.dicts;

/**
 * 处方 登记 状态
 */
public enum PrescriptionRegisterStateEnum implements ICodeEnum{
    WAIT_DISPENSING("WAIT_DISPENSING", "待调剂"),
    HAD_DISPENSING("HAD_DISPENSING", "已调剂");

    private String code;
    private String name;

    PrescriptionRegisterStateEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PrescriptionRegisterStateEnum fromCode(String code) {
        for (PrescriptionRegisterStateEnum storageSpaceTypeCodeEnum : PrescriptionRegisterStateEnum.values()) {
            if (storageSpaceTypeCodeEnum.code.equals(code)) {
                return storageSpaceTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(PrescriptionRegisterStateEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
