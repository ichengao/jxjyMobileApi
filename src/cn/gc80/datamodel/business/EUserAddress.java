package cn.gc80.datamodel.business;

public class EUserAddress {
	private String id;
	private String userId;
	private String address;
	private String phone;
	private String isDefault;
	private String name;
	private String postcode;
	private String area;
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	private String addressName;
	private String provinceCity;
	private String orgId;
	private String delflag;
	
	private String addressDetail;
	
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public EUserAddress(){}
	public EUserAddress(String id, String userId, String address, String phone,
			String isDefault, String name) {
		this.id = id;
		this.userId = userId;
		this.address = address;
		this.phone = phone;
		this.isDefault = isDefault;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProvinceCity() {
		return provinceCity;
	}
	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public EUserAddress(String id, String userId, String address, String phone,
			String isDefault, String name,String addressName,String provinceCity) {
		this.id = id;
		this.userId = userId;
		this.address = address;
		this.phone = phone;
		this.isDefault = isDefault;
		this.name = name;
		this.addressName = addressName;
		this.provinceCity = provinceCity;
	}
}
