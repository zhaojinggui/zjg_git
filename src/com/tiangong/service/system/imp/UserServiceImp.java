package com.tiangong.service.system.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tiangong.bean.User;
import com.tiangong.bean.RmUser;
import com.tiangong.dao.system.imp.UserDaoImp;
import com.tiangong.service.system.UserService;

public class UserServiceImp implements UserService {

	private UserDaoImp userDAO;
	
	public void setUserDAO(UserDaoImp userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:59:32
	 * 方法描述：删除对象
	 * 参数名称：@param tp
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public boolean delete(Object tp) {
		// TODO Auto-generated method stub
		return userDAO.delete(tp);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:59:32
	 * 方法描述：根据id获取对象
	 * 参数名称：@param id
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public Object gettp(String id,Class obj) {
		// TODO Auto-generated method stub
		return userDAO.gettp(id, obj);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:59:32
	 * 方法描述：保存对象信息
	 * 参数名称：@param tp
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public boolean save(Object tp) {
		// TODO Auto-generated method stub
		return userDAO.save(tp);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:59:32
	 * 方法描述：更新对象信息
	 * 参数名称：@param tp
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public boolean update(Object tp) {
		// TODO Auto-generated method stub
		return userDAO.update(tp);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-7-26 下午05:17:41
	 * 方法描述：
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public List<User> list() {
		// TODO Auto-generated method stub
		return userDAO.list();
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-7-29 下午02:51:13
	 * 方法描述：
	 * 参数名称：@param userId
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public User getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return userDAO.getUserByUserId(userId);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-7-29 下午05:15:24
	 * 方法描述：
	 * 参数名称：@param userId
	 * 参数名称：@param password
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public RmUser getUser(String userId, String password) {
		// TODO Auto-generated method stub
		return userDAO.getUser(userId, password);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-8-20 下午02:24:56
	 * 方法描述：
	 * 参数名称：@return
	 * 返回值：
	 * @param user 
	 * 
	 */
	public Integer getCount(User user) {
		// TODO Auto-generated method stub
		return userDAO.getCount(user);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-8-20 下午02:25:04
	 * 方法描述：
	 * 参数名称：@param user
	 * 参数名称：@return
	 * 返回值：
	 * 
	 */
	public List<User> userList(User user) {
		// TODO Auto-generated method stub
		return userDAO.userList(user);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-8-26 上午10:02:34
	 * 方法描述：
	 * 参数名称：@param userId
	 * 参数名称：@param password
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public boolean isAdmin(String userId, String password) {
		// TODO Auto-generated method stub
		return userDAO.isAdmin(userId, password);
	}

	public Map getFunctions(User user) {
		// TODO Auto-generated method stub
		Map map =null;
		try {
			map=new HashMap();
			String roleid=userDAO.getRoleid(user);
			String function=userDAO.getFunctionsByroleid(roleid);
			String funs[]=function.split(",");
			for(int i=0;i<funs.length;i++)
			{
				map.put(funs[i], true);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(map);
		return map;
	}
}
