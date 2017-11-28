package com.imall.commons.dicts;


/**
 * Created by lxh on 2016/12/25.
 * 锁库存
 */
public enum LockStateCodeEnum implements ICodeEnum {
    LOCKED("LOCKED", "已上锁"),
    UNLOCK("UNLOCK", "已还原锁"),
    USE_LOCK("USE_LOCK", "已用锁");

    private String code;
    private String name;

    private LockStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static LockStateCodeEnum fromCode(String code) {
        for (LockStateCodeEnum codeEnum : values()) {
            if (codeEnum.toCode().equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(LockStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    @Override
    public String toName() {
        return name;
    }

    @Override
    public String toCode() {
        return code;
    }
}
