package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/28.
 * 库存类型Enum
 */
public enum StockTypeCodeEnum implements ICodeEnum {
    PURCHASE_IN_STOCK("PURCHASE_IN_STOCK", "采购入库"),
    PURCHASE_OUT_STOCK("PURCHASE_OUT_STOCK", "采购出库"),
    SELLS_RETURNED("SELLS_RETURNED", "销售退货"),
    PURCHASE_RETURNED("PURCHASE_RETURNED", "采购退货"),
    STOCK_CHECK("STOCK_CHECK", "盘点"),
    OTHER_OUT_STOCK("OTHER_OUT_STOCK", "其他出库"),
    OTHER_IN_STOCK("OTHER_IN_STOCK", "其他入库"),
    DRUG_IN_BUCKET("DRUG_IN_BUCKET", "装斗"),
    DRUG_CLEAR_BUCKET("DRUG_CLEAR_BUCKET", "清斗");

    private String code;
    private String name;

    StockTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StockTypeCodeEnum fromCode(String code) {
        for (StockTypeCodeEnum codeEnum : StockTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }

        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(StockTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
