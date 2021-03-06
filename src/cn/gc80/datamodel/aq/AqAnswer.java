package cn.gc80.datamodel.aq;

import cn.gc80.datamodel.sysbase.SysAdmin;

/**
 * YfssAqAnswer generated by MyEclipse Persistence Tools
 */

public class AqAnswer implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4735051787281544546L;

	private String id;

	private SysAdmin sysAdmin;

	private AqQuestion aqQuestion;

	private String content;

	private String createtime;

	private String lastedittime;

	private String creator;

	private String lasteditor;

	private String delflag;

	// Constructors

	/** default constructor */
	public AqAnswer() {
	}

	/** minimal constructor */
	public AqAnswer(SysAdmin sysAdmin, AqQuestion aqQuestion,
			String content, String delflag) {
		this.sysAdmin = sysAdmin;
		this.aqQuestion = aqQuestion;
		this.content = content;
		this.delflag = delflag;
	}

	/** full constructor */
	public AqAnswer(SysAdmin sysAdmin, AqQuestion aqQuestion,
			String content, String createtime, String lastedittime,
			String creator, String lasteditor, String delflag) {
		this.sysAdmin = sysAdmin;
		this.aqQuestion = aqQuestion;
		this.content = content;
		this.createtime = createtime;
		this.lastedittime = lastedittime;
		this.creator = creator;
		this.lasteditor = lasteditor;
		this.delflag = delflag;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public AqQuestion getAqQuestion() {
		return aqQuestion;
	}

	public void setAqQuestion(AqQuestion aqQuestion) {
		this.aqQuestion = aqQuestion;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public SysAdmin getSysAdmin() {
		return sysAdmin;
	}

	public void setSysAdmin(SysAdmin sysAdmin) {
		this.sysAdmin = sysAdmin;
	}

}