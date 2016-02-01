
package com.tiangong.service.system;

import java.util.List;
import java.util.Map;

import com.tiangong.bean.RmWxDictionary;

public interface DictionaryService {
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:08:08
	 * 方法描述：保存数据字典信息
	 * 参数名称：@param obj　数据字典对象
	 * 返回值：true 保存成功　/ false 保存失败
	 */
	public boolean save(RmWxDictionary obj);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:09:32
	 * 方法描述：修改数据字典信息
	 * 参数名称：@param obj　数据字典对象
	 * 返回值：true 修改成功　/ false 修改失败
	 */
	public boolean update(RmWxDictionary obj);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:10:38
	 * 方法描述：删除字典信息
	 * 参数名称：@param obj　数据字典对象
	 * 返回值：true 删除成功　/ false 删除失败
	 */
	public boolean delete(RmWxDictionary obj);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:12:23
	 * 方法描述：获取数据字典对象
	 * 参数名称：@param id　数据字典id
	 * 参数名称：@return
	 * 返回值：数据字典对象
	 */
	public RmWxDictionary getDict(String id);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:13:27
	 * 方法描述：查询数据字典的数目
	 * 返回值：字典数目
	 */
	public int count(RmWxDictionary RmWxDictionary);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 下午03:42:16
	 * 方法描述：分页查询字典信息
	 * 参数名称：@param currentPage　当前页
	 * 参数名称：@param pageSize　　　总页数
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
	 * 创建人：赵井桂
	 * 创建时间：2013-10-11 下午01:13:05
	 * 方法描述：根据字典名称查询字典值
	 * 参数名称：@param dictName
	 * 参数名称：@return
	 * 返回值：
	 */
	public String getDictValue(String dictName);
	
	/**
	 * 得到所有字典表
	 * @author wangzhe/bdjb0064
	 * date: 2015-3-27 上午10:34:00 <br/>
	 * @return
	 */
	public List getAllDictionary();
	
	/**
	 * 
	 * @author wangzhe/bdjb0064
	 * date: 2015-3-31 上午10:21:38 <br/>
	 * @param dicNames = 'name1','name2','name3'
	 * @return
	 */
	public List<RmWxDictionary> getByDicNames(String dicNames);
	
}
