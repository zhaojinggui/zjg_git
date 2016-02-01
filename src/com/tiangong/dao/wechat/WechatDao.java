package com.tiangong.dao.wechat;
import java.util.List;

import com.tiangong.bean.RmActivityUser;

public interface WechatDao {

	public List<RmActivityUser> list(RmActivityUser user);
	
}
