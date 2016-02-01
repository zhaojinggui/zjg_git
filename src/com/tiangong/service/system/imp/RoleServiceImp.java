package com.tiangong.service.system.imp;

import java.util.List;

import com.tiangong.bean.Userrole;
import com.tiangong.bean.Role;
import com.tiangong.bean.Rolefunction;
import com.tiangong.dao.system.imp.RoleDaoImp;
import com.tiangong.service.system.RoleService;

public class RoleServiceImp implements RoleService {

	RoleDaoImp roleDao;

	public void setRoleDao(RoleDaoImp roleDao) {
		this.roleDao = roleDao;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:32:35
	 * 方法描述：查询角色总数目
	 * 返回值：角色数目
	 */
	
	public int count() {
		// TODO Auto-generated method stub
		return roleDao.count();
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:32:35
	 * 方法描述：删除角色信息
	 * 参数名称：@param role　角色对象
	 * 返回值：true 删除成功　/  false　删除失败
	 */
	
	public boolean delrole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.delrole(role);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:32:35
	 * 方法描述：根据id 获取角色对象
	 * 参数名称：@param id　角色id 
	 * 返回值：角色对象
	 */
	
	public Role getrole(String id) {
		// TODO Auto-generated method stub
		return roleDao.getrole(id);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:32:35
	 * 方法描述：保存角色信息
	 * 参数名称：@param role　角色对象
	 * 返回值：true 保存成功　/  false 保存失败
	 */
	
	public boolean save(Role role) {
		// TODO Auto-generated method stub
		return roleDao.save(role);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:32:35
	 * 方法描述：分页查询角色对象
	 * 参数名称：@param currentPage　当前页
	 * 参数名称：@param pageSize　　总页数
	 * 返回值：角色信息集合
	 */
	
	public List selrole(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return roleDao.selrole(currentPage, pageSize);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:32:35
	 * 方法描述：更新角色对象
	 * 参数名称：@param role　角色对象
	 * 返回值：true 更新成功　/  false 更新失败
	 */
	
	public boolean updaterole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.updaterole(role);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 上午11:33:30
	 * 方法描述：保存员工角色信息
	 * 参数名称：@param empRole　员工角色对象
	 * 返回值：true 保存成功 /false 保存失败
	 */
	
	public boolean saveemp_role(Userrole empRole) {
		// TODO Auto-generated method stub
		return roleDao.saveemp_role(empRole);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午09:33:03
	 * 方法描述：从员工角色表中根据角色id 查询员工id
	 * 参数名称：@param roleid　　　角色id
	 * 返回值：员工id集合
	 */
	
	public List selempidbyroleid(String roleid) {
		// TODO Auto-generated method stub
		return roleDao.selempidbyroleid(roleid);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午10:30:19
	 * 方法描述：从员工角色表中根据角色id删除
	 * 参数名称：@param roleid　角色id 
	 * 返回值：  true 删除成功　/ false 删除失败
	 */
	
	public boolean delerbyroleid(String roleid) {
		// TODO Auto-generated method stub
		return roleDao.delerbyroleid(roleid);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午10:59:30
	 * 方法描述：根据角色id查询该角色所拥有的权限id
	 * 参数名称：@param roleid　角色id
	 * 返回值：权限集合
	 */
	
	public List selfunbyroleid(String roleid) {
		// TODO Auto-generated method stub
		return roleDao.selfunbyroleid(roleid);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午11:39:13
	 * 方法描述：获取角色权限对象
	 * 参数名称：@param id　角色权限对象id
	 * 返回值：角色权限对象
	 */
	
	public Rolefunction getrf(String id) {
		// TODO Auto-generated method stub
		return roleDao.getrf(id);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午11:39:13
	 * 方法描述：保存角色权限对象信息
	 * 参数名称：@param rf 角色权限对象
	 * 返回值：true 保存成功 /false 保存失败
	 */
	
	public boolean saverole_fun(Rolefunction rf) {
		// TODO Auto-generated method stub
		return roleDao.saverole_fun(rf);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午11:39:13
	 * 方法描述：修改角色权限对象信息
	 * 参数名称：@param rf 角色权限对象
	 * 返回值：true 修改成功 /false 修改失败
	 */
	
	public boolean updaterole_fun(Rolefunction rf) {
		// TODO Auto-generated method stub
		return roleDao.updaterole_fun(rf);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 下午01:09:56
     * 方法描述：根据角色id 查询角色权限主键
	 * 参数名称：@param roleid　角色id
	 * 返回值：角色权限主键
	 */
	
	public String getrole_fun_id(String roleid) {
		// TODO Auto-generated method stub
		return roleDao.getrole_fun_id(roleid);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 下午05:59:36
	 * 方法描述：从员工角色表中根据员工id查询角色id
	 * 参数名称：@param empid 员工id
	 * 返回值：角色id集合
	 */
	
	public List selroleidbyempid(String empid) {
		// TODO Auto-generated method stub
		return roleDao.selroleidbyempid(empid);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-18 上午09:54:24
	 * 方法描述：查询所有的角色信息
	 * 返回值：所有角色信息集合
	 */
	
	public List selroleinfo() {
		// TODO Auto-generated method stub
		return roleDao.selroleinfo();
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-18 上午11:14:05
	 * 方法描述：从员工角色表中根据员工id删除相关信息
	 * 参数名称：@param empid　员工id
	 * 返回值：true 删除成功　/  false 删除失败
	 */
	
	public boolean delemp_role(String empid) {
		// TODO Auto-generated method stub
		return roleDao.delemp_role(empid);
	}

	
	public List selectroIdByroname(String roleName) {
		// TODO Auto-generated method stub
		return roleDao.selectRoleIdByrolename(roleName);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-2-29 下午03:54:35
	 * 方法描述：根据员工id获取员工角色信息
	 * 参数名称：@param empid
	 */
	
	public List selemprolebyempid(String empid) {
		// TODO Auto-generated method stub
		List list =roleDao.selemprolebyempid(empid);
		System.out.println(list);
		return list;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-8-14 下午01:42:42
	 * 方法描述：查询角色等级
	 */
	
	public List selrolelevel(Boolean level) {
		// TODO Auto-generated method stub
		return roleDao.selrolelevel(level);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-8-16 上午10:25:01
	 * 方法描述：
	 * 参数名称：@param roleid
	 * 参数名称：@param userid
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public List selCountByRoleIdUserId(String roleid, String userid) {
		// TODO Auto-generated method stub
		return roleDao.selCountByRoleIdUserId(roleid, userid);
	}

}
