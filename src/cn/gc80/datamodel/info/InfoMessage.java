package cn.gc80.datamodel.info;

import cn.gc80.datamodel.sysbase.SysCode;
import cn.gc80.datamodel.sysbase.SysProvince;
/**
 * LRN_INFO_MESSAGE信息表
 */

public class InfoMessage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1885722867528067861L;

	private String id;

	private SysCode sysCode;

	private String title;

	private String summary;
	
	private String infoContent;

	private String keywords;

	private String author;

	private String publishfrom;//来源

	private String publishtime;

	private Long ordernum;
    //信息公告类型
	private String infotype;

	private String pop;

	private String istop;
	
	private String isred;
	

	private String starttime;

	private String endtime;

	private Long visitcount;

	private String visible;
	
	private String infoImage;

	private String creator;

	private String createtime;

	private String lasteditor;

	private String lastedittime;

	private String delflag;
	
	private String isshow;
	
	private String iscomment;
	
	private Long name;
	
	private SysProvince province;

	// Constructors

	/** default constructor */
	public InfoMessage() {
	}
	
	public InfoMessage(String id,String title,String codeid,String codeName,String author,String publishtime,Long ordernum,String visible,String publishfrom){
		this.id = id;
		this.title = title;
		SysCode code = new SysCode();
		code.setId(codeid);
		code.setCodeName(codeName);
		this.sysCode = code;
		this.author = author;
		this.publishtime = publishtime;
		this.ordernum = ordernum;
		this.visible = visible;
		this.publishfrom=publishfrom;
	}
	
	public InfoMessage(String Id,String title,String infoContent,String codeNo){
		this.id=Id;
		this.title=title;
		this.infoContent = infoContent;
		SysCode code=new SysCode();
		code.setCodeNo(codeNo);
		this.sysCode=code;
	}
	public InfoMessage(String Id,String title,String infoContent,String codeNo,Long name){
		this.id=Id;
		this.title=title;
		this.infoContent = infoContent;
		this.name = name;
		SysCode code=new SysCode();
		code.setCodeNo(codeNo);
		this.sysCode=code;
	}
	
	public InfoMessage(String Id,String title,String isred,String infoContent,String codeNo){
		this.id=Id;
		this.title=title;
		this.isred = isred;
		this.infoContent = infoContent;
		SysCode code=new SysCode();
		code.setCodeNo(codeNo);
		this.sysCode=code;
	}
	
	public InfoMessage(String Id,String title,String isred,String infoContent,String codeNo,String publishtime){
		this.id=Id;
		this.title=title;
		this.isred = isred;
		this.infoContent = infoContent;
		this.publishtime = publishtime;
		SysCode code=new SysCode();
		code.setCodeNo(codeNo);
		this.sysCode=code;
	}
	public InfoMessage(String Id,String title,String isred,String infoContent,String codeNo,String publishtime,Long name){
		this.id=Id;
		this.title=title;
		this.isred = isred;
		this.infoContent = infoContent;
		this.publishtime = publishtime;
		this.name = name;
		SysCode code=new SysCode();
		code.setCodeNo(codeNo);
		this.sysCode=code;
	}
	public InfoMessage(String Id,String title,String summary,String isred,String infoContent,String codeNo,String publishtime,Long name){
		this.id=Id;
		this.title=title;
		this.summary = summary;
		this.isred = isred;
		this.infoContent = infoContent;
		this.publishtime = publishtime;
		this.name = name;
		SysCode code=new SysCode();
		code.setCodeNo(codeNo);
		this.sysCode=code;
	}
	public InfoMessage(String Id,String title,String summary,String publishtime,String infoContent,String isred,String codeNo){
		this.id=Id;
		this.title=title;
		this.summary = summary;
		this.isred = isred;
		this.infoContent = infoContent;
		this.publishtime = publishtime;
		SysCode code=new SysCode();
		code.setCodeNo(codeNo);
		this.sysCode=code;
	}
	/** full constructor */
	public InfoMessage(SysCode sysCode, String title,
			String infoContent, String keywords, String author,
			String publishfrom, String publishtime, Long ordernum,
			String infotype, String pop, String istop, String starttime,
			String endtime, Long visitcount, String visible, String creator,
			String createtime, String lasteditor, String lastedittime,
			String delflag) {
		this.sysCode = sysCode;
		this.title = title;
		this.infoContent = infoContent;
		this.keywords = keywords;
		this.author = author;
		this.publishfrom = publishfrom;
		this.publishtime = publishtime;
		this.ordernum = ordernum;
		this.infotype = infotype;
		this.pop = pop;
		this.istop = istop;
		this.starttime = starttime;
		this.endtime = endtime;
		this.visitcount = visitcount;
		this.visible = visible;
		this.creator = creator;
		this.createtime = createtime;
		this.lasteditor = lasteditor;
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

	public SysCode getSysCode() {
		return this.sysCode;
	}

	public void setSysCode(SysCode sysCode) {
		this.sysCode = sysCode;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfoContent() {
		return this.infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishfrom() {
		return this.publishfrom;
	}

	public void setPublishfrom(String publishfrom) {
		this.publishfrom = publishfrom;
	}

	public String getPublishtime() {
		return this.publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

	public Long getOrdernum() {
		return this.ordernum;
	}

	public void setOrdernum(Long ordernum) {
		this.ordernum = ordernum;
	}

	public String getInfotype() {
		return this.infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public String getPop() {
		return this.pop;
	}

	public void setPop(String pop) {
		this.pop = pop;
	}

	public String getIstop() {
		return this.istop;
	}

	public void setIstop(String istop) {
		this.istop = istop;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Long getVisitcount() {
		return this.visitcount;
	}

	public void setVisitcount(Long visitcount) {
		this.visitcount = visitcount;
	}

	public String getVisible() {
		return this.visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getLasteditor() {
		return this.lasteditor;
	}

	public void setLasteditor(String lasteditor) {
		this.lasteditor = lasteditor;
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

	public String getInfoImage() {
		return infoImage;
	}

	public void setInfoImage(String infoImage) {
		this.infoImage = infoImage;
	}

	public String getIsred() {
		return isred;
	}

	public void setIsred(String isred) {
		this.isred = isred;
	}

	public Long getName() {
		return name;
	}

	public void setName(Long name) {
		this.name = name;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public String getIscomment() {
		return iscomment;
	}

	public void setIscomment(String iscomment) {
		this.iscomment = iscomment;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public SysProvince getProvince() {
		return province;
	}

	public void setProvince(SysProvince province) {
		this.province = province;
	}
	
	
}