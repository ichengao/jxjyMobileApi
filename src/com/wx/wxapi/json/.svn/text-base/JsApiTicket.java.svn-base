package com.wx.wxapi.json;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * 调用微信JS接口 jsapi_ticket
 * 
 * @author WWKJ0123
 * 
 */
public class JsApiTicket implements Serializable {

	private static final long serialVersionUID = 1L;

	private String errcode;// 错误代码
	private String errmsg;// 错误信息
	private String ticket;// 微信公众号用于调用微信JS接口的临时票据
	private String expires_in;// 有限时长（单位：秒）
	private String createtime;// 创建时间（单位：毫秒）

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public static JsApiTicket fromJson(String json) {
		JsApiTicket ticket = (JsApiTicket) JSONObject.toBean(JSONObject.fromObject(json), JsApiTicket.class);
		ticket.setCreatetime(Long.toString(System.currentTimeMillis()));
		return ticket;
	}

}
