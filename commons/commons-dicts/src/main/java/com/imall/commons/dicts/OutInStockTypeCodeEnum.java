package com.imall.commons.dicts;

public enum OutInStockTypeCodeEnum implements ICodeEnum{

	OUT_STOCK("OUT_STOCK", "出库"),
	IN_STOCK("IN_STOCK", "入库");


	private String code;
	private String name;

	OutInStockTypeCodeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static OutInStockTypeCodeEnum fromCode(String code) {
		for (OutInStockTypeCodeEnum codeEnum : OutInStockTypeCodeEnum.values()) {
			if (codeEnum.code.equals(code)) {
				return codeEnum;
			}
		}

		String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
				"".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
		throw new RuntimeException(OutInStockTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
