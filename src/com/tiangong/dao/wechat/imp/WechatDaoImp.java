package com.tiangong.dao.wechat.imp;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tiangong.bean.RmActivity;
import com.tiangong.bean.RmActivityCardList;
import com.tiangong.bean.RmActivityCardRecord;
import com.tiangong.bean.RmActivityUser;
import com.tiangong.bean.RmUser;
import com.tiangong.dao.comm.imp.BaseDaoImpl;
import com.tiangong.dao.wechat.WechatDao;

public class WechatDaoImp extends BaseDaoImpl implements WechatDao {

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
	
	public Object gettp(int id,Class obj) {
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
		boolean b=true;
		try {
			this.getHibernateTemplate().save(tp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
	 * 创建时间：2013-8-20 下午02:25:31
	 * 方法描述：
	 * 参数名称：@return
	 * 返回值：
	 * @param user 
	 * 
	 */
	public Integer getCount(RmActivityUser user) {
		// TODO Auto-generated method stub
		List<RmActivityUser> list=null;
	    Criteria Proposal =this.getSession().createCriteria(RmActivityUser.class);
        if(!"".equals(user.getName()) && user.getName()!=null)
        {
            Proposal.add( Restrictions.like("name", "%"+user.getName()+"%"));
            
        }
        if(!"".equals(user.getMobile()) && user.getMobile()!=null)
        {
        	 Proposal.add( Restrictions.like("mobile", "%"+user.getMobile()+"%"));
        }
        if (!"".equals(user.getTeacherNo())&& user.getTeacherNo()!=null) {
            
            Proposal.add(Restrictions.like("teacherNo", "%"+user.getTeacherNo()+"%"));
            
        }
        Proposal.addOrder(Order.desc("addDate"));
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
	@Override
	public List<RmActivityUser> list(RmActivityUser user) {
		// TODO Auto-generated method stub
		  List<RmActivityUser> list=null;
		  Criteria Proposal =this.getSession().createCriteria(RmActivityUser.class);
	        if(!"".equals(user.getName()) && user.getName()!=null)
	        {
	            Proposal.add( Restrictions.like("name", "%"+user.getName()+"%"));
	            
	        }
	        if(!"".equals(user.getMobile()) && user.getMobile()!=null)
	        {
	        	 Proposal.add( Restrictions.like("mobile", "%"+user.getMobile()+"%"));
	        }
	        if (!"".equals(user.getTeacherNo())&& user.getTeacherNo()!=null) {
	            
	            Proposal.add(Restrictions.like("teacherNo", "%"+user.getTeacherNo()+"%"));
	            
	        }
	        Proposal.addOrder(Order.desc("addDate"));
	        Proposal.setFirstResult((user.getCurrentPage()-1)*user.getPageSize());
	        Proposal.setMaxResults(user.getPageSize());
	        list =Proposal.list();  
	        return list;
	}

	public List<RmActivityCardList> findCardList(String activityId, String state) {
		// TODO Auto-generated method stub
		String hql="from RmActivityCardList where sendStatus='"+state+"' and activityId='"+activityId+"' order by cardId ";
		List<RmActivityCardList> list =this.getHibernateTemplate().find(hql);
		return list;
	}

	public RmActivityCardList getCard(String activityId) {
		// TODO Auto-generated method stub
		String hql="from RmActivityCardList where sendStatus='0' and activityId='"+activityId+"' order by cardId ";
		List<RmActivityCardList> list =this.getHibernateTemplate().find(hql);
		if(list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	public List findActivityUser(String wxOpenId) {
		// TODO Auto-generated method stub
		String hql="from RmActivityUser where wxOpenId='"+wxOpenId+"' ";
		List<RmActivityUser> list =this.getHibernateTemplate().find(hql);
		return list;
	}

	public List findCardRecordList(String wxOpenId) {
		// TODO Auto-generated method stub
		String hql="from RmActivityCardRecord where wxOpenid='"+wxOpenId+"' and state='Y'";
		List<RmActivityCardRecord> list =this.getHibernateTemplate().find(hql);
		return list;
	}

	public RmUser isBandAccount(String openId) {
		// TODO Auto-generated method stub
		String hql="from RmUser where wxOpenCommId='"+openId+"' or wxOpenJzId='"+openId+"'";
		List<RmUser> user =this.getHibernateTemplate().find(hql);
		if(user!=null && user.size()>0)
		{
			return user.get(0);
		}
		return null;
	}

	public RmActivity getActivity(String activityID) {
		// TODO Auto-generated method stub
		String hql="from RmActivity where activityId='"+activityID+"'";
		List<RmActivity> activitys =this.getHibernateTemplate().find(hql);
		if(activitys!=null && activitys.size()>0)
		{
			return activitys.get(0);
		}
		return null;
	}

	public Integer getCardCount() {
		// TODO Auto-generated method stub
		Integer count=0;
		String hql="select count(*) from RmActivityCardList where sendStatus='1'";
		List cards =this.getHibernateTemplate().find(hql);
		if(cards!=null && cards.size()>0)
		{
			count=Integer.parseInt(cards.get(0).toString());
		}
		return count;
	}

	public List findActivityUserByMobile(String mobile) {
		// TODO Auto-generated method stub
		String hql="from RmActivityUser where mobile='"+mobile+"' ";
		List<RmActivityUser> list =this.getHibernateTemplate().find(hql);
		return list;
	}

	public List findActivityUserByTeacher(String teacher) {
		// TODO Auto-generated method stub
		String hql="from RmActivityUser where teacherNo='"+teacher+"' ";
		List<RmActivityUser> list =this.getHibernateTemplate().find(hql);
		return list;
	}

}
