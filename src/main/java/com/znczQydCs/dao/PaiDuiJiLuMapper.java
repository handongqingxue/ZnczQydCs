package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface PaiDuiJiLuMapper {

	int add(PaiDuiJiLu pdjl);

	int addFromYfw(PaiDuiJiLu paiDuiJiLu);

	int queryForInt(@Param("ddh") String ddh, @Param("cph") String cph);

	List<PaiDuiJiLu> queryList(@Param("ddh") String ddh, @Param("cph") String cph, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	PaiDuiJiLu selectById(String id);

	int getCountByQyjlId(@Param("qyjlId") Integer qyjlId);

	int editByQyjlId(PaiDuiJiLu paiDuiJiLu);

	int getWtbToYfCount();

	List<PaiDuiJiLu> selectListByYfwtb(@Param("yfwtb") Integer yfwtb);

	int updateTbZtByYfwtb(@Param("yfwtb") int yfwtb, @Param("xtbzt") int xtbzt);

}
