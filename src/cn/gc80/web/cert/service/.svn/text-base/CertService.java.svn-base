package cn.gc80.web.cert.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import cn.gc80.base.util.ConfigUtil;
import cn.gc80.base.util.DateTime;
import cn.gc80.datamodel.business.EOrderDetail;
import cn.gc80.datamodel.sysbase.SysCertProvice;
import cn.gc80.datamodel.sysbase.SysProvince;
import cn.gc80.datamodel.sysbase.SysUser;
import cn.gc80.datamodel.sysbase.SysUserInfo;
import cn.gc80.datamodel.training.TrainCert;
import cn.gc80.datamodel.training.TrainClass;
import cn.gc80.datamodel.training.TrainCredit;
import cn.gc80.web.cert.dao.CertDao;

@Service("certService")
public class CertService {
	@Resource
	private CertDao certDao;

	public TrainCert getCertByCertNo(String certNo) {
		if (certNo == null || "".equals(certNo)) {
			return null;
		}
		return this.certDao.getCertByCertNo(certNo);
	}

	@SuppressWarnings("rawtypes")
	private TrainCert findCertDetailsById(String id) {
		TrainCert cert = this.certDao.findCertDetailsById(id);
		if (cert != null && cert.getTrainClass() != null) {
			List creditList = this.certDao.findCertDetailList(cert.getTrainClass().getId(), cert.getSysUser().getId());
			cert.setCreditList(creditList);
		}
		return cert;
	}

	public TrainCert findCertDetailsByCertNo(String certNo) {
		if (certNo == null || "".equals(certNo)) {
			return null;
		}
		return this.certDao.findCertDetailsByCertNo(certNo);
	}
	
	public List<TrainCert> findCertList(String realname, String cardno) {
		return this.certDao.findCertList(realname, cardno);
	}
	
	public TrainCert findCert(String certNo){
		return this.certDao.getCertByCertNo(certNo);
	}
	

	public List<Map<String,Object>> findCredit(String realname, String cardNo) {
		List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
		List<EOrderDetail> orderDetailList = this.certDao.findEOrderDetail(realname, cardNo);
		for (int i = 0; i < orderDetailList.size(); i++) {
			EOrderDetail orderDetail = orderDetailList.get(i);
			Map<String,Object> map=new HashMap<String, Object>();
			if (orderDetail.getTrainClass() != null) {
				map.put("id", orderDetail.getId());
				map.put("className", orderDetail.getTrainClass().getClassName());
				list.add(map);
			}
		}
		return list;
	}
	
	public Map<String,Object> findCreditById(String odId) {
		Map<String,Object> map=new HashMap<String, Object>();
		EOrderDetail od=this.certDao.findEOrderDetailById(odId);
		if(od!=null){
			if(od.getTrainClass()!=null){
				map.put("classId", od.getTrainClass().getId());
				map.put("className", od.getTrainClass().getClassName());
				map.put("codeName", od.getTrainClass().getTrainProject().getSysCode().getSysCode().getCodeName());
			}
			if(od.getSysUser()!=null){
				map.put("userId", od.getSysUser().getId());
				map.put("realname", od.getSysUser().getRealname());
				map.put("cardno", od.getSysUser().getCardno());
			}
			map.put("year", od.getYear());
			if(od.getTrainClass()!=null&&od.getSysUser()!=null){
				long hasCredit = this.certDao.findCreditCount(od.getSysUser().getId(), od.getTrainClass().getId());
				map.put("hasCredit", hasCredit);
			}
		}
		return map;
	}
	

	public EOrderDetail findOrderDetailByClassId(String classId, String userId) {
		return this.certDao.findOrderDetailByClassId(classId, userId);
	}

	public TrainClass findTrainClassById(String classid) {
		return this.certDao.findTrainClassById(classid);
	}

	public List<TrainCredit> findCreditList(String realname, String cardno) {
		return this.certDao.findCreditList(realname,cardno);
	}

	public TrainCert getCertById(String id) {
		return this.certDao.getCertById(id);
	}

	public Map<String,Object> findCertMap(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		TrainCert trainCert = this.findCertDetailsById(id);
		if(trainCert!=null){
			List creditList1 = this.certDao.findCertDetailList(trainCert.getTrainClass().getId(),trainCert.getSysUser().getId());
			trainCert.setCreditList(creditList1);
			EOrderDetail orderDetail = this.findOrderDetailByClassId(trainCert.getTrainClass().getId(), trainCert.getSysUser().getId());
			if (orderDetail != null) {
				map.put("studyNo", orderDetail.getStudyNo());
				map.put("realname", trainCert.getRealname());
				map.put("cardno", trainCert.getCardno());
				map.put("sex", trainCert.getSysUser().getSex());
				map.put("organization", trainCert.getOrganization());
				map.put("userphoto", trainCert.getSysUser().getUserphoto());
				map.put("certName", trainCert.getTrainClass().getClassName());
				String startYear = "";
				String startMonth = "";
				String startDay = "";
				String endYear = "";
				String endMonth = "";
				String endDay = "";
				String createYear = "";
				String createMonth = "";
				String createDay = "";
				String starttime = trainCert.getStarttime();
				String finishtime = trainCert.getFinishtime();
				String certdate = trainCert.getCreatetime();
				if (starttime != null && !"".equals(starttime)) {
					String[] tempStart = starttime.substring(0, 10).split("-");
					startYear = tempStart[0];
					startMonth = tempStart[1];
					startDay = tempStart[2];
				}
				if (finishtime != null && !"".equals(finishtime)) {
					String[] tempEnd = finishtime.substring(0, 10).split("-");
					endYear = tempEnd[0];
					endMonth = tempEnd[1];
					endDay = tempEnd[2];
				}
				if(certdate!=null&&!"".equals(certdate)){
					if(certdate.indexOf("年")>0){
						String[] temp = certdate.split("年");
						createYear = temp[0];
						if(temp[1]!=null&&!"".equals(temp[1])){
							String[] temp1 = temp[1].split("月");
							createMonth = temp1[0];
							if(temp1[1]!=null&&!"".equals(temp1[1])){
								String[] temp2 = temp1[1].split("日");
								createDay = temp2[0];
							}
						}
					}
					else{
						String[] temp = certdate.split("-");
						createYear = temp[0];
						createMonth = temp[1];
						createDay = temp[2].substring(0,2);
					}
				}
				map.put("startYear", startYear);
				map.put("startMonth", startMonth);
				map.put("startDay", startDay);
				map.put("endYear", endYear);
				map.put("endMonth", endMonth);
				map.put("endDay", endDay);
				map.put("createYear", createYear);
				map.put("createMonth", createMonth);
				map.put("createDay", createDay);
				map.put("credit", trainCert.getCredit());
				List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
				List<TrainCredit> creditList = trainCert.getCreditList();
				for(int i=0;i<creditList.size();i++){
					TrainCredit credit=creditList.get(i);
					Map<String, Object> m= new HashMap<String, Object>();
					m.put("courseName", credit.getCourseName());
					m.put("credit", credit.getCredit());
					list.add(m);
				}
				map.put("list", list);
				map.put("ewm", ConfigUtil.getConfig("ewm_url")+"/member/web_toEwm.page?certid="+id);
			}
		}
		return map;
	}

	public TrainCredit getCreditById(String id) {
		return this.certDao.getCreditById(id);
	}

	public Map<String, Object> findCreditMap(String userId, String classId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null && !userId.equals("") && classId != null&& !classId.equals("")) {
			TrainClass trainClass=this.certDao.findTrainClassById(classId);
			if(trainClass!=null){
				map.put("className", trainClass.getClassName());
				map.put("year", trainClass.getClassYear());
			}
			SysUser user = this.certDao.findUser(userId);
			if (user != null) {
				map.put("realname", user.getRealname());
				map.put("cardno", user.getCardno());
				map.put("userphoto", user.getUserphoto());
			}
			//完成学时
			long totalCredit = this.certDao.findCreditSum(userId, classId);
			map.put("totalCredit", totalCredit);
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			List<TrainCredit> creditList = certDao.findCredit(userId, classId);
			for(int i=0;i<creditList.size();i++){
				TrainCredit credit=creditList.get(i);
				Map<String, Object> m= new HashMap<String, Object>();
				m.put("codeName", credit.getTrainClass().getTrainProject().getSysCode().getSysCode().getCodeName());
				m.put("courseName", credit.getCourseName());
				m.put("credit", credit.getCredit());
				list.add(m);
			}
			map.put("list", list);
			map.put("ewm", ConfigUtil.getConfig("ewm_url")+"/member/web_toXszmEwm.page?userid="+userId+"&classid="+classId);
			map.put("newData", DateTime.getCurrentDate());
		}
		return map;
	}

	public List<TrainCert> findCertList(String cardno) {
		return this.certDao.findCertList(cardno);
	}
	
	public TrainCert getCertDetailById(String id) {
		return this.certDao.getCertDetailById(id);
	}
	
	public SysCertProvice findCertProvince(String userid){
		SysUser user = this.certDao.findUser(userid);
		if(user!=null&&user.getArea()!=null&&!user.getArea().equals("")){
			SysCertProvice cp = this.certDao.findCertProvince(user.getArea());
			return cp;
		}
		return null;
	}
	
	public SysProvince findSysProvinceCodeid(String id) {
		return this.certDao.findSysProvinceCodeid(id);
	}
}