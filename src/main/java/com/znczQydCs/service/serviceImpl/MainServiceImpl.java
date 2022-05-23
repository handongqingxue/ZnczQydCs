package com.znczQydCs.service.serviceImpl;

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

}
