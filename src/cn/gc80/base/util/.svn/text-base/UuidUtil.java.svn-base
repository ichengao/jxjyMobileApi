package cn.gc80.base.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@Controller
public class UuidUtil {
	
	public static String getUuid(){  
        UUID uuid = UUID.randomUUID();  
        String uuidStr =  uuid.toString();  
        uuidStr = uuidStr.replace("-", "");  
        return uuidStr;  
    }  
	
    //生成uuid
	@RequestMapping("/getUuid.html")
	public ModelAndView getUuid(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", UuidUtil.getUuid().toUpperCase());
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}
	
    public static void main(String[] args) {  
        System.out.println(UuidUtil.getUuid());   
    }  
	
}
