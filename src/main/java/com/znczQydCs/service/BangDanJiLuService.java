package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface BangDanJiLuService {

	int add(BangDanJiLu bdjl);

	int edit(BangDanJiLu bdjl);

	int queryForInt(String ddh);

	List<BangDanJiLu> queryList(String ddh, int page, int rows, String sort, String order);

	BangDanJiLu selectById(String id);

	BangDanJiLu selectByDdId(Integer ddId);

	boolean checkIfExistByDdId(Integer ddId);

	int deleteByDdId(Integer id);
}
