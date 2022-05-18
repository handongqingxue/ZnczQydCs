package com.znczQydCs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class ZhiJianJiLuServiceImpl implements ZhiJianJiLuService {

	@Autowired
	private ZhiJianJiLuMapper zhiJianJiLuDao;

	@Override
	public int queryForInt(Integer zt, String ddh, Integer ddztId, String zjyXm) {
		// TODO Auto-generated method stub
		return zhiJianJiLuDao.queryForInt(zt, ddh, ddztId, zjyXm);
	}

	@Override
	public List<ZhiJianJiLu> queryList(Integer zt, String ddh, Integer ddztId, String zjyXm, int page, int rows,
			String sort, String order) {
		// TODO Auto-generated method stub
		return zhiJianJiLuDao.queryList(zt, ddh, ddztId, zjyXm, (page-1)*rows, rows, sort, order);
	}
}
