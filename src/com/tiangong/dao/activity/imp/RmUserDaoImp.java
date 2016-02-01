package com.tiangong.dao.activity.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tiangong.bean.RmUser;
import com.tiangong.comm.MD5Util;
import com.tiangong.dao.activity.RmUserDao;
import com.tiangong.dao.comm.imp.BaseDaoImpl;

public class RmUserDaoImp  extends BaseDaoImpl<RmUser> implements RmUserDao{

	@Override
	public boolean rmUserExeist(RmUser rmUser) {
		List<RmUser> result = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM rm_user WHERE ( name = '");
		sql.append(rmUser.getName());
		sql.append("' and password = '");
		sql.append(MD5Util.MD5Encode(rmUser.getPassword(),""));
		sql.append("') or ( mobile = '");
		sql.append(rmUser.getName());//rmUser的手机号
		sql.append("' and password = '");
		sql.append(MD5Util.MD5Encode(rmUser.getPassword(),""));
		sql.append("')");
		result = this.getSession().createSQLQuery(sql.toString()).list();
		if(result.size() > 0){
			return true;
		}
		return false;
	}

	@Override
	public RmUser getRmUser(RmUser rmUser) {
		List<RmUser> list = new ArrayList<RmUser>();
		String s = "from RmUser u where u.name = '"+rmUser.getName()+"' and u.password = '"+MD5Util.MD5Encode(rmUser.getPassword(), "")+"'";
		list = this.getHibernateTemplate().find(s);
		if(list.size() > 0){
			return (RmUser)list.get(0);
		}
		return null;
	}

	@Override
	public void bindRmUser(RmUser rmUser) {
		this.saveOrUpdate(rmUser);
	}

	@Override
	public RmUser getTeacherRmUser(RmUser rmUser) {
		List<RmUser> list = new ArrayList<RmUser>();
		String s = "from RmUser u where u.name = '"+rmUser.getName()+"' and u.password = '"+rmUser.getPassword()+"' and u.role ='" +rmUser.getRole()+"'";
		list = this.getHibernateTemplate().find(s);
		if(list.size() > 0){
			return (RmUser)list.get(0);
		}
		return null;
	}

	@Override
	public RmUser getById(int id) {
		// TODO Auto-generated method stub
		return this.get(id);
	}

	@Override
	public boolean isHasPoint(int teacherId) {
		
		String sql = "SELECT COUNT(*) FROM rm_point WHERE teacher_id = "+teacherId;		
		
		List<Object> obj = this.getSession().createSQLQuery(sql).list();
		
		System.out.println(obj.get(0)+"aaaa");
		
		int result = Integer.valueOf(obj.get(0).toString());
		if(result>0){
			return true;
		}
		return false;
	}

	@Override
	public void addPoint(int pointValue, int teacherId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO rm_point (teacher_id,point_value) VALUES (");
		sql.append(teacherId);
		sql.append(",");
		sql.append(pointValue);
		sql.append(")");
		System.out.println("添加教师积分："+sql.toString());
		this.getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	@Override
	public void addPointDetail(int pointId, String pointType, int pointValue) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO rm_point_detail (point_id,point_type,point_value,upd_date) VALUES (");
		sql.append(pointId);
		sql.append(",'");
		sql.append(pointType);
		sql.append("',");
		sql.append(pointValue);
		/*sql.append(")");*/
		String d = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 d = sd.format(new Date());
		sql.append(",'");
		sql.append(d);
		sql.append("')");
		System.out.println("添加积分详细："+sql.toString());
		this.getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	@Override
	public void updatePoint(int teacherId) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE rm_point SET point_value=point_value+100");
		sql.append(" WHERE teacher_id = ");
		sql.append(teacherId);
		System.out.println("更新教师积分："+sql.toString());
		this.getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	@Override
	public int getPointIdByTeacherId(int teacherId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM rm_point WHERE teacher_id = ");
		sql.append(teacherId);
		System.out.println("根据教师id获取积分id："+sql.toString());
		List<Object[]> obj = this.getSession().createSQLQuery(sql.toString()).list();
		if(obj.size()>0){
			return Integer.valueOf(obj.get(0)[0].toString());
		}
		return 0;
	}

}
