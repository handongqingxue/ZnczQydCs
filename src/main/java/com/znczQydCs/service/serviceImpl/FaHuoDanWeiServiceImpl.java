package com.znczQydCs.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class FaHuoDanWeiServiceImpl implements FaHuoDanWeiService {

	@Autowired
	private FaHuoDanWeiMapper faHuoDanWeiDao;
	
	@Override
	public int add(FaHuoDanWei fhdw) {
		// TODO Auto-generated method stub
		return faHuoDanWeiDao.add(fhdw);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=faHuoDanWeiDao.deleteByIds(idList);
		return count;
	}

	@Override
	public int edit(FaHuoDanWei fhdw) {
		// TODO Auto-generated method stub
		return faHuoDanWeiDao.edit(fhdw);
	}

	@Override
	public int queryForInt(String mc) {
		// TODO Auto-generated method stub
		return faHuoDanWeiDao.queryForInt(mc);
	}

	@Override
	public List<FaHuoDanWei> queryList(String mc, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return faHuoDanWeiDao.queryList(mc, (page-1)*rows, rows, sort, order);
	}

	@Override
	public FaHuoDanWei selectById(String id) {
		// TODO Auto-generated method stub
		return faHuoDanWeiDao.selectById(id);
	}
	
	@Override
	public List<FaHuoDanWei> queryCBBList() {
		// TODO Auto-generated method stub
		return faHuoDanWeiDao.queryCBBList();
	}

	@Override
	public boolean checkIfWtbToYf() {
		// TODO Auto-generated method stub
		int count=faHuoDanWeiDao.getWtbToYfCount();
		return count>0?true:false;
	}

	@Override
	public List<FaHuoDanWei> selectListByYfwtb(Integer yfwtb) {
		// TODO Auto-generated method stub
		return faHuoDanWeiDao.selectListByYfwtb(yfwtb);
	}

	@Override
	public int updateTbZtByYfwtb(int yfwtb, int xtbzt) {
		// TODO Auto-generated method stub
		return faHuoDanWeiDao.updateTbZtByYfwtb(yfwtb, xtbzt);
	}
}
