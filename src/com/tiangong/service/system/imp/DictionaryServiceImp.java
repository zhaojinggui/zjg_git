package com.tiangong.service.system.imp;

import java.util.List;

import com.tiangong.bean.RmWxDictionary;
import com.tiangong.dao.system.imp.DictionaryDaoImp;
import com.tiangong.service.system.DictionaryService;

public class DictionaryServiceImp implements DictionaryService {

	private DictionaryDaoImp dictDao;

	public void setDictDao(DictionaryDaoImp dictDao) {
		this.dictDao = dictDao;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:33:27
	 * 方法描述：查询字典总数目
	 * 返回值：字典数目
	 */
	
	public int count(RmWxDictionary dictionary) {
		// TODO Auto-generated method stub
		return dictDao.count(dictionary);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:33:27
	 * 方法描述：删除字典信息
	 * 参数名称：@param obj　数据字典对象
	 * 返回值：true 删除成功　/ false 删除失败
	 */
	
	public boolean delete(RmWxDictionary obj) {
		// TODO Auto-generated method stub
		return dictDao.delete(obj);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:33:27
	 * 方法描述：获取数据字典信息对象
	 * 参数名称：@param id　数据字典id
	 * 返回值：数据字典对象
	 */
	
	public RmWxDictionary getDict(String id) {
		// TODO Auto-generated method stub
		return dictDao.get(RmWxDictionary.class, id);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:33:27
	 * 方法描述：分页查询数据字典信息
	 * 参数名称：@param pageSize　总页数
	 * 参数名称：@param currentPage　当前页
	 * 返回值：　数据字典集合
	 */
	
	public List pagelist(RmWxDictionary dictionary) {
		// TODO Auto-generated method stub
		return dictDao.pagelist(dictionary);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:33:27
	 * 方法描述：保存字典信息
	 * 参数名称：@param obj　数据字典对象
	 * 返回值：true 保存成功　/ false 保存失败
	 */
	
	public boolean save(RmWxDictionary obj) {
		// TODO Auto-generated method stub
		return dictDao.save(obj);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 上午10:33:27
	 * 方法描述：修改字典信息
	 * 参数名称：@param obj　数据字典对象
	 * 返回值：true 修改成功　/ false 修改失败
	 */
	
	public boolean update(RmWxDictionary obj) {
		// TODO Auto-generated method stub
		return dictDao.update(obj);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 下午03:42:55
	 * 方法描述：根据字典项目名称查询字典信息
	 * 参数名称：@param dictname　字典项目名称
	 * 返回值：字典项目信息集合
	 */
	
	public List seldictbyname(String dictname) {
		// TODO Auto-generated method stub
		return dictDao.seldictbyname(dictname);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-21 下午04:02:02
	 * 方法描述：根据字典项名称和字典项键名进行查询
	 * 参数名称：@param dictname　字典项名称
	 * 参数名称：@param key　　　　字典项键名
	 * 返回值：　字典项目信息集合
	 */
	
	public List selbynameandkey(String dictname, String key) {
		// TODO Auto-generated method stub
		return dictDao.selbynameandkey(dictname, key);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-10-11 下午01:13:28
	 * 方法描述：根据字典名称查询字典值
	 * 参数名称：@param dictName
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public String getDictValue(String dictName) {
		// TODO Auto-generated method stub
		String string="";
		List<RmWxDictionary> list=dictDao.seldictbyname(dictName);
		if(list.size()>0)
		{
			string=list.get(0).getDictKeyValue();
		}
		return string;
	}
	
	public List getAllDictionary(){
		return dictDao.getAllDictionary();
	}

	public List getByDicNames(String dicNames) {
		return dictDao.getByDicNames(dicNames);
	}
	
	
}
