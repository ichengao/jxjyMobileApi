package cn.gc80.base.page;

import java.util.ArrayList;
import java.util.List;

public class Pageination {
	public Pageination(){
		try{
			Pinit();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	private int num;//������
	private int pagesize;//ÿҳ����
	private int pages;//��ǰҳ
	private int pagenum;
	private List dataList = new ArrayList();
	private void Pinit() {
		// TODO Auto-generated method stub
		num = 0;
		pagesize = 0;
		pages = 0;
		pagenum = 0;
	}
	public void setNum(int num)
	{
		this.num = num;
	}
	public int getNum()
	{
		return num;
	}
	public void setPagesize(int pagesize)
	{
		this.pagesize = pagesize;
	}
	public int getPagesize()
	{
		return pagesize;
	}
	public void setPages(int pages)
	{
		this.pages = pages;
	}
	public int getPages()
	{
		if(pages>pagenum)
		{
			pages = pagenum;
			return pages;
		}
		else
		{
			return pages;
		}
		
	}
	public void setPagenum(int num,int pagesize)
	{
		this.num = num;
		this.pagesize = pagesize;
		this.pagenum = (int)Math.ceil(num/(double)pagesize);
	}
	public int getPagenum()
	{
		return pagenum;
	}
	public int getNext()
	{
		if(pages == pagenum)
		{
			return pagenum;
		}
		else
		{
			return pages+1;
		}
	}
	public int getPre()
	{
		if(pages ==1)
		{
			return 1;
		}
		else
		{
			return pages-1;
		}
	}
	public int getKs()
	{
		return (pages-1)*pagesize+1;
	}
	public int getJs()
	{
		if(pages*pagesize>num)
		{
			return num;
		}
		else
		{
			return pages*pagesize;
		}
	}
	
	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	
	
}
