package cn.gc80.datamodel.business;

import cn.gc80.datamodel.res.ResCourse;
import cn.gc80.datamodel.sysbase.SysUser;
import cn.gc80.datamodel.training.TrainClass;

/**
 * LRN_SHOPPING_CART（购物车表）
 *
 */

public  class ShoppingCart implements java.io.Serializable {

	// Fields

	private String id;
	//用户id
	private SysUser sysUser;
	//班级id
	private TrainClass trainClass;
	//课程id
	private ResCourse resCourse;
	//商品种类
	private String productType;
	//创建时间
	private String createtime;
	//删除标志
	private String delflag;
	
	private String isSubItem;

	//赋值参数
	private Integer totalCourse;
	private Long totalCredit;
	private Double totalPrice;
	
	// Constructors

	/** default constructor */
	public ShoppingCart() {
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

	public TrainClass getTrainClass() {
		return trainClass;
	}

	public void setTrainClass(TrainClass trainClass) {
		this.trainClass = trainClass;
	}

	public ResCourse getResCourse() {
		return resCourse;
	}

	public void setResCourse(ResCourse resCourse) {
		this.resCourse = resCourse;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getIsSubItem() {
		return isSubItem;
	}

	public void setIsSubItem(String isSubItem) {
		this.isSubItem = isSubItem;
	}

	public Integer getTotalCourse() {
		return totalCourse;
	}

	public void setTotalCourse(Integer totalCourse) {
		this.totalCourse = totalCourse;
	}

	public Long getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(Long totalCredit) {
		this.totalCredit = totalCredit;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


}