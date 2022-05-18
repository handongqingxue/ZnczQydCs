package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface CheLiangTaiZhangMapper {

	int add(CheLiangTaiZhang cltz);

	int deleteByIds(List<String> idList);

	int edit(CheLiangTaiZhang cltz);

	int queryZHCXForInt(@Param("ddh") String ddh, @Param("cph") String cph, @Param("jcsjks") String jcsjks, @Param("jcsjjs") String jcsjjs);
	
	List<CheLiangTaiZhang> queryZHCXList(@Param("ddh") String ddh, @Param("cph") String cph, @Param("jcsjks") String jcsjks, @Param("jcsjjs") String jcsjjs, 
			@Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);
	
	CheLiangTaiZhang selectById(@Param("id") String id);
}
