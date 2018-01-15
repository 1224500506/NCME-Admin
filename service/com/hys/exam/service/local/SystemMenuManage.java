package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.system.SystemLog;
import com.hys.exam.model.system.SystemMenu;
import com.hys.framework.service.BaseService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface SystemMenuManage extends BaseService {
	public List<SystemMenu> getMenuList(SystemMenu searchMenu);
	public List<SystemLog> getSystemLogList(SystemLog systemLog);
	public HSSFWorkbook export(List<SystemLog> list);
	boolean deleteSystemLog(Long id);
	public Boolean addMenu(SystemMenu menu);
	public Boolean updateMenu(SystemMenu menu);
	public Boolean updateState(Integer id,Integer state);
	
	/**
	 * @author   张建国
	 * @time     2017-01-08
	 * @return   List<SystemMenu>
	 * 方法说明： 查询培训后台显示的一级菜单
	 */
	public List<SystemMenu> getOneLevelMenu();
	
	/**
	 * @author   张建国
	 * @time     2017-01-08
	 * @return   List<SystemMenu>
	 * 方法说明： 根据一级菜单查询培训后台显示的二级菜单
	 */
	public List<SystemMenu> getTwoLevelMenu(String name,long userId);

	/**
	 * 获取全部菜单
	 * @return
	 */
	public List<SystemMenu> getAllMenu();
	/**
	 * @author   刘金明
	 * @time     2017-06-19
	 * @return   Boolean
	 * 方法说明： 判断菜单名称是否存在
	 */
	public Boolean getSystemMenuByName(String name);
}
