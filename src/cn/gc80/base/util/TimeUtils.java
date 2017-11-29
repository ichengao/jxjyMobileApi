package cn.gc80.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	/**
	 * 得到当前标准格式的时间,标准格式如下:"2005-03-23 13:34:56".
	 * 
	 * @return 以字符串表示的标准格式的时间,如:"2005-03-23 13:34:56".
	 */
	public static String getCurrentStandardTime() {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
	}

	public static String getSpecifiedStandardDate1(Date date) {
		// Date date = new Date(second * 1000);
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	public static String getSpecifiedStandardDate2(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public static String getSpecifiedStandardTime2(Date date) {
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}

	/*获得系统当前时间，格式"yyyyMMddhhmmss"  */
	public static String getCurrentStandardTime1() {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("yyyyMMddhhmmss").format(c.getTime());
	}
	
	/*获得系统当前时间，格式"hhmmss"  */
	public static String getCurrentStandardTime2() {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("HHmmss").format(c.getTime());
	}
	
	/*获得系统当前时间，格式"yyyy-MM-dd"  */
	public static String CurrentStandardDate1() {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	

	
	
	/*获得系统当前时间，格式"yyyyMMdd"  */
	public static String getCurrentStandardDate2() {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("yyyyMMdd").format(c.getTime());
	}
	

	/*获得系统当前时间，格式"HH:mm:ss"  */	
	public static String CurrentStandardTime2(Date date) {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("HH:mm:ss").format(c.getTime());
	}
	

	/*获得指定日期（字符串）+指定天数，格式"yyyy-MM-dd"  */
	public static String getDateByAddDays(String date, int number)  {
		
		Calendar c = Calendar.getInstance();
		  
         
		Date d;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			c.setTime(d);
			c.add(Calendar.DAY_OF_YEAR, +number);
			return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		
	}
	
	//获得当前月份1日字符串
	public static String getFirstDayOfCurrentMonth() {
		Calendar now = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		String yf="";
		if(month<9){//拼月份长度2位
			yf="0"+String.valueOf(month);
		}else{
			yf=String.valueOf(month);
		}
		// System.out.println(String.valueOf(month));
		String tmp = year + "-" + yf + "-01";
		// System.out.println(tmp);
		
		return tmp;

	}
	
	public static int getNewYear(){
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR);
	}
	

	
	/**
	 * 根据秒得到标准格式的时间,标准格式如下:"2005-03-23".
	 * 
	 * @return 以字符串表示的标准格式的时间,如:"2005-03-23".
	 */
	public static String getSpecifiedStandardDate(long second) {
		Date date = new Date(second * 1000);
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 根据秒得到标准格式的时间,标准格式如下:"2005-03-23".
	 * 
	 * @return 以字符串表示的标准格式的时间,如:"2005-03-23".
	 */
	public static String getSpecifiedStandardDate(Date date) {
		// Date date = new Date(second * 1000);
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 根据毫秒得到标准格式的时间,标准格式如下:"2005-03-23 13:34:56".
	 * 
	 * @return 以字符串表示的标准格式的时间,如:"2005-03-23 13:34:56".
	 */
	public static String getSpecifiedStandardTime(long milisecond) {
		Date date = new Date(milisecond);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String getSpecifiedStandardTime(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 根据标准格式的时间字符串得到对应的Date对象,标准格式如下:"2005-03-23 13:34:56".
	 * 
	 * @return 时间字符串对应的Date对象.
	 * @throws ParseException
	 */
	public static Date getDateFromStandardTime(String standardTime)
			throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(standardTime);
	}
	
	/**
	 * 根据标准格式的时间字符串得到对应的Date对象,标准格式如下:"2005-03-23 13:34:56:120".
	 * 
	 * @return 时间字符串对应的Date对象.
	 * @throws ParseException
	 */
	public static Date getDateFromMillionTime(String MillionTime)
			throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").parse(MillionTime);
	}

	/**
	 * 根据指定格式的时间字符串得到对应的Date对象,指定格式如下:"2005年9月16日 14:00-15:00(北京时间)".
	 * 
	 * @return 时间字符串对应的Date对象.
	 */
	public static Date getDateFromSpecifiedTime(String specifiedTime)
			throws ParseException {
		return new SimpleDateFormat("yyyy年M月dd日 HH:mm").parse(specifiedTime);
	}

	/**
	 * 根据标准格式的日期字符串得到对应的Date对象,标准格式如下:"2005-03-23".
	 * 
	 * @return 时间字符串对应的Date对象.
	 */
	public static Date getDateFromStandardDate(String standardDate)
			throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(standardDate);
	}

	/**
	 * 指定日期，加减指定的天数，传入负数就是减
	 * 
	 * @param date
	 *            指定日期
	 * @param number
	 *            加减的天数
	 * @return
	 * @throws ParseException
	 */

	public static Date getDateByAddDays(Date date, int number)
			throws ParseException {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// out.println("It is now " + formatter.format(now.getTime()));
		now.add(Calendar.DAY_OF_YEAR, number);
		return now.getTime();
		// String str = getSpecifiedStandardDate(now.getTime().getTime());
		// return getDateFromStandardDate(str);

		// return now.getTime();
	}

	public static String getSpecifiedFormatTime(Date date, String format) {
		if (ValidateUtils.isEmpty(format))
			format = "yyyy-MM-dd HH:mm:ss";
		if (null == date)
			date = new Date();

		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	/**
	 * cumpute Date, add/minus month
	 * 
	 * @param date
	 * @param number,
	 *            month
	 * @return
	 */
	public static Date getDateByAddMonths(Date date, int number) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MONTH, number);
		return now.getTime();
	}


	/**
	 * 比较两个日期的先后顺旬
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareTo(Date date1, Date date2) {

		// Workaround for bug in JDK 1.5.x. This bug is fixed in JDK 1.5.07. See
		// http://bugs.sun.com/bugdatabase/view_bug.do;:YfiG?bug_id=6207898 for
		// more information.

		if ((date1 != null) && (date2 == null)) {
			return -1;
		} else if ((date1 == null) && (date2 != null)) {
			return 1;
		} else if ((date1 == null) && (date2 == null)) {
			return 0;
		}

		long time1 = date1.getTime();
		long time2 = date2.getTime();

		if (time1 == time2) {
			return 0;
		} else if (time1 < time2) {
			return -1;
		} else {
			return 1;
		}
	}

	public static Date getFirstDayOfMonth(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		// System.out.println(String.valueOf(month));
		String tmp = year + "-" + month + "-01";
		// System.out.println(tmp);
		Date d = null;
		try {
			d = getDateFromStandardDate(tmp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(d);
		return d;

	}
	
	/**
	 * 指定日期与当前系统日期的比较
	 * */
	public static long getBetweenMinutes(Date begin){
//		String st= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(begin);
//		System.out.println(st);
		
		Calendar c = Calendar.getInstance();
		
		long between=(c.getTime().getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
	    long day=between/(24*3600);
	    long hour=between%(24*3600)/3600;
	    long minute=between%3600/60;
	    long second=between%60/60;
	    
//	    long rv=day*24*60+hour*60 + minute; //根据需要可设置返回是天，时，分，秒，这里反回分
	    long rv=(day*24*60*60+hour*60*60 + minute*60+second)/60; //根据需要可设置返回是天，时，分，秒，这里反回秒
	    
	    if (rv==0 && between%1000>0){//保证有秒上的差异时也能区别出来
	    	rv=1;
	    }
        return rv;
	}
	
	public static long getBetweenMinutes(Date begin,Date end){
		long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
	    long day=between/(24*3600);
	    long hour=between%(24*3600)/3600;
	    long minute=between%3600/60;
	    long second=between%60/60;
	    
	    long rv=day*24*60+hour*60 + minute; //根据需要可设置返回是天，时，分，秒，这里反回分
	    
        return rv;
	}

	public static void main(String argv[]) {
		try {
			// System.out.println(TimeUtils.getDateFromSpecifiedTime("2005年9月16日
			// 14:00-15:00(北京时间)").getTime());
			// System.out.println(TimeUtils.getDateFromStandardDate("2005-11-01")
			// .getTime());
			// System.out.println(TimeUtils.getDateFromStandardDate("2005-11-08")
			// .getTime());
			// System.out.println(TimeUtils.getDateFromStandardDate("2005-11-11")
			// .getTime());
			// System.out.println(TimeUtils.getDateFromStandardDate("2005-11-21")
			// .getTime());
			// System.out.println(TimeUtils.getDateFromStandardDate("2005-11-30")
			// .getTime());
			// System.out.println(TimeUtils.getDateFromStandardDate("2005-12-01")
			// .getTime());
//			String date = TimeUtils.getSpecifiedStandardDate2(new Date());
//			System.out.println(date);
//			Date date2 = TimeUtils.getDateFromStandardDate(date);
//			System.out.println(date2.toString());
			// StringBuffer sb = new StringBuffer("reg,NW0916");
			// sb.replace(sb.indexOf(",NW0916"), sb.indexOf("NW0916") + 6, "");
			// System.out.println(sb);
			
//			String a = "2008-01-01 13:00:00";
//			String b = "2008-01-01 14:10:00";
//			Date d1 =TimeUtils.getDateFromStandardTime(a);
//			Date d2 =TimeUtils.getDateFromStandardTime(b);
//			System.out.println(TimeUtils.getBetweenMinutes(d1,d2));
//			
//	        
//	         Calendar c = Calendar.getInstance();
//	     c.add(Calendar.DATE,+1);
//	     System.out.println(c.getTime());
			String a = "2008-01-01 13:00:00";
			System.out.println(a.substring(0,10));
			
			System.out.println(getNewYear());
	     
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1125050877
	}

}
