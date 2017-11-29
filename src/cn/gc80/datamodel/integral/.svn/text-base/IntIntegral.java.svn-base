package cn.gc80.datamodel.integral;

import cn.gc80.datamodel.sysbase.SysUser;

/**
 * LRN_INT_INTEGRAL(积分表)
 */
public class IntIntegral implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	// 用户id
	private SysUser sysUser;
	// 积分项目id
	private IntContrast intContrast;
	// 分值
	private Long integral;
	// 设备
	private String equipment;
	// 帮助人id
	private SysUser sysUserHelp;
	// 访问ip
	private String ip;
	// 创建时间
	private String createtime;
	// 删除标志
	private String delflag;
	// 备注
	private String describe;
	// 类型
	private String type;
	// 物流id
	private String expressId;
	// 当前积分
	private Long CurrentIntegral;

	// Constructors

	
	/** default constructor */
	public IntIntegral() {
	}
	
	
	
	public IntIntegral(String id,String integralItem,long integral,String equipment,String ip,String createtime,String describe,String type,long CurrentIntegral,String userName) {
		this.id = id;
		IntContrast intContrast=new IntContrast();
		intContrast.setIntegralItem(integralItem);
		this.intContrast=intContrast;
		this.integral=integral;
		this.equipment=equipment;
		this.ip=ip;
		this.createtime=createtime;
		this.describe=describe;
		this.type=type;
		this.CurrentIntegral=CurrentIntegral;
		SysUser sysUser=new SysUser();
		sysUser.setUserName(userName);
		this.sysUser=sysUser;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public IntContrast getIntContrast() {
		return intContrast;
	}

	public void setIntContrast(IntContrast intContrast) {
		this.intContrast = intContrast;
	}

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public SysUser getSysUserHelp() {
		return sysUserHelp;
	}

	public void setSysUserHelp(SysUser sysUserHelp) {
		this.sysUserHelp = sysUserHelp;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpressId() {
		return expressId;
	}

	public void setExpressId(String expressId) {
		this.expressId = expressId;
	}

	public Long getCurrentIntegral() {
		return CurrentIntegral;
	}

	public void setCurrentIntegral(Long currentIntegral) {
		CurrentIntegral = currentIntegral;
	}

}
