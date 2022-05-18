package com.znczQydCs.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class CheLiangTaiZhangServiceImpl implements CheLiangTaiZhangService {

	@Autowired
	private CheLiangTaiZhangMapper cheLiangTaiZhangDao;

	@Override
	public int add(CheLiangTaiZhang cltz) {
		// TODO Auto-generated method stub
		return cheLiangTaiZhangDao.add(cltz);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=cheLiangTaiZhangDao.deleteByIds(idList);
		return count;
	}

	@Override
	public int edit(CheLiangTaiZhang cltz) {
		// TODO Auto-generated method stub
		return cheLiangTaiZhangDao.edit(cltz);
	}

	@Override
	public int queryZHCXForInt(String ddh, String cph, String jcsjks, String jcsjjs) {
		// TODO Auto-generated method stub
		return cheLiangTaiZhangDao.queryZHCXForInt(ddh,cph,jcsjks,jcsjjs);
	}

	@Override
	public List<CheLiangTaiZhang> queryZHCXList(String ddh, String cph, String jcsjks, String jcsjjs, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return cheLiangTaiZhangDao.queryZHCXList(ddh, cph, jcsjks, jcsjjs, (page-1)*rows, rows, sort, order);
	}

	@Override
	public CheLiangTaiZhang selectById(String id) {
		// TODO Auto-generated method stub
		return cheLiangTaiZhangDao.selectById(id);
	}
}
