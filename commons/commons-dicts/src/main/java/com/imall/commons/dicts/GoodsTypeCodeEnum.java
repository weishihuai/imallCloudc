package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/6.
 * 商品类型Enum
 */
public enum GoodsTypeCodeEnum implements ICodeEnum {
    /**
     * 药品
     */
    DRUG("DRUG", "药品"),
    /**
     * 其他
     */
    OTHER("OTHER", "其他"),
    /**
     * 中药饮片
     */
    CHINESE_MEDICINE_PIECES("CHINESE_MEDICINE_PIECES", "中药饮片"),
    /**
     * 食品保健品
     */
    FOOD_HEALTH("FOOD_HEALTH", "食品保健品"),
    /**
     * 日用品
     */
    DAILY_NECESSITIES("DAILY_NECESSITIES", "日用品"),
    /**
     * 医疗器械
     */
    MEDICAL_INSTRUMENTS("MEDICAL_INSTRUMENTS", "医疗器械"),
    /**
     * 化妆品
     */
    COSMETIC("COSMETIC", "化妆品");

    private String code;
    private String name;

    GoodsTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static GoodsTypeCodeEnum fromCode(String code) {
        for (GoodsTypeCodeEnum goodsTypeCodeEnum : GoodsTypeCodeEnum.values()) {
            if (goodsTypeCodeEnum.code.equals(code)) {
                return goodsTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(GoodsTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
