package com.tiangong.service.wechat;

import java.util.List;

import com.tiangong.bean.RmActivityCardList;
import com.tiangong.bean.RmActivityUser;
import com.tiangong.bean.User;

public interface WechatService {
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:03:25
	 * 方法描述：保存对象
	 * 参数名称：@param tp　对象
	 * 返回值：true 保存成功/false 保存失败
	 */
	public boolean save(Object tp);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:04:25
	 * 方法描述：删除
	 * 参数名称：@param tp　对象
	 * 返回值：true 删除成功/false 删除失败
	 */
	public boolean delete(Object tp);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:05:36
	 * 方法描述：更新
	 * 参数名称：@param tp　 对象
	 * 返回值：true 更新成功/false 更新失败
	 */
	public boolean update(Object tp);
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-15 下午05:07:43
	 * 方法描述：根据id获取对象
	 * 参数名称：@param id　id
	 * 返回值： 对象
	 */
	public Object gettp(int id,Class obj);
	
	public List<RmActivityUser> list(RmActivityUser user);
	
	/**
	* @Title: findCardList
	* @Description:根据活动ID和发卡状态获取卡信息列表
	* @author zhao_jinggui
	* @param activityId
	* @param state
	* @return
	 */
	public List<RmActivityCardList> findCardList(String activityId,String state);
	
	/**
	* @Title: CardList
	* @Description:获取当前未使用的卡
	* @author zhao_jinggui
	* @param activityId
	* @return
	 */
	public RmActivityCardList getCard(String activityId);
	
	public List findActivityUserByMobile(String Mobile);
	
	public List findActivityUserByTeacher(String Teacher);
	
	
	
}
