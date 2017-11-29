package cn.gc80.base.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


public class Constants {
	public final static String FILE_SEPARATOR = "/";
	public final static String CONDITIONSPLIT = "\\";
	
	//项目地址
	public final static String PROJECT_PATH=ConfigUtil.getConfig("project");
	
	//ftp
	public final static String FTP_IP=ConfigUtil.getConfig("ftp_ip");
	public final static String FTP_USERNAME=ConfigUtil.getConfig("ftp_username");
	public final static String FTP_PASSWORD=ConfigUtil.getConfig("ftp_password");
	public final static String FTP_PORT=ConfigUtil.getConfig("ftp_port");
	public final static String IMG_SERVICE=ConfigUtil.getConfig("imgServer");
	
	//用户上传头像的路径
	public static final String USERPHOTO_SERVERPATH = "userphoto/";
	
	public static String getSerialnumber(String type){
		Date date = new Date();
		Long parseDate = date.getTime();
		String serialnumber = type + "-" + parseDate.toString();
		return serialnumber;
	}
	
	public static String getDate(){
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String d = s.format(date);
		return d;
	}
	
}
