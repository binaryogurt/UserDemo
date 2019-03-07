package com.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.bean.User;
import com.user.dao.UserDao;
import com.user.service.UserService;

/**
 * 用户service
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public int queryUserCount(User user) {
		// TODO Auto-generated method stub
		return userDao.queryUserCount(user);
	}

	@Override
	public List<User> queryUserList(User user) {
		// TODO Auto-generated method stub
		return userDao.queryUserList(user);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		userDao.deleteUser(id);
	}

	@Override
	public void deleteBatchUser(List<String> ids) {
		// TODO Auto-generated method stub
		userDao.deleteBatchUser(ids);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}

}
