package com.znczQydCs.service;

public interface MainService {

	Object getQyColValByYfwColVal(String qyColName, String yfwColVal, String yfwColName, String tabName);

	int updateYfwtbByIds(Integer yfwtb, String ids, String tabName);

}
