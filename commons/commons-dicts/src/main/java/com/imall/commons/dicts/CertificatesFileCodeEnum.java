package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/5.
 * 供應商供应商资质文件Enum
 */
public enum CertificatesFileCodeEnum implements ICodeEnum {

    BUSINESS_LICENSE("BUSINESS_LICENSE", "营业执照号"),
    ORGANIZATION_CERTIFICATE("ORGANIZATION_CERTIFICATE", "组织机构代码证号"),
    GSP_CERTIFICATE("GSP_CERTIFICATE", "GSP证书号"),
    COMMODITY_LICENSE("COMMODITY_LICENSE", "经营许可证号"),
    QUALITY_AGREEMENT("QUALITY_AGREEMENT", "质量协议书号"),
    FOOD_CIRCULATION_LICENSE("FOOD_CIRCULATION_LICENSE", "食品流通许可证号"),
    FOOD_HYGIENE_LICENSE("FOOD_HYGIENE_LICENSE", "食品卫生许可证号"),
    HEALTH_PRODUCT_HYGIENE_LICENSE("HEALTH_PRODUCT_HYGIENE_LICENSE", "保健品卫生许可证号"),
    MEDIC_DEVICE_MANUFACTURE_PERMISS("MEDIC_DEVICE_MANUFACTURE_PERMISS", "医疗器械许可证号"),
    COSMETICS_BUSINESS_LICENSE("COSMETICS_BUSINESS_LICENSE", "化妆品经营许可证号"),
    COSMETICS_HYGIENIC_LICENSE("COSMETICS_HYGIENIC_LICENSE", "化妆品卫生许可证号");

    private String code;
    private String name;

    CertificatesFileCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CertificatesFileCodeEnum fromCode(String code) {
        for (CertificatesFileCodeEnum storageSpaceTypeCodeEnum : CertificatesFileCodeEnum.values()) {
            if (storageSpaceTypeCodeEnum.code.equals(code)) {
                return storageSpaceTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(CertificatesFileCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
