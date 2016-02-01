package com.tiangong.service.system;

import java.util.List;

import com.tiangong.bean.Rolefunction;
import com.tiangong.bean.RmWxMenu;

public interface FunctionService {
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:43:50
	 * 方法描述：保存权限信息
	 * 参数名称：@param fun　权限对象
	 * 返回值：true 保存成功　/ false 保存失败
	 */
	public boolean save(Object fun);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:44:38
	 * 方法描述：删除权限信息
	 * 参数名称：@param fun　权限对象
	 * 返回值：true 删除成功　/ false 删除失败
	 */
	public boolean delfun(Object fun);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:45:49
	 * 方法描述：更新权限信息
	 * 参数名称：@param fun　权限对象
	 * 返回值：true 更新成功　/ false 更新失败
	 *
	 */
	public boolean updatefun(Object fun);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:47:13
	 * 方法描述：根据id获取权限对象
	 * 参数名称：@param id　权限id
	 * 返回值： 权限对象　
	 */
	public RmWxMenu getfun(String id);
	

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午03:16:50
	 * 方法描述：查询所有权限id
	 * 返回值：权限id集合
	 */
	public List selAllfunid();
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午03:18:05
	 * 方法描述：查询父id的信息
	 * 参数名称：@param id
	 * 返回值：父id对象集合
	 */
	public List selPid(String id);
	
	public List selfunidbyck(String ck);
	
	public List emppurviewlist(int currentPage, int pageSize);
	
	public int count();
	
	public List selorgnamebyid(int id);
	
	public List selempview(String empid);
	
	public Rolefunction getep(int id);
	
	public List selSpecialByaccount(String account);
	
	public boolean delSpecial(String account);
	
	public List selprovewlist();
	
}
