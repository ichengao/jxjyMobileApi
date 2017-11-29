package cn.gc80.base.util;

import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
/* 
 * 利用HttpClient进行post请求的工具类 
 */  
public class HttpClientUtil {  
    public String doPost(String url,Map<String,String> map,String charset){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);  
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }  
    
    public String doGet(String url){
    	String result= null;
    	try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(url);//这里发送get请求
            // 获取当前客户端对象
            HttpClient httpClient = new SSLClient(); 
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
            } 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	return result;
    }
    //测试
    public static void main(String[] args) {
     
        /*HttpClientUtil httpClientUtil = new HttpClientUtil();
        String url = "http://175.102.15.195:8081/bcp/zbadmin/index/addCust";  
        Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("realname","张先生");  
        createMap.put("city","100100");  
        long phone=13888888888l;
        phone = (phone - 1998)*5+2017;
        createMap.put("phone",String.valueOf(phone));  
        createMap.put("address","无");  
        createMap.put("channel","1");  
        String httpOrgCreateTestRtn = httpClientUtil.doPost(url,createMap,"utf-8");  
        System.out.println("result:"+httpOrgCreateTestRtn); */
        
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=123.125.71.38";  
        String httpOrgCreateTestRtn = httpClientUtil.doGet(url);
        System.out.println("result:"+httpOrgCreateTestRtn); 
        JSONObject jsonObject=JSONObject.fromObject(httpOrgCreateTestRtn);
        System.out.println(jsonObject.get("country"));
        System.out.println(jsonObject.get("province"));
        System.out.println(jsonObject.get("city"));
	}
    
    
    
}  
