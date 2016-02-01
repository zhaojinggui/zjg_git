package com.tiangong.service.system.imp;

import java.util.List;

import com.tiangong.bean.Rolefunction;
import com.tiangong.bean.RmWxMenu;
import com.tiangong.dao.system.imp.FunctionDaoImp;
import com.tiangong.service.system.FunctionService;

public class FunctionServiceImp implements FunctionService {

	FunctionDaoImp funDao;

	public void setFunDao(FunctionDaoImp funDao) {
		this.funDao = funDao;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:59:59
	 * 方法描述：删除权限信息
	 * 参数名称：@param fun　权限对象
	 * 返回值：true 删除成功　/ false 删除失败
	 */
	
	public boolean delfun(Object fun) {
		// TODO Auto-generated method stub
		return funDao.delfun(fun);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:59:59
	 * 方法描述：根据id获取权限对象
	 * 参数名称：@param id　权限id
	 * 返回值：权限对象
	 */
	
	public RmWxMenu getfun(String id) {
		// TODO Auto-generated method stub
		return funDao.getfun(id);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:59:59
	 * 方法描述：保存权限信息
	 * 参数名称：@param fun　权限对象
	 * 返回值：　true 保存成功　/ false 保存失败
	 */
	
	public boolean save(Object fun) {
		// TODO Auto-generated method stub
		return funDao.save(fun);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午01:59:59
	 * 方法描述：更新权限信息
	 * 参数名称：@param fun　权限对象
	 * 返回值：　true 更新成功　/ false 更新失败
	 */
	
	public boolean updatefun(Object fun) {
		// TODO Auto-generated method stub
		return funDao.updatefun(fun);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午03:25:37
	 * 方法描述：查询所有权限id
	 * 返回值：权限id 集合
	 */
	
	public List selAllfunid() {
		// TODO Auto-generated method stub
		return funDao.selAllfunid();
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午03:25:37
	 * 方法描述：查询父id对象信息
	 * 参数名称：@param id　父id
	 * 返回值：父id对象信息集合
	 */
	
	public List selPid(String id) {
		// TODO Auto-generated method stub
		return funDao.selPid(id);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-1-10 下午01:44:29
	 * 方法描述：
	 * 参数名称：@param ck
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public List selfunidbyck(String ck) {
		// TODO Auto-generated method stub
		return funDao.selfunidbyck(ck);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-3-6 上午10:30:08
	 * 方法描述：
	 * 参数名称：@param currentPage
	 * 参数名称：@param pageSize
	 * 返回值：
	 */
	
	public List emppurviewlist(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		
		return funDao.emppurviewlist(currentPage, pageSize);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-3-6 上午10:48:44
	 * 方法描述：
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public int count() {
		// TODO Auto-generated method stub
		return funDao.count();
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2012-3-6 下午02:55:23
	 * 方法描述：
	 * 参数名称：@param id
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public List selorgnamebyid(int id) {
		// TODO Auto-generated method stub
		return funDao.selorgnamebyid(id);
	}

	
	public List selempview(String empaccount) {
		// TODO Auto-generated method stub
		return funDao.selempview(empaccount);
	}

	
	public Rolefunction getep(int id) {
		// TODO Auto-generated method stub
		return funDao.getep(id);
	}
	
	
	public List selSpecialByaccount(String account) {
		// TODO Auto-generated method stub
		return funDao.selSpecialByaccount(account);
	}

	
	public boolean delSpecial(String account) {
		// TODO Auto-generated method stub
		return funDao.delSpecial(account);
	}

	
	public List selprovewlist() {
		// TODO Auto-generated method stub
		return funDao.selprovewlist();
	}
	
}
