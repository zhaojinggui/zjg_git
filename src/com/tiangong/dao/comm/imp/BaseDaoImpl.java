package com.tiangong.dao.comm.imp;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tiangong.comm.ReflectUtils;
import com.tiangong.dao.comm.BaseDao;

/**
 * 
 * 主干持久类
 * @author 
 * @param <T>
 */

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class<T> entityClass;
	
	public BaseDaoImpl() {
		entityClass = ReflectUtils.getClassGenricType(super.getClass());
	}
	
	
	public boolean save(T t) {
		// TODO Auto-generated method stub
		boolean b=false;
		try {
			this.getHibernateTemplate().save(t);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			b=false;
		}
		return b;
	}
	
	public boolean saveOrUpdate(T t) {
		// TODO Auto-generated method stub
		boolean b=false;
		try {
			this.getHibernateTemplate().saveOrUpdate(t);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}

	public boolean update(T t) {
		// TODO Auto-generated method stub
		boolean b=false;
		try {
			this.getHibernateTemplate().update(t);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}

	public boolean delete(T t) {
		// TODO Auto-generated method stub
		boolean b=false;
		try {
			this.getHibernateTemplate().delete(t);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}

	public boolean deleteById(Class c, String id) {
		// TODO Auto-generated method stub
		boolean b=false;
		try {
			Object obj = get(c, id);
			getHibernateTemplate().delete(obj);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
			e.printStackTrace();
		}
		return b;
	}

	public boolean deleteById(Class c, Integer id) {
		// TODO Auto-generated method stub
		boolean b=false;
		try {
			Object obj = get(c, id);
			getHibernateTemplate().delete(obj);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean deleteById(Class c,Long id){
		boolean b=false;
		try {
			Object obj = get(id);
			getHibernateTemplate().delete(obj);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
			e.printStackTrace();
		}
		return b;
	}

	public T get(Class c, String id) {
		// TODO Auto-generated method stub
		return (T) getHibernateTemplate().get(c, id);
	}

	public T get(Class c, Integer id) {
		// TODO Auto-generated method stub
		return (T) getHibernateTemplate().get(c, id);
	}
	
	public boolean  deleteById(Serializable id){
		boolean b=false;
		try {
			Object obj = get(id);
			getHibernateTemplate().delete(obj);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
			e.printStackTrace();
		}
		return b;
	}
	

	public T get(Serializable id)  {
		try {
			return (T) getHibernateTemplate().get(entityClass, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Long findCountBySQL( final String sql,final Object[] params) {
		return (Long) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,SQLException {
				Query query = s.createSQLQuery(sql);
				if(params!=null && params.length>0){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				Long result = (new BigInteger(query.uniqueResult().toString())).longValue();
				return result;
			}
		});
	}
}
