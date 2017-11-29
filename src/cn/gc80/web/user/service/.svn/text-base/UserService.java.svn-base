package cn.gc80.web.user.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import cn.gc80.base.page.PageHolder;
import cn.gc80.base.util.ClientIpUtil;
import cn.gc80.base.util.ConfigUtil;
import cn.gc80.base.util.DateTime;
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
import cn.gc80.datamodel.training.TrainCert;
import cn.gc80.datamodel.training.TrainClass;
import cn.gc80.datamodel.training.TrainCredit;
import cn.gc80.web.cert.dao.CertDao;
import cn.gc80.web.integral.service.IntegralService;
import cn.gc80.web.user.dao.UserDao;

@Service("userService")
public class UserService {
	@Resource
	private UserDao userDao;
	@Resource
	private IntegralService integralService;
	@Resource
	private CertDao certDao;
	
	public SysUser findUserByParam(String param) {
		return userDao.findUserByParam(param);
	}
	
	public SysUser findUserByParam(String userName,String mobile,String cardno) {
		return userDao.findUserByParam(userName, mobile, cardno);
	}

	public int updateLoginErrNum(String id, int num) {
		return this.userDao.updateLoginErrNum(id,num);
	}

	public List<SysProperty> findPropertyList(String propType) {
		return this.userDao.findPropertyList(propType);
	}
	
	public Map<String, Object> findPersonalMap(String userId,Map<String, Object> map){
		SysUser user=this.userDao.findUserByUserId(userId);
		//头像
		map.put("userphoto",user.getUserphoto());
		//姓名
		map.put("realname",user.getRealname());
		//会员等级
		Long integral = user.getTotalIntegral();
		if(integral==null){
			integral = 0l;
		}
		// 学霸的积分、名称
		Long oneLevelIntegral = Long.parseLong(ConfigUtil.getConfig("one_level_integral"));
		String oneLevelName = ConfigUtil.getConfig("one_level_name");
		// 大学生的积分、名称
		Long twoLevelIntegral = Long.parseLong(ConfigUtil.getConfig("two_level_integral"));
		String twoLevelName = ConfigUtil.getConfig("two_level_name");
		// 中学生的积分、名称
		Long threeLevelIntegral = Long.parseLong(ConfigUtil.getConfig("three_level_integral"));
		String threeLevelName = ConfigUtil.getConfig("three_level_name");
		// 小学生的积分、名称
		Long fourLevelIntegral = Long.parseLong(ConfigUtil.getConfig("four_level_integral"));
		String fourLevelName = ConfigUtil.getConfig("four_level_name");
		String levelName="";
		if(integral>=fourLevelIntegral&&integral<threeLevelIntegral){
			levelName=fourLevelName;
		}else if(integral>=threeLevelIntegral&&integral<twoLevelIntegral){
			levelName=threeLevelName;
		}else if(integral>=twoLevelIntegral&&integral<oneLevelIntegral){
			levelName=twoLevelName;
		}else if(integral>=oneLevelIntegral){
			levelName=oneLevelName;
		}
		map.put("levelName",levelName);
		//余额
		map.put("balance",user.getBalance());
		//积分
		map.put("surplusIntegral",user.getSurplusIntegral());
		//区域
		map.put("area", user.getArea());
		//cityName
		String cityName="";
		if(user.getArea()!=null){
			if(user.getArea().length()>=4){
				SysProvince province=this.userDao.findSysProvinceByCodeid(user.getArea().substring(0,4));
				if(province!=null) cityName=province.getCityname();
			}
		}
		map.put("city", cityName);
		return map;
	}

	public PageHolder findIntegralRecord(String userId, String staTime,String endTime, PageHolder ph) {
		return this.userDao.findIntegralRecord(userId, staTime,endTime, ph);
	}
	
	public SysUser findUserByUserId(String userId){
		return this.userDao.findUserByUserId(userId);
	}

	public PageHolder toMyAccount(String userId, String staTime,String endTime, PageHolder ph) {
		return this.userDao.toMyAccount(userId, staTime,endTime, ph);
	}
	
	public List<SysProvince> findProvinceByParent(String parent){
		return this.userDao.findProvinceByParent(parent);
	}

	public String doRegister(SysUser user,String ip,String equipment) {
		String uid=this.userDao.saveUser(user);
		if(!"".equals(uid)){
			integralService.addIntegral("gc001", uid, ip,equipment);
			return uid;
		}
		return "";
	}
	
	public String doRegister1(SysUser user,String ip,String equipment) {
		String uid=this.userDao.saveUser(user);
		if(!"".equals(uid)){
			integralService.addIntegral("gc003", uid, ip,equipment);
			return uid;
		}
		return "";
	}

	public void doBirthdayInteger(SysUser sysUser,String ip,String equipment) {
		SimpleDateFormat df1 = new SimpleDateFormat("MMdd");
		if(sysUser.getCardno()!=null&&!"".equals(sysUser.getCardno())){
			if(sysUser.getCardno().length()>15){
				String birthday = sysUser.getCardno().substring(10,14); 
				String date=df1.format(new Date());
				if(birthday.equals(date)){
					SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
					IntIntegral intIntegral=integralService.findIntegral(sysUser.getId(),"gc004",df2.format(new Date()));
					if(intIntegral==null || "".equals(intIntegral)){
						integralService.addIntegral("gc004", sysUser.getId(), ip,equipment);
					}
				}
			}
		}
	}

	public List<Map<String, Object>> findEOrderDetail(String userId) {
		List<Map<String, Object>> list =new ArrayList<Map<String,Object>>();
		List<EOrderDetail> orderDetailList=this.userDao.findEOrderDetail(userId);
		for(int i=0;i<orderDetailList.size();i++){
			EOrderDetail orderDetail=orderDetailList.get(i);
			if(orderDetail.getTrainClass()!=null){
				Map<String, Object> map=new HashMap<String, Object>();
				TrainClass trainClass=this.userDao.getTrainClassById(orderDetail.getTrainClass().getId());
				map.put("classId", trainClass.getId());
				map.put("className", trainClass.getClassName());
				map.put("classImage", trainClass.getClassImage());
				map.put("cityName", trainClass.getProvince().getCityname());
				map.put("year", trainClass.getClassYear());
				map.put("codeParentName", trainClass.getTrainProject().getSysCode().getSysCode().getCodeName());
				map.put("codeName", trainClass.getTrainProject().getSysCode().getCodeName());
				map.put("classCredithour", trainClass.getClassCredithour());
				long hasCredit=this.userDao.findCreditCount(userId,orderDetail.getTrainClass().getId());
				map.put("hasCredit", hasCredit);
				list.add(map);
			}
		}
		return list;
	}

	public PageHolder toMyClassCourse(String userId, String classId,PageHolder ph) {
		return this.userDao.findOrdDetails(userId, classId, "", "", ph);
	}
	
	public PageHolder toMyCourseware(String userId, String courseId,PageHolder ph) {
		ph=this.userDao.findUserCoursesPercent(userId,courseId,"02",ph);
		//容错处理
		this.updateUserCourseStudy(userId, courseId, ph.getDataList());
		return ph;
	}
	
	//容错处理
	public void updateUserCourseStudy(String userId, String courseId,List list){
		for(int i=0;i<list.size();i++){
			Object[] uc = (Object[])list.get(i);
			ResCourseware cw = this.userDao.findCourseware(uc[3].toString());
			if(cw!=null){
				uc[4]=cw.getCwName();
				uc[5]=cw.getCreditnum();
				uc[12]=cw.getCwPeriod();
			}
			//如果所有章节都完成,但课程状态为未完成，则修改课程状态为完成
			if(!(uc[10].toString()).equals("03")){
				List uchapterList = this.userDao.findUserChapterSome(userId, courseId, uc[3].toString());
				if(uchapterList!=null&&!uchapterList.isEmpty()&&uchapterList.size()>0){
					boolean complete = true;
					for(int j=0;j<uchapterList.size();j++){
						LearnUserChapter luc = (LearnUserChapter)uchapterList.get(j);
						if(luc.getHours()+120>luc.getResChapter().getPeriod()){
							luc.setHours(luc.getResChapter().getPeriod());
							luc.setLessonstatus("completed");
							luc.setLessonLocation(String.valueOf(luc.getHours()*1000));
							this.userDao.updateUserChapter(luc);
						}
						if(luc.getLessonstatus()==null||!luc.getLessonstatus().equals("completed")){
							complete = false;
							break;
						}
					}
					//如果课件完成,则课程是否完成
					if(complete){
						LearnUserCourse tmp = this.userDao.findUserCourseStudyStatus(userId, courseId, uc[3].toString());
						if (tmp != null&& !tmp.getStudystatus().equals("03")) {// 当前章节在数据库形式已经改变？
							tmp.setStudystatus("03");
							tmp.setLasttime(DateTime.formatDateTime(new Date()));
							tmp.setHasCredit(tmp.getResCourseware().getCreditnum());
							tmp.setCompletetime(DateTime.formatDateTime(new Date()));
							uc[10]="03";
							//this.userCourseManager.updateUserCourseFinish(tmp);
							this.userDao.saveUserCourse(tmp);
							List<LearnUserCourse> ucList = this.userDao.findUserCourseByCourseId(userId, courseId);
							boolean completeFlag = true;
							if(ucList!=null&&ucList.size()>0){
								for(int j=0;j<ucList.size();j++){
									LearnUserCourse tmp1 = (LearnUserCourse)ucList.get(j);
									if(!tmp1.getId().equals(tmp.getId())&&!tmp1.getStudystatus().equals("03")){
										completeFlag =  false;
										break;
									}
								}
							}
							//如果课程完成
							List<EOrderDetail> odlist = this.userDao.findOrdDetail(userId, courseId);
							if(odlist!=null&&odlist.size()>0){
								for (EOrderDetail od : odlist) {
									if(od.getHasCredit()+tmp.getResCourseware().getCreditnum()<tmp.getResCourse().getCCredithour()){
										od.setHascredit(od.getHasCredit()+tmp.getResCourseware().getCreditnum());
									}else{
										od.setHascredit(tmp.getResCourse().getCCredithour());
									}
									if(completeFlag){
										od.setHascredit(tmp.getResCourse().getCCredithour());
										od.setStudystatus("03");
										//更改课程下的考试状态
										try{
											ExamUserQuiz uquiz1 = userDao.findQuiz(userId,od.getResCourse().getId());
											if(uquiz1!=null){
												uquiz1.setStatus("01");
												this.userDao.updateUserQuiz(uquiz1);
											}
										}catch (Exception e) {
											e.printStackTrace();
										}
										//不需要考试生成学时
										if(tmp.getResCourse().getIsExam()!=null&&tmp.getResCourse().getIsExam().equals("01")){
											TrainCredit credit = this.userDao.findCreditByUC(userId, courseId);
											String currenttime = DateTime.formatDateTime(new Date());
											if(credit==null){
												credit = new TrainCredit();
												credit.setCodeNo((od.getResCourse().getSysCode().getCodeNo()).substring(0,4));
												credit.setCourseMode("02");
												credit.setCourseName(od.getResCourse().getCName());
												credit.setResCourse(od.getResCourse());
												credit.setTrainClass(od.getTrainClass());
												SysUser user = new SysUser();
												user.setId(userId);
												credit.setSysUser(user);
												if(od.getYear()!=null){
													credit.setYear(od.getYear());
												}else{
													credit.setYear(currenttime.substring(0,4));
												}
												credit.setCreditStatus("01");
												credit.setRegType("02");//按课程申报
												credit.setCreatetime(currenttime);
												LearnUserCourse luc = this.userDao.findLastUserCourse(userId, courseId);
												if(luc.getFirsttime()!=null&&!luc.getFirsttime().equals("")){
													credit.setStartTime(luc.getFirsttime().substring(0, 10));
												}else{
													if(luc.getEnsuretime()!=null){
														credit.setStartTime(luc.getEnsuretime());
													}else{
														if(luc.getLasttime()!=null){
															credit.setStartTime(luc.getLasttime());
														}
													}
												}
											}
											
											if(credit.getCredit()==null){
												credit.setCredit((long) 0d);
											}
											if(credit.getGrade()==null){
												credit.setGrade(0d);
											}
											credit.setCredit(od.getResCourse().getCCredithour());
											credit.setEndTime(currenttime);
											credit.setGrade(80d);
											try {
												this.certDao.saveCredit(credit);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
									this.userDao.saveOrderDetail(od);
									//生成证书（如果班级所得学时等于班级要求学时，则生成证书）
									long creditSum=this.certDao.findCreditCount(userId, od.getTrainClass().getId());
									if(creditSum>=od.getTrainClass().getClassCredithour()){
										TrainCert cert=this.certDao.findCert(userId,od.getTrainClass().getId());
										if(cert==null){
											cert=new TrainCert();
											EOrderDetail orderDetail=this.userDao.getOrderDetail(userId,od.getTrainClass().getId());
											cert.setCertNo("GC"+orderDetail.getStudyNo());
											cert.setCertName(orderDetail.getTrainClass().getClassName());
											cert.setCertDate(DateTime.formatDateTime(new Date()));
											SysUser user=this.userDao.findUserByUserId(userId);
											cert.setCardno(user.getCardno());
											cert.setCredit(creditSum);
											cert.setRealname(user.getRealname());
											cert.setTrainType("03");
											cert.setYear(orderDetail.getYear());
											cert.setCertAlias(user.getRealname()+orderDetail.getYear()+"继续教育学时证明");
											cert.setCreatetime(DateTime.formatDateTime(new Date()));
											List<TrainCredit> creditList=this.userDao.findCredit(userId, orderDetail.getTrainClass().getId());
											cert.setStarttime(creditList.get(0).getStartTime());
											cert.setFinishtime(creditList.get(creditList.size()-1).getEndTime());
											cert.setDelflag("02");
											cert.setSysUser(user);
											cert.setPrintFlag("01");
											cert.setCertType(orderDetail.getTrainClass().getTrainProject().getSysCode().getSysCode().getCodeNo());
											cert.setCertSeq(0l);
											cert.setTrainClass(orderDetail.getTrainClass());
											SysUserInfo userInfo=this.userDao.findUserInfoByUserId(userId);
											if(userInfo!=null){
												cert.setOrganization(userInfo.getOrganization());
											}
										}else{
											cert.setCredit(creditSum);
										}
										this.certDao.saveCert(cert);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public Map<String,Object> updateUserCourseForStudyMap(String userId,String courseId,String coursewareId,String chapterId){
		Map<String,Object> map = new HashMap<String,Object>();
		String launch = "";
		try {
			ResCourse course = this.userDao.findCourseInStudy(courseId);
			if(course!=null){
				if(course.getCNo().startsWith("ZB")){
					map.put("course", course);
				}else{
					ResCourseware courseware = this.userDao.findCoursewareSome(coursewareId);
					ResChapter chapter = null;
					String coursewareDomain = ConfigUtil.getConfig("courseware_url");
					if(coursewareDomain==null||coursewareDomain.equals("")){
						coursewareDomain = "/";
					}
					if(!coursewareDomain.endsWith("/")){
						coursewareDomain = coursewareDomain + "/";
					}
					if (courseware!=null&&courseware.getCwFileserver() != null) {
						coursewareDomain = coursewareDomain + courseware.getCwFileserver();
					}
					List<ResChapter> chapterList = this.userDao.findChapter(coursewareId);
					LearnUserCourse uc = this.userDao.findUserCourseInStudy(userId, courseId,coursewareId);
					if(uc!=null){
						if(uc.getStudystatus()==null||uc.getStudystatus().equals("01")){
							uc.setStudystatus("02");
							this.initUserScos(userId, courseId,coursewareId, chapterList);
							EOrderDetail od = this.userDao.findOrdDetailBySome(userId, courseId);
							if(od!=null&&(od.getStudystatus()==null||od.getStudystatus().equals("01"))){
								od.setStudystatus("02");
								this.userDao.updateOrdDetail(od);
							}
						}
						if(uc.getFirsttime()==null||uc.getFirsttime().equals("")||uc.getFirsttime().length()<=4){
							uc.setFirsttime(DateTime.formatDateTime(new Date()));
						}
						uc.setLasttime(DateTime.formatDateTime(new Date()));
						this.userDao.updateUserCourseInStudy(uc);
					}
					if (courseware!=null&&courseware.getCwScorm().equals("01")) {
						if(chapterList==null||chapterList.isEmpty()){
							launch = coursewareDomain+"/" + courseware.getCwReleaseDir() + "/" + courseware.getCwIndexFile();
						}else{
							for(int i=0;i<chapterList.size();i++){
								chapter = (ResChapter)chapterList.get(i);
								chapterId = chapter.getId();
								if(chapter.getLaunch().indexOf(".")!=-1){
									LearnUserChapter activeItem = this.userDao.findUserChapterStatus(userId, courseId, coursewareId, chapter.getId());
									if(activeItem.getLessonstatus()!=null&&!activeItem.getLessonstatus().equals("completed")){
										if(chapter.getLaunch().startsWith("http")){
											launch = chapter.getLaunch();
										}else{
											launch = coursewareDomain + chapter.getLaunch();
										}
										break;
									}
								}
							}
						}
					}
					if (courseware!=null&&courseware.getCwScorm().equals("02")) {
						Map<String,Object> map1 = this.getChapterLaunch(userId,courseId,coursewareId,chapterId,chapterList);
						chapter = (ResChapter)map1.get("chapter");
						String completeFlag = (String)map1.get("completeFlag");
						map.put("completeFlag", completeFlag);
						chapterId = chapter.getId();
						if(chapter.getLaunch().startsWith("http")){
							launch = chapter.getLaunch();
						}else{
							launch = coursewareDomain + chapter.getLaunch();
						}
					}
					if(chapterList!=null){
						LearnUserChapter trackinfo = this.userDao.findUserChapterInStudy(userId, courseId, coursewareId, chapterId);
						if(trackinfo!=null){
							//trackinfo.setLaststarttime(System.currentTimeMillis());
							//this.userChapterManager.updateUserChapterInStudy(trackinfo);
							map.put("trackinfo", trackinfo);
							int current = 0;
							if(chapterList!=null&&!chapterList.isEmpty()){
								for(int i=0;i<chapterList.size();i++){
									ResChapter tmp = (ResChapter) chapterList.get(i);
									if (chapterId.equals(tmp.getId())) {
										current = i;
										break;
									}
								}
								if(current<chapterList.size()-1){
									ResChapter tmp =  (ResChapter) chapterList.get(current+1);
									map.put("nextid", tmp.getId());
								}
								if(current>0){
									ResChapter tmp =  (ResChapter) chapterList.get(current-1);
									map.put("preid", tmp.getId());
								}
							}
							if(courseware!=null){
								if(courseware.getTrackMode()==null||courseware.getTrackMode().equals("")){
									courseware.setTrackMode("01");
								}
								map.put("courseware", courseware);
								String trackMode = courseware.getTrackMode();
								if(trackMode.equals("01")){
									if(trackinfo.getLessonLocation()!=null){
										map.put("lessonLocation", Integer.parseInt(trackinfo.getLessonLocation()));
									}
								}else if(trackMode.equals("02")){
									String tempLis ="";
									if(trackinfo.getLessonLocation()!=null){
										if (!"".equals(trackinfo.getSuspendData())&& trackinfo.getSuspendData() != null) {
											String temsus[] = trackinfo.getSuspendData().split("=");
											if (temsus != null && temsus.length > 4) {
												for (int j = temsus.length - 2; j > 2;) {// 从后往前找
													if(!temsus[j].equals("0")){
														if(!temsus[j].equals(temsus[j-1])){//某个章节没学完
															tempLis = temsus[j-2].substring(temsus[j-2].indexOf(";")+1,temsus[j-2].length());
															break;
														}else{
															if((j+2)==temsus.length){//全部学完的时候
																tempLis = temsus[1].substring(temsus[1].indexOf(";")+1, temsus[1].length());
															}else{//非正常退出，只记录最后跳转点，中间无记录的， lessonlocation是不正确的
																tempLis = temsus[j+1].substring(temsus[j+1].indexOf(";")+1,temsus[j+1].length());
																break;
															}
														}
													}
													j = j - 3;
												}
											}
											map.put("lessonLocation", tempLis);
										}else{
											if(trackinfo.getLessonLocation()!=null&&trackinfo.getLessonLocation().length()==4&&("".equals(trackinfo.getSuspendData())|| trackinfo.getSuspendData() == null)){
												map.put("recmodel","1");
											}
											map.put("lessonLocation", trackinfo.getLessonLocation());
										}
									}
								}
							}
						}
					}
					if(course!=null){
						map.put("course", course);
					}
					if(courseware!=null){
						map.put("courseware", courseware);
					}
					if(chapterList!=null){
						map.put("chapterList", chapterList);
					}
					if(launch!=null){
						map.put("launch", launch);
					}
					if(chapter!=null){
						map.put("chapter", chapter);
					}
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return map;
	}

	private Map<String,Object> getChapterLaunch(String userId, String courseId,String coursewareId, String chapterId, List<ResChapter> chapterList) {
		Map<String,Object> map = new HashMap<String,Object>();
		LearnUserChapter trackinfo;
		ResChapter chapter = null;
		String lessonStatus = new String();
		ResChapter rtnChapter = null;
		if(chapterList!=null&&!chapterList.isEmpty()){
			String completeFlag = "02";
			int total = chapterList.size();
			int completed = 0;
			boolean chapterFlag = false;//是否定位到章节
			for (int i = 0; i < chapterList.size(); i++) {
				chapter = (ResChapter)chapterList.get(i);
				if(chapter.getId().equals(chapterId)){
					rtnChapter = chapter;
					chapterFlag = true;
				}
				if(chapter.getScotype()!=null&&(chapter.getScotype().equals("sco")||chapter.getScotype().equals("asset"))){
					trackinfo = this.userDao.findUserChapterStatus(userId, courseId, coursewareId, chapter.getId());
					if(trackinfo==null){
						lessonStatus = "not attempted";
					}else{
						if(trackinfo.getLessonstatus()!=null){
							lessonStatus = trackinfo.getLessonstatus();
						}
						else{
							lessonStatus = "incomplete";
						}
					}
					if (!lessonStatus.equalsIgnoreCase("completed")) {
						if(!chapterFlag){
							if(chapterId.equals("")){
								rtnChapter = chapter;
							}
							chapterFlag = true;
						}
						completeFlag = "01";
					}
					if (lessonStatus.equalsIgnoreCase("completed")){
						completed = completed + 1;
					}
				}
			}
			if(completed+1==total){
				map.put("completeFlag", "02");
			}else{
				map.put("completeFlag", "01");
			}
			if(completeFlag.equals("02")&&chapterId.equals("")){
				rtnChapter = (ResChapter)chapterList.get(0);
			}
		}
		map.put("chapter", rtnChapter);
		return map;
	}

	private void initUserScos(String userId, String courseId,String coursewareId, List<ResChapter> itemList) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Iterator it = itemList.iterator();
        while(it.hasNext()){
        	ResChapter item = (ResChapter)it.next();
	        if(item.getLaunch()!=null){
	        	String scotype = item.getScotype();
	        	if(scotype!=null&&(!scotype.equals(""))&&(scotype.equals("sco")||scotype.equals("asset"))){
	                 	LearnUserChapter trackinfo = this.userDao.findUserChapter(userId, courseId, coursewareId, item.getId());
	                 	if(trackinfo==null){
	    	        		String location = item.getLaunch();
	  	                 	long sequence = item.getSequence().longValue();
	  	                 	trackinfo = new LearnUserChapter();
	  	                 	trackinfo.setScotype(scotype);
	  	                 	SysUser user = new SysUser();
	  	                 	user.setId(userId);
	  	                 	trackinfo.setSysUser(user);  
	  	                 	ResCourse course = new ResCourse();
	  	                 	course.setId(courseId);
	  	                 	trackinfo.setResCourse(course);
	  	                 	trackinfo.setResChapter(item);
	  	                 	trackinfo.setResCourseware(item.getResCourseware());
	  	                 	trackinfo.setResUrl(location);      	       
	  	                 	trackinfo.setLessonstatus("incomplete");
	  	                 	trackinfo.setLessonLocation("0");
	  	                 	trackinfo.setHours(new Long(0));
	  	                 	trackinfo.setTime(new Long(0));
	  	                 	trackinfo.setStarttime(df.format(new Date()));
	  	                 	trackinfo.setEndtime("");
	  	                 	trackinfo.setLasttime(df.format(new Date()));
	  	                 	trackinfo.setScore("0");
	  	                 	trackinfo.setSequence(sequence);
	  	                 	trackinfo.setTotalHours(0l);
	  	                 	trackinfo.setLaststarttime(0l);
	  	                 	this.userDao.saveUserChapter(trackinfo);
	                 	}
	        	}
	        }
        }
	}
	
	public boolean updateDBTrack(String position, String duration,String courseId, String coursewareId, String chapterId,
			String userId, String trackMode, String lessonStatus,String lessonLocation, String suspendData,String flag) {
		boolean b=false;
		String tempLis = "";
		if (!userId.equals("") && !courseId.equals("")) {
			if (chapterId != null) {
				LearnUserChapter uchapter = this.userDao.findUserChapterInStudy(userId, courseId, coursewareId,chapterId);// 学习记录对象
				if (uchapter != null&& !uchapter.getLessonstatus().equals("completed")) {
					if (uchapter.getHours() == null) {
						uchapter.setHours(0l);
					}
					// trackMode=1：按时间点跟踪，trackMode=2:按知识点跟踪
					if (uchapter.getLessonLocation() == null|| uchapter.getLessonLocation().equals("")) {
						if (trackMode.equals("01")) {
							uchapter.setLessonLocation("0");
						}
						if (trackMode.equals("02")) {
							uchapter.setLessonLocation("");
						}
					}// 正好整数出问题
					int hours = 0;
					if (position.indexOf(".") == -1) {
						position = position + ".000";
					}
					if (position != null && !position.equals("")) {
						hours = Integer.parseInt(position.substring(0,position.indexOf(".")));
					}
					int totalHours = 0;
					if (duration.indexOf(".") == -1) {
						duration = duration + ".000";
					}
					if (duration != null && !duration.equals("")) {
						totalHours = Integer.parseInt(duration.substring(0,duration.indexOf(".")));
					}
					Long timestart = uchapter.getLaststarttime();
					Long timeend = System.currentTimeMillis();
					uchapter.setTotalHours(uchapter.getTotalHours()+(timeend-timestart)/1000);
					String lesson_location = uchapter.getLessonLocation();
					if (trackMode.equals("01")) {
						if (lesson_location == null|| lesson_location.equals("")) {// ?
							lesson_location = "0";
						}
						if (hours > Integer.parseInt(lesson_location)/1000) {
							uchapter.setLessonLocation(String.valueOf(hours * 1000));
						}
					}else if(trackMode.equals("02")) {// 02的时候
						if(lessonLocation!=null&&!lessonLocation.equals("")){
							uchapter.setLessonLocation(lessonLocation);
						}
						if("1".equals(flag)){//老学员问题
							if (!"".equals(suspendData)&& suspendData != null&&!(("0101").equals(lessonLocation)||("0100").equals(lessonLocation))){
								String temsu[] = suspendData.split("=");
								for(int j = 0;j<temsu.length;j++){
									if(temsu[j].indexOf(lessonLocation)!=-1){
										temsu[j+2]="1";//
									}
								}
								suspendData = "";
								for (int m = 0; m < temsu.length; m++) {
									suspendData += temsu[m];
									if (m != temsu.length - 1) {
										suspendData += "=";
									}
								}
							}
						}
						if (hours > uchapter.getHours()||"1".equals(flag)) {
							if (!"".equals(suspendData)&& suspendData != null) {
								String temsus[] = suspendData.split("=");
								if (temsus != null && temsus.length > 4) {
									for (int i = 0; i < (temsus.length - 2) / 3; i++) {// 3个为一组，-2是为了把开头那个个数分出去
										if (!temsus[temsus.length - 2 - i* 3].equals("0")) {// 找到最后那个数
											tempLis = temsus[temsus.length- 4 - i * 3].substring(temsus[temsus.length- 4 - i * 3].lastIndexOf(";") + 1);
											for (int j = temsus.length - 5 - i * 3; j > 2;) {// 从后往前找
												temsus[j] = temsus[j - 1];
												j = j - 3;
											}
											break;
										}
									}
									suspendData = "";
									for (int m = 0; m < temsus.length; m++) {
										suspendData += temsus[m];
										if (m != temsus.length - 1) {
											suspendData += "=";
										}
									}
								}
							}
							uchapter.setLessonLocation(tempLis);
							uchapter.setSuspendData(suspendData);
						}
					}
					if(lessonStatus==null||lessonStatus.equals("")){
						lessonStatus = "incomplete";
					}
					uchapter.setLessonstatus(lessonStatus);
					if(lessonStatus.equals("completed")){
						hours = totalHours;
					}
					if (hours > uchapter.getHours()) {
						uchapter.setHours(new Long(hours));
					}
					if (hours==totalHours&&totalHours!=0) {
						uchapter.setLessonstatus("completed");
						if (trackMode.equals("02")) {
							if (!"".equals(uchapter.getSuspendData())&& uchapter.getSuspendData() != null) {
								String temsus[] = suspendData.split("=");
								if (temsus != null && temsus.length > 4) {
									for (int j = temsus.length - 2; j > 2;) {// 从后往前找
										temsus[j] = temsus[j - 1];
										j = j - 3;
									}
									suspendData = "";
									for (int m = 0; m < temsus.length; m++) {
										suspendData += temsus[m];
										if (m != temsus.length - 1) {
											suspendData += "=";
										}
									}
									tempLis = temsus[1].substring(temsus[1].indexOf(";") + 1, temsus[1].length());
								}
								uchapter.setLessonLocation(tempLis);
								uchapter.setSuspendData(suspendData);
							}
						}
						uchapter.setLessonstatus("completed");// !!!!!!!!!!!!!!!改章节状态
						uchapter.setEndtime(DateTime.formatDateTime(new Date()));
					}
					uchapter.setLasttime(DateTime.formatDateTime(new Date()));
					if(totalHours!=0){
	        			this.userDao.updateUserChapterInStudy(uchapter);//！！！！！！写学习记录数据！！！
	        		}
					LearnUserCourse uc = this.userDao.findUserCourseStudyStatus(userId, courseId, coursewareId);
					uc.setHours(new Long(hours));
					uc.setLasttime(DateTime.formatDateTime(new Date()));
					if(hours==totalHours&&totalHours!=0){
						List list = this.userDao.findUserChapterSome(userId, courseId, coursewareId);
						if (list != null&& !list.isEmpty()) {
							boolean completed = true;
							for (int i = 0; i < list.size(); i++) {
								uchapter = (LearnUserChapter) list.get(i);
								if (!uchapter.getResChapter().getId().equals(chapterId)) {// 不管当前的章节状态
									if(!uchapter.getLessonstatus().equals("completed")){
										completed = false;
										break;
									}
								}
							}
							if (completed) {
								EOrderDetail od = this.userDao.findOrdDetailBySome(userId, courseId);
								if (!uc.getStudystatus().equals("03")) {// 当前章节在数据库形式已经改变？
									uc.setStudystatus("03");
									uc.setHours(uc.getResCourseware().getCwPeriod());
									uc.setHasCredit(uc.getResCourseware().getCreditnum());
									uc.setCompletetime(DateTime.formatDateTime(new Date()));
									//this.userCourseManager.updateUserCourseFinish(uc);
									this.userDao.saveUserCourse(uc);
									List ucList = this.userDao.findUserCourseByCourseId(userId, courseId);
									boolean completeFlag = true;
									if(ucList!=null&&ucList.size()>0){
										for(int i=0;i<ucList.size();i++){
											LearnUserCourse tmp = (LearnUserCourse)ucList.get(i);
											if(!tmp.getStudystatus().equals("03")&&!tmp.getId().equals(uc.getId())){
												completeFlag =  false;
												break;
											}
										}
									}
									if(od!=null){
										if(od.getHasCredit()+uc.getResCourseware().getCreditnum()<uc.getResCourse().getCCredithour()){
											od.setHascredit(od.getHasCredit()+uc.getResCourseware().getCreditnum());
										}else{
											od.setHascredit(uc.getResCourse().getCCredithour());
										}
										if(completeFlag){
											od.setHascredit(uc.getResCourse().getCCredithour());
											od.setStudystatus("03");
											ExamUserQuiz uq = userDao.findQuiz(userId,courseId);
											if(uq!=null){
												uq.setStatus("01");
												this.userDao.updateUserQuiz(uq);
											}
											//不需要考试
											if(od.getIsExam()!=null&&od.getIsExam().equals("01")){
												TrainCredit credit = this.userDao.findCreditByUC(userId, courseId);
												String currenttime = DateTime.formatDateTime(new Date());
												if(credit==null){
													credit = new TrainCredit();
													credit.setCodeNo((uc.getResCourse().getSysCode().getCodeNo()).substring(0,4));
													if(od.getIsSubItem()!=null&&od.getIsSubItem().equals("02")){
														//credit.setCodeNo("0200");
														credit.setTrainProject(od.getTrainProject());
														credit.setTrainClass(od.getTrainClass());
													}
													credit.setCourseMode("02");
													credit.setCourseName(uc.getResCourse().getCName());
													credit.setResCourse(uc.getResCourse());
													SysUser user = new SysUser();
													user.setId(userId);
													credit.setSysUser(user);
													credit.setYear(od.getYear());
													credit.setCreditStatus("01");
													credit.setRegType("02");//按课程申报
													credit.setCreatetime(currenttime);
													LearnUserCourse luc = this.userDao.findLastUserCourse(userId, courseId);
													if(luc.getFirsttime()!=null&&!luc.getFirsttime().equals("")){
														credit.setStartTime(luc.getFirsttime().substring(0, 10));
													}else{
														if(luc.getEnsuretime()!=null){
															credit.setStartTime(luc.getEnsuretime());
														}else{
															if(luc.getLasttime()!=null){
																credit.setStartTime(luc.getLasttime());
															}
														}
													}
												}
												credit.setCreditStatus("01");
												if(credit.getCredit()==null){
													credit.setCredit(0l);
												}
												if(credit.getGrade()==null){
													credit.setGrade(0d);
												}
												credit.setCredit(od.getHasCredit());
												credit.setEndTime(currenttime);
												credit.setGrade(80d);
												try {
													this.certDao.saveCredit(credit);
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
										}
										this.userDao.updateOrdDetail(od);
										//生成证书（如果班级所得学时等于班级要求学时，则生成证书）
										long creditSum=this.userDao.findCreditCount(userId, od.getTrainClass().getId());
										if(creditSum>=od.getTrainClass().getClassCredithour()){
											TrainCert cert=this.certDao.findCert(userId,od.getTrainClass().getId());
											if(cert==null){
												cert=new TrainCert();
												EOrderDetail orderDetail=this.userDao.getOrderDetail(userId,od.getTrainClass().getId());
												cert.setCertNo("GC"+orderDetail.getStudyNo());
												cert.setCertName(orderDetail.getTrainClass().getClassName());
												cert.setCertDate(DateTime.formatDateTime(new Date()));
												SysUser user=this.userDao.findUserByUserId(userId);
												cert.setCardno(user.getCardno());
												cert.setCredit(creditSum);
												cert.setRealname(user.getRealname());
												cert.setTrainType("03");
												cert.setYear(orderDetail.getYear());
												cert.setCertAlias(user.getRealname()+orderDetail.getYear()+"继续教育学时证明");
												cert.setCreatetime(DateTime.formatDateTime(new Date()));
												List<TrainCredit> creditList=this.userDao.findCredit(userId, orderDetail.getTrainClass().getId());
												cert.setStarttime(creditList.get(0).getStartTime());
												cert.setFinishtime(creditList.get(creditList.size()-1).getEndTime());
												cert.setDelflag("02");
												cert.setSysUser(user);
												cert.setPrintFlag("01");
												cert.setCertType(orderDetail.getTrainClass().getTrainProject().getSysCode().getSysCode().getCodeNo());
												cert.setCertSeq(0l);
												cert.setTrainClass(orderDetail.getTrainClass());
												SysUserInfo userInfo=this.userDao.findUserInfoByUserId(userId);
												if(userInfo!=null){
													cert.setOrganization(userInfo.getOrganization());
												}
											}else{
												cert.setCredit(creditSum);
											}
											this.certDao.saveCert(cert);
										}
										//生成证书结束
									}
								}
							}
						}
					}
					//this.userCourseManager.updateUserCourseWhenQuit(uc);
					this.userDao.saveUserCourse(uc);
				}
			}
		}
		b=true;
		return b;
	}
	public Map<String, Object> findPersonalDataMap(String userId,Map<String, Object> map){
		SysUserInfo userInfo = this.userDao.findUserInfoByUserId(userId);
		if(userInfo==null){
			userInfo = new SysUserInfo();
			SysUser user = this.userDao.findUserByUserId(userId);
			userInfo.setSysUser(user);
		}
		SysUser user=this.userDao.findUserByUserId(userId);
		//公司名称
		map.put("organization",userInfo.getOrganization());
		String area = user.getArea();
		if(area!=null){
			if(area.length()==2){
				SysProvince sysProvince = this.userDao.getProvince(area);
				map.put("provinceCity",sysProvince.getCityname());
			}
			if(area.length()==4){
				String cityName = "";
				SysProvince sysProvince = this.userDao.getProvince(area.substring(0, 2));
				cityName += sysProvince.getCityname();
				SysProvince sysProvince1 = this.userDao.getProvince(area);
				cityName += sysProvince1.getCityname();
				map.put("provinceCity",cityName);
			}
			if(area.length()==6){
				String cityName = "";
				SysProvince sysProvince = this.userDao.getProvince(area.substring(0, 2));
				cityName += sysProvince.getCityname()+"  ";
				SysProvince sysProvince1 = this.userDao.getProvince(area.substring(0, 4));
				cityName += sysProvince1.getCityname()+"市  ";
				SysProvince sysProvince2 = this.userDao.getProvince(area);
				cityName += sysProvince2.getCityname();
				map.put("provinceCity",cityName);
			}
		}
		//职称
		String title = userInfo.getTitle();
		String major = "";
		if(!"".equals(title) && null!=title){
			SysCode sysCode = userDao.findCodeByNo(title.substring(0, 4));
			if(null != sysCode){
				major += sysCode.getCodeName()+" ";
				SysCode sysCode1 = userDao.findCodeByNo(title);
				if(null != sysCode1){
					major += sysCode1.getCodeName()+" ";
				}
			}
		}
		map.put("major",major);
		//学历
		map.put("degree",degree(userInfo.getDegree()));
		//单位地址
		map.put("infoAddress",userInfo.getAddress());
		//头像
		map.put("userphoto",user.getUserphoto());
		//姓名
		map.put("realname",user.getRealname());
		map.put("userName",user.getUserName());
		//身份证
		map.put("cardno",user.getCardno());
		//性别
		if("02".equals(user.getSex())){
			map.put("sex","女");
		} else if ("01".equals(user.getSex())){
			map.put("sex","男");
		}
		//手机号
		map.put("mobile",user.getBindMobile());
		//身份证
		map.put("phone",user.getPhone());
		//邮箱
		map.put("email",user.getEmail());
		//地址
		map.put("address",user.getUserAddress());
		//会员等级
		Long integral = user.getTotalIntegral();
		if(integral==null){
			integral = 0l;
		}
		
		//资料完整度
		String requiredFlag="02";
		if(userInfo.getSysUser().getUserName()==null || "".equals(userInfo.getSysUser().getUserName())
				|| userInfo.getSysUser().getRealname()==null || "".equals(userInfo.getSysUser().getRealname())
				|| userInfo.getSysUser().getCardno()==null || "".equals(userInfo.getSysUser().getCardno())
				|| userInfo.getSysUser().getSex()==null || "".equals(userInfo.getSysUser().getSex())
				|| userInfo.getSysUser().getBindMobile()==null || "".equals(userInfo.getSysUser().getBindMobile())
				|| userInfo.getSysUser().getArea()==null || "".equals(userInfo.getSysUser().getArea())
 				|| userInfo.getOrganization()==null || "".equals(userInfo.getOrganization())
				|| userInfo.getAddress()==null || "".equals(userInfo.getAddress())
 				|| userInfo.getTitle()==null || "".equals(userInfo.getTitle())
				){
			requiredFlag="01";
		}
		map.put("requiredFlag", requiredFlag);
		
		Integer fillInNum=0;
		if(user.getUserName()!=null&&!"".equals(user.getUserName())){
			fillInNum=fillInNum+1;
		}
		if(user.getRealname()!=null&&!"".equals(user.getRealname())){
			fillInNum=fillInNum+1;
		}
		if(user.getCardno()!=null&&!"".equals(user.getCardno())){
			fillInNum=fillInNum+1;
		}
		if(user.getSex()!=null&&!"".equals(user.getSex())){
			fillInNum=fillInNum+1;
		}
		if(user.getBindMobile()!=null&&!"".equals(user.getBindMobile())){
			fillInNum=fillInNum+1;
		}
		if(user.getPhone()!=null&&!"".equals(user.getPhone())){
			fillInNum=fillInNum+1;
		}
		if(user.getEmail()!=null&&!"".equals(user.getEmail())){
			fillInNum=fillInNum+1;
		}
		if(user.getArea()!=null&&!"".equals(user.getArea())){
			fillInNum=fillInNum+1;
		}
		if(user.getUserAddress()!=null&&!"".equals(user.getUserAddress())){
			fillInNum=fillInNum+1;
		}
		if(userInfo.getAddress()!=null&&!"".equals(userInfo.getAddress())){
			fillInNum=fillInNum+1;
		}
		if(userInfo.getOrganization()!=null&&!"".equals(userInfo.getOrganization())){
			fillInNum=fillInNum+1;
		}
		if(userInfo.getTitle()!=null&&!"".equals(userInfo.getTitle())){
			fillInNum=fillInNum+1;
		}
		if(userInfo.getDegree()!=null&&!"".equals(userInfo.getDegree())){
			fillInNum=fillInNum+1;
		}
		map.put("fillInNum", fillInNum);
		// 学霸的积分、名称
		Long oneLevelIntegral = Long.parseLong(ConfigUtil.getConfig("one_level_integral"));
		String oneLevelName = ConfigUtil.getConfig("one_level_name");
		// 大学生的积分、名称
		Long twoLevelIntegral = Long.parseLong(ConfigUtil.getConfig("two_level_integral"));
		String twoLevelName = ConfigUtil.getConfig("two_level_name");
		// 中学生的积分、名称
		Long threeLevelIntegral = Long.parseLong(ConfigUtil.getConfig("three_level_integral"));
		String threeLevelName = ConfigUtil.getConfig("three_level_name");
		// 小学生的积分、名称
		Long fourLevelIntegral = Long.parseLong(ConfigUtil.getConfig("four_level_integral"));
		String fourLevelName = ConfigUtil.getConfig("four_level_name");
		String levelName="";
		if(integral>=fourLevelIntegral&&integral<threeLevelIntegral){
			levelName=fourLevelName;
		}else if(integral>=threeLevelIntegral&&integral<twoLevelIntegral){
			levelName=threeLevelName;
		}else if(integral>=twoLevelIntegral&&integral<oneLevelIntegral){
			levelName=twoLevelName;
		}else if(integral>=oneLevelIntegral){
			levelName=oneLevelName;
		}
		map.put("levelName",levelName);
		map.put("levelName1",levelName.substring(levelName.length()-3));
		//余额
		map.put("balance",user.getBalance());
		//积分
		map.put("surplusIntegral",user.getSurplusIntegral());
		map.put("totalIntegral",integral);//累计积分
		return map;
	}
	
	public List<EUserAddress> getPageByUserId(String userId) {
		List<EUserAddress> list = this.userDao.getPageByUserId(userId);
		for (int i = 0; i < list.size(); i++) {
			EUserAddress userAddress = list.get(i);
			String area = userAddress.getArea();
			if(area!=null){
				if(area.length()==2){
					SysProvince sysProvince = this.userDao.getProvince(area);
					userAddress.setProvinceCity(sysProvince.getCityname());
				}
				if(area.length()==4){
					String cityName = "";
					SysProvince sysProvince = this.userDao.getProvince(area.substring(0, 2));
					cityName += sysProvince.getCityname();
					SysProvince sysProvince1 = this.userDao.getProvince(area);
					cityName += sysProvince1.getCityname();
					userAddress.setProvinceCity(cityName);
				}
				if(area.length()==6){
					String cityName = "";
					SysProvince sysProvince = this.userDao.getProvince(area.substring(0, 2));
					cityName += sysProvince.getCityname()+"  ";
					SysProvince sysProvince1 = this.userDao.getProvince(area.substring(0, 4));
					cityName += sysProvince1.getCityname()+"市  ";
					SysProvince sysProvince2 = this.userDao.getProvince(area);
					cityName += sysProvince2.getCityname();
					userAddress.setProvinceCity(cityName);
				}
			}
		}
		return list;
	}
	public List<EUserAddress> findDefault(String userId) {
		return this.userDao.findDefault(userId);
	}
	public void updateAddress(EUserAddress userAddress) {
		this.userDao.updateAddress(userAddress);
	}
	public void saveAddress(EUserAddress userAddress) {
		this.userDao.saveAddress(userAddress);
	}
	public EUserAddress findById(String addressId) {
		EUserAddress userAddress = this.userDao.findById(addressId);
		String area = userAddress.getArea();
		if(area!=null){
			if(area.length()==2){
				SysProvince sysProvince = this.userDao.getProvince(area);
				userAddress.setProvinceCity(sysProvince.getCityname());
			}
			if(area.length()==4){
				String cityName = "";
				SysProvince sysProvince = this.userDao.getProvince(area.substring(0, 2));
				cityName += sysProvince.getCityname();
				SysProvince sysProvince1 = this.userDao.getProvince(area);
				cityName += sysProvince1.getCityname();
				userAddress.setProvinceCity(cityName);
			}
			if(area.length()==6){
				String cityName = "";
				SysProvince sysProvince = this.userDao.getProvince(area.substring(0, 2));
				cityName += sysProvince.getCityname()+"  ";
				SysProvince sysProvince1 = this.userDao.getProvince(area.substring(0, 4));
				cityName += sysProvince1.getCityname()+"市  ";
				SysProvince sysProvince2 = this.userDao.getProvince(area);
				cityName += sysProvince2.getCityname();
				userAddress.setProvinceCity(cityName);
			}
		}
		return userAddress;
		
		
	}
	public String degree (String type){
		String degree = "";
		if("01".equals(type)){
			degree = "大专以下";
		} else if("02".equals(type)){
			degree = "大专";
		} else if("03".equals(type)){
			degree = "本科";
		} else if("04".equals(type)){
			degree = "硕士";
		} else if("05".equals(type)){
			degree = "博士";
		} else if("09".equals(type)){
			degree = "其他";
		} else {
			degree = "";
		}
		return degree;
	}
	public void delAddress(String addressId) {
		this.userDao.delAddress(addressId);
	}
	public SysUserInfo findUserInfoByUserId(String userid){
		return userDao.findUserInfoByUserId(userid);
	}
	public String updateUser(SysUser user){
		return this.userDao.saveUser(user);
	}
	public String saveUserInfo(SysUserInfo userInfo){
		return this.userDao.saveUserInfo(userInfo);
	}
	public IntContrast findByCodeNo(String codeNo) {
		return userDao.findByCodeNo(codeNo);
	}
	public IntIntegral findIntIntegal(String userid, String codeid) {
		return userDao.findIntIntegal(userid,codeid);
	}
	public Map<String, Object> findSign(String userid, String date, String date1 ,Map<String, Object> map) {
		List signList=userDao.findSign(userid,date);
		List integralList=userDao.findIntegral(userid,date);
		map.put("signList",signList);
		map.put("integralList",integralList);
		int number = 0;
		if(signList != null){
			number=signList.size();
		}
		map.put("number",String.valueOf(number));
		//查看当天是否已签到
		List list=userDao.findSign(userid, date1);
		map.put("list",list);
		//查看总的奖励
		Long integralval=(long) 0;
		if(!integralList.isEmpty()){
			for(int i=0;i<integralList.size();i++){
				IntIntegral s=(IntIntegral) integralList.get(i);
				integralval=integralval+s.getIntegral();
			}
		}
		map.put("integralval",integralval);
		//系统时间
		map.put("sysdate",date1);
		return map;
	}
	
	public Map<String, Object> doSign(String userid, String date, String date1, String ip ,Map<String, Object> map,String equipment) {
		List signList=userDao.findSign(userid,date);
		if(!signList.isEmpty()){
			SysUser sysUser = this.userDao.findUserByUserId(userid);
			//查看当天已签到
			List list =userDao.findSignTime(userid,date1);
			if(list.isEmpty()){
				if(signList.size()==4){
					integralService.addIntegral("gc006", userid, ip,equipment);
				}
				else if(signList.size()==9){
					integralService.addIntegral("gc007", userid, ip,equipment);
				}
				else if(signList.size()==19){
					integralService.addIntegral("gc008", userid, ip,equipment);
				}else{
					Sign sign =new Sign();
					sign.setSysUser(sysUser);
					sign.setReward((long)0);
					sign.setTime(date1);
					sign.setEquipment("web");
					sign.setDescribe("签到"+((signList.size())+1)+"天");
					userDao.saveSign(sign);
				}
			}
		}else{
			integralService.addIntegral("gc005", userid, ip,equipment);
		}
		map.put("result", "1");
		return map;
	}
}
