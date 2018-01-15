package com.hys.auth.service.impl;

import java.util.List;

import com.hys.auth.dao.UsersDAO;
import com.hys.auth.model.HysUsers;
import com.hys.auth.service.UsersManage;
import com.hys.auth.util.PageList;
import com.hys.exam.model.HysUserRoleProp;

public class UsersManageImpl extends AbstractManageImpl implements UsersManage {

	private UsersDAO usersDAO;
	
	public UsersDAO getDao(){
		return usersDAO;
	}

	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public int updateUsers(HysUsers users) {
		return usersDAO.update(users);
	}

	public int delete(Integer id) {
		return usersDAO.delete(id);
	}

	public int updatePassword(HysUsers users) {
		return usersDAO.updatePassword(users);
	}

	public HysUsers valid(String userName, String passWord){
		return usersDAO.valid(userName, passWord);
	}

	public List<HysUsers> getUsersWithPagination(String loginName, PageList pageList) {
		return usersDAO.getUsersWithPagination(loginName, pageList);
	}
	
	public List<HysUsers> getSearchList(List<String> list, PageList pageList) {
		return usersDAO.getSearchList(list, pageList);
	}

	@Override
	public Long save(HysUsers users) {
		return usersDAO.save(users);
	}

	@Override
	public void saveUserRoles(HysUserRoleProp role) {
		// TODO Auto-generated method stub
		usersDAO.saveUserRoles(role);
	}
	@Override
	public void deleteUserRoles(Long userId) {
		// TODO Auto-generated method stub
		usersDAO.deleteUserRoles(userId);
	}
	public List<HysUserRoleProp> getUserRoleList(Long userid)
	{
		return usersDAO.getUserRoleList(userid);
	}
}
