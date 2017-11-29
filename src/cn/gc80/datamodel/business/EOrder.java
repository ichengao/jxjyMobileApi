package cn.gc80.datamodel.business;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.gc80.base.util.TimeUtils;
import cn.gc80.datamodel.sysbase.SysAdmin;
import cn.gc80.datamodel.sysbase.SysCard;
import cn.gc80.datamodel.sysbase.SysUser;
/**
 * LRN_E_ORDER（订单表）
 */
public class EOrder implements java.io.Serializable {

	private static final long serialVersionUID = 1616970091523681424L;

	private String id;

	private SysUser sysUser;

	private EFreight freight;
	
	private SysCard sysCard;
	
	private EUserAddress address;

	private String ordNo = "";

	private String ordTempNo = "";

	private String ordStatus = "";

	private String ordPaytype = "";

	private String ordPaytime = "";

	private Double ordMoneysum = 0d;

	private Double ordOffers = 0d;

	private Long ordFgtFee = 0l;

	private Long ordDiscount = 0l;

	private Double ordTotalMoney = 0d;

	private String ordName = "";

	private String ordAddress = "";

	private String ordPostcode = "";

	private String ordEmail = "";

	private String ordPhone = "";

	private String ordMobile = "";

	private String ordBill = "";

	private String ordCompany = "";

	private String ordBillType = "";
	
	private String ordBillStyle = "";

	private String creator = "";

	private String lasteditor = "";

	private String createtime = "";

	private String lastedittime = "";

	private String delflag = "";
	
	private String compareDate = "";
	
	private String ordSubmitTime = "";

	private String expressFlag;
	
	private String expressCom;
	
	private String expressNo;
	
	private String expressState;
	
	private List orderDetailList;
	private List courseList;
	private List bookList;
	
	private int itemCount;
	
	//账户金额
	private double ordAccountMoney;
	//积分金额
	private double ordIntegralMoney;
	//积分
	private Long ordIntegral;
	//支付来源
	private String ordPaySource;
	//订单类型，个人|代理
	private String ordType;
	//退款方式
	private String ordRefundType;
	//退款人
	private SysAdmin  OrdRefundPerson;
	//退款时间
	private String oreRefundtime;
	//退款备注
	private String oreRefundDesc;
	//发票索取状态
	private String invoiceClainType;
	//发票ID
	private EOrderInvoice eOrderInvoice;
	// Constructors

	public EOrderInvoice geteOrderInvoice() {
		return eOrderInvoice;
	}
	public void seteOrderInvoice(EOrderInvoice eOrderInvoice) {
		this.eOrderInvoice = eOrderInvoice;
	}
	/** default constructor */
	public EOrder() {
	}
	public EOrder(String id,String ordNo,Double ordMoneysum,String ordPaytime,String ordPaytype){
		this.id = id;
		this.ordNo = ordNo;
		this.ordMoneysum = ordMoneysum;
		this.ordPaytime = ordPaytime;
		this.ordPaytype = ordPaytype;
	}
	public EOrder(String id,String ordBill,Double ordMoneysum,String ordNo){
		this.id = id;
		this.ordBill = ordBill;
		this.ordMoneysum = ordMoneysum;
		this.ordNo = ordNo;
		
	}
	public EOrder(String id,String ordNo,String ordStatus){
		this.id = id;
		this.ordNo = ordNo;
		this.ordStatus = ordStatus;
		
	}
	public EOrder(String id,String ordNo,String ordTempNo,String ordStatus){
		this.id = id;
		this.ordNo = ordNo;
		this.ordTempNo = ordTempNo;
		this.ordStatus = ordStatus;
		
	}
	
	public EOrder(String ordNo, String ordPaytime, Double ordMoneysum,Double ordTotalMoney,String ordType,String ordPaytype,String ordPaySource,
			Double ordAccountMoney,Double ordIntegralMoney,String ordStatus,String invoiceClainType) {
		this.ordNo = ordNo;
		this.ordPaytime = ordPaytime;
		this.ordMoneysum = ordMoneysum;
		this.ordTotalMoney = ordTotalMoney;
		this.ordType = ordType;
		this.ordPaytype=ordPaytype;
		this.ordPaySource=ordPaySource; 
		this.ordAccountMoney=ordAccountMoney;
		this.ordIntegralMoney=ordIntegralMoney;
		this.ordStatus=ordStatus;
		this.invoiceClainType=invoiceClainType;
	}
	public EOrder(String id,String ordPaytime,String lastedittime,String ordStatus,String ordNo,String ordTempNo,String ordPaytype,Double ordMoneysum,String expressFlag){
		this.id = id;
		this.ordPaytime = ordPaytime;
		this.lastedittime = lastedittime;
		this.ordStatus = ordStatus;
		this.ordNo = ordNo;
		this.ordTempNo = ordTempNo;
		this.ordPaytype = ordPaytype;
		this.ordMoneysum = ordMoneysum;
		this.expressFlag = expressFlag;
	}
	public EOrder(String id,String ordPaytime,String lastedittime,String ordStatus,String ordNo,String ordTempNo,String ordPaytype,Double ordMoneysum){
		this.id = id;
		this.ordPaytime = ordPaytime;
		this.lastedittime = lastedittime;
		this.ordStatus = ordStatus;
		this.ordNo = ordNo;
		this.ordTempNo = ordTempNo;
		this.ordPaytype = ordPaytype;
		this.ordMoneysum = ordMoneysum;
	}
	
	public EOrder(String id,String ordNo,String ordTempNo,String ordStatus,String ordPaytype,String userid,double balance,double totalConsumeAmount){
		this.id = id;
		this.ordNo = ordNo;
		this.ordTempNo = ordTempNo;
		this.ordStatus = ordStatus;
		this.ordPaytype = ordPaytype;
		SysUser user = new SysUser();
		user.setId(userid);
		user.setBalance(balance);
		user.setTotalConsumeAmount(totalConsumeAmount);
		this.sysUser = user;
	}
	
	public EOrder(String id,String ordNo,String ordTempNo,String userid,String userName,String realname,Double balance,Double totalConsumeAmount,Double ordMoneysum,String ordPaytype,String ordStatus,String createtime,String ordPaytime){
		this.id = id;
		this.ordNo = ordNo;
		this.ordTempNo = ordTempNo;
		this.ordMoneysum = ordMoneysum;
		this.ordPaytype = ordPaytype;
		this.ordStatus = ordStatus;
		this.ordPaytime = ordPaytime;
		this.createtime = createtime;
		SysUser user = new SysUser();
		user.setId(userid);
		user.setUserName(userName);
		user.setRealname(realname);
		user.setBalance(balance);
		user.setTotalConsumeAmount(totalConsumeAmount);
		this.sysUser = user;
	}
	
	public EOrder(String id,String ordNo,String ordTempNo,String userid,String userName,String realname,Double ordMoneysum,String ordPaytype,String ordStatus,String createtime,String ordPaytime){
		this.id = id;
		this.ordNo = ordNo;
		this.ordTempNo = ordTempNo;
		this.ordMoneysum = ordMoneysum;
		this.ordPaytype = ordPaytype;
		this.ordStatus = ordStatus;
		this.ordPaytime = ordPaytime;
		this.createtime = createtime;
		SysUser user = new SysUser();
		user.setId(userid);
		user.setUserName(userName);
		user.setRealname(realname);
		this.sysUser = user;
	}
	
	/** minimal constructor */
	public EOrder(String ordNo, String ordTempNo, String ordStatus,
			Double ordMoneysum, Double ordOffers, String delflag) {
		this.ordNo = ordNo;
		this.ordTempNo = ordTempNo;
		this.ordStatus = ordStatus;
		this.ordMoneysum = ordMoneysum;
		this.ordOffers = ordOffers;
		this.delflag = delflag;
	}
	public EOrder(String ordStatus, String ordPaytime, Double ordMoneysum,String ordPaytype, String id, String ordNo,String createtime,String ordSubmitTime,String expressFlag,String expressState) {
		this.id=id;
		this.ordStatus=ordStatus;
		this.ordPaytime=ordPaytime;
		this.ordMoneysum=ordMoneysum;
		this.ordPaytype=ordPaytype;
		this.ordNo=ordNo;
		this.createtime=createtime;
		this.ordSubmitTime=ordSubmitTime;
		this.expressFlag = expressFlag;
		this.expressState = expressState;
		
	}
	public EOrder(String ordStatus, String ordPaytime, Double ordMoneysum,String ordPaytype, String id, String ordNo,String createtime,String ordSubmitTime) {
		this.id=id;
		this.ordStatus=ordStatus;
		this.ordPaytime=ordPaytime;
		this.ordMoneysum=ordMoneysum;
		this.ordPaytype=ordPaytype;
		this.ordNo=ordNo;
		this.createtime=createtime;
		this.ordSubmitTime=ordSubmitTime;
	}

	/** full constructor */
	public EOrder(SysUser sysUser, EFreight freight,
			String ordNo, String ordTempNo, String ordStatus,
			String ordPaytype, String ordPaytime, Double ordMoneysum,
			Double ordOffers, Long ordFgtFee, Long ordDiscount,
			Double ordTotalMoney, String ordName, String ordAddress,
			String ordPostcode, String ordEmail, String ordPhone,
			String ordMobile, String ordBill, String ordCompany,
			String ordBillType, String creator, String lasteditor,
			String createtime, String lastedittime, String delflag) {
		this.sysUser = sysUser;
		this.freight = freight;
		this.ordNo = ordNo;
		this.ordTempNo = ordTempNo;
		this.ordStatus = ordStatus;
		this.ordPaytype = ordPaytype;
		this.ordPaytime = ordPaytime;
		this.ordMoneysum = ordMoneysum;
		this.ordOffers = ordOffers;
		this.ordFgtFee = ordFgtFee;
		this.ordDiscount = ordDiscount;
		this.ordTotalMoney = ordTotalMoney;
		this.ordName = ordName;
		this.ordAddress = ordAddress;
		this.ordPostcode = ordPostcode;
		this.ordEmail = ordEmail;
		this.ordPhone = ordPhone;
		this.ordMobile = ordMobile;
		this.ordBill = ordBill;
		this.ordCompany = ordCompany;
		this.ordBillType = ordBillType;
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

	public String getOrdNo() {
		return this.ordNo;
	}

	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}

	public String getOrdTempNo() {
		return this.ordTempNo;
	}

	public void setOrdTempNo(String ordTempNo) {
		this.ordTempNo = ordTempNo;
	}

	public String getOrdStatus() {
		return this.ordStatus;
	}

	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}

	public String getOrdPaytype() {
		return this.ordPaytype;
	}

	public void setOrdPaytype(String ordPaytype) {
		this.ordPaytype = ordPaytype;
	}

	public String getOrdPaytime() {
		return this.ordPaytime;
	}

	public void setOrdPaytime(String ordPaytime) {
		this.ordPaytime = ordPaytime;
	}

	public Double getOrdMoneysum() {
		return this.ordMoneysum;
	}

	public void setOrdMoneysum(Double ordMoneysum) {
		this.ordMoneysum = ordMoneysum;
	}

	public Double getOrdOffers() {
		return this.ordOffers;
	}

	public void setOrdOffers(Double ordOffers) {
		this.ordOffers = ordOffers;
	}

	public Long getOrdFgtFee() {
		return this.ordFgtFee;
	}

	public void setOrdFgtFee(Long ordFgtFee) {
		this.ordFgtFee = ordFgtFee;
	}

	public Long getOrdDiscount() {
		return this.ordDiscount;
	}

	public void setOrdDiscount(Long ordDiscount) {
		this.ordDiscount = ordDiscount;
	}

	public Double getOrdTotalMoney() {
		return this.ordTotalMoney;
	}

	public void setOrdTotalMoney(Double ordTotalMoney) {
		this.ordTotalMoney = ordTotalMoney;
	}

	public String getOrdName() {
		return this.ordName;
	}

	public void setOrdName(String ordName) {
		this.ordName = ordName;
	}

	public String getOrdAddress() {
		return this.ordAddress;
	}

	public void setOrdAddress(String ordAddress) {
		this.ordAddress = ordAddress;
	}

	public String getOrdPostcode() {
		return this.ordPostcode;
	}

	public void setOrdPostcode(String ordPostcode) {
		this.ordPostcode = ordPostcode;
	}

	public String getOrdEmail() {
		return this.ordEmail;
	}

	public void setOrdEmail(String ordEmail) {
		this.ordEmail = ordEmail;
	}

	public String getOrdPhone() {
		return this.ordPhone;
	}

	public void setOrdPhone(String ordPhone) {
		this.ordPhone = ordPhone;
	}

	public String getOrdMobile() {
		return this.ordMobile;
	}

	public void setOrdMobile(String ordMobile) {
		this.ordMobile = ordMobile;
	}

	public String getOrdBill() {
		return this.ordBill;
	}

	public void setOrdBill(String ordBill) {
		this.ordBill = ordBill;
	}

	public String getOrdCompany() {
		return this.ordCompany;
	}

	public void setOrdCompany(String ordCompany) {
		this.ordCompany = ordCompany;
	}

	public String getOrdBillType() {
		return this.ordBillType;
	}

	public void setOrdBillType(String ordBillType) {
		this.ordBillType = ordBillType;
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

	public SysCard getSysCard() {
		return sysCard;
	}

	public void setSysCard(SysCard sysCard) {
		this.sysCard = sysCard;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public List getBookList() {
		return bookList;
	}

	public void setBookList(List bookList) {
		this.bookList = bookList;
	}

	public List getCourseList() {
		return courseList;
	}

	public void setCourseList(List courseList) {
		this.courseList = courseList;
	}

	public long getCompareDate() throws ParseException {
		try {
			if (ordSubmitTime == null || ordSubmitTime.equalsIgnoreCase("NULL")||ordSubmitTime.equals(""))
				return -1;
			Date date1 = TimeUtils.getDateFromStandardTime(ordSubmitTime);
			long tmp = TimeUtils.getBetweenMinutes(date1);
			if (tmp == 0)
				return -1;
			return tmp;
		} catch (ParseException pe) {
			pe.printStackTrace();
			throw pe;
		}
		// return this.compareDate;
	}

	public void setCompareDate(String compareDate) {
		this.compareDate = compareDate;
	}

	public String getOrdBillStyle() {
		return ordBillStyle;
	}

	public void setOrdBillStyle(String ordBillStyle) {
		this.ordBillStyle = ordBillStyle;
	}

	public String getOrdSubmitTime() {
		return ordSubmitTime;
	}

	public void setOrdSubmitTime(String ordSubmitTime) {
		this.ordSubmitTime = ordSubmitTime;
	}

	public EFreight getFreight() {
		return freight;
	}

	public void setFreight(EFreight freight) {
		this.freight = freight;
	}

	public List getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getExpressFlag() {
		return expressFlag;
	}

	public void setExpressFlag(String expressFlag) {
		this.expressFlag = expressFlag;
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
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getExpressState() {
		return expressState;
	}
	public void setExpressState(String expressState) {
		this.expressState = expressState;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int i) {
		this.itemCount = i;
	}
	public double getOrdAccountMoney() {
		return ordAccountMoney;
	}
	public void setOrdAccountMoney(double ordAccountMoney) {
		this.ordAccountMoney = ordAccountMoney;
	}
	public double getOrdIntegralMoney() {
		return ordIntegralMoney;
	}
	public void setOrdIntegralMoney(double ordIntegralMoney) {
		this.ordIntegralMoney = ordIntegralMoney;
	}
	public Long getOrdIntegral() {
		return ordIntegral;
	}
	public void setOrdIntegral(Long ordIntegral) {
		this.ordIntegral = ordIntegral;
	}
	public String getOrdPaySource() {
		return ordPaySource;
	}
	public void setOrdPaySource(String ordPaySource) {
		this.ordPaySource = ordPaySource;
	}
	public String getOrdType() {
		return ordType;
	}
	public void setOrdType(String ordType) {
		this.ordType = ordType;
	}
	public String getOrdRefundType() {
		return ordRefundType;
	}
	public void setOrdRefundType(String ordRefundType) {
		this.ordRefundType = ordRefundType;
	}
	public SysAdmin getOrdRefundPerson() {
		return OrdRefundPerson;
	}
	public void setOrdRefundPerson(SysAdmin ordRefundPerson) {
		OrdRefundPerson = ordRefundPerson;
	}
	public String getOreRefundtime() {
		return oreRefundtime;
	}
	public void setOreRefundtime(String oreRefundtime) {
		this.oreRefundtime = oreRefundtime;
	}
	public String getOreRefundDesc() {
		return oreRefundDesc;
	}
	public void setOreRefundDesc(String oreRefundDesc) {
		this.oreRefundDesc = oreRefundDesc;
	}
	public String getInvoiceClainType() {
		return invoiceClainType;
	}
	public void setInvoiceClainType(String invoiceClainType) {
		this.invoiceClainType = invoiceClainType;
	}
	
	
}