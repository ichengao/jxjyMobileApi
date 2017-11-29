package cn.gc80.web.user.contorller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import sun.misc.BASE64Decoder;
import cn.gc80.base.BaseController;
import cn.gc80.base.page.PageHolder;
import cn.gc80.base.util.ClientIpUtil;
import cn.gc80.base.util.ConfigUtil;
import cn.gc80.base.util.DateTime;
import cn.gc80.base.util.HttpClientUtil;
import cn.gc80.base.util.MD5Util;
import cn.gc80.base.util.MemCached;
import cn.gc80.base.util.SysSendUtil;
import cn.gc80.base.util.TokenUtil;
import cn.gc80.base.util.UploadFile;
import cn.gc80.datamodel.business.EUserAddress;
import cn.gc80.datamodel.integral.IntContrast;
import cn.gc80.datamodel.integral.IntIntegral;
import cn.gc80.datamodel.learning.LearnUserChapter;
import cn.gc80.datamodel.res.ResChapter;
import cn.gc80.datamodel.res.ResCourse;
import cn.gc80.datamodel.res.ResCourseware;
import cn.gc80.datamodel.sign.Sign;
import cn.gc80.datamodel.sysbase.SysProperty;
import cn.gc80.datamodel.sysbase.SysProvince;
import cn.gc80.datamodel.sysbase.SysUser;
import cn.gc80.datamodel.sysbase.SysUserInfo;
import cn.gc80.web.cert.service.CertService;
import cn.gc80.web.user.service.UserService;
@Controller
public class UserContorller extends BaseController {
	@Resource
	public UserService userService;
	@Resource
	public CertService certService;
	MD5Util md = new MD5Util();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//登录
	@RequestMapping("/user/doLogin.do")
	public ModelAndView doLogin(HttpServletRequest request,HttpServletResponse response,String userName,String password) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		//获取设备类型
		String equipment= (String) request.getAttribute("equipment");
		String result="";
		int loginErrNum=0;
		if(userName==null||"".equals(userName)||password==null||"".equals(password)){
			//帐号或者密码不能为空
			result="0";
		}else{
			SysUser sysUser=userService.findUserByParam(userName);
			if(sysUser!=null){
				if(sysUser.getStatus().equals("02")){
					if(sysUser.getLoginErrNum()<8){
						if(md.getMD5ofStr(password).equals(sysUser.getUserPasswd())){
							//生日积分
							this.userService.doBirthdayInteger(sysUser,ClientIpUtil.getIpAddress(request),equipment);
							//生成token
							String token=TokenUtil.getToken(sysUser.getId());
							result="1";
							map.put("token", token);
						}else{
							this.userService.updateLoginErrNum(sysUser.getId(),sysUser.getLoginErrNum()+1);
							if(8-sysUser.getLoginErrNum()-1==0){
								//你已用完8次输入密码错误机会，账号已锁定，请明天登录
								result="5";
							}else{
								//帐号或密码错误，每天有8次输入错误机会，你还有"+(8-sysUser.getLoginErrNum()-1)+"次机会！，如您忘记账号或密码，可在登录界面点击“忘记密码”找回（账号需绑定手机号码）
								result="4";
								loginErrNum=sysUser.getLoginErrNum();
							}
						}
					}else{
						//你已用完8次输入密码错误机会，账号已锁定，请明天登录
						result="5";
					}
				}else{
					//帐号未激活，请联系管理员
					result="3";
				}
			}else{
				//帐号不存在
				result="2";
			}
		}
		map.put("result", result);
		map.put("loginErrNum", loginErrNum);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//个人中心
	@RequestMapping("/user/toPersonalCenter.do")
	public ModelAndView toPersonalCenter(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		Map<String, Object> map = this.userService.findPersonalMap(userId,resultMap);
		map.put("result", "1");
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	
	//我的积分
	@RequestMapping("/user/toMyIntegralRecord.do")
	public ModelAndView toMyIntegralRecord(HttpServletRequest request,HttpServletResponse response,String staTime,String endTime,String pages,String pageSize) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		PageHolder ph = new PageHolder();
		if (pages == null || pages.equals("")) {
			ph.setPageNumber(1);
		} else {
			int pagesa = Integer.parseInt(pages);
			ph.setPageNumber(pagesa);
		}
		if (pageSize == null || pageSize.equals("")) {
			ph.setPageSize(10);
		} else {
			int pagesize = Integer.parseInt(pageSize);
			ph.setPageSize(pagesize);
		}
		ph=this.userService.findIntegralRecord(userId,staTime,endTime,ph);
		Map<String, Object> map = this.findPageInfo(ph.getDataList(),"list",resultMap,ph);
		map.put("newDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		SysUser user=this.userService.findUserByUserId(userId);
		map.put("totalIntegral", user.getTotalIntegral());
		map.put("surplusIntegral", user.getSurplusIntegral());
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//我的帐号
	@RequestMapping("/user/toMyAccount.do")
	public ModelAndView toMyAccount(HttpServletRequest request,HttpServletResponse response,String staTime,String endTime,String pages,String pageSize) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		PageHolder ph = new PageHolder();
		if (pages == null || pages.equals("")) {
			ph.setPageNumber(1);
		} else {
			int pagesa = Integer.parseInt(pages);
			ph.setPageNumber(pagesa);
		}
		if (pageSize == null || pageSize.equals("")) {
			ph.setPageSize(10);
		} else {
			int pagesize = Integer.parseInt(pageSize);
			ph.setPageSize(pagesize);
		}
		ph=this.userService.toMyAccount(userId,staTime,endTime,ph);
		Map<String, Object> map = this.findPageInfo(ph.getDataList(),"list",resultMap,ph);
		map.put("newDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	// 查询学员唯一性
	@RequestMapping("/user/toOnlyUser.html")
	public ModelAndView toOnlyUser(HttpServletRequest request,HttpServletResponse response,String parameter,String type) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		SysUser user=null;
		if("userName".equals(type)){
			user=this.userService.findUserByParam(parameter, "", "");
		}else if("mobile".equals(type)){
			user=this.userService.findUserByParam("", parameter, "");
		}else if("cardno".equals(type)){
			user=this.userService.findUserByParam("", "", parameter);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", user==null);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	// 发送短信验证码
	@RequestMapping("/user/toSendMessage.html")
	public ModelAndView toSendMessage(HttpServletRequest request,HttpServletResponse response,String mobile,String valideCode,String uuid) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		String result="0";
		//验证码校验
		String d ="";
		if(uuid!=null&&!"".equals(uuid)){
			d=(String) MemCached.getInstance().get(uuid);
		}else{
			d=(String) MemCached.getInstance().get("valideCode");
		}
		if (valideCode == null || d == null || !valideCode.toUpperCase().equals(d.toUpperCase())) {
			//验证码错误或者为空
			result="2";
		}else{
			result="1";
			//清空验证码
			MemCached.getInstance().delete(uuid);
			//启动发送短信线程
			MutliThread m1=new MutliThread(mobile,1);
		    m1.start();
		}
		map.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//发送短信线程
	class MutliThread extends Thread{
		private String mobile;
		private int type;
		MutliThread(String mobile,int type){
	        this.mobile=mobile;
	        this.type=type;
	    }
	    public void run(){
	    	try {
				int s = (int)((Math.random()*9+1)*100000);
				//保存短信验证码到memCached中
				MemCached.getInstance().addOrReplace("smsCode",String.valueOf(s));
				// 短信内容
				String msg= "验证短信验证码"+s+"，（有效期30分钟），请勿向任何人泄露";
				switch(type){
					case 1: 
						msg= "账号注册短信验证码"+s+"，（有效期30分钟），请勿向任何人泄露。";
						break; 
					case 2: 
						msg= "找回密码短信验证码"+s+"，（有效期30分钟），请勿向任何人泄露";
						break;
					case 3:
						msg= "原手机号验证短信验证码"+s+"，（有效期30分钟），请勿向任何人泄露";
						break;
					case 4:
						msg= "新手机号验证短信验证码"+s+"，（有效期30分钟），请勿向任何人泄露";
						break;
				}
				String util = SysSendUtil.batchSend(ConfigUtil.getConfig("sms_url"), ConfigUtil.getConfig("sms_account"), ConfigUtil.getConfig("sms_pswd"), mobile, msg, false, null, null);
				System.out.println("sms-result:"+util);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
	
	//获取地区信息
	@RequestMapping("/user/toArea.html")
	public ModelAndView toArea(HttpServletRequest request,HttpServletResponse response,String parent) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		String result="0";
		List<SysProvince> list=this.userService.findProvinceByParent(parent);
		if(list!=null&&list.size()>0){
			result="1";
			map.put("list", list);
		}
		map.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//注册
	@RequestMapping("/user/doRegister.html")
	public ModelAndView doRegister(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		//获取设备类型
		String equipment= (String) request.getAttribute("equipment");
		String userName=request.getParameter("userName");
		String mobile=request.getParameter("mobile");
		String smsCode=request.getParameter("smsCode");
		String password=request.getParameter("password");
		String realname=request.getParameter("realname");
		String cardno=request.getParameter("cardno");
		String area=request.getParameter("area");
		if(userName==null||"".equals(userName)||mobile==null||"".equals(mobile)||smsCode==null||"".equals(smsCode)||password==null||"".equals(password)
				||realname==null||"".equals(realname)||cardno==null||"".equals(cardno)||area==null||"".equals(area)){
			map.put("result", "0");
			view.setAttributesMap(map);
			modelAndView.setView(view);
			return modelAndView;
		}
		SysUser user=null;
		user=this.userService.findUserByParam(userName, "", "");
		if(user!=null){
			map.put("result", "2");
			view.setAttributesMap(map);
			modelAndView.setView(view);
			return modelAndView;
		}
		user=this.userService.findUserByParam("", mobile, "");
		if(user!=null){
			map.put("result", "3");
			view.setAttributesMap(map);
			modelAndView.setView(view);
			return modelAndView;
		}
		user=this.userService.findUserByParam("", "", cardno);
		if(user!=null){
			map.put("result", "4");
			view.setAttributesMap(map);
			modelAndView.setView(view);
			return modelAndView;
		}
		String ds = (String) MemCached.getInstance().get("smsCode");
		if (ds == null || !smsCode.toUpperCase().equals(ds.toUpperCase())) {
			map.put("result", "5");
			view.setAttributesMap(map);
			modelAndView.setView(view);
			return modelAndView;
		}
		user=new SysUser();
		user.setUserName(userName);
		user.setUserPasswd(md.getMD5ofStr(password));
		user.setCreatetime(DateTime.formatDateTime(new Date()));
		user.setLogonNum(0l);
		user.setBalance(0.00);
		user.setTotalConsumeAmount(0.00);
		user.setTotalIntegral(0l);
		user.setSurplusIntegral(0l);
		user.setStatus("02");
		user.setDelflag("02");
		user.setCompleteStatus("02");
		user.setArea(area);
		user.setRealname(realname);
		user.setCardno(cardno.toLowerCase());
		user.setUserType("01");
		user.setBindMobile(mobile);
		user.setMobile(mobile);
		user.setLoginErrNum(0);
		user.setEquipment(equipment);
		String uid=this.userService.doRegister(user,ClientIpUtil.getIpAddress(request),equipment);
		if(!"".equals(uid)){
			map.put("result", "1");
			String token=TokenUtil.getToken(uid);
			map.put("token", token);
		}else{
			map.put("result", "6");
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//我的培训班
	@RequestMapping("/user/toMyClass.do")
	public ModelAndView toMyClass(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		String result="0";
		List<Map<String,Object>> list=this.userService.findEOrderDetail(userId);
		if(list!=null&&list.size()>0){
			result="1";
			resultMap.put("list", list);
		}
		resultMap.put("result", result);
		view.setAttributesMap(resultMap);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//我的培训班课程
	@RequestMapping("/user/toMyClassCourse.do")
	public ModelAndView toMyClassCourse(HttpServletRequest request,HttpServletResponse response,String classId,String pages,String pageSize) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		PageHolder ph = new PageHolder();
		if (pages == null || pages.equals("")) {
			ph.setPageNumber(1);
		} else {
			int pagesa = Integer.parseInt(pages);
			ph.setPageNumber(pagesa);
		}
		if (pageSize == null || pageSize.equals("")) {
			ph.setPageSize(10);
		} else {
			int pagesize = Integer.parseInt(pageSize);
			ph.setPageSize(pagesize);
		}
		ph=this.userService.toMyClassCourse(userId,classId,ph);
		Map<String, Object> map = this.findPageInfo(ph.getDataList(),"list",resultMap,ph);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//我的课程课件
	@RequestMapping("/user/toMyCourseware.do")
	public ModelAndView toMyCourseware(HttpServletRequest request,HttpServletResponse response,String courseId,String pages,String pageSize) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		PageHolder ph = new PageHolder();
		if (pages == null || pages.equals("")) {
			ph.setPageNumber(1);
		} else {
			int pagesa = Integer.parseInt(pages);
			ph.setPageNumber(pagesa);
		}
		if (pageSize == null || pageSize.equals("")) {
			ph.setPageSize(10);
		} else {
			int pagesize = Integer.parseInt(pageSize);
			ph.setPageSize(pagesize);
		}
		ph=this.userService.toMyCourseware(userId,courseId,ph);
		Map<String, Object> map = this.findPageInfo(ph.getDataList(),"list",resultMap,ph);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//学时证明
	@RequestMapping("/user/findCredit.do")
	public ModelAndView findCredit(HttpServletRequest request,HttpServletResponse response,String classId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		Map<String, Object> map = this.certService.findCreditMap(userId,classId);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//视频学习
	@RequestMapping("/user/toStudy.do")
	public ModelAndView toStudy(HttpServletRequest request,HttpServletResponse response,String courseId,String coursewareId,String chapterId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		//String userId="ff8080815afa95a2015afe0351860071";
		if(chapterId==null){
			chapterId = "";
		}
		Map<String, Object> map=this.userService.updateUserCourseForStudyMap(userId, courseId, coursewareId, chapterId);
		ResCourse course = (ResCourse)map.get("course");
		if(course!=null){
			ResCourseware courseware = (ResCourseware)map.get("courseware");
			ResChapter chapter = (ResChapter)map.get("chapter");
			LearnUserChapter trackinfo = (LearnUserChapter)map.get("trackinfo");
			if(trackinfo!=null){
				resultMap.put("chapterhours", trackinfo.getHours());
				resultMap.put("suspendData", trackinfo.getSuspendData());
				resultMap.put("lessonStatus", trackinfo.getLessonstatus());
			}
			if(courseware!=null){
				resultMap.put("trackMode", courseware.getTrackMode());
				resultMap.put("cwName", courseware.getCwName());
			}
			resultMap.put("lessonLocation", map.get("lessonLocation"));
			resultMap.put("launch", map.get("launch"));
			if(chapter!=null){
				resultMap.put("chapterId", chapter.getId());
				resultMap.put("mediaduration", chapter.getPeriod());
			}
			resultMap.put("courseId", courseId);
			resultMap.put("coursewareId", coursewareId);
			resultMap.put("cName", course.getCName());
			List<SysProperty> list = this.userService.findPropertyList("rule_study");
			for(int i=0;i<list.size();i++){
				SysProperty prop = (SysProperty)list.get(i);
				if(prop.getPropKey().equals("rule_study_focus")){
					resultMap.put("isFocus", prop.getPropValue());
				}
				if(prop.getPropKey().equals("rule_study_media_time")){
					resultMap.put("interactionTime", prop.getPropValue());
				}
				if(prop.getPropKey().equals("rule_study_content")){
					resultMap.put("interactionContent", prop.getPropValue());
				}
			}
		}
		view.setAttributesMap(resultMap);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	@RequestMapping("/user/updateDBTrack.do")
	public ModelAndView updateDBTrack(HttpServletRequest request,HttpServletResponse response,String position, String duration,String courseId, 
			String coursewareId, String chapterId, String trackMode, String lessonStatus,String lessonLocation, String suspendData,String flag) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		boolean b=this.userService.updateDBTrack(position, duration, courseId, coursewareId, chapterId, userId, trackMode, lessonStatus, lessonLocation, suspendData, flag);
		resultMap.put("result", b);
		view.setAttributesMap(resultMap);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//寄送地址
	@RequestMapping("/user/toUserAddress.do")
	public ModelAndView listAddress(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = this.getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		List<EUserAddress> list = this.userService.getPageByUserId(userId);
		if(null != list && list.size()>0){
			map.put("result", "1");
			map.put("addList", list);
		} else {
			map.put("result", "0");
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//根据id获取寄送地址信息
	@RequestMapping("/user/toEdit.do")
	public ModelAndView toEdit(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = this.getResultMap(request, response);
		String addressId= request.getParameter("addressId");
		EUserAddress userAddress = this.userService.findById(addressId);
		map.put("userAddress", userAddress);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//保存地区信息
	@RequestMapping("/user/saveAddress.do")
	public ModelAndView saveAddress(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		String userId= (String) request.getAttribute("userId");
		String userName= request.getParameter("userName");
		String mobile= request.getParameter("mobile");
		String area= request.getParameter("area");
		String address=request.getParameter("address");
		String code= request.getParameter("code");
		String isDefault= request.getParameter("isDefault");
		String addressId= request.getParameter("addressId");
		EUserAddress userAddress = null;
		if(null == addressId || "".equals(addressId) || "undefined".endsWith(addressId)){
			userAddress = new EUserAddress();
			userAddress.setUserId(userId);
			userAddress.setId(UUID.randomUUID().toString());
			userAddress.setDelflag("02");
		} else {
			userAddress = this.userService.findById(addressId);
		}
		
		if(isDefault!=null&&"02".equals(isDefault)){
			List<EUserAddress> userAddressList = this.userService.findDefault(userId);
			if(!userAddressList.isEmpty()){
				for(int i=0;i<userAddressList.size();i++){
					EUserAddress userAddressd=userAddressList.get(i);
					userAddressd.setIsDefault("01");
					this.userService.updateAddress(userAddressd);
				}
				userAddress.setIsDefault(isDefault);
			}else{
				userAddress.setIsDefault(isDefault);
			}
		}else{
			userAddress.setIsDefault("01");
		}
		userAddress.setArea(area);
		userAddress.setAddress(address);
		userAddress.setPhone(mobile);
		userAddress.setName(userName);
		//userAddress.setAddressName(addressName);
		userAddress.setPostcode(code);
		if(null == addressId || "".equals(addressId) ||"undefined".equals(addressId)){
			this.userService.saveAddress(userAddress);
		} else {
			this.userService.updateAddress(userAddress);
		}
		Map<String, Object> map = this.getResultMap(request, response);
		map.put("result", "1");
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//删除寄送地址
	@RequestMapping("/user/delAddress.do")
	public ModelAndView delAddress(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		String addressId= request.getParameter("addId");
		this.userService.delAddress(addressId);
		Map<String, Object> map = this.getResultMap(request, response);
		map.put("result", "1");
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//个人资料
	@RequestMapping("/user/toPersonalData.do")
	public ModelAndView toPersonalData(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		String userId= (String) request.getAttribute("userId");
		Map<String, Object> map = this.userService.findPersonalDataMap(userId,resultMap);
		String result="0";
		if(map!=null && map.size()>0){
			result="1";
		}
		resultMap.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//修改个人资料
	@RequestMapping("/user/doUpdatePerson.do")
	public ModelAndView doUpdatePerson(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		//获取设备类型
		String equipment= (String) request.getAttribute("equipment");
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		SysUser newuser=null;
		if(userId!=null&&!userId.equals("")){
			newuser=this.userService.findUserByUserId(userId);
		}
		String type = request.getParameter("type");
		String sValue = request.getParameter("sValue");
		String image = request.getParameter("image");
		String imageName = request.getParameter("imageName");
		String sex = request.getParameter("sex");
		String degree = request.getParameter("degree");
		String phone="",email="",address="",
			   organization="",area="",infoAddress="",major="";
		if("phone".equals(type)){
			phone= sValue;
		}
		if("email".equals(type)){
			email= sValue;
		}
		if("address".equals(type)){
			address= sValue;
		}
		if("organization".equals(type)){
			organization= sValue;
		}
		if("area".equals(type)){
			area= sValue;
		}
		if("infoAddress".equals(type)){
			infoAddress= sValue;
		}
		if("major".equals(type)){
			major= sValue;
		}
		
		if(newuser.getTotalIntegral()==null){
			newuser.setTotalIntegral(0l);
		}
		if(newuser.getSurplusIntegral()==null){
			newuser.setSurplusIntegral(0l);
		}
		if(sex!=null&&!"".equals(sex)&&!"undefined".equals(sex)){
			newuser.setSex(sex);
		}
		if(area!=null&&!area.equals("")&&!area.equals("undefined")){
			newuser.setArea(area);
		} else {
			area=newuser.getArea();
		}
		if(address!=null&&!"".equals(address)&&!"undefined".equals(address)){
			newuser.setUserAddress(address);
		} 
		if(email!=null&&!"".equals(email)&&!"undefined".equals(email)){
			newuser.setEmail(email);
		}
		if(phone!=null&&!"".equals(phone)&&!"undefined".equals(phone)){
			newuser.setPhone(phone);
		}
		
		newuser.setStatus("02");
		newuser.setDelflag("02");
		SysUserInfo userInfo = this.userService.findUserInfoByUserId(userId);
		if(userInfo == null){
			userInfo = new SysUserInfo();
			SysUser user = new SysUser();
			user.setId(userId);
			userInfo.setSysUser(user);
		}
		if(infoAddress!=null&&!"".equals(infoAddress)&&!"undefined".equals(infoAddress)){
			userInfo.setAddress(infoAddress);
		}
		
		if(degree!=null&&!"".equals(degree)&&!"undefined".equals(degree)){
			userInfo.setDegree(degree);
		} 
		if(major!=null&&!"".equals(major)&&!"undefined".equals(major)){
			userInfo.setTitle(major);
		} else {
			major=userInfo.getTitle();
		}
		if(organization!=null&&!"".equals(organization)&&!"undefined".equals(organization)){
			userInfo.setOrganization(organization);
		}
		userInfo.setCreatetime(df.format(new Date()));
		userInfo.setLastedittime(df.format(new Date()));
		BASE64Decoder decoder = new BASE64Decoder(); 
		try {
			if(image!=null&&!"".equals(image)&&!"undefined".equals(image)){
				byte[] b = decoder.decodeBuffer(image.substring(image.indexOf("base64")+7));  
	            InputStream in = new ByteArrayInputStream(b);
	            String photo = UploadFile.upFtpPhoto(in,imageName);
	            newuser.setUserphoto(photo);
			}
			this.userService.updateUser(newuser);
			this.userService.saveUserInfo(userInfo);
			if(newuser.getSex()!=null && !"".equals(newuser.getSex()) && newuser.getUserAddress()!=null && !"".equals(newuser.getUserAddress())
			  && newuser.getArea()!=null && !"".equals(newuser.getArea()) &&  newuser.getEmail()!=null && !"".equals(newuser.getEmail()) && newuser.getPhone()!=null && !"".equals(newuser.getPhone())
			  && userInfo.getOrganization()!=null && !"".equals(userInfo.getOrganization()) && userInfo.getAddress()!=null && !"".equals(userInfo.getAddress()) && userInfo.getDegree()!=null && !"".equals(userInfo.getDegree())
			  && major!=null && !"".equals(major) && newuser.getUserphoto() !=null && !"".equals(newuser.getUserphoto())){
				IntContrast intContrast=intContrast=userService.findByCodeNo("gc003");
				if(intContrast!=null &&!"".equals(intContrast)){
					IntIntegral  intIntegral=userService.findIntIntegal(newuser.getId(), intContrast.getId());
					if(intIntegral==null || "".equals(intIntegral)){
						this.userService.doRegister1(newuser,ClientIpUtil.getIpAddress(request),equipment);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> map = this.userService.findPersonalDataMap(userId,resultMap);
		map.put("result", "1");
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//获取签到数据
	@RequestMapping("/user/toSign.do")
	public ModelAndView toSign(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		String userId= (String) request.getAttribute("userId");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = this.userService.findSign(userId,df1.format(new Date()),df2.format(new Date()),resultMap);
		String result="0";
		if(map!=null && map.size()>0){
			result="1";
		}
		resultMap.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	} 
	
	//签到
	@RequestMapping("/user/doSign.do")
	public ModelAndView doSign(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = this.getResultMap(request, response);
		//获取设备类型
		String equipment= (String) request.getAttribute("equipment");
		String userId= (String) request.getAttribute("userId");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		//查看当月是否有签到
		String ip = ClientIpUtil.getIpAddress(request);
		Map<String, Object> map = this.userService.doSign(userId,df1.format(new Date()),df2.format(new Date()),ip,resultMap,equipment);
		String result="0";
		if(map!=null && map.size()>0){
			result="1";
		}
		resultMap.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
}
