package cn.gc80.web.user.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import cn.gc80.base.hibernate.HibernateDao;
import cn.gc80.base.page.PageHolder;
import cn.gc80.datamodel.business.EDepositLog;
import cn.gc80.datamodel.business.EOrderDetail;
import cn.gc80.datamodel.business.EUserAddress;
import cn.gc80.datamodel.exam.ExamUserQuiz;
import cn.gc80.datamodel.integral.IntContrast;
import cn.gc80.datamodel.integral.IntIntegral;
import cn.gc80.datamodel.learning.LearnUserChapter;
import cn.gc80.datamodel.learning.LearnUserCourse;
import cn.gc80.datamodel.res.ResChapter;
import cn.gc80.datamodel.res.ResCourse;
import cn.gc80.datamodel.res.ResCourseware;
import cn.gc80.datamodel.sign.Sign;
import cn.gc80.datamodel.sysbase.SysCode;
import cn.gc80.datamodel.sysbase.SysProperty;
import cn.gc80.datamodel.sysbase.SysProvince;
import cn.gc80.datamodel.sysbase.SysUser;
import cn.gc80.datamodel.sysbase.SysUserInfo;
import cn.gc80.datamodel.training.TrainClass;
import cn.gc80.datamodel.training.TrainCredit;

@Repository("userDao")
public class UserDao {
	@Resource
	private HibernateDao hibernateDao;

	public SysUser findUserByParam(String param) {
		SysUser user = null;
		if(param!=null&&!param.equals("")){
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			String hsql = " from SysUser where delflag='02' and (userName=:param or cardno=:cardno1 or cardno=:cardno2 or bindMobile=:param)";
			map.put("param", param.trim());
			map.put("cardno1", param.trim().toLowerCase());//大写
			map.put("cardno2", param.trim().toUpperCase());//小写
			List list = hibernateDao.queryObjects(hsql,map);
			if(list!=null&&!list.isEmpty()){
				user =  (SysUser)list.get(0);
			}
		}
		return user;
	}
	
	public SysUser findUserByParam(String userName,String mobile,String cardno) {
		SysUser user = null;
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql =  new StringBuffer(" from SysUser where delflag='02'");
		if(userName!=null&&!"".equals(userName)){
			hsql.append(" and userName=:userName");
			map.put("userName", userName.trim());
		}
		if(mobile!=null&&!"".equals(mobile)){
			hsql.append(" and bindMobile=:mobile");
			map.put("mobile", mobile.trim());
		}
		if(cardno!=null&&!"".equals(cardno)){
			hsql.append(" and cardno=:cardno1 or cardno=:cardno2");
			map.put("cardno1", cardno.trim().toLowerCase());//大写
			map.put("cardno2", cardno.trim().toUpperCase());//小写
		}
		List<SysUser> list = hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null&&!list.isEmpty()){
			user = list.get(0);
		}
		return user;
	}
	
	public int updateLoginErrNum(String id,int num) {
		try {
			String hsql = "update SysUser set loginErrNum='"+num+"' where id='"+id+"'";
			return hibernateDao.executeSql(hsql);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<SysProperty> findPropertyList(String propType){
		StringBuffer hsql = new StringBuffer(" from SysProperty where 1=1");
		if(propType!=null&&!"".equals(propType)){
			hsql.append(" and propKey like '"+propType+"%'"); 
		}
		hsql.append(" order by id desc");
		
		return hibernateDao.queryObjects(hsql.toString());
	}

	public SysUser findUserByUserId(String userId) {
		if(userId!=null&&!userId.equals("")){
			Object temp =hibernateDao.queryObject(SysUser.class, userId);
			if(temp!=null)
				return (SysUser)temp;
		}
		return null;
	}

	public PageHolder findIntegralRecord(String userId, String staTime,String endTime, PageHolder ph) {
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer("select new IntIntegral(id,intContrast.integralItem,integral,equipment,ip,createtime,describe,type,CurrentIntegral,sysUser.userName) from IntIntegral where delflag='02' and createtime like :year");
		StringBuffer counthql = new StringBuffer(" select count(id) from IntIntegral where delflag='02' and createtime like :year");
		map.put("year", new SimpleDateFormat("yyyy").format(new Date())+"%");
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			counthql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(staTime!=null&&!"".equals(staTime)){
			hsql.append(" and createtime >=:staTime ");
			counthql.append(" and createtime >=:staTime ");
			map.put("staTime", staTime.trim());
		}
		if(endTime!=null&&!"".equals(endTime)){
			hsql.append(" and createtime <=:endTime ");
			counthql.append(" and createtime <=:endTime ");
			map.put("endTime", endTime.trim()+"%");
		}
		hsql.append( " order by createtime desc");
		return hibernateDao.executePage(counthql.toString(), hsql.toString(), ph.getPageNumber(), ph.getPageSize(),map);
	}

	public PageHolder toMyAccount(String userId, String staTime,String endTime, PageHolder ph) {
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer("select new EDepositLog(id,depositType,equipment,ip,depositTime,sysUser.userName,type,depositAmount,balance,describe) from EDepositLog where 1=1 ");
		StringBuffer counthql = new StringBuffer(" select count(id) from EDepositLog where 1=1");
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			counthql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(staTime!=null&&!"".equals(staTime)){
			hsql.append(" and depositTime >=:staTime ");
			counthql.append(" and depositTime >=:staTime ");
			map.put("staTime", staTime.trim());
		}
		if(endTime!=null&&!"".equals(endTime)){
			hsql.append(" and depositTime <=:endTime ");
			counthql.append(" and depositTime <=:endTime ");
			map.put("endTime", endTime.trim()+"%");
		}
		hsql.append( " order by depositTime desc");
		return hibernateDao.executePage(counthql.toString(), hsql.toString(), ph.getPageNumber(), ph.getPageSize(),map);
	} 
	
	public List<SysProvince> findProvinceByParent(String parent){
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer("select new SysProvince(codeid, cityname) from SysProvince where 1>0");
		if(parent!=null&&!parent.equals("")){
			hsql.append( " and codeid!=:parent and (sysProvince.codeid=:parent)");
			map.put("parent", parent);
		}
		hsql.append( " order by codeid asc");
		return hibernateDao.queryObjects(hsql.toString(),map);
	}
	
	@SuppressWarnings("rawtypes")
	public SysProvince findSysProvinceByCodeid(String codeId) {
		StringBuffer hsql = new StringBuffer(" from SysProvince where 1>0");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(codeId != null && !"".equals(codeId)) {
			hsql.append(" and codeid=:codeId");
			map.put("codeId", codeId);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(), map);
		if(!list.isEmpty()) {
			return (SysProvince)list.get(0);
		}
		return null;
	}
	
	public String saveUser(SysUser user){
		try{
			if(user.getId()!=null&&!user.getId().equals("")){
				hibernateDao.updateObject(user);
				return user.getId();
			}else{
				return hibernateDao.insertObject(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public List<EOrderDetail> findEOrderDetail(String userId) {
		if(userId!=null&&!"".equals(userId)){
			StringBuffer hsql = new StringBuffer("select new EOrderDetail(trainClass.id) from EOrderDetail where sysUser.id=:userId and delflag='02'and isSubItem='01' and trainClass is not null and order.ordStatus='20' ");
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			map.put("userId", userId);
			hsql.append( " group by trainClass.id order by trainClass.id ");
			return hibernateDao.queryObjects(hsql.toString(), map);
		}
		return null;
	}

	public TrainClass getTrainClassById(String id) {
		StringBuffer hsql = new StringBuffer("select new  TrainClass(id,classNo,className,province.cityname,classCredithour,classPrice,classImage,trainProject.sysCode.sysCode.codeName,trainProject.sysCode.codeName,classYear) from TrainClass where delflag='02' ");
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		if (id != null && !id.equals("")) {
			hsql.append(" and id=:id");
			map.put("id", id);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(), map);
		if (list != null && !list.isEmpty() && list.get(0) != null) {
			return (TrainClass) list.get(0);
		}
		return null;
	}

	public long findCreditCount(String userId, String classId) {
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer("select sum(t.credit) from TrainCredit t where t.creditStatus!='00'");
		if(userId!=null&&!userId.equals("")){
			hsql.append(" and t.sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(classId!=null&&!classId.equals("")){
			hsql.append(" and t.trainClass.id = :classId ");
			map.put("classId", classId);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null&&!list.isEmpty()){
			Long credit = (Long)list.get(0);
			if(credit!=null)
				return credit.longValue();
		}
		return 0;
	}
	
	public PageHolder findOrdDetails(String userId,String classId,String studystatus,String isExam, PageHolder ph){
		StringBuffer hsql = new StringBuffer("select new EOrderDetail(resCourse.id,resCourse.CNo,resCourse.CName,resCourse.CImage,resCourse.CCredithour,hasCredit,studystatus,isExam) from EOrderDetail  where sysUser.id=:userId and isSubItem='02' and trainClass.id=:classId and delflag='02' and order.ordStatus='20'");
		StringBuffer counthql = new StringBuffer("select count(id) from EOrderDetail where sysUser.id=:userId  and isSubItem='02' and trainClass.id=:classId and delflag='02' and order.ordStatus='20'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		map.put("userId", userId);
		map.put("classId", classId);
		if(studystatus!=null&&!studystatus.equals("")){
			hsql.append( " and studystatus=:studystatus");
			counthql.append( " and studystatus=:studystatus");
			map.put("studystatus", studystatus);
		}
		if(isExam!=null&&!isExam.equals("")){
			hsql.append( " and resCourse.isExam=:isExam");
			counthql.append( " and resCourse.isExam=:isExam");
			map.put("isExam", isExam);
		}
		hsql.append( " order by order.ordPaytime desc");
		return this.hibernateDao.executePage(counthql.toString(), hsql.toString(), ph.getPageNumber(), ph.getPageSize(),map);
	}

	public PageHolder findUserCoursesPercent(String userId,String courseId,String status,PageHolder ph){
		StringBuffer hsql = new StringBuffer("select id,resCourse.id,resCourse.CName,resCourseware.id,resCourseware.cwName,resCourseware.creditnum,hasCredit,time,(select sum(lu.hours) from  LearnUserChapter lu where lu.sysUser.id=:userId and lu.resCourse.id=:courseId and lu.resCourseware.id=luc.resCourseware.id and lu.resChapter.delflag='02')," +
		"(select sum(lu.hours)*100/sum(lu.resChapter.period) from  LearnUserChapter lu where lu.sysUser.id=:userId and lu.resCourse.id=:courseId and lu.resCourseware.id=luc.resCourseware.id and lu.resChapter.delflag='02'),studystatus,resCourse.sysCode.codeName,resCourseware.cwPeriod,allowAttempt " +
		"from LearnUserCourse luc where luc.sysUser.id=:userId and luc.resCourse.id=:courseId and delflag='02'");
		StringBuffer counthql = new StringBuffer("select count(id) from LearnUserCourse luc where luc.sysUser.id=:userId and luc.resCourse.id=:courseId and delflag='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		map.put("userId", userId);
		map.put("courseId", courseId);
		if(status!=null&&!status.equals("")){
			hsql.append( " and luc.status=:status");
			counthql.append( " and luc.status=:status");
			map.put("status", status);
		}
		hsql.append( " order by luc.resCourseware.cwNo asc");
		return hibernateDao.executePage(counthql.toString(), hsql.toString(), ph.getPageNumber(), ph.getPageSize(),map);
	}

	public ResCourseware findCourseware(String coursewareId) {
		if(coursewareId!=null&&!coursewareId.equals("")){
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			String hsql = "from ResCourseware where id=:coursewareId";
			map.put("coursewareId", coursewareId);
			List list = this.hibernateDao.queryObjects(hsql,map);
			if(list!=null&&!list.isEmpty()){
				return (ResCourseware)list.get(0);
			}
		}
		return null;
	}

	public List<LearnUserChapter> findUserChapterSome(String userId, String courseId, String coursewareId) {
		StringBuffer hsql = new StringBuffer("select new LearnUserChapter(id,hours,lessonstatus,resChapter.id,resChapter.period) from LearnUserChapter where 1>0");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(courseId!=null&&!courseId.equals("")){
			hsql.append( " and resCourse.id=:courseId");
			map.put("courseId", courseId);
		}
		if(coursewareId!=null&&!coursewareId.equals("")){
			hsql.append( " and resCourseware.id=:coursewareId");
			map.put("coursewareId", coursewareId);
		}
		hsql.append( " and resChapter.delflag='02'");
		hsql.append( " order by resChapter.sequence asc");
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}

	public String updateUserChapter(LearnUserChapter uch) {
		try {
			if(uch.getId()!=null&&!uch.getId().equals("")){
				String hsql = "update LearnUserChapter set lessonstatus='"+uch.getLessonstatus()+"',hours="+uch.getHours()+" where id='"+uch.getId()+"'";
				this.hibernateDao.executeSql(hsql);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String saveUserCourse(LearnUserCourse usercourse) {
		try {
			if(usercourse.getId()!=null&&!usercourse.getId().trim().equals("")){
				this.hibernateDao.updateObject(usercourse);
				return usercourse.getId();
			}else{
				return this.hibernateDao.insertObject(usercourse);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return "";
	}

	public List<LearnUserCourse> findUserCourseByCourseId(String userId,String courseId){
		if(userId!=null&&courseId!=null&&!userId.equals("")&&!courseId.equals("")){
			String hsql = "select new LearnUserCourse(id,sysUser.id,sysUser.userName,resCourse.id,resCourse.CName,resCourseware.id,resCourseware.cwName,resCourseware.creditnum,hasCredit,time,studystatus) from LearnUserCourse where delflag='02' and sysUser.id=:userId and resCourse.id=:courseId order by resCourseware.cwNo,ensuretime desc";
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			map.put("userId", userId);
			map.put("courseId", courseId);
			return this.hibernateDao.queryObjects(hsql,map);
		}
		return null;
	}

	public LearnUserCourse findUserCourseStudyStatus(String userId,String courseId,String coursewareId){
		if(userId!=null&&coursewareId!=null){
			StringBuffer hsql = new StringBuffer("select new LearnUserCourse(id,sysUser.id,resCourse.id,resCourse.CName,resCourse.isExam,resCourseware.id,resCourseware.cwName,studystatus,hasCredit,resCourseware.creditnum,resCourse.sysCode.sysCode.codeNo,resCourse.sysCode.codeNo,resCourse.CCredithour,resCourseware.cwPeriod) from LearnUserCourse where sysUser.id=:userId");
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			map.put("userId", userId);
			if(courseId!=null&&!courseId.equals("")){
				hsql.append( " and resCourse.id=:courseId");
				map.put("courseId", courseId);
			}
			if(coursewareId!=null&&!coursewareId.equals("")){
				hsql.append( " and resCourseware.id=:coursewareId");
				map.put("coursewareId", coursewareId);
			}
			List list = this.hibernateDao.queryObjects(hsql.toString(),map);
			if(list!=null&&!list.isEmpty()){
				return (LearnUserCourse)list.get(0);
			}
		}
		else{
			return null;
		}
		return null;
	}

	public List<EOrderDetail> findOrdDetail(String userId,String courseId){
		StringBuffer hsql = new StringBuffer(" from EOrderDetail where delflag='02' and isSubItem='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append(" and sysUser.id=:userId");
			map.put("userId",userId);
		}
		if(courseId!=null&&!courseId.equals("")){
			hsql.append(" and resCourse.id=:courseId");
			map.put("courseId",courseId);
		}
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}

	public ExamUserQuiz findQuiz(String userId,String sourceid){
		if(userId!=null&&sourceid!=null&&!userId.equals("")&&!sourceid.equals("")){
			String hql=" from ExamUserQuiz q where q.sysUser.id='"+userId+"' and (q.examQuiz.resCourse.id = '"+sourceid+"' or q.examQuiz.trainProject.id ='"+sourceid+"' ) ";
			try {
				List list = this.hibernateDao.queryObjects(hql);
				if(list!=null&&!list.isEmpty()){
					return (ExamUserQuiz)list.get(0);
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String updateUserQuiz(ExamUserQuiz bean) {
		try {
			return this.hibernateDao.updateObject(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public TrainCredit findCreditByUC(String userId,String courseId){
		if(userId!=null&&!userId.equals("")&&courseId!=null&&!courseId.equals("")){
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			String hsql = " from TrainCredit where creditStatus!='00' and sysUser.id=:userId and resCourse.id=:courseId";
			map.put("userId", userId);
			map.put("courseId", courseId);
			List list = this.hibernateDao.queryObjects(hsql,map);
			if(list!=null&&!list.isEmpty()){
				return (TrainCredit)list.get(0);
			}
		}
		return null;
	}

	public LearnUserCourse findLastUserCourse(String userId,String courseId){
		if(userId!=null&&courseId!=null){
			StringBuffer hsql = new StringBuffer("select new LearnUserCourse(id,firsttime,lasttime,completetime,ensuretime,time) from LearnUserCourse where sysUser.id=:userId");
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			map.put("userId", userId);
			if(courseId!=null&&!courseId.equals("")){
				hsql.append( " and resCourse.id=:courseId");
				map.put("courseId", courseId);
			}
			hsql.append( " order by firsttime");
			List list = this.hibernateDao.queryObjects(hsql.toString(),map);
			if(list!=null&&!list.isEmpty()){
				return (LearnUserCourse)list.get(0);
			}
		}
		return null;
	}

	public String saveOrderDetail(EOrderDetail eod) {
		try {
			if(eod.getId()!=null&&!eod.getId().equals("")){
				this.hibernateDao.updateObject(eod);
				return eod.getId();
			}
			else{
				return this.hibernateDao.insertObject(eod);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return "";
	}
	
	public String updateOrderDetail(EOrderDetail eod) {
		try {
			if(eod.getId()!=null&&!eod.getId().equals("")){
				Map<String, Object> map= new ConcurrentHashMap<String, Object>();
				StringBuffer hsql = new StringBuffer("update EOrderDetail set hasCredit=:hasCredit , studystatus=:studystatus where id=:id");
				map.put("id", eod.getId());
				map.put("hasCredit", eod.getHasCredit());
				map.put("studystatus", eod.getStudystatus());
				this.hibernateDao.executeSql(hsql.toString(),map);
				return eod.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "";
	}
	

	public EOrderDetail getOrderDetail(String userId, String classid) {
		StringBuffer hsql = new StringBuffer("select new EOrderDetail(trainClass.id,trainClass.className,trainClass.trainProject.sysCode.sysCode.codeName,year,studyNo) from EOrderDetail where delflag='02' and isSubItem='01'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append(" and sysUser.id=:userId");
			map.put("userId",userId);
		}
		if(classid!=null&&!classid.equals("")){
			hsql.append(" and trainClass.id=:classid");
			map.put("classid",classid);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null){
			return (EOrderDetail)list.get(0);
		}
		return null;
	}

	public List<TrainCredit> findCredit(String userId, String classId) {
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer(" from TrainCredit t where 1=1");
		if(userId!=null&&!userId.equals("")){
			hsql.append(" and t.sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(classId!=null&&!classId.equals("")){
			hsql.append(" and t.trainClass.id = :classId ");
			map.put("classId", classId);
		}
		hsql.append(" order by createtime");
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}

	public SysUserInfo findUserInfoByUserId(String userId){
		if(userId!=null&&!userId.equals("")){
			String hsql = " from SysUserInfo where sysUser.id=:userId";
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			map.put("userId", userId);
			List list=this.hibernateDao.queryObjects(hsql,map);
			if(list!=null&&!list.isEmpty()&&list.get(0)!=null){
				return (SysUserInfo) list.get(0);
			}
		}
		return null;
	}

	public ResCourse findCourseInStudy(String courseId) {
		if (courseId != null && !courseId.equals("")) {
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			String hsql = "select new ResCourse(id,CNo,CName,CCredithour,CPrice,CReleaseFlag,COrdernum,sysCode.id, sysCode.codeNo) from ResCourse where id=:courseId";
			map.put("courseId", courseId);
			List list =this.hibernateDao.queryObjects(hsql,map);
			if(list!=null&&!list.isEmpty()){
				return (ResCourse)list.get(0);
			}
		}
		return null;
	}

	public ResCourseware findCoursewareSome(String coursewareId) {
		if(coursewareId!=null&&!coursewareId.equals("")){
			String hsql = "select new ResCourseware(id,cwNo,cwFileserver,cwScorm,cwReleaseDir,cwIndexFile,creditnum,cwPeriod,trackMode,cwName) from ResCourseware where id=:coursewareId";
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			map.put("coursewareId", coursewareId);
			List list =this.hibernateDao.queryObjects(hsql,map);
			if(list!=null&&!list.isEmpty()){
				return (ResCourseware)list.get(0);
			}
		}
		return null;
	}

	public List<ResChapter> findChapter(String coursewareId) {
		StringBuffer hql = new StringBuffer("select new ResChapter(resCourseware.id,resCourseware.trackMode,id,scotype,launch,sequence,period,chapterTitle) from ResChapter where delflag='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(coursewareId!=null&&!coursewareId.equals("")){
			hql.append( " and resCourseware.id=:coursewareId");
			map.put("coursewareId", coursewareId);
		}
		hql.append( " order by sequence asc");
		List<ResChapter> list = this.hibernateDao.queryObjects(hql.toString(),map);
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

	public LearnUserCourse findUserCourseInStudy(String userId,String courseId, String coursewareId) {
		if(userId!=null&&coursewareId!=null){
			StringBuffer hsql = new StringBuffer("select new LearnUserCourse(id,firsttime,lasttime,completetime,studystatus,time,hours) from LearnUserCourse where sysUser.id=:userId");
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			map.put("userId", userId);
			if(courseId!=null&&!courseId.equals("")){
				hsql.append( " and resCourse.id=:courseId");
				map.put("courseId", courseId);
			}
			if(coursewareId!=null&&!coursewareId.equals("")){
				hsql.append( " and resCourseware.id=:coursewareId");
				map.put("coursewareId", coursewareId);
			}
			List list = this.hibernateDao.queryObjects(hsql.toString(),map);
			if(list!=null&&!list.isEmpty()){
				return (LearnUserCourse)list.get(0);
			}
		}else{
			return null;
		}
		return null;
	}

	public LearnUserChapter findUserChapter(String userId, String courseId,String coursewareId, String chapterId) {
		StringBuffer hsql = new StringBuffer("from LearnUserChapter where 1>0");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(courseId!=null&&!courseId.equals("")){
			hsql.append( " and resCourse.id=:courseId");
			map.put("courseId", courseId);
		}
		if(coursewareId!=null&&!coursewareId.equals("")){
			hsql.append( " and resCourseware.id=:coursewareId");
			map.put("coursewareId", coursewareId);
		}
		if(chapterId!=null&&!chapterId.equals("")){
			hsql.append( " and resChapter.id=:chapterId");
			map.put("chapterId", chapterId);
		}
		hsql.append( " order by sequence");
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null&&!list.isEmpty()){
			return (LearnUserChapter)list.get(0);
		}else{
			return null;
		}
	}

	public String saveUserChapter(LearnUserChapter userChapter) {
		try {
			if(userChapter.getId()!=null&&!userChapter.getId().equals("")){
				this.hibernateDao.updateObject(userChapter);
				return userChapter.getId();
			}else{
				return this.hibernateDao.insertObject(userChapter);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public EOrderDetail findOrdDetailBySome(String userId,String courseId){
		StringBuffer hsql = new StringBuffer(" from EOrderDetail where delflag='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append(" and sysUser.id=:userId");
			map.put("userId",userId);
		}
		if(courseId!=null&&!courseId.equals("")){
			hsql.append(" and resCourse.id=:courseId");
			map.put("courseId",courseId);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null){
			return (EOrderDetail)list.get(0);
		}
		return null;
	}

	public void updateOrdDetail(EOrderDetail od) {
		try {
			if(od.getId()!=null&&!od.getId().trim().equals("")){
				String hsql = "update EOrderDetail set hasCredit="+od.getHasCredit()+",studystatus='"+od.getStudystatus()+"',isExam='"+od.getIsExam()+"' where id='"+od.getId()+"'";
				this.hibernateDao.executeSql(hsql);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String updateUserCourseInStudy(LearnUserCourse uc) {
		try {
			if(uc.getId()!=null&&!uc.getId().trim().equals("")){
				String hsql = "update LearnUserCourse set firsttime='"+uc.getFirsttime()+"',lasttime='"+uc.getLasttime()+"',studystatus='"+uc.getStudystatus()+"',time="+uc.getTime()+",hours="+uc.getHours()+" where id='"+uc.getId()+"'";
				this.hibernateDao.executeSql(hsql);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public LearnUserChapter findUserChapterStatus(String userId,String courseId, String coursewareId, String chapterId) {
		StringBuffer hsql = new StringBuffer("select new LearnUserChapter(lessonstatus) from LearnUserChapter where 1>0");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(courseId!=null&&!courseId.equals("")){
			hsql.append( " and resCourse.id=:courseId");
			map.put("courseId", courseId);
		}
		if(coursewareId!=null&&!coursewareId.equals("")){
			hsql.append( " and resCourseware.id=:coursewareId");
			map.put("coursewareId", coursewareId);
		}
		if(chapterId!=null&&!chapterId.equals("")){
			hsql.append( " and resChapter.id=:chapterId");
			map.put("chapterId", chapterId);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null&&!list.isEmpty()){
			return (LearnUserChapter)list.get(0);
		}else{
			return null;
		}
	}

	public LearnUserChapter findUserChapterInStudy(String userId,String courseId, String coursewareId, String chapterId) {
		StringBuffer hsql = new StringBuffer("select new LearnUserChapter(id,hours,time,starttime,lasttime,endtime,lessonstatus,lessonLocation,suspendData,laststarttime,totalHours) from LearnUserChapter where 1>0");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(courseId!=null&&!courseId.equals("")){
			hsql.append( " and resCourse.id=:courseId");
			map.put("courseId", courseId);
		}
		if(coursewareId!=null&&!coursewareId.equals("")){
			hsql.append( " and resCourseware.id=:coursewareId");
			map.put("coursewareId", coursewareId);
		}
		if(chapterId!=null&&!chapterId.equals("")){
			hsql.append( " and resChapter.id=:chapterId");
			map.put("chapterId", chapterId);
		}
		hsql.append( " order by sequence");
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null&&!list.isEmpty()){
			return (LearnUserChapter)list.get(0);
		}else{
			return null;
		}
	}

	public int updateUserChapterInStudy(LearnUserChapter uch) {
		try {
			if(uch.getId()!=null&&!uch.getId().equals("")){
				String hsql = "update LearnUserChapter set lessonstatus='"+uch.getLessonstatus()+"',time="+uch.getTime()+",hours="+uch.getHours()+",totalHours="+uch.getTotalHours()+",lessonLocation='"+uch.getLessonLocation()+"',suspendData='"+uch.getSuspendData()+"',lasttime='"+uch.getLasttime()+"',laststarttime="+uch.getLaststarttime()+",endtime='"+uch.getEndtime()+"' where id='"+uch.getId()+"'";
				return this.hibernateDao.executeSql(hsql);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void addDeposit(EDepositLog deposit) {
		try {
			if(deposit.getId()!=null&&!deposit.getId().equals("")){
				this.hibernateDao.updateObject(deposit);
			}
			else{
				this.hibernateDao.insertObject(deposit);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
	}
	
	public List<EUserAddress> getPageByUserId(String userId) {
		StringBuffer hql = new StringBuffer(" from EUserAddress where userId=:userId and delflag='02' order by isDefault desc");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		map.put("userId", userId);
		return this.hibernateDao.queryObjects(hql.toString(), map);
	}
	
	public SysProvince getProvince(String area) {
		SysProvince sysProvince = null;
		String hql = " from SysProvince where codeid='"+area+"'";
		List<SysProvince> list = this.hibernateDao.queryObjects(hql);
		if(list.size()>0){
			sysProvince = list.get(0);
		}
		return sysProvince;
	}
	public List<EUserAddress> findDefault(String userId) {
		StringBuffer hql = new StringBuffer(" from EUserAddress where userId=:userId and delflag='02' and isDefault='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		map.put("userId", userId);
		return this.hibernateDao.queryObjects(hql.toString(), map);
		 
	}
	public void updateAddress(EUserAddress userAddress) {
		try {
			this.hibernateDao.updateObject(userAddress);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void saveAddress(EUserAddress userAddress) {
		try {
			this.hibernateDao.insertObject(userAddress);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public EUserAddress findById(String addressId) {
		String hql = " from EUserAddress where id='"+addressId+"'";
		@SuppressWarnings("unchecked")
		List<EUserAddress> list = this.hibernateDao.queryObjects(hql);
		return list.get(0);
	}
	public void delAddress(String addressId) {
		String hql = "delete from EUserAddress where id='"+addressId+"'";
		try {
			this.hibernateDao.deleteObject(hql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public SysCode findCodeByNo(String codeNo){
		StringBuffer hsql = new StringBuffer("from SysCode where 1>0");
		if(codeNo!=null&&!codeNo.equals("")){
			hsql.append( " and codeNo='"+codeNo+"'");
			List list = this.hibernateDao.queryObjects(hsql.toString());
			if(list!=null&&!list.isEmpty()){
				return (SysCode)list.get(0);
			}
		}
		return null;
	}
	public String saveUserInfo(SysUserInfo userInfo){
		try {
			if(userInfo.getId()!=null&&!userInfo.getId().equals("")){
				this.hibernateDao.updateObject(userInfo);
				return userInfo.getId();
			}else{
				return this.hibernateDao.insertObject(userInfo);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "";
	}
	public IntContrast findByCodeNo(String codeNo) {
		IntContrast intContrast = null;
		if(codeNo!=null&&!"".equals(codeNo)){
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			String hsql = " from IntContrast where codeNo=:codeNo";
			map.put("codeNo", codeNo);
			List list= hibernateDao.queryObjects(hsql,map);
			if(!list.isEmpty()){
				intContrast=(IntContrast) list.get(0);
			}
		}
		return intContrast;
	}
	
	public IntIntegral findIntIntegal(String userid, String codeid) {
		IntIntegral intIntegral=null;
		StringBuffer hsql = new StringBuffer(" from IntIntegral where delflag='02' ");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userid!=null&& !"".equals(userid)){
			hsql.append(" and sysUser.id=:userid");
			map.put("userid", userid);
		}
		if(codeid!=null&& !"".equals(codeid)){
			hsql.append(" and intContrast.id=:codeid");
			map.put("codeid", codeid);
		}
		List list=this.hibernateDao.queryObjects(hsql.toString(), map);
		if(!list.isEmpty()){
			intIntegral=(IntIntegral)list.get(0);
		}
		return intIntegral;
	}
	
	public List findSign(String id, String time) {
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer("from Sign where 1>0");
		if(id!=null&&!id.equals("")){
			hsql.append( " and sysUser.id=:id ");
			map.put("id", id);
		}
		if(time!=null&&!time.equals("")){
			hsql.append( " and time like :time ");
			map.put("time", time+"%");
		}
		List list=this.hibernateDao.queryObjects(hsql.toString(),map);
		return list;
	}
	
	public List findIntegral(String id, String time) {
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer("from IntIntegral where delflag='02' and (intContrast.codeNo='gc006' or intContrast.codeNo='gc005' or intContrast.codeNo='gc007' or intContrast.codeNo='gc008' )");
		if(id!=null&&!id.equals("")){
			hsql.append( " and sysUser.id=:id ");
			map.put("id", id);
		}
		if(time!=null&&!time.equals("")){
			hsql.append( " and createtime like :time ");
			map.put("time", time+"%");
		}
		List list=this.hibernateDao.queryObjects(hsql.toString(),map);
		return list;
	}
	
	public List findSignTime(String id, String time) {
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer("select new Sign(time) from Sign where 1>0");
		if(id!=null&&!id.equals("")){
			hsql.append( " and sysUser.id=:id ");
			map.put("id", id);
		}
		if(time!=null&&!time.equals("")){
			hsql.append( " and time like :time ");
			map.put("time", time+"%");
		}
		List list=this.hibernateDao.queryObjects(hsql.toString(),map);
		return list;
	}


	public String saveSign(Sign sign) {
		try {
			return 	this.hibernateDao.insertObject(sign);
			} catch (HibernateException e) {
				e.printStackTrace();
			} 
			return null;
	}
}
