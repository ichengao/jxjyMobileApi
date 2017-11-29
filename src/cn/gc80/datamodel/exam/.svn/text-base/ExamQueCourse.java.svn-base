package cn.gc80.datamodel.exam;

import cn.gc80.datamodel.res.ResChapter;
import cn.gc80.datamodel.res.ResCourse;
import cn.gc80.datamodel.res.ResCourseware;
import cn.gc80.datamodel.training.TrainProject;

public class ExamQueCourse implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2985314334275983552L;

	private String id;

	private ResCourse resCourse;

	private ResChapter resChapter;

	private ExamQuestion examQuestion;

	private ResCourseware resCourseware;
	
	private TrainProject trainProject;

	// Constructors

	/** default constructor */
	public ExamQueCourse() {
	}

	/** minimal constructor */
	public ExamQueCourse(ExamQuestion examQuestion) {
		this.examQuestion = examQuestion;
	}

	/** full constructor */
	public ExamQueCourse(ResCourse resCourse,ResChapter resChapter,
			ExamQuestion examQuestion, ResCourseware resCourseware) {
		this.resCourse = resCourse;
		this.resChapter = resChapter;
		this.examQuestion = examQuestion;
		this.resCourseware = resCourseware;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ResChapter getResChapter() {
		return resChapter;
	}

	public void setResChapter(ResChapter resChapter) {
		this.resChapter = resChapter;
	}

	public ResCourse getResCourse() {
		return resCourse;
	}

	public void setResCourse(ResCourse resCourse) {
		this.resCourse = resCourse;
	}

	public ResCourseware getResCourseware() {
		return resCourseware;
	}

	public void setResCourseware(ResCourseware resCourseware) {
		this.resCourseware = resCourseware;
	}

	public ExamQuestion getExamQuestion() {
		return examQuestion;
	}

	public void setExamQuestion(ExamQuestion examQuestion) {
		this.examQuestion = examQuestion;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public TrainProject getTrainProject() {
		return trainProject;
	}

	public void setTrainProject(TrainProject trainProject) {
		this.trainProject = trainProject;
	}
}