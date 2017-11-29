package cn.gc80.datamodel.sysbase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LRN_SYS_CODE分类表
 */

public class SysCode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3072142402871324923L;

	private String id;
	//编号
	private String codeNo;
	//名称
	private String codeName;
	//父id
	private SysCode sysCode;
	//描述
	private String codeDesc;

	private String delflag;
	//显示标志，在网站上是否显示，01,不显示，02显示
	private String isshow;
	
	private List advertList;
	
	private List codeList;
	//别名
	private String codeAlias;
	
	private List list;
	
	/** default constructor */
	public SysCode() {
	}

	/** minimal constructor */
	public SysCode(String id,String codeNo, String codeName) {
		this.codeNo = codeNo;
		this.codeName = codeName;
		this.id=id;
	}
	
	public SysCode(String id,String codeNo, String codeName,String codeAlias) {
		this.codeNo = codeNo;
		this.codeName = codeName;
		this.id=id;
		this.codeAlias = codeAlias;
	}
	 

	/** full constructor */
	public SysCode(String codeNo, String codeName, String codeParent,
			String codeDesc, String delflag) {
		this.codeNo = codeNo;
		this.codeName = codeName;
		this.codeDesc = codeDesc;
		this.delflag = delflag;
	}
	public SysCode(String id,String codeNo, String codeName, String codeParent,
			String codeDesc, String delflag) {
		SysCode code=new SysCode();
		code.setId(codeParent);
		this.id=id;
		this.codeNo = codeNo;
		this.codeName = codeName;
		this.codeDesc = codeDesc;
		this.delflag = delflag;
		this.sysCode=code;
	}
	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeNo() {
		return this.codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public SysCode getYfssSysCode() {
		return sysCode;
	}

	public void setYfssSysCode(SysCode sysCode) {
		this.sysCode = sysCode;
	}

	public String getCodeDesc() {
		return this.codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getDelflag() {
		return this.delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public SysCode getSysCode() {
		return sysCode;
	}

	public void setSysCode(SysCode sysCode) {
		this.sysCode = sysCode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public String getCodeAlias() {
		return codeAlias;
	}

	public void setCodeAlias(String codeAlias) {
		this.codeAlias = codeAlias;
	}

	public List getAdvertList() {
		return advertList;
	}

	public void setAdvertList(List advertList) {
		this.advertList = advertList;
	}

	public List getCodeList() {
		return codeList;
	}

	public void setCodeList(List codeList) {
		this.codeList = codeList;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}