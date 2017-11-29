package cn.gc80.web.tclass.contorller;

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
import cn.gc80.base.page.PageHolder;
import cn.gc80.datamodel.res.ResChapter;
import cn.gc80.datamodel.res.ResCouCw;
import cn.gc80.datamodel.res.ResCourse;
import cn.gc80.datamodel.sysbase.SysCode;
import cn.gc80.datamodel.training.TrainClass;
import cn.gc80.datamodel.training.TrainClassCourse;
import cn.gc80.web.tclass.service.ClassService;

@Controller
public class ClassContorller extends BaseController {
	@Resource
	private ClassService classService;
	
	//获取分类信息
	@RequestMapping("/class/toCode.html")
	public ModelAndView toCode(HttpServletRequest request,HttpServletResponse response,String parent) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		String result="0";
		List<SysCode> list=this.classService.findCodeByParent(parent);
		if(list!=null&&list.size()>0){
			result="1";
			map.put("list", list);
		}
		map.put("result", result);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//班级选课中心
	@RequestMapping("/class/toClassCenter.html")
	public ModelAndView toClassCenter(HttpServletRequest request,HttpServletResponse response,String area,String codeNo,String className,String pages,String pageSize) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = new HashMap<String, Object>();
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
		ph=this.classService.findClass(area,className,codeNo,ph);
		Map<String, Object> map = this.findPageInfo(ph.getDataList(),"list",resultMap,ph);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//班级详情
	@RequestMapping("/class/toClassDetail.html")
	public ModelAndView toClassDetail(HttpServletRequest request,HttpServletResponse response,String classNo) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = this.classService.findClassDetailMap(classNo);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//确定报名
	@RequestMapping("/class/toEnterClass.do")
	public ModelAndView toEnterClass(HttpServletRequest request,HttpServletResponse response,String classId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		TrainClass tClass=this.classService.getTrainClassById(classId);
		map.put("tClass", tClass);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//选择学习课程
	@RequestMapping("/class/toSelectLeaningCourse.do")
	public ModelAndView toSelectLeaningCourse(HttpServletRequest request,HttpServletResponse response,String classId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String userId= (String) request.getAttribute("userId");
		List<TrainClassCourse> classCourseList=this.classService.findClassCourseByClassId(userId,classId);
		map.put("classId", classId);
		map.put("classCourseList", classCourseList);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//课程详情页面
	@RequestMapping("/class/toCourseDetail.html")
	public ModelAndView toCourseDetail(HttpServletRequest request,HttpServletResponse response,String pages,String pageSize) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> resultMap = getResultMap(request, response);
		//将过滤器中存入的payload数据取出来
		String classNo = request.getParameter("classNo");
		String courseNo = request.getParameter("courseNo");
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
		Map<String, Object> map = this.classService.toCourseDetail(classNo,courseNo,resultMap,ph);
		map = this.findPageInfo((List)map.get("couCwList"),"couCwList",resultMap,(PageHolder)map.get("ph"));
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
