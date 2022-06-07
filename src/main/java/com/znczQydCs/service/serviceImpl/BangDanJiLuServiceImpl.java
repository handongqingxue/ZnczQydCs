package com.znczQydCs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class BangDanJiLuServiceImpl implements BangDanJiLuService {

	@Autowired
	private BangDanJiLuMapper bangDanJiLuDao;

	@Override
	public int add(BangDanJiLu bdjl) {
		// TODO Auto-generated method stub
		return bangDanJiLuDao.add(bdjl);
	}

	@Override
	public int edit(BangDanJiLu bdjl) {
		// TODO Auto-generated method stub
		return bangDanJiLuDao.edit(bdjl);
	}

	@Override
	public int queryForInt(String ddh) {
		// TODO Auto-generated method stub
		return bangDanJiLuDao.queryForInt(ddh);
	}

	@Override
	public List<BangDanJiLu> queryList(String ddh, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return bangDanJiLuDao.queryList(ddh, (page-1)*rows, rows, sort, order);
	}

	@Override
	public BangDanJiLu selectById(String id) {
		// TODO Auto-generated method stub
		return bangDanJiLuDao.selectById(id);
	}

	@Override
	public BangDanJiLu selectByDdId(Integer ddId) {
		// TODO Auto-generated method stub
		return bangDanJiLuDao.selectByDdId(ddId);
	}

	@Override
	public boolean checkIfExistByDdId(Integer ddId) {
		// TODO Auto-generated method stub
		int count=bangDanJiLuDao.getCountByDdId(ddId);
		return count==0?false:true;
	}

	@Override
	public int deleteByDdId(Integer ddId) {
		// TODO Auto-generated method stub
		return bangDanJiLuDao.deleteByDdId(ddId);
	}

	@Override
	public boolean checkIfWtbToYf() {
		// TODO Auto-generated method stub
		int count=bangDanJiLuDao.getWtbToYfCount();
		return count>0?true:false;
	}

	@Override
	public List<BangDanJiLu> selectListByYfwtb(Integer yfwtb) {
		// TODO Auto-generated method stub
		return bangDanJiLuDao.selectListByYfwtb(yfwtb);
	}

	@Override
	public int updateTbZtByYfwtb(int yfwtb, int xtbzt) {
		// TODO Auto-generated method stub
		return bangDanJiLuDao.updateTbZtByYfwtb(yfwtb, xtbzt);
	}

	@Override
	public int syncToQy(List<BangDanJiLu> bdjlList) {
		// TODO Auto-generated method stub
		int count=0;
		for (BangDanJiLu bdjl : bdjlList) {
			BangDanJiLu bangDanJiLu=bdjl;
			bangDanJiLu.setYfwjlId(bdjl.getId());
			bangDanJiLu.setYfwtb(Main.YI_TONG_BU);
			
		    if(bangDanJiLuDao.getCountByQyjlId(bangDanJiLu.getQyjlId())==0)
		    	count+=bangDanJiLuDao.addFromYfw(bangDanJiLu);
		    else
		    	count+=bangDanJiLuDao.editByQyjlId(bangDanJiLu);
		}
		return count;
	}
}
