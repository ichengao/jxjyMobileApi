package cn.gc80.web.info.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;


import cn.gc80.base.hibernate.HibernateDao;
import cn.gc80.base.page.PageHolder;
import cn.gc80.datamodel.info.AdPicture;
import cn.gc80.datamodel.info.InfoMessage;

@Repository("infoDao")
public class InfoDao {
	@Resource
	private HibernateDao hibernateDao;
	
	public PageHolder findMessageBySome(String codeNo, String area,PageHolder ph) {
		StringBuffer counthql = new StringBuffer("select count(id) from InfoMessage where delflag='02' and visible='02'");
		StringBuffer hql = new StringBuffer(" from InfoMessage where delflag='02' and visible='02'");
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		if (codeNo != null && !codeNo.equals("")) {
			hql.append(" and sysCode.codeNo like :codeNo");
			counthql.append(" and sysCode.codeNo like :codeNo");
			map.put("codeNo", codeNo + "%");
		}
		if (area != null && !area.equals("")) {
			hql.append(" and ( province.codeid like :area or province.codeid is null or province.codeid='34') ");
			counthql.append(" and (province.codeid like :area or province.codeid is null or province.codeid='34')");
			map.put("area", area + "%");
		}
		hql.append(" order by ordernum asc,istop desc,publishtime desc");
		ph = this.hibernateDao.executePage(counthql.toString(),hql.toString(), ph.getPageNumber(), ph.getPageSize(), map);
		return ph;
	}
	
	public InfoMessage findMessage(String codeNo, String name) {
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		InfoMessage message = null;
		StringBuffer hsql = new StringBuffer(" from InfoMessage where delflag='02' and visible='02'");
		if (codeNo != null && !codeNo.equals("")) {
			hsql.append(" and sysCode.codeNo =:codeNo");
			map.put("codeNo", codeNo);
		}
		if (name != null && !name.equals("")) {
			hsql.append(" and name =:name");
			map.put("name", name);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(), map);
		if (list != null && !list.isEmpty()) {
			message = (InfoMessage) list.get(0);
		}
		return message;
	}
	
	public List<InfoMessage> findMessageByCodeNo(String codeNo) {
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		StringBuffer hsql = new StringBuffer("select new InfoMessage(id,title,infoContent,sysCode.codeNo,name) from InfoMessage where delflag='02' and visible='02'");
		if (codeNo != null && !codeNo.equals("")) {
			hsql.append(" and sysCode.codeNo like :codeNo");
			map.put("codeNo", codeNo+"%");
		}
		hsql.append(" order by ordernum asc,istop desc,publishtime desc");
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}
	
	public List<InfoMessage> findMessageBySome(String codeNo,int num) {
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		StringBuffer hsql = new StringBuffer("select new InfoMessage(id,title,infoContent,sysCode.codeNo,name) from InfoMessage where delflag='02' and visible='02'");
		if (codeNo != null && !codeNo.equals("")) {
			hsql.append(" and sysCode.codeNo like :codeNo");
			map.put("codeNo", codeNo+"%");
		}
		hsql.append(" order by ordernum asc,istop desc,publishtime desc");
		return this.hibernateDao.queryObjects(hsql.toString(), 1, num,map);
	}
	
	public List<AdPicture> findPicture(int num){
		StringBuffer hsql = new StringBuffer(" select new AdPicture(id,picTitle,picDesc,picUrl,picType,picStatus,picOrder) from AdPicture where picStatus='02' and picType='05' order by picOrder desc" );
		return this.hibernateDao.queryObjects(hsql.toString(), 1, num);
	}
	
	public List<AdPicture> findAllPicture(){
		StringBuffer hsql = new StringBuffer(" select new AdPicture(id,picTitle,picDesc,picUrl,picType,picStatus,picOrder) from AdPicture where picStatus='02' and picType='05' order by picOrder desc" );
		return this.hibernateDao.queryObjects(hsql.toString());
	}
	
	public AdPicture findPictureById(String id) {
		AdPicture picture = null;
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		if (id != null && !id.equals("")) {
			StringBuffer hsql = new StringBuffer("from AdPicture where id=:id");
			map.put("id", id);
			List list = this.hibernateDao.queryObjects(hsql.toString(),map);
			if (list != null && !list.isEmpty()) {
				picture = (AdPicture) list.get(0);
			}
		}
		return picture;
	}
	
}
