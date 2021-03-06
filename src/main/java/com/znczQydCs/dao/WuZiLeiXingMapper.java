package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface WuZiLeiXingMapper {

	int add(WuZiLeiXing wzlx);

	int deleteByIds(List<String> idList);

	int edit(WuZiLeiXing wzlx);

	int queryForInt(@Param("mc") String mc);

	List<WuZiLeiXing> queryList(@Param("mc") String mc, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	WuZiLeiXing selectById(@Param("id") String id);

	List<WuZiLeiXing> queryCBBList();

	int getWtbToYfCount();

	List<WuZiLeiXing> selectListByYfwtb(@Param("yfwtb") Integer yfwtb);

	public int updateTbZtByYfwtb(@Param("yfwtb") int yfwtb, @Param("xtbzt") int xtbzt);

	int getCountByQyjlId(@Param("qyjlId") Integer qyjlId);

	int editByQyjlId(WuZiLeiXing wuZiLeiXing);

}