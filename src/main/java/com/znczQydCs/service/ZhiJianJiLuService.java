package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface ZhiJianJiLuService {

	int queryForInt(Integer jg, String ddh, Integer ddztId, String zjyZsxm);

	List<ZhiJianJiLu> queryList(Integer jg, String ddh, Integer ddztId, String zjyZsxm, int page, int rows, String sort,
			String order);

	int add(ZhiJianJiLu zjjl);

	ZhiJianJiLu selectById(String id);

	int syncToQy(List<ZhiJianJiLu> zjjlList);

	boolean checkIfWtbToYf();

	List<ZhiJianJiLu> selectListByYfwtb(Integer yfwtb);

	int updateTbZtByYfwtb(int yfwtb, int xtbzt);

}
