package cn.gc80.datamodel.business;

import java.util.List;

import cn.gc80.datamodel.sysbase.SysUser;
/**
 * LRN_ORDER_INVOICE（发票表）
 */
public class EOrderInvoice {
	private String id;
	private SysUser sysUser;
	private EUserAddress address;
	private String invoiceProject;
	private String invoiceTitle;
	private String invoiceTime;//开票时间
	private Double invoicePrice;
	private Long billAmount;
	private String invoiceState;
	private String invoiceType;
	private String invoiceOrdno;
	private String invoiceNo;
	private String expressNo;
	private String expressCom;
	
	private String invoicePost;
	private String ordNo;
	private Double ordMoneysum = 0d;
	private String ordStatus;
	private String ordPaytype;
	private String ordPaytime;
	private List orderList;
	private String expressContent;
	//发票批次
	private String invoiceBatch;
	//发票抬头
	private String invoiceHeader;
	//代领状态
	private String leadState;
	//代领人
	private SysUser sysUserLead;
	//运单id
	private EOrderExpress eOrderExpress;
	//创建时间
	private String createTime;
	//删除标志
	private String delflag;
	//打印时间
	private String printTime;
	//领取方式
	private String receive;
	//纳税人识别号或统一社会信用代码
	private String identifyNum;
	
	public String getExpressContent() {
		return expressContent;
	}
	public void setExpressContent(String expressContent) {
		this.expressContent = expressContent;
	}
	public String getInvoiceOrdno() {
		return invoiceOrdno;
	}
	public void setInvoiceOrdno(String invoiceOrdno) {
		this.invoiceOrdno = invoiceOrdno;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public EOrderInvoice(){}
	public String getInvoiceState() {
		return invoiceState;
	}
	public EOrderInvoice(String id,Double invoicePrice, String invoiceOrdno,
			String invoiceBatch, String invoiceHeader,String cardNo, 
			String addressDesc,String expressNo,String lodisticeNo,
			String expressPayType,String expressPaySource,String expressPayTime) {
		this.id=id;
		this.invoicePrice = invoicePrice;
		this.invoiceOrdno = invoiceOrdno;
		this.invoiceBatch = invoiceBatch;
		this.invoiceHeader = invoiceHeader;
		SysUser userLead=new SysUser();
		userLead.setCardno(cardNo);
		this.sysUserLead = userLead;
		EOrderExpress express=new EOrderExpress();
		express.setAddressDesc(addressDesc);
		express.setExpressNo(expressNo);
		express.setLodisticeNo(lodisticeNo);
		express.setExpressPayType(expressPayType);
		express.setExpressPaySource(expressPaySource);
		express.setExpressPayTime(expressPayTime);
		this.eOrderExpress = express;
	}
	
	public EOrderInvoice(String id,Double invoicePrice, String invoiceOrdno,
			String invoiceBatch, String invoiceHeader,String cardNo) {
		this.id=id;
		this.invoicePrice = invoicePrice;
		this.invoiceOrdno = invoiceOrdno;
		this.invoiceBatch = invoiceBatch;
		this.invoiceHeader = invoiceHeader;
		
	}
	
	public EOrderInvoice(String invoiceProject,String invoiceTime,Double invoicePrice,String invoiceState){
		this.invoiceProject = invoiceProject;
		this.invoiceTime = invoiceTime;
		this.invoicePrice = invoicePrice;
		this.invoiceState = invoiceState;
	}
	public EOrderInvoice(String id,String invoiceProject,String invoiceTitle,String invoiceTime,Double invoicePrice,Long billAmount,String invoiceState,String invoiceType,String invoiceOrdno,String invoiceNo,String expressNo){
		this.id = id;
		this.invoiceProject = invoiceProject;
		this.invoiceTitle = invoiceTitle;
		this.invoiceTime = invoiceTime;
		this.invoicePrice = invoicePrice;
		this.billAmount = billAmount;
		this.invoiceState = invoiceState;
		this.invoiceType = invoiceType;
		this.invoiceOrdno = invoiceOrdno;
		this.invoiceNo = invoiceNo;
		this.expressNo = expressNo;
	}
	
	public EOrderInvoice(String id, SysUser sysUser, EUserAddress address,
			String invoiceProject, String invoiceTitle, String invoiceTime,
			Double invoicePrice, Long billAmount, String invoiceState,
			String invoiceType, String invoiceOrdno, String invoiceNo,
			String expressNo, String expressCom, String invoicePost,
			String ordNo, Double ordMoneysum, String ordStatus,
			String ordPaytype, String ordPaytime, List orderList,
			String expressContent, String invoiceBatch, String invoiceHeader,
			String leadState, SysUser sysUserLead, EOrderExpress eOrderExpress,
			String createTime, String deflag, String printTime, String receive) {
		super();
		this.id = id;
		this.sysUser = sysUser;
		this.address = address;
		this.invoiceProject = invoiceProject;
		this.invoiceTitle = invoiceTitle;
		this.invoiceTime = invoiceTime;
		this.invoicePrice = invoicePrice;
		this.billAmount = billAmount;
		this.invoiceState = invoiceState;
		this.invoiceType = invoiceType;
		this.invoiceOrdno = invoiceOrdno;
		this.invoiceNo = invoiceNo;
		this.expressNo = expressNo;
		this.expressCom = expressCom;
		this.invoicePost = invoicePost;
		this.ordNo = ordNo;
		this.ordMoneysum = ordMoneysum;
		this.ordStatus = ordStatus;
		this.ordPaytype = ordPaytype;
		this.ordPaytime = ordPaytime;
		this.orderList = orderList;
		this.expressContent = expressContent;
		this.invoiceBatch = invoiceBatch;
		this.invoiceHeader = invoiceHeader;
		this.leadState = leadState;
		this.sysUserLead = sysUserLead;
		this.eOrderExpress = eOrderExpress;
		this.createTime = createTime;
		this.delflag = delflag;
		this.printTime = printTime;
		this.receive = receive;
	}
	public void setInvoiceState(String invoiceState) {
		this.invoiceState = invoiceState;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public String getInvoiceProject() {
		return invoiceProject;
	}
	public void setInvoiceProject(String invoiceProject) {
		this.invoiceProject = invoiceProject;
	}
	public String getInvoiceTime() {
		return invoiceTime;
	}
	public void setInvoiceTime(String invoiceTime) {
		this.invoiceTime = invoiceTime;
	}
	public Double getInvoicePrice() {
		return invoicePrice;
	}
	public void setInvoicePrice(Double invoicePrice) {
		this.invoicePrice = invoicePrice;
	}
	public Long getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Long billAmount) {
		this.billAmount = billAmount;
	}
	public List getOrderList() {
		return orderList;
	}
	public void setOrderList(List orderList) {
		this.orderList = orderList;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public EUserAddress getAddress() {
		return address;
	}
	public void setAddress(EUserAddress address) {
		this.address = address;
	}
	public String getExpressCom() {
		return expressCom;
	}
	public void setExpressCom(String expressCom) {
		this.expressCom = expressCom;
	}
	public String getInvoicePost() {
		return invoicePost;
	}
	public void setInvoicePost(String invoicePost) {
		this.invoicePost = invoicePost;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public Double getOrdMoneysum() {
		return ordMoneysum;
	}
	public void setOrdMoneysum(Double ordMoneysum) {
		this.ordMoneysum = ordMoneysum;
	}
	public String getOrdStatus() {
		return ordStatus;
	}
	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}
	public String getOrdPaytype() {
		return ordPaytype;
	}
	public void setOrdPaytype(String ordPaytype) {
		this.ordPaytype = ordPaytype;
	}
	public String getOrdPaytime() {
		return ordPaytime;
	}
	public void setOrdPaytime(String ordPaytime) {
		this.ordPaytime = ordPaytime;
	}
	public String getInvoiceBatch() {
		return invoiceBatch;
	}
	public void setInvoiceBatch(String invoiceBatch) {
		this.invoiceBatch = invoiceBatch;
	}
	public String getInvoiceHeader() {
		return invoiceHeader;
	}
	public void setInvoiceHeader(String invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}
	public String getLeadState() {
		return leadState;
	}
	public void setLeadState(String leadState) {
		this.leadState = leadState;
	}
	public SysUser getSysUserLead() {
		return sysUserLead;
	}
	public void setSysUserLead(SysUser sysUserLead) {
		this.sysUserLead = sysUserLead;
	}
	public EOrderExpress geteOrderExpress() {
		return eOrderExpress;
	}
	public void seteOrderExpress(EOrderExpress eOrderExpress) {
		this.eOrderExpress = eOrderExpress;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String deflag) {
		this.delflag = deflag;
	}
	public String getPrintTime() {
		return printTime;
	}
	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public String getIdentifyNum() {
		return identifyNum;
	}
	public void setIdentifyNum(String identifyNum) {
		this.identifyNum = identifyNum;
	}
	
}
