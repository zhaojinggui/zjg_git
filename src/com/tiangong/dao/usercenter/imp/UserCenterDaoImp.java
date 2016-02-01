package com.tiangong.dao.usercenter.imp;

import java.util.List;

import org.hibernate.Query;
import com.tiangong.bean.RmUser;
import com.tiangong.dao.comm.imp.BaseDaoImpl;
import com.tiangong.dao.usercenter.UserCenterDao;

public class UserCenterDaoImp extends BaseDaoImpl implements UserCenterDao{

	public RmUser getUserCenterById(String mobile) {
		RmUser rmUser = null;
		String hql="from RmUser where mobile=?";
		List list =this.getHibernateTemplate().find(hql,mobile);
		if(list.size()>0)
		{
			rmUser=(RmUser) list.get(0);
		}
		return rmUser;
	}

	public List getUserReadingList(String str) {
		// TODO Auto-generated method stub
		String hql = "select u.id,u.name,u.role,u.moodle_token,d.age,d.study_years from rm_user u  inner JOIN rm_user_detail d on u.id=d.id where name='"+str+"'";
		Query query =this.getSession().createSQLQuery(hql);
		List list =query.list();
		return list;
	}

	/*public RmUser gettp(String id, Class obj) {
		Object object=null;
		try {
			object=this.getHibernateTemplate().get(obj, id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return (RmUser) object;
	}*/

	public RmUser getUserCenterByName(String name) {
		RmUser rmUser=null;
		String hql="from RmUser where name=?";
		List list =this.getHibernateTemplate().find(hql,name);
		if(list.size()>0)
		{
			rmUser=(RmUser) list.get(0);
		}
		return rmUser;
	}

	public Object gettp(String rmId, Class obj) {
		// TODO Auto-generated method stub
				Object object=null;
				try {
					object=this.getHibernateTemplate().get(obj, rmId);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return object;
	}

}
