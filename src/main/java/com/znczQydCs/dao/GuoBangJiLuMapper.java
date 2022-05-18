package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;

import com.znczQydCs.entity.*;

public interface GuoBangJiLuMapper {

	int add(GuoBangJiLu gbjl);

	int edit(GuoBangJiLu gbjl);

	int queryForInt(@Param("ddh") String ddh, @Param("cph") String cph, @Param("gbsjks") String gbsjks, @Param("gbsjjs") String gbsjjs);

	List<GuoBangJiLu> queryList(@Param("ddh") String ddh, @Param("cph") String cph, @Param("gbsjks") String gbsjks, @Param("gbsjjs") String gbsjjs, 
			@Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);
	
	GuoBangJiLu selectById(@Param("id") String id);

	int queryDJYForInt(@Param("ddh") String ddh, @Param("ddztMc") String ddztMc, @Param("sjxm") String sjxm, @Param("sjsfzh") String sjsfzh, @Param("cph") String cph, @Param("yssMc") String yssMc, @Param("fhdwMc") String fhdwMc, @Param("shbmMc") String shbmMc, @Param("gbsjks") String gbsjks, @Param("gbsjjs") String gbsjjs, @Param("gblx") Integer gblx);

	List<GuoBangJiLu> queryDJYList(@Param("ddh") String ddh, @Param("ddztMc") String ddztMc, @Param("sjxm") String sjxm, @Param("sjsfzh") String sjsfzh, @Param("cph") String cph, @Param("yssMc") String yssMc, @Param("fhdwMc") String fhdwMc, @Param("shbmMc") String shbmMc, @Param("gbsjks") String gbsjks, @Param("gbsjjs") String gbsjjs, @Param("gblx") Integer gblx, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort,
			String order);

	GuoBangJiLu selectPrintInfo(@Param("ddId") Integer ddId, @Param("gblx") Integer gblx);

	int deleteByDdId(@Param("gblx") Integer gblx, @Param("ddId") Integer ddId);

	int getCountByDdId(@Param("gblx") Integer gblx, @Param("ddId") Integer ddId);
}
