package com.imall.commons.dicts;

public enum IndexTypeCodeEnum implements ICodeEnum {

    INDEX_OBJECT_TYPE("INDEX_OBJECT_TYPE", "索引对象类型"),
    SYS_DOC("sys_doc", "文档"),
    GOODS("goods", "商品"),
    GOODS_BATCH("goods_batch", "商品"),
    OUT_IN_STOCK_LOG("out_in_stock_log", "出入库日志"),
    ORDER("order", "订单"),
    SELL_RETURNED_PURCHASE_ORDER("srporder", "销售退货订单"),
    WE_SHOP("we_shop", "微门店");

    private String code;
    private String name;

    IndexTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static IndexTypeCodeEnum fromCode(String code) {
        for (IndexTypeCodeEnum codeEnum : IndexTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }

        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(IndexTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    @Override
    public String toCode() {
        return code;
    }

    @Override
    public String toName(){
        return name;
    }
}
