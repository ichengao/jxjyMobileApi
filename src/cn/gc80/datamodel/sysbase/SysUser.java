package cn.gc80.datamodel.sysbase;

import java.util.ArrayList;
import java.util.List;

import cn.gc80.datamodel.training.TrainSpot;

public class SysUser implements java.io.Serializable {
	
	private String id;
	
	private SysUserInfo sysUserInfo;
	
	private TrainSpot trainSpot;

	private String userType;

	private String area;

	private String userName;

	private String userPasswd;

	private String email;

	private String realname;

	private String cardno;

	private String mobile;

	private String phone;

	private Long logonNum;
	
	private String status;

	private String createtime;

	private String delflag;
	//账户余额
	private Double balance;
	
	private Double totalConsumeAmount=0.00;
	//累计积分
	private Long totalIntegral;
	//当前积分
	private Long surplusIntegral;
	
	private String sex;
	
	private String userphoto;
	
	private String completeStatus;
	
	private List courseList = new ArrayList();
	
	private List certList = new ArrayList();
	
    private int credit;
    
    private long courseSum;
    
    private long creditSum;
    
    private long completeCredit;
    
    private SysProvince province;
    
    private String sexName = "";
    
    private String bindMobile;//绑定手机号
    
    private int loginErrNum;//登录错误次数
    
    private String userAddress;
    
    private SysUser recommender;
    
    private String equipment;
    
    //以下赋值用
    private int classNum;
    private Long classCredithour;
    private Long resCourseCredithour;
    private Long hasCredithour;
    
	public SysUser getRecommender() {
		return recommender;
	}
	public void setRecommender(SysUser recommender) {
		this.recommender = recommender;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	// Constructors
	public long getCourseSum() {
		return courseSum;
	}
	public void setCourseSum(long courseSum) {
		this.courseSum = courseSum;
	}
	public long getCreditSum() {
		return creditSum;
	}
	public void setCreditSum(long creditSum) {
		this.creditSum = creditSum;
	}
	public long getCompleteCredit() {
		return completeCredit;
	}
	public void setCompleteCredit(long completeCredit) {
		this.completeCredit = completeCredit;
	}
	public List getCourseList() {
		return courseList;
	}
	public void setCourseList(List courseList) {
		this.courseList = courseList;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	/** default constructor */
	public SysUser() {
	}
	
	public SysUser(String id,Long totalIntegral,Long surplusIntegral){
		this.id = id;
		this.totalIntegral = totalIntegral;
		this.surplusIntegral = surplusIntegral;
	}
	public SysUser(String id,String userName,String realname,String createtime,String mobile,String email,double balance,double totalConsumeAmount){
		this.id = id;
		this.userName = userName;
		this.realname = realname;
		this.createtime = createtime;
		this.mobile = mobile;
		this.email = email;
		this.balance = balance;
		this.totalConsumeAmount = totalConsumeAmount;
	}
	
	public SysUser(String id,String userName,String realname,Double balance,Double totalConsumeAmount){
		this.id = id;
		this.userName = userName;
		this.realname = realname;
		this.balance = balance;
		this.totalConsumeAmount= totalConsumeAmount;
	}
	public SysUser(String id,String userName,String email,String realname,String userType,String status,String cardno,Double balance,Double totalConsumeAmount){
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.realname = realname;
		this.userType = userType;
		this.status = status;
		this.cardno = cardno;
		this.balance = balance;
		this.totalConsumeAmount= totalConsumeAmount;
	}
	public SysUser(String id,String userName,String email,String realname,String userType,String status,String cardno,Double balance,Double totalConsumeAmount,String completeStatus){
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.realname = realname;
		this.userType = userType;
		this.status = status;
		this.cardno = cardno;
		this.balance = balance;
		this.totalConsumeAmount= totalConsumeAmount;
		this.completeStatus = completeStatus;
	}
	
	public SysUser(String id,String userName,String email,String realname,String userType,String status,String cardno,Double balance,Double totalConsumeAmount,Long totalIntegral,Long surplusIntegral){
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.realname = realname;
		this.userType = userType;
		this.status = status;
		this.cardno = cardno;
		this.balance = balance;
		this.totalConsumeAmount= totalConsumeAmount;
		this.totalIntegral = totalIntegral;
		this.surplusIntegral = surplusIntegral;
	}
	public SysUser(String id,String realName){
		this.id=id;
		this.realname=realName;
	}
	public SysUser(String id,String realName,String cardno,String phone,String mobile,String email,String area,Double balance){
		this.id=id;
		this.area = area;
		this.email = email;
		this.realname = realName;
		this.cardno = cardno;
		this.mobile = mobile;
		this.phone = phone;
		this.balance = balance;
	}
	
	public SysUser(String id,String realName,String cardno,String phone,String mobile,String email,String area){
		this.id=id;
		this.area = area;
		this.email = email;
		this.realname = realName;
		this.cardno = cardno;
		this.mobile = mobile;
		this.phone = phone;
	}
	
	public SysUser(String id,String userName,String realname,String createtime,String mobile,String area,String email,String cardno,Double balance,Double totalConsumeAmount,Long totalIntegral,Long surplusIntegral){
		this.id = id;
		this.userName = userName;
		this.realname = realname;
		this.createtime = createtime;
		this.mobile = mobile;
		this.area = area;
		this.email = email;
		this.cardno = cardno;
		this.balance = balance;
		this.totalConsumeAmount= totalConsumeAmount;
		this.totalIntegral = totalIntegral;
		this.surplusIntegral = surplusIntegral;
	}
	/** minimal constructor */
	public SysUser(String userName, String userPasswd, String realname,
			Long logonNum, String lastLogonTime, String status,
			String delflag) {
		this.userName = userName;
		this.userPasswd = userPasswd;
		this.realname = realname;
		this.logonNum = logonNum;
		this.status = status;
		this.delflag = delflag;
	}

	public SysUser(String id, String userName, String email, String realname,
			String mobile, String phone, String createtime ,String status) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.realname = realname;
		this.mobile = mobile;
		this.phone = phone;
		this.createtime = createtime;
		this.status=status;
	}
	/** full constructor */
	public SysUser( String area, String userName,
			String userPasswd, String email, String realname,
			String cardno, String mobile, String phone, Long logonNum,
			String status, String createtime,String delflag)
	{
		this.area = area;
		this.userName = userName;
		this.userPasswd = userPasswd;
		this.email = email;
		this.realname = realname;
		this.cardno = cardno;
		this.mobile = mobile;
		this.phone = phone;
		this.logonNum = logonNum;
		this.status = status;
		this.createtime = createtime;
		this.delflag = delflag;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPasswd() {
		return this.userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Long getLogonNum() {
		return this.logonNum;
	}

	public void setLogonNum(Long logonNum) {
		this.logonNum = logonNum;
	}


	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDelflag() {
		return this.delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}


	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getTotalConsumeAmount() {
		return totalConsumeAmount;
	}

	public void setTotalConsumeAmount(Double totalConsumeAmount) {
		this.totalConsumeAmount = totalConsumeAmount;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public SysUserInfo getSysUserInfo() {
		return sysUserInfo;
	}
	public void setSysUserInfo(SysUserInfo sysUserInfo) {
		this.sysUserInfo = sysUserInfo;
	}
	public Long getTotalIntegral() {
		return totalIntegral;
	}
	public void setTotalIntegral(Long totalIntegral) {
		this.totalIntegral = totalIntegral;
	}
	public Long getSurplusIntegral() {
		return surplusIntegral;
	}
	public void setSurplusIntegral(Long surplusIntegral) {
		this.surplusIntegral = surplusIntegral;
	}
	public TrainSpot getTrainSpot() {
		return trainSpot;
	}
	public void setTrainSpot(TrainSpot trainSpot) {
		this.trainSpot = trainSpot;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public List getCertList() {
		return certList;
	}
	public void setCertList(List certList) {
		this.certList = certList;
	}

	public String getCompleteStatus() {
		return completeStatus;
	}
	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
	}
	public String getUserphoto() {
		return userphoto;
	}
	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}
	public SysProvince getProvince() {
		return province;
	}
	public void setProvince(SysProvince province) {
		this.province = province;
	}
	public String getBindMobile() {
		return bindMobile;
	}
	public void setBindMobile(String bindMoblie) {
		this.bindMobile = bindMoblie;
	}
	public int getLoginErrNum() {
		return loginErrNum;
	}
	public void setLoginErrNum(int loginErrNum) {
		this.loginErrNum = loginErrNum;
	}
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	public Long getClassCredithour() {
		return classCredithour;
	}
	public void setClassCredithour(Long classCredithour) {
		this.classCredithour = classCredithour;
	}
	public Long getResCourseCredithour() {
		return resCourseCredithour;
	}
	public void setResCourseCredithour(Long resCourseCredithour) {
		this.resCourseCredithour = resCourseCredithour;
	}
	public Long getHasCredithour() {
		return hasCredithour;
	}
	public void setHasCredithour(Long hasCredithour) {
		this.hasCredithour = hasCredithour;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	

}