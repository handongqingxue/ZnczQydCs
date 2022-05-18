package com.znczQydCs.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class DingDanServiceImpl implements DingDanService {

	@Autowired
	private DingDanMapper dingDanDao;
	@Autowired
	private DingDanZhuangTaiMapper dingDanZhuangTaiDao;
	@Autowired
	private ShenHeJiLuMapper shenHeJiLuDao;
	@Autowired
	private BangDanJiLuMapper bangDanJiLuDao;
	@Autowired
	private GuoBangJiLuMapper guoBangJiLuDao;
	private SimpleDateFormat ddhSdf=new SimpleDateFormat("yyyyMMdd");

	@Override
	public int queryZHCXForInt(String ddh, Integer ddztId, String ddztMc, String cph, String yssMc, String wzMc, 
			String fhdwMc, String shbmMc, String sjxm, String sjsfzh) {
		// TODO Auto-generated method stub
		return dingDanDao.queryZHCXForInt(ddh,ddztId,ddztMc,cph,yssMc,wzMc,fhdwMc,shbmMc,sjxm,sjsfzh);
	}

	@Override
	public List<DingDan> queryZHCXList(String ddh, Integer ddztId, String ddztMc, String cph, String yssMc, String wzMc, 
			String fhdwMc, String shbmMc, String sjxm, String sjsfzh, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return dingDanDao.queryZHCXList(ddh, ddztId, ddztMc, cph, yssMc, wzMc, fhdwMc, shbmMc, sjxm, sjsfzh, (page-1)*rows, rows, sort, order);
	}

	@Override
	public DingDan selectById(String id) {
		// TODO Auto-generated method stub
		return dingDanDao.selectById(id);
	}

	@Override
	public int add(DingDan dd) {
		// TODO Auto-generated method stub
		int ddztId=dingDanZhuangTaiDao.getIdByMc(DingDanZhuangTai.DAI_SHEN_HE_TEXT);
		dd.setDdztId(ddztId);
		return dingDanDao.add(dd);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=dingDanDao.deleteByIds(idList);
		return count;
	}

	@Override
	public int edit(DingDan dd) {
		// TODO Auto-generated method stub
		String ddztMc = dd.getDdztMc();
		if(!StringUtils.isEmpty(ddztMc)) {
			int ddztId=dingDanZhuangTaiDao.getIdByMc(ddztMc);
			dd.setDdztId(ddztId);
		}
		return dingDanDao.edit(dd);
	}

	@Override
	public int editByZt(DingDan dd) {
		// TODO Auto-generated method stub
		int ddztId=dingDanZhuangTaiDao.getIdByMc(dd.getDdztMc());
		dd.setDdztId(ddztId);
		String xddztMc = dd.getXddztMc();
		if(!StringUtils.isEmpty(xddztMc)&&!"null".equals(xddztMc)) {
			int xddztId=dingDanZhuangTaiDao.getIdByMc(dd.getXddztMc());
			dd.setXddztId(xddztId);
		}
		return dingDanDao.editByZt(dd);
	}

	@Override
	public int checkByIds(String ids,String ddztMc,Integer jyFlag,ShenHeJiLu shjl) {
		// TODO Auto-generated method stub
		int count=0;
		int ddztId=dingDanZhuangTaiDao.getIdByMc(ddztMc);
		List<String> idList = Arrays.asList(ids.split(","));
		if(dingDanDao.checkByIds(idList,ddztId)>0) {
			for (String idStr : idList) {
				Integer ddId = Integer.valueOf(idStr);
				shjl.setDdId(ddId);
				count+=shenHeJiLuDao.add(shjl);
				
				if(!shjl.getShjg()) {//这块代码是在一检审核或二检审核不通过情况下，把之前的磅单记录、过磅记录删除。与下单审核、入库审核无关
					if(jyFlag==GuoBangJiLu.RU_CHANG_GUO_BANG)//在入厂过磅审核不通过情况下，要删除入厂过磅记录，肯定要连同磅单记录一起删除掉
						bangDanJiLuDao.deleteByDdId(ddId);
					guoBangJiLuDao.deleteByDdId(jyFlag,ddId);
				}
			}
		}
		return count;
	}

	@Override
	public DingDan getDingDan(String cph, String ddztMc) {
		// TODO Auto-generated method stub
		int ddztId=dingDanZhuangTaiDao.getIdByMc(ddztMc);
		DingDan dd = dingDanDao.getByZtCph(ddztId,cph);
		return dd;
	}

	@Override
	public DingDan getByZt(Integer yjbfh,Integer ejbfh,String ddztMc, Integer yjzt, Integer ejzt) {
		// TODO Auto-generated method stub
		return dingDanDao.getByZt(yjbfh,ejbfh,ddztMc, yjzt, ejzt);
	}

	@Override
	public boolean checkDdhIfExist(String ddh) {
		// TODO Auto-generated method stub
		int count=dingDanDao.getCountByDdh(ddh);
		return count==0?false:true;
	}

	@Override
	public boolean checkIfExistByZt(Integer yjbfh,Integer ejbfh,String ddztMc, Integer yjzt, Integer ejzt) {
		// TODO Auto-generated method stub
		int count=dingDanDao.getCountByZt(yjbfh,ejbfh,ddztMc, yjzt, ejzt);
		return count==0?false:true;
	}

	@Override
	public DingDan getByCphJL(String cph) {
		// TODO Auto-generated method stub
		return dingDanDao.getByCphJL(cph);
	}

	@Override
	public int getIdByDdh(String ddh) {
		// TODO Auto-generated method stub
		return dingDanDao.getIdByDdh(ddh);
	}

	@Override
	public boolean checkIfExistByIdCph(Integer id, String cph) {
		// TODO Auto-generated method stub
		return dingDanDao.getCountByIdCph(id, cph)==0?false:true;
	}

	@Override
	public String createDdhByDateYMD() {
		// TODO Auto-generated method stub
		String ddhDate = ddhSdf.format(new Date());
		int count=dingDanDao.getCountByDdhDate(ddhDate);
		String ddhxhStr=null;
		int ddhxh=count+1;
		if(ddhxh<10)
			ddhxhStr="00"+ddhxh;
		else if(ddhxh<100)
			ddhxhStr="0"+ddhxh;
		return ddhDate+ddhxhStr;
	}
}