package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface ShouHuoBuMenMapper {

	public int add(ShouHuoBuMen shbm);

	public int deleteByIds(List<String> idList);

	public int edit(ShouHuoBuMen shbm);

	public int queryForInt(@Param("mc") String mc);

	public List<ShouHuoBuMen> queryList(@Param("mc") String mc, int i, int rows, String sort, String order);

	public ShouHuoBuMen selectById(String id);

	List<ShouHuoBuMen> queryCBBList();

	int getWtbToYfCount();

	List<ShouHuoBuMen> selectListByYfwtb(@Param("yfwtb") Integer yfwtb);

	int updateTbZtByYfwtb(@Param("yfwtb") int yfwtb, @Param("xtbzt") int xtbzt);

	int getCountByQyjlId(@Param("qyjlId") Integer qyjlId);

	int editByQyjlId(ShouHuoBuMen shouHuoBuMen);

}
