package cn.gc80.datamodel.exam;

import cn.gc80.datamodel.res.ResResinfo;

public class ExamQueOption implements java.io.Serializable {

	private static final long serialVersionUID = 393218190519928794L;

	private String id;

	private ResResinfo resResinfo;

	private ExamQuestion examQuestion;

	private String opContent;
	
	private boolean isAnswer = false;

	// Constructors

	public boolean isAnswer() {
		return isAnswer;
	}

	public void setAnswer(boolean isAnswer) {
		this.isAnswer = isAnswer;
	}

	/** default constructor */
	public ExamQueOption() {
	}

	public ExamQueOption(String id,String opContent) {
		this.id = id;
		this.opContent = opContent;
	}
	/** minimal constructor */
	public ExamQueOption(ExamQuestion examQuestion, String opContent) {
		this.examQuestion = examQuestion;
		this.opContent = opContent;
	}

	/** full constructor */
	public ExamQueOption(ResResinfo resResinfo,
			ExamQuestion examQuestion, String opContent) {
		this.resResinfo = resResinfo;
		this.examQuestion = examQuestion;
		this.opContent = opContent;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ExamQuestion getExamQuestion() {
		return examQuestion;
	}

	public void setExamQuestion(ExamQuestion examQuestion) {
		this.examQuestion = examQuestion;
	}

	public ResResinfo getResResinfo() {
		return resResinfo;
	}

	public void setResResinfo(ResResinfo resResinfo) {
		this.resResinfo = resResinfo;
	}

	public String getOpContent() {
		return this.opContent;
	}

	public void setOpContent(String opContent) {
		this.opContent = opContent;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}