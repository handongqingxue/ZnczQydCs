package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.ZhiJianJiLu;

public interface ZhiJianJiLuMapper {

	int queryForInt(@Param("zt") Integer zt, @Param("ddh") String ddh, @Param("ddztId") Integer ddztId, @Param("zjyXm") String zjyXm);

	List<ZhiJianJiLu> queryList(@Param("zt") Integer zt, @Param("ddh") String ddh, @Param("ddztId") Integer ddztId, @Param("zjyXm") String zjyXm, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort,
			String order);

}
