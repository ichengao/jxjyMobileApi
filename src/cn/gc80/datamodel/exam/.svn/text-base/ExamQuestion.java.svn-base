package cn.gc80.datamodel.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.gc80.datamodel.res.ResChapter;
import cn.gc80.datamodel.res.ResResinfo;
import cn.gc80.datamodel.sysbase.SysCode;

public class ExamQuestion implements java.io.Serializable {

	private static final long serialVersionUID = 7516183687751472575L;

	private String id;

	private ResResinfo resResinfo;

	private SysCode sysCode;
	
	private ExamQuestion examQuestion;
	
	private String qtype;

	private String parent;

	private String title;

	private String content;

	private String keywords;

	private String shuffleoptions;

	private String difficulty;

	private Long defaultgrade;

	private Long usetime;

	private String answers;

	private String generalfeedback;

	private String corretfeedback;

	private String incorretfeedback;

	private Long exercisetime;

	private Long correttime;

	private String lastusetime;

	private String knowledge;

	private String queUsedFor;

	private String createtime;

	private String lastedittime;

	private String creator;

	private String lasteditor;

	private String delflag;


	private Set examQueCourses = new HashSet(0);

	private Set examQueOptions = new HashSet(0);

	private Set examMatchOptions = new HashSet(0);

	private Set examQueMatchAnswers = new HashSet(0);

	private List optionList = new ArrayList(0);

 	private List matchingOptionList = new ArrayList(0);

 	private List matchingAnswerList = new ArrayList(0);
 	
 	private Set examQuestions = new HashSet(0);
 	
 	private ResChapter chapter;
 	 public ResChapter getChapter() {
     	Set set = this.getExamQueCourses();
     	Iterator iter=set.iterator();
     	if(iter.hasNext()){
     		ExamQueCourse qc = (ExamQueCourse)iter.next();
     		ResChapter tmpchapter = qc.getResChapter();
     		if(tmpchapter!=null){
     			chapter = tmpchapter;
     		}
     	}
     	
 		return chapter;
 	}

 	public void setChapter(ResChapter chapter) {
 		this.chapter = chapter;
 	}
	// Constructors

	/** default constructor */
	public ExamQuestion() {
	}

	public ExamQuestion(String id,String codeNo,String answers,Long defaultgrade){
		this.id = id;
		this.answers = answers;
		SysCode code = new SysCode();
		code.setCodeNo(codeNo);
		this.sysCode = code;
		this.defaultgrade = defaultgrade;
	}
	public ExamQuestion(String id,String codeNo,String content,String answers,Long defaultgrade){
		this.id = id;
		this.answers = answers;
		this.content = content;
		SysCode code = new SysCode();
		code.setCodeNo(codeNo);
		this.sysCode = code;
		this.defaultgrade = defaultgrade;
	}
	/** minimal constructor */
	public ExamQuestion(SysCode sysCode, String shuffleoptions,
			String delflag) {
		this.sysCode = sysCode;
		this.shuffleoptions = shuffleoptions;
		this.delflag = delflag;
	}

	/** full constructor */
	public ExamQuestion(ResResinfo resResinfo,
			SysCode sysCode, String parent, String title,
			String content, String keywords, String shuffleoptions,
			String difficulty, Long defaultgrade, Long usetime, String answers,
			String generalfeedback, String corretfeedback,
			String incorretfeedback, Long exercisetime, Long correttime,
			String lastusetime, String knowledge, String queUsedFor,
			String createtime, String lastedittime, String creator,
			String lasteditor, String delflag,
			Set examQueCourses, Set examQueOptions,
			Set examMatchOptions, Set examQueMatchAnswers) {
		this.resResinfo = resResinfo;
		this.sysCode = sysCode;
		this.parent = parent;
		this.title = title;
		this.content = content;
		this.keywords = keywords;
		this.shuffleoptions = shuffleoptions;
		this.difficulty = difficulty;
		this.defaultgrade = defaultgrade;
		this.usetime = usetime;
		this.answers = answers;
		this.generalfeedback = generalfeedback;
		this.corretfeedback = corretfeedback;
		this.incorretfeedback = incorretfeedback;
		this.exercisetime = exercisetime;
		this.correttime = correttime;
		this.lastusetime = lastusetime;
		this.knowledge = knowledge;
		this.queUsedFor = queUsedFor;
		this.createtime = createtime;
		this.lastedittime = lastedittime;
		this.creator = creator;
		this.lasteditor = lasteditor;
		this.delflag = delflag;
		this.examQueCourses = examQueCourses;
		this.examQueOptions = examQueOptions;
		this.examMatchOptions = examMatchOptions;
		this.examQueMatchAnswers = examQueMatchAnswers;
	}

	 public void addResQueCourses(ExamQueCourse qc){
			this.examQueCourses.add(qc);
			qc.setExamQuestion(this);
		}
	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getShuffleoptions() {
		return this.shuffleoptions;
	}

	public void setShuffleoptions(String shuffleoptions) {
		this.shuffleoptions = shuffleoptions;
	}

	public String getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Long getDefaultgrade() {
		return this.defaultgrade;
	}

	public void setDefaultgrade(Long defaultgrade) {
		this.defaultgrade = defaultgrade;
	}

	public Long getUsetime() {
		return this.usetime;
	}

	public void setUsetime(Long usetime) {
		this.usetime = usetime;
	}

	public String getAnswers() {
		return this.answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public String getGeneralfeedback() {
		return this.generalfeedback;
	}

	public void setGeneralfeedback(String generalfeedback) {
		this.generalfeedback = generalfeedback;
	}

	public String getCorretfeedback() {
		return this.corretfeedback;
	}

	public void setCorretfeedback(String corretfeedback) {
		this.corretfeedback = corretfeedback;
	}

	public String getIncorretfeedback() {
		return this.incorretfeedback;
	}

	public void setIncorretfeedback(String incorretfeedback) {
		this.incorretfeedback = incorretfeedback;
	}

	public Long getExercisetime() {
		return this.exercisetime;
	}

	public void setExercisetime(Long exercisetime) {
		this.exercisetime = exercisetime;
	}

	public Long getCorrettime() {
		return this.correttime;
	}

	public void setCorrettime(Long correttime) {
		this.correttime = correttime;
	}

	public String getLastusetime() {
		return this.lastusetime;
	}

	public void setLastusetime(String lastusetime) {
		this.lastusetime = lastusetime;
	}

	public String getKnowledge() {
		return this.knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public String getQueUsedFor() {
		return this.queUsedFor;
	}

	public void setQueUsedFor(String queUsedFor) {
		this.queUsedFor = queUsedFor;
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

	public String getDelflag() {
		return this.delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public List getMatchingAnswerList() {
		return matchingAnswerList;
	}

	public void setMatchingAnswerList(List matchingAnswerList) {
		this.matchingAnswerList = matchingAnswerList;
	}

	public List getMatchingOptionList() {
		return matchingOptionList;
	}

	public void setMatchingOptionList(List matchingOptionList) {
		this.matchingOptionList = matchingOptionList;
	}

	public List getOptionList() {
		return optionList;
	}

	public void setOptionList(List optionList) {
		this.optionList = optionList;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public String getQtype() {
		if(this.getSysCode()!=null){
			qtype=this.getSysCode().getCodeNo();
		}
		return qtype;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Set getExamMatchOptions() {
		return examMatchOptions;
	}

	public void setExamMatchOptions(Set examMatchOptions) {
		this.examMatchOptions = examMatchOptions;
	}

	public Set getExamQueCourses() {
		return examQueCourses;
	}

	public void setExamQueCourses(Set examQueCourses) {
		this.examQueCourses = examQueCourses;
	}

	public Set getExamQueMatchAnswers() {
		return examQueMatchAnswers;
	}

	public void setExamQueMatchAnswers(Set examQueMatchAnswers) {
		this.examQueMatchAnswers = examQueMatchAnswers;
	}

	public Set getExamQueOptions() {
		List list = new ArrayList(examQueOptions);
		if(this.getShuffleoptions().equals("02")){
			Collections.shuffle(list);
		}
		return new HashSet(list);
	}

	public void setExamQueOptions(Set examQueOptions) {
		this.examQueOptions = examQueOptions;
	}

	public ExamQuestion getExamQuestion() {
		return examQuestion;
	}

	public void setExamQuestion(ExamQuestion examQuestion) {
		this.examQuestion = examQuestion;
	}

	public Set getExamQuestions() {
		return examQuestions;
	}

	public void setExamQuestions(Set examQuestions) {
		this.examQuestions = examQuestions;
	}

}