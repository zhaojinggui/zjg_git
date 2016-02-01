package com.tiangong.dao.system;

import java.util.List;

import com.tiangong.bean.Role;
import com.tiangong.bean.Rolefunction;
import com.tiangong.bean.Userrole;
public interface RoleDao {
	

	/**
	 * 
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:03:25
	 * 方法描述：保存角色信息
	 * 参数名称：@param role　角色对象
	 * 返回值：true 保存成功/false 保存失败
	 */
	public boolean save(Role role);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:04:25
	 * 方法描述：删除角色信息
	 * 参数名称：@param role　角色对象
	 * 返回值：true 删除成功/false 删除失败
	 */
	public boolean delrole(Role role);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:05:36
	 * 方法描述：更新角色信息
	 * 参数名称：@param role　 角色对象
	 * 返回值：true 更新成功/false 更新失败
	 */
	public boolean updaterole(Role role);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:07:43
	 * 方法描述：根据id获取员工对象
	 * 参数名称：@param id　角色id
	 * 返回值： 角色对象
	 */
	public Role getrole(String id);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:09:54
	 * 方法描述：分页查询角色信息集合
	 * 参数名称：@param currentPage　当前页
	 * 参数名称：@param pageSize　　　总页数
	 * 返回值：角色信息集合
	 */
	public List selrole(int currentPage,int pageSize);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:11:38
	 * 方法描述：查询角色数目
	 * 返回值：角色数目
	 */
	public int count();
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 上午11:29:30
	 * 方法描述：保存员工角色信息
	 * 返回值：true 保存成功　/ false 保存失败
	 */
	public boolean saveemp_role(Userrole emp_role);

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午09:24:26
	 * 方法描述：从员工角色表中根据角色id 查询员工id
	 * 参数名称：@param roleid　　　角色id
	 * 返回值：员工id集合
	 */
	public List selempidbyroleid(String roleid);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午10:22:33
	 * 方法描述：从员工角色表中删除出角色id的员工
	 * 参数名称：@param roleid　角色id
	 * 返回值：true 删除成功　/  false 删除失败
	 */
	public boolean delerbyroleid(String roleid);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午10:55:35
	 * 方法描述：根据角色id查询该角色所拥有的权限id
	 * 参数名称：@param roleid　角色id
	 * 返回值：权限集合
	 */
	public List selfunbyroleid(String roleid);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午11:30:56
	 * 方法描述：保存角色权限信息
	 * 参数名称：@param rf　角色权限对象
	 * 返回值：true 保存成功　/ false 保存失败
	 */
	public boolean saverole_fun(Rolefunction rf);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午11:32:16
	 * 方法描述：根据id 获取角色权限对象
	 * 参数名称：@param id　角色权限对象id
	 * 返回值：角色权限对象
	 */
	public Rolefunction getrf(String id);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午11:33:00
	 * 方法描述：更新角色权限信息
	 * 参数名称：@param rf　角色权限对象
	 * 返回值：true 更新成功/false 更新失败
	 */
	public boolean updaterole_fun(Rolefunction rf);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 下午12:08:34
	 * 方法描述：根据角色id 查询角色权限主键
	 * 参数名称：@param roleid　角色id
	 * 返回值：角色权限主键
	 */
	public String getrole_fun_id(String roleid);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 下午05:55:21
	 * 方法描述：从员工角色表中根据员工id查询角色id
	 * 参数名称：@param empid 员工id
	 * 返回值：角色id集合
	 */
	public List selroleidbyempid(String empid);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-18 上午09:52:21
	 * 方法描述：查询所有的角色信息
	 * 返回值：所有角色信息集合
	 */
	public List selroleinfo();
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-18 上午11:05:41
	 * 方法描述：从员工角色表中根据员工id删除相关信息
	 * 参数名称：@param empid　员工id
	 * 返回值：true 删除成功　/  false 删除失败
	 */
	public boolean delemp_role(String empid);

	public List selectRoleIdByrolename(String roleName);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-2-29 下午03:46:37
	 * 方法描述：根据员工id查询员工角色信息
	 * 参数名称：@param empid
	 *
	 */
	public List selemprolebyempid(String empid);
	
	public List selrolelevel(Boolean level);
	
	public List selCountByRoleIdUserId(String roleid,String userid);
	
}
