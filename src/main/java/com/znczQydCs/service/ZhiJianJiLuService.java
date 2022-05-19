package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface ZhiJianJiLuService {

	int queryForInt(Integer jg, String ddh, Integer ddztId, String zjyZsxm);

	List<ZhiJianJiLu> queryList(Integer jg, String ddh, Integer ddztId, String zjyZsxm, int page, int rows, String sort,
			String order);

	int add(ZhiJianJiLu zjjl);

}
