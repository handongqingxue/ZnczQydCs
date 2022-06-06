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

	@Override
	public boolean checkIfWtbToYf() {
		// TODO Auto-generated method stub
		int count=shouHuoBuMenDao.getWtbToYfCount();
		return count>0?true:false;
	}

	@Override
	public List<ShouHuoBuMen> selectListByYfwtb(Integer yfwtb) {
		// TODO Auto-generated method stub
		return shouHuoBuMenDao.selectListByYfwtb(yfwtb);
	}

	@Override
	public int updateTbZtByYfwtb(int yfwtb, int xtbzt) {
		// TODO Auto-generated method stub
		return shouHuoBuMenDao.updateTbZtByYfwtb(yfwtb, xtbzt);
	}

	@Override
	public int syncToQy(List<ShouHuoBuMen> shbmList) {
		// TODO Auto-generated method stub
		int count=0;
		for (ShouHuoBuMen shbm : shbmList) {
			ShouHuoBuMen shouHuoBuMen=shbm;
			shouHuoBuMen.setYfwjlId(shbm.getId());
			shouHuoBuMen.setMc(shbm.getMc());
			shouHuoBuMen.setBjsj(shbm.getBjsj());
			shouHuoBuMen.setYfwtb(Main.YI_TONG_BU);
			
		    if(shouHuoBuMenDao.getCountByQyjlId(shouHuoBuMen.getQyjlId())==0)
		    	count+=shouHuoBuMenDao.add(shouHuoBuMen);
		    else
		    	count+=shouHuoBuMenDao.editByQyjlId(shouHuoBuMen);
		}
		return count;
	}
}
