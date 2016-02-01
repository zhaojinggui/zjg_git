package com.tiangong.service.activity;

import com.tiangong.bean.RmActivityYzYzm;



public interface RmActivityYzYzmService {
	/**
	 * 保存验证码（如果有改手机号记录则更新，没有则添加）
	 * @method: saveYzm
	 * @Description: 保存验证码
	 * @param phone 手机号
	 * @param yzm 验证码
	 * @author: lijianjun
	 * @date 2015年10月22日 下午5:59:50
	 */
	public void saveYzm(String phone,String yzm);
	
	/**
	 * 根据手机号获取验证码记录
	 * @method: getByPhone
	 * @Description: 根据手机号获取验证码记录
	 * @param phone 手机号
	 * @return
	 * @author: lijianjun
	 * @date 2015年10月22日 下午6:04:41
	 */
	public RmActivityYzYzm getByPhone(String phone);
	
	/**
	 * 验证验证码是否正确
	 * @method: getByPhone
	 * @Description: 验证验证码是否正确
	 * @param phone 手机号
	 * @param phone 手机号
	 * @return
	 * @author: lijianjun
	 * @date 2015年10月22日 下午6:04:41
	 */
	public RmActivityYzYzm getByPhoneAndCode(String phone,String code);
}
