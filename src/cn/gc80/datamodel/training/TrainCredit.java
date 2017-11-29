package cn.gc80.datamodel.training;

import cn.gc80.datamodel.res.ResCourse;
import cn.gc80.datamodel.sysbase.SysCode;
import cn.gc80.datamodel.sysbase.SysUser;

/**
 * LRN_TRAIN_CREDIT（学时表）
 */

public class TrainCredit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6371335169748624638L;

	private String id;

	private ResCourse resCourse;

	private SysUser sysUser;
	
	private TrainCert trainCert;
	
	private TrainProject trainProject;
	
	private String courseName;

	private String courseMode;

	private Long credit;

	private String codeNo;

	private String createtime;
	
	private String regtime;
	
	private String year;
	
	private String creditStatus;

	private Double grade;
	
	private String regType;
	
	private String startTime;
	
	private String endTime;
	
	private Long totalcredit;
	
	private Long gxcredit;
	
	private Long zycredit;
	
	private Long zgcredit;
	//班级ID
	private TrainClass trainClass;
	
	// Constructors

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getRegType() {
		return regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	/** default constructor */
	public TrainCredit() {
	}

	public TrainCredit(String id,String userid,String realname,String cardno,String codeNo,String courseName,String courseMode,Long credit,String year,String creditStatus,String regType){
		this.id=id;
		SysUser user = new SysUser();
		user.setId(userid);
		user.setRealname(realname);
		user.setCardno(cardno);
		this.sysUser = user;
		this.codeNo = codeNo;
		this.courseName = courseName;
		this.courseMode = courseMode;
		this.credit = credit;
		this.year = year;
		this.creditStatus = creditStatus;
		this.regType = regType;
		
	}
	public TrainCredit(String id,String userid,String realname,String cardno,String codeNo,String courseName,String courseMode,Long credit,String year,String creditStatus,String regType,String regtime,String startTime,String endTime,String courseid,Long CCredithour){
		this.id=id;
		SysUser user = new SysUser();
		user.setId(userid);
		user.setRealname(realname);
		user.setCardno(cardno);
		this.sysUser = user;
		this.codeNo = codeNo;
		this.courseName = courseName;
		this.courseMode = courseMode;
		this.credit = credit;
		this.year = year;
		this.creditStatus = creditStatus;
		this.regType = regType;
		this.regtime = regtime;
		this.startTime = startTime;
		this.endTime = endTime;
		ResCourse course = new ResCourse();
		course.setId(courseid);
		course.setCCredithour(CCredithour);
		this.resCourse = course;
	}
	
	public TrainCredit(String id,String userid,String realname,String cardno,String codeNo,String courseName,String courseMode,Long credit,String year,String creditStatus,String regType,String regtime,String startTime,String endTime){
		this.id=id;
		SysUser user = new SysUser();
		user.setId(userid);
		user.setRealname(realname);
		user.setCardno(cardno);
		this.sysUser = user;
		this.codeNo = codeNo;
		this.courseName = courseName;
		this.courseMode = courseMode;
		this.credit = credit;
		this.year = year;
		this.creditStatus = creditStatus;
		this.regType = regType;
		this.regtime = regtime;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public TrainCredit(String id,String userid,String realname,String cardno,String codeNo,String courseName,String courseMode,Long credit,String year,String creditStatus,String regType,String regtime,String startTime,String endTime,String courseid,Long CCredithour,String CNo,String codeAlias){
		this.id=id;
		SysUser user = new SysUser();
		user.setId(userid);
		user.setRealname(realname);
		user.setCardno(cardno);
		this.sysUser = user;
		this.codeNo = codeNo;
		this.courseName = courseName;
		this.courseMode = courseMode;
		this.credit = credit;
		this.year = year;
		this.creditStatus = creditStatus;
		this.regType = regType;
		this.regtime = regtime;
		this.startTime = startTime;
		this.endTime = endTime;
		ResCourse course = new ResCourse();
		course.setId(courseid);
		course.setCCredithour(CCredithour);
		course.setCNo(CNo);
		SysCode code = new SysCode();
		code.setCodeAlias(codeAlias);
		course.setSysCode(code);
		this.resCourse = course;
	}
	public TrainCredit(String id,String courseName,Long credit,String creditStatus){
		this.id= id;
		this.courseName = courseName;
		this.credit = credit;
		this.creditStatus = creditStatus;
	}
	public TrainCredit(String id,String courseName,Long credit,String startTime,String endTime){
		this.id= id;
		this.courseName = courseName;
		this.credit = credit;
		this.startTime=startTime;
		this.endTime=endTime;
	}
	/** minimal constructor */
	public TrainCredit(SysUser sysUser, String courseName,
			Long credit, String codeNo, String createtime) {
		this.sysUser = sysUser;
		this.courseName = courseName;
		this.credit = credit;
		this.codeNo = codeNo;
		this.createtime = createtime;
	}

	/** full constructor */
	public TrainCredit(ResCourse resCourse,
			SysUser sysUser, String courseName, String courseMode,
			Long credit, String codeNo, String createtime) {
		this.resCourse = resCourse;
		this.sysUser = sysUser;
		this.courseName = courseName;
		this.courseMode = courseMode;
		this.credit = credit;
		this.codeNo = codeNo;
		this.createtime = createtime;
	}
	
	public TrainCredit(String userId,String year,Long totalcredit,Long gxcredit,Long zycredit, Long zgcredit){
		SysUser sysUser=new SysUser();
		sysUser.setId(userId);
		this.sysUser = sysUser;
		this.setYear(year);
		this.setTotalcredit(totalcredit);
		this.setGxcredit(gxcredit);
		this.setZycredit(zycredit);
		this.setZgcredit(zgcredit);
	}
	
	public TrainCredit(String userId,String realname,String cardno,String year,Long totalcredit,Long gxcredit,Long zycredit, Long zgcredit){
		SysUser sysUser=new SysUser();
		sysUser.setId(userId);
		sysUser.setRealname(realname);
		sysUser.setCardno(cardno);
		this.sysUser = sysUser;
		this.setYear(year);
		this.setTotalcredit(totalcredit);
		this.setGxcredit(gxcredit);
		this.setZycredit(zycredit);
		this.setZgcredit(zgcredit);
	}
	
	public TrainCredit(String id,String className){
		this.id=id;
		TrainClass trainClass=new TrainClass();
		trainClass.setClassName(className);
		this.trainClass=trainClass;
	}
	
	public TrainCredit(String id,String classId,String className,String userId,String realname,String cardno,String year,String codeNo,long credit){
		this.id=id;
		TrainClass trainClass=new TrainClass();
		trainClass.setId(classId);
		trainClass.setClassName(className);
		this.trainClass=trainClass;
		SysUser sysUser=new SysUser();
		sysUser.setId(userId);
		sysUser.setRealname(realname);
		sysUser.setCardno(cardno);
		this.sysUser=sysUser;
		this.year=year;
		if(codeNo!=null&&!"".equals(codeNo)&&codeNo.length()>=4){
			this.codeNo=codeNo.substring(0,4);
		}
		this.credit=credit;
	}
	
	public TrainCredit(String codeName,String courseName,Long credit){
		TrainProject trainProject =new TrainProject();
		SysCode sysCode=new SysCode();
		SysCode c=new SysCode();
		c.setCodeName(codeName);
		sysCode.setSysCode(c);
		trainProject.setSysCode(sysCode);
		TrainClass trainClass=new TrainClass();
		trainClass.setTrainProject(trainProject);
		this.trainClass = trainClass;
		this.courseName = courseName;
		this.credit = credit;
	}
	
	public TrainCredit(String courseName,Long credit){
		this.courseName = courseName;
		this.credit = credit;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ResCourse getResCourse() {
		return this.resCourse;
	}

	public void setResCourse(ResCourse resCourse) {
		this.resCourse = resCourse;
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseMode() {
		return this.courseMode;
	}

	public void setCourseMode(String courseMode) {
		this.courseMode = courseMode;
	}

	public Long getCredit() {
		return this.credit;
	}

	public void setCredit(Long credit) {
		this.credit = credit;
	}

	public String getCodeNo() {
		return this.codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public TrainCert getTrainCert() {
		return trainCert;
	}

	public void setTrainCert(TrainCert trainCert) {
		this.trainCert = trainCert;
	}

	public TrainProject getTrainProject() {
		return trainProject;
	}

	public void setTrainProject(TrainProject trainProject) {
		this.trainProject = trainProject;
	}

	public Long getTotalcredit() {
		return totalcredit;
	}

	public void setTotalcredit(Long totalcredit) {
		this.totalcredit = totalcredit;
	}

	public Long getGxcredit() {
		return gxcredit;
	}

	public void setGxcredit(Long gxcredit) {
		this.gxcredit = gxcredit;
	}

	public Long getZycredit() {
		return zycredit;
	}

	public void setZycredit(Long zycredit) {
		this.zycredit = zycredit;
	}

	public Long getZgcredit() {
		return zgcredit;
	}

	public void setZgcredit(Long zgcredit) {
		this.zgcredit = zgcredit;
	}

	public TrainClass getTrainClass() {
		return trainClass;
	}

	public void setTrainClass(TrainClass trainClass) {
		this.trainClass = trainClass;
	}

}