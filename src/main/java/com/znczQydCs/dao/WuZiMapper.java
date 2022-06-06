package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface WuZiMapper {


	public int add(WuZi wz);

	public int deleteByIds(List<String> idList);

	public int edit(WuZi wz);

	public int queryForInt(@Param("mc") String mc, @Param("wzlxmc") String wzlxmc);

	public List<WuZi> queryList(@Param("mc") String mc, @Param("wzlxmc") String wzlxmc, int i, int rows, String sort, String order);

	public WuZi selectById(String id);

	public List<WuZi> queryCBBList(@Param("wzlxId") String wzlxId);

	public int getCountByLxId(@Param("wzlxId") String wzlxId);

	public int getWtbToYfCount();

	public List<WuZi> selectListByYfwtb(@Param("yfwtb") Integer yfwtb);

	public int updateTbZtByYfwtb(@Param("yfwtb") int yfwtb, @Param("xtbzt") int xtbzt);

	int getCountByQyjlId(@Param("qyjlId") Integer qyjlId);

	int editByQyjlId(WuZi wuZi);
}
