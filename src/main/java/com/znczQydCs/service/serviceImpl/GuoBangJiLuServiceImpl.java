package com.znczQydCs.service.serviceImpl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class GuoBangJiLuServiceImpl implements GuoBangJiLuService {

	@Autowired
	private GuoBangJiLuMapper guoBangJiLuDao;

	@Override
	public int add(GuoBangJiLu gbjl) {
		// TODO Auto-generated method stub
		return guoBangJiLuDao.add(gbjl);
	}

	@Override
	public int edit(GuoBangJiLu gbjl) {
		// TODO Auto-generated method stub
		return guoBangJiLuDao.edit(gbjl);
	}

	@Override
	public int queryForInt(String ddh, String cph, String gbsjks, String gbsjjs) {
		// TODO Auto-generated method stub
		return guoBangJiLuDao.queryForInt(ddh, cph, gbsjks, gbsjjs);
	}

	@Override
	public List<GuoBangJiLu> queryList(String ddh, String cph, String gbsjks, String gbsjjs, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return guoBangJiLuDao.queryList(ddh, cph, gbsjks, gbsjjs, (page-1)*rows, rows, sort, order);
	}

	@Override
	public GuoBangJiLu selectById(String id) {
		// TODO Auto-generated method stub
		return guoBangJiLuDao.selectById(id);
	}

	@Override
	public int queryDJYForInt(String ddh, String ddztMc, String sjxm, String sjsfzh, String cph, String yssMc, String fhdwMc, String shbmMc, String gbsjks, String gbsjjs, Integer gblx) {
		// TODO Auto-generated method stub
		return guoBangJiLuDao.queryDJYForInt(ddh, ddztMc, sjxm, sjsfzh, cph, yssMc, fhdwMc, shbmMc, gbsjks, gbsjjs, gblx);
	}

	@Override
	public List<GuoBangJiLu> queryDJYList(String ddh, String ddztMc, String sjxm, String sjsfzh, String cph, String yssMc, String fhdwMc, String shbmMc, String gbsjks, String gbsjjs, Integer gblx, int page, int rows,
			String sort, String order) {
		// TODO Auto-generated method stub
		return guoBangJiLuDao.queryDJYList(ddh, ddztMc, sjxm, sjsfzh, cph, yssMc, fhdwMc, shbmMc, gbsjks, gbsjjs, gblx, (page-1)*rows, rows, sort, order);
	}

	@Override
	public GuoBangJiLu selectPrintInfo(Integer ddId, Integer gblx) {
		// TODO Auto-generated method stub
		GuoBangJiLu gbjl = guoBangJiLuDao.selectPrintInfo(ddId, gblx);
		if(gbjl.getGblx()==GuoBangJiLu.RU_CHANG_GUO_BANG)
			gbjl.setGblxName("入厂过磅");
		else
			gbjl.setGblxName("出厂过磅");
		return gbjl;
	}

	@Override
	public boolean checkIfExistByDdId(Integer gblx, Integer ddId) {
		// TODO Auto-generated method stub
		int count=guoBangJiLuDao.getCountByDdId(gblx,ddId);
		return count==0?false:true;
	}

	@Override
	public int deleteByDdId(Integer gblx,Integer ddId) {
		// TODO Auto-generated method stub
		return guoBangJiLuDao.deleteByDdId(gblx, ddId);
	}
}
