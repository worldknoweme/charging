package cn.edu.shu.service.impl;

import java.util.List;

import cn.edu.shu.dao.IDataAcquisitionDao;
import cn.edu.shu.dao.impl.DataAcquisitionDao;
import cn.edu.shu.entity.User;
import cn.edu.shu.service.IUserService;

/**
 * 
 * @author 
 *用户操作服务类
 */
public class UserServiceImpl implements IUserService{
	IDataAcquisitionDao dataAcquisitionDao = new DataAcquisitionDao<User>();
	@Override
	public List<User> getUserList(String username, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		int start = (currentPage-1)*pageSize;
		
		int end = currentPage*pageSize;
		return dataAcquisitionDao.getUserListByUsernameAndLimit(username, start, end);
	}

	@Override
	public int count(String username) {
		// TODO Auto-generated method stub
		return dataAcquisitionDao.countUser(username);
	}

	@Override
	public void saveOrUpdateUser(User user) {
		// TODO Auto-generated method stub
		dataAcquisitionDao.saveOrUpdateUser(user);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		dataAcquisitionDao.delUserById(id);
	}

	@Override
	public User getUserByID(int id) {
		// TODO Auto-generated method stub
		return dataAcquisitionDao.getUserByID(id);
	}

	@Override
	public List<User> getUserAll() {
		// TODO Auto-generated method stub
		return dataAcquisitionDao.getUserAll();
	}

}
