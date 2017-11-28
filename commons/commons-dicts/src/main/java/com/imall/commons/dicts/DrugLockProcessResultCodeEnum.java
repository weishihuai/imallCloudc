package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/5.
 * 商品货位类型Enum
 */
public enum DrugLockProcessResultCodeEnum implements ICodeEnum {

    UNLOCK("UNLOCK", "解锁"),
    DISQUALIFICATION("DISQUALIFICATION", "不合格");

    private String code;
    private String name;

    DrugLockProcessResultCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DrugLockProcessResultCodeEnum fromCode(String code) {
        for (DrugLockProcessResultCodeEnum storageSpaceTypeCodeEnum : DrugLockProcessResultCodeEnum.values()) {
            if (storageSpaceTypeCodeEnum.code.equals(code)) {
                return storageSpaceTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(DrugLockProcessResultCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
