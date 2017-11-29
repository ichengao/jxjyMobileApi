package cn.gc80.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;

public class UpFile {
	
	/**
    * @param args
    */
	public static void main(String[] args) throws Exception{
		int port= Integer.parseInt(Constants.FTP_PORT);
		UpFile.upMyfile(Constants.FTP_IP, Constants.FTP_USERNAME, Constants.FTP_PASSWORD, port ,"/userphoto/",null,"");
		//myUpFile.upFileByConfigXml();
	 
	 }
	
	
	public static int upMyfile(String ip,String userName,String pwd, int port,String upDir,InputStream inputStream,String localFileName){
	    FTPClient ftpClient = new FTPClient();
	    try {
		     ftpClient.connect(ip, port);
		     if(!ftpClient.login(userName, pwd)){
		    	 System.out.println("用户名或密码错误！");
		    	 return 0;
		     }
		     // 将工作目录切换到指定目录
		     if(!ftpClient.changeWorkingDirectory(upDir)){
		    	 if(upDir.contains("/")){   
		             //创建服务器远程目录结构，创建失败直接返回
		              CreateDirecroty(upDir, ftpClient);
		         }   
		     }
		     //ftpClient.changeWorkingDirectory("/home/tssa");
		  //无此句，传输非txt文件会变大
		     ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		     // 指定要上传的文件目录,通过传入的localFileDir参数值获得
		     // 上传本地指定目录下的文件
		     ftpClient.enterLocalPassiveMode();
		     if(!ftpClient.storeFile(localFileName, inputStream)){
		    	 System.out.println("指定文件上传失败！此目录下该用户无权上传文件！");
		    	 return 0;
		     }
		     // 上传成功后退出系统
		     ftpClient.logout();
		     ftpClient.disconnect();
		     return 1;
	    } catch (SocketException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		     return 0;
	    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		     return 0;
	    }
	}
	
	public static void CreateDirecroty(String remote,FTPClient ftpClient) throws IOException{   
	    String directory = remote.substring(0,remote.lastIndexOf("/")+1);   
	    if(!directory.equalsIgnoreCase("/")&&!ftpClient.changeWorkingDirectory(new String(directory.getBytes("GBK"),"iso-8859-1"))){   
	        //如果远程目录不存在，则递归创建远程服务器目录   
	        int start=0;   
	        int end = 0;   
	        if(directory.startsWith("/")){   
	            start = 1;   
	        }else{   
	            start = 0;   
	        }   
	        end = directory.indexOf("/",start);   
	        while(true){   
	            String subDirectory = new String(remote.substring(start,end).getBytes("GBK"),"iso-8859-1");   
	            boolean b= ftpClient.changeWorkingDirectory(subDirectory);
	            if(!b){   
	                if(ftpClient.makeDirectory(subDirectory)){   
	                    ftpClient.changeWorkingDirectory(subDirectory);   
	                }else {   
	                    System.out.println("创建目录失败");   
	                    return  ;   
	                }   
	            }   
	            start = end + 1;   
	            end = directory.indexOf("/",start);   
	            //检查所有目录是否创建完毕   
	            if(end <= start){   
	                break;   
	            }   
	        }   
	    }   
	}   

}