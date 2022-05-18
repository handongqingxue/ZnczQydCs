package com.znczQydCs.service;

import java.util.List;

import com.znczQydCs.entity.*;

public interface RglrCphJiLuService {

	int add(RglrCphJiLu rglrCphJiLu);

	/**
	 * ���ݶ���id�����ƺ���֤��¼�Ƿ����(�����������֤���ƺż�¼���Ƿ���ڣ�����֤����������Ǹ�������ͬ����Ϊ���Ƽ�¼���붩������ļ�¼��һ��ͬ�����������ű��Ӹ���֤����)
	 * @param ddId
	 * @param cph
	 * @return
	 */
	boolean checkIfExistByDdIdCph(Integer ddId, String cph);

	List<String> queryXzCphCBBList(int page, int rows, String sort, String order);

	List<RglrCphJiLu> queryLrSjcCBBList(String sjc, int page, int rows, String sort, String order);

	List<RglrCphJiLu> queryLrWscphCBBList(String sjc, String wscph, int page, int rows, String sort, String order);

}
