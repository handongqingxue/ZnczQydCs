package com.znczQydCs.service.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class WuZiServiceImpl implements WuZiService {

	@Autowired
	private WuZiMapper wuZiDao;
	
	@Override
	public int add(WuZi wz) {
		// TODO Auto-generated method stub
		return wuZiDao.add(wz);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=wuZiDao.deleteByIds(idList);
		return count;
	}

	@Override
	public int edit(WuZi wz) {
		// TODO Auto-generated method stub
		return wuZiDao.edit(wz);
	}

	@Override
	public int queryForInt(String mc, String wzlxmc) {
		// TODO Auto-generated method stub
		return wuZiDao.queryForInt(mc,wzlxmc);
	}

	@Override
	public List<WuZi> queryList(String mc, String wzlxmc, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return wuZiDao.queryList(mc, wzlxmc, (page-1)*rows, rows, sort, order);
	}

	@Override
	public WuZi selectById(String id) {
		// TODO Auto-generated method stub
		return wuZiDao.selectById(id);
	}

	@Override
	public List<WuZi> queryCBBList(String wzlxId) {
		// TODO Auto-generated method stub
		return wuZiDao.queryCBBList(wzlxId);
	}

	@Override
	public List<WuZiLeiXing> checkIfExistByLxIds(String lxIds,String lxMcs) {
		// TODO Auto-generated method stub
		List<WuZiLeiXing> wzlxList=new ArrayList<WuZiLeiXing>();
		String[] lxIdArr = lxIds.split(",");
		String[] lxMcArr = lxMcs.split(",");
		for (int i = 0; i < lxIdArr.length; i++) {
			String lxId = lxIdArr[i];
			if(wuZiDao.getCountByLxId(lxId)>0) {
				WuZiLeiXing wzlx=new WuZiLeiXing();
				wzlx.setId(Integer.valueOf(lxId));
				wzlx.setMc(lxMcArr[i]);
				wzlxList.add(wzlx);
			}
		}
		return wzlxList;
	}
}
