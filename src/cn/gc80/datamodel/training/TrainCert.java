package cn.gc80.datamodel.training;

import java.util.List;

import cn.gc80.datamodel.business.EOrderExpress;
import cn.gc80.datamodel.business.EUserAddress;
import cn.gc80.datamodel.sysbase.SysUser;

/**
 * LRN_TRAIN_CERT（证书表）
 */

public class TrainCert implements java.io.Serializable {

	private String id;

	private String certName;

	private String certAlias;

	private String certNo;

	private Long credit;

	private Long certSeq;

	private String cardno;

	private String year;

	private String trainType;

	private String certType;

	private String organization;

	private String certDate;

	private String realname;

	private String createtime;

	private String delflag;

	private SysUser sysUser;

	private String starttime;

	private String finishtime;

	private String printFlag;

	private List creditList;

	private EUserAddress address;

	private String expressFlag;

	private String expressCom;

	private String expressNo;

	private String expressState;

	private String startYear;

	private String startMonth;

	private String startDay;

	private String endYear;

	private String endMonth;

	private String endDay;

	private String createYear;

	private String createMonth;

	private String createDay;

	private String courseType;

	private String certQrImage;

	private String userphoto;

	private String webName;

	private String webSite;

	private String webFlag;

	private String courseName;
	// 领取方式
	private String receiveType;
	// 代领状态
	private String leadState;
	// 代领人
	private SysUser sysUserLead;
	// 运单id
	private EOrderExpress eOrderExpress;

	private TrainClass trainClass;
	// 打印时间
	private String printTime;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getWebFlag() {
		return webFlag;
	}

	public void setWebFlag(String webFlag) {
		this.webFlag = webFlag;
	}

	public String getUserphoto() {
		return userphoto;
	}

	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}

	public String getCertQrImage() {
		return certQrImage;
	}

	public void setCertQrImage(String certQrImage) {
		this.certQrImage = certQrImage;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	/** default constructor */
	public TrainCert() {
	}

	// Constructors

	public TrainCert(String id, String realname, String cardno,
			String organization, String trainType, Long credit,
			String certName, String certDate, String year, String certNo) {
		this.id = id;
		this.realname = realname;
		this.cardno = cardno;
		this.organization = organization;
		this.trainType = trainType;
		this.credit = credit;
		this.certName = certName;
		this.certDate = certDate;
		this.year = year;
		this.certNo = certNo;
	}
	
	public TrainCert(String id,String realname, String cardno,String certType, Long credit,String certName, String year, String certNo) {
		this.id=id;
		this.realname = realname;
		this.cardno = cardno;
		this.certType = certType;
		this.credit = credit;
		this.certName = certName;
		this.year = year;
		this.certNo =certNo;
	}

	public TrainCert(String realname, String certName, String certNo,
			String organization, String certDate, String expressState) {
		this.realname = realname;
		this.certName = certName;
		this.certNo = certNo;
		this.organization = organization;
		this.certDate = certDate;
		this.expressState = expressState;
	}

	public TrainCert(String id, String realname, String certName,
			String certNo, String organization, String certDate,
			String expressState) {
		this.id = id;
		this.realname = realname;
		this.certName = certName;
		this.certNo = certNo;
		this.organization = organization;
		this.certDate = certDate;
		this.expressState = expressState;
	}
	
	public TrainCert(String classId,String className,String userId,String realname,String cardno,String sex,String organization,String userphoto,String starttime,String finishtime
			,String createtime,long credit) {
		TrainClass trainClass=new TrainClass();
		trainClass.setId(classId);
		trainClass.setClassName(className);
		this.trainClass = trainClass;
		SysUser sysUser=new SysUser();
		sysUser.setId(userId);
		sysUser.setRealname(realname);
		sysUser.setCardno(cardno);
		sysUser.setSex(sex);
		sysUser.setUserphoto(userphoto);
		this.sysUser=sysUser;
		this.realname=realname;
		this.cardno=cardno;
		this.organization=organization;
		this.starttime=starttime;
		this.finishtime=finishtime;
		this.createtime=createtime;
		this.credit=credit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public Long getCredit() {
		return credit;
	}

	public void setCredit(Long credit) {
		this.credit = credit;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCertDate() {
		return certDate;
	}

	public void setCertDate(String certDate) {
		this.certDate = certDate;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public List getCreditList() {
		return creditList;
	}

	public void setCreditList(List creditList) {
		this.creditList = creditList;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
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

	public String getCertAlias() {
		return certAlias;
	}

	public void setCertAlias(String certAlias) {
		this.certAlias = certAlias;
	}

	public EUserAddress getAddress() {
		return address;
	}

	public void setAddress(EUserAddress address) {
		this.address = address;
	}

	public String getExpressFlag() {
		return expressFlag;
	}

	public void setExpressFlag(String expressFlag) {
		this.expressFlag = expressFlag;
	}

	public String getExpressCom() {
		return expressCom;
	}

	public void setExpressCom(String expressCom) {
		this.expressCom = expressCom;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getExpressState() {
		return expressState;
	}

	public void setExpressState(String expressState) {
		this.expressState = expressState;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getCreateYear() {
		return createYear;
	}

	public void setCreateYear(String createYear) {
		this.createYear = createYear;
	}

	public String getCreateMonth() {
		return createMonth;
	}

	public void setCreateMonth(String createMonth) {
		this.createMonth = createMonth;
	}

	public String getCreateDay() {
		return createDay;
	}

	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}

	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public Long getCertSeq() {
		return certSeq;
	}

	public void setCertSeq(Long certSeq) {
		this.certSeq = certSeq;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	public String getLeadState() {
		return leadState;
	}

	public void setLeadState(String leadState) {
		this.leadState = leadState;
	}

	public SysUser getSysUserLead() {
		return sysUserLead;
	}

	public void setSysUserLead(SysUser sysUserLead) {
		this.sysUserLead = sysUserLead;
	}

	public EOrderExpress geteOrderExpress() {
		return eOrderExpress;
	}

	public void seteOrderExpress(EOrderExpress eOrderExpress) {
		this.eOrderExpress = eOrderExpress;
	}

	public TrainClass getTrainClass() {
		return trainClass;
	}

	public void setTrainClass(TrainClass trainClass) {
		this.trainClass = trainClass;
	}

	public String getPrintTime() {
		return printTime;
	}

	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}

}