package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface GuoBangJiLuService {

	int add(GuoBangJiLu gbjl);

	int edit(GuoBangJiLu gbjl);

	int queryForInt(String ddh, String cph, String gbsjks, String gbsjjs);

	List<GuoBangJiLu> queryList(String ddh, String cph, String gbsjks, String gbsjjs, int page, int rows, String sort, String order);

	GuoBangJiLu selectById(String id);

	int queryDJYForInt(String ddh, String ddztMc, String sjxm, String sjsfzh, String cph, String yssMc, String fhdwMc, String shbmMc, String gbsjks, String gbsjjs, Integer gblx);

	List<GuoBangJiLu> queryDJYList(String ddh, String ddztMc, String sjxm, String sjsfzh, String cph, String yssMc, String fhdwMc, String shbmMc, String gbsjks, String gbsjjs, Integer gblx, int page, int rows,
			String sort, String order);

	GuoBangJiLu selectPrintInfo(Integer ddId, Integer gblx);

	boolean checkIfExistByDdId(Integer gblx, Integer ddId);

	int deleteByDdId(Integer gblx,Integer ddId);

	boolean checkIfWtbToYf();

	List<GuoBangJiLu> selectListByYfwtb(Integer yfwtb);

	int updateTbZtByYfwtb(int yfwtb, int xtbzt);

	int syncToQy(List<GuoBangJiLu> gbjlList);

}
