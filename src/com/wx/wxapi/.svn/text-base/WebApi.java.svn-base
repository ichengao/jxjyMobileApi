package com.wx.wxapi;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import cn.gc80.base.util.ConfigUtil;

import com.wx.wxapi.util.HttpUtil;

/**
 * 
 * @author WWKJ0123
 * 
 */
public class WebApi {
	/** 通过code换取网页授权access_token的URL */
	private static final String GetAccessTokenByCodeUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
			+ ConfigUtil.getConfig("appid")
			+ "&secret="
			+ ConfigUtil.getConfig("secret")
			+ "&code=CODE&grant_type=authorization_code";

	/**
	 * 根据code获取AccessToken
	 * 
	 * @param code
	 * @return
	 */
	public static String getAccessTokenByCode(String code) {
		String url = GetAccessTokenByCodeUrl.replace("CODE", code);
		System.out.println(url);
		String jsonInfo = HttpUtil.doGet(url);
		JSONObject jb = new JSONObject();
		jb = JSONObject.fromObject(jsonInfo);
		// System.out.println("code ret json:\t" + jb.toString());
		try {
			String openid = jb.getString("openid");
			// System.out.println(openid);
			return openid;
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/** 测试用 */
	public static void main(String[] args) {
		String msg = getAccessTokenByCode("021EzEZD0uMnP82EokZD0v2GZD0EzEZj");
		System.out.println(msg);
	}

}
