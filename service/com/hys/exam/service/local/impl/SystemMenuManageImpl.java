package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CaseDAO;
import com.hys.exam.dao.local.ExamImportQuestAttDAO;
import com.hys.exam.dao.local.SystemMenuManageDAO;
import com.hys.exam.model.*;
import com.hys.exam.model.system.SystemLog;
import com.hys.exam.model.system.SystemMenu;
import com.hys.exam.service.local.CaseManage;
import com.hys.exam.service.local.ExamImportQuestManage;
import com.hys.exam.service.local.SystemMenuManage;
import com.hys.framework.service.impl.BaseMangerImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class SystemMenuManageImpl extends BaseMangerImpl implements
		SystemMenuManage {

	private SystemMenuManageDAO localSystemMenuManageDAO;
	public SystemMenuManageDAO getLocalSystemMenuManageDAO() {
		return localSystemMenuManageDAO;
	}
	public void setLocalSystemMenuManageDAO(
			SystemMenuManageDAO localSystemMenuManageDAO) {
		this.localSystemMenuManageDAO = localSystemMenuManageDAO;
	}
	
	public List<SystemMenu> getMenuList(SystemMenu searchMenu)
	{
		return localSystemMenuManageDAO.getMenuList(searchMenu);
	}
	public List<SystemLog> getSystemLogList(SystemLog systemLog)
	{
		return localSystemMenuManageDAO.getSystemLogList(systemLog);
	}
	public HSSFWorkbook export(List<SystemLog> list){
		return localSystemMenuManageDAO.export(list);
	}
	@Override
	public boolean deleteSystemLog(Long id) {
		return localSystemMenuManageDAO.deleteSystemLog(id);
	}
	public Boolean addMenu(SystemMenu menu)
	{
		return localSystemMenuManageDAO.addMenu(menu);
	}
	public Boolean updateMenu(SystemMenu menu)
	{
		return localSystemMenuManageDAO.updateMenu(menu);
	}
	public Boolean updateState(Integer id,Integer state)
	{
		return localSystemMenuManageDAO.updateState(id, state);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-08
	 * @return   List<SystemMenu>
	 * 方法说明： 查询培训后台显示的一级菜单
	 */
	public List<SystemMenu> getOneLevelMenu(){
		return localSystemMenuManageDAO.getOneLevelMenu();
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-08
	 * @return   List<SystemMenu>
	 * 方法说明： 根据一级菜单查询培训后台显示的二级菜单
	 */
	public List<SystemMenu> getTwoLevelMenu(String name,long userId){
		return localSystemMenuManageDAO.getTwoLevelMenu(name,userId);
	}
	public List<SystemMenu> getAllMenu(){
		return localSystemMenuManageDAO.getALLMenu();
	}
	@Override
	public Boolean getSystemMenuByName(String name) {
		List<SystemMenu> list = localSystemMenuManageDAO.getSystemMenuByName(name);
		if (list.size()>0) {
			return false;
		}else{
			return true;
		}
		
	}
}
