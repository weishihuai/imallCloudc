package com.imall.commons.dicts;

/**
 * Created by caidapao on 2017/7/6.
 * 商品审核状态Enum
 */
public enum GoodsApproveStateCodeEnum implements ICodeEnum {
    /**
     * 待审核
     */
    WAIT_APPROVE("WAIT_APPROVE", "待审核"),
    /**
     * 已拒绝
     */
    DENY_APPROVE("DENY_APPROVE", "已拒绝"),
    /**
     * 已通过
     */
    PASS_APPROVE("PASS_APPROVE", "已通过");


    private String code;
    private String name;

    GoodsApproveStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static GoodsApproveStateCodeEnum fromCode(String code) {
        for (GoodsApproveStateCodeEnum goodsTypeCodeEnum : GoodsApproveStateCodeEnum.values()) {
            if (goodsTypeCodeEnum.code.equals(code)) {
                return goodsTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(GoodsApproveStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
