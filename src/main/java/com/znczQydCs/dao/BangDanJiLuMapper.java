package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface BangDanJiLuMapper {

	int add(BangDanJiLu bdjl);

	int edit(BangDanJiLu bdjl);

	int queryForInt(@Param("ddh") String ddh);
	
	List<BangDanJiLu> queryList(@Param("ddh") String ddh, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);
	
	BangDanJiLu selectById(@Param("id") String id);

	BangDanJiLu selectByDdId(@Param("ddId") Integer ddId);

	int deleteByDdId(@Param("ddId") Integer ddId);

	int getCountByDdId(@Param("ddId") Integer ddId);
}
