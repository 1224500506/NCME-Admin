package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.Advert;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.PageList;

public interface BannerManageDAO {

	Advert getAdvertById(Long id);
	List<Advert> getAdvertList(Advert advert);
	boolean addAdvert(Advert advert);
	boolean updateState(Long id, int state);
	boolean deleteAdvertById(Long id);	 
	boolean updateAdvert(Advert advert);
	void getAdvertPageList(PageList pl, Advert advert);
	/**
	 * 根据标题查询
	 * @param title
	 * @return
	 */
	int getAdvertByName(String name);
	/**
	 *根据bannerid查询站点信息
	 * @param id
	 * @return
	 */
	public List<SystemSite> getSiteListByBannerId(long id);
	/**
	 * 排序操作
	 * @param orderstr
	 * @return
	 */
	boolean resortOrderNum(String orderstr);
	void updateImageById(Long id, String url);
	int getAdvertByState(Integer state, Integer type); 
}
