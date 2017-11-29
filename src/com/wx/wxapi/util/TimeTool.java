package com.wx.wxapi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author WWKJ0123
 *
 */
public class TimeTool {

	/** 年月日格式 */
	private static String CONSTANT_YEAR_MONTH_DAY = "yyyy年MM月dd日";

	/** 当前时间格式 */
	private static String CONSTANT_CURRENT_TIME = "yyyy年MM月dd日 HH:mm:ss";

	
	/**
	 * 当前年月日
	 * 
	 * @return
	 */
	public static String currentYMD() {
		return new SimpleDateFormat(CONSTANT_YEAR_MONTH_DAY).format(Calendar.getInstance().getTime());
	}

	/**
	 * 当前时间，精确到秒
	 * 
	 * @return
	 */
	public static String currentTime() {
		return new SimpleDateFormat(CONSTANT_CURRENT_TIME).format(Calendar.getInstance().getTime());
	}

	/**
	 * 秒级时间戳
	 * 
	 * @return
	 */
	public static String timeStamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 秒级时间戳解析
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static String parseTimeStamp(String timeStamp) {
		return new SimpleDateFormat(CONSTANT_CURRENT_TIME).format(Long.parseLong(timeStamp) * 1000);
	}

	/**
	 * 秒级时间戳解析成年月日
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static String parseTimeStampToYMD(String timeStamp) {
		return new SimpleDateFormat(CONSTANT_YEAR_MONTH_DAY).format(Long.parseLong(timeStamp) * 1000);
	}

	/**
	 * 当前时间YMD转换成时间戳
	 * 
	 * @param currentYMD
	 * @return
	 */
	public static Long currentYMDTimeStamp(String currentYMD) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CONSTANT_YEAR_MONTH_DAY);
		Date date = null;
		try {
			date = dateFormat.parse(currentYMD.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime() / 1000;

	}

	/**
	 * 当前时间YMD转换成时间戳
	 * 
	 * @param currentYMD
	 * @return
	 */
	public static Long currentTimeToTimeStamp(String currentYMD) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CONSTANT_CURRENT_TIME);
		Date date = null;
		try {
			date = dateFormat.parse(currentYMD.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime() / 1000;

	}

	/**
	 * 获取今天是本周的第几天
	 * 
	 * @return
	 */
	public static int DAY_OF_WEEK() {
		int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
		return day == 0 ? 7 : day;
	}

	/**
	 * 获取今天是本月的第几天
	 * 
	 * @return
	 */
	public static int DAY_OF_MONTH() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取本周是这个月的第几周
	 * 
	 * @return
	 */
	public static int DAY_OF_WEEK_IN_MONTH() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * 获取上个月的天数
	 * 
	 * @return
	 */
	public static int LAST_MONTH_DAYS() {
		// 取得系统当前时间
		Calendar calendar = Calendar.getInstance();
		// 取得系统当前时间所在月第一天时间对象
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		// 日期减一,取得上月最后一天时间对象
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		// 输出上月最后一天日期
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取系统时间（设备校时）
	 * 
	 * @return EE+yyMMddHHmm
	 */
	public static String checkDeviceTime() {
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
		return "EE" + dateFormat.format(calendar.getTime());
	}

	public static void main(String[] args) {
		// System.out.println(LAST_MONTH_DAYS());
		System.out.println(checkDeviceTime());
	}

}
