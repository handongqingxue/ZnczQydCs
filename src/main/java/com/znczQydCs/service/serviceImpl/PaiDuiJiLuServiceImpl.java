package com.znczQydCs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class PaiDuiJiLuServiceImpl implements PaiDuiJiLuService {

	@Autowired
	private PaiDuiJiLuMapper paiDuiJiLuDao;
	@Autowired
	private MainMapper mainDao;

	@Override
	public int add(PaiDuiJiLu pdjl) {
		// TODO Auto-generated method stub
		try {
			return paiDuiJiLuDao.add(pdjl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int queryForInt(String ddh, String cph) {
		// TODO Auto-generated method stub
		return paiDuiJiLuDao.queryForInt(ddh, cph);
	}

	@Override
	public List<PaiDuiJiLu> queryList(String ddh, String cph, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return paiDuiJiLuDao.queryList(ddh, cph, (page-1)*rows, rows, sort, order);
	}

	@Override
	public PaiDuiJiLu selectById(String id) {
		// TODO Auto-generated method stub
		return paiDuiJiLuDao.selectById(id);
	}

	@Override
	public int syncToQy(List<PaiDuiJiLu> pdjlList) {
		// TODO Auto-generated method stub
		int count=0;
		for (PaiDuiJiLu pdjl : pdjlList) {
			PaiDuiJiLu paiDuiJiLu=pdjl;
			paiDuiJiLu.setYfwjlId(pdjl.getId());
			Object qyDdIdObj = mainDao.getQyColValByYfwColVal("id", pdjl.getYfwDdId()+"", "yfwjlId", "ding_dan");
			if(qyDdIdObj!=null) {
				Integer qyDdId=Integer.valueOf(qyDdIdObj.toString());
				paiDuiJiLu.setQyDdId(qyDdId);
			}
			paiDuiJiLu.setYfwtb(Main.YI_TONG_BU);
			
		    if(paiDuiJiLuDao.getCountByQyjlId(paiDuiJiLu.getQyjlId())==0)
		    	count+=paiDuiJiLuDao.addFromYfw(paiDuiJiLu);
		    else
		    	count+=paiDuiJiLuDao.editByQyjlId(paiDuiJiLu);
		}
		return count;
	}

	@Override
	public boolean checkIfWtbToYf() {
		// TODO Auto-generated method stub
		int count=paiDuiJiLuDao.getWtbToYfCount();
		return count>0?true:false;
	}

	@Override
	public List<PaiDuiJiLu> selectListByYfwtb(Integer yfwtb) {
		// TODO Auto-generated method stub
		return paiDuiJiLuDao.selectListByYfwtb(yfwtb);
	}

	@Override
	public int updateTbZtByYfwtb(int yfwtb, int xtbzt) {
		// TODO Auto-generated method stub
		return paiDuiJiLuDao.updateTbZtByYfwtb(yfwtb, xtbzt);
	}
}
