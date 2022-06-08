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

	@Override
	public int add(ZhiJianJiLu zjjl) {
		// TODO Auto-generated method stub
		return zhiJianJiLuDao.add(zjjl);
	}

	@Override
	public ZhiJianJiLu selectById(String id) {
		// TODO Auto-generated method stub
		return zhiJianJiLuDao.selectById(id);
	}

	@Override
	public int syncToQy(List<ZhiJianJiLu> zjjlList) {
		// TODO Auto-generated method stub
		int count=0;
		for (ZhiJianJiLu zjjl : zjjlList) {
			ZhiJianJiLu zhiJianJiLu=zjjl;
			zhiJianJiLu.setYfwjlId(zjjl.getId());
			zhiJianJiLu.setYfwtb(Main.YI_TONG_BU);
			
		    if(zhiJianJiLuDao.getCountByQyjlId(zhiJianJiLu.getQyjlId())==0)
		    	count+=zhiJianJiLuDao.addFromYfw(zhiJianJiLu);
		    else
		    	count+=zhiJianJiLuDao.editByQyjlId(zhiJianJiLu);
		}
		return count;
	}

	@Override
	public boolean checkIfWtbToYf() {
		// TODO Auto-generated method stub
		int count=zhiJianJiLuDao.getWtbToYfCount();
		return count>0?true:false;
	}

	@Override
	public List<ZhiJianJiLu> selectListByYfwtb(Integer yfwtb) {
		// TODO Auto-generated method stub
		return zhiJianJiLuDao.selectListByYfwtb(yfwtb);
	}

	@Override
	public int updateTbZtByYfwtb(int yfwtb, int xtbzt) {
		// TODO Auto-generated method stub
		return zhiJianJiLuDao.updateTbZtByYfwtb(yfwtb, xtbzt);
	}
}
