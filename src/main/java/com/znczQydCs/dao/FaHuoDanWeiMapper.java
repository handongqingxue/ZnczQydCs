package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface FaHuoDanWeiMapper {

	public int add(FaHuoDanWei fhdw);

	public int deleteByIds(List<String> idList);

	public int edit(FaHuoDanWei fhdw);

	public int queryForInt(@Param("mc") String mc);

	public List<FaHuoDanWei> queryList(@Param("mc") String mc, int i, int rows, String sort, String order);

	public FaHuoDanWei selectById(String id);

	List<FaHuoDanWei> queryCBBList();

	public int getWtbToYfCount();

	public List<FaHuoDanWei> selectListByYfwtb(@Param("yfwtb") Integer yfwtb);

	public int updateTbZtByYfwtb(@Param("yfwtb") int yfwtb, @Param("xtbzt") int xtbzt);

	int getCountByQyjlId(@Param("qyjlId") Integer qyjlId);

	int editByQyjlId(FaHuoDanWei faHuoDanWei);

}
