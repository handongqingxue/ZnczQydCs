package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface WuZiLeiXingService {

	int add(WuZiLeiXing wzlx);

	int deleteByIds(String ids);

	int edit(WuZiLeiXing wzlx);

	int queryForInt(String mc);

	List<WuZiLeiXing> queryList(String mc, int page, int rows, String sort, String order);

	WuZiLeiXing selectById(String id);

	List<WuZiLeiXing> queryCBBList();

}
