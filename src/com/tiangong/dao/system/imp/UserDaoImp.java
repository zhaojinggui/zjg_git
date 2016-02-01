package com.tiangong.dao.system.imp;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tiangong.bean.RmUser;
import com.tiangong.bean.User;
import com.tiangong.dao.system.UserDao;
public class UserDaoImp extends HibernateDaoSupport implements UserDao {

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:57:14
	 * 方法描述：删除对象信息
	 * 参数名称：@param tp
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public boolean delete(Object tp) {
		// TODO Auto-generated method stub
		boolean b=false;
		try {
			this.getHibernateTemplate().delete(tp);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:57:14
	 * 方法描述：获取对象信息
	 * 参数名称：@param id
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public Object gettp(String id,Class obj) {
		// TODO Auto-generated method stub
		Object object=null;
		try {
			object=this.getHibernateTemplate().get(obj, id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return object;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:57:14
	 * 方法描述：保存对象信息
	 * 参数名称：@param tp
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public boolean save(Object tp) {
		// TODO Auto-generated method stub
		boolean b=false;
		try {
			this.getHibernateTemplate().save(tp);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:57:14
	 * 方法描述：更新对象信息
	 * 参数名称：@param tp
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public boolean update(Object tp) {
		// TODO Auto-generated method stub
		boolean b=false;
		try {
			this.getHibernateTemplate().update(tp);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-7-26 下午05:17:57
	 * 方法描述：查询用户列表
	 * 参数名称：@return
	 * 返回值：
	 * 
	 */
	public List<User> list() {
		// TODO Auto-generated method stub
		String hql="from User";
		List<User> list =this.getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-7-29 下午02:48:00
	 * 方法描述：根据用户id获取用户信息
	 * 参数名称：@param userId
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public User getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		User user =null;
		String hql="from User where userId=?";
		List list =this.getHibernateTemplate().find(hql,userId);
		if(list.size()>0)
		{
			user=(User) list.get(0);
		}
		return user;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-7-29 下午05:14:00
	 * 方法描述：
	 * 参数名称：@param userId
	 * 参数名称：@param password
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public RmUser getUser(String userId, String password) {
		// TODO Auto-generated method stub
		RmUser user =null;
		String hql="from RmUser where name=? and password=?";
		List list =this.getHibernateTemplate().find(hql,new String[]{userId,password});
		if(list.size()>0)
		{
			user=(RmUser) list.get(0);
		}
		return user;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-8-20 下午02:25:31
	 * 方法描述：
	 * 参数名称：@return
	 * 返回值：
	 * @param user 
	 * 
	 */
	public Integer getCount(User user) {
		// TODO Auto-generated method stub
		  Criteria Proposal =this.getSession().createCriteria(User.class);
	        if(!"".equals(user.getUserId()) && user.getUserId()!=null)
	        {
	            Proposal.add( Restrictions.like("userId", "%"+user.getUserId()+"%"));
	        }
	        if(!"".equals(user.getEmail()) && user.getEmail()!=null)
	        {
	        	 Proposal.add( Restrictions.like("email", "%"+user.getEmail()+"%"));
	        }
	        if (!"".equals(user.getName())&& user.getName()!=null) {
	            
	            Proposal.add(Restrictions.like("name", "%"+user.getName()+"%"));
	        }
//	        if(!"".equals(user.getState()) && user.getState()!=null)
//	        {
//	        	Proposal.add( Restrictions.eq("state",user.getState()));
//	        }
	        return ((Integer) Proposal.setProjection(Projections.rowCount()).uniqueResult()).intValue();   
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-8-20 下午02:25:51
	 * 方法描述：
	 * 参数名称：@param user
	 * 参数名称：@return
	 * 返回值：
	 * 
	 */
	public List<User> userList(User user) {
		// TODO Auto-generated method stub
		  List<User> list=null;
		  Criteria Proposal =this.getSession().createCriteria(User.class);
	        if(!"".equals(user.getUserId()) && user.getUserId()!=null)
	        {
	            Proposal.add( Restrictions.like("userId", "%"+user.getUserId()+"%"));
	            
	        }
	        if(!"".equals(user.getEmail()) && user.getEmail()!=null)
	        {
	        	 Proposal.add( Restrictions.like("email", "%"+user.getEmail()+"%"));
	        }
	        if (!"".equals(user.getName())&& user.getName()!=null) {
	            
	            Proposal.add(Restrictions.like("name", "%"+user.getName()+"%"));
	            
	        }
//	        if(!"".equals(user.getState()) && user.getState()!=null)
//	        {
//	        	Proposal.add( Restrictions.eq("state",user.getState()));
//	        }
//	        Proposal.addOrder(Order.desc("createDate"));
	        Proposal.setFirstResult((user.getCurrentPage()-1)*user.getPageSize());
	        Proposal.setMaxResults(user.getPageSize());
	        list =Proposal.list();  
	        return list;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-8-26 上午10:00:57
	 * 方法描述：
	 * 参数名称：@param userId
	 * 参数名称：@param password
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public boolean isAdmin(String userId, String password) {
		// TODO Auto-generated method stub
		User user =null;
		String hql="from User where userId=? and password=? and isAdmin='1'";
		List list =this.getHibernateTemplate().find(hql,new String[]{userId,password});
		if(list.size()>0)
		{
			return true;
		}
		return false;
	}

	public String getRoleid(User user) {
		// TODO Auto-generated method stub
		String roleid=null;
		String hql="select roleId from Userrole where userId=?";
		List list =this.getHibernateTemplate().find(hql,user.getUserId());
		if(!list.isEmpty())
		{
			roleid =list.get(0).toString();
		}
		return roleid;
	}

	public String getFunctionsByroleid(String roleid) {
		// TODO Auto-generated method stub
		String functionids=null;
		String hql="select functionIds from Rolefunction where roleId=?";
		List list =this.getHibernateTemplate().find(hql,roleid);
		if(!list.isEmpty())
		{
			functionids=list.get(0).toString();
		}
		return functionids;
	}
}
