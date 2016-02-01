package com.tiangong.service.wechat.imp;

import java.util.List;

import com.tiangong.bean.RmActivity;
import com.tiangong.bean.RmActivityCardList;
import com.tiangong.bean.RmActivityUser;
import com.tiangong.bean.RmUser;
import com.tiangong.dao.wechat.imp.WechatDaoImp;
import com.tiangong.service.wechat.WechatService;

public class WechatServiceImp implements WechatService {

	private WechatDaoImp wechatDao;
	

	public void setWechatDao(WechatDaoImp wechatDao) {
		this.wechatDao = wechatDao;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:59:32
	 * 方法描述：删除对象
	 * 参数名称：@param tp
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public boolean delete(Object tp) {
		// TODO Auto-generated method stub
		return wechatDao.delete(tp);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:59:32
	 * 方法描述：根据id获取对象
	 * 参数名称：@param id
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public Object gettp(int id,Class obj) {
		// TODO Auto-generated method stub
		return wechatDao.gettp(id, obj);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:59:32
	 * 方法描述：保存对象信息
	 * 参数名称：@param tp
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public boolean save(Object tp) {
		// TODO Auto-generated method stub
		return wechatDao.save(tp);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-6-27 下午05:59:32
	 * 方法描述：更新对象信息
	 * 参数名称：@param tp
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public boolean update(Object tp) {
		// TODO Auto-generated method stub
		return wechatDao.update(tp);
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2013-7-26 下午05:17:41
	 * 方法描述：
	 * 参数名称：@return
	 * 返回值：
	 */
	
	public List<RmActivityUser> list(RmActivityUser user) {
		// TODO Auto-generated method stub
		return wechatDao.list(user);
	}

	@Override
	public List<RmActivityCardList> findCardList(String activityId,String state) {
		// TODO Auto-generated method stub
		return wechatDao.findCardList(activityId,state);
	}

	@Override
	public RmActivityCardList getCard(String activityId) {
		// TODO Auto-generated method stub
		return wechatDao.getCard(activityId);
	}

	public List findActivityUser(String wxOpenId) {
		// TODO Auto-generated method stub
		return wechatDao.findActivityUser(wxOpenId);
	}

	public List findCardRecordList(String wxOpenId) {
		// TODO Auto-generated method stub
		return wechatDao.findCardRecordList(wxOpenId);
	}

	public RmUser isBandAccount(String openId) {
		// TODO Auto-generated method stub
		return wechatDao.isBandAccount(openId);
	}

	public RmActivity getActivity(String activityID) {
		// TODO Auto-generated method stub
		return wechatDao.getActivity(activityID);
	}

	public Integer getCardCount() {
		// TODO Auto-generated method stub
		return wechatDao.getCardCount();
	}

	@Override
	public List findActivityUserByMobile(String Mobile) {
		// TODO Auto-generated method stub
		return wechatDao.findActivityUserByMobile(Mobile);
	}

	@Override
	public List findActivityUserByTeacher(String Teacher) {
		// TODO Auto-generated method stub
		return wechatDao.findActivityUserByTeacher(Teacher);
	}

	public String findCardFlag(String openId) {
		// TODO Auto-generated method stub
		String flag=null;
		List list =findActivityUser(openId);
		List list2 =findCardRecordList(openId);
		if(list.size()>0 && list2.size()>0)
		{
			flag="1";//已参加报名活动			
		}else if(list.size()>0 && list2.size()==0)
		{
			flag="2";//已参加报名活动未领卡	
		}
		return flag;
	}

}
