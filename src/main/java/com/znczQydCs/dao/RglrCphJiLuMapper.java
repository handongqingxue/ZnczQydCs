package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface RglrCphJiLuMapper {

	int add(RglrCphJiLu rglrCphJiLu);

	int getCount(@Param("ddId") Integer ddId, @Param("cph") String cph);

	List<String> queryXzCphCBBList(@Param("rowNum") int rowNum, @Param("rows") int rows, @Param("sort") String sort, @Param("order") String order);

	List<RglrCphJiLu> queryLrSjcCBBList(@Param("sjc") String sjc, @Param("rowNum") int rowNum, @Param("rows") int rows, @Param("sort") String sort, @Param("order") String order);

	List<RglrCphJiLu> queryLrWscphCBBList(@Param("sjc") String sjc, @Param("wscph") String wscph, @Param("rowNum") int rowNum, @Param("rows") int rows, @Param("sort") String sort, @Param("order") String order);
}
