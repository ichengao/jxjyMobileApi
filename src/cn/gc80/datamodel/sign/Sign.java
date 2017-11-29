package cn.gc80.datamodel.sign;

import cn.gc80.datamodel.sysbase.SysUser;

public class Sign {
	 private String id;
	 private SysUser sysUser;
	 private String time;
	 //奖励
	 private Long reward;
	 private String describe;
	 private String equipment;//设备
	 private Long continuDays;//连续签到天数
	public String getId() {
		return id;
	}
	public Sign(){
		
	}
	public Sign(String id, SysUser sysUser, String time, Long reward,
			String describe, String equipment) {
		super();
		this.id = id;
		this.sysUser = sysUser;
		this.time = time;
		this.reward = reward;
		this.describe = describe;
		this.equipment = equipment;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getReward() {
		return reward;
	}
	public void setReward(Long reward) {
		this.reward = reward;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	
	public Long getContinuDays() {
		return continuDays;
	}
	public void setContinuDays(Long continuDays) {
		this.continuDays = continuDays;
	}
	public Sign(String id, SysUser sysUser, String time, Long reward,
			String describe) {
		super();
		this.id = id;
		this.sysUser = sysUser;
		this.time = time;
		this.reward = reward;
		this.describe = describe;
	}
	public Sign(String time) {
		super();
		this.time = time;
	}
	public Sign(String id, SysUser sysUser, String time, Long reward,
			String describe, String equipment, Long continuDays) {
		super();
		this.id = id;
		this.sysUser = sysUser;
		this.time = time;
		this.reward = reward;
		this.describe = describe;
		this.equipment = equipment;
		this.continuDays = continuDays;
	}
	 

}
