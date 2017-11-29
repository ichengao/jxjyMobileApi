package com.wx.wxapi;

import java.util.List;

import com.wx.wxapi.json.DeviceAuth;
import com.wx.wxapi.util.HttpUtil;

import net.sf.json.JSONObject;

/**
 * 设备相关 API
 * <p>
 * https://api.weixin.qq.com/device/ 下的API为设备相关API， 测试号可以调用，正式服务号需要申请权限后才能调用。
 */
public class DeviceApi {
	private static final String TransMsgUrl = "https://api.weixin.qq.com/device/transmsg?access_token=ACCESS_TOKEN";
	private static final String AuthorizeUrl = "https://api.weixin.qq.com/device/authorize_device?access_token=ACCESS_TOKEN";
	private static final String CreateQrcode = "https://api.weixin.qq.com/device/create_qrcode?access_token=ACCESS_TOKEN";
	private static final String GetStatUrl = "https://api.weixin.qq.com/device/get_stat?access_token=ACCESS_TOKEN&device_id=DEVICE_ID";
	private static final String VerifyQrcodeUrl = "https://api.weixin.qq.com/device/verify_qrcode?access_token=ACCESS_TOKEN";
	private static final String GetOpenidUrl = "https://api.weixin.qq.com/device/get_openid?access_token=ACCESS_TOKEN&device_type=DEVICE_TYPE&device_id=DEVICE_ID";
	private static final String UnbindUrl = "https://api.weixin.qq.com/device/unbind?access_token=ACCESS_TOKEN";

	/**
	 * 解绑用户和设备
	 * 
	 * @param ticket
	 * @param deviceID
	 * @param openID
	 * @return
	 */
	public static String unbindDevice(String ticket, String deviceID,
			String openID) {
		JSONObject json = new JSONObject();
		json.put("ticket", ticket);
		json.put("device_id", deviceID);
		json.put("openid", openID);
		return HttpUtil.doPost(UnbindUrl, json.toString());
	}

	/**
	 * 向设备推送消息
	 * 
	 * @param deviceType
	 * @param deviceID
	 * @param openID
	 * @param content
	 * @return
	 */
	public static String transMsg(String deviceType, String deviceID,
			String openID, String content) {
		JSONObject json = new JSONObject();
		json.put("device_type", deviceType);
		json.put("device_id", deviceID);
		json.put("open_id", openID);
		json.put("content", content);
		return HttpUtil.doPost(TransMsgUrl, json.toString());
	}

	/**
	 * 根据设备id获取二维码生成串
	 * 
	 * @param deviceIds
	 * @return
	 */
	public static String createQrcode(List<String> deviceIds) {
		JSONObject json = new JSONObject();
		json.put("device_num", deviceIds.size());
		json.put("device_id_list", deviceIds);
		return HttpUtil.doPost(CreateQrcode, json.toString());
	}

	/**
	 * 批量授权/更新设备属性
	 * <p>
	 * 授权后设备才能进行绑定操作
	 * 
	 * @param devices
	 *            设备属性列表
	 * @param isCreate
	 *            是否首次授权： true 首次授权； false 更新设备属性
	 */
	public static String authorize(List<DeviceAuth> devices, boolean isCreate) {
		JSONObject json = new JSONObject();
		json.put("device_num", String.valueOf(devices.size()));
		json.put("op_type", isCreate ? "0" : "1");// 请求操作的类型 0：设备授权（缺省值为0）
													// 1：设备更新（更新已授权设备的各属性值）
		json.put("product_id", "27275");
		json.put("device_list", devices);
		return HttpUtil.doPost(AuthorizeUrl, json.toString());
	}

	/**
	 * 设备状态查询
	 * <p>
	 * status 0：未授权 1：已经授权（尚未被用户绑定） 2：已经被用户绑定<br/>
	 * {"errcode":0,"errmsg":"ok","status":1,"status_info":"authorized"}
	 */
	public static String getStat(String deviceId) {
		String url = GetStatUrl.replace("DEVICE_ID", deviceId);
		return HttpUtil.doGet(url);
	}

	/**
	 * 验证二维码 获取二维码对应设备属性
	 * 
	 * @param ticket
	 *            二维码生成串
	 */
	public static String verifyQrcode(String ticket) {
		JSONObject json = new JSONObject();
		json.put("ticket", ticket);
		return HttpUtil.doPost(VerifyQrcodeUrl, json.toString());
	}

	/**
	 * 根据设备类型和设备id查询绑定的openid
	 */
	public static String getOpenId(String deviceType, String deviceId) {
		String url = GetOpenidUrl.replace("DEVICE_TYPE", deviceType).replace(
				"DEVICE_ID", deviceId);
		return HttpUtil.doGet(url);
	}

	public static void main(String[] args) {
		// String deviceId = "gh_1bafe245c2cb_9e081608d6d62b984edf52d5d3a50aba";

		// 查询状态
		// System.out.println(getStat(deviceId));

		// String openid = "oub-Ys_7uYY53BYYPWnLv6JmmRag";
		// String deviceType = "gh_1bafe245c2cb";
		// 发送消息给设备
		// System.out.println(transMsg(deviceType, deviceId,
		// openid,"/s8AAQAMIAIAAAAA"));

		// verifyQrcode("http://we.qq.com/d/AQC5v5izyLn_1CG3ML9oax0ovqZ24jmySU_inUCW");
	}

}
