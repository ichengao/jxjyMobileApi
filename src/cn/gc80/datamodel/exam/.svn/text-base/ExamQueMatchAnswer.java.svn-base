package cn.gc80.datamodel.exam;

import java.util.HashSet;
import java.util.Set;

import cn.gc80.datamodel.res.ResResinfo;

public class ExamQueMatchAnswer implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6218318046419323529L;

	private String id;

	private ResResinfo resResinfo;

	private ExamQuestion examQuestion;

	private String answerContent;

	private Set examMatchOptions = new HashSet(0);

	// Constructors

	/** default constructor */
	public ExamQueMatchAnswer() {
	}

	/** minimal constructor */
	public ExamQueMatchAnswer(ExamQuestion examQuestion) {
		this.examQuestion = examQuestion;
	}

	/** full constructor */
	public ExamQueMatchAnswer(ResResinfo resResinfo,
			ExamQuestion examQuestion, String answerContent,
			Set examMatchOptions) {
		this.resResinfo = resResinfo;
		this.examQuestion = examQuestion;
		this.answerContent = answerContent;
		this.examMatchOptions = examMatchOptions;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswerContent() {
		return this.answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public ResResinfo getResResinfo() {
		return resResinfo;
	}

	public void setResResinfo(ResResinfo resResinfo) {
		this.resResinfo = resResinfo;
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

	public ExamQuestion getExamQuestion() {
		return examQuestion;
	}

	public void setExamQuestion(ExamQuestion examQuestion) {
		this.examQuestion = examQuestion;
	}

}