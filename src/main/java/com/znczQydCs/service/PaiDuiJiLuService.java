package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface PaiDuiJiLuService {

	int add(PaiDuiJiLu pdjl);

	int queryForInt(String ddh, String cph);

	List<PaiDuiJiLu> queryList(String ddh, String cph, int page, int rows, String sort, String order);

	PaiDuiJiLu selectById(String id);

	int syncToQy(List<PaiDuiJiLu> pdjlList);

	boolean checkIfWtbToYf();

	List<PaiDuiJiLu> selectListByYfwtb(Integer yfwtb);

	int updateTbZtByYfwtb(int yfwtb, int xtbzt);

}
