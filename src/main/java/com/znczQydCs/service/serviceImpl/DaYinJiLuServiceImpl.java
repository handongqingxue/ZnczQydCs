package com.znczQydCs.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczQydCs.dao.*;
import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Service
public class DaYinJiLuServiceImpl implements DaYinJiLuService {

	@Autowired
	private DaYinJiLuMapper daYinJiLuDao;

	@Override
	public int add(DaYinJiLu dyjl) {
		// TODO Auto-generated method stub
		dyjl.setHtml(dyjl.getHtml().replaceAll("\\\"", "\\\\\""));
		return daYinJiLuDao.add(dyjl);
	}

	@Override
	public DaYinJiLu selectByTime(String time) {
		// TODO Auto-generated method stub
		return daYinJiLuDao.selectByTime(time);
	}
	
	public static void main(String[] args) {
		String str="<div id=\"aaa\"></div>";
		System.out.println("str==="+str.replaceAll("\\\"", "\\\\\""));
	}
}
