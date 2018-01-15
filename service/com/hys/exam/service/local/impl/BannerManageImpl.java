package com.hys.exam.service.local.impl;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;

import com.hys.exam.dao.local.BannerManageDAO;
import com.hys.exam.model.Advert;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.BannerManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

public class BannerManageImpl extends BaseMangerImpl implements BannerManage {

	private BannerManageDAO localBannerManageDAO;
	
	@Override
	public Advert getAdvertById(Long id) {
		return localBannerManageDAO.getAdvertById(id);
	}

	@Override
	public List<Advert> getAdvertList(Advert advert) {
		return localBannerManageDAO.getAdvertList(advert);
	}

	@Override
	public boolean addAdvert(Advert advert) {
		if(localBannerManageDAO.addAdvert(advert))
			return true;
		else
			return false;
	}

	@Override
	public boolean updateState(Long id, int state) {
		return localBannerManageDAO.updateState(id, state);
	}

	@Override
	public boolean deleteAdvertById(Long id) {
		return localBannerManageDAO.deleteAdvertById(id);
	}

	@Override
	public boolean updateAdvert(Advert advert) {
		localBannerManageDAO.updateAdvert(advert);
		return true;
	}

	@Override
	public void getAdvertPageList(PageList pl, Advert advert) {
		//模糊查询banner
		localBannerManageDAO.getAdvertPageList(pl, advert);
		//设置banner对于的站点信息
		List<Advert> list=pl.getList();
		for(Advert adv:list){
			adv.setSiteList(getSiteListByBannerId(adv.getId()));
		}
	
	}

	@Override
	public int getAdvertByName(String name) {
		return localBannerManageDAO.getAdvertByName(name);
	}

	
	
	@Override
	public List<SystemSite> getSiteListByBannerId(long id) {
		return localBannerManageDAO.getSiteListByBannerId(id);
	}
	
	public void setLocalBannerManageDAO(BannerManageDAO localBannerManageDAO) {
		this.localBannerManageDAO = localBannerManageDAO;
	}

	public BannerManageDAO getLocalBannerManageDAO() {
		return localBannerManageDAO;
	}

	//排序操作
	@Override
	public boolean resortOrderNum(String orderstr) {
		boolean flag = false;
		try{
			flag = localBannerManageDAO.resortOrderNum(orderstr);
		}
		catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public void updateImageById(Long id, String url) {
		localBannerManageDAO.updateImageById(id,url);
	}

	@Override
	public int getAdvertByState(Integer state,Integer type) {
		return localBannerManageDAO.getAdvertByState(state,type);
	}


}
