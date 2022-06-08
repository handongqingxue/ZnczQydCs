package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.ZhiJianJiLu;

public interface ZhiJianJiLuMapper {

	int queryForInt(@Param("jg") Integer jg, @Param("ddh") String ddh, @Param("ddztId") Integer ddztId, @Param("zjyZsxm") String zjyZsxm);

	List<ZhiJianJiLu> queryList(@Param("jg") Integer jg, @Param("ddh") String ddh, @Param("ddztId") Integer ddztId, @Param("zjyZsxm") String zjyZsxm, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort,
			String order);

	int add(ZhiJianJiLu zjjl);

	int addFromYfw(ZhiJianJiLu zhiJianJiLu);

	int editByQyjlId(ZhiJianJiLu zhiJianJiLu);

	ZhiJianJiLu selectById(String id);

	int getCountByQyjlId(@Param("qyjlId") Integer qyjlId);

	int getWtbToYfCount();

	List<ZhiJianJiLu> selectListByYfwtb(@Param("yfwtb") Integer yfwtb);

	int updateTbZtByYfwtb(@Param("yfwtb") int yfwtb, @Param("xtbzt") int xtbzt);

}
