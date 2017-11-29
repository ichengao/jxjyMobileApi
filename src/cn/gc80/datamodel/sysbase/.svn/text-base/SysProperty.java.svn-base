package cn.gc80.datamodel.sysbase;

import cn.gc80.base.util.StringTool;

/**
 * LRN_SYS_PROPERTY系统属性
 * 
 */

public class SysProperty implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5482150669491651303L;

	private String id;

	private String propKey;

	private String propValue;

	private String propName;
	
	private String propType;
	
	private String propTextValue;

	// Constructors

	/** default constructor */
	public SysProperty() {
	}

	/** full constructor */
	public SysProperty(String propKey, String propValue, String propName,String propTextValue) {
		this.propKey = propKey;
		this.propValue = propValue;
		this.propName = propName;
		this.propTextValue=propTextValue;
	}
	public SysProperty(String id,String propName,String propKey,String propTextValue,String propValue){
		this.propKey = propKey;
		this.propValue = propValue;
		this.propName = propName;
		this.propTextValue=propTextValue;
		this.id = id;
	}
	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPropKey() {
		return this.propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	public String getPropValue() {
		return this.propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public String getPropName() {
		return this.propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropTextValue() {
		propTextValue=StringTool.Html2Text(this.propValue);
		return propTextValue;
	}

	public void setPropTextValue(String propTextValue) {
		
		this.propTextValue = propTextValue;
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

}