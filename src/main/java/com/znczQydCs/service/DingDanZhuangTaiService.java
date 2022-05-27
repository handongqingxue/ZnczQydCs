package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface DingDanZhuangTaiService {

	int add(DingDanZhuangTai ddzt);

	int edit(DingDanZhuangTai ddzt);

	int queryForInt(String mc);

	List<DingDanZhuangTai> queryList(String mc, int page, int rows, String sort, String order);

	DingDanZhuangTai selectById(String id);

	List<DingDanZhuangTai> queryCBBList();

	boolean checkIfWtbToYf();

	List<DingDanZhuangTai> selectListByYfwtb(Integer yfwtb);

	int updateTbZtByYfwtb(int yfwtb, int xtbzt);
}
