package com.imall.commons.dicts;

public enum OutInStockLogSourceTypeCodeEnum implements ICodeEnum{

	PURCHASE_IN_STOCK("PURCHASE_IN_STOCK", "采购入库"),
	OUT_STOCK("OUT_STOCK", "销售出库"),
	SELL_RETURNED("SELL_RETURNED", "销售退货"),
	RETURNED_PURCHASE("RETURNED_PURCHASE", "采购退货"),
	STOCK_CHECK("STOCK_CHECK", "盘点"),
	STORAGE_SPACE_MOVE("STORAGE_SPACE_MOVE", "货位移动"),
	OTHER_OUT_STOCK("OTHER_OUT_STOCK", "其他出库"),
	OTHER_IN_STOCK("OTHER_IN_STOCK", "其他入库"),
	DRUG_IN_BUCKET("DRUG_IN_BUCKET", "装斗"),
	DRUG_CLEAR_BUCKET("DRUG_CLEAR_BUCKET", "清斗"),
	DRUG_LOCK("DRUG_LOCK", "药品锁定"),
	DRUG_DESTROY("DRUG_DESTROY", "药品销毁"),
	RELEASE_LOCK("RELEASE_LOCK", "解除锁定"),
	NOT_QUALIFIED("NOT_QUALIFIED", "不合格"),
	STOP_SALE("STOP_SALE", "停售"),
	RELEASE_SALE("RELEASE_SALE", "解停");


	private String code;
	private String name;

	OutInStockLogSourceTypeCodeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static OutInStockLogSourceTypeCodeEnum fromCode(String code) {
		for (OutInStockLogSourceTypeCodeEnum codeEnum : OutInStockLogSourceTypeCodeEnum.values()) {
			if (codeEnum.code.equals(code)) {
				return codeEnum;
			}
		}

		String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
				"".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
		throw new RuntimeException(OutInStockLogSourceTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
