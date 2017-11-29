package cn.gc80.web.integral.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import cn.gc80.base.hibernate.HibernateDao;
import cn.gc80.datamodel.integral.IntContrast;
import cn.gc80.datamodel.integral.IntIntegral;

@Repository("integralDao")
public class IntegralDao {
	@Resource
	private HibernateDao hibernateDao;

	public IntContrast findContrastByCodeNo(String codeNo) {
		IntContrast intContrast = null;
		if(codeNo!=null&&!"".equals(codeNo)){
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			String hsql = " from IntContrast where codeNo=:codeNo";
			map.put("codeNo", codeNo);
			List<IntContrast> list=hibernateDao.queryObjects(hsql,map);
			if(!list.isEmpty()){
				intContrast=list.get(0);
			}
		}
		return intContrast;
	}

	public String saveIntIntegral(IntIntegral intIntegral) {
		try {
			return hibernateDao.insertObject(intIntegral);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public IntIntegral findIntegral(String userId, String codeNo, String time) {
		IntIntegral intIntegral=null;
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer(" from IntIntegral where delflag='02' ");
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(codeNo!=null&&!codeNo.equals("")){
			hsql.append( " and intContrast.codeNo=:codeNo");
			map.put("codeNo", codeNo);
		}
		if(time!=null&&!"".equals(time)){
			hsql.append(" and createtime like :time ");
			map.put("time", time+"%");
		}
		List list=hibernateDao.queryObjects(hsql.toString(), map);
		if(!list.isEmpty() && list!=null){
			intIntegral=(IntIntegral)list.get(0);
		}
		return intIntegral;
	}
	
	public IntContrast getContrast(String codeNo) {
		StringBuffer hsql = new StringBuffer(" from IntContrast where delflag='02' and status='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(codeNo!=null&&!codeNo.equals("")){
			hsql.append( " and codeNo=:codeNo");
			map.put("codeNo", codeNo);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null&&!list.isEmpty()){
			return (IntContrast)list.get(0);
		}
		return null;
	}
	
	public String saveIntegral(IntIntegral integral) {
		try {
			if(integral.getId()!=null&&!integral.getId().trim().equals("")){
				this.hibernateDao.updateObject(integral);
				return integral.getId();
			}
			else{
				return this.hibernateDao.insertObject(integral);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return "";
	}
	public IntIntegral findIntIntegral1(String userid, String codeid) {
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
}
