package cn.gc80.web.cert.contorller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import cn.gc80.base.BaseController;
import cn.gc80.base.util.MemCached;
import cn.gc80.datamodel.sysbase.SysCertProvice;
import cn.gc80.datamodel.sysbase.SysProvince;
import cn.gc80.datamodel.sysbase.SysUser;
import cn.gc80.datamodel.training.TrainCert;
import cn.gc80.datamodel.training.TrainCredit;
import cn.gc80.web.cert.service.CertService;
import cn.gc80.web.user.service.UserService;

@Controller
public class CertContorller extends BaseController {
	@Resource
	private CertService certService;
	
	@Resource
	public UserService userService;
	
	//证书查询
	@RequestMapping("/web/cert/doCertVerify.html")
	public ModelAndView doCertVerify(HttpServletRequest request,HttpServletResponse response,String searchType,String realname,String cardno,String certNo,String valideCode,String uuid) {
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
			//验证码正确
			//清空验证码
			MemCached.getInstance().delete(uuid);
			if(searchType!=null&&"01".equals(searchType)){
				//身份信息查询
				List<TrainCert> trainCertList=this.certService.findCertList(realname, cardno);
				if(trainCertList!=null&&trainCertList.size()>0){
					//有证书信息
					result="1";
					map.put("trainCertList", trainCertList);
				}else{
					//无证书信息
					result="3";
				}
			}else if(searchType!=null&&"02".equals(searchType)){
				//证书编号查询
				TrainCert trainCert=this.certService.findCert(certNo);
				if(trainCert!=null){
					//有证书信息
					result="1";
					map.put("trainCert", trainCert);
				}else{
					//无证书信息
					result="3";
				}
			}
		}
		map.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//证书详情
	@RequestMapping("/web/cert/findCertById.html")
	public ModelAndView findCertById(HttpServletRequest request,HttpServletResponse response,String id) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		TrainCert cert=this.certService.getCertById(id);
		if(cert==null){
			map.put("result", 0);
		}else{
			map.put("result", 1);
			map.put("cert", cert);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//查看证书
	@RequestMapping("/web/cert/findCert.html")
	public ModelAndView findCert(HttpServletRequest request,HttpServletResponse response,String id) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = this.certService.findCertMap(id);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//学时查询
	@RequestMapping("/web/cert/doCreditVerify.html")
	public ModelAndView doCreditVerify(HttpServletRequest request,HttpServletResponse response,String realname,String cardno,String valideCode,String uuid) {
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
			//验证码正确
			//清空验证码
			MemCached.getInstance().delete(uuid);
			//身份信息查询
			List<Map<String,Object>> list=this.certService.findCredit(realname, cardno);
			if(list!=null&&list.size()>0){
				//有学时信息
				result="1";
				map.put("list", list);
			}else{
				//无学时信息
				result="3";
			}
		}
		map.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//学时详情
	@RequestMapping("/web/cert/findCreditById.html")
	public ModelAndView findCreditById(HttpServletRequest request,HttpServletResponse response,String id) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String,Object> credit=this.certService.findCreditById(id);
		if(credit==null){
			map.put("result", 0);
		}else{
			map.put("result", 1);
			map.put("credit", credit);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//学时证明
	@RequestMapping("/web/cert/findCredit.html")
	public ModelAndView findCredit(HttpServletRequest request,HttpServletResponse response,String userId,String classId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = this.certService.findCreditMap(userId,classId);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//证书查询
	@RequestMapping("/web/cert/doMyCertVerify.do")
	public ModelAndView doMyCertVerify(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = this.getResultMap(request, response);
		String userId= (String) request.getAttribute("userId");
		SysUser user = this.userService.findUserByUserId(userId);
		SysCertProvice cp = this.certService.findCertProvince(userId);
		if(cp==null || !cp.getCertNo().equals("01")){
			map.put("flag", "01");
		}
		String result="0";
		//身份信息查询
		List<TrainCert> trainCertList=this.certService.findCertList(user.getCardno());
		if(trainCertList!=null&&trainCertList.size()>0){
			//有证书信息
			result="1";
			map.put("trainCertList", trainCertList);
		}else{
			//无证书信息
			result="3";
		}
		map.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//证书详情
	@RequestMapping("/web/cert/findCertDetailById.html")
	public ModelAndView findCertDetailById(HttpServletRequest request,HttpServletResponse response,String id) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		TrainCert cert=this.certService.getCertDetailById(id);
		if(cert==null){
			map.put("result", 0);
		}else{
			if (cert.geteOrderExpress() != null
					&& !"".equals(cert.geteOrderExpress())) {
				String area = cert.geteOrderExpress().getAddressArea();
				if(null!=area){
					String Cityname = "";
					// 区域
					if (area.length() > 4) {
						SysProvince sy = certService.findSysProvinceCodeid(area
								.substring(0, 4));
						Cityname = sy.getCityname();
						SysProvince sy1 = certService.findSysProvinceCodeid(area);
						Cityname += sy1.getCityname();
						map.put("addressArea", Cityname);
					}
				}
			}
			map.put("result", 1);
			map.put("cert", cert);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
}
