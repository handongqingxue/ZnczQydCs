package com.znczQydCs.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class PaiDuiJiLuServiceImpl implements PaiDuiJiLuService {

	@Autowired
	private PaiDuiJiLuMapper paiDuiJiLuDao;

	@Override
	public int add(PaiDuiJiLu pdjl) {
		// TODO Auto-generated method stub
		return paiDuiJiLuDao.add(pdjl);
	}
}
