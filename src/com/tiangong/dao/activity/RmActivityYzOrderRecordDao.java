package com.tiangong.dao.activity;

import java.util.List;

import com.tiangong.bean.RmActivityCardList;
import com.tiangong.bean.RmActivityYzOrderRecord;


public interface RmActivityYzOrderRecordDao {
	
	/**
	 * 保存有赞销售订单记录
	 * @method: saveOrderList 
	 * @Description: 保存有赞销售订单记录
	 * @param list 订单记录
	 * @param cardList 库存用户卡
	 * @author: lijianjun 
	 * @date 2015年10月22日 上午9:45:37
	 */
	public void saveOrderList(List<RmActivityYzOrderRecord> list,List<RmActivityCardList> cardList);
	
	/**
	 * 根据活动编号,发送状态,数量 查询活动用户卡片信息
	 * @method: findCardListByASN
	 * @Description: 根据活动编号,发送状态,数量 查询活动用户卡片信息
	 * @param activityId 活动编号
	 * @param sendStatus 发送状态
	 * @param num 数量
	 * @return 活动用户卡片信息
	 * @author: lijianjun
	 * @date 2015年10月22日 上午10:00:06
	 */
	public List<RmActivityCardList> findCardListByASN(String activityId,String sendStatus,int num);
	
	/**
	 * 更新已经发卡的记录状态
	 * @method: updateCardListByList
	 * @Description: 更新已经发卡的记录状态
	 * @param list 已经发卡的记录
	 * @author: lijianjun
	 * @date 2015年10月22日 上午10:16:20
	 */
	public void updateCardListByList(List<RmActivityCardList> list);
	
	/**
	 * 根据订单编号获取有赞交易记录
	 * @method: getOrderByTid
	 * @Description: 根据订单编号获取有赞交易记录
	 * @param tid 订单编号
	 * @return 有赞交易记录对象
	 * @author: lijianjun
	 * @date 2015年10月22日 上午11:09:24
	 */
	public RmActivityYzOrderRecord getOrderByTid(String tid); 
	
	/**
	 * 根据手机号查询所有的订单记录
	 * @method: getOrderListByPhone
	 * @Description: 根据手机号查询所有的订单记录
	 * @param phone 手机号
	 * @return
	 * @author: lijianjun
	 * @date 2015年10月22日 下午4:55:07
	 */
	public List<RmActivityYzOrderRecord> getOrderListByPhone(String phone);
	
	/**
	 * 根据订单号和手机号查询所有的订单记录
	 * @method: getOrderListByPhoneAndTid
	 * @Description: 根据订单号和手机号查询所有的订单记录
	 * @param phone 手机号
	 * @param tid 订单号
	 * @return
	 * @author: lijianjun
	 * @date 2015年10月27日 上午11:53:55
	 */
	public List<RmActivityYzOrderRecord> getOrderListByPhoneAndTid(String phone,String tid);
	
}
