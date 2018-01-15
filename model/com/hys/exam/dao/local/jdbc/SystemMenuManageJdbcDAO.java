package com.hys.exam.dao.local.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.system.SystemLog;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.SystemMenuManageDAO;
import com.hys.exam.model.system.SystemMenu;
import com.hys.exam.utils.StringUtils;


public class SystemMenuManageJdbcDAO extends BaseDao
		implements SystemMenuManageDAO {
	
	
	public List<SystemMenu> getMenuList(SystemMenu searchMenu){
		List values = new ArrayList();
		StringBuilder sql = new StringBuilder();		
		sql.append("select * from system_menu where id > 0");
		
		if (searchMenu.getName() != null && !searchMenu.getName().equals("")) {
			sql.append(" AND name like ?");
			values.add("%"+searchMenu.getName().trim()+"%");
		}
		if (searchMenu.getSystem_type() != null && !searchMenu.getSystem_type().equals("")) {
			sql.append(" AND system_type like ?");
			values.add("%"+searchMenu.getSystem_type().trim()+"%");
		}
		if (searchMenu.getMenu_type() != null && !searchMenu.getMenu_type().equals("")) {
			sql.append(" AND menu_type like ?");
			values.add("%"+searchMenu.getMenu_type().trim()+"%");
		}
		if(searchMenu.getState() != null)
		{
			sql.append(" AND state =").append(searchMenu.getState());
		}
		sql.append(" order by system_type,menu_type,id");
		return getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(SystemMenu.class), values.toArray());
	}
	public List<SystemLog> getSystemLogList(SystemLog systemLog){
		List values = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  t.operate_id,t.visit_ip,t.request_url,u.ACCOUNT_NAME operate_login_name,t.operate_type,t.operate_context,t.operate_platform,t.module_name,date_format(t.operate_time,'%Y-%m-%d %H:%i:%s') operate_time,t.create_time\n" +
				" from system_operate_log t ,system_account u where  t.user_id=u.user_id  ");

		if (systemLog.getOperate_login_name() != null && !systemLog.getOperate_login_name().equals("")) {
			sql.append(" AND operate_login_name like ?");
			values.add("%"+ systemLog.getOperate_login_name().trim()+"%");
		}
		if (systemLog.getStart_date() != null && !systemLog.getStart_date().equals("")) {
			sql.append(" AND operate_time > ?");
			values.add(systemLog.getStart_date().trim());
		}
		if (systemLog.getEnd_date() != null && !systemLog.getEnd_date().equals("")) {
			sql.append(" AND operate_time < ?");
			values.add(systemLog.getEnd_date().trim());
		}
		if (systemLog.getOperate_platform() != null && !systemLog.getOperate_platform().equals("")) {
			sql.append(" AND operate_platform like ?");
			values.add("%"+ systemLog.getOperate_platform().trim()+"%");
		}
		if (systemLog.getOperate_type() != null && !systemLog.getOperate_type().equals("")) {
			//增加“查看”类型的数据
			if(systemLog.getOperate_type().contains("view")){
				sql.append(" AND operate_type not in ('view','login','exit','add','delete','qt_bindCard')");
			}else {
				sql.append(" AND operate_type like ?");
				values.add("%"+ systemLog.getOperate_type().trim()+"%");
			}

		}

		sql.append(" order by operate_id desc ");
		return getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(SystemLog.class), values.toArray());
	}
	public HSSFWorkbook export(List<SystemLog> list){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("审查日志");
		//1:冻结第一行
		sheet.createFreezePane( 0, 1, 0, 1 );
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		String[] excelHeader = { "ID","日志类型","操作平台","模块名称","日志内容","操作时间","来访IP","操作用户"};
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
//			sheet.autoSizeColumn((short) i);
			if(i>0){
				sheet.setColumnWidth((short) i, (short)20* 256);
			}else {
				sheet.setColumnWidth((short) i, (short)5* 256);
			}

		}
		String operate_platform= null;
		String platform= null;

		String operate_type= null;
		String type= null;

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			SystemLog systemLog = list.get(i);
			type=systemLog.getOperate_type();
			if (type .equals("login")) {
				operate_type = "登录";
			}else if(type .equals("exit")) {
				operate_type = "退出";
			}else if(type .equals("add")) {
				operate_type = "添加";
			}else if(type .equals("edit")) {
				operate_type = "编辑";
			}else if(type .equals("delete")) {
				operate_type = "删除";
			}else if(type .equals("bind")) {
				operate_type = "绑定学习卡";
			}else if(type .equals("buy")) {
				operate_type = "购买项目";
			}else {
				operate_type = "查看";
			}
			operate_platform= systemLog.getOperate_platform();
			if(operate_platform.equals("admin")){
				platform="资源管理系统";
			}else if(operate_platform.equals("qiantai")){
				platform="中国继续医学教育网";
			}else {
				platform="学习培训后台";
			}
			row.createCell(0).setCellValue(systemLog.getOperate_id());
			row.createCell(1).setCellValue(operate_type);
			row.createCell(2).setCellValue(platform);
			row.createCell(3).setCellValue(systemLog.getModule_name());
			row.createCell(4).setCellValue(systemLog.getOperate_context());
			row.createCell(5).setCellValue(systemLog.getOperate_time());
			row.createCell(6).setCellValue(systemLog.getVisit_ip());
			row.createCell(7).setCellValue(systemLog.getOperate_login_name());
		}
		return wb;
	}
	@Override
	public boolean deleteSystemLog(Long id) {

		Long del_id =id;
		Connection conn;
		try {
			conn = (Connection) getJdbcTemplate().getDataSource().getConnection();
			try{
				conn.setAutoCommit(false);
				Statement conn_statement = (Statement) conn.createStatement();
				String sql_del = "delete from system_operate_log where operate_id=" + del_id;
				conn_statement.executeUpdate(sql_del);
				conn_statement.close();
				conn.commit();
				conn.setAutoCommit(true);
			} catch(SQLException e2) {
				try {
					conn.rollback();
					conn.setAutoCommit(true);
				} catch (Exception e3) {
					return false;
				}
				return false;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return true;
	}
	public Boolean addMenu(SystemMenu menu)
	{
		String add_sql = "insert into system_menu (NAME,SYSTEM_TYPE,MENU_TYPE,URL,STATE) values (:name,:system_type,:menu_type,:url,:state)";
		int result = getSimpleJdbcTemplate().update(add_sql,
				new BeanPropertySqlParameterSource(menu));
		if(result != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public Boolean updateMenu(SystemMenu menu)
	{
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update system_menu set ");
		if(!StringUtils.checkNull(menu.getName())){
			sql.append("NAME=?,");
			values.add(menu.getName());
		}
		if(!StringUtils.checkNull(menu.getSystem_type())){
			sql.append("SYSTEM_TYPE=?,");
			values.add(menu.getSystem_type());
		}
		if(null != menu.getState()){
			sql.append("STATE=?,");
			values.add(menu.getState());
		}
		if(!StringUtils.checkNull(menu.getMenu_type())){
			sql.append("MENU_TYPE=?,");
			values.add(menu.getMenu_type());
		}
		if(!StringUtils.checkNull(menu.getUrl())){
			sql.append("URL = ?");
			values.add(menu.getUrl());
		}
		sql.append("where id = ?");
		values.add(menu.getId());
		
		int result = getJdbcTemplate().update(sql.toString(),values.toArray());
		if(result != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public Boolean updateState(Integer id,Integer state)
	{
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update system_menu set ");
		if(null != state){
			sql.append("STATE=? ");
			values.add(state);
		}
		else
		{
			return false;
		}
		sql.append("where id = ?");
		values.add(id);
		
		int result = getJdbcTemplate().update(sql.toString(),values.toArray());
		if(result != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-08
	 * @return   List<SystemMenu>
	 * 方法说明： 查询培训后台显示的一级菜单
	 */
	public List<SystemMenu> getOneLevelMenu(){
		//String sql = " select * from system_menu where system_type ='后台管理系统' and menu_type in ('能力管理','项目管理','客户管理','内容管理','系统维护') group by menu_type order by id asc";
		//2017/01/11, Change by lee
		//For get All menu
		String sql = " select ID,NAME,SYSTEM_TYPE,MENU_TYPE,URL,STATE from system_menu where system_type ='后台管理系统' and menu_type in ('能力管理','项目管理','考试管理','订单管理','学习卡管理','客户管理','内容管理','系统维护') group by ID,NAME,SYSTEM_TYPE,MENU_TYPE,URL,STATE order by id asc";
		return getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(SystemMenu.class));
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-08
	 * @return   List<SystemMenu>
	 * 方法说明： 根据一级菜单查询培训后台显示的二级菜单
	 */
	public List<SystemMenu> getTwoLevelMenu(String name,long userId){
		/*
		String sql = " select * from system_menu where system_type ='后台管理系统' and menu_type ='"+name+"' and state=1 "
			        +" and id in ( "
					    +" select menu_id from system_role_menu where role_id in( "
						   +" select role_id from system_account_role where account_id in ( "
							  +" select account_id from system_account where user_id = "+userId
							  +" ) "
						  +" ) "
					+" ) ";
		return getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(SystemMenu.class));
		*/
		//YHQ，2017-03-16，重新实现
		String sql = "select distinct sm.id as id,sm.* from system_menu sm "
				   + " inner join system_role_menu srm on sm.id =  srm.menu_id " 
				   + " inner join  system_Role p on p.ROLE_ID=srm.ROLE_ID "
				   + " inner join  system_account_role sar on srm.role_id = sar.role_id " 
				   + " inner join  system_account sa on sar.account_id = sa.account_id " 
				   + " where sm.system_type ='后台管理系统'  and sm.state = 1 and sa.user_id = " + userId  
				   + " and p.STATUS='1' order by sm.id asc " ;
		return getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(SystemMenu.class));
	}
	@Override
	public List<SystemMenu> getSystemMenuByName(String name) {
			StringBuilder sql = new StringBuilder();		
			sql.append("select * from system_menu where name='"+name+"'");
			return getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(SystemMenu.class));
	}

	@Override
	public List<SystemMenu> getALLMenu() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from system_menu ");
		return getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(SystemMenu.class));
	}
}
