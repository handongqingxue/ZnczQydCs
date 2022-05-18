package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface ZhiJianJiLuService {

	int queryForInt(Integer zt, String ddh, Integer ddztId, String zjyXm);

	List<ZhiJianJiLu> queryList(Integer zt, String ddh, Integer ddztId, String zjyXm, int page, int rows, String sort,
			String order);

}
