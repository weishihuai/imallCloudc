package com.imall.commons.dicts;

public enum FileTypeCodeEnum implements ICodeEnum{

	FILE_TYPE("FILE_TYPE", "文件类型"),
	/**
	 * 图像
	 */
	IMAGE("IMAGE","图像"),
	/**
	 * 文本 
	 */
	TEXT("TEXT","文本"),
	/**
	 * OFFICE文件
	 */
	OFFICE("OFFICE","OFFICE文件"),
	/**
	 * 声音
	 */
	SOUND("SOUND","声音"),
	/**
	 * FLASH
	 */
	FLASH("FLASH","FLASH"),
	/**
	 * 视频
	 */
	VIDEO("VIDEO","视频"),
	/**
	 * 其他
	 */
	OTHER("OTHER","其他");

	private String code;
	private String name;

	FileTypeCodeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static FileTypeCodeEnum fromCode(String code) {
		for (FileTypeCodeEnum codeEnum : FileTypeCodeEnum.values()) {
			if (codeEnum.code.equals(code)) {
				return codeEnum;
			}
		}
		String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
				"".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
		throw new RuntimeException(FileTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
