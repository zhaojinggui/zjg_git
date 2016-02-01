package com.tiangong.dao.system.imp;

import java.util.List;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.tiangong.bean.Role;
import com.tiangong.bean.Rolefunction;
import com.tiangong.comm.OperateTab;
import com.tiangong.dao.system.RoleDao;
import com.tiangong.bean.Userrole;
public class RoleDaoImp extends HibernateDaoSupport implements RoleDao  {

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:13:59
	 * 方法描述：查询角色数目
	 * 返回值：　返回角色个数
	 */
	
	public int count() {
		// TODO Auto-generated method stub
		return OperateTab.count(getSession(), "select count(*) from Role where roleLevel <> 2");
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:13:59
	 * 方法描述：删除角色信息
	 * 参数名称：@param role　角色对象
	 * 返回值：true 删除成功　/ false 删除失败
	 */
	
	public boolean delrole(Role role) {
		// TODO Auto-generated method stub
		return OperateTab.delete(getSession(), role);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:13:59
	 * 方法描述：根据id获取角色对象
	 * 参数名称：@param id　角色id
	 * 返回值：角色对象
	 */
	
	public Role getrole(String id) {
		// TODO Auto-generated method stub
		return (Role) this.getHibernateTemplate().get(Role.class, id);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:13:59
	 * 方法描述：保存角色信息
	 * 参数名称：@param role 角色对象
	 * 返回值：true 保存成功　/ false 保存失败
	 */
	
	public boolean save(Role role) {
		// TODO Auto-generated method stub
		return OperateTab.save(getSession(), role);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:13:59
	 * 方法描述：分页查询角色信息
	 * 参数名称：@param currentPage　当前页
	 * 参数名称：@param pageSize　　总页数
	 * 返回值：　角色集合
	 */
	
	public List selrole(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return OperateTab.showtab(getSession(), currentPage, pageSize, "from Role");
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:13:59
	 * 方法描述：更新角色信息
	 * 参数名称：@param role　角色对象
	 * 返回值：　true 更新成功　/ false 更新失败
	 */
	
	public boolean updaterole(Role role) {
		// TODO Auto-generated method stub
		return OperateTab.update(getSession(), role);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 上午11:31:25
	 * 方法描述：保存员工角色信息
	 * 参数名称：@param empRole　员工角色对象
	 * 返回值：true 保存成功　/ false 保存失败
	 */
	
	public boolean saveemp_role(Userrole empRole) {
		// TODO Auto-generated method stub
		return OperateTab.save(getSession(), empRole);
		
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午09:26:18
	 * 方法描述：从员工角色表中根据角色id 查询员工id
	 * 参数名称：@param roleid　　　角色id
	 * 返回值：员工id集合
	 */
	
	public List selempidbyroleid(String roleid) {
		// TODO Auto-generated method stub
		String hql="select userId from Userrole where roleId=?";
		List list =this.getHibernateTemplate().find(hql,roleid);
		return list;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午10:24:19
	 * 方法描述：从角色员工表中删除角色id的员工
	 * 参数名称：@param roleid　角色id 
	 * 返回值：true 删除成功　 /  false 删除失败
	 */
	
	public boolean delerbyroleid(String roleid) {
		// TODO Auto-generated method stub
		boolean flag =false;
		try {
			String queryString="delete from Userrole where roleId =? ";
			Query query =this.getSession().createQuery(queryString);
			query.setString(0, roleid);
			query.executeUpdate();
			flag =true;
		} catch (Exception e) {
			// TODO: handle exception
			flag=false;
		}
		return flag;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午10:55:35
	 * 方法描述：根据角色id查询该角色所拥有的权限id
	 * 参数名称：@param roleid　角色id
	 * 返回值：权限集合
	 */
	
	public List selfunbyroleid(String roleid) {
		// TODO Auto-generated method stub
		String queryString="select functionIds from Rolefunction where roleId=? ";
		List list =this.getHibernateTemplate().find(queryString,roleid);
		return list;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午11:33:52
	 * 方法描述：获取角色权限对象
	 * 参数名称：@param id　角色权限对象id
	 * 返回值：角色权限对象
	 */
	
	public Rolefunction getrf(String id) {
		// TODO Auto-generated method stub
		return (Rolefunction) this.getHibernateTemplate().get(Rolefunction.class, id);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午11:33:52
	 * 方法描述：保存角色权限对象信息
	 * 参数名称：@param rf　角色权限对象
	 * 返回值：true 保存成功　/ false 保存失败
	 */
	
	public boolean saverole_fun(Rolefunction rf) {
		// TODO Auto-generated method stub
		return OperateTab.save(getSession(), rf);
		
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 上午11:33:52
	 * 方法描述：修改角色权限对象信息
	 * 参数名称：@param rf　角色权限对象
	 * 返回值：true 修改成功　/ false 修改失败
	 */
	
	public boolean updaterole_fun(Rolefunction rf) {
		// TODO Auto-generated method stub
		boolean b=false;
		try {
			this.getHibernateTemplate().update(rf);
			b=true;			
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 下午12:09:59
	 * 方法描述：根据角色id 查询角色权限主键
	 * 参数名称：@param roleid　角色id
	 * 返回值：角色权限主键
	 */
	
	public String getrole_fun_id(String roleid) {
		// TODO Auto-generated method stub
		String id=null;
		String queryString="select id from Rolefunction where roleId=?";
		Query query =this.getSession().createQuery(queryString);
		query.setString(0, roleid);
		if(!query.list().isEmpty())
		{
			id=query.uniqueResult().toString();
		}
		return id;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 下午05:56:52
	 * 方法描述：从员工角色表中根据员工id查询角色id
	 * 参数名称：@param empid 员工id
	 * 返回值：角色id集合：
	 */
	
	public List selroleidbyempid(String empid) {
		// TODO Auto-generated method stub
		String queryString="select roleId from Userrole where userId=?";
		List list =this.getHibernateTemplate().find(queryString,empid);
		return list;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-18 上午09:53:06
	 * 方法描述：查询所有的角色信息
	 * 返回值：所有角色信息集合
	 */
	
	public List selroleinfo() {
		// TODO Auto-generated method stub
		String queryString="from Role";
		List list =this.getHibernateTemplate().find(queryString);
		return list;
	}
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-18 上午11:07:39
	 * 方法描述：从员工角色表中根据员工id删除相关信息
	 * 参数名称：@param empid　员工id
	 * 返回值：true 删除成功　/  false 删除失败
	 */
	
	public boolean delemp_role(String empid) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			String queryString="delete from EmpRole where erEmpId=?";
			Query query =this.getSession().createQuery(queryString);
			query.setString(0, empid);
			query.executeUpdate();
			flag=true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	
	public List selectRoleIdByrolename(String roleName){
		String queryString  = "select roleId from Role where roleName = ?";
		Query query = this.getSession().createQuery(queryString);
		query.setString(0, roleName);
		return query.list();
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-2-29 下午03:47:24
	 * 方法描述：根据员工id查询角色信息
	 * 参数名称：@param empid
	 */
	
	public List selemprolebyempid(String empid) {
		// TODO Auto-generated method stub
		String queryString="from Userrole where userId=?";
		List list =this.getHibernateTemplate().find(queryString,empid);
		return list;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-8-14 下午01:39:24
	 * 方法描述：查询角色等级
	 * 参数名称：@param level　角色等级
	 */
	
	public List selrolelevel(Boolean level) {
		// TODO Auto-generated method stub
	   String queryString="from Role where roleLevel=? ";
	   Query query =this.getSession().createQuery(queryString);
	   query.setBoolean(0, level);
	   return query.list();
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-8-16 上午10:21:54
	 * 方法描述：
	 * 参数名称：@param roleid
	 * 参数名称：@param userid
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public List selCountByRoleIdUserId(String roleid, String userid) {
		// TODO Auto-generated method stub
		
		String hql="select id from Userrole where userId=? and roleId not in (?)";
		List list =this.getHibernateTemplate().find(hql,new String[]{userid,roleid});
		if(!list.isEmpty())
		{
			return list;
		}
		return null;
	}
	
}
