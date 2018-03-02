package cn.edu.shu.service;

import java.util.List;

import cn.edu.shu.entity.User;

public interface IUserService {

	//分页查询用户信息
	public List<User> getUserList(String username,int currentPage,int pageSize);
	//根据用户编号获取用户总数
	public int count(String username);
	//插入或者更新用户信息
	public void saveOrUpdateUser(User user);
	//删除用户信息
	public void deleteUser(int id);
	//根据ID获取用户详细信息
	public User getUserByID(int id);
	//获取所有用户信息
	public List<User> getUserAll();
}
