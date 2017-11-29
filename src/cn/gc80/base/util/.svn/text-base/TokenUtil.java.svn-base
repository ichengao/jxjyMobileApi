package cn.gc80.base.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import cn.gc80.jwt.Jwt;

public class TokenUtil {
	//获取
	public static String getToken(String uid){
		Map<String , Object> payload=new HashMap<String, Object>();
		Date date=new Date();
		//用户ID
		payload.put("uid", uid);
		//生成时间
		payload.put("iat", date.getTime());
		//过期时间2小时
		payload.put("ext",date.getTime()+2000*60*60);
		String token=Jwt.createToken(payload);
		//保存token到memcached
		MemCached.getInstance().addOrReplace("m-"+uid,token);
		return token;
	}
	
	public static String getMUid(String uid){
		return (String) MemCached.getInstance().get("m-"+uid);
	}
	
	public static boolean deleteMUid(String uid){
		return MemCached.getInstance().delete("m-"+uid);
	}
	
}
