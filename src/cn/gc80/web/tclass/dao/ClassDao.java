package cn.gc80.web.tclass.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import cn.gc80.base.hibernate.HibernateDao;
import cn.gc80.base.page.PageHolder;
import cn.gc80.datamodel.business.EOrderDetail;
import cn.gc80.datamodel.evaluate.EvaTemPro;
import cn.gc80.datamodel.res.ResChapter;
import cn.gc80.datamodel.res.ResCouCw;
import cn.gc80.datamodel.res.ResCourse;
import cn.gc80.datamodel.sysbase.SysCode;
import cn.gc80.datamodel.training.TrainClass;
import cn.gc80.datamodel.training.TrainClassCourse;

@Repository("classDao")
public class ClassDao {
	@Resource
	private HibernateDao hibernateDao;
	
	public List<SysCode> findCodeByParent(String parent){
		if(parent!=null&&!parent.equals("")){
			Map<String, Object> map=new HashMap<String, Object>();
			StringBuffer hsql = new StringBuffer("select new SysCode( id, codeNo, codeName) from SysCode where delflag='02' and isshow='02' and codeNo!=:parent and sysCode.codeNo=:parent order by codeNo asc");
			map.put("parent", parent);
			return this.hibernateDao.queryObjects(hsql.toString(), map);
		}
		return null;
	}

	public PageHolder findClass(String area,String className,String codeNo,PageHolder ph){
		StringBuffer hsql = new StringBuffer(" from TrainClass where delflag='02' and state='02' and trainProject.status='02'");
		StringBuffer counthql = new StringBuffer("select count(id) from TrainClass where delflag='02' and state='02' and trainProject.status='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(area!=null&&!area.equals("")){
			hsql.append( " and (province.codeid like:area or province.codeid='34')");
			counthql.append( " and (province.codeid like:area or province.codeid='34')");
			map.put("area", area+"%");
		}
		if(className!=null&&!className.equals("")){
			hsql.append(" and className like :className");
			counthql.append(" and className like :className");
			map.put("className", "%"+className+"%");
		}
		if(codeNo!=null&&!codeNo.equals("")){
			hsql.append( " and trainProject.sysCode.codeNo=:codeNo");
			counthql.append( " and trainProject.sysCode.codeNo=:codeNo");
			map.put("codeNo", codeNo);
		}
		hsql.append(" order by lastedittime desc");
		return this.hibernateDao.executePage(counthql.toString(), hsql.toString(), ph.getPageNumber(), ph.getPageSize(),map);
	}

	public List<TrainClassCourse> findClassCourse(String classId,int num) {
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer(" from TrainClassCourse where 1=1");
		if(classId!=null&&!classId.equals("")){
			hsql.append( " and trainClass.id=:classId");
			map.put("classId", classId);
		}
		return this.hibernateDao.queryObjects(hsql.toString(), 1, num,map);
	}

	public EvaTemPro findEvaTemProByProId(String classid) {
		Map<String,Object> map =new ConcurrentHashMap<String,Object>();
		EvaTemPro evaTemPro=null;
		StringBuffer hsql = new StringBuffer("from EvaTemPro where EvaTemplate.tempStatus='02' and delflag='02'");
		if(classid!=null&&!"".equals(classid)){
			hsql.append( " and proId =:classid");
			map.put("classid", classid);
		}
		List list=this.hibernateDao.queryObjects(hsql.toString(), map);
			if(!list.isEmpty()){
				evaTemPro=(EvaTemPro)list.get(0);
			}
		return evaTemPro;
	}

	public TrainClass getClassByClassNo(String classNo) {
		if(classNo!=null&&!classNo.equals("")){
			Map<String, String> map= new HashMap<String, String>();
			String hsql = " from TrainClass where delflag='02' and classNo=:classNo";
			map.put("classNo",classNo);
			List list = this.hibernateDao.queryObjects(hsql,map);
			if(list!=null&&!list.isEmpty()){
				return (TrainClass)list.get(0);
			}
		}
		return null;
	}
	
	public List<TrainClassCourse> findClassCourseByClassNo(String classNo) {
		StringBuffer hsql = new StringBuffer(" from TrainClassCourse where 1=1");
		StringBuffer counthql = new StringBuffer("select count(id) from TrainClassCourse where 1=1");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(classNo!=null&&!classNo.equals("")){
			hsql.append(" and trainClass.classNo=:classNo");
			counthql.append(" and trainClass.classNo=:classNo");
			map.put("classNo", classNo);
		}
		hsql.append(" order by resCourse.lastedittime desc");
		return this.hibernateDao.queryObjects(hsql.toString(), map);
	}

	public TrainClass getTrainClassById(String id) {
		if(id!=null&&!"".equals(id)){
			return (TrainClass) this.hibernateDao.queryObject(TrainClass.class, id);
		}
		return null;
	}
	
	public List<TrainClassCourse> findClassCourseByClassId(String classId) {
		StringBuffer hsql = new StringBuffer(" from TrainClassCourse where 1=1");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(classId!=null&&!classId.equals("")){
			hsql.append(" and trainClass.id=:classId");
			map.put("classId", classId);
		}
		return this.hibernateDao.queryObjects(hsql.toString(), map);
	}
	
	public List<EOrderDetail> findOrderDetail(String userId,String courseId){
		StringBuffer hsql = new StringBuffer(" from EOrderDetail where delflag='02' and order.ordStatus='20'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append(" and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(courseId!=null&&!courseId.equals("")){
			hsql.append(" and resCourse.id=:courseId");
			map.put("courseId", courseId);
		}
		return this.hibernateDao.queryObjects(hsql.toString(), map);
	}

	public String saveClass(TrainClass tclass) {
		try {
			if(tclass.getId()!=null&&!tclass.getId().trim().equals("")){
				this.hibernateDao.updateObject(tclass);
				return tclass.getId();
			}
			else{
				return this.hibernateDao.insertObject(tclass);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return "";
	}
	
	public ResCourse getCourseByCourseNo(String courseNo) {
		if(courseNo!=null&&!courseNo.equals("")){
			Map<String, String> map= new HashMap<String, String>();
			String hsql = " from ResCourse where delflag='02' and CNo=:courseNo";
			map.put("courseNo",courseNo);
			List list = this.hibernateDao.queryObjects(hsql,map);
			if(list!=null&&!list.isEmpty()){
				return (ResCourse)list.get(0);
			}
		}
		return null;
	}
	
	public PageHolder findCoursewareByCourseNo(String courseNo,PageHolder ph){
		StringBuffer hsql = new StringBuffer(" from ResCouCw cc where cc.resCourse.delflag='02' and cc.resCourse.CNo=:courseNo order by cc.resCourseware.cwNo");
		StringBuffer counthql = new StringBuffer("select count(id) from ResCouCw cc where cc.resCourse.delflag='02' and cc.resCourse.CNo=:courseNo order by cc.resCourseware.cwNo");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		map.put("courseNo", courseNo);
		return this.hibernateDao.executePage(counthql.toString(), hsql.toString(), ph.getPageNumber(), ph.getPageSize(),map);
	}
	
	public ResChapter findChapter(String coursewareId) {
		if(coursewareId!=null&&!coursewareId.equals("")){
			Map<String, String> map= new HashMap<String, String>();
			String hsql = " from ResChapter where resCourseware.id=:coursewareId";
			map.put("coursewareId",coursewareId);
			List list = this.hibernateDao.queryObjects(hsql,map);
			if(list!=null&&!list.isEmpty()){
				return (ResChapter)list.get(0);
			}
		}
		return null;
	}
}
