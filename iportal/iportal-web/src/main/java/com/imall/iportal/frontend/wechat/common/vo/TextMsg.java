package com.imall.iportal.frontend.wechat.common.vo;

/**
 * 文本消息
 *
 */
public class TextMsg extends BaseMsg {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}