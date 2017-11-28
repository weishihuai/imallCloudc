package com.imall.commons.dicts;

/**
 * Created by lxh on 2017/8/24.
 * 二维码类型
 */
public enum  QRCodeTypeCodeEnum implements ICodeEnum{
    QR_STR_SCENE("QR_STR_SCENE", "临时二维码"),
    QR_LIMIT_STR_SCENE("QR_LIMIT_STR_SCENE", "永久二维码");

    private String code;
    private String name;

    QRCodeTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static QRCodeTypeCodeEnum fromCode(String code) {
        for (QRCodeTypeCodeEnum codeEnum : QRCodeTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(QueueExecuteStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
