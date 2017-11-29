package cn.gc80.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;


public class UploadFile {
	public static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	//本地上传
	public static String upLoadImg(MultipartFile imgFile,String DiscPath,String DBPath){
		
		String filepath = DiscPath;
		String fileName=imgFile.getOriginalFilename();       //文件全名带后缀
		String date = df.format(new Date());
		filepath = filepath+Constants.FILE_SEPARATOR+date;
		String newFileName = fileName.substring(0,fileName.lastIndexOf(".")).toLowerCase();	//文件名
		Date d = new Date();
		Long parseDate = d.getTime();
		newFileName = "image-" + parseDate.toString();
		String newExtName = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
		fileName = newFileName+newExtName;
		filepath = filepath+Constants.FILE_SEPARATOR+fileName;
		File savePath = new File(filepath);
        if (!savePath.exists()) {                             // 文件夹
            savePath.mkdirs();
        }
        try {
			imgFile.transferTo(savePath);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("imgFile:"+imgFile);
        System.out.println("DBPath:"+DBPath);
        System.out.println("filepath:"+filepath);
        System.out.println("db:"+DBPath+date+"/"+fileName);
        return DBPath+date+"/"+fileName;
	}
	
	//ftp上传                                                                                                             "userphoto/"http\://service.ahsjxjy.com\:8888/imgserver/
	public static String upFtpFile(MultipartFile mFile,String basePath,String dbService) throws Exception{
		String filePath=dbService;
		String fileName=mFile.getOriginalFilename();       //文件全名带后缀
		Date d = new Date();
		Long parseDate = d.getTime();
		String newFileName =  parseDate.toString(); //新文件名
		String extName = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
		filePath = filePath+basePath+newFileName+extName;
		UpFile.upMyfile(Constants.FTP_IP, Constants.FTP_USERNAME, Constants.FTP_PASSWORD, Integer.parseInt(Constants.FTP_PORT) ,Constants.FILE_SEPARATOR+Constants.USERPHOTO_SERVERPATH,mFile.getInputStream(),newFileName+extName);
		return filePath;
		
	}

	//ftp上传头像
	public static String upFtpPhoto(InputStream in,String imageName) {
		String filePath= Constants.IMG_SERVICE;
		Date d = new Date();
		Long parseDate = d.getTime();
		String newFileName =  parseDate.toString(); //新文件名
		String extName = imageName.substring(imageName.lastIndexOf("."), imageName.length());//文件后缀
		filePath = filePath+"userphoto/"+newFileName+extName;
		UpFile.upMyfile(Constants.FTP_IP, Constants.FTP_USERNAME, Constants.FTP_PASSWORD, Integer.parseInt(Constants.FTP_PORT) ,Constants.FILE_SEPARATOR+Constants.USERPHOTO_SERVERPATH,in,newFileName+extName);
		return filePath;
	}
}
