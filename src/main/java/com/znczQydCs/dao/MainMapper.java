package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MainMapper {

	Object getQyColValByYfwColVal(@Param("qyColName") String qyColName, @Param("yfwColVal") String yfwColVal, @Param("yfwColName") String yfwColName, @Param("tabName") String tabName);

	int updateYfwtbByIdList(@Param("yfwtb") Integer yfwtb, @Param("idList") List<String> idList, @Param("tabName") String tabName);

}
