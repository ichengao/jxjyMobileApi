package cn.gc80.web.business.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.gc80.base.hibernate.HibernateDao;
import cn.gc80.datamodel.business.EOrderInvoice;

@Repository("invoiceDao")
public class InvoiceDao {
	@Resource
	private HibernateDao hibernateDao;

	@SuppressWarnings("unchecked")
	public List<EOrderInvoice> getInvoiceList(String userId, String invoiceState) {
		StringBuffer hsql = new StringBuffer(" from EOrderInvoice where sysUser.id=:userId and delflag='02' and invoiceType='01'");
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		if (userId != null && !"".equals(userId)) {
			map.put("userId", userId);
			if (invoiceState != null && !"".equals(invoiceState)) {
				hsql.append(" and invoiceState=:invoiceState");
				map.put("invoiceState", invoiceState);
			}
			return this.hibernateDao.queryObjects(hsql.toString(), map);
		} else {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public EOrderInvoice findEOrderInvoiceById(String invoiceId) {
		StringBuffer hsql = new StringBuffer(" from EOrderInvoice where delflag='02'");
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		EOrderInvoice eOrderInvoice = null;
		if (invoiceId != null && !"".equals(invoiceId)) {
			hsql.append(" and id=:invoiceId");
			map.put("invoiceId", invoiceId);
			List list = this.hibernateDao.queryObjects(hsql.toString(), map);
			if (list != null && !list.isEmpty()) {
				eOrderInvoice = (EOrderInvoice) list.get(0);
			}
		}
		return eOrderInvoice;
	}
	
}
