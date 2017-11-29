package cn.gc80.datamodel.res;

import java.util.Set;

public class ResChapter implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5589080302482601809L;

	private String id;

	private ResCourseware resCourseware;

	private String chapterType;

	private String chapterTitle;
	
	private String videoUrl;

	private Long period;
	
	private Double creditnum;

	private String identifier;

	private String launch;

	private String params;

	private String scotype;

	private String prerequisites;

	private String maxtimeallowed;

	private String timelimitaction;

	private String datafromlms;

	private String masteryscore;

	private Long sequence;

	private Long thelevel;

	private String upidentifier;

	private String orgidentifier;

	private String persiststate;

	private String completionthreshold;

	private String cwNext;

	private String cwPrevious;

	private String cwExit;

	private String cwAbandon;

	private String creator;

	private String lasteditor;

	private String createtime;

	private String lastedittime;

	private String delflag;
	
	private String lessonLocation;

	private String minute;
	
	private String lessonStatus;
	
	private int score;
	
	private String examtime;
	
	private long questionCount;
	// Constructors


	public long getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(long questionCount) {
		this.questionCount = questionCount;
	}

	public String getLessonStatus() {
		return lessonStatus;
	}

	public void setLessonStatus(String lessonStatus) {
		this.lessonStatus = lessonStatus;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	/** default constructor */
	
	public ResChapter(String id,String chapterTitle,String upidentifier,String scotype){
		this.id = id;
		this.chapterTitle = chapterTitle;
		this.upidentifier = upidentifier;
		this.scotype = scotype;
	}
	
	
	public ResChapter(String courseWareid,String trackMode,String id,String scotype,String launch,Long sequence,Long period,String chapterTitle) {
		this.id=id;
		this.scotype=scotype;
		this.launch=launch;
		this.sequence=sequence;
		this.period=period;
		this.chapterTitle=chapterTitle;
		
		ResCourseware resCourseware=new ResCourseware();
		resCourseware.setId(courseWareid);
		resCourseware.setTrackMode(trackMode);
		this.resCourseware=resCourseware;
	}
	public ResChapter(){
		
	}
	
	public ResChapter(String id,String launch, String videoUrl) {
		this.id=id;
		this.launch=launch;
		this.videoUrl=videoUrl;
	}
	
	public ResChapter(String id){
		this.id = id;
	}
	/** minimal constructor */
	public ResChapter(ResCourseware resCourseware,
			String chapterTitle, Long sequence, Long thelevel, String delflag) {
		this.resCourseware = resCourseware;
		this.chapterTitle = chapterTitle;
		this.sequence = sequence;
		this.thelevel = thelevel;
		this.delflag = delflag;
	}

	/** full constructor */
	public ResChapter(ResCourseware resCourseware,
			ResResinfo resResinfo, String chapterType,
			String chapterTitle, Long period, String identifier, String launch,
			String params, String scotype, String prerequisites,
			String maxtimeallowed, String timelimitaction, String datafromlms,
			String masteryscore, Long sequence, Long thelevel,
			String upidentifier, String orgidentifier, String persiststate,
			String completionthreshold, String cwNext, String cwPrevious,
			String cwExit, String cwAbandon, String creator, String lasteditor,
			String createtime, String lastedittime, String delflag,
			Set yfssLearnHistories, Set yfssExamUserHouses,
			Set yfssAqQuestions, Set yfssLearnUserChapters,
			Set yfssTrainTeachCourseLogs, Set yfssResQueCourses) {
		this.resCourseware = resCourseware;
		this.chapterType = chapterType;
		this.chapterTitle = chapterTitle;
		this.period = period;
		this.identifier = identifier;
		this.launch = launch;
		this.params = params;
		this.scotype = scotype;
		this.prerequisites = prerequisites;
		this.maxtimeallowed = maxtimeallowed;
		this.timelimitaction = timelimitaction;
		this.datafromlms = datafromlms;
		this.masteryscore = masteryscore;
		this.sequence = sequence;
		this.thelevel = thelevel;
		this.upidentifier = upidentifier;
		this.orgidentifier = orgidentifier;
		this.persiststate = persiststate;
		this.completionthreshold = completionthreshold;
		this.cwNext = cwNext;
		this.cwPrevious = cwPrevious;
		this.cwExit = cwExit;
		this.cwAbandon = cwAbandon;
		this.creator = creator;
		this.lasteditor = lasteditor;
		this.createtime = createtime;
		this.lastedittime = lastedittime;
		this.delflag = delflag;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public ResCourseware getResCourseware() {
		return resCourseware;
	}

	public void setResCourseware(ResCourseware resCourseware) {
		this.resCourseware = resCourseware;
	}

	public String getChapterType() {
		return this.chapterType;
	}

	public void setChapterType(String chapterType) {
		this.chapterType = chapterType;
	}

	public String getChapterTitle() {
		return this.chapterTitle;
	}

	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}

	public Long getPeriod() {
		return this.period;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getLaunch() {
		return this.launch;
	}

	public void setLaunch(String launch) {
		this.launch = launch;
	}

	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getScotype() {
		return this.scotype;
	}

	public void setScotype(String scotype) {
		this.scotype = scotype;
	}

	public String getPrerequisites() {
		return this.prerequisites;
	}

	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}

	public String getMaxtimeallowed() {
		return this.maxtimeallowed;
	}

	public void setMaxtimeallowed(String maxtimeallowed) {
		this.maxtimeallowed = maxtimeallowed;
	}

	public String getTimelimitaction() {
		return this.timelimitaction;
	}

	public void setTimelimitaction(String timelimitaction) {
		this.timelimitaction = timelimitaction;
	}

	public String getDatafromlms() {
		return this.datafromlms;
	}

	public void setDatafromlms(String datafromlms) {
		this.datafromlms = datafromlms;
	}

	public String getMasteryscore() {
		return this.masteryscore;
	}

	public void setMasteryscore(String masteryscore) {
		this.masteryscore = masteryscore;
	}

	public Long getSequence() {
		return this.sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public Long getThelevel() {
		return this.thelevel;
	}

	public void setThelevel(Long thelevel) {
		this.thelevel = thelevel;
	}

	public String getUpidentifier() {
		return this.upidentifier;
	}

	public void setUpidentifier(String upidentifier) {
		this.upidentifier = upidentifier;
	}

	public String getOrgidentifier() {
		return this.orgidentifier;
	}

	public void setOrgidentifier(String orgidentifier) {
		this.orgidentifier = orgidentifier;
	}

	public String getPersiststate() {
		return this.persiststate;
	}

	public void setPersiststate(String persiststate) {
		this.persiststate = persiststate;
	}

	public String getCompletionthreshold() {
		return this.completionthreshold;
	}

	public void setCompletionthreshold(String completionthreshold) {
		this.completionthreshold = completionthreshold;
	}

	public String getCwNext() {
		return this.cwNext;
	}

	public void setCwNext(String cwNext) {
		this.cwNext = cwNext;
	}

	public String getCwPrevious() {
		return this.cwPrevious;
	}

	public void setCwPrevious(String cwPrevious) {
		this.cwPrevious = cwPrevious;
	}

	public String getCwExit() {
		return this.cwExit;
	}

	public void setCwExit(String cwExit) {
		this.cwExit = cwExit;
	}

	public String getCwAbandon() {
		return this.cwAbandon;
	}

	public void setCwAbandon(String cwAbandon) {
		this.cwAbandon = cwAbandon;
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

	public String getLessonLocation() {
		return lessonLocation;
	}

	public void setLessonLocation(String lessonLocation) {
		this.lessonLocation = lessonLocation;
	}

	public Double getCreditnum() {
		return creditnum;
	}

	public void setCreditnum(Double creditnum) {
		this.creditnum = creditnum;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getExamtime() {
		return examtime;
	}

	public void setExamtime(String examtime) {
		this.examtime = examtime;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

}