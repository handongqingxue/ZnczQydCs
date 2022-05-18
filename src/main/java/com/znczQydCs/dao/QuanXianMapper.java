package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface QuanXianMapper {

	int add(QuanXian qx);

	int edit(QuanXian qx);

	int queryForInt(@Param("mc") String mc);

	List<QuanXian> queryList(@Param("mc") String mc, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	QuanXian selectById(@Param("id") String id);

	List<QuanXian> queryCBBList();

}
