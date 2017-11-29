package cn.gc80.web.business.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import sun.net.util.IPAddressUtil;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayCore;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wx.wxapi.WebApi;
import com.wx.wxapi.util.JsApiTicketUtil;
import com.wx.wxapi.util.SHA1Util;
import com.wx.wxpay.WXPay;
import com.wx.wxpay.WXPayConfigImpl;
import com.wx.wxpay.WXPayConstants;
import com.wx.wxpay.WXPayUtil;
import com.wx.wxpay.WXPayConstants.SignType;

import cn.gc80.base.BaseController;
import cn.gc80.base.util.ClientIpUtil;
import cn.gc80.base.util.ConfigUtil;
import cn.gc80.base.util.DateTime;
import cn.gc80.base.util.HttpClientUtil;
import cn.gc80.base.util.MemCached;
import cn.gc80.datamodel.business.EOrder;
import cn.gc80.datamodel.business.EOrderDetail;
import cn.gc80.datamodel.business.ShoppingCart;
import cn.gc80.datamodel.sysbase.SysUser;
import cn.gc80.datamodel.training.TrainClass;
import cn.gc80.web.business.service.OrderService;
import cn.gc80.web.user.service.UserService;

@Controller
public class OrderContorller extends BaseController {
	@Resource
	private OrderService orderService;
	@Resource
	private UserService userService;
	
	//加入购物车(公需、专业)
	@RequestMapping("/order/doShoppingCart.do")
	public ModelAndView doShoppingCart(HttpServletRequest request,HttpServletResponse response,String classId,String courseIds) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		String result=this.orderService.addShoppingCart(userId,classId,courseIds);
		map.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//加入购物车(转岗)
	@RequestMapping("/order/doShoppingCartZG.do")
	public ModelAndView doShoppingCartZG(HttpServletRequest request,HttpServletResponse response,String classId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		String result=this.orderService.addShoppingCart(userId,classId);
		map.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//查看购物车
	@RequestMapping("/order/toShoppingCart.do")
	public ModelAndView toShoppingCart(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		List<ShoppingCart> sCartList=this.orderService.findShoppingCart(userId);
		if(sCartList!=null&&sCartList.size()>0){
			map.put("result", 1);
			map.put("sCartList", sCartList);
		}else{
			map.put("result", 0);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//删除购物车班级
	@RequestMapping("/order/doDelShoppingCartByClassIds.do")
	public ModelAndView doDelShoppingCartByClassIds(HttpServletRequest request,HttpServletResponse response,String classIds) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		String result=this.orderService.doDelShoppingCartByClassIds(userId,classIds);
		if(result!=null&&!"".equals(result)){
			map.put("result", 1);
		}else{
			map.put("result", 0);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//购物车课程查看
	@RequestMapping("/order/toCourseCk.do")
	public ModelAndView toCourseCk(HttpServletRequest request,HttpServletResponse response,String classId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		List<ShoppingCart> sCartList=this.orderService.findShoppingCartByClassId(userId,classId);
		if(sCartList!=null&&sCartList.size()>0){
			map.put("result", 1);
			map.put("sCartList", sCartList);
		}else{
			map.put("result", 0);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	
	//删除购物车课程
	@RequestMapping("/order/doDelShoppingCartByCourseId.do")
	public ModelAndView doDelShoppingCartByCourseId(HttpServletRequest request,HttpServletResponse response,String courseId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		String result=this.orderService.doDelShoppingCartByCourseId(userId,courseId);
		if(result!=null&&!"".equals(result)){
			map.put("result", 1);
		}else{
			map.put("result", 0);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//生成订单
	@RequestMapping("/order/toGenerateOrder.do")
	public ModelAndView toGenerateOrder(HttpServletRequest request,HttpServletResponse response,String classIds) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		//获取设备类型
		String equipment= (String) request.getAttribute("equipment");
		String result=this.orderService.generateOrder(userId,classIds,equipment);
		if(result!=null&&!"".equals(result)){
			if("2".equals(result)||"3".equals(result)){
				//2所选班级已不在购物车
				//3您有未支付订单，不能再次报名，请前往学习中心的缴费订单模块处理
				map.put("result", result);
			}else{
				//订单生成成功
				map.put("result", "1");
				map.put("orderId", result);
			}
		}else{
			//订单生成错误
			map.put("result", 0);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//查询订单
	@RequestMapping("/order/toPayOrder.do")
	public ModelAndView toPayOrder(HttpServletRequest request,HttpServletResponse response,String orderId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		if(orderId!=null&&!"".equals(orderId)){
			EOrder order=this.orderService.findOrderById(orderId);
			if(order!=null){
				map.put("ordId", order.getId());
				map.put("ordNo", order.getOrdNo());
				map.put("ordTotalMoney", order.getOrdTotalMoney());
			}
			List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
			List<EOrderDetail> odList=this.orderService.findOrderDetail(orderId);
			for(int i=0;i<odList.size();i++){
				Map<String,Object> m=new HashMap<String, Object>();
				EOrderDetail od=odList.get(i);
				if(od!=null){
					TrainClass tClass=od.getTrainClass();
					if(tClass!=null){
						m.put("className", tClass.getClassName());
						m.put("classImage", tClass.getClassImage());
						m.put("cityName", tClass.getProvince().getCityname());
						m.put("codeNameParent", tClass.getTrainProject().getSysCode().getSysCode().getCodeName());
						m.put("codeName", tClass.getTrainProject().getSysCode().getCodeName());
					}
					m.put("credit", od.getCredit());
					m.put("price", od.getPrice());
				}
				list.add(m);
			}
			map.put("list", list);
			SysUser user=this.userService.findUserByUserId(userId);
			if(user!=null&&!"".equals(user)){
				map.put("balance", user.getBalance());
				map.put("surplusIntegral", user.getSurplusIntegral());
			}
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//微信注入权限验证配置
	@RequestMapping("/order/toWxPayInitByUrl.do")
	public ModelAndView toWxPayInitByUrl(HttpServletRequest request,HttpServletResponse response,String url,String code) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		// 根据code获取openID
		if (code == null || code.equals("")) {
			map.put("result", 0);
			map.put("resultMsg", "获取code参数失败");
		} else {
	        try {
	        	//获取uuid
	    		String uuid=request.getHeader("uuid");
	    		String openid=(String) MemCached.getInstance().get("openid-"+uuid);
	    		if(openid==null||"".equals(openid)){
	    			openid = WebApi.getAccessTokenByCode(code);
					//保存openid到memCached中
					MemCached.getInstance().addOrReplace("openid-"+uuid,openid);
	    		}
	        	String nonceStr=WXPayUtil.generateUUID();
				String jsapiTicket=JsApiTicketUtil.getJsApiTicketStr();
				String timeStamp=String.valueOf(System.currentTimeMillis());
				map.put("appId", ConfigUtil.getConfig("appid"));
		        map.put("timeStamp", timeStamp);
		        map.put("nonceStr", nonceStr);
		        Map<String,Object> data = new HashMap<String, Object>();
		        data.put("noncestr", nonceStr);
		        data.put("jsapi_ticket", jsapiTicket);
		        data.put("timestamp", timeStamp);
		        data.put("url", url);
	        	String signature =SHA1Util.SHA1(data);
				map.put("signature", signature);
				map.put("result", 1);
	        	map.put("resultMsg", "成功");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("result", 0);
				map.put("resultMsg", "系统错误");
			}
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//订单支付
	@RequestMapping("/order/payEnd.do")
	public synchronized ModelAndView payEnd(HttpServletRequest request,HttpServletResponse response,String orderId,String ordPaytype,String derateType) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		String result="0";
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		//获取设备类型
		String equipment= (String) request.getAttribute("equipment");
		if (orderId != null && !orderId.equals("")&&userId!=null&&!userId.equals("")){
			SysUser user=this.userService.findUserByUserId(userId);
			if(user!=null){
				EOrder order = orderService.findOrderById(orderId);			
				if (order != null){
					order.setOrdAccountMoney(0d);
					order.setOrdIntegral(0l);
					order.setOrdIntegralMoney(0d);
					order.setOrdMoneysum(order.getOrdTotalMoney());
					if(ordPaytype==null||"".equals(ordPaytype)){//默认支付宝支付
						order.setOrdPaytype("07");
					}else{
						order.setOrdPaytype(ordPaytype);
					}
					order.setLastedittime(DateTime.formatDateTime(new Date()));
					//减免的方式
					if("01".equals(derateType)&&!order.getOrdStatus().equals("20")){//账户方式
						if(user.getBalance()>=order.getOrdTotalMoney()){
							order.setOrdPaytype("00");
							order.setOrdMoneysum(0d);
							order.setOrdAccountMoney(order.getOrdTotalMoney());
							order = this.orderService.savePayByAccount(user, order,ClientIpUtil.getIpAddress(request),equipment);
							map.put("result", "1");
							view.setAttributesMap(map);
							modelAndView.setView(view);
							return modelAndView;
						}else{
							order.setOrdMoneysum(new BigDecimal(order.getOrdTotalMoney()-user.getBalance()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							order.setOrdAccountMoney(user.getBalance());
						}
					}else if("02".equals(derateType)&&!order.getOrdStatus().equals("20")){
						//积分方式
						if(user.getSurplusIntegral()>=order.getOrdTotalMoney()*30){
							order.setOrdPaytype("00");
							order.setOrdMoneysum(0d);
							order.setOrdIntegral((long)(order.getOrdTotalMoney()*30));
							order.setOrdIntegralMoney(order.getOrdTotalMoney());
							order =this.orderService.savePayByIntegral(user, order,ClientIpUtil.getIpAddress(request),equipment);
							map.put("result", "1");
							view.setAttributesMap(map);
							modelAndView.setView(view);
							return modelAndView;
						}else{
							order.setOrdMoneysum(new BigDecimal(order.getOrdTotalMoney()-user.getSurplusIntegral()/30d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							order.setOrdIntegral(user.getSurplusIntegral());
							order.setOrdIntegralMoney(new BigDecimal(user.getSurplusIntegral()/30d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						}
					}else{
							order.setOrdAccountMoney(0d);
							order.setOrdIntegral(0l);
							order.setOrdIntegralMoney(0d);
							order.setOrdMoneysum(order.getOrdTotalMoney());
					}
					
					if (order.getOrdPaytype().equals("01")){
						//在线支付生成缴费订单
						//String tmpOrderNo = this.orderService.getGenerateOrderNo("01");
						String tmpOrderNo = this.orderService.generateOrderNo(user.getUserName());
						if(order.getOrdTempNo()==null||"".equals(order.getOrdTempNo())){
							order.setOrdTempNo(tmpOrderNo);
						}else{
							order.setOrdTempNo(order.getOrdTempNo()+","+tmpOrderNo);
						}
					}
					/*
					if(order.getOrdPaytype().equals("06")){
						order.setOrdTempNo(order.getOrdNo());
					}
					*/
					
					if(order.getOrdPaytype().equals("01")&&!order.getOrdStatus().equals("20")){
						//在线缴费
						/*
						order.setOrdPaytime(DateTime.formatDateTime(new Date()));
						this.orderService.updateOrder(order);
						this.setSuccessView(this.doPayByNet(order));
						jmodel.clear();
						return;
						*/
						
					}else if (order.getOrdPaytype().equals("07")&&!order.getOrdStatus().equals("20")) {
						//手机支付宝
						System.out.println("-----AliPay--------");
						order.setOrdPaytime(DateTime.formatDateTime(new Date()));
						this.orderService.updateOrder(order);
						String WIDseller_email = ConfigUtil.getConfig("WIDseller_email");
						String WIDsubject = ConfigUtil.getConfig("WIDsubject");
						String WIDshow_url = ConfigUtil.getConfig("WIDshow_url");
						String WIDbody = ConfigUtil.getConfig("WIDbody");
						if(WIDshow_url==null){
							WIDshow_url = "";
						}
						if(WIDbody==null){
							WIDbody = "";
						}
						
						String sHtmlText = this.doPayByAli(order,WIDseller_email,WIDsubject,WIDshow_url,WIDbody);
						modelAndView.addObject("sHtmlText", sHtmlText);
						modelAndView.setViewName("web/alipayapi");
						return modelAndView;
						/*
						map.put("result", "1");
						String httpUrl = this.doPayByAliByUrl(order,WIDseller_email,WIDsubject,WIDshow_url,WIDbody);
						map.put("httpUrl", httpUrl);
						view.setAttributesMap(map);
						modelAndView.setView(view);
						return modelAndView;
						*/
					}else if (order.getOrdPaytype().startsWith("09")&&!order.getOrdStatus().equals("20")) {
						//微信公众号或者微信H5
						System.out.println("-----wxpay--------");
						order.setOrdPaytime(DateTime.formatDateTime(new Date()));
						this.orderService.updateOrder(order);
						HashMap<String, String> data = new HashMap<String, String>();
				        data.put("body", "安徽继续教育网报名订单");
				        data.put("out_trade_no", order.getOrdNo());
				        data.put("device_info", "WEB");
				        data.put("fee_type", "CNY");
				        data.put("total_fee", new BigDecimal(order.getOrdMoneysum()*100).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
				        data.put("spbill_create_ip", ClientIpUtil.getIpAddress(request));
				        data.put("notify_url", ConfigUtil.getConfig("wx_notify_url"));
				        if (order.getOrdPaytype().equals("0901")){
				        	data.put("trade_type", "JSAPI");
				        	//获取uuid
							String uuid=request.getHeader("uuid");
					        String openid = (String) MemCached.getInstance().get("openid-"+uuid);
					        if(openid==null || "".equals(openid)){
					        	map.put("result", "0");
					            map.put("resultMsg", "openid is null");
					        	view.setAttributesMap(map);
								modelAndView.setView(view);
								return modelAndView;
					        }
				        	data.put("openid", openid);
				        }else if(order.getOrdPaytype().equals("0902")){
				        	data.put("trade_type", "MWEB");
			        		Map<String, Object> sceneInfoMap=new HashMap<String, Object>();
				        	Map<String, String> infoMap=new HashMap<String, String>();
				        	infoMap.put("type", "Wap");
				        	infoMap.put("wap_url", ConfigUtil.getConfig("project"));
				        	infoMap.put("wap_name", "安徽继续教育网报名订单");
				        	sceneInfoMap.put("h5_info", infoMap);
				        	JSONObject jsonObject = JSONObject.fromObject(sceneInfoMap);
				        	data.put("scene_info", jsonObject.toString());
				        }
				        try {
				        	WXPay wxpay = new WXPay(WXPayConfigImpl.getInstance());
				            Map<String, String> r = wxpay.unifiedOrder(data);
				            if("SUCCESS".equals(r.get("return_code"))){
				            	if("SUCCESS".equals(r.get("result_code"))){
				            		if (order.getOrdPaytype().equals("0901")){
				            			String appId=r.get("appid");
					                    String timeStamp=String.valueOf(System.currentTimeMillis());
					                    String package_1="prepay_id="+r.get("prepay_id");
					                    String nonceStr=r.get("nonce_str");
					            		//签名或者返回数据
					            		HashMap<String, String> signMap = new HashMap<String, String>();
					            		signMap.put("appId", appId);
					            		signMap.put("timeStamp", timeStamp);
					            		signMap.put("nonceStr", nonceStr);
					            		signMap.put("package", package_1);
					            		Map<String, String> paySignMap=wxpay.generateSignatureMap(signMap);
					            		map.putAll(paySignMap);
				            		}else if (order.getOrdPaytype().equals("0902")){
				            			map.put("mWebUrl", r.get("mweb_url"));
				            		}
				            		map.put("result", "1");
				            	}else{
				            		map.put("result", "0");
						            map.put("resultMsg", r.get("err_code_des"));
				            	}
				            }else{
				            	map.put("result", "0");
					            map.put("resultMsg", r.get("return_msg"));
				            }
				        } catch (Exception e) {
				            e.printStackTrace();
				            map.put("result", "0");
				            map.put("resultMsg", "系统错误");
				        }
						view.setAttributesMap(map);
						modelAndView.setView(view);
						return modelAndView;
					}
				}
			}
		}
		map.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	private String doPayByAliByUrl(EOrder order,String WIDseller_email,String WIDsubject,String WIDshow_url,String WIDbody){
		//把请求参数打包成数组
		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url = ConfigUtil.getConfig("ali_notify_urll");
		//需http://格式的完整路径，不能加?id=123这类自定义参数

		//页面跳转同步通知页面路径
		String return_url = ConfigUtil.getConfig("ali_return_url");
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数

		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1
		Map<String, String> sParaTemp = new HashMap<String, String>();
		//sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", WIDseller_email);
		sParaTemp.put("out_trade_no", order.getOrdNo());
		sParaTemp.put("subject", WIDsubject);
		sParaTemp.put("total_fee", String.valueOf(order.getOrdMoneysum()));
		sParaTemp.put("body", WIDbody);
		sParaTemp.put("show_url", WIDshow_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
		
		//建立请求
		String httpUrl =AlipaySubmit.buildRequest(sParaTemp);
		
		return httpUrl;
	}

	public String doPayByAli(EOrder order,String WIDseller_email,String WIDsubject,String WIDshow_url,String WIDbody){
		//把请求参数打包成数组
		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url = ConfigUtil.getConfig("ali_notify_url");
		//需http://格式的完整路径，不能加?id=123这类自定义参数

		//页面跳转同步通知页面路径
		String return_url = ConfigUtil.getConfig("ali_return_url");
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数

		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1
		Map<String, String> sParaTemp = new HashMap<String, String>();
		//sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", WIDseller_email);
		sParaTemp.put("out_trade_no", order.getOrdNo());
		sParaTemp.put("subject", WIDsubject);
		sParaTemp.put("total_fee", String.valueOf(order.getOrdMoneysum()));
		sParaTemp.put("body", WIDbody);
		sParaTemp.put("show_url", WIDshow_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		//sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		return sHtmlText;
	}
	
	
	//订单支付回调
	@RequestMapping("/order/toPayReturn.html")
	public synchronized ModelAndView toPayReturn(HttpServletRequest request,HttpServletResponse response){
		System.out.println("---toPayReturn-----");
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		//获取支付宝GET过来反馈信息
		try {
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if(AlipayNotify.verify(params)){//验证成功
				System.out.println("验证成功");
				if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					EOrder order= this.orderService.updateOrder(out_trade_no, "20",ClientIpUtil.getIpAddress(request),DateTime.formatDateTime(new Date()));
					if(order!=null){
						map.put("result", "1");
					}else{
						map.put("result", "0");
					}
				}else{
					map.put("result", "0");
				}
			}else{
				System.out.println("验证失败");
				map.put("result", "0");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//订单支付回调notify_url
	@RequestMapping("/order/toPayNotify.html")
	public synchronized void toPayNotify(HttpServletRequest request,HttpServletResponse response){
		System.out.println("---toPayNotify-----");
		//获取支付宝GET过来反馈信息
		try {
			PrintWriter out = response.getWriter();
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//计算得出通知验证结果
			if(AlipayNotify.verify(params)){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					this.orderService.updateOrder(out_trade_no, "20",ClientIpUtil.getIpAddress(request),DateTime.formatDateTime(new Date()));
				}
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				out.print("success");	//请不要修改或删除
			}else{//验证失败
				out.print("fail");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//支付宝订单查询接口
	@RequestMapping("/order/toTradeQueryZFB.html")
	public synchronized ModelAndView toTradeQueryZFB(HttpServletRequest request,HttpServletResponse response,String orderNo){
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//支付宝交易号与商户网站订单号不能同时为空
			EOrder order =this.orderService.findOrderSomeByTmpNo(orderNo);
			if(order!=null){
				//把请求参数打包成数组
				Map<String, String> sParaTemp = new HashMap<String, String>();
				sParaTemp.put("service", "single_trade_query");
				sParaTemp.put("partner", AlipayConfig.partner);
				sParaTemp.put("_input_charset", AlipayConfig.input_charset);
				sParaTemp.put("trade_no", "");
				sParaTemp.put("out_trade_no", order.getOrdNo());
				//建立请求
				String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
				XMLSerializer xmls = new XMLSerializer();
				JSONObject obj = (JSONObject)xmls.read(sHtmlText);
				if(sHtmlText.indexOf("TRADE_FINISHED")!=-1||sHtmlText.indexOf("TRADE_SUCCESS")!=-1){
					JSONObject resp = obj.getJSONObject("response").getJSONObject("trade");
					String payTime = resp.getString("gmt_payment");
					this.orderService.updateOrder(order.getOrdNo(),"20",ClientIpUtil.getIpAddress(request),payTime);
					map.put("result", 1);
				}else if(sHtmlText.indexOf("TRADE_FINISHED")!=-1||sHtmlText.indexOf("TRADE_NOT_EXIST")!=-1){
					this.orderService.updateOrder(order.getOrdNo(),"01",ClientIpUtil.getIpAddress(request),"");
					map.put("result", 2);
				}else{
					map.put("result", 2);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			map.put("result", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", 0);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//订单列表
	@RequestMapping("/order/toOrderList.do")
	public ModelAndView toOrderList(HttpServletRequest request,HttpServletResponse response,String ordStatus) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		List<EOrder> orderList = this.orderService.findEOrder(userId,ordStatus);
		if(orderList!=null&&orderList.size()>0){
			map.put("result", 1);
			map.put("orderList", orderList);
		}else{
			map.put("result", 0);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//订单详情
	@RequestMapping("/order/toOrderDetail.do")
	public ModelAndView toOrderDetail(HttpServletRequest request,HttpServletResponse response,String orderId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		EOrder eOrder=this.orderService.findOrderById(orderId);
		if(eOrder!=null){
			map.put("ordNo",  eOrder.getOrdNo());
			map.put("ordPaytime",  eOrder.getOrdPaytime());
			map.put("ordMoneysum",  eOrder.getOrdMoneysum());
			map.put("ordTotalMoney",  eOrder.getOrdTotalMoney());
			map.put("ordType",  eOrder.getOrdType());
			map.put("ordPaytype",  eOrder.getOrdPaytype());
			map.put("ordPaySource",  eOrder.getOrdPaySource());
			map.put("ordAccountMoney",  eOrder.getOrdAccountMoney());
			map.put("ordIntegralMoney",  eOrder.getOrdIntegralMoney());
			map.put("ordStatus",  eOrder.getOrdStatus());
			map.put("invoiceClainType",  eOrder.getInvoiceClainType());
			if(eOrder.geteOrderInvoice()!=null &&!"".equals(eOrder.geteOrderInvoice())){
				map.put("invoiceBatch",  eOrder.geteOrderInvoice().getInvoiceBatch());
			}else{
				map.put("invoiceBatch",  "");
			}
		}
		List<EOrderDetail> orderDetailList=this.orderService.findOrderDetailById(orderId);
		if(orderDetailList !=null &&!"".equals(orderDetailList)){
			map.put("orderDetailList", orderDetailList);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//订单删除
	@RequestMapping("/order/toDeleterOrder.do")
	public ModelAndView toDeleterOrder(HttpServletRequest request,HttpServletResponse response,String orderId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		String result=this.orderService.doDeleterOrder(orderId);
		map.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//订单微信支付回调
	@RequestMapping("/order/toWxPayNotify.html")
	public synchronized String toWxPayNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("---wxPayNotify-----");
		String resXml = "";  
        InputStream inStream;  
        try {  
            inStream = request.getInputStream();  
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();  
            byte[] buffer = new byte[1024];  
            int len = 0;  
            while ((len = inStream.read(buffer)) != -1) {  
                outSteam.write(buffer, 0, len);  
            }  
            System.out.println("微信支付----付款成功----"); 
            outSteam.close();  
            inStream.close();  
            String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息  
            Map<String, String> map = WXPayUtil.xmlToMap(result);
            String resultCode=map.get("result_code");
            if (resultCode.equalsIgnoreCase("SUCCESS")) {  
            	System.out.println("微信支付----返回成功");  
            	WXPay wxpay = new WXPay(WXPayConfigImpl.getInstance());
                if (wxpay.isResponseSignatureValid(map)) {
                    // 订单处理 操作 orderconroller 的回写操作?  
                	System.out.println("微信支付----验证签名成功");  
                    // 处理业务 -修改订单支付状态  
                	String out_trade_no=map.get("out_trade_no");
                    System.out.println("微信支付回调：修改的订单=" + out_trade_no);  
                    this.orderService.updateOrder(out_trade_no, "20",ClientIpUtil.getIpAddress(request),DateTime.formatDateTime(new Date()));
                    // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
                }else{
                	 System.out.println("微信支付回调 ~~~~~~~~~~~~~~~~验证签名失败");  
                } 
                // ------------------------------  
                // 处理业务完毕  
                // ------------------------------  
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
                out.write(resXml.getBytes());  
                out.flush();  
                out.close();  
            }else {
            	System.out.println("支付失败,错误信息：" + map.get("err_code"));
            	resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
            }
        } catch (IOException e) {  
        	System.out.println("支付回调发布异常：" + e);  
            e.printStackTrace();  
        }  
        return resXml;  
	}
	
	//微信订单查询接口
	@RequestMapping("/order/toTradeQueryWx.html")
	public synchronized ModelAndView toTradeQueryWx(HttpServletRequest request,HttpServletResponse response,String orderNo){
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EOrder order =this.orderService.findOrderSomeByTmpNo(orderNo);
			if(order!=null){
				WXPay wxpay = new WXPay(WXPayConfigImpl.getInstance());
				HashMap<String, String> data = new HashMap<String, String>();
		        data.put("out_trade_no", order.getOrdTempNo());
		        Map<String, String> r = wxpay.orderQuery(data);
		        if("SUCCESS".equals(r.get("return_code"))){
	            	if("SUCCESS".equals(r.get("result_code"))){
	            		if("SUCCESS".equals(r.get("trade_state"))){
	            			this.orderService.updateOrder(r.get("out_trade_no"), "20",ClientIpUtil.getIpAddress(request),DateTime.formatDateTime(new Date()));
	            			map.put("result", "1");
	            			map.put("resultMsg", "支付成功");
	            		}else if("REFUND".equals(r.get("trade_state"))){
	            			map.put("result", "0");
				            map.put("resultMsg", "转入退款");
	            		}else if("NOTPAY".equals(r.get("trade_state"))){
	            			map.put("result", "0");
				            map.put("resultMsg", "未支付");
	            		}else if("CLOSED".equals(r.get("trade_state"))){
	            			map.put("result", "0");
				            map.put("resultMsg", "已关闭");
	            		}else if("REVOKED".equals(r.get("trade_state"))){
	            			map.put("result", "0");
				            map.put("resultMsg", "已撤销（刷卡支付）");
	            		}else if("USERPAYING".equals(r.get("trade_state"))){
	            			map.put("result", "0");
				            map.put("resultMsg", "用户支付中");
	            		}else if("PAYERROR".equals(r.get("trade_state"))){
	            			map.put("result", "0");
				            map.put("resultMsg", "支付失败(其他原因，如银行返回失败)");
	            		}else{
	            			map.put("result", 0);
	            			map.put("resultMsg", "其它错误");
	            		}
	            	}else{
	            		map.put("result", "0");
			            map.put("resultMsg", r.get("err_code_des"));
	            	}
	            }else{
	            	map.put("result", "0");
		            map.put("resultMsg", r.get("return_msg"));
	            }
			}else{
				map.put("result", 0);
				map.put("resultMsg", "订单号不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", 0);
			map.put("resultMsg", "系统错误");
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
}
