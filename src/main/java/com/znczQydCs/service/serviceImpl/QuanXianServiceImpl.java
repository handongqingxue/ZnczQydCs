package com.znczQydCs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class QuanXianServiceImpl implements QuanXianService {
	
	@Autowired
	private QuanXianMapper quanXianDao;

	@Override
	public int add(QuanXian qx) {
		// TODO Auto-generated method stub
		return quanXianDao.add(qx);
	}

	@Override
	public int edit(QuanXian qx) {
		// TODO Auto-generated method stub
		return quanXianDao.edit(qx);
	}

	@Override
	public int queryForInt(String mc) {
		// TODO Auto-generated method stub
		return quanXianDao.queryForInt(mc);
	}

	@Override
	public List<QuanXian> queryList(String mc, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return quanXianDao.queryList(mc, (page-1)*rows, rows, sort, order);
	}

	@Override
	public QuanXian selectById(String id) {
		// TODO Auto-generated method stub
		return quanXianDao.selectById(id);
	}

	@Override
	public List<QuanXian> queryCBBList() {
		// TODO Auto-generated method stub
		return quanXianDao.queryCBBList();
	}

}
