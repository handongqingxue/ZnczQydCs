package com.znczQydCs.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class YunShuShangServiceImpl implements YunShuShangService {

	@Autowired
	private YunShuShangMapper yunShuShangDao;
	
	@Override
	public int add(YunShuShang yss) {
		// TODO Auto-generated method stub
		return yunShuShangDao.add(yss);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=yunShuShangDao.deleteByIds(idList);
		return count;
	}

	@Override
	public int edit(YunShuShang yss) {
		// TODO Auto-generated method stub
		return yunShuShangDao.edit(yss);
	}

	@Override
	public int queryForInt(String mc) {
		// TODO Auto-generated method stub
		return yunShuShangDao.queryForInt(mc);
	}

	@Override
	public List<YunShuShang> queryList(String mc, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return yunShuShangDao.queryList(mc, (page-1)*rows, rows, sort, order);
	}

	@Override
	public YunShuShang selectById(String id) {
		// TODO Auto-generated method stub
		return yunShuShangDao.selectById(id);
	}
	
	@Override
	public List<YunShuShang> queryCBBList() {
		// TODO Auto-generated method stub
		return yunShuShangDao.queryCBBList();
	}

	@Override
	public boolean checkIfWtbToYf() {
		// TODO Auto-generated method stub
		int count=yunShuShangDao.getWtbToYfCount();
		return count>0?true:false;
	}

	@Override
	public List<YunShuShang> selectListByYfwtb(Integer yfwtb) {
		// TODO Auto-generated method stub
		return yunShuShangDao.selectListByYfwtb(yfwtb);
	}

	@Override
	public int updateTbZtByYfwtb(int yfwtb, int xtbzt) {
		// TODO Auto-generated method stub
		return yunShuShangDao.updateTbZtByYfwtb(yfwtb, xtbzt);
	}

	@Override
	public int syncToQy(List<YunShuShang> yssList) {
		// TODO Auto-generated method stub
		int count=0;
		for (YunShuShang yss : yssList) {
			YunShuShang yunShuShang=yss;
			yunShuShang.setYfwjlId(yss.getId());
			yunShuShang.setMc(yss.getMc());
			yunShuShang.setBjsj(yss.getBjsj());
			yunShuShang.setYfwtb(Main.YI_TONG_BU);
			
		    if(yunShuShangDao.getCountByQyjlId(yunShuShang.getQyjlId())==0)
		    	count+=yunShuShangDao.add(yunShuShang);
		    else
		    	count+=yunShuShangDao.editByQyjlId(yunShuShang);
		}
		return count;
	}
}
