package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface CheLiangTaiZhangService {

	int add(CheLiangTaiZhang cltz);

	int deleteByIds(String ids);

	int edit(CheLiangTaiZhang cltz);

	int queryZHCXForInt(String ddh, String cph, String jcsjks, String jcsjjs);

	List<CheLiangTaiZhang> queryZHCXList(String ddh, String cph, String jcsjks, String jcsjjs, int page, int rows, String sort, String order);

	CheLiangTaiZhang selectById(String id);
}
