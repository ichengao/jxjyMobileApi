package cn.gc80.datamodel.business;

import cn.gc80.datamodel.sysbase.SysUser;

/**
 * LRN_ORDER_EXPRESS（运单表）
 */
public class EOrderExpress {
	private String id;
	private SysUser sysUser;
	//收件地址ID
	private String addressId;
	//运单类型
	private String expressType;//01发票02证书
	private String expressCom; //物流公司，拼音全写
	private String expressSerial;
	//运单号
	private String expressNo;
	//缴费单号
	private String expressPayNo;
	//运单总金额
	private Double expressTotalMoney;
	//运单支付金额
	private Double expressPrice;
	//支付方式
	private String expressPayType;
	//账户金额
	private Double expressAccountMoney;
	//积分金额
	private Double expressIntegralMoney;
	//积分
	private Long expressIntegral;
	//支付来源
	private String expressPaySource;
	//支付状态
	private String expressState;
	//物流号
	private String lodisticeNo;
	//物流状态
	private String lodisticeState;
	//创建时间
	private String createTime;
	//删除标志
	private String delflag;
	//支付时间
	private String expressPayTime;
	//发货时间
	private String lodisticeTime;
	//收件人
	private String addressName;
	//收件人号码
	private String addressPhone;
	//收件人区域
	private String addressArea;
	//收件人地址详细信息
	private String addressDesc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExpressCom() {
		return expressCom;
	}
	public void setExpressCom(String expressCom) {
		this.expressCom = expressCom;
	}
	public String getExpressSerial() {
		return expressSerial;
	}
	public void setExpressSerial(String expressSerial) {
		this.expressSerial = expressSerial;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser= sysUser;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getExpressType() {
		return expressType;
	}
	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getExpressPayNo() {
		return expressPayNo;
	}
	public void setExpressPayNo(String expressPayNo) {
		this.expressPayNo = expressPayNo;
	}
	public Double getExpressTotalMoney() {
		return expressTotalMoney;
	}
	public void setExpressTotalMoney(Double expressTotalMoney) {
		this.expressTotalMoney = expressTotalMoney;
	}
	public Double getExpressPrice() {
		return expressPrice;
	}
	public void setExpressPrice(Double expressPrice) {
		this.expressPrice = expressPrice;
	}
	public String getExpressPayType() {
		return expressPayType;
	}
	public void setExpressPayType(String expressPayType) {
		this.expressPayType = expressPayType;
	}
	public Double getExpressAccountMoney() {
		return expressAccountMoney;
	}
	public void setExpressAccountMoney(Double expressAccountMoney) {
		this.expressAccountMoney = expressAccountMoney;
	}
	public Double getExpressIntegralMoney() {
		return expressIntegralMoney;
	}
	public void setExpressIntegralMoney(Double expressIntegralMoney) {
		this.expressIntegralMoney = expressIntegralMoney;
	}
	public Long getExpressIntegral() {
		return expressIntegral;
	}
	public void setExpressIntegral(Long expressIntegral) {
		this.expressIntegral = expressIntegral;
	}
	public String getExpressPaySource() {
		return expressPaySource;
	}
	public void setExpressPaySource(String expressPaySource) {
		this.expressPaySource = expressPaySource;
	}
	public String getExpressState() {
		return expressState;
	}
	public void setExpressState(String expressState) {
		this.expressState = expressState;
	}
	public String getLodisticeNo() {
		return lodisticeNo;
	}
	public void setLodisticeNo(String lodisticeNo) {
		this.lodisticeNo = lodisticeNo;
	}
	public String getLodisticeState() {
		return lodisticeState;
	}
	public void setLodisticeState(String lodisticeState) {
		this.lodisticeState = lodisticeState;
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
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getExpressPayTime() {
		return expressPayTime;
	}
	public void setExpressPayTime(String expressPayTime) {
		this.expressPayTime = expressPayTime;
	}
	public String getLodisticeTime() {
		return lodisticeTime;
	}
	public void setLodisticeTime(String lodisticeTime) {
		this.lodisticeTime = lodisticeTime;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getAddressPhone() {
		return addressPhone;
	}
	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}
	public String getAddressArea() {
		return addressArea;
	}
	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}
	public String getAddressDesc() {
		return addressDesc;
	}
	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}
	
}
