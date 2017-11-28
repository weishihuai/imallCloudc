package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  生产企业信息来源类型 Enum
 */
public enum BadReactionManufacturingEnterpriseInfSourceTypeCodeEnum implements ICodeEnum {
    /**
     * 医疗机构
     */
    MEDICAL_INSTITUTION("MEDICAL_INSTITUTION", "医疗机构"),
    /**
     * 经营企业
     */
    OPERATE_ENTERPRISE("OPERATE_ENTERPRISE", "经营企业"),
    /**
     * 个人
     */
    PERSONAL("PERSONAL", "个人"),
    /**
     * 上市后研究
     */
    POST_MARKETING_STUDY("POST_MARKETING_STUDY", "上市后研究"),
    /**
     * 文献报道
     */
    DOCUMENT_REPORT("DOCUMENT_REPORT", "文献报道"),
    /**
     * 其他
     */
    OTHER("OTHER", "其他");

    private String code;
    private String name;

    BadReactionManufacturingEnterpriseInfSourceTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionManufacturingEnterpriseInfSourceTypeCodeEnum fromCode(String code) {
        for (BadReactionManufacturingEnterpriseInfSourceTypeCodeEnum typeCodeEnum : BadReactionManufacturingEnterpriseInfSourceTypeCodeEnum.values()) {
            if (typeCodeEnum.code.equals(code)) {
                return typeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionManufacturingEnterpriseInfSourceTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
