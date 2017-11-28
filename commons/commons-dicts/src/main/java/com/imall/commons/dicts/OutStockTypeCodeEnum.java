package com.imall.commons.dicts;

public enum OutStockTypeCodeEnum implements ICodeEnum{

	REPORTED_LOSS("REPORTED_LOSS", "报损"),
	OTHER("OTHER", "其他"),
	CHECK_OUT("CHECK_OUT", "抽检出库"),
	INTERNAL_USE("INTERNAL_USE", "内部领用");

	private String code;
	private String name;

	OutStockTypeCodeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static OutStockTypeCodeEnum fromCode(String code) {
		for (OutStockTypeCodeEnum codeEnum : OutStockTypeCodeEnum.values()) {
			if (codeEnum.code.equals(code)) {
				return codeEnum;
			}
		}

		String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
				"".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
		throw new RuntimeException(OutStockTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
