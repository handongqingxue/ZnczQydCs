package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface DingDanZhuangTaiMapper {

	int add(DingDanZhuangTai ddzt);

	int edit(DingDanZhuangTai ddzt);

	int queryForInt(@Param("mc") String mc);
	
	List<DingDanZhuangTai> queryList(@Param("mc") String mc, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	DingDanZhuangTai selectById(@Param("id") String id);
	
	List<DingDanZhuangTai> queryCBBList();

	int getIdByMc(@Param("mc") String mc);

	int getWtbToYfCount();

	List<DingDanZhuangTai> selectListByYfwtb(@Param("yfwtb") Integer yfwtb);

	int updateTbZtByYfwtb(@Param("yfwtb") int yfwtb, @Param("xtbzt") int xtbzt);

	int getCountByQyjlId(@Param("qyjlId") Integer qyjlId);

	int editByQyjlId(DingDanZhuangTai dingDanZhuangTai);
}
