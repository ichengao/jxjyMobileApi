package cn.gc80.datamodel.training;

import cn.gc80.datamodel.sysbase.SysCode;
import cn.gc80.datamodel.sysbase.SysProvince;

/**
 * LrnTrainClass entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TrainClass implements java.io.Serializable {

	// Fields

	private String id;
	private String className;
	private String classDesc;
	private Long classStuNumber;
	// 上课开始时间
	private String classStarttime;
	// 结束
	private String classEndtime;
	private String creator;
	private String lasteditor;
	private String createtime;
	private String lastedittime;
	private String delflag;
	// 上课地点
	private String classSpot;
	// 上课时间
	private String classTime;
	// 报名须知
	private String classDemo;
	// 发布状态
	private String state;
	// 项目id
	private TrainProject trainProject;
	// 区域id
	private SysProvince province;
	// 是否热门班级
	private String isHot;
	// 班级封面
	private String classImage;
	// 要求学时
	private Long classCredithour;
	// 价格
	private Double classPrice;
	// 班级编号
	private String classNo;
	// 招生人数
	private Long classPeopleNumber;
	// 报名人数
	private Long classEnrolNumber;
	// 报名开始时间
	private String classEnrolStarttime;
	// 报名结束时间
	private String classEnrolEndtime;
	// 报名状态
	private String classEnrolStatus;
	// 是否要求学时
	private String isAskHours;
	// 缴费方式
	private String classPaytype;
	// 培训方式
	private String classType;
	// 是否考试
	private String isExam;
	// 赠送积分
	private Long giveIntegral;
	
	private String classYear;
	
	//赋值用
	private String teachers;
	private String timeFlag;
	private String isEva;
	// Constructors

	public String getClassYear() {
		return classYear;
	}

	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}

	public String getClassNo() {
			return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	
	public Long getClassPeopleNumber() {
		return classPeopleNumber;
	}

	public void setClassPeopleNumber(Long classPeopleNumber) {
		this.classPeopleNumber = classPeopleNumber;
	}
	
		
	public Long getClassEnrolNumber() {
		return classEnrolNumber;
	}

	public void setClassEnrolNumber(Long classEnrolNumber) {
		this.classEnrolNumber = classEnrolNumber;
	}

	public String getClassEnrolStarttime() {
		return classEnrolStarttime;
	}

	public void setClassEnrolStarttime(String classEnrolStarttime) {
		this.classEnrolStarttime = classEnrolStarttime;
	}

	public String getClassEnrolEndtime() {
		return classEnrolEndtime;
	}

	public void setClassEnrolEndtime(String classEnrolEndtime) {
		this.classEnrolEndtime = classEnrolEndtime;
	}

	public String getClassEnrolStatus() {
		return classEnrolStatus;
	}

	public void setClassEnrolStatus(String classEnrolStatus) {
		this.classEnrolStatus = classEnrolStatus;
	}

	public String getIsAskHours() {
		return isAskHours;
	}

	public void setIsAskHours(String isAskHours) {
		this.isAskHours = isAskHours;
	}

		public String getClassPaytype() {
		return classPaytype;
	}

	public void setClassPaytype(String classPaytype) {
		this.classPaytype = classPaytype;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getIsExam() {
		return isExam;
	}

	public void setIsExam(String isExam) {
		this.isExam = isExam;
	}


	public Long getGiveIntegral() {
		return giveIntegral;
	}

	public void setGiveIntegral(Long giveIntegral) {
		this.giveIntegral = giveIntegral;
	}

	public TrainProject getTrainProject() {
		return trainProject;
	}

	public void setTrainProject(TrainProject trainProject) {
		this.trainProject = trainProject;
	}

	/** default constructor */
	public TrainClass() {
	}

	/** minimal constructor */
	public TrainClass(String className, Long classStuNumber, String delflag) {
		this.className = className;
		this.classStuNumber = classStuNumber;
		this.delflag = delflag;
	}

	/** full constructor */
	public TrainClass(String className, String classDesc, Long classStuNumber,
			String classStarttime, String classEndtime, String creator,
			String lasteditor, String createtime, String lastedittime,
			String delflag) {
		this.className = className;
		this.classDesc = classDesc;
		this.classStuNumber = classStuNumber;
		this.classStarttime = classStarttime;
		this.classEndtime = classEndtime;
		this.creator = creator;
		this.lasteditor = lasteditor;
		this.createtime = createtime;
		this.lastedittime = lastedittime;
		this.delflag = delflag;
	}
	
	public TrainClass(String id,String classNo,String className,String cityName,Long classCredithour,Double classPrice,String classImage) {
		this.id=id;
		this.className = className;
		this.classNo=classNo;
		SysProvince province=new SysProvince();
		province.setCityname(cityName);
		this.province=province;
		this.classCredithour=classCredithour;
		this.classPrice=classPrice;
		this.classImage=classImage;
	}
	
	public TrainClass(String id,String classNo,String className,String cityName,Long classCredithour,Double classPrice,String classImage,String codeParentName,String codeName) {
		this.id=id;
		this.classNo=classNo;
		this.className = className;
		SysProvince province=new SysProvince();
		province.setCityname(cityName);
		this.province=province;
		this.classCredithour=classCredithour;
		this.classPrice=classPrice;
		this.classImage=classImage;
		TrainProject trainProject=new TrainProject();
		SysCode sysCode=new SysCode();
		SysCode codeParent=new SysCode();
		codeParent.setCodeName(codeParentName);
		sysCode.setSysCode(codeParent);
		sysCode.setCodeName(codeName);
		trainProject.setSysCode(sysCode);
		this.trainProject=trainProject;
	}
	
	public TrainClass(String id,String classNo,String className,String cityName,Long classCredithour,Double classPrice,String classImage,String codeParentName,String codeName,String classYear) {
		this.id=id;
		this.classNo=classNo;
		this.className = className;
		SysProvince province=new SysProvince();
		province.setCityname(cityName);
		this.province=province;
		this.classCredithour=classCredithour;
		this.classPrice=classPrice;
		this.classImage=classImage;
		TrainProject trainProject=new TrainProject();
		SysCode sysCode=new SysCode();
		SysCode codeParent=new SysCode();
		codeParent.setCodeName(codeParentName);
		sysCode.setSysCode(codeParent);
		sysCode.setCodeName(codeName);
		trainProject.setSysCode(sysCode);
		this.trainProject=trainProject;
		this.classYear=classYear;
	}
	
	public TrainClass(String id,String className,String classType,String cityName, Long classCredithour, String codeParentName,String codeName, String classPaytype, String isAskHours, String classDesc,String classDemo) {
		this.id=id;
		this.className=className;
		this.classType=classType;
		SysProvince province=new SysProvince();
		province.setCityname(cityName);
		this.province=province;
		this.classCredithour=classCredithour;
		TrainProject trainProject=new TrainProject();
		SysCode sysCode=new SysCode();
		SysCode codeParent=new SysCode();
		codeParent.setCodeName(codeParentName);
		sysCode.setSysCode(codeParent);
		sysCode.setCodeName(codeName);
		trainProject.setSysCode(sysCode);
		this.trainProject=trainProject;
		this.classPaytype=classPaytype;
		this.isAskHours=isAskHours;
		this.classDesc=classDesc;
		this.classDemo=classDemo;
	}
	
	public TrainClass(String className, String classYear) {
		this.className = className;
		this.classYear=classYear;
	}
	
	public TrainClass(String cityname) {
		SysProvince province=new SysProvince();
		province.setCityname(cityname);
		this.province=province;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassDesc() {
		return this.classDesc;
	}

	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}

	public Long getClassStuNumber() {
		return this.classStuNumber;
	}

	public void setClassStuNumber(Long classStuNumber) {
		this.classStuNumber = classStuNumber;
	}

	public String getClassStarttime() {
		return this.classStarttime;
	}

	public void setClassStarttime(String classStarttime) {
		this.classStarttime = classStarttime;
	}

	public String getClassEndtime() {
		return this.classEndtime;
	}

	public void setClassEndtime(String classEndtime) {
		this.classEndtime = classEndtime;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getLasteditor() {
		return this.lasteditor;
	}

	public void setLasteditor(String lasteditor) {
		this.lasteditor = lasteditor;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getLastedittime() {
		return this.lastedittime;
	}

	public void setLastedittime(String lastedittime) {
		this.lastedittime = lastedittime;
	}

	public String getDelflag() {
		return this.delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public SysProvince getProvince() {
		return province;
	}

	public void setProvince(SysProvince province) {
		this.province = province;
	}

	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	public String getClassImage() {
		return classImage;
	}

	public void setClassImage(String classImage) {
		this.classImage = classImage;
	}

	public Long getClassCredithour() {
		return classCredithour;
	}

	public void setClassCredithour(Long classCredithour) {
		this.classCredithour = classCredithour;
	}

	public Double getClassPrice() {
		return classPrice;
	}

	public void setClassPrice(Double classPrice) {
		this.classPrice = classPrice;
	}

	public String getClassSpot() {
		return classSpot;
	}

	public void setClassSpot(String classSpot) {
		this.classSpot = classSpot;
	}

	public String getClassTime() {
		return classTime;
	}

	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}

	public String getClassDemo() {
		return classDemo;
	}

	public void setClassDemo(String classDemo) {
		this.classDemo = classDemo;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public String getTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
	}

	public String getIsEva() {
		return isEva;
	}

	public void setIsEva(String isEva) {
		this.isEva = isEva;
	}
}