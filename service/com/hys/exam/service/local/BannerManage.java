package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.Advert;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;
/**
 * BannerManage   banner管理的service接口
 * @author weeho
 *
 */
public interface BannerManage extends BaseService {
	Advert getAdvertById(Long id);
	List<Advert> getAdvertList(Advert advert);
	boolean addAdvert(Advert advert);
	boolean updateState(Long id, int state);
	boolean deleteAdvertById(Long id);	 
	boolean updateAdvert(Advert advert);
	void getAdvertPageList(PageList pl, Advert advert);
	/**
	 * 排序操作
	 * @param orderstr
	 * @return
	 */
	boolean resortOrderNum(String orderstr); 
	/**
	 * 根据标题查询
	 * @param title
	 * @return
	 */
	int getAdvertByName(String name);
	public List<SystemSite> getSiteListByBannerId(long id);
	void updateImageById(Long id, String url);
	int getAdvertByState(Integer state, Integer type);
}
