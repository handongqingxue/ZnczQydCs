package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface YunShuShangMapper {

	public int add(YunShuShang yss);

	public int deleteByIds(List<String> idList);

	public int edit(YunShuShang yss);

	public int queryForInt(@Param("mc") String mc);

	public List<YunShuShang> queryList(@Param("mc") String mc, int i, int rows, String sort, String order);

	public YunShuShang selectById(String id);

	List<YunShuShang> queryCBBList();

	public int getWtbToYfCount();

	public List<YunShuShang> selectListByYfwtb(@Param("yfwtb") Integer yfwtb);

	public int updateTbZtByYfwtb(@Param("yfwtb") int yfwtb, @Param("xtbzt") int xtbzt);

	int getCountByQyjlId(@Param("qyjlId") Integer qyjlId);

	int editByQyjlId(YunShuShang yunShuShang);
}
