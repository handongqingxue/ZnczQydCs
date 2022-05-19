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
	public int queryForInt(Integer jg, String ddh, Integer ddztId, String zjyZsxm) {
		// TODO Auto-generated method stub
		return zhiJianJiLuDao.queryForInt(jg, ddh, ddztId, zjyZsxm);
	}

	@Override
	public List<ZhiJianJiLu> queryList(Integer jg, String ddh, Integer ddztId, String zjyZsxm, int page, int rows,
			String sort, String order) {
		// TODO Auto-generated method stub
		return zhiJianJiLuDao.queryList(jg, ddh, ddztId, zjyZsxm, (page-1)*rows, rows, sort, order);
	}
}
