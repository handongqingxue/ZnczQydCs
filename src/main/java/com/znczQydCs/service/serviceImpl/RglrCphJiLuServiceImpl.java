package com.znczQydCs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class RglrCphJiLuServiceImpl implements RglrCphJiLuService {

	@Autowired
	private RglrCphJiLuMapper rglrCphJiLuDao;

	@Override
	public int add(RglrCphJiLu rglrCphJiLu) {
		// TODO Auto-generated method stub
		return rglrCphJiLuDao.add(rglrCphJiLu);
	}

	@Override
	public boolean checkIfExistByDdIdCph(Integer ddId, String cph) {
		// TODO Auto-generated method stub
		int count=rglrCphJiLuDao.getCount(ddId, cph);
		return count==0?false:true;
	}

	@Override
	public List<String> queryXzCphCBBList(int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return rglrCphJiLuDao.queryXzCphCBBList((page-1)*rows, rows, sort, order);
	}

	@Override
	public List<RglrCphJiLu> queryLrSjcCBBList(String sjc, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return rglrCphJiLuDao.queryLrSjcCBBList(sjc, (page-1)*rows, rows, sort, order);
	}

	@Override
	public List<RglrCphJiLu> queryLrWscphCBBList(String sjc, String wscph, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return rglrCphJiLuDao.queryLrWscphCBBList(sjc, wscph, (page-1)*rows, rows, sort, order);
	}
}
