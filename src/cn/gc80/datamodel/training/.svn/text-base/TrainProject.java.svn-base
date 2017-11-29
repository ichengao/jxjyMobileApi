package cn.gc80.datamodel.training;

import cn.gc80.datamodel.sysbase.SysCode;

/**
 * LrnTrainProject entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TrainProject implements java.io.Serializable {

	// Fields

	private String id;
	private String projName;
	private String projDesc;
	private Long projCredit;
	// 必修课学时
	private Long projCreditRequired;
	// 选修课学时
	private Long projCreditOption;
	private Long projClassNumber;
	// 缴费方式
	private String projPayType;
	// 最小学习时间
	private Long projMinHour;
	// 最小学习次数
	private Long projMinTime;
	// 通过成绩
	private Long projScore;
	// 培训方式,01在线，02面授，03混合
	private String projTrainType;
	private Long projPrice;
	private String creator;
	private String lasteditor;
	private String createtime;
	private String lastedittime;
	private String delflag;
	// 是否需要面授测评
	private String ismsexam;
	// 面授成绩
	private Long msscore;
	// 是否有教材
	private String isbook;
	// 是否需要听课证
	private String isrollcard;
	// 发布状态 01结束，02发布
	private String status;
	// 必修课学时
	private long questionCount;
	// 信息显示的静态页面名称
	private long pageName;
	private String isshow;
	// 项目分类
	private SysCode sysCode;
	//班级数量
	private long classNumber;
	//项目编号
	private String projNo;
	
	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public long getPageName() {
		return pageName;
	}

	public void setPageName(long pageName) {
		this.pageName = pageName;
	}

	public long getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(long questionCount) {
		this.questionCount = questionCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsbook() {
		return isbook;
	}

	public void setIsbook(String isbook) {
		this.isbook = isbook;
	}

	public String getIsrollcard() {
		return isrollcard;
	}

	public void setIsrollcard(String isrollcard) {
		this.isrollcard = isrollcard;
	}

	public String getIsmsexam() {
		return ismsexam;
	}

	public void setIsmsexam(String ismsexam) {
		this.ismsexam = ismsexam;
	}

	public Long getMsscore() {
		return msscore;
	}

	public void setMsscore(Long msscore) {
		this.msscore = msscore;
	}

	/** default constructor */
	public TrainProject() {
	}

	/** minimal constructor */
	public TrainProject(String projName, Long projCredit, Long projClassNumber,
			String projPayType, Long projMinHour, Long projMinTime,
			Long projScore, String projTrainType, String delflag) {
		this.projName = projName;
		this.projCredit = projCredit;
		this.projClassNumber = projClassNumber;
		this.projPayType = projPayType;
		this.projMinHour = projMinHour;
		this.projMinTime = projMinTime;
		this.projScore = projScore;
		this.projTrainType = projTrainType;
		this.delflag = delflag;

	}

	public TrainProject(String id, String projName, Long projCredit,
			Long projPrice) {
		this.id = id;
		this.projName = projName;
		this.projCredit = projCredit;
		this.projPrice = projPrice;
	}

	/** full constructor */
	public TrainProject(String id, String projName, String projDesc,
			Long projCredit, Long projCreditRequired, Long projCreditOption,
			Long projClassNumber, String projPayType, Long projMinHour,
			Long projMinTime, Long projScore, String projTrainType,
			Long projPrice, String creator, String lasteditor,
			String createtime, String lastedittime, String delflag,
			String ismsexam, Long msscore, String isbook, String isrollcard,
			String status, long questionCount, long pageName, String isshow,
			SysCode sysCode, long classNumber, String projNo) {
		this.id = id;
		this.projName = projName;
		this.projDesc = projDesc;
		this.projCredit = projCredit;
		this.projCreditRequired = projCreditRequired;
		this.projCreditOption = projCreditOption;
		this.projClassNumber = projClassNumber;
		this.projPayType = projPayType;
		this.projMinHour = projMinHour;
		this.projMinTime = projMinTime;
		this.projScore = projScore;
		this.projTrainType = projTrainType;
		this.projPrice = projPrice;
		this.creator = creator;
		this.lasteditor = lasteditor;
		this.createtime = createtime;
		this.lastedittime = lastedittime;
		this.delflag = delflag;
		this.ismsexam = ismsexam;
		this.msscore = msscore;
		this.isbook = isbook;
		this.isrollcard = isrollcard;
		this.status = status;
		this.questionCount = questionCount;
		this.pageName = pageName;
		this.isshow = isshow;
		this.sysCode = sysCode;
		this.projNo = projNo;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjDesc() {
		return this.projDesc;
	}

	public void setProjDesc(String projDesc) {
		this.projDesc = projDesc;
	}

	public Long getProjCredit() {
		return this.projCredit;
	}

	public void setProjCredit(Long projCredit) {
		this.projCredit = projCredit;
	}

	public Long getProjClassNumber() {
		return this.projClassNumber;
	}

	public void setProjClassNumber(Long projClassNumber) {
		this.projClassNumber = projClassNumber;
	}

	public String getProjPayType() {
		return this.projPayType;
	}

	public void setProjPayType(String projPayType) {
		this.projPayType = projPayType;
	}

	public Long getProjMinHour() {
		return this.projMinHour;
	}

	public void setProjMinHour(Long projMinHour) {
		this.projMinHour = projMinHour;
	}

	public Long getProjMinTime() {
		return this.projMinTime;
	}

	public void setProjMinTime(Long projMinTime) {
		this.projMinTime = projMinTime;
	}

	public Long getProjScore() {
		return this.projScore;
	}

	public void setProjScore(Long projScore) {
		this.projScore = projScore;
	}

	public String getProjTrainType() {
		return this.projTrainType;
	}

	public void setProjTrainType(String projTrainType) {
		this.projTrainType = projTrainType;
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

	public Long getProjPrice() {
		return projPrice;
	}

	public void setProjPrice(Long projPrice) {
		this.projPrice = projPrice;
	}

	public Long getProjCreditRequired() {
		return projCreditRequired;
	}

	public void setProjCreditRequired(Long projCreditRequired) {
		this.projCreditRequired = projCreditRequired;
	}

	public Long getProjCreditOption() {
		return projCreditOption;
	}

	public void setProjCreditOption(Long projCreditOption) {
		this.projCreditOption = projCreditOption;
	}

	public SysCode getSysCode() {
		return sysCode;
	}

	public void setSysCode(SysCode sysCode) {
		this.sysCode = sysCode;
	}

	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
}