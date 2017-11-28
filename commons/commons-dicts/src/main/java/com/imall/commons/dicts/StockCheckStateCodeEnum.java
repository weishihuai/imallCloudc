package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/21.
 * 库存盘点状态Enum
 */
public enum StockCheckStateCodeEnum implements ICodeEnum {
    /**
     * 已盘点
     */
    CHECKED("CHECKED", "已盘点"),
    /**
     * 未盘点
     */
    UNCHECKED("UNCHECKED", "未盘点"),
    /**
     * 已取消
     */
    CANCEL("CANCEL", "已取消"),
    /**
     * 未过账
     */
    UN_POST("UN_POST", "未过账");

    private String code;
    private String name;

    StockCheckStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StockCheckStateCodeEnum fromCode(String code) {
        for (StockCheckStateCodeEnum stockCheckStatCodeEnum : StockCheckStateCodeEnum.values()) {
            if (stockCheckStatCodeEnum.code.equals(code)) {
                return stockCheckStatCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(StockCheckStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
