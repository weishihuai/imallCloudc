package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/5.
 * 商品货位类型Enum
 */
public enum StorageSpaceTypeCodeEnum implements ICodeEnum {
    /**
     * 商品货位
     */
    GOODS_STORAGE_SPACE("GOODS_STORAGE_SPACE", "商品货位"),
    /**
     * 中药饮片
     */
    CHINESE_HERBAL_MEDICINE("CHINESE_HERBAL_MEDICINE", "中药饮片"),
    /**
     * 医疗器械
     */
    MEDICAL_APPARATUS_INSTRUMENTS("MEDICAL_APPARATUS_INSTRUMENTS", "医疗器械");

    private String code;
    private String name;

    StorageSpaceTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StorageSpaceTypeCodeEnum fromCode(String code) {
        for (StorageSpaceTypeCodeEnum storageSpaceTypeCodeEnum : StorageSpaceTypeCodeEnum.values()) {
            if (storageSpaceTypeCodeEnum.code.equals(code)) {
                return storageSpaceTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(StorageSpaceTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
