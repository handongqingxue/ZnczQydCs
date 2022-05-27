package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface WuZiService {


	public int add(WuZi wz);

	public int deleteByIds(String ids);

	public int edit(WuZi wz);

	public int queryForInt(String mc, String wzlxmc);

	public List<WuZi> queryList(String mc, String wzlxmc, int page, int rows, String sort, String order);

	public WuZi selectById(String id);

	public List<WuZi> queryCBBList(String wzlxId);

	public List<WuZiLeiXing> checkIfExistByLxIds(String lxIds,String lxMcs);

	public boolean checkIfWtbToYf();

	public List<WuZi> selectListByYfwtb(Integer yfwtb);

	int updateTbZtByYfwtb(int yfwtb, int xtbzt);
}
