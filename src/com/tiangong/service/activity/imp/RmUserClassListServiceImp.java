package com.tiangong.service.activity.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.tiangong.bean.RmUser;
import com.tiangong.bean.RmUserClassDetail;
import com.tiangong.bean.RmUserClassList;
import com.tiangong.comm.MoodleUtil;
import com.tiangong.dao.activity.RmUserClassListDao;
import com.tiangong.dao.activity.RmUserDao;
import com.tiangong.service.activity.RmUserClassListService;

public class RmUserClassListServiceImp implements RmUserClassListService{

	private RmUserClassListDao rmUserClassListDao;
	@Resource
	private RmUserDao rmUserDao;
	public void setRmUserClassListDao(RmUserClassListDao rmUserClassListDao) {
		this.rmUserClassListDao = rmUserClassListDao;
	}
	@Override
	public List<RmUserClassList> getListByClassNum(int classNum) {
		// TODO Auto-generated method stub
		
		List<RmUserClassList> list = new ArrayList<RmUserClassList>();
		List<RmUserClassList> list2 = new ArrayList<RmUserClassList>();
		list = rmUserClassListDao.getListByClassNum(classNum);
		RmUser rmUser = null;
		String name = null;
		RmUserClassList rmUserClassList = null;
		for (int i = 0; i < list.size(); i++) {
			rmUserClassList = list.get(i);
			rmUser = rmUserDao.getById(list.get(i).getStudentUserId());
			rmUserClassList.setLevel(MoodleUtil.getCurrentLevel(rmUser.getMoodleToken()));
			rmUserClassList.setStuUserName(rmUser.getName());
			list2.add(rmUserClassList);
			//list.get(i).setStuUserName(rmUser.getName());
		}
		
		return list2;
	}

}
