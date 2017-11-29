package cn.gc80.datamodel.business;

import cn.gc80.datamodel.sysbase.SysCard;
import cn.gc80.datamodel.sysbase.SysUser;

/**
 * EDepositLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EDepositLog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private SysUser sysUser;
	private SysCard sysCard;
	private String depositTime;
	private String depositOperator;
	private String depositCardType;
	private String depositType;
	private Double depositAmount;
	private String depositStatus;
	private String depositOrdNo;
	private String type;
	private String time;
	private String equipment;
	private String ip;
	private String describe;
	private Double balance;

	// Constructors

	public String getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(String depositStatus) {
		this.depositStatus = depositStatus;
	}

	public String getDepositOrdNo() {
		return depositOrdNo;
	}

	public void setDepositOrdNo(String depositOrdNo) {
		this.depositOrdNo = depositOrdNo;
	}

	/** default constructor */
	public EDepositLog() {
	}

	/** minimal constructor */
	public EDepositLog(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	
	public EDepositLog(String id,String depositType,String equipment,String ip,String depositTime,String userName,String type,Double depositAmount,Double balance,String describe){
		this.id=id;
		this.depositType=depositType;
		this.equipment=equipment;
		this.ip=ip;
		this.depositTime=depositTime;
		SysUser sysUser=new SysUser();
		sysUser.setUserName(userName);
		this.sysUser=sysUser;
		this.type=type;
		this.depositAmount=depositAmount;
		this.balance=balance;
		this.describe=describe;
	}

	public EDepositLog(String id, String depositStatus, String depositOrdNo) {
		this.id = id;
		this.depositStatus = depositStatus;
		this.depositOrdNo = depositOrdNo;
	}

	public EDepositLog(String id, String userid, String userName,
			String realname, String cardid, String cardNo, Double cardBalance,
			String depositTime, String depositCardType, String depositType,
			Double depositAmount, String depositStatus, String depositOrdNo) {
		this.id = id;
		SysUser user = new SysUser();
		user.setId(userid);
		user.setUserName(userName);
		user.setRealname(realname);
		this.sysUser = user;
		SysCard card = new SysCard();
		card.setId(cardid);
		card.setCardNo(cardNo);
		card.setCardBalance(cardBalance);
		this.sysCard = card;
		this.depositTime = depositTime;
		this.depositCardType = depositCardType;
		this.depositType = depositType;
		this.depositAmount = depositAmount;
		this.depositStatus = depositStatus;
		this.depositOrdNo = depositOrdNo;
	}

	public EDepositLog(String id, String userid, String userName,
			String realname, String depositTime, String depositCardType,
			String depositType, Double depositAmount, String depositStatus,
			String depositOrdNo) {
		this.id = id;
		SysUser user = new SysUser();
		user.setId(userid);
		user.setUserName(userName);
		user.setRealname(realname);
		this.sysUser = user;
		this.depositTime = depositTime;
		this.depositCardType = depositCardType;
		this.depositType = depositType;
		this.depositAmount = depositAmount;
		this.depositStatus = depositStatus;
		this.depositOrdNo = depositOrdNo;
	}

	public EDepositLog(String id, String userid, String userName,
			String realname, String cardNo, Double cardBalance,
			String depositTime, String depositCardType, String depositType,
			Double depositAmount, String depositStatus, String depositOrdNo) {
		this.id = id;
		SysUser user = new SysUser();
		user.setId(userid);
		user.setUserName(userName);
		user.setRealname(realname);
		this.sysUser = user;
		SysCard card = new SysCard();
		card.setCardNo(cardNo);
		card.setCardBalance(cardBalance);
		this.sysCard = card;
		this.depositTime = depositTime;
		this.depositCardType = depositCardType;
		this.depositType = depositType;
		this.depositAmount = depositAmount;
		this.depositStatus = depositStatus;
		this.depositOrdNo = depositOrdNo;
	}

	/** full constructor */
	public EDepositLog(SysUser sysUser, SysCard sysCard, String depositTime,
			String depositOperator, String depositOfferType, String depositType) {
		this.sysUser = sysUser;
		this.sysCard = sysCard;
		this.depositTime = depositTime;
		this.depositOperator = depositOperator;
		this.depositType = depositType;
	}

	// Property accessors

	public EDepositLog(String id, SysUser sysUser, SysCard sysCard,
			String depositTime, String depositOperator, String depositCardType,
			String depositType, Double depositAmount, String depositStatus,
			String depositOrdNo, String type, String time, String equipment,
			String ip, String describe) {
		super();
		this.id = id;
		this.sysUser = sysUser;
		this.sysCard = sysCard;
		this.depositTime = depositTime;
		this.depositOperator = depositOperator;
		this.depositCardType = depositCardType;
		this.depositType = depositType;
		this.depositAmount = depositAmount;
		this.depositStatus = depositStatus;
		this.depositOrdNo = depositOrdNo;
		this.type = type;
		this.time = time;
		this.equipment = equipment;
		this.ip = ip;
		this.describe = describe;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public SysCard getSysCard() {
		return this.sysCard;
	}

	public void setSysCard(SysCard sysCard) {
		this.sysCard = sysCard;
	}

	public String getDepositTime() {
		return this.depositTime;
	}

	public void setDepositTime(String depositTime) {
		this.depositTime = depositTime;
	}

	public String getDepositOperator() {
		return this.depositOperator;
	}

	public void setDepositOperator(String depositOperator) {
		this.depositOperator = depositOperator;
	}

	public String getDepositCardType() {
		return depositCardType;
	}

	public void setDepositCardType(String depositCardType) {
		this.depositCardType = depositCardType;
	}

	public String getDepositType() {
		return this.depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getTime() {
		return time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}