package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface RglrCphJiLuService {

	int add(RglrCphJiLu rglrCphJiLu);

	/**
	 * 根据订单id、车牌号验证记录是否存在(这个方法是验证车牌号记录里是否存在，与验证订单表里的那个方法不同。因为车牌记录表与订单表里的记录不一定同步，必须两张表都加个验证方法)
	 * @param ddId
	 * @param cph
	 * @return
	 */
	boolean checkIfExistByDdIdCph(Integer ddId, String cph);

	List<String> queryXzCphCBBList(int page, int rows, String sort, String order);

	List<RglrCphJiLu> queryLrSjcCBBList(String sjc, int page, int rows, String sort, String order);

	List<RglrCphJiLu> queryLrWscphCBBList(String sjc, String wscph, int page, int rows, String sort, String order);

}
