package com.tiangong.dao.system.imp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tiangong.bean.Rolefunction;
import com.tiangong.bean.RmWxMenu;
import com.tiangong.comm.OperateTab;
import com.tiangong.dao.system.FunctionDao;

public class FunctionDaoImp extends HibernateDaoSupport implements FunctionDao  {

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:49:30
	 * 方法描述：删除权限信息
	 * 参数名称：@param fun　权限对象
	 * 返回值：true 删除成功　/ false　删除失败
	 */
	
	public boolean delfun(Object fun) {
		// TODO Auto-generated method stub
		return OperateTab.delete(getSession(), fun);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:49:30
	 * 方法描述：根据id 获取权限信息
	 * 参数名称：@param id　权限id
	 * 返回值：权限对象
	 */
	
	public RmWxMenu getfun(String id) {
		// TODO Auto-generated method stub
		return (RmWxMenu) this.getHibernateTemplate().get(RmWxMenu.class, id);
		
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:49:30
	 * 方法描述： 保存权限信息
	 * 参数名称：@param fun　权限对象
	 * 返回值：true 保存成功　/ false 保存失败
	 */
	
	public boolean save(Object fun) {
		// TODO Auto-generated method stub
		return OperateTab.save(getSession(), fun);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:49:30
	 * 方法描述：更新权限信息
	 * 参数名称：@param fun　权限对象
	 * 返回值：true 更新成功　/ false 更新失败
	 */
	
	public boolean updatefun(Object fun) {
		// TODO Auto-generated method stub
		return OperateTab.update(getSession(), fun);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午03:19:43
	 * 方法描述：查询所有权限id
	 * 返回值：权限id集合
	 */
	
	public List selAllfunid() {
		// TODO Auto-generated method stub
		String hql="select id from RmWxMenu";
		List list =this.getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午03:19:43
	 * 方法描述：查询所有父对象信息
	 * 参数名称：@param id　父对象id
	 * 返回值：父对象信息集合
	 */
	
	public List selPid(String id) {
		// TODO Auto-generated method stub
		String queryString="from RmWxMenu where pid =? ";
		List list =this.getHibernateTemplate().find(queryString,id);
		return list;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-1-10 下午01:40:39
	 * 方法描述：
	 * 参数名称：@param ck
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public List selfunidbyck(String ck) {
		// TODO Auto-generated method stub
		//String hql="from Function where funChecked='0' and funId=?";
		String hql="select * from rw_function where fun_checked='0' and fun_id=?";
		Query query =this.getSession().createSQLQuery(hql);
		query.setString(0, ck);
		return query.list();
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-3-6 上午10:27:09
	 * 方法描述：
	 * 参数名称：@param currentPage
	 * 参数名称：@param pageSize
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public List emppurviewlist(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		String queryString ="from EmpPurview";
	    return OperateTab.showtab(getSession(), currentPage, pageSize, queryString);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-3-6 上午10:47:16
	 * 方法描述：
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public int count() {
		// TODO Auto-generated method stub
		String query ="select count(*) from EmpPurview";
		return OperateTab.count(getSession(), query);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-3-6 下午02:52:09
	 * 方法描述：
	 * 参数名称：@param id
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public List selorgnamebyid(int id) {
		// TODO Auto-generated method stub
		String queryString="select orgName from Organization where orgId=?";
		Query query =this.getSession().createQuery(queryString);
		query.setInteger(0,id);
		return query.list();
	}

	
	public List selempview(String account) {
		// TODO Auto-generated method stub
		String queryString ="from EmpPurview where empAccount=?";
		List list =this.getHibernateTemplate().find(queryString,account);
		return list;
	}

	
	public Rolefunction getep(int id) {
		// TODO Auto-generated method stub
		Rolefunction ep =(Rolefunction) this.getHibernateTemplate().get(Rolefunction.class, id);
		return ep;
	}

	
	public List selSpecialByaccount(String account) {
		// TODO Auto-generated method stub
		String queryString="from EmpPurviewSpecial where empAccount=?";
		List list =this.getHibernateTemplate().find(queryString,account);
		return list;
	}

	
	public boolean delSpecial(String account) {
		// TODO Auto-generated method stub
		boolean flag =false;
		try {
			String queryString="delete from EmpPurviewSpecial where empAccount =? ";
			Query query =this.getSession().createQuery(queryString);
			query.setString(0, account);
			query.executeUpdate();
			flag =true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	
	public List selprovewlist() {
		// TODO Auto-generated method stub
		String queryString="select a.fu_approveid,b.fun_text from rw_flow_configure a,rw_function b  where a.fu_approveid=b.fun_id";
		Query query =this.getSession().createSQLQuery(queryString);
		return query.list();
	}


}
