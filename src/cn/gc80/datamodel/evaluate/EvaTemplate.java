package cn.gc80.datamodel.evaluate;

import cn.gc80.datamodel.sysbase.SysAdmin;
import cn.gc80.datamodel.sysbase.SysCode;

/**
 * LRN_EAV_TEMPLATE（评价模版表）
 */
public class EvaTemplate implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	// 模块名称
	private String tempName;
	// 模块分类
	private SysCode sysCode;
	// 模块介绍
	private String tempDesc;
	// 模块状态
	private String tempStatus;
	// 创建时间
	private String createTime;
	// 修改时间
	private String lastEditTime;
	// 创建人
	private SysAdmin creator;
	// 修改人
	private SysAdmin lastEditor;
	// 删除标志
	private String delflag;

	/** default constructor */
	public EvaTemplate() {
		
	}
	/** All constructor */
	public EvaTemplate(String id, String tempName, SysCode sysCode,
			String tempDesc, String tempStatus, String createTime,
			String lastEditTime, SysAdmin creator, SysAdmin lastEditor,
			String delflag) {
		this.id = id;
		this.tempName = tempName;
		this.sysCode = sysCode;
		this.tempDesc = tempDesc;
		this.tempStatus = tempStatus;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
		this.creator = creator;
		this.lastEditor = lastEditor;
		this.delflag = delflag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	
	public SysCode getSysCode() {
		return sysCode;
	}
	
	public void setSysCode(SysCode sysCode) {
		this.sysCode = sysCode;
	}
	
	public String getTempDesc() {
		return tempDesc;
	}

	public void setTempDesc(String tempDesc) {
		this.tempDesc = tempDesc;
	}

	public String getTempStatus() {
		return tempStatus;
	}

	public void setTempStatus(String tempStatus) {
		this.tempStatus = tempStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public SysAdmin getCreator() {
		return creator;
	}

	public void setCreator(SysAdmin creator) {
		this.creator = creator;
	}

	public SysAdmin getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(SysAdmin lastEditor) {
		this.lastEditor = lastEditor;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

}
