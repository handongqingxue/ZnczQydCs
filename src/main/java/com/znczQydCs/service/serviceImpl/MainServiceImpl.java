package com.znczQydCs.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.service.*;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainMapper mainDao;
	
	@Override
	public Object getQyColValByYfwColVal(String qyColName, String yfwColVal, String yfwColName, String tabName) {
		// TODO Auto-generated method stub
		return mainDao.getQyColValByYfwColVal(qyColName, yfwColVal, yfwColName, tabName);
	}

	@Override
	public int updateYfwtbByIds(Integer yfwtb, String ids, String tabName) {
		// TODO Auto-generated method stub
		List<String> idList = Arrays.asList(ids.split(","));
		return mainDao.updateYfwtbByIdList(yfwtb,idList,tabName);
	}

}
