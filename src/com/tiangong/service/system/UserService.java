package com.tiangong.service.system;

import java.util.List;

import com.tiangong.bean.RmUser;
import com.tiangong.bean.User;

public interface UserService {
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:03:25
	 * 方法描述：保存定时计划
	 * 参数名称：@param tp　定时计划对象
	 * 返回值：true 保存成功/false 保存失败
	 */
	public boolean save(Object tp);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:04:25
	 * 方法描述：删除定时计划
	 * 参数名称：@param tp　定时计划对象
	 * 返回值：true 删除成功/false 删除失败
	 */
	public boolean delete(Object tp);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:05:36
	 * 方法描述：更新定时计划
	 * 参数名称：@param tp　 定时计划对象
	 * 返回值：true 更新成功/false 更新失败
	 */
	public boolean update(Object tp);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:07:43
	 * 方法描述：根据id获取员工对象
	 * 参数名称：@param id　定时计划id
	 * 返回值： 定时计划对象
	 */
	public Object gettp(String id,Class obj);
	
	public List<User> list();
	
	public User getUserByUserId(String userId);
	
	public RmUser getUser(String userId,String password);
	
	public boolean isAdmin(String userId, String password);
}
