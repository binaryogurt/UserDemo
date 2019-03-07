package com.user.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.bean.User;
import com.user.page.PageHelper;
import com.user.service.UserService;
import com.user.util.StringUtil;

/**
 * 用户管理controller
 * controller需要用@Controller注解
 * @RequestMapping 注解是请求的路径
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	/**
	 * @Autowired 注解是spring的自动注入
	 * 就不用创建对象了
	 */

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("")
	public String userPage(){
		return "userList";
	}
	
	/**
	 * 用户列表,分页
	 * bootstrap-table分页需要两个参数limit和offset, 都封装在Page对象中
	 * 让每个实体类继承page类, 这样每个对象就有limit和offset两个分页参数了
	 * PageHelper类是返回给页面的, 包括rows(数据集合)和total(数据总数)两个属性
	 * @param user
	 * @return
	 */
	@RequestMapping("/userList.do")
	@ResponseBody
	public PageHelper<User> userList (User user) {
		PageHelper<User> pageHelper = new PageHelper<User>();
		//统计总记录数
		Integer total = userService.queryUserCount(user);
		pageHelper.setTotal(total);
		//当前页数据集合
		List<User> list = userService.queryUserList(user);
		pageHelper.setRows(list);
		return pageHelper;
	}
	
	/**
	 * 新增和编辑公用方法
	 * 判断id是否为空, 如果id为空, 调用新增方法, 如果id不为空, 调用更新方法
	 * @param user
	 * @return
	 */
	@RequestMapping("/saveUser.do")
	@ResponseBody
	public Map<String, Object> saveUser (User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(null == user.getId() || user.getId().equals("")) {
				//如果id为空,则是新增
				//设置主键字符串
				user.setId(StringUtil.uuid());
				userService.addUser(user);
			} else {
				//否则则是修改
				userService.updateUser(user);
			}
			result.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
		}
		return result;
	}
	
	/**
	 * 批量删除
	 * 参数为存有用户id的数组
	 * 需要将数组转化为集合, 才能传到mapper.xml中, 使用foreach标签, 批量删除用户
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteBatchUser.do")
	@ResponseBody
	public Map<String, Object> deleteBatchUser (String[] ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//将接收的字符串类型的数组利用集合的工具类Arrays转化为list集合
			userService.deleteBatchUser(Arrays.asList(ids));
			result.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
			logger.info("deleteBatchUser error./n");
		}
		return result;
	}
	
	/**
	 * 删除一条用户
	 * 参数为用户id
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUser.do")
	@ResponseBody
	public Map<String, Object> deleteUser (String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userService.deleteUser(id);
			result.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
		}
		return result;
	}
	
}
