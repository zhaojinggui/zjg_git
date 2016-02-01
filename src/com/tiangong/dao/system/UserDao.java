package com.tiangong.dao.system;
import java.util.List;

import com.tiangong.bean.RmUser;
import com.tiangong.bean.User;

public interface UserDao {
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:03:25
	 * 方法描述：保存对象信息
	 * 参数名称：@param tp　对象
	 * 返回值：true 保存成功/false 保存失败
	 */
	public boolean save(Object tp);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:04:25
	 * 方法描述：删除对象
	 * 参数名称：@param tp　对象
	 * 返回值：true 删除成功/false 删除失败
	 */
	public boolean delete(Object tp);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:05:36
	 * 方法描述：更新对象
	 * 参数名称：@param tp　 对象
	 * 返回值：true 更新成功/false 更新失败
	 */
	public boolean update(Object tp);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:07:43
	 * 方法描述：根据id获取对象信息
	 * 参数名称：@param id　对象id
	 * 返回值： 对象
	 */
	public Object gettp(String id,Class obj);
	
	public List<User> list();
	
	public User getUserByUserId(String userId);
	
	public RmUser getUser(String userId,String password);
	
	public boolean isAdmin(String userId,String password);
}
