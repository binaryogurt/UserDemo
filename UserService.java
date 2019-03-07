package com.user.service;

import java.util.List;

import com.user.bean.User;

/**
 * 用户service
 */
public interface UserService {
	
	/**
	 * 查询数据总数
	 * @param user
	 * @return
	 */
	public int queryUserCount (User user);
	
	/**
	 * 插叙数据集合
	 * @param user
	 * @return
	 */
	public List<User> queryUserList (User user);
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public void addUser (User user);

	/**
	 * 根据用户id删除用户
	 * @param id
	 * @return
	 */
	public void deleteUser (String id);
	
	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	public void deleteBatchUser (List<String> ids);

	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public void updateUser (User user);
	
}
