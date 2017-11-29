package cn.gc80.web.cert.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import cn.gc80.base.hibernate.HibernateDao;
import cn.gc80.datamodel.business.EOrderDetail;
import cn.gc80.datamodel.sysbase.SysCertProvice;
import cn.gc80.datamodel.sysbase.SysProvince;
import cn.gc80.datamodel.sysbase.SysUser;
import cn.gc80.datamodel.sysbase.SysUserInfo;
import cn.gc80.datamodel.training.TrainCert;
import cn.gc80.datamodel.training.TrainClass;
import cn.gc80.datamodel.training.TrainCredit;

@Repository("certDao")
public class CertDao {
	@Resource
	private HibernateDao hibernateDao;

	@SuppressWarnings("rawtypes")
	public TrainCert getCertByCertNo(String certNo) {
		if(certNo!=null&&!"".equals(certNo)){
			StringBuffer hsql = new StringBuffer("select new TrainCert( id,realname,certName,certNo,organization,certDate,expressState) from TrainCert where delflag='02'");
			Map<String, String> map = new ConcurrentHashMap<String, String>();
			if (certNo != null && !certNo.equals("")) {
				hsql.append(" and certNo = :certNo");
				map.put("certNo", certNo);
			}
			List list = this.hibernateDao.queryObjects(hsql.toString(), map);
			if (list != null && !list.isEmpty()) {
				return (TrainCert) list.get(0);
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public TrainCert findCertDetailsByCertNo(String certNo) {
		StringBuffer hsql = new StringBuffer(
				" from TrainCert where delflag='02'");
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		if (certNo != null && !certNo.equals("")) {
			hsql.append(" and certNo = :certNo");
			map.put("certNo", certNo);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(), map);
		if (list != null && !list.isEmpty()) {
			return (TrainCert) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List findCertList(String realname, String cardNo) {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		if(realname!=null&&!"".equals(realname)&&cardNo!=null&&!"".equals(cardNo)){
			StringBuffer hql = new StringBuffer("select new TrainCert(id,realname,certName,certNo,organization,certDate,expressState) from TrainCert where delflag='02'");
			if (realname != null && !realname.equals("")) {
				hql.append(" and realname =:realname");
				map.put("realname", realname);
			}
			if (cardNo != null && !cardNo.equals("")) {
				hql.append(" and cardno =:cardno");
				map.put("cardno", cardNo);
			}
			hql.append(" order by year desc");
			return this.hibernateDao.queryObjects(hql.toString(), map);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<EOrderDetail> findEOrderDetail(String realname, String cardno) {
		StringBuffer hsql = new StringBuffer(
				"select new EOrderDetail(id,trainClass.className) from EOrderDetail where delflag='02'and isSubItem='01' and trainClass is not null and order.ordStatus='20' ");
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		if (realname != null && !realname.equals("")) {
			hsql.append(" and sysUser.realname=:realname ");
			map.put("realname", realname);
		}
		if (cardno != null && !cardno.equals("")) {
			hsql.append(" and (sysUser.cardno=:cardno or sysUser.cardno=:cardno1)");
			map.put("cardno", cardno.toLowerCase());
			map.put("cardno1", cardno.toUpperCase());
		}
		hsql.append(" order by trainClass.id");
		return this.hibernateDao.queryObjects(hsql.toString(), map);
	}

	public TrainClass getTrainClassById(String classId) {
		return (TrainClass) this.hibernateDao.queryObject(TrainClass.class,
				classId);
	}

	@SuppressWarnings("rawtypes")
	public long findCreditCount(String userId, String classId) {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer(
				"select sum(t.credit) from TrainCredit t where creditStatus!='00'");
		if (userId != null && !userId.equals("")) {
			hsql.append(" and t.sysUser.id=:userId");
			map.put("userId", userId);
		}
		if (classId != null && !classId.equals("")) {
			hsql.append(" and t.trainClass.id = :classId ");
			map.put("classId", classId);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(), map);
		if (list != null && !list.isEmpty()) {
			Long credit = (Long) list.get(0);
			if (credit != null)
				return credit.longValue();
		}
		return 0;
	}

	@SuppressWarnings("rawtypes")
	public EOrderDetail findOrderDetailByClassId(String classId, String userId) {
		StringBuffer hsql = new StringBuffer("select new EOrderDetail(id,trainClass.className,studyNo,year) from EOrderDetail where delflag='02' and isSubItem='01'");
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		if (classId != null && !"".equals(classId)) {
			hsql.append(" and trainClass.id=:classId");
			map.put("classId", classId);
		}
		if (userId != null && !"".equals(userId)) {
			hsql.append(" and sysUser.id=:userId");
			map.put("userId", userId);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(), map);
		if (!list.isEmpty()) {
			return (EOrderDetail) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public TrainClass findTrainClassById(String classid) {
		StringBuffer hsql = new StringBuffer("select new  TrainClass(className,classYear) from TrainClass where delflag='02' ");
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		if (classid != null && !classid.equals("")) {
			hsql.append(" and id=:classid");
			map.put("classid", classid);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(), map);
		if (list != null && !list.isEmpty() && list.get(0) != null) {
			return (TrainClass) list.get(0);
		}
		return null;
	}
	
	public SysUser findTrainClassById1(String classid) {
		StringBuffer hsql = new StringBuffer(" from SysUser where delflag='02' ");
		List list = this.hibernateDao.queryObjects(hsql.toString());
		if (list != null && !list.isEmpty() && list.get(0) != null) {
			return (SysUser) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Long findCreditSum(String userid, String classid) {
		StringBuffer hsql = new StringBuffer(" select sum(credit) from TrainCredit where creditStatus!='00' ");
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		if (userid != null && !userid.equals("")) {
			hsql.append(" and sysUser.id=:userid");
			map.put("userid", userid);
		}
		if (classid != null && !classid.equals("")) {
			hsql.append(" and trainClass.id=:classid");
			map.put("classid", classid);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(), map);
		if (!list.isEmpty() && list.size() > 0 && list.get(0) != null) {
			return Long.valueOf(list.get(0).toString());
		} else {
			return 0L;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TrainCredit> findCredit(String userid, String classid) {
		StringBuffer hsql = new StringBuffer("select new  TrainCredit(trainClass.trainProject.sysCode.sysCode.codeName,courseName,credit) from TrainCredit where creditStatus!='00' ");
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		if (userid != null && !userid.equals("")) {
			hsql.append(" and sysUser.id=:userid");
			map.put("userid", userid);
		}
		if (classid != null && !classid.equals("")) {
			hsql.append(" and trainClass.id=:classid");
			map.put("classid", classid);
		}
		return this.hibernateDao.queryObjects(hsql.toString(), map);
	}

	public SysUser findUser(String userid) {
		if (userid != null && !userid.equals("")) {
			Object temp = this.hibernateDao.queryObject(SysUser.class, userid);
			if (temp != null) {
				return (SysUser) temp;
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public SysUserInfo findUserInfo(String userid) {
		if (userid != null && !userid.equals("")) {
			String hsql = " from SysUserInfo where sysUser.id=:userid";
			Map<String, String> map = new ConcurrentHashMap<String, String>();
			map.put("userid", userid);
			List list = this.hibernateDao.queryObjects(hsql, map);
			if (list != null && !list.isEmpty() && list.get(0) != null) {
				return (SysUserInfo) list.get(0);
			}
		}
		return null;
	}

	public TrainCert findCertDetailsById(String id) {
		if(id!=null&&!"".equals(id)){
			StringBuffer hsql = new StringBuffer("select new TrainCert(trainClass.id,trainClass.className,sysUser.id,realname,cardno,sysUser.sex,organization,sysUser.userphoto,starttime,finishtime,createtime,credit) from TrainCert where id=:id");
			Map<String, String> map = new ConcurrentHashMap<String, String>();
			map.put("id", id);
			List<TrainCert> list = hibernateDao.queryObjects(hsql.toString(),map);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List findCertDetailList(String classid, String userid) {
		if (classid != null && !classid.equals("") && userid != null && !userid.equals("")) {
			StringBuffer hsql = new StringBuffer("select new TrainCredit(courseName,credit) from TrainCredit where creditStatus!='00'");
			Map<String, String> map = new ConcurrentHashMap<String, String>();
			hsql.append(" and trainClass.id=:classid");
			hsql.append(" and sysUser.id=:userid");
			map.put("classid", classid);
			map.put("userid", userid);
			return this.hibernateDao.queryObjects(hsql.toString(), map);
		}
		return null;
	}

	public List<TrainCredit> findCreditList(String realname, String cardno) {
		if(realname!=null&&!"".equals(realname)&&cardno!=null&&!"".equals(cardno)){
			StringBuffer hsql = new StringBuffer("select new TrainCredit(id,trainClass.className) from TrainCredit where creditStatus!='00'");
			Map<String, String> map = new ConcurrentHashMap<String, String>();
			hsql.append(" and sysUser.realname=:realname");
			hsql.append(" and sysUser.cardno=:cardno");
			map.put("realname", realname);
			map.put("cardno", cardno);
			return this.hibernateDao.queryObjects(hsql.toString(), map);
		}
		return null;
	}

	public TrainCert getCertById(String id) {
		if(id!=null&&!"".equals(id)){
			StringBuffer hsql = new StringBuffer("select new TrainCert(id,realname, cardno,certType,credit,certName,year,certNo) from TrainCert where delflag='02'");
			Map<String, String> map = new ConcurrentHashMap<String, String>();
			if (id != null && !id.equals("")) {
				hsql.append(" and id=:id");
				map.put("id", id);
			}
			List<TrainCert> list=this.hibernateDao.queryObjects(hsql.toString(), map);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	public TrainCredit getCreditById(String id) {
		if(id!=null&&!"".equals(id)){
			StringBuffer hsql = new StringBuffer("select new TrainCredit(id,trainClass.id,trainClass.className,sysUser.id,sysUser.realname,sysUser.cardno,year,trainClass.trainProject.sysCode.codeNo,credit) from TrainCredit where creditStatus!='00'");
			Map<String, String> map = new ConcurrentHashMap<String, String>();
			if (id != null && !id.equals("")) {
				hsql.append(" and id=:id");
				map.put("id", id);
			}
			List<TrainCredit> list=this.hibernateDao.queryObjects(hsql.toString(), map);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	public EOrderDetail findEOrderDetailById(String odId) {
		StringBuffer hsql = new StringBuffer("select new EOrderDetail(trainClass.id,trainClass.className,trainClass.trainProject.sysCode.sysCode.codeName,sysUser.id,sysUser.realname,sysUser.cardno,year) from EOrderDetail where delflag='02'");
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		if (odId != null && !"".equals(odId)) {
			hsql.append(" and id=:odId");
			map.put("odId", odId);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(), map);
		if (!list.isEmpty()) {
			return (EOrderDetail) list.get(0);
		}
		return null;
	}
	
	public String saveCredit(TrainCredit credit) {
		try {
			if(credit.getId()!=null&&!credit.getId().equals("")){
				this.hibernateDao.updateObject(credit);
				return credit.getId();
			}else{
				return this.hibernateDao.insertObject(credit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public TrainCert findCert(String userId, String classId) {
		Map<String,Object> map =new ConcurrentHashMap<String,Object>();
		StringBuffer hsql = new StringBuffer("from TrainCert where 1=1");
		if(userId!=null&&!"".equals(userId)){
			hsql.append( " and sysUser.id =:userId");
			map.put("userId", userId);
		}
		if(classId!=null&&!"".equals(classId)){
			hsql.append( " and trainClass.id =:classId");
			map.put("classId", classId);
		}
		List list=this.hibernateDao.queryObjects(hsql.toString(), map);
			if(!list.isEmpty()){
				return (TrainCert)list.get(0);
			}
		return null;
	}

	public String saveCert(TrainCert cert) {
		try {
			if(cert.getId()!=null&&!cert.getId().equals("")){
				this.hibernateDao.updateObject(cert);
				return cert.getId();
			}
			else{
				return this.hibernateDao.insertObject(cert);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return "";
	}

	@SuppressWarnings("rawtypes")
	public List findCertList(String cardNo) {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		StringBuffer hql = new StringBuffer(" from TrainCert where delflag='02'");

		if (cardNo != null && !cardNo.equals("")) {
			hql.append(" and cardno =:cardno");
			map.put("cardno", cardNo);
		}
		hql.append(" order by year desc");
		return this.hibernateDao.queryObjects(hql.toString(), map);
	}
	
	public TrainCert getCertDetailById(String id) {
		if(id!=null&&!"".equals(id)){
			StringBuffer hsql = new StringBuffer(" from TrainCert where delflag='02'");
			Map<String, String> map = new ConcurrentHashMap<String, String>();
			if (id != null && !id.equals("")) {
				hsql.append(" and id=:id");
				map.put("id", id);
			}
			List<TrainCert> list=this.hibernateDao.queryObjects(hsql.toString(), map);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	
	public SysCertProvice findCertProvince(String area){
		if(area!=null&&area.length()>4){
			area = area.substring(0,4);
		}
		StringBuffer hsql = new StringBuffer(" from SysCertProvice where sysProvince.codeid=:area");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		map.put("area", area);
		List list=this.hibernateDao.queryObjects(hsql.toString(), map);
		if(!list.isEmpty()){
			return(SysCertProvice)list.get(0);
		}
		return null;
	}
	
	public SysProvince findSysProvinceCodeid(String id) {
		StringBuffer hsql = new StringBuffer(" from SysProvince where  1=1");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(id!=null &&!"".equals(id)){
			hsql.append(" and codeid=:id");
			map.put("id", id);
		}
		List list=this.hibernateDao.queryObjects(hsql.toString(), map);
		if(!list.isEmpty()){
			return (SysProvince)list.get(0);
		}
		return null;
	}
}
