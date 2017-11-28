package com.imall.commons.dicts;

/**
 * Created by caidapao on 2017/7/6.
 * 审核状态Enum
 */
public enum ApproveStateCodeEnum implements ICodeEnum {
    /**
     * 待审核
     */
    WAIT_APPROVE("WAIT_APPROVE", "待审核"),
    /**
     * 已审核
     */
    PASS_APPROVE("PASS_APPROVE", "已审核"),
    /**
     * 已驳回
     */
    REJECTED("REJECTED", "已驳回");


    private String code;
    private String name;

    ApproveStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ApproveStateCodeEnum fromCode(String code) {
        for (ApproveStateCodeEnum goodsTypeCodeEnum : ApproveStateCodeEnum.values()) {
            if (goodsTypeCodeEnum.code.equals(code)) {
                return goodsTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(ApproveStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
