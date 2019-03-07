package com.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.user.bean.User;

@Repository
public interface UserDao {
	
	/**
	 * 查询总数
	 * @param user
	 * @return
	 */
	public int queryUserCount (User user);
	
	/**
	 * 查询数据集合
	 * @param user
	 * @return
	 */
	public List<User> queryUserList (User user);
	
	/**
	 * 新增
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
