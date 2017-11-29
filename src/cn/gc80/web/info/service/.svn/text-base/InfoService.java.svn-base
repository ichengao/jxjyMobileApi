package cn.gc80.web.info.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.gc80.base.page.PageHolder;
import cn.gc80.datamodel.info.AdPicture;
import cn.gc80.datamodel.info.InfoMessage;
import cn.gc80.web.info.dao.InfoDao;

@Service("infoService")
public class InfoService {
	@Resource
	private InfoDao infoDao;
	
	public PageHolder findMessageBySome(String codeNo, String area,PageHolder ph) {
		return this.infoDao.findMessageBySome(codeNo, area, ph);
	}

	public Map<String, Object> findInfoMessageDetailMap(String name,String codeNo) {
		Map<String,Object> map =new HashMap<String, Object>();
		if (name != null && !name.equals("")) {
			InfoMessage infoMsg = this.infoDao.findMessage(codeNo, name);
			if (infoMsg != null) {
				String infoid = infoMsg.getId();
				map.put("title", infoMsg.getTitle());
				map.put("publishtime", infoMsg.getPublishtime());
				map.put("infoContent", infoMsg.getInfoContent());
				map.put("publishfrom", infoMsg.getPublishfrom());
				// 上一篇/下一篇
				if (codeNo != null && !"".equals(codeNo)) {
					InfoMessage lasttTextMsg = null;
					InfoMessage nextTextMsg = null;
					List<InfoMessage> list = this.infoDao.findMessageByCodeNo(codeNo);
					for (int i = 0; i < list.size(); i++) {
						InfoMessage infoMes = list.get(i);
						if (infoid.equals(infoMes.getId())) {
							if (i > 0) {
								lasttTextMsg = (InfoMessage) list.get(i - 1);
							}
							if (i < list.size() - 1) {
								nextTextMsg = (InfoMessage) list.get(i + 1);
							}
						}
					}
					if (lasttTextMsg != null){
						map.put("lasttTextTitle", lasttTextMsg.getTitle());
						map.put("lasttTextName", lasttTextMsg.getName());
					}else{
						map.put("lasttTextMsg",null);
					}
					if (nextTextMsg != null){
						map.put("nextTextTitle", nextTextMsg.getTitle());
						map.put("nextTextName", nextTextMsg.getName());
					}else{
						map.put("nextTextMsg", null);
					}
					List<Map<String, Object>> relatedInfoMessageList = new ArrayList<Map<String,Object>>();
					List<InfoMessage> list1 = this.infoDao.findMessageBySome(codeNo,3);
					for (int i = 0; i < list1.size(); i++) {
						Map<String, Object> m =new HashMap<String, Object>();
						InfoMessage infoMes = list1.get(i);
						m.put("title", infoMes.getTitle());
						m.put("name", infoMes.getName());
						relatedInfoMessageList.add(m);
					}
					map.put("relatedInfoMessageList", relatedInfoMessageList);
				}
			}
		}
		return map;
	}
	
	public List<AdPicture> findPicture(int num){
		return this.infoDao.findPicture(num);
	}

	public Map<String, Object> findPicMessageMap(String picId) {
		Map<String,Object> map =new HashMap<String, Object>();
		if (picId != null && !picId.equals("")) {
			AdPicture picMsg = this.infoDao.findPictureById(picId);
			if (picMsg != null) {
				map.put("picMsg", picMsg);
				// 上一篇/下一篇
				AdPicture lastTextPic= null;
				AdPicture nextTextPic = null;
				List<AdPicture> list = this.infoDao.findAllPicture();
				for (int i = 0; i < list.size(); i++) {
					AdPicture picMsg1 = list.get(i);
					if (picId.equals(picMsg1.getId())) {
						if (i > 0) {
							lastTextPic = list.get(i - 1);
						}
						if (i < list.size() - 1) {
							nextTextPic = list.get(i + 1);
						}
					}
				}
				if (lastTextPic != null){
					map.put("lastTextTitle", lastTextPic.getPicTitle());
					map.put("lastTextId", lastTextPic.getId());
				}else{
					map.put("lastTextPic",null);
				}
				if (nextTextPic != null){
					map.put("nextTextTitle", nextTextPic.getPicTitle());
					map.put("nextTextId", nextTextPic.getId());
				}else{
					map.put("nextTextPic", null);
				}
				List<Map<String, Object>> relatedPicList = new ArrayList<Map<String,Object>>();
				List<AdPicture> list1 = this.infoDao.findPicture(3);
				for (int i = 0; i < list1.size(); i++) {
					Map<String, Object> m =new HashMap<String, Object>();
					AdPicture pic = list1.get(i);
					m.put("title", pic.getPicTitle());
					m.put("id", pic.getId());
					relatedPicList.add(m);
				}
				map.put("relatedPicList", relatedPicList);
			}
		}
		return map;
	}
	
}
