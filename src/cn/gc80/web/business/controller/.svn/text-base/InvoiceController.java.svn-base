package cn.gc80.web.business.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import cn.gc80.base.BaseController;
import cn.gc80.datamodel.business.EOrderInvoice;
import cn.gc80.web.business.service.InvoiceService;

@Controller
public class InvoiceController extends BaseController {
	@Resource
	private InvoiceService invoiceService;

	/** 发票列表
	 * 
	 * @param request
	 * @param response
	 * @param invoiceState 发票状态（00 发票作废,01 打印中,02 已打印,03 已领取,04 未打印,05 已寄送,06 未领取）
	 * @return
	 */
	@RequestMapping("/invoice/toEOrderInvoiceList.do")
	public ModelAndView toEOrderInvoiceList(HttpServletRequest request, HttpServletResponse response, String invoiceState) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		// 将过滤器中存入的payload数据取出来
		String userId = (String) request.getAttribute("userId");
		List<EOrderInvoice> eOrderInvoiceList = this.invoiceService.findEOrderInvoice(userId, invoiceState);
		if (eOrderInvoiceList != null && eOrderInvoiceList.size() > 0) {
			map.put("result", 1);
			map.put("eOrderInvoiceList", eOrderInvoiceList);
		} else {
			map.put("result", 0);
		}
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}

	// 发票详情
	@RequestMapping("/invoice/toEOrderInvoiceDetail.do")
	public ModelAndView toEOrderInvoiceDetail(HttpServletRequest request, HttpServletResponse response, String invoiceId) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = getResultMap(request, response);
		EOrderInvoice eOrderInvoice = this.invoiceService.findEOrderInvoiceById(invoiceId);
		map.put("eOrderInvoice", eOrderInvoice);
		view.setAttributesMap(map);
		modelAndView.setView(view);
		return modelAndView;
	}

}
