package cn.gc80.base.page;

import java.util.List;

public class PageHolder implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -937228487776407728L;
	public int pageNumber = 1;//��ǰҳ
	public int pageSize = 10;
	public int totalPage =1 ;
	
	public long recordNum ;
	private List dataList = null;
	
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if(pageNumber<1) 
			this.pageNumber = 1;
		this.pageNumber = pageNumber;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		//if(pageSize<=0) pageSize = 1;
		this.pageSize = pageSize;
	}
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getCurPage() {
		return pageNumber;
	}
	
	public void setRecordNum(long recordNum) {
		this.recordNum = recordNum;

		totalPage = (int)(recordNum/pageSize)	+ ((recordNum%pageSize==0)?0:1);
		if(pageNumber>totalPage)
			pageNumber = totalPage;
	}
	
	public long getRecordNum(){
		return recordNum;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	
}
