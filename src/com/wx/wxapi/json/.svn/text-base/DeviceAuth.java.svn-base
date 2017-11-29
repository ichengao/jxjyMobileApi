package com.wx.wxapi.json;

/**
 * 设备授权属性
 */
public class DeviceAuth {

	private String id;// 设备的deviceid
	/**
	 * 设备的mac地址（48bit）格式采用16进制串的方式（长度为12字节），不需要0X前缀，如： 1234567890AB
	 */
	private String mac;
	/**
	 * android classic bluetooth – 1 ios classic bluetooth – 2 ble – 3 wifi -- 4
	 * 一个设备可以支持多种连接类型，用符号"|"做分割， 客户端优先选择靠前的连接方式（优先级按|关系的排序依次降低）
	 */
	private String connect_protocol;
	/**
	 * auth及通信的加密key，第三方需要将key烧制在设备上（128bit）， 格式采用16进制串的方式（长度为32字节），不需要0X前缀，如：
	 * 1234567890ABCDEF1234567890ABCDEF
	 */
	private String auth_key;

	/**
	 * 断开策略，目前支持： 1：退出公众号页面时即断开连接 2：退出公众号之后保持连接不断开
	 * 3：退出公众号之后一直保持连接（设备主动断开连接后，微信尝试重连）
	 */
	private String close_strategy;

	/**
	 * 连接策略，32位整型，按bit位置位，目前仅第1bit和第3bit位有效（bit置0为无效，1为有效；第2bit已被废弃），且bit位可以按或置位
	 * （如1|4=5），各bit置位含义说明如下：<br/>
	 * 1：（第1bit置位）在公众号对话页面，不停的尝试连接设备<br/>
	 * 4：（第3bit置位）处于非公众号页面（如主界面等），微信自动连接。当用户切换微信到前台时，可能尝试去连接设备，连上后一定时间会断开<br/>
	 * 8：（第4bit置位），进入微信后即刻开始连接。只要微信进程在运行就不会主动断开
	 */
	private String conn_strategy;
	/**
	 * auth加密方法，目前支持两种取值： 0：不加密 1：AES加密（CBC模式，PKCS7填充方式）
	 */
	private String crypt_method = "0";
	private String auth_ver;// 0：不加密的version 1：version 1

	/**
	 * 表示mac地址在厂商广播manufature data里含有mac地址的偏移，取值如下： -1：在尾部、 -2：表示不包含mac地址
	 * 其他：非法偏移
	 */
	private String manu_mac_pos;
	/**
	 * 表示mac地址在厂商serial number里含有mac地址的偏移，取值如下： -1：表示在尾部 -2：表示不包含mac地址 其他：非法偏移
	 */
	private String ser_mac_pos;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getConnect_protocol() {
		return connect_protocol;
	}

	public void setConnect_protocol(String connect_protocol) {
		this.connect_protocol = connect_protocol;
	}

	public String getAuth_key() {
		return auth_key;
	}

	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}

	public String getClose_strategy() {
		return close_strategy;
	}

	public void setClose_strategy(String close_strategy) {
		this.close_strategy = close_strategy;
	}

	public String getConn_strategy() {
		return conn_strategy;
	}

	public void setConn_strategy(String conn_strategy) {
		this.conn_strategy = conn_strategy;
	}

	public String getCrypt_method() {
		return crypt_method;
	}

	public void setCrypt_method(String crypt_method) {
		this.crypt_method = crypt_method;
	}

	public String getAuth_ver() {
		return auth_ver;
	}

	public void setAuth_ver(String auth_ver) {
		this.auth_ver = auth_ver;
	}

	public String getManu_mac_pos() {
		return manu_mac_pos;
	}

	public void setManu_mac_pos(String manu_mac_pos) {
		this.manu_mac_pos = manu_mac_pos;
	}

	public String getSer_mac_pos() {
		return ser_mac_pos;
	}

	public void setSer_mac_pos(String ser_mac_pos) {
		this.ser_mac_pos = ser_mac_pos;
	}

}
