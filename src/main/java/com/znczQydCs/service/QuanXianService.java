package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface QuanXianService {

	int add(QuanXian qx);

	int edit(QuanXian qx);

	int queryForInt(String mc);

	List<QuanXian> queryList(String mc, int page, int rows, String sort, String order);

	QuanXian selectById(String id);

	List<QuanXian> queryCBBList();

}
