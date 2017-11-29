package cn.gc80.web.info.contorller;

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
import cn.gc80.datamodel.info.AdPicture;
import cn.gc80.web.info.service.InfoService;

@Controller
public class InfoContorller extends BaseController {
	@Resource
	private InfoService infoService;
	
	//信息列表
	@RequestMapping("/info/toInfoMessage.html")
	public ModelAndView toInfoMessage(HttpServletRequest request,HttpServletResponse response,String codeNo,String area,String pages,String pageSize) {
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
		ph=this.infoService.findMessageBySome(codeNo, area, ph);
		Map<String, Object> map = this.findPageInfo(ph.getDataList(),"list",resultMap,ph);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//信息详情
	@RequestMapping("/info/toInfoMessageDetail.html")
	public ModelAndView toInfoMessageDetail(HttpServletRequest request,HttpServletResponse response,String name,String codeNo) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = this.infoService.findInfoMessageDetailMap(name,codeNo);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//图片
	@RequestMapping("/info/tofindPicture.html")
	public ModelAndView tofindPicture(HttpServletRequest request,HttpServletResponse response,int num) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		List<AdPicture> list=this.infoService.findPicture(num);
		if(list!=null&&list.size()>0){
			map.put("result", "1");
			map.put("list", list);
		}else{
			map.put("result", "0");
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	//图片详情
	@RequestMapping("/info/tofindPictureDetail.html")
	public ModelAndView tofindPictureDetail(HttpServletRequest request,HttpServletResponse response,String picId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = this.infoService.findPicMessageMap(picId);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
}
