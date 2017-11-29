package com.wx.wxapi.json;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * 访问凭证
 */
public class AccessToken implements Serializable {

	private static final long serialVersionUID = 1L;

	private String access_token;// 令牌
	private long expires_in;// 有效时长 单位秒
	private long createTime;// 创建时间 单位毫秒

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public static AccessToken fromJson(String json) {
		AccessToken token = (AccessToken) JSONObject.toBean(JSONObject.fromObject(json), AccessToken.class);
		token.setCreateTime(System.currentTimeMillis());
		return token;
	}

}
