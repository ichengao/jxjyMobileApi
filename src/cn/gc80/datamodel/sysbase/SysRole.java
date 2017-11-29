package cn.gc80.datamodel.sysbase;

import java.util.HashSet;
import java.util.Set;

/**
 * SysRole generated by MyEclipse Persistence Tools
 */

public class SysRole implements java.io.Serializable {

	

	private String id;

	private String roleName;

	private String roleLevel;

	private String roleStatus;

	private String roleDesc;

	private String delflag;

	private Set sysUserRoles = new HashSet(0);

	public SysRole() {
	}

	/** minimal constructor */
	public SysRole(String roleName, String roleStatus, String delflag) {
		this.roleName = roleName;
		this.roleStatus = roleStatus;
		this.delflag = delflag;
	}
	public SysRole(String roleName,String roleStatus,String  id, String delflag){
		this.roleName = roleName;
		this.roleStatus = roleStatus;
		this.delflag = delflag;this.id = id;
	}
	/** full constructor */
	public SysRole(String roleName, String roleLevel, String roleStatus,
			String roleDesc, String delflag, 
			Set sysUserRoles) {
		this.roleName = roleName;
		this.roleLevel = roleLevel;
		this.roleStatus = roleStatus;
		this.roleDesc = roleDesc;
		this.delflag = delflag;
		this.sysUserRoles = sysUserRoles;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleLevel() {
		return this.roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getRoleStatus() {
		return this.roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getDelflag() {
		return this.delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public Set getSysUserRoles() {
		return this.sysUserRoles;
	}

	public void setSysUserRoles(Set sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

}