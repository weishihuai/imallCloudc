package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  报告单位类型 Enum
 */
public enum BadReactionRepDepartmentTypeCodeEnum implements ICodeEnum {
    /**
     * 医疗机构
     */
    MEDICAL_INSTITUTION("MEDICAL_INSTITUTION", "医疗机构"),
    /**
     * 经营企业
     */
    OPERATE_ENTERPRISE("OPERATE_ENTERPRISE", "经营企业"),
    /**
     * 生产企业
     */
    MANUFACTURING_ENTERPRISE("MANUFACTURING_ENTERPRISE", "生产企业"),
    /**
     * 个人
     */
    PERSONAL("PERSONAL", "个人"),
    /**
     * 其他
     */
    OTHER("OTHER", "其他");

    private String code;
    private String name;

    BadReactionRepDepartmentTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionRepDepartmentTypeCodeEnum fromCode(String code) {
        for (BadReactionRepDepartmentTypeCodeEnum typeCodeEnum : BadReactionRepDepartmentTypeCodeEnum.values()) {
            if (typeCodeEnum.code.equals(code)) {
                return typeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionRepDepartmentTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
