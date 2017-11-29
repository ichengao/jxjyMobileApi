package cn.gc80.web.business.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import cn.gc80.base.hibernate.HibernateDao;
import cn.gc80.datamodel.business.EOrder;
import cn.gc80.datamodel.business.EOrderDetail;
import cn.gc80.datamodel.business.ShoppingCart;
import cn.gc80.datamodel.exam.ExamUserQuiz;
import cn.gc80.datamodel.learning.LearnUserCourse;
import cn.gc80.datamodel.res.ResCourse;
import cn.gc80.datamodel.sysbase.SysUser;
import cn.gc80.datamodel.training.TrainClassCourse;

@Repository("orderDao")
public class OrderDao {
	@Resource
	private HibernateDao hibernateDao;

	public ResCourse findCourseSome(String courseid) {
		if (courseid != null && !courseid.equals("")) {
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			String hsql = " from ResCourse where id=:courseid";
			map.put("courseid", courseid);
			List list = this.hibernateDao.queryObjects(hsql,map);
			if(list!=null&&!list.isEmpty()){
				return (ResCourse)list.get(0);
			}
		}
		return null;
	}

	public ShoppingCart getShoppingCart(String userId,String classId, String courseId) {
		StringBuffer hsql = new StringBuffer(" from ShoppingCart where delflag='02' and isSubItem!='01'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(classId!=null&&!classId.equals("")){
			hsql.append( " and trainClass.id=:classId");
			map.put("classId", classId);
		}
		if(courseId!=null&&!courseId.equals("")){
			hsql.append( " and resCourse.id=:courseId");
			map.put("courseId", courseId);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null&&!list.isEmpty()){
			return (ShoppingCart)list.get(0);
		}
		return null;
	}

	public ShoppingCart getShoppingCart(String userId, String classId) {
		StringBuffer hsql = new StringBuffer(" from ShoppingCart where delflag='02' and isSubItem='01'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(classId!=null&&!classId.equals("")){
			hsql.append( " and trainClass.id=:classId");
			map.put("classId", classId);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null&&!list.isEmpty()){
			return (ShoppingCart)list.get(0);
		}
		return null;
	}

	public String saveShoppingCart(ShoppingCart shoppingCart) {
		try {
			if(shoppingCart.getId()!=null&&!shoppingCart.getId().trim().equals("")){
				this.hibernateDao.updateObject(shoppingCart);
				return shoppingCart.getId();
			}
			else{
				return this.hibernateDao.insertObject(shoppingCart);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return "";
	}
	
	public List<ShoppingCart> findShoppingCart(String userId) {
		StringBuffer hsql = new StringBuffer(" from ShoppingCart where delflag='02' and isSubItem='01'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}

	public List<ShoppingCart> findShoppingCartByClassId(String userId,String classId) {
		StringBuffer hsql = new StringBuffer(" from ShoppingCart where delflag='02' and isSubItem!='01'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(classId!=null&&!classId.equals("")){
			hsql.append( " and trainClass.id=:classId");
			map.put("classId", classId);
		}
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}
	
	public List<ShoppingCart> findShoppingCart(String userId,String classId) {
		StringBuffer hsql = new StringBuffer(" from ShoppingCart where delflag='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(classId!=null&&!classId.equals("")){
			hsql.append( " and trainClass.id=:classId");
			map.put("classId", classId);
		}
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}
	
	public List<ShoppingCart> findShoppingCartByUser(String userId) {
		StringBuffer hsql = new StringBuffer(" from ShoppingCart where delflag='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}
	
	public int doDelShoppingCartByCourseId(String userId,String courseId) {
		try {
			StringBuffer hsql = new StringBuffer(" update ShoppingCart  set delflag='01' where  isSubItem!='01'");
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			if(userId!=null&&!userId.equals("")){
				hsql.append( " and sysUser.id=:userId");
				map.put("userId", userId);
			}
			if(courseId!=null&&!courseId.equals("")){
				hsql.append( " and resCourse.id=:courseId");
				map.put("courseId", courseId);
			}
			return this.hibernateDao.executeSql(hsql.toString(), map);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int doDelShoppingCartByClassId(String userId,String classId) {
		try {
			StringBuffer hsql = new StringBuffer(" update ShoppingCart  set delflag='01' where  isSubItem='01'");
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			if(userId!=null&&!userId.equals("")){
				hsql.append( " and sysUser.id=:userId");
				map.put("userId", userId);
			}
			if(classId!=null&&!classId.equals("")){
				hsql.append( " and trainClass.id=:classId");
				map.put("classId", classId);
			}
			return this.hibernateDao.executeSql(hsql.toString(), map);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public EOrderDetail findOrdDetailClassSome(String userid,String classId){
		StringBuffer hsql = new StringBuffer("select new EOrderDetail(id,order.id) from EOrderDetail where delflag='02' and order.ordStatus='20' and isSubItem='01' ");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userid!=null&&!userid.equals("")){
			hsql.append( " and sysUser.id=:userid");
			map.put("userid", userid);
		}
		if(classId!=null&&!classId.equals("")){
			hsql.append( " and trainClass.id=:classId");
			map.put("classId", classId);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(),1,1,map);
		if(list!=null&&!list.isEmpty()){
			return (EOrderDetail)list.get(0);
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

	public long getAllOrderCount() {
		return this.hibernateDao.queryObjectCount(EOrder.class);
	}

	public List<EOrder> findOrderByStatus(String userId, String ordStatus) {
		StringBuffer hsql = new StringBuffer("select new EOrder(id,ordNo,ordTempNo,ordStatus) from EOrder where delflag='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(ordStatus!=null&&!ordStatus.equals("")){
			hsql.append( " and ordStatus=:ordStatus");
			map.put("ordStatus", ordStatus);
		}
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}

	public String saveOrder(EOrder order) {
		try {
			if(order.getId()!=null&&!order.getId().equals("")){
				this.hibernateDao.updateObject(order);
				return order.getId();
			}
			else{
				return this.hibernateDao.insertObject(order);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return "";
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

	public EOrder findOrderById(String id) {
		if(id!=null&&!"".equals(id)){
			return (EOrder) this.hibernateDao.queryObject(EOrder.class, id);
		}
		return null;
	}
	
	public List<EOrderDetail> findOrderDetail(String orderId){
		StringBuffer hsql = new StringBuffer(" from EOrderDetail where delflag='02' and isSubItem='01'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(orderId!=null&&orderId.trim().length()>0){
			hsql.append(" and order.id=:orderId");
			map.put("orderId", orderId);
		}
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}
	
	public List<EOrderDetail> findOrderDetail2(String orderId){
		StringBuffer hsql = new StringBuffer(" from EOrderDetail where delflag='02' and isSubItem!='01'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(orderId!=null&&orderId.trim().length()>0){
			hsql.append(" and order.id=:orderId");
			map.put("orderId", orderId);
		}
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}
	
	public List<EOrderDetail> findOrdDetailByOrdId(String id) {
		if(id!=null&&!"".equals(id)){
			StringBuffer hsql=new StringBuffer("from EOrderDetail where delflag='02' and order.id=:id" );
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			map.put("id", id);
			List<EOrderDetail> list = this.hibernateDao.queryObjects(hsql.toString(),map);
			return list;
		}
		return null;
	}

	public EOrderDetail findOrdClassDetailByPayStatus(String userId, String classId,String payStatus) {
		StringBuffer hsql = new StringBuffer(" from EOrderDetail where delflag='02' and isSubItem='01'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(userId!=null&&!userId.equals("")){
			hsql.append( " and sysUser.id=:userId");
			map.put("userId", userId);
		}
		if(classId!=null&&!classId.equals("")){
			hsql.append( " and trainClass.id=:classId");
			map.put("classId", classId.trim());
		}
		if(payStatus!=null&&!payStatus.equals("")){
			hsql.append( " and order.ordStatus=:payStatus");
			map.put("payStatus", payStatus);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null&&!list.isEmpty()){
			return (EOrderDetail)list.get(0);
		}
		return null;
	}

	//统计数据用，赋值值不对应
	public List<EOrderDetail> findAllOrdClass(String classId) {
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		StringBuffer hsql = new StringBuffer("select new EOrderDetail(sysUser.id) from EOrderDetail where isSubItem='01' and order.ordStatus='20' and trainClass.id=:classId group by sysUser.id");
		map.put("classId", classId);
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}

	public String updateUser(SysUser user){
		try {
			if(user.getId()!=null&&!user.getId().equals("")){
				String hsql = "update SysUser set balance="+user.getBalance()+",totalConsumeAmount="+user.getTotalConsumeAmount()+",totalIntegral="+user.getTotalIntegral()+",surplusIntegral="+user.getSurplusIntegral()+" where id='"+user.getId()+"'";
				this.hibernateDao.executeSql(hsql);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public String updateOrder(EOrder order) {
		try {
			if(order.getId()!=null&&!order.getId().equals("")){
				this.hibernateDao.updateObject(order);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return order.getId();
	}
	
	public List findCoursewareByCourseId(String courseid){
		StringBuffer hsql = new StringBuffer("select new ResCouCw(id,resCourseware.id,resCourseware.cwAllowAttempt) from ResCouCw cc where cc.resCourse.id=:courseid");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		map.put("courseid", courseid);
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		return list;
	}
	
	public LearnUserCourse findUserCourseStudyStatus(String userid,String courseid,String coursewareid){
		if(userid!=null&&coursewareid!=null){
			StringBuffer hsql = new StringBuffer("select new LearnUserCourse(id,firsttime,lasttime,studystatus,status,hasCredit,delflag) from LearnUserCourse where sysUser.id=:userid");
			Map<String, String> map= new ConcurrentHashMap<String, String>();
			map.put("userid", userid);
			if(courseid!=null&&!courseid.equals("")){
				hsql.append( " and resCourse.id=:courseid");
				map.put("courseid", courseid);
			}
			if(coursewareid!=null&&!coursewareid.equals("")){
				hsql.append( " and resCourseware.id=:coursewareid");
				map.put("coursewareid", coursewareid);
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
	
	public String saveUserCourse(LearnUserCourse usercourse) {
		// TODO Auto-generated method stub
		try {
			if(usercourse.getId()!=null&&!usercourse.getId().trim().equals("")){
				this.hibernateDao.updateObject(usercourse);
				return usercourse.getId();
			}
			else{
				return this.hibernateDao.insertObject(usercourse);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "";
	}
	
	public String updateUserCourseEnsure(LearnUserCourse uc) {
		try {
			if(uc.getId()!=null&&!uc.getId().trim().equals("")){
				String hsql = "update LearnUserCourse set lasttime='"+uc.getLasttime()+"',firsttime='"+uc.getFirsttime()+"',studystatus='"+uc.getStudystatus()+"',status='"+uc.getStatus()+"',hasCredit="+uc.getHasCredit()+",delflag='"+uc.getDelflag()+"' where id='"+uc.getId()+"'";
				this.hibernateDao.executeSql(hsql);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public List findQuiz(String quizType,String sourceid,String coursewareid){
		StringBuffer hsql = new StringBuffer(" from ExamQuiz where delfalg='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(coursewareid!=null&&!coursewareid.equals("")){
			hsql.append(" and resCourseware.id=:coursewareid");
			map.put("coursewareid",coursewareid);
		}
		if(sourceid!=null&&!sourceid.equals("")){
			hsql.append(" and (resCourse.id=:sourceid or trainProject.id=:sourceid)");
			map.put("sourceid",sourceid);
		}
		if(quizType!=null&&!quizType.equals("")){
			hsql.append(" and quizType=:quizType");
			map.put("quizType",quizType);
		}
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}
	
	public String insertUserQuiz(ExamUserQuiz bean) throws HibernateException, SQLException {
		return this.hibernateDao.insertObject(bean);
	}

	public EOrder findOrderSomeByTmpNo(String orderNo) {
		StringBuffer hsql = new StringBuffer(" from EOrder where delflag='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(orderNo!=null&&!orderNo.equals("")){
			hsql.append( " and (ordNo=:orderNo or ordTempNo=:orderNo)");
			map.put("orderNo", orderNo);
		}
		List list = this.hibernateDao.queryObjects(hsql.toString(),map);
		if(list!=null&&!list.isEmpty()){
			EOrder order = (EOrder)list.get(0);
			return order;
		}
		return null;
	}

	public List<EOrder> findEOrder(String userId, String ordStatus) {
		StringBuffer hsql = new StringBuffer(" from EOrder where sysUser.id=:userId and delflag='02'");
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		map.put("userId", userId);
		if(ordStatus!=null&&!ordStatus.equals("")){
			hsql.append( " and ordStatus=:ordStatus");
			map.put("ordStatus", ordStatus);
		}
		hsql.append( " order by createtime desc");
		return this.hibernateDao.queryObjects(hsql.toString(),map);
	}
	
	public List<EOrderDetail> findOrderDetailById(String orderId) {
		StringBuffer hsql=new StringBuffer("select new EOrderDetail(id,resCourse.CName,trainClass.className) from EOrderDetail where delflag='02' and isSubItem='02'" );
		Map<String, String> map= new ConcurrentHashMap<String, String>();
		if(orderId!=null&&orderId.trim().length()>0){
			hsql.append(" and order.id=:orderId");
			map.put("orderId", orderId);
		}
		return this.hibernateDao.queryObjects(hsql.toString(), map);
	}
	
}
