package com.wx.wxapi;

import java.util.HashMap;
import java.util.Map;

import cn.gc80.base.util.ConfigUtil;

import com.wx.wxapi.json.AccessToken;
import com.wx.wxapi.json.JsApiTicket;
import com.wx.wxapi.util.HttpUtil;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 公众平台API 设备专用API定义在DeviceApi
 */
public class MpApi {

	private static final String GetAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
			+ ConfigUtil.getConfig("appid") + "&secret="+ ConfigUtil.getConfig("secret");
	private static final String CustomSendUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	private static final String CreateMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	private static final String QueryMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	private static final String DeleteMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	private static final String GetTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	private static final String GetUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	/**
	 * 获取访问凭证
	 * <p>
	 * 正常情况下access_token有效期为7200秒，重复获取将导致上次获取的access_token失效。
	 * 由于获取access_token的api调用次数非常有限，需要全局存储与更新access_token <br/>
	 * 文档位置：基础支持->获取access token
	 */
	public static AccessToken getAccessToken() {
		String resultContent = HttpUtil.executeGet(GetAccessTokenUrl);
		return AccessToken.fromJson(resultContent);
	}

	/**
	 * 获取jsapi_ticket临时票据
	 * 
	 * @return
	 */
	public static JsApiTicket getJsApiTicket() {
		String resultContent = HttpUtil.doGet(GetTicketUrl);
		return JsApiTicket.fromJson(resultContent);
	}

	/**
	 * 获取用户信息
	 * 
	 * @param openID
	 * @return user
	 *         <p>
	 *         subscribe 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 <br>
	 *         openid 用户的标识，对当前公众号唯一 nickname 用户的昵称 <br>
	 *         sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知<br>
	 *         city 用户所在城市<br>
	 *         country 用户所在国家 <br>
	 *         province 用户所在省份 <br>
	 *         language 用户的语言，简体中文为zh_CN<br>
	 *         headimgurl 用户头像，最后一个数值代表正方形头像大小 （有0、46、64、96、132数值可选，0代表640*640
	 *         正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。<br>
	 *         subscribe_time 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间<br>
	 *         unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制） <br>
	 *         remark 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 <br>
	 *         groupid 用户所在的分组ID
	 */
	public static Map<String,Object> getUserInfo(String openID) {
		Map<String,Object> map = new HashMap<String, Object>();
		if (openID == null || openID.equals("")) {
			System.out.println("OpenID错误");
			return null;
		}
		String url = GetUserInfoUrl.replace("OPENID", openID);
		String jsonInfo = HttpUtil.doGet(url);
		System.out.println(jsonInfo);
		JSONObject jb = new JSONObject();
		jb = JSONObject.fromObject(jsonInfo);
		if (!(jb.getString("subscribe")).equals("1")) {
			System.out.println("该用户未关注本微信公众号");
			return null;
		}
		try {
			map.put("subscribe", Integer.parseInt(jb.getString("subscribe")));
			map.put("openid", jb.getString("openid"));
			/*user.setSubscribe(Integer.parseInt(jb.getString("subscribe")));
			user.setOpenID(jb.getString("openid"));
			user.setNickname(jb.getString("nickname"));
			user.setSex(Integer.parseInt(jb.getString("sex")));
			user.setCity(jb.getString("city"));
			user.setCountry(jb.getString("country"));
			user.setProvince(jb.getString("province"));
			user.setLanguage(jb.getString("language"));
			user.setHeadImgUrl(jb.getString("headimgurl"));
			user.setSubscribe_time(jb.getString("subscribe_time"));
			user.setUnionID(jb.getString("unionid"));
			user.setRemark(jb.getString("remark"));
			user.setGroupID(Integer.parseInt(jb.getString("groupid")));*/
		} catch (JSONException e) {
			// do nothing
		}
		return map;
	}

	/**
	 * 发送客服消息 <br/>
	 * 文档位置：发送消息->发送客服消息
	 */
	public static void customSend(String body) {
		System.out.println("customSend body=" + body);
		HttpUtil.doPost(CustomSendUrl, body);
	}

	/**
	 * 发送客服文本消息
	 */
	public static void customSendText(String touser, String content) {
		JSONObject json = new JSONObject();
		json.put("touser", touser);
		json.put("msgtype", "text");
		JSONObject text = new JSONObject();
		text.put("content", content);
		json.put("text", text);
		customSend(json.toString());
	}

	/**
	 * 创建自定义菜单
	 * <p>
	 * 文档位置：自定义菜单->自定义菜单创建接口
	 */
	public static String menuCreate(String body) {
		return HttpUtil.doPost(CreateMenuUrl, body);
	}

	/**
	 * 查询自定义菜单
	 * <p>
	 * 文档位置：自定义菜单->自定义菜单查询接口
	 */
	public static String menuQuery() {
		return HttpUtil.doGet(QueryMenuUrl);
	}

	/**
	 * 删除自定义菜单
	 * <p>
	 * 文档位置：自定义菜单->自定义菜单删除接口
	 */
	public static String menuDelete() {
		return HttpUtil.doGet(DeleteMenuUrl);
	}

}
