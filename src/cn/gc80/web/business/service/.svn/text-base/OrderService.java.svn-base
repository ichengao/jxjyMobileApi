package cn.gc80.web.business.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import cn.gc80.base.util.ConfigUtil;
import cn.gc80.base.util.DateTime;
import cn.gc80.datamodel.business.EDepositLog;
import cn.gc80.datamodel.business.EOrder;
import cn.gc80.datamodel.business.EOrderDetail;
import cn.gc80.datamodel.business.EOrderLog;
import cn.gc80.datamodel.business.ShoppingCart;
import cn.gc80.datamodel.exam.ExamPaper;
import cn.gc80.datamodel.exam.ExamQuiz;
import cn.gc80.datamodel.exam.ExamUserQuiz;
import cn.gc80.datamodel.integral.IntContrast;
import cn.gc80.datamodel.integral.IntIntegral;
import cn.gc80.datamodel.learning.LearnUserCourse;
import cn.gc80.datamodel.res.ResCouCw;
import cn.gc80.datamodel.res.ResCourse;
import cn.gc80.datamodel.sysbase.SysUser;
import cn.gc80.datamodel.training.TrainClass;
import cn.gc80.datamodel.training.TrainClassCourse;
import cn.gc80.web.business.dao.OrderDao;
import cn.gc80.web.integral.service.IntegralService;
import cn.gc80.web.tclass.dao.ClassDao;
import cn.gc80.web.user.dao.UserDao;

@Service("orderService")
public class OrderService {
	@Resource
	private OrderDao orderDao;
	@Resource
	private ClassDao classDao;
	@Resource
	private UserDao userDao;
	@Resource
	private IntegralService integralService;
	
	public String addShoppingCart(String userId,String classId, String courseIds) {
		//判断是否需要按要求学时报名
		if(classId!=null&&!"".equals(classId)&&courseIds!=null&&!"".equals(courseIds)){
			TrainClass tClass=this.classDao.getTrainClassById(classId);
			double classCredit=0d;
			if(tClass!=null){
				if(tClass.getIsAskHours()!=null&&"02".equals(tClass.getIsAskHours())){
					classCredit=tClass.getClassCredithour();
					double selCourseCredit=0d;
					String[] tIds=courseIds.split(",");
					for(int i=0;i<tIds.length;i++){
						ResCourse c=this.orderDao.findCourseSome(tIds[i]);
						if(c!=null){
							selCourseCredit+=c.getCCredithour();
						}
					}
					if(selCourseCredit<classCredit){
						return "2";
					}
				}
			}
		}
		
		SysUser user=null;
		if(userId!=null&&!"".equals(userId)){
			user=this.userDao.findUserByUserId(userId);
		}else{
			user=new SysUser();
		}
		if(classId!=null&&!"".equals(classId)){
			//判断课程是否在购物车中
			String[] tIds=courseIds.split(",");
			for(int i=0;i<tIds.length;i++){
				ShoppingCart c=this.orderDao.getShoppingCart(userId,"",tIds[i]);
				if(c!=null){
					return "3";
				}
			}
			//加入购物车中
			ShoppingCart cart=this.orderDao.getShoppingCart(userId,classId);
			if(cart==null){
				ShoppingCart shoppingCart=new ShoppingCart();
				TrainClass trainClass=new TrainClass();
				trainClass.setId(classId);
				shoppingCart.setTrainClass(trainClass);
				shoppingCart.setSysUser(user);
				shoppingCart.setDelflag("02");
				shoppingCart.setCreatetime(DateTime.formatDateTime(new Date()));
				shoppingCart.setProductType("01");
				shoppingCart.setIsSubItem("01");
				this.orderDao.saveShoppingCart(shoppingCart);
			}
			if(courseIds!=null&&!"".equals(courseIds)){
				String[] temIds=courseIds.split(",");
				for(int i=0;i<temIds.length;i++){
					ShoppingCart sCart=this.orderDao.getShoppingCart(userId,classId,temIds[i]);
					if(sCart==null){
						ShoppingCart sc=new ShoppingCart();
						TrainClass tc=new TrainClass();
						tc.setId(classId);
						sc.setTrainClass(tc);
						ResCourse course=new ResCourse();
						course.setId(temIds[i]);
						sc.setResCourse(course);
						sc.setSysUser(user);
						sc.setDelflag("02");
						sc.setCreatetime(DateTime.formatDateTime(new Date()));
						sc.setProductType("01");
						sc.setIsSubItem("02");
						String result=this.orderDao.saveShoppingCart(sc);
						if(result==null || "".equals(result)){
							return "0";
						}
					}
				}
			}
		}
		return "1";
	}
	
	public String addShoppingCart(String userId,String classId) {
		String result="0";
		SysUser user=null;
		if(userId!=null&&!"".equals(userId)){
			user=this.userDao.findUserByUserId(userId);
		}else{
			user=new SysUser();
		}
		if(classId!=null&&!"".equals(classId)){
			//加入购物车中
			EOrderDetail od = this.orderDao.findOrdDetailClassSome(userId, classId);
			if(od==null){
				ShoppingCart cart=this.orderDao.getShoppingCart(userId,classId);
				if(cart==null){
					ShoppingCart shoppingCart=new ShoppingCart();
					TrainClass trainClass=new TrainClass();
					trainClass.setId(classId);
					shoppingCart.setTrainClass(trainClass);
					shoppingCart.setSysUser(user);
					shoppingCart.setDelflag("02");
					shoppingCart.setCreatetime(DateTime.formatDateTime(new Date()));
					shoppingCart.setProductType("01");
					shoppingCart.setIsSubItem("01");
					this.orderDao.saveShoppingCart(shoppingCart);
				}
				List tccList = this.orderDao.findClassCourseByClassId(classId);
				if(tccList!=null&&!tccList.isEmpty()){
					for(int i=0;i<tccList.size();i++){
						TrainClassCourse tcCourse = (TrainClassCourse)tccList.get(i);
						ShoppingCart sCart=this.orderDao.getShoppingCart(userId,classId,tcCourse.getResCourse().getId());
						if(sCart==null){
							ShoppingCart sc=new ShoppingCart();
							TrainClass tc=new TrainClass();
							tc.setId(classId);
							sc.setTrainClass(tc);
							ResCourse course=new ResCourse();
							course.setId(tcCourse.getResCourse().getId());
							sc.setResCourse(course);
							sc.setSysUser(user);
							sc.setDelflag("02");
							sc.setCreatetime(DateTime.formatDateTime(new Date()));
							sc.setProductType("01");
							sc.setIsSubItem("02");
							this.orderDao.saveShoppingCart(sc);
						}
					}
				}
				result="1";
			}else{
				result="2";
			}
		}
		return result;
	}

	public List<ShoppingCart> findShoppingCart(String userId) {
		List<ShoppingCart> sCartList=this.orderDao.findShoppingCart(userId);
		for(int i=0;i<sCartList.size();i++){
			ShoppingCart sCart=sCartList.get(i);
			List<ShoppingCart> scList=this.orderDao.findShoppingCartByClassId(userId,sCart.getTrainClass().getId());
			Integer totalCourse=scList.size();
			Long totalCredit=0l;
			Double totalPrice=0d;
			
			for(int j=0;j<scList.size();j++){
				ShoppingCart sc=scList.get(j);
				totalCredit=totalCredit+sc.getResCourse().getCCredithour();
				totalPrice=totalPrice+sc.getResCourse().getCPrice();
			}
			sCart.setTotalCourse(totalCourse);
			sCart.setTotalCredit(totalCredit);
			sCart.setTotalPrice(totalPrice);
			if(sCart.getTrainClass().getTrainProject().getSysCode().getCodeNo().startsWith("0703")){
				sCart.setTotalPrice(sCart.getTrainClass().getClassPrice());
			}
		}
		return sCartList;
	}

	public String doDelShoppingCartByClassIds(String userId, String classIds) {
		if(classIds!=null&&!"".equals(classIds)){
			String[] classId=classIds.split(",");
			for(int i=0;i<classId.length;i++){
				List<ShoppingCart> sCartList=this.orderDao.findShoppingCart(userId, classId[i]);
				for(int j=0;j<sCartList.size();j++){
					ShoppingCart shoppingCart=sCartList.get(j);
					shoppingCart.setDelflag("01");
					String result=this.orderDao.saveShoppingCart(shoppingCart);
					if(result==null) return "";
				}
			}
			return "1";
		}
		return "0";
	}

	public List<ShoppingCart> findShoppingCartByClassId(String userId,String classId) {
		return this.orderDao.findShoppingCartByClassId(userId,classId);
	}

	public String doDelShoppingCartByCourseId(String userId, String courseId) {
		ShoppingCart shoppingCart=this.orderDao.getShoppingCart(userId, "",courseId);
		shoppingCart.setDelflag("01");
		String result=this.orderDao.saveShoppingCart(shoppingCart);
		if(result!=null&&!"".equals(result)){
			List<ShoppingCart> scList=this.orderDao.findShoppingCartByClassId(userId,shoppingCart.getTrainClass().getId());
			if(scList.size()==0){
				ShoppingCart sCart=this.orderDao.getShoppingCart(userId, shoppingCart.getTrainClass().getId());
				sCart.setDelflag("01");
				return this.orderDao.saveShoppingCart(sCart);
			}else{
				return "1";
			}
		}else{
			return "1";
		}
	}

	//生成订单号
	public String getGenerateOrderNo(String type){
		String ordNo=new SimpleDateFormat("yyyyMMdd").format(new Date());
		ordNo=ordNo+new DecimalFormat("00000000").format(this.orderDao.getAllOrderCount()+1);
		ordNo=ordNo+type;
		return ordNo;
	}
	
	//生成订单
	public String generateOrder(String userId,String classIds,String equipment) {
		List<EOrder> orderList=this.orderDao.findOrderByStatus(userId, "01");
		if(orderList.size()>0){
			return "3";
		}
		SysUser user=this.userDao.findUserByUserId(userId);
		if(classIds!=null&&!"".equals(classIds)){
			//判断购物车是否为空
			//计算订单总金额
			double totalPrice=0d;
			String[] tIds=classIds.split(",");
			for(int i=0;i<tIds.length;i++){
				ShoppingCart sCart=this.orderDao.getShoppingCart(userId, tIds[i]);
				if(sCart==null){
					return "2";
				}
				else if(sCart.getTrainClass().getTrainProject().getSysCode().getCodeNo().startsWith("0703")){
					totalPrice = totalPrice + sCart.getTrainClass().getClassPrice();
				}
			}
			
			for(int i=0;i<tIds.length;i++){
				List<ShoppingCart> sCartList=this.orderDao.findShoppingCartByClassId(userId, tIds[i]);
				for (int j=0;j<sCartList.size();j++){
					ShoppingCart sCart=sCartList.get(j);
					if(sCart!=null&&sCart.getResCourse()!=null&&!sCart.getTrainClass().getTrainProject().getSysCode().getCodeNo().startsWith("0703")){
						totalPrice=totalPrice+sCart.getResCourse().getCPrice();
					}
				}
			}
			//生成主订单
			EOrder order =new EOrder();
			String ordNo=this.getGenerateOrderNo("01");
			order.setOrdNo(ordNo);
			order.setOrdName(user.getRealname());
			order.setOrdMobile(user.getMobile());
			order.setOrdPhone(user.getPhone());
			order.setOrdTempNo(ordNo);//临时订单
			order.setOrdStatus("01");
			order.setOrdDiscount(100l);
			order.setOrdMoneysum(totalPrice);
			order.setOrdTotalMoney(totalPrice);
			order.setOrdAccountMoney(0d);
			order.setOrdIntegralMoney(0d);
			order.setOrdIntegral(0l);
			order.setCreatetime(DateTime.formatDateTime(new Date()));
			order.setOrdPaytime("");
			order.setOrdSubmitTime("");//支付时间默认不填写
			order.setLastedittime(DateTime.formatDateTime(new Date()));
			order.setCreator(user.getId());
			order.setLasteditor(user.getId());
			order.setDelflag("02");
			order.setOrdOffers(0d);
			order.setOrdPaytype("00");
			order.setOrdEmail(user.getEmail());
			order.setSysUser(user);
			order.setOrdPaySource(equipment);
			order.setOrdType("01");
			order.setInvoiceClainType("01");
			String orderId=this.orderDao.saveOrder(order);
			order.setId(orderId);
			//生成订单详情
			String[] tempIds=classIds.split(",");
			for(int i=0;i<tempIds.length;i++){
				ShoppingCart sCart=this.orderDao.getShoppingCart(userId, tempIds[i]);
				EOrderDetail detail=new EOrderDetail();
				detail.setOrder(order);
				detail.setTrainClass(sCart.getTrainClass());
				detail.setSysUser(order.getSysUser());
				detail.setIsSubItem("01");
				detail.setAmount(new Long(1));
				detail.setDelflag("02");
				detail.setYear(sCart.getTrainClass().getClassYear());
				detail.setHascredit(0l);
				detail.setStudystatus("01");
				detail.setIsExam(sCart.getTrainClass().getIsExam());
				
				Long totalCredit=0l;
				double tPrice=0d;
				List<ShoppingCart> sCartList=this.orderDao.findShoppingCartByClassId(userId, tempIds[i]);
				for(int j=0;j<sCartList.size();j++){
					ResCourse course=sCartList.get(j).getResCourse();
					EOrderDetail temp=new EOrderDetail();
					temp.setOrder(order);
					temp.setTrainClass(sCart.getTrainClass());
					temp.setSysUser(order.getSysUser());
					temp.setIsSubItem("02");
					temp.setAmount(new Long(1));
					temp.setPrice(course.getCPrice());
					temp.setDelflag("02");
					temp.setYear(sCart.getTrainClass().getClassYear());
					temp.setHascredit(0l);
					temp.setCredit(course.getCCredithour());
					temp.setStudystatus("01");
					temp.setIsExam(course.getIsExam());
					temp.setResCourse(course);
					totalCredit+=course.getCCredithour();
					tPrice+=course.getCPrice();
					this.orderDao.saveOrderDetail(temp);
				}
				detail.setCredit(totalCredit);
				detail.setPrice(tPrice);
				if(sCart.getTrainClass().getTrainProject().getSysCode().getCodeNo().startsWith("0703")){
					detail.setPrice(sCart.getTrainClass().getClassPrice());
				}
				this.orderDao.saveOrderDetail(detail);
				//清空购物车
				this.doDelShoppingCartByClassIds(userId, tempIds[i]);
			}
			return orderId;
		}
		return "";
	}

	public EOrder findOrderById(String id) {
		return this.orderDao.findOrderById(id);
	}

	public List<EOrderDetail> findOrderDetail(String orderId) {
		return this.orderDao.findOrderDetail(orderId);
	}

	public EOrder savePayByAccount(SysUser user,EOrder order,String ip,String equipment){
		//-------------积分-------------
		//总积分
		if(user.getTotalIntegral()==null){
			user.setTotalIntegral(0l);
		}
		//剩余积分
		if(user.getSurplusIntegral()==null){
			user.setSurplusIntegral(0l);
		}
		//获得总积分
		Long surplusIntegral=0l;
		//会员等级
		Long integral = user.getTotalIntegral();
		Double rate=integralService.findLevelRateByTotalIntegral(integral);
		
		IntContrast contrast=integralService.getContrast("gc019");
		if(contrast!=null &&!"".equals(contrast)){
			String startTime=contrast.getStarttime();
			String endTime=contrast.getEndtime();
			Long integral_val=contrast.getIntegral();
			//判断是否有时间限制
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(startTime!=null && endTime!=null){
				Date d;
				try {
					d = df.parse(DateTime.formatDateTime(new Date()));
					Date sta = df.parse(startTime);
					Date end = df.parse(endTime);
					long dl1 = d.getTime()-sta.getTime();
					long dl2 = end.getTime()-d.getTime();
					if(dl1>=0&& dl2>=0){
						Long con_rate =contrast.getRate();
						integral_val=integral_val*con_rate;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			surplusIntegral=new BigDecimal(order.getOrdAccountMoney()*integral_val*rate).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
		}
		//如果班级报名过则不给班级获取积分
		List<EOrderDetail> list=this.orderDao.findOrderDetail(order.getId());
		for(int i=0;i<list.size();i++){
			EOrderDetail od=list.get(i);
			EOrderDetail detail=this.orderDao.findOrdClassDetailByPayStatus(user.getId(), od.getTrainClass().getId(), "20");
			if(detail==null){
				surplusIntegral=surplusIntegral+new BigDecimal(od.getTrainClass().getGiveIntegral()*rate).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
				//增加报名人数
				TrainClass tclass=this.classDao.getTrainClassById(od.getTrainClass().getId());
				if(tclass!=null){
					tclass.setClassEnrolNumber(tclass.getClassEnrolNumber()+1);
					this.classDao.saveClass(tclass);
				}
				//若之前没有报名同一班级，则增加学号
				String studyNo=list.get(i).getTrainClass().getClassNo()+new DecimalFormat("00000").format(this.orderDao.findAllOrdClass(od.getTrainClass().getId()).size()+1);
				od.setStudyNo(studyNo);
			}else{
				//若之前报名同一班级，则学号一致
				od.setStudyNo(detail.getStudyNo());
			}
			this.orderDao.saveOrderDetail(od);
		}
		user.setTotalIntegral(user.getTotalIntegral()+surplusIntegral);
		user.setSurplusIntegral(user.getSurplusIntegral()+surplusIntegral);
		this.orderDao.updateUser(user);
		//获取积分记录
		if(surplusIntegral>0){
			IntContrast intContrast=this.integralService.getContrast("gc019");
			IntIntegral inl=new IntIntegral();
			inl.setIntContrast(intContrast);
			inl.setSysUser(user);
			inl.setIntegral(surplusIntegral);
			inl.setEquipment(equipment);
			inl.setDescribe(order.getOrdNo());
			inl.setIp(ip);
			inl.setCreatetime(DateTime.formatDateTime(new Date()));
			inl.setDelflag("02");
			inl.setCurrentIntegral(user.getSurplusIntegral());
			inl.setType("01");
			this.integralService.saveIntegral(inl);
		}
		//修改订单
		order.setOrdStatus("20");
		order.setOrdPaytime(DateTime.formatDateTime(new Date()));
		order.setExpressFlag("01");
		String orderid = this.orderDao.updateOrder(order);
		order.setId(orderid);
		
		//插入订单详情
		List orderDetailList = this.orderDao.findOrderDetail2(order.getId());
		order.setOrderDetailList(orderDetailList);
		if(orderDetailList!=null&&orderDetailList.size()>0){
			for (int i=0;i<orderDetailList.size();i++) {
				EOrderDetail detail = (EOrderDetail)orderDetailList.get(i);
				if(detail.getResCourse()!=null){
					this.saveUserCourse(detail.getResCourse(),user);
				}
				if(detail.getIsExam()!=null&&"02".equals(detail.getIsExam())){
					this.saveUserQuiz(detail);
				}
			}
		}
		
		//余额
		if(user.getBalance()==null){
			user.setBalance(0d);
		}
		user.setBalance(new BigDecimal(user.getBalance()- order.getOrdTotalMoney()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//总消费额
		if(user.getTotalConsumeAmount()==null){
			user.setTotalConsumeAmount(0d);
		}
		user.setTotalConsumeAmount(user.getTotalConsumeAmount()+order.getOrdTotalMoney());
		this.orderDao.updateUser(user);
		//账户余额使用记录
		EDepositLog delpositLog=new EDepositLog();
		delpositLog.setSysUser(user);
		delpositLog.setDepositAmount(order.getOrdTotalMoney());
		delpositLog.setDepositTime(DateTime.formatDateTime(new Date()));
		delpositLog.setDepositOperator(user.getId());
		delpositLog.setDepositType("08");
		delpositLog.setDepositStatus("02");
		delpositLog.setTime(DateTime.formatDateTime(new Date()));
		delpositLog.setType("02");
		delpositLog.setEquipment(equipment);
		delpositLog.setIp(ip);
		delpositLog.setDescribe(order.getOrdNo());
		delpositLog.setBalance(user.getBalance());
		this.userDao.addDeposit(delpositLog);
		
		//推荐人
		SysUser recommender=user.getRecommender();
		if(recommender!=null){
			//获取积分
			long surplusIntegral_c=0l;
			IntContrast intC=this.integralService.getContrast("gc027");
			if(intC!=null){
				String startTime=intC.getStarttime();
				String endTime=intC.getEndtime();
				Long integral_val_c=intC.getIntegral();
				//判断是否有时间限制
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(startTime!=null && endTime!=null){
					Date d;
					try {
						d = df.parse(DateTime.formatDateTime(new Date()));
						Date sta = df.parse(startTime);
						Date end = df.parse(endTime);
						long dl1 = d.getTime()-sta.getTime();
						long dl2 = end.getTime()-d.getTime();
						if(dl1>=0&& dl2>=0){
							integral_val_c=integral_val_c*intC.getRate();
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				surplusIntegral_c=new BigDecimal(order.getOrdTotalMoney()*integral_val_c).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
				
				recommender.setTotalIntegral(recommender.getTotalIntegral()+surplusIntegral_c);
				recommender.setSurplusIntegral(recommender.getSurplusIntegral()+surplusIntegral_c);
				this.orderDao.updateUser(recommender);
				//推荐人积分记录
				if(surplusIntegral_c>0){
					IntIntegral inl_c=new IntIntegral();
					inl_c.setIntContrast(intC);
					inl_c.setSysUser(recommender);
					inl_c.setIntegral(surplusIntegral_c);
					inl_c.setEquipment(equipment);
					inl_c.setDescribe(user.getRealname()+"消费获取积分");
					inl_c.setIp(ip);
					inl_c.setCreatetime(DateTime.formatDateTime(new Date()));
					inl_c.setDelflag("02");
					inl_c.setCurrentIntegral(recommender.getSurplusIntegral());
					inl_c.setType("01");
					this.integralService.saveIntegral(inl_c);
				}
			}
		}
		
		order.setSysUser(user);
		return order;
	}
	
	public EOrder savePayByIntegral(SysUser user,EOrder order,String ip,String equipment){
		//修改用户积分
		long surplusIntegral=new BigDecimal(new Double(order.getOrdTotalMoney()*30)).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
		user.setSurplusIntegral(user.getSurplusIntegral()-surplusIntegral);
		this.orderDao.updateUser(user);
		//积分记录表
		if(surplusIntegral>0){
			IntIntegral integral=new IntIntegral();
			IntContrast intContrast=this.integralService.getContrast("sy002");
			integral.setIntContrast(intContrast);
			integral.setSysUser(user);
			integral.setIntegral(surplusIntegral);
			integral.setEquipment(equipment);
			integral.setDescribe(order.getOrdNo());
			integral.setIp(ip);
			integral.setCreatetime(DateTime.formatDateTime(new Date()));
			integral.setDelflag("02");
			integral.setCurrentIntegral(user.getSurplusIntegral());
			integral.setType("02");
			this.integralService.saveIntegral(integral);
		}
		//赠送积分
		//会员等级
		Long integral = user.getTotalIntegral();
		Double rate=integralService.findLevelRateByTotalIntegral(integral);
		//如果班级报名过则不给班级获取积分
		long sIntegral=0l;
		List<EOrderDetail> list=this.orderDao.findOrderDetail(order.getId());
		for(int i=0;i<list.size();i++){
			EOrderDetail od=list.get(i);
			EOrderDetail detail=this.orderDao.findOrdClassDetailByPayStatus(user.getId(), od.getTrainClass().getId(), "20");
			if(detail==null){
				sIntegral=sIntegral+new BigDecimal(od.getTrainClass().getGiveIntegral()*rate).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
				//增加报名人数
				TrainClass tclass=this.classDao.getTrainClassById(od.getTrainClass().getId());
				if(tclass!=null){
					tclass.setClassEnrolNumber(tclass.getClassEnrolNumber()+1);
					this.classDao.saveClass(tclass);
				}
				//若之前没有报名同一班级，则增加学号
				String studyNo=list.get(i).getTrainClass().getClassNo()+new DecimalFormat("00000").format(this.orderDao.findAllOrdClass(od.getTrainClass().getId()).size()+1);
				od.setStudyNo(studyNo);
			}else{
				//若之前报名同一班级，则学号一致
				od.setStudyNo(detail.getStudyNo());
			}
			this.orderDao.saveOrderDetail(od);
		}
		if(sIntegral>0){
			user.setTotalIntegral(user.getTotalIntegral()+sIntegral);
			user.setSurplusIntegral(user.getSurplusIntegral()+sIntegral);
			this.orderDao.updateUser(user);
			IntContrast intCon=this.integralService.getContrast("gc019");
			IntIntegral inl=new IntIntegral();
			inl.setIntContrast(intCon);
			inl.setSysUser(user);
			inl.setIntegral(sIntegral);
			inl.setEquipment(equipment);
			inl.setDescribe(order.getOrdNo());
			inl.setIp(ip);
			inl.setCreatetime(DateTime.formatDateTime(new Date()));
			inl.setDelflag("02");
			inl.setCurrentIntegral(user.getSurplusIntegral());
			inl.setType("01");
			this.integralService.saveIntegral(inl);
		}
		
		//修改订单
		order.setOrdStatus("20");
		order.setOrdPaytime(DateTime.formatDateTime(new Date()));
		order.setExpressFlag("01");
		String orderid = this.orderDao.updateOrder(order);
		order.setId(orderid);
		
		//插入订单详情
		List orderDetailList = this.orderDao.findOrderDetail2(order.getId());
		order.setOrderDetailList(orderDetailList);
		if(orderDetailList!=null&&orderDetailList.size()>0){
			for (int i=0;i<orderDetailList.size();i++) {
				EOrderDetail detail = (EOrderDetail)orderDetailList.get(i);
				if(detail.getResCourse()!=null){
					this.saveUserCourse(detail.getResCourse(),user);
				}
				if(detail.getIsExam()!=null&&"02".equals(detail.getIsExam())){
					this.saveUserQuiz(detail);
				}
			}
		}
		order.setSysUser(user);
		
		//推荐人
		SysUser recommender=user.getRecommender();
		if(recommender!=null){
			//获取积分
			long surplusIntegral_c=0l;
			IntContrast intC=this.integralService.getContrast("gc027");
			if(intC!=null){
				String startTime=intC.getStarttime();
				String endTime=intC.getEndtime();
				Long integral_val_c=intC.getIntegral();
				//判断是否有时间限制
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(startTime!=null && endTime!=null){
					Date d;
					try {
						d = df.parse(DateTime.formatDateTime(new Date()));
						Date sta = df.parse(startTime);
						Date end = df.parse(endTime);
						long dl1 = d.getTime()-sta.getTime();
						long dl2 = end.getTime()-d.getTime();
						if(dl1>=0&& dl2>=0){
							integral_val_c=integral_val_c*intC.getRate();
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				surplusIntegral_c=new BigDecimal(order.getOrdTotalMoney()*integral_val_c).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
				
				recommender.setTotalIntegral(recommender.getTotalIntegral()+surplusIntegral_c);
				recommender.setSurplusIntegral(recommender.getSurplusIntegral()+surplusIntegral_c);
				this.orderDao.updateUser(recommender);
				//推荐人积分记录
				if(surplusIntegral_c>0){
					IntIntegral inl_c=new IntIntegral();
					inl_c.setIntContrast(intC);
					inl_c.setSysUser(recommender);
					inl_c.setIntegral(surplusIntegral_c);
					inl_c.setEquipment(equipment);
					inl_c.setDescribe(user.getRealname()+"消费获取积分");
					inl_c.setIp(ip);
					inl_c.setCreatetime(DateTime.formatDateTime(new Date()));
					inl_c.setDelflag("02");
					inl_c.setCurrentIntegral(recommender.getSurplusIntegral());
					inl_c.setType("01");
					this.integralService.saveIntegral(inl_c);
				}
			}
		}
		return order;
	}
	
	public void saveUserCourse(ResCourse course,SysUser user){
		List cwList = this.orderDao.findCoursewareByCourseId(course.getId());
		if(cwList!=null&&!cwList.isEmpty()){
			for (int i=0;i<cwList.size();i++) {
				ResCouCw cc = (ResCouCw)cwList.get(i);
				LearnUserCourse uc = null;
				uc = this.orderDao.findUserCourseStudyStatus(user.getId(), course.getId(), cc.getResCourseware().getId());
				if(uc == null){
					uc = new LearnUserCourse();
					uc.setResCourse(course);
					uc.setResCourseware(cc.getResCourseware());
					uc.setSysUser(user);
					uc.setEnsuretime(DateTime.formatDateTime(new Date()));
					uc.setHours(0l);
					uc.setTime(0l);
					uc.setHasCredit(0l);
					uc.setStudystatus("01");
					uc.setDuration(course.getCDuration());
					uc.setStatus("02");
					uc.setExamscore(0d);
					uc.setAllowAttempt(cc.getResCourseware().getCwAllowAttempt());
					this.orderDao.saveUserCourse(uc);
				}else{
					uc.setStatus("02");
					uc.setDelflag("02");
					this.orderDao.updateUserCourseEnsure(uc);
				}
				
			}
		}
	}
	
	public void saveUserQuiz(EOrderDetail detail){
		List list = new ArrayList();
		if(detail.getResCourse()!=null){
			list = this.orderDao.findQuiz("", detail.getResCourse().getId(), "");
		}
		if(detail.getTrainProject()!=null){
			list = this.orderDao.findQuiz("", detail.getTrainProject().getId(), "");
		}
		if(list!=null&&!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				ExamQuiz quiz = (ExamQuiz)list.get(i);
				String[] paper = quiz.getPaperids().split(",");

				ExamUserQuiz userQuiz=new ExamUserQuiz();
				String paperId1 = paper[(int) (Math.ceil(Math.random()* (paper.length)) - 1)];
				
				ExamPaper examPaper = new ExamPaper();
				examPaper.setId(paperId1);
				userQuiz.setExamQuiz(quiz);
				userQuiz.setExamPaper(examPaper);
				userQuiz.setSysUser(detail.getSysUser());
				userQuiz.setSequence((long)1);
				userQuiz.setStarttime(DateTime.formatDateTime(new Date()));
				userQuiz.setFinishtime(DateTime.formatDateTime(new Date()));
				userQuiz.setUsedtime((long) 0);
				userQuiz.setGrade((double) 0);
				userQuiz.setStatus("00");
				try {
					String uqid = this.orderDao.insertUserQuiz(userQuiz);
				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public String generateOrderNo(String userName) {
		String orderNo = "";
		String curDateStr = "";
		String midId = "";
		try {
			curDateStr = DateTime.getTime(System.currentTimeMillis(), "yyyyMMdd");
			midId = "";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		midId = ConfigUtil.getConfig("v_mid");
		orderNo = curDateStr+"-"+midId+"-"+userName+"-"+DateTime.getCurrentStandardTime2();
		return orderNo;
	}

	public String updateOrder(EOrder order) {
		return this.orderDao.updateOrder(order);
	}

	public EOrder updateOrder(String orderid,String v_pstatus,String ip,String payTime){
		EOrder order =this.orderDao.findOrderSomeByTmpNo(orderid);
		if(order!=null){
			SysUser user = order.getSysUser();
			if(!order.getOrdStatus().equals("20")&&v_pstatus.equals("20")){
				//使用账户金额
				double accountMoney=order.getOrdAccountMoney();
				//使用积分
				Long ii=order.getOrdIntegral();
				//判断账户余额是否够用
				if(new BigDecimal(user.getBalance()-accountMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()<0){
					return order;
				}
				//判断积分是否够用
				if(user.getSurplusIntegral()-ii<0){
					return order;
				}
				List detailList = this.orderDao.findOrderDetail2(order.getId());
				if(detailList!=null&&!detailList.isEmpty()){
					for (int i=0;i<detailList.size();i++) {
						EOrderDetail detail = (EOrderDetail)detailList.get(i);
						if(detail.getResCourse()!=null){
							this.saveUserCourse(detail.getResCourse(), user);
						}
						if(detail.getIsExam()!=null&&"02".equals(detail.getIsExam())){
							this.saveUserQuiz(detail);
						}
					}
				}
				//总消费额
				if(user.getTotalConsumeAmount()==null){
					user.setTotalConsumeAmount(0d);
				}
				user.setTotalConsumeAmount(user.getTotalConsumeAmount()+ new Double(order.getOrdMoneysum()));
				
				//是否使用余额
				if(accountMoney>0){
					user.setBalance(new BigDecimal(user.getBalance()-accountMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					this.orderDao.updateUser(user);
					EDepositLog delpositLog=new EDepositLog();
					delpositLog.setSysUser(user);
					delpositLog.setDepositAmount(accountMoney);
					delpositLog.setDepositTime(DateTime.formatDateTime(new Date()));
					delpositLog.setDepositOperator(user.getId());
					delpositLog.setDepositType("08");
					delpositLog.setDepositStatus("02");
					delpositLog.setTime(DateTime.formatDateTime(new Date()));
					delpositLog.setType("02");
					delpositLog.setEquipment(order.getOrdPaySource());
					delpositLog.setIp(ip);
					delpositLog.setDescribe(order.getOrdNo());
					delpositLog.setBalance(user.getBalance());
					this.userDao.addDeposit(delpositLog);
				}
				//是否使用积分
				if(ii>0){
					user.setSurplusIntegral(user.getSurplusIntegral()-ii);
					this.orderDao.updateUser(user);
					IntIntegral inte=new IntIntegral();
					IntContrast intContrast=this.integralService.getContrast("sy002");
					inte.setIntContrast(intContrast);
					inte.setSysUser(user);
					inte.setIntegral(ii);
					inte.setDescribe(order.getOrdNo());
					inte.setEquipment(order.getOrdPaySource());
					inte.setIp(ip);
					inte.setCreatetime(DateTime.formatDateTime(new Date()));
					inte.setDelflag("02");
					inte.setCurrentIntegral(user.getSurplusIntegral());
					inte.setType("02");
					this.integralService.saveIntegral(inte);
				}
				
				//-------------积分-------------
				//总积分
				if(user.getTotalIntegral()==null){
					user.setTotalIntegral(0l);
				}
				//剩余积分
				if(user.getSurplusIntegral()==null){
					user.setSurplusIntegral(0l);
				}
				//获得总积分
				Long surplusIntegral=0l;
				//会员等级
				Long integral = user.getTotalIntegral();
				
				Double rate=integralService.findLevelRateByTotalIntegral(integral);
				
				IntContrast contrast=integralService.getContrast("gc019");
				if(contrast!=null &&!"".equals(contrast)){
					String startTime=contrast.getStarttime();
					String endTime=contrast.getEndtime();
					Long integral_val=contrast.getIntegral();
					//判断是否有时间限制
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(startTime!=null && endTime!=null){
						Date d;
						try {
							d = df.parse(DateTime.formatDateTime(new Date()));
							Date sta = df.parse(startTime);
							Date end = df.parse(endTime);
							long dl1 = d.getTime()-sta.getTime();
							long dl2 = end.getTime()-d.getTime();
							if(dl1>=0&& dl2>=0){
								Long con_rate =contrast.getRate();
								integral_val=integral_val*con_rate;
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					surplusIntegral=new BigDecimal((order.getOrdMoneysum()+accountMoney)*integral_val*rate).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
				}
				
				//班级列表
				List<EOrderDetail> list=this.orderDao.findOrderDetail(order.getId());
				for(int i=0;i<list.size();i++){
					EOrderDetail od=list.get(i);
					//如果班级报名过则不给班级获取积分
					EOrderDetail detail=this.orderDao.findOrdClassDetailByPayStatus(user.getId(), od.getTrainClass().getId(), "20");
					if(detail==null){
						surplusIntegral=surplusIntegral+new BigDecimal(od.getTrainClass().getGiveIntegral()*rate).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
						//增加报名人数
						TrainClass tclass=this.classDao.getTrainClassById(od.getTrainClass().getId());
						if(tclass!=null){
							tclass.setClassEnrolNumber(tclass.getClassEnrolNumber()+1);
							this.classDao.saveClass(tclass);
						}
						//若之前没有报名同一班级，则增加学号
						String studyNo=list.get(i).getTrainClass().getClassNo()+new DecimalFormat("00000").format(this.orderDao.findAllOrdClass(od.getTrainClass().getId()).size()+1);
						od.setStudyNo(studyNo);
					}else{
						//若之前报名同一班级，则学号一致
						od.setStudyNo(detail.getStudyNo());
					}
					this.orderDao.saveOrderDetail(od);
				}
				user.setTotalIntegral(user.getTotalIntegral()+surplusIntegral);
				user.setSurplusIntegral(user.getSurplusIntegral()+surplusIntegral);
				this.orderDao.updateUser(user);
	
				//获取积分记录
				IntContrast intContrast=this.integralService.getContrast("gc019");
				IntIntegral inl=new IntIntegral();
				inl.setIntContrast(intContrast);
				inl.setSysUser(user);
				inl.setIntegral(surplusIntegral);
				inl.setEquipment(order.getOrdPaySource());
				inl.setDescribe(order.getOrdNo());
				inl.setIp(ip);
				inl.setCreatetime(DateTime.formatDateTime(new Date()));
				inl.setDelflag("02");
				inl.setCurrentIntegral(user.getSurplusIntegral());
				inl.setType("01");
				this.integralService.saveIntegral(inl);
				//推荐人
				SysUser recommender=user.getRecommender();
				if(recommender!=null){
					//获取积分
					long surplusIntegral_c=0l;
					IntContrast intC=this.integralService.getContrast("gc027");
					if(intC!=null){
						String startTime=intC.getStarttime();
						String endTime=intC.getEndtime();
						Long integral_val_c=intC.getIntegral();
						//判断是否有时间限制
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(startTime!=null && endTime!=null){
							Date d;
							try {
								d = df.parse(DateTime.formatDateTime(new Date()));
								Date sta = df.parse(startTime);
								Date end = df.parse(endTime);
								long dl1 = d.getTime()-sta.getTime();
								long dl2 = end.getTime()-d.getTime();
								if(dl1>=0&& dl2>=0){
									integral_val_c=integral_val_c*intC.getRate();
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
						surplusIntegral_c=new BigDecimal(order.getOrdTotalMoney()*integral_val_c).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
						
						recommender.setTotalIntegral(recommender.getTotalIntegral()+surplusIntegral_c);
						recommender.setSurplusIntegral(recommender.getSurplusIntegral()+surplusIntegral_c);
						this.orderDao.updateUser(recommender);
						//推荐人积分记录
						if(surplusIntegral_c>0){
							IntIntegral inl_c=new IntIntegral();
							inl_c.setIntContrast(intC);
							inl_c.setSysUser(recommender);
							inl_c.setIntegral(surplusIntegral_c);
							inl_c.setEquipment(order.getOrdPaySource());
							inl_c.setDescribe(user.getRealname()+"消费获取积分");
							inl_c.setIp(ip);
							inl_c.setCreatetime(DateTime.formatDateTime(new Date()));
							inl_c.setDelflag("02");
							inl_c.setCurrentIntegral(recommender.getSurplusIntegral());
							inl_c.setType("01");
							this.integralService.saveIntegral(inl_c);
						}
					}
				}
				
			}
			if(payTime!=null&&!"".equals(payTime))
				order.setOrdPaytime(payTime);
			order.setLastedittime(DateTime.formatDateTime(new Date()));
			order.setOrdStatus(v_pstatus);
			order.setExpressState("01");
			if(v_pstatus.equals("30")){
				String orderNo = this.getGenerateOrderNo("01");
				order.setOrdTempNo(orderNo);
			}
			this.orderDao.updateOrder(order);
		}
		return order;
	}

	public List<EOrder> findEOrder(String userId, String ordStatus) {
		return this.orderDao.findEOrder(userId,ordStatus);
	}
	
	public List<EOrderDetail> findOrderDetailById(String orderId) {
		return this.orderDao.findOrderDetailById(orderId);
	}

	public String doDeleterOrder(String orderId) {
		EOrder  eOrder=orderDao.findOrderById(orderId);
		if(eOrder!=null){
			List<EOrderDetail> eOrderDetailList=this.orderDao.findOrdDetailByOrdId(orderId);
			for(int i=0;i<eOrderDetailList.size();i++){
				EOrderDetail eOrderDetail=eOrderDetailList.get(i);
				eOrderDetail.setDelflag("01");
				this.orderDao.saveOrderDetail(eOrderDetail);
			}
			eOrder.setDelflag("01");
			this.orderDao.saveOrder(eOrder);
			return "1";
		}
		return "0";
	}

	public EOrder findOrderSomeByTmpNo(String orderNo) {
		return this.orderDao.findOrderSomeByTmpNo(orderNo);
	}
	
	
}
