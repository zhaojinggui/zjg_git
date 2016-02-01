package com.tiangong.dao.comm;

import java.io.Serializable;

public interface BaseDao<T> {
	
	/**
	 * 保存
	 * @param t
	 */
	public boolean save(T t) ;
	
	/**
	 * 更新
	 * @param t
	 */
	public boolean update(T t) ;

	/**
	 * 删除
	 * @param t
	 */
	public boolean delete(T t);

	/**
	 * 
	 * @author wangzhe/bdjb0064
	 * date: 2015-3-12 下午3:52:23 <br/>
	 * @param id
	 * @return
	 */
	public boolean deleteById(Serializable id);
	/**
	 * 删除
	 * @param c
	 * @param id
	 */
	public boolean deleteById(Class c,String id) ;
	
	/**
	 * 删除
	 * @param c
	 * @param id
	 */
	public boolean deleteById(Class c,Integer id) ;
	
	/**
	 * 删除
	 * @param c
	 * @param id
	 */
	public boolean deleteById(Class c,Long id) ;

	/**
	 * 根据id查询查询实体信息
	 * @param c 实体类
	 * @param id string类型的id
	 * @return T 所属的实体
	 */
	public T get(Class c,String id);
	
	/**
	 * 根据id查询查询实体信息
	 * @param c 实体类
	 * @param id integer类型的id
	 * @return 所属的实体
	 */
	public T get(Class c,Integer id);
	
	/**
	 * 根据id查询查询实体信息
	 * @param c 实体类
	 * @param id integer类型的id
	 * @return 所属的实体
	 */
	public T get(Serializable id);
	
	/**
	 *  保存或修改
	 * @author wangzhe/bdjb0064
	 * date: 2015-3-17 上午9:49:05 <br/>
	 * @param t
	 * @return
	 */
	public boolean saveOrUpdate(T t);
	
	public Long findCountBySQL( final String sql,final Object[] params);
}
