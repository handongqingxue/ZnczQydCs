package com.znczQydCs.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class YongHuServiceImpl implements YongHuService {

	@Autowired
	private YongHuMapper yongHuDao;

	@Override
	public int add(YongHu yh) {
		// TODO Auto-generated method stub
		return yongHuDao.add(yh);
	}

	@Override
	public int edit(YongHu yh) {
		// TODO Auto-generated method stub
		return yongHuDao.edit(yh);
	}

	@Override
	public int queryForInt(String yhm,Boolean check) {
		// TODO Auto-generated method stub
		return yongHuDao.queryForInt(yhm,check);
	}

	@Override
	public List<YongHu> queryList(String yhm, Boolean check, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return yongHuDao.queryList(yhm, check, (page-1)*rows, rows, sort, order);
	}

	@Override
	public YongHu selectById(String id) {
		// TODO Auto-generated method stub
		return yongHuDao.selectById(id);
	}

	@Override
	public int checkByIds(Boolean check, String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count = yongHuDao.checkByIds(check,idList);
		return count;
	}

	@Override
	public boolean checkIfWtbToYf() {
		// TODO Auto-generated method stub
		int count=yongHuDao.getWtbToYfCount();
		return count>0?true:false;
	}

	@Override
	public List<YongHu> selectListByYfwtb(Integer yfwtb) {
		// TODO Auto-generated method stub
		return yongHuDao.selectListByYfwtb(yfwtb);
	}

	@Override
	public int updateTbZtByYfwtb(int yfwtb, int xtbzt) {
		// TODO Auto-generated method stub
		return yongHuDao.updateTbZtByYfwtb(yfwtb, xtbzt);
	}

	@Override
	public int syncYHToQy(List<YongHu> yhList) {
		// TODO Auto-generated method stub
		int count=0;
		for (YongHu yh : yhList) {
			YongHu yongHu=yh;
			yongHu.setYfwjlId(yh.getId());
			yongHu.setYhm(yh.getYhm());
			yongHu.setMm(yh.getMm());
			yongHu.setZsxm(yh.getZsxm());
			yongHu.setCjsj(yh.getCjsj());
			yongHu.setCheck(yh.getCheck());
			yongHu.setJs(yh.getJs());
			yongHu.setQxIds(yh.getQxIds());
			yongHu.setYfwtb(Main.YI_TONG_BU);
			
		    if(yongHuDao.getCountByQyjlId(yongHu.getQyjlId())==0)
		    	count+=yongHuDao.add(yongHu);
		    else
		    	count+=yongHuDao.editByQyjlId(yongHu);
		}
		return count;
	}
}
