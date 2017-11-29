package com.wx.wxapi.util;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import com.wx.wxapi.MpApi;
import com.wx.wxapi.json.JsApiTicket;

/**
 * JsApiTicket 临时票据
 * 
 * @author WWKJ0123
 * 
 */
public class JsApiTicketUtil {

	private static ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {

		@Override
		public Thread newThread(Runnable run) {
			Thread t = new Thread(run);
			t.setDaemon(true);
			return t;
		}

	});

	static {
		init();
	}

	/** 获取临时票据 */
	public static String getJsApiTicketStr() {
		return queryJsApiTicket().getTicket();
	}

	/** 刷新并返回新的临时票据 */
	public static synchronized String refreshAndGetJsApiTicket() {
		JsApiTicket tk = queryJsApiTicket();
		// 10秒之内只刷新一次，防止并发引起的多次刷新
		if (tk == null || (System.currentTimeMillis() - Long.parseLong(tk.getCreatetime()) > 10000)) {
			refreshJsApiTicket();
		}
		return getJsApiTicketStr();
	}

	// 刷新凭证并更新全局凭着值
	private static void refreshJsApiTicket() {
		try {
			System.out.println("refresh ticket...");
			JsApiTicket jsApiTicket = MpApi.getJsApiTicket();
			saveJsApiTicket(jsApiTicket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 初始化 */
	private static void init() {
		if (queryJsApiTicket() == null) {
			refreshJsApiTicket();
		}
		initTimer(queryJsApiTicket());
	}

	/** 定时刷新ticket */
	private static void initTimer(JsApiTicket tk) {
		// 刷新频率：有效期的三分之二?
		long refreshTime = Long.parseLong(tk.getExpires_in()) * 2 / 3;
		// 延迟时间100秒内随机
		long delay = (long) (100 * (new Random().nextDouble()));
		timer.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				JsApiTicket tk = queryJsApiTicket();
				// 200秒内只刷新一次，防止分布式集群定时任务同一时间内重复刷新
				if (tk == null || (System.currentTimeMillis() - Long.parseLong(tk.getCreatetime()) > 200000)) {
					refreshJsApiTicket();
				}
			}
		}, delay, refreshTime, TimeUnit.SECONDS);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				timer.shutdown();
			}
		}));
	}

	/**
	 * 临时票据的存储需要全局唯一
	 * <p>
	 * 单机部署情况下可以存在内存中 <br>
	 * 分布式情况需要存在集中缓存或DB中
	 */
	private static JsApiTicket ticket;

	/** 获取存储的jsapi_ticket */
	public static JsApiTicket queryJsApiTicket() {
		return ticket;
	}

	/** 保存ticket */
	private static void saveJsApiTicket(JsApiTicket jsapi_ticket) {
		ticket = jsapi_ticket;
	}

	public static void main(String[] args) {
		System.out.println(getJsApiTicketStr());
	}

}
