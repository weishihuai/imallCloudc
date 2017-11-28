package com.imall.commons.dicts;

/**
 * User: yang
 *系统参数 文件，密码，单行文本，多行文本，html，数字，金额，日期，时间
 */
public enum ParamTypeCodeEnum implements ICodeEnum {
    /**
	 * 文件
	 */
	FILE("FILE", "文件"),
	/**
	 * 密码
	 */
	PASSWORD("PASSWORD", "密码"),
	/**
	 * 单行文本
	 */
	TEXT("TEXT", "单行文本"),
	/**
	 * 多行文本
	 */
	MULTI_TEXT("MULTI_TEXT", "多行文本"),
	/**
	 * html
	 */
	HTML("HTML", "html"),
	/**
	 * 数字
	 */
	NUMBER("NUMBER", "数字"),
	/**
	 * 金额
	 */
	MONEY("MONEY", "金额"),
	/**
	 * 日期
	 */
	DATE("DATE", "日期"),
	/**
	 * 时间
	 */
	TIME("TIME", "时间");


	private String code;
	private String name;

	private ParamTypeCodeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static ParamTypeCodeEnum fromCode(String code) {
		for (ParamTypeCodeEnum codeEnum : ParamTypeCodeEnum.values()) {
			if (codeEnum.code.equals(code)) {
				return codeEnum;
			}
		}
		String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
				"".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
		throw new RuntimeException(ParamTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
