package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface DingDanMapper {

	int queryZHCXForInt(@Param("ddh") String ddh, @Param("ddztId") Integer ddztId, @Param("ddztMc") String ddztMc, @Param("cph") String cph, @Param("yssMc") String yssMc, @Param("wzMc") String wzMc, 
			@Param("fhdwMc") String fhdwMc, @Param("shbmMc") String shbmMc, @Param("sjxm") String sjxm, @Param("sjsfzh") String sjsfzh);

	List<DingDan> queryZHCXList(@Param("ddh") String ddh, @Param("ddztId") Integer ddztId, @Param("ddztMc") String ddztMc, @Param("cph") String cph, @Param("yssMc") String yssMc, @Param("wzMc") String wzMc, 
			@Param("fhdwMc") String fhdwMc, @Param("shbmMc") String shbmMc, @Param("sjxm") String sjxm, @Param("sjsfzh") String sjsfzh, 
			@Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	DingDan selectById(@Param("id") String id);

	int add(DingDan dd);

	int deleteByIds(List<String> idList);

	int edit(DingDan dd);

	int editByQyjlId(DingDan dingDan);

	int editByZt(DingDan dd);

	DingDan getByZtCph(@Param("ddztId") int ddztId, @Param("cph") String cph);

	DingDan getByZt(@Param("yjbfh")Integer yjbfh,@Param("ejbfh") Integer ejbfh, @Param("ddztMc") String ddztMc, @Param("yjzt") Integer yjzt, @Param("ejzt") Integer ejzt);

	int checkByIds(@Param("idList") List<String> idList, @Param("qyDdztId") int ddztId);

	int getCountByDdh(@Param("ddh") String ddh);

	int getCountByZt(@Param("yjbfh") Integer yjbfh,@Param("ejbfh") Integer ejbfh,@Param("ddztMc") String ddztMc, @Param("yjzt") Integer yjzt, @Param("ejzt") Integer ejzt);

	DingDan getByCphJL(@Param("cph") String cph);

	int getIdByDdh(@Param("ddh") String ddh);

	int getCountByIdCph(@Param("id") Integer id, @Param("cph") String cph);

	int getCountByDdhDate(@Param("ddhDate") String ddhDate);

	int getWtbToYfCount();

	List<DingDan> selectListByYfwtb(@Param("yfwtb") Integer yfwtb);

	int updateTbZtByYfwtb(@Param("yfwtb") int yfwtb, @Param("xtbzt") int xtbzt);

	int getCountByQyjlId(@Param("qyjlId") Integer qyjlId);

}