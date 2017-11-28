package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/4.
 * 计量器具 使用状态Enum
 */
public enum MeasureDeviceUseStateCodeEnum implements ICodeEnum {
    /**
     * 启用
     */
    ENABLED("ENABLED", "启用"),
    /**
     * 禁用
     */
    DISABLED("DISABLED", "禁用"),
    /**
     * 封存
     */
    SEAL_UP("SEAL_UP", "封存"),
    /**
     * 报废
     */
    SCRAP("SCRAP", "报废");

    private String code;
    private String name;

    MeasureDeviceUseStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static MeasureDeviceUseStateCodeEnum fromCode(String code) {
        for (MeasureDeviceUseStateCodeEnum stateCodeEnum : MeasureDeviceUseStateCodeEnum.values()) {
            if (stateCodeEnum.code.equals(code)) {
                return stateCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(MeasureDeviceUseStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
