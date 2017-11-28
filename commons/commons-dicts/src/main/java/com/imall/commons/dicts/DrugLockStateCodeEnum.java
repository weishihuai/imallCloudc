package com.imall.commons.dicts;

/**
 * 药品锁定状态Enum
 */
public enum DrugLockStateCodeEnum implements ICodeEnum {

    PENDING("PENDING", "待处理"),
    WAIT_DESTROY("WAIT_DESTROY", "待销毁"),
    UNLOCK("UNLOCK", "已解锁"),
    DESTROYED("DESTROYED", "已销毁");

    private String code;
    private String name;

    DrugLockStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DrugLockStateCodeEnum fromCode(String code) {
        for (DrugLockStateCodeEnum storageSpaceTypeCodeEnum : DrugLockStateCodeEnum.values()) {
            if (storageSpaceTypeCodeEnum.code.equals(code)) {
                return storageSpaceTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(DrugLockStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
