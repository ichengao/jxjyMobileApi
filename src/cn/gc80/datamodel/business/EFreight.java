package cn.gc80.datamodel.business;


public class EFreight implements java.io.Serializable {

	private static final long serialVersionUID = -8453399015094456636L;

	private String id;

	private String fgtName;

	private String fgtNo;

	private Long fgtBasicPrice;

	private String fgtDesc;

	// Constructors

	/** default constructor */
	public EFreight() {
	}

	/** minimal constructor */
	public EFreight(String fgtName, String fgtNo, Long fgtBasicPrice) {
		this.fgtName = fgtName;
		this.fgtNo = fgtNo;
		this.fgtBasicPrice = fgtBasicPrice;
	}

	/** full constructor */
	public EFreight(String fgtName, String fgtNo, Long fgtBasicPrice,
			String fgtDesc) {
		this.fgtName = fgtName;
		this.fgtNo = fgtNo;
		this.fgtBasicPrice = fgtBasicPrice;
		this.fgtDesc = fgtDesc;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFgtName() {
		return this.fgtName;
	}

	public void setFgtName(String fgtName) {
		this.fgtName = fgtName;
	}

	public String getFgtNo() {
		return this.fgtNo;
	}

	public void setFgtNo(String fgtNo) {
		this.fgtNo = fgtNo;
	}

	public Long getFgtBasicPrice() {
		return this.fgtBasicPrice;
	}

	public void setFgtBasicPrice(Long fgtBasicPrice) {
		this.fgtBasicPrice = fgtBasicPrice;
	}

	public String getFgtDesc() {
		return this.fgtDesc;
	}

	public void setFgtDesc(String fgtDesc) {
		this.fgtDesc = fgtDesc;
	}
}