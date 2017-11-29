package cn.gc80.datamodel.exam;

import java.util.HashSet;
import java.util.Set;

import cn.gc80.datamodel.res.ResCourse;
import cn.gc80.datamodel.res.ResCourseware;
import cn.gc80.datamodel.res.ResResinfo;
import cn.gc80.datamodel.sysbase.SysCode;
import cn.gc80.datamodel.training.TrainProject;

/**
 * ResPaper generated by MyEclipse Persistence Tools
 */

public class ExamPaper implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8808617571736025753L;

	private String id;

	private ExamQuiz examQuiz;
	private TrainProject trainProject;
	
	private ResCourse resCourse;
	
	private ResCourseware resCourseware;

	private ResResinfo resResinfo;

	private SysCode sysCode;

	private String description;

	private String name;

	private String source;

	private String scope;

	private Long totalPoint;

	private String randomquestions;

	private String status;

	private String paperType;

	private String buildType;

	private String lastedittime;

	private String creator;

	private String lasteditor;

	private String createtime;

	private String delflag;

	private Set examPaperQuestions = new HashSet(0);

	private Set examUserHouses = new HashSet(0);

	// Constructors

	/** default constructor */
	public ExamPaper() {
	}

	/** minimal constructor */
	public ExamPaper(String name, String randomquestions, String status,
			String delflag) {
		this.name = name;
		this.randomquestions = randomquestions;
		this.status = status;
		this.delflag = delflag;
	}

	/** full constructor */
	public ExamPaper(ResCourse resCourse,ResResinfo resResinfo,
			SysCode sysCode, String description, String name,
			String source, String scope, Long totalPoint,
			String randomquestions, String status, String paperType,
			String buildType, String lastedittime, String creator,
			String lasteditor, String createtime, String delflag,
			Set examPaperQuestions, Set examUserHouses) {
		this.resCourse = resCourse;
		this.resResinfo = resResinfo;
		this.sysCode = sysCode;
		this.description = description;
		this.name = name;
		this.source = source;
		this.scope = scope;
		this.totalPoint = totalPoint;
		this.randomquestions = randomquestions;
		this.status = status;
		this.paperType = paperType;
		this.buildType = buildType;
		this.lastedittime = lastedittime;
		this.creator = creator;
		this.lasteditor = lasteditor;
		this.createtime = createtime;
		this.delflag = delflag;
		this.examPaperQuestions = examPaperQuestions;
		this.examUserHouses = examUserHouses;
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

	public ResResinfo getResResinfo() {
		return this.resResinfo;
	}

	public void setResResinfo(ResResinfo resResinfo) {
		this.resResinfo = resResinfo;
	}

	public SysCode getSysCode() {
		return this.sysCode;
	}

	public void setSysCode(SysCode sysCode) {
		this.sysCode = sysCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Long getTotalPoint() {
		return this.totalPoint;
	}

	public void setTotalPoint(Long totalPoint) {
		this.totalPoint = totalPoint;
	}

	public String getRandomquestions() {
		return this.randomquestions;
	}

	public void setRandomquestions(String randomquestions) {
		this.randomquestions = randomquestions;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaperType() {
		return this.paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getBuildType() {
		return this.buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getLastedittime() {
		return this.lastedittime;
	}

	public void setLastedittime(String lastedittime) {
		this.lastedittime = lastedittime;
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

	public String getDelflag() {
		return this.delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public Set getExamUserHouses() {
		return examUserHouses;
	}

	public void setExamUserHouses(Set examUserHouses) {
		this.examUserHouses = examUserHouses;
	}

	public Set getExamPaperQuestions() {
		return examPaperQuestions;
	}

	public void setExamPaperQuestions(Set examPaperQuestions) {
		this.examPaperQuestions = examPaperQuestions;
	}

	public ResCourseware getResCourseware() {
		return resCourseware;
	}

	public void setResCourseware(ResCourseware resCourseware) {
		this.resCourseware = resCourseware;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public ExamQuiz getExamQuiz() {
		return examQuiz;
	}

	public void setExamQuiz(ExamQuiz examQuiz) {
		this.examQuiz = examQuiz;
	}

	public TrainProject getTrainProject() {
		return trainProject;
	}

	public void setTrainProject(TrainProject trainProject) {
		this.trainProject = trainProject;
	}

}