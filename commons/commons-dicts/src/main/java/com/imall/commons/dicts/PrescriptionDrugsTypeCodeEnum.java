package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/6.
 * 商品类型Enum
 */
public enum PrescriptionDrugsTypeCodeEnum implements ICodeEnum {

    /**
     * RX
     */
    RX("RX", "处方药"),
    /**
     * OTC
     */
    OTC("OTC", "非处方药"),
    /**
     * 双轨制
     */
    SG("SG", "双轨制");

    private String code;
    private String name;

    PrescriptionDrugsTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PrescriptionDrugsTypeCodeEnum fromCode(String code) {
        for (PrescriptionDrugsTypeCodeEnum goodsTypeCodeEnum : PrescriptionDrugsTypeCodeEnum.values()) {
            if (goodsTypeCodeEnum.code.equals(code)) {
                return goodsTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(PrescriptionDrugsTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
