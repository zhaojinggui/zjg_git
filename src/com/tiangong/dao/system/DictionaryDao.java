package com.tiangong.dao.system;

import java.util.List;

import com.tiangong.bean.RmWxDictionary;

public interface DictionaryDao {
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:13:27
	 * 方法描述：查询数据字典的数目
	 * 返回值：字典数目
	 */
	public int count(RmWxDictionary dictionary);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 下午03:38:32
	 * 方法描述：分页查询字典信息
	 * 参数名称：@param currentPage　当前页
	 * 参数名称：@param pageSize　　总页数
	 * 返回值：字典信息集合
	 */
	public List pagelist (RmWxDictionary dictionary);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 下午03:39:35
	 * 方法描述：根据字典项目名称查询字典信息
	 * 参数名称：@param dictname　字典项目名称
	 * 返回值：字典项目信息集合
	 */
	public List seldictbyname(String dictname);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 下午03:58:24
	 * 方法描述：根据字典项名称和字典项键名进行查询
	 * 参数名称：@param dictname　字典项名称
	 * 参数名称：@param key　　　　字典项键名
	 * 返回值：　字典项目信息集合
	 */
	public List selbynameandkey(String dictname,String key);
	
	/**
	 * 得到所有字典表
	 * @author wangzhe/bdjb0064
	 * date: 2015-3-27 上午10:35:54 <br/>
	 * @return
	 */
	public List getAllDictionary();
	
	
}
