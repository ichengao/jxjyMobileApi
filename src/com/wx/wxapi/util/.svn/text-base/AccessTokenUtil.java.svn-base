package com.wx.wxapi.util;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import com.wx.wxapi.MpApi;
import com.wx.wxapi.json.AccessToken;

/**
 * 访问凭证定时刷新
 */
public class AccessTokenUtil {

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

	/**
	 * 获取凭证
	 */
	public static String getTokenStr() {
		return queryAccessToken().getAccess_token();
	}

	/**
	 * 刷新并返回新凭证
	 */
	public static synchronized String refreshAndGetToken() {
		AccessToken tk = queryAccessToken();
		// 10秒之内只刷新一次，防止并发引起的多次刷新
		if (tk == null || (System.currentTimeMillis() - tk.getCreateTime() > 10000)) {
			refreshToken();
		}
		return getTokenStr();
	}

	// 刷新凭证并更新全局凭证值
	private static void refreshToken() {
		try {
			System.out.println("refresh token...");
			AccessToken accessToken = MpApi.getAccessToken();
			saveAccessToken(accessToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void init() {
		if (queryAccessToken() == null) {
			refreshToken();
		}
		initTimer(queryAccessToken());
	}

	/**
	 * 定时刷新token
	 */
	private static void initTimer(AccessToken tk) {
		// 刷新频率：有效期的三分之二
		long refreshTime = tk.getExpires_in() * 2 / 3;
		// 延迟时间100秒内随机
		long delay = (long) (100 * (new Random().nextDouble()));
		timer.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				AccessToken actk = queryAccessToken();
				// 200秒内只刷新一次，防止分布式集群定时任务同一段时间内重复刷新
				if (actk == null || (System.currentTimeMillis() - actk.getCreateTime() > 200000)) {
					refreshToken();
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
	 * 凭证的存储需要全局唯一
	 * <p>
	 * 单机部署情况下可以存在内存中 <br>
	 * 分布式情况需要存在集中缓存或DB中
	 */
	private static AccessToken token;

	/**
	 * 获取存储的token
	 */
	public static AccessToken queryAccessToken() {
		return token;
	}

	/**
	 * 保存token
	 */
	private static void saveAccessToken(AccessToken accessToken) {
		token = accessToken;
	}

	public static void main(String[] args) {
		System.out.println("---"+getTokenStr());
	}

}
