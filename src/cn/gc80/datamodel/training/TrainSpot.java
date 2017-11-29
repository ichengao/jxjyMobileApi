package cn.gc80.datamodel.training;


/**
 * LrnTrainSpot entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TrainSpot implements java.io.Serializable {

	// Fields

	private String id;
	private String spotName;
	private String spotAddr;
	private String spotDesc;
	private String spotContact;
	private String spotLinkman;
	private String spotEmail;
	private String creator;
	private String lasteditor;
	private String createtime;
	private String lastedittime;
	private String area;
	private String delflag;
	// Constructors

	/** default constructor */
	public TrainSpot() {
	}

	/** minimal constructor */
	public TrainSpot(String delflag) {
		this.delflag = delflag;
	}

	/** full constructor */
	public TrainSpot(String spotName, String spotAddr, String spotDesc,
			String spotContact, String spotLinkman, String spotEmail,
			String creator, String lasteditor, String createtime,
			String lastedittime, String delflag) {
		this.spotName = spotName;
		this.spotAddr = spotAddr;
		this.spotDesc = spotDesc;
		this.spotContact = spotContact;
		this.spotLinkman = spotLinkman;
		this.spotEmail = spotEmail;
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

	public String getSpotName() {
		return this.spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public String getSpotAddr() {
		return this.spotAddr;
	}

	public void setSpotAddr(String spotAddr) {
		this.spotAddr = spotAddr;
	}

	public String getSpotDesc() {
		return this.spotDesc;
	}

	public void setSpotDesc(String spotDesc) {
		this.spotDesc = spotDesc;
	}

	public String getSpotContact() {
		return this.spotContact;
	}

	public void setSpotContact(String spotContact) {
		this.spotContact = spotContact;
	}

	public String getSpotLinkman() {
		return this.spotLinkman;
	}

	public void setSpotLinkman(String spotLinkman) {
		this.spotLinkman = spotLinkman;
	}

	public String getSpotEmail() {
		return this.spotEmail;
	}

	public void setSpotEmail(String spotEmail) {
		this.spotEmail = spotEmail;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}