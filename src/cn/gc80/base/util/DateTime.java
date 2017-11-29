package cn.gc80.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class DateTime {

	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat YYYYMMDDFORMAT = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat YYYYMMDDHHmmFORMAT = new SimpleDateFormat("yyyyMMddHHmm");
	public static SimpleDateFormat YYYYMMDDHHmmssFORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	//获取当前是第几周
	public static int getCurrentWeek() {
		int week = 0;
		try{
			Date dat = new java.util.Date();
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(dat);          
            week =cal.get(java.util.Calendar.DAY_OF_WEEK)-1; 
		}catch(Exception e){
			e.printStackTrace();
		}
		return week;
	}
	
	//获取当前天数
	public static String getCurrentDay(){
		int day = 0;
		try{
			Date dat = new java.util.Date();
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(dat);          
            day =cal.get(java.util.Calendar.DATE); 
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return String.valueOf(day);
	}
	
	//获取当前时间形式是yyyy-MM-dd
	public static String getCurrentDate(){
		String time = "";
		try{
			time = FORMAT.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return time;
	}
	
	//获取当前时间形式是yyyyMMdd
	public static String getDate(){
		String time = "";
		try{
			time = YYYYMMDDFORMAT.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return time;
	}
	
	//将时间转换成字符串
	public static String getStringDate(Date date){
		String time = "";
		try{
			time = DATE_FORMAT.format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return time;
	}
	
	//将时间转换成字符串
	public static String getStringDate2(Date date){
		String time = "";
		try{
			time = FORMAT.format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return time;
	}
	
	//获取当前时间形式是yyyyMMddHHmmss
	public static String getDateTime(){
		String time = "";
		try{
			time = YYYYMMDDHHmmssFORMAT.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return time;
	}
	
	//获取当前时间形式是yyyy-MM-dd hh:mm:ss
	public static String getCurrentDateTime() {
		String d = "";
		try{
			d = DATE_FORMAT.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return d;
	}
	 
	/*获得系统当前时间，格式"hhmmss"  */
	public static String getCurrentStandardTime2() {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("HHmmss").format(c.getTime());
	}
	
	public static String getTime(long timeStamp,String pattern) throws ParseException {
		
		return new SimpleDateFormat(pattern).format(new Date(timeStamp));
	}
	
    private static DateFormat createDateFormat(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(true);
		return sdf;
	}

    //根据提供时间格式将字符串转换成时间格式
    public static Date str2Date(String str, String pattern) {
		DateFormat df = createDateFormat(pattern);
		try {
			Date newdate = df.parse(str);
			return newdate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
   
    //获取两个时间差
	public static long getDateSubtract(Date dt1,Date dt2){  
		  long   seconds   =   (dt1.getTime()-   dt2.getTime())/1000;   
		  if(seconds<0)seconds=seconds*-1;
		  return seconds; 
	}
	
	//获取当前时间
	public static Date getDateTimeDate() {
		Date now = new Date();
		return now;
	}

	public static String getDateTimeStr() {
		Calendar calendar = Calendar.getInstance();
		return DATE_FORMAT.format(calendar.getTime());
	}
	
	public static String formatDateTime(Date date){
		String result = "";
		if(date!=null){
			result = DATE_FORMAT.format(date);
		}
		return result;
	}
	 
	public static String getLastOneDay() { 		 
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY) - 24);
		return DATE_FORMAT.format(calendar.getTime());
	}

	public static String getPrevHalfHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 30);
		return DATE_FORMAT.format(calendar.getTime());
	}
	
	public static String getPrev20Minute() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 20);
		return DATE_FORMAT.format(calendar.getTime());
	}
	 
    public static Calendar getDiscrepantWorkCalendar(Calendar primaryCalendar, int day) {
        Calendar calendar = (Calendar) primaryCalendar.clone();
        int gene = 1;
        if (day < 0) {
            gene = -1;
        }
        int absDay = day;
        if (absDay < 0) {
            absDay = -absDay;
        }

        int dayCount = 0;
        long milliSecond = primaryCalendar.getTime().getTime();
        while (dayCount < absDay) {
            milliSecond += 86400000 * gene;
            calendar.setTimeInMillis(milliSecond);
            int weekDate = calendar.get(Calendar.DAY_OF_WEEK);
            if ((weekDate != Calendar.SUNDAY) && weekDate != Calendar.SATURDAY) {
                dayCount++;
            }
        }
        return calendar;
    }
 
    //获取两个时间相差多少天
    public static int getMarginDay(Date beginDate, Date endDate) {
        int dayCount = 0;
        long beginMilliSecond = beginDate.getTime();
        long endMilliSecond = endDate.getTime();
        long marginMilliSecond = endMilliSecond - beginMilliSecond;
        long oneDayMilliSecond = 24 * 60 * 60 * 1000;
        long dayCount_tmp = marginMilliSecond / oneDayMilliSecond;
        dayCount = Integer.parseInt(dayCount_tmp + "");
        return dayCount;
    }

    public static java.sql.Date getSqlDate(Date beginDate, long marginMilliSecond) {
        long endMilliSecond = beginDate.getTime() + marginMilliSecond;
        return new java.sql.Date(endMilliSecond);
    }
 
    public static int getDatemark(Date date,int flag) {
        int result=0;
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        result=calendar.get(flag);
        if (flag==Calendar.MONTH) {
            result++;
        }
        return result;
    }
    
    
    public static String formartMs(long ms){
    	int ss = 1000;
    	   int mi = ss * 60;
    	   int hh = mi * 60;
    	   int dd = hh * 24; 
    	   long day = ms / dd;
    	   long hour = (ms - day * dd) / hh;
    	   long minute = (ms - day * dd - hour * hh) / mi;
    	   long second = (ms - day * dd - hour * hh - minute * mi) / ss;
    	   long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss; 
    	   String strDay = day < 10 ? "0" + day : "" + day;
    	   String strHour = hour < 10 ? "0" + hour : "" + hour;
    	   String strMinute = minute < 10 ? "0" + minute : "" + minute;
    	   String strSecond = second < 10 ? "0" + second : "" + second;
    	   String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
    	   strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
    	   return strDay + " " + strHour + ":" + strMinute + ":" + strSecond + " " + strMilliSecond; 
    }
    
  //根据秒数获取时间
    public static String formartS4CN(long ms){
    	ms = ms*1000;
    	String result = formartMs(ms);
    	result = result.replaceFirst(" ", "天");
    	result = result.replaceFirst(":", "小时");
    	result = result.replaceFirst(":", "分");
    	result = result.replaceFirst(" ", "秒");
    	result = StringUtils.substringBeforeLast(result, "秒");
    	return result + "秒";
    }
    
    @SuppressWarnings("unused")
    public static String plusMonthsDate(int months,Date baseDate) {
		
    	Calendar c = Calendar.getInstance();
    	c.setTime(baseDate);
    	long baseMills = c.getTimeInMillis();
    	int month = c.get(Calendar.MONTH);
    	int newMonth = month + months;
    	c.set(Calendar.MONTH, newMonth);
    	Date newDate =  c.getTime();
    	String formateDateStr = formatDateTime(newDate);
    	return formateDateStr;
	}
    
	@SuppressWarnings("unused")
    public static String plusDaysDate(int months,Date baseDate){
    	Calendar c = Calendar.getInstance();
    	c.setTime(baseDate);
		long baseMills = c.getTimeInMillis();
    	int month = c.get(Calendar.DATE);	
    	int newMonth = month + months;
    	c.set(Calendar.DATE, newMonth);
    	Date newDate =  c.getTime();
    	String formateDateStr =new SimpleDateFormat("yyyy-MM-dd").format(newDate);
    	return formateDateStr;
    }
    
    public static long getTimeMillons(String dateTime){
    	long millons = 0;
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			Date date = sd.parse(dateTime);
			millons = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return millons;
    }
    
    public static String getMillonsStr(long millons){
    	Date date = new Date(millons);
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String result = sd.format(date);
    	return result;
    }
    
    public static String getTimeDetail(long loginTimeLong){
		long days = loginTimeLong / 86400000L; 
		loginTimeLong %= 86400000L;
		long hours = loginTimeLong / 3600000L;
		loginTimeLong %= 3600000L;
		long minutes = loginTimeLong / 60000L;
		loginTimeLong %= 60000L;
		long seconds = loginTimeLong / 1000L;
		StringBuilder sb = new StringBuilder();

		boolean flg = false;
		if(0 != days){
			sb.append(days + "天");
			flg = true;
		}
		if(0 != hours || flg){
			sb.append(hours + "时");
			flg = true;
		}
		if(0 != minutes || flg){
			sb.append(minutes + "分");
			flg = true;
		}
		sb.append(seconds + "秒");
		return sb.toString();
	}
    
    /**
     * 查询距离今天第七天前的日期
     * 获得 指定日期提前几天的日期对象
     * @param dateString 日期对象 ，格式如 1900-1-31
     * @param lazyDays 倒推的天数
     * @return 指定日期倒推指定天数后的日期对象
     * @throws ParseException 
     * by xsg
     */
    public static String getBeforeDate() throws ParseException{
     
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      Calendar cal = Calendar.getInstance();
      cal.setTime(new Date());
     
      int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
      cal.set(Calendar.DAY_OF_YEAR , inputDayOfYear-7 );
     
      return df.format(cal.getTime()) ;
    }
    
    //昨天
    public static String getYesterDay() throws ParseException{
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
       
        int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR , inputDayOfYear-1);
       
        return df.format(cal.getTime()) ;
      }
    
    //最近一个月
    public static String getBeforeMonth() throws ParseException{
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
       
        int inputDayOfYear = cal.get(Calendar.MONTH);
        cal.set(Calendar.MONTH , inputDayOfYear-1);
        return df.format(cal.getTime()) ;
   }
    
    //最近三个月
    public static String getBeforeThreeMonth() throws ParseException{
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
       
        int inputDayOfYear = cal.get(Calendar.MONTH);
        cal.set(Calendar.MONTH , inputDayOfYear-3);
        return df.format(cal.getTime()) ;
   }
    
   //获取当前这周的开始时间
   public static Date getCurWeekStrarTime(){
	   Calendar currentDate = new GregorianCalendar();   
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
		currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		currentDate.set(Calendar.MINUTE, 0);  
		currentDate.set(Calendar.SECOND, 0);  
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
		Date date=(Date)currentDate.getTime().clone();
		return date;
   }
   
   //获取当前这周的结束时间
   public static Date getCurWeekEndTime(){
		Calendar currentDate = new GregorianCalendar();   
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
		currentDate.set(Calendar.HOUR_OF_DAY, 23);  
		currentDate.set(Calendar.MINUTE, 59);  
		currentDate.set(Calendar.SECOND, 59);  
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); 
		Date date=(Date)currentDate.getTime().clone();
		return date;
   }
    
}
