package com.tiangong.dao.system.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tiangong.bean.RmWxDictionary;
import com.tiangong.dao.comm.imp.BaseDaoImpl;
import com.tiangong.dao.system.DictionaryDao;


public class DictionaryDaoImp extends BaseDaoImpl<RmWxDictionary> implements DictionaryDao  {

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:18:56
	 * 方法描述：查询字典总数目
	 * 返回值：字典数目
	 */
	
	public int count(RmWxDictionary dictionary) {
		// TODO Auto-generated method stub
		  Criteria Proposal =this.getSession().createCriteria(RmWxDictionary.class);
	        if(!"".equals(dictionary.getDictName()) && dictionary.getDictName()!=null)
	        {
	            Proposal.add( Restrictions.like("dictName", "%"+dictionary.getDictName()+"%"));
	        }
	        if(!"".equals(dictionary.getDictKeyName()) && dictionary.getDictKeyName()!=null)
	        {
	        	 Proposal.add( Restrictions.like("dictKeyName", "%"+dictionary.getDictKeyName()+"%"));
	        }
	        if (!"".equals(dictionary.getDictKeyValue())&& dictionary.getDictKeyValue()!=null) {
	            
	            Proposal.add(Restrictions.like("dictKeyValue", "%"+dictionary.getDictKeyValue()+"%"));
	        }
	        return ((Integer) Proposal.setProjection(Projections.rowCount()).uniqueResult()).intValue();   
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:18:56
	 * 方法描述：分页查询数据字典信息
	 * 参数名称：@param pageSize　总页数
	 * 参数名称：@param currentPage　当前页
	 * 返回值：　数据字典集合
	 */
	
	public List pagelist(RmWxDictionary dictionary) {
		// TODO Auto-generated method stub
		List<RmWxDictionary> list=null;
		  Criteria Proposal =this.getSession().createCriteria(RmWxDictionary.class);
	        if(!"".equals(dictionary.getDictName()) && dictionary.getDictName()!=null)
	        {
	            Proposal.add( Restrictions.like("dictName", "%"+dictionary.getDictName()+"%"));
	        }
	        if(!"".equals(dictionary.getDictKeyName()) && dictionary.getDictKeyName()!=null)
	        {
	        	 Proposal.add( Restrictions.like("dictKeyName", "%"+dictionary.getDictKeyName()+"%"));
	        }
	        if (!"".equals(dictionary.getDictKeyValue())&& dictionary.getDictKeyValue()!=null) {
	            
	            Proposal.add(Restrictions.like("dictKeyValue", "%"+dictionary.getDictKeyValue()+"%"));
	        }
	        Proposal.setFirstResult((dictionary.getCurrentPage()-1)*dictionary.getPageSize());
	        Proposal.setMaxResults(dictionary.getPageSize());
	        list =Proposal.list();  
	        return list;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 下午03:40:18
	 * 方法描述：根据字典项目名称查询字典信息
	 * 参数名称：@param dictname　字典项目名称
	 * 返回值：字典项目信息集合
	 */
	
	public List seldictbyname(String dictname) {
		// TODO Auto-generated method stub
		String queryString="from RmWxDictionary where dictName=? and dictStatus=1 order by sortNum asc";
		return this.getHibernateTemplate().find(queryString,dictname);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 下午03:59:39
	 * 方法描述：根据字典项名称和字典项键名进行查询
	 * 参数名称：@param dictname　字典项名称
	 * 参数名称：@param key　　　　字典项键名
	 * 返回值：　字典项目信息集合
	 */
	
	public List selbynameandkey(String dictname, String key) {
		// TODO Auto-generated method stub
		String queryString="from RmWxDictionary where dictName=? and dictKeyName=? and dictStatus=1 ";
		return this.getHibernateTemplate().find(queryString,new String[]{dictname,key});
	}
	 
	public List<String> getAllDictionary() {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct(dict_name) from ");
		sql.append(" RmWxDictionary");
		sql.append(" where dict_status=1");
		return this.getSession().createSQLQuery(sql.toString()).list();
	}
	
	public List getByDicNames(String dicNames){
		String queryString="from RmWxDictionary where dictName in ("+dicNames+") and dictStatus=1 ";
		return this.getHibernateTemplate().find(queryString);
	}

}
