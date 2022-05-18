package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface YunShuShangService {

	public int add(YunShuShang yss);

	public int deleteByIds(String ids);

	public int edit(YunShuShang yss);

	public int queryForInt(String mc);

	public List<YunShuShang> queryList(String mc, int page, int rows, String sort, String order);

	public YunShuShang selectById(String id);

	List<YunShuShang> queryCBBList();
}