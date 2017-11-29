package cn.gc80.base.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.minidev.json.JSONObject;
import cn.gc80.base.util.ConfigUtil;
import cn.gc80.base.util.TokenUtil;
import cn.gc80.jwt.Jwt;
import cn.gc80.jwt.TokenState;

public class MyFilter  implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;  
		HttpServletResponse res = (HttpServletResponse) servletResponse;  
		
		// 设置返回值
		Map<String, Object> map = new HashMap<String, Object>();
		req.setAttribute("resultMap", map);
		
		// 设置跨域
		String allowOrigin=ConfigUtil.getConfig("allow_origin");
		res.setHeader("Access-Control-Allow-Origin",allowOrigin);
//		res.setHeader("Access-Control-Allow-Origin","*");
		res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		res.setHeader("Access-Control-Allow-Headers", "Authorization, Accept, Origin, X-Requested-With, Content-Type, Last-Modified, equipment,uuid,isWx");
		res.addHeader("Access-Control-Max-Age", "1800");//30 min
		
		//获取设备类型
		String equipment=req.getHeader("equipment");
		req.setAttribute("equipment", equipment);
		
        String url = req.getRequestURI();
        if(url!=""&&url.contains(".do")){
        	 //接口不校验token，直接放行
	         if(url.indexOf("/doLogin.do") > -1 ) {
	        	 filterChain.doFilter(servletRequest, servletResponse);
	             return;
	         }else{
	        	//其他API接口一律校验token
	     		//从请求头中获取token
	     		String token=req.getHeader("authorization");
	     		Map<String, Object> resultMap=Jwt.validToken(token);
	     		TokenState state=TokenState.getTokenState((String)resultMap.get("state"));
	     		switch (state) {
	     		case VALID:
	     			//是否需要更新token
	     			//取出payload中数据,放入到request作用域中
					Map<String, String> data=(Map<String, String>) resultMap.get("data");
	     			req.setAttribute("userId", data.get("uid"));
	     			//放行
	     			filterChain.doFilter(servletRequest, servletResponse);
	     			break;
	     		case EXPIRED:
	     		case INVALID:
	     			//token过期或者无效，则输出错误信息返回给ajax
	     			JSONObject outputMSg=new JSONObject();
	     			outputMSg.put("result", "-1");
	     			output(outputMSg.toJSONString(), res);
	     			break;
	     		case REFURBISH:
	     			//取出payload中数据,放入到request作用域中
					Map<String, String> data1=(Map<String, String>) resultMap.get("data");
	     			req.setAttribute("userId", data1.get("uid"));
	     			//更新token
	    			map.put("token", TokenUtil.getToken(data1.get("uid")));
	     			req.setAttribute("resultMap", map);
	     			//放行
	     			filterChain.doFilter(servletRequest, servletResponse);
	     			break;
	     		}
	         }
        }else{
        	filterChain.doFilter(servletRequest, servletResponse); 	
        }
        return ;
	}
	
	public void output(String jsonStr,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		out.flush();
		out.close();
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
