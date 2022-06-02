package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface YongHuMapper {

	int add(YongHu yh);

	int edit(YongHu yh);

	int editByQyjlId(YongHu yongHu);
	
	//通过用户信息查询用户
	YongHu getYongHu(YongHu yh);

	int queryForInt(@Param("yhm") String yhm,@Param("check") Boolean check);
	
	List<YongHu> queryList(@Param("yhm") String yhm, @Param("check") Boolean check, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);
	
	YongHu selectById(@Param("id") String id);

	int checkByIds(@Param("check") Boolean check, @Param("idList") List<String> idList);

	int getWtbToYfCount();

	public List<YongHu> selectListByYfwtb(@Param("yfwtb") Integer yfwtb);

	int updateTbZtByYfwtb(@Param("yfwtb") int yfwtb, @Param("xtbzt") int xtbzt);

	int getCountByQyjlId(@Param("qyjlId") Integer qyjlId);
}
