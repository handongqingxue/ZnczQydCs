package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface ShenHeJiLuMapper {

	int add(ShenHeJiLu shjl);

	int queryForInt(@Param("ddh") String ddh, @Param("shlx") Integer shlx, @Param("shsjks") String shsjks, @Param("shsjjs") String shsjjs, @Param("cph") String cph, @Param("shrYhm") String shrYhm, @Param("yssMc") String yssMc, @Param("wzMc") String wzMc, @Param("fhdwMc") String fhdwMc, @Param("shbmMc") String shbmMc, @Param("sjxm") String sjxm,
			@Param("sjsfzh") String sjsfzh);

	List<ShenHeJiLu> queryList(@Param("ddh") String ddh, @Param("shlx") Integer shlx, @Param("shsjks") String shsjks, @Param("shsjjs") String shsjjs, @Param("cph") String cph, @Param("shrYhm") String shrYhm, @Param("yssMc") String yssMc, @Param("wzMc") String wzMc, @Param("fhdwMc") String fhdwMc, @Param("shbmMc") String shbmMc,
			@Param("sjxm") String sjxm, @Param("sjsfzh") String sjsfzh, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	int deleteByIds(List<String> idList);

	int getWtbToYfCount();

	List<ShenHeJiLu> selectListByYfwtb(@Param("yfwtb") Integer yfwtb);

	int updateTbZtByYfwtb(@Param("yfwtb") int yfwtb, @Param("xtbzt") int xtbzt);

}
