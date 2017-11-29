package cn.gc80.web.tclass.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.gc80.base.page.PageHolder;
import cn.gc80.datamodel.business.EOrderDetail;
import cn.gc80.datamodel.evaluate.EvaTemPro;
import cn.gc80.datamodel.res.ResChapter;
import cn.gc80.datamodel.res.ResCouCw;
import cn.gc80.datamodel.res.ResCourse;
import cn.gc80.datamodel.sysbase.SysCode;
import cn.gc80.datamodel.sysbase.SysTeacher;
import cn.gc80.datamodel.training.TrainClass;
import cn.gc80.datamodel.training.TrainClassCourse;
import cn.gc80.web.tclass.dao.ClassDao;

@Service("classService")
public class ClassService {
	@Resource
	private ClassDao classDao;
	
	public List<SysCode> findCodeByParent(String parent){
		return this.classDao.findCodeByParent(parent);
	}
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public PageHolder findClass(String area, String className, String codeNo,PageHolder ph) {
		ph=this.classDao.findClass(area,className,codeNo,ph);
		List<TrainClass> list=ph.getDataList();
		for(int i=0;i<list.size();i++){
			TrainClass tClass=list.get(i);
			if(tClass.getClassEnrolStarttime()!=null&&!"".equals(tClass.getClassEnrolStarttime())&&tClass.getClassEnrolEndtime()!=null&&!"".equals(tClass.getClassEnrolEndtime())){
				try {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date d=df.parse(df.format(new Date()));
					Date stad=df.parse(tClass.getClassEnrolStarttime());
					Date endd=df.parse(tClass.getClassEnrolEndtime());
					if(d.getTime()>=stad.getTime()&&endd.getTime()>=d.getTime()){
						tClass.setTimeFlag("02");
					}else{
						tClass.setTimeFlag("01");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				tClass.setTimeFlag("02");
			}
			String teachers="";
			List<TrainClassCourse> tccList=this.classDao.findClassCourse(tClass.getId(),3);
			for(int j=0;j<tccList.size();j++){
				TrainClassCourse tcc=tccList.get(j);
				SysTeacher teacher= tcc.getResCourse().getSysTeacher();
				if(teacher!=null){
					teachers=teachers+teacher.getRealname()+" ";
				}
			}
			tClass.setTeachers(teachers);
			//是否分配班级评价
			String isEva="01";
			EvaTemPro temPro=this.classDao.findEvaTemProByProId(tClass.getId());
			if(temPro!=null){
				isEva="02";
			}
			tClass.setIsEva(isEva);
		}
		return ph;
	}

	public Map<String, Object> findClassDetailMap(String classNo) {
		Map<String,Object> map =new HashMap<String, Object>();
		if(classNo!=null){
			TrainClass tClass=this.classDao.getClassByClassNo(classNo);
			if(tClass!=null){
				if(tClass.getClassEnrolStarttime()!=null&&!"".equals(tClass.getClassEnrolStarttime())&&tClass.getClassEnrolEndtime()!=null&&!"".equals(tClass.getClassEnrolEndtime())){
					try {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date d=df.parse(df.format(new Date()));
						Date stad=df.parse(tClass.getClassEnrolStarttime());
						Date endd=df.parse(tClass.getClassEnrolEndtime());
						if(d.getTime()>=stad.getTime()&&endd.getTime()>=d.getTime()){
							tClass.setTimeFlag("02");
						}else{
							tClass.setTimeFlag("01");
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else{
					tClass.setTimeFlag("02");
				}
				String teachers="";
				List<TrainClassCourse> tccList=this.classDao.findClassCourse(tClass.getId(),3);
				for(int j=0;j<tccList.size();j++){
					TrainClassCourse tcc=tccList.get(j);
					SysTeacher teacher= tcc.getResCourse().getSysTeacher();
					if(teacher!=null){
						teachers=teachers+teacher.getRealname()+" ";
					}
				}
				tClass.setTeachers(teachers);
				//是否分配班级评价
				String isEva="01";
				EvaTemPro temPro=this.classDao.findEvaTemProByProId(tClass.getId());
				if(temPro!=null){
					isEva="02";
				}
				tClass.setIsEva(isEva);
			}
			map.put("tClass", tClass);
			List<TrainClassCourse> classCourseList=this.classDao.findClassCourseByClassNo(classNo);
			map.put("classCourseList", classCourseList);
		}
		return map;
	}
	
	public TrainClass getTrainClassById(String id){
		return this.classDao.getTrainClassById(id);
	}

	public List<TrainClassCourse> findClassCourseByClassId(String userId,String classId) {
		List<TrainClassCourse> tccList=this.classDao.findClassCourseByClassId(classId);
		for(int i=0;i<tccList.size();i++){
			ResCourse course=tccList.get(i).getResCourse();
			List<EOrderDetail> odList=this.classDao.findOrderDetail(userId, course.getId());
			if(odList.size()>0){
				course.setIsShopping("02");		
			}else{
				course.setIsShopping("01");	
			}
		}
		return tccList;
	}
	
	public Map<String, Object> toCourseDetail(String classNo,String courseNo,Map<String, Object> map,PageHolder ph) {
		TrainClass tClass=this.classDao.getClassByClassNo(classNo);
		if(tClass!=null){
			if(tClass.getClassEnrolStarttime()!=null&&!"".equals(tClass.getClassEnrolStarttime())&&tClass.getClassEnrolEndtime()!=null&&!"".equals(tClass.getClassEnrolEndtime())){
				try {
					Date d=df.parse(df.format(new Date()));
					Date stad=df.parse(tClass.getClassEnrolStarttime());
					Date endd=df.parse(tClass.getClassEnrolEndtime());
					if(d.getTime()>=stad.getTime()&&endd.getTime()>=d.getTime()){
						tClass.setTimeFlag("02");
					}else{
						tClass.setTimeFlag("01");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				tClass.setTimeFlag("02");
			}
			
			String teachers="";
			List<TrainClassCourse> tccList=this.classDao.findClassCourse(tClass.getId(),3);
			for(int j=0;j<tccList.size();j++){
				TrainClassCourse tcc=tccList.get(j);
				SysTeacher teacher= tcc.getResCourse().getSysTeacher();
				if(teacher!=null){
					teachers=teachers+teacher.getRealname()+" ";
				}
			}
			tClass.setTeachers(teachers);
			//是否分配班级评价
			String isEva="01";
			EvaTemPro temPro=this.classDao.findEvaTemProByProId(tClass.getId());
			if(temPro!=null){
				isEva="02";
			}
			tClass.setIsEva(isEva);
			
		}
		map.put("tClass", tClass);
		ResCourse course=this.classDao.getCourseByCourseNo(courseNo);
		map.put("course", course);
		ph=this.classDao.findCoursewareByCourseNo(courseNo,ph);
		map.put("couCwList", ph.getDataList());
		map.put("ph", ph);
		int couCwNum = ph.getDataList().size();
		if(couCwNum>0){
			ResChapter chapter=this.classDao.findChapter(((ResCouCw)ph.getDataList().get(0)).getResCourseware().getId());
			map.put("chapter", chapter);
		}
		return map;
	}
	
}
