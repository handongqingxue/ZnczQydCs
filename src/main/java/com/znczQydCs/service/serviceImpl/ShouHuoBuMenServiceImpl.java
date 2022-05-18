package com.znczQydCs.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class ShouHuoBuMenServiceImpl implements ShouHuoBuMenService {

	@Autowired
	private ShouHuoBuMenMapper shouHuoBuMenDao;
	
	@Override
	public int add(ShouHuoBuMen shbm) {
		// TODO Auto-generated method stub
		return shouHuoBuMenDao.add(shbm);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=shouHuoBuMenDao.deleteByIds(idList);
		return count;
	}

	@Override
	public int edit(ShouHuoBuMen shbm) {
		// TODO Auto-generated method stub
		return shouHuoBuMenDao.edit(shbm);
	}

	@Override
	public int queryForInt(String mc) {
		// TODO Auto-generated method stub
		return shouHuoBuMenDao.queryForInt(mc);
	}

	@Override
	public List<ShouHuoBuMen> queryList(String mc, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return shouHuoBuMenDao.queryList(mc, (page-1)*rows, rows, sort, order);
	}

	@Override
	public ShouHuoBuMen selectById(String id) {
		// TODO Auto-generated method stub
		return shouHuoBuMenDao.selectById(id);
	}
	
	@Override
	public List<ShouHuoBuMen> queryCBBList() {
		// TODO Auto-generated method stub
		return shouHuoBuMenDao.queryCBBList();
	}
}
