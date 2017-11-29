package cn.gc80.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.gc80.base.page.PageHolder;
import cn.gc80.base.page.Pageination;

public abstract class BaseController{
	
	public Map<String, Object> findPageInfo(List<?> list,String listName,Map<String, Object> map,PageHolder ph){
		Pageination pageination=new Pageination();
		pageination.setPages(ph.getPageNumber());
		pageination.setPagesize(ph.getPageSize());
		map.put(listName, list);
		if(list!=null&&!list.isEmpty()){
			pageination.setNum(new Long(ph.getRecordNum()).intValue());//总条数
			pageination.setPagenum(new Long(ph.getRecordNum()).intValue(), ph.getPageSize());//总页数
			map.put("next", pageination.getNext());
			map.put("pre", pageination.getPre());
			map.put("pagenum", pageination.getPagenum());//总页数
			map.put("num", pageination.getNum());//总条数
			map.put("page", pageination.getPages());//当前页
		}
		else{
			map.put("next", 0);
			map.put("pre", 0);
			map.put("pagenum", 0);//总页数
			map.put("num", 0);//总条数
			map.put("page", 0);//当前页
		}
		map.put("pageSize", ph.getPageSize());
		return map;
	}
	
	public Map<String,Object> getResultMap(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map=(Map<String, Object>) request.getAttribute("resultMap");
		if(map==null){
			map = new HashMap<String, Object>();
			request.setAttribute("resultMap", map);
		}
		return map;
	}
    
}
