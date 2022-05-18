package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface ShouHuoBuMenService {

	public int add(ShouHuoBuMen shbm);

	public int deleteByIds(String ids);

	public int edit(ShouHuoBuMen shbm);

	public int queryForInt(String mc);

	public List<ShouHuoBuMen> queryList(String mc, int page, int rows, String sort, String order);

	public ShouHuoBuMen selectById(String id);

	List<ShouHuoBuMen> queryCBBList();

}
