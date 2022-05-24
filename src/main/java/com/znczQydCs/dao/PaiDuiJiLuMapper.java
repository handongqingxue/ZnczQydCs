package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface PaiDuiJiLuMapper {

	int add(PaiDuiJiLu pdjl);

	int queryForInt(@Param("ddh") String ddh, @Param("cph") String cph);

	List<PaiDuiJiLu> queryList(@Param("ddh") String ddh, @Param("cph") String cph, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	PaiDuiJiLu selectById(String id);

}
