package com.imall.commons.dicts;

/**
 * 设备 类型
 */
public enum DeviceTypeCodeEnum implements ICodeEnum{
    TEMPERATURE_AND_HUMIDITY_TESTING("TEMPERATURE_AND_HUMIDITY_TESTING", "温湿度检测设备"),
    REFRIGERATION("REFRIGERATION", "冷藏设备"),
    CHINESE_HERBAL_PIECES_DISPENSING("CHINESE_HERBAL_PIECES_DISPENSING", "中药饮片调配设备"),
    PHARMACEUTICAL_DISMANTLE("PHARMACEUTICAL_DISMANTLE", "药品拆零设备"),
    SPECIAL_FOR_DRUG_MANAGEMENT("SPECIAL_FOR_DRUG_MANAGEMENT", "特殊管理药品专用设备"),
    LIGHT_AVOIDING("LIGHT_AVOIDING", "避光设备"),
    VENTILATION("VENTILATION", "通风设备"),
    FIRE_FIGHTING("FIRE_FIGHTING", "消防设备"),
    LIGHTING("LIGHTING", "照明设备"),
    ACCEPTANCE_MAINTENANCE("ACCEPTANCE_MAINTENANCE", "验收养护设备");

    private String code;
    private String name;

    DeviceTypeCodeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static DeviceTypeCodeEnum fromCode(String code) {
        for (DeviceTypeCodeEnum deviceTypeCodeEnum : DeviceTypeCodeEnum.values()) {
            if (deviceTypeCodeEnum.code.equals(code)) {
                return deviceTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(DeviceTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
