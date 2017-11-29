package cn.gc80.web.business.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.gc80.datamodel.business.EOrderExpress;
import cn.gc80.datamodel.business.EOrderInvoice;
import cn.gc80.datamodel.sysbase.SysProvince;
import cn.gc80.datamodel.sysbase.SysUser;
import cn.gc80.web.business.dao.InvoiceDao;
import cn.gc80.web.user.dao.UserDao;

@Service("invoiceService")
public class InvoiceService {
	@Resource
	private InvoiceDao invoiceDao;
	@Resource
	private UserDao userDao;

	public List<EOrderInvoice> findEOrderInvoice(String userId, String invoiceState) {
		List<EOrderInvoice> list = this.invoiceDao.getInvoiceList(userId, invoiceState);
		if(list !=null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).geteOrderExpress() == null) {
					list.get(i).seteOrderExpress(new EOrderExpress());
				}
			}
		}
		return list;
	}

	public EOrderInvoice findEOrderInvoiceById(String invoiceId) {
		EOrderInvoice eOrderInvoice = invoiceDao.findEOrderInvoiceById(invoiceId);
		if (eOrderInvoice == null) {
			eOrderInvoice = new EOrderInvoice();
		}
		if (eOrderInvoice.geteOrderExpress() != null) {
			String addressArea = "";
			String area = eOrderInvoice.geteOrderExpress().getAddressArea();
			if (area != null && !"".equals(area)) {
				if (area.length() > 4) {
					SysProvince sysProvince1 = userDao.findSysProvinceByCodeid(area.substring(0, 2));
					if (sysProvince1 != null) {
						addressArea = sysProvince1.getCityname();
					}
					SysProvince sysProvince2 = userDao.findSysProvinceByCodeid(area.substring(0, 4));
					if (sysProvince2 != null) {
						addressArea += sysProvince2.getCityname();
					}
					SysProvince sysProvince3 = userDao.findSysProvinceByCodeid(area);
					if (sysProvince3 != null) {
						addressArea += sysProvince3.getCityname();
					}
				} else if (area.length() > 2) {
					SysProvince sysProvince1 = userDao.findSysProvinceByCodeid(area.substring(0, 2));
					if (sysProvince1 != null) {
						addressArea = sysProvince1.getCityname();
					}
					SysProvince sysProvince2 = userDao.findSysProvinceByCodeid(area);
					if (sysProvince2 != null) {
						addressArea += sysProvince2.getCityname();
					}
				} else {
					SysProvince sysProvince = userDao.findSysProvinceByCodeid(area);
					if (sysProvince != null) {
						addressArea = sysProvince.getCityname();
					}
				}
			}
			eOrderInvoice.geteOrderExpress().setAddressArea(addressArea);
		} else {
			eOrderInvoice.seteOrderExpress(new EOrderExpress());
		}
		if (eOrderInvoice.getSysUser() == null) {
			eOrderInvoice.setSysUser(new SysUser());
		}
		if (eOrderInvoice.getSysUserLead() == null) {
			eOrderInvoice.setSysUserLead(new SysUser());
		}
		return eOrderInvoice;
	}
	


}
