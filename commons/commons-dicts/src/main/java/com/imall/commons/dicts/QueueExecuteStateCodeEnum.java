package com.imall.commons.dicts;

/**
 * Created by ZJC on 2016/9/7.
 */
public enum QueueExecuteStateCodeEnum implements ICodeEnum {
    QUEUE_EXECUTE_STATE("QUEUE_EXECUTE_STATE", "队列执行状态"),
    UN_PROCESSED("UN_PROCESSED","未处理"),
    PROCESSING("PROCESSING","处理中");

    private String code;
    private String name;

    QueueExecuteStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static QueueExecuteStateCodeEnum fromCode(String code) {
        for (QueueExecuteStateCodeEnum codeEnum : QueueExecuteStateCodeEnum.values()) {
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
