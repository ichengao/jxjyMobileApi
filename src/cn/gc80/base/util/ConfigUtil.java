package cn.gc80.base.util;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * 负责提取配置文件信息，并监测配置文件的改动
 * 
 * 
 */
public class ConfigUtil {
	
	private static Logger logger = Logger.getLogger(ConfigUtil.class);
	
	private static long lastModified = 0L;

	private static File configFile = null;  

	private static Logger log = Logger.getLogger(ConfigUtil.class);

	private static Properties props = new Properties();

	static {
		
		logger.info("Prepare to load property from file.");
		
		loadProperty();
		
		logger.info("Success for loading !");
		
	}

	/**
	 * 从配置文件中读取所有的属性
	 */
	private static void loadProperty() {
		try {
			String path = ConfigUtil.class.getResource("/config.properties").getFile();
			
			logger.info("loading property from file : " + path);
			
			if (System.getProperty("os.name").startsWith("Windows")) {
				path = path.substring(1).replaceAll("%20", " ");
			}
			
			File propertyFile = new File(path);

			lastModified = propertyFile.lastModified();
			
			configFile = propertyFile;
			
			props.load(new FileInputStream(propertyFile));
			
			(new ReloadThread()).start();
		} catch (Exception e) {
			log.error("load config falied! caused by : ", e);
			System.exit(-1);
		}
	}

	/**
	 * 检测config文件是否被改动，改动后立即更新
	 */
	private static void checkUpdate() {
		if (configFile != null) {
			long m = configFile.lastModified();
			if (m != lastModified) {
				lastModified = m;
				try {
					Properties prop = new Properties();
					prop.load(new FileInputStream(configFile));
					props = prop;
					log.info("reload config file:" + configFile.getAbsolutePath());
				} catch (Exception e) {
					log.error("failed to reload config file: " + configFile.getAbsolutePath(), e);
				}
			}
		}
	}

	/**
	 * 根据属性名获得对应值，如果得不到值返回defaultValue
	 * 
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getConfig(String name, String defaultValue) {
		checkUpdate();
		String ret = props.getProperty(name, defaultValue);
		if (ret == null) {
			return defaultValue;
		} else {
			return ret.trim();
		}
	}

	public static boolean getConfigBoolean(String name) {
		boolean flag = false;
		name = getConfig(name, null);
		if (name != null)
			flag = Boolean.parseBoolean(name);
		return flag;
	}

	public static String getConfig(String name) {
		return getConfig(name, null);
	}

	/**
	 * 检测config文件是否被改动的线程，每5秒检测一次
	 */
	static class ReloadThread extends Thread {
		public void run() {
			log.info("update checking for config file: " + configFile.getAbsolutePath());
			while (true) {
				if (configFile != null) {
					long m = configFile.lastModified();
					if (m != lastModified) {
						lastModified = m;
						try {
							Properties prop = new Properties();
							prop.load(new FileInputStream(configFile));
							props = prop;
							log.info("config file changed, reload: " + configFile.getAbsolutePath());
						} catch (Exception e) {
							log.error("failed to reload config file: " + configFile.getAbsolutePath(), e);
						}
					}
					try {
						Thread.sleep(24*60*60*1000);
					} catch (Exception e) {
						log.error("", e);
					}
				} else
					break;
			}
		}
	}

}

