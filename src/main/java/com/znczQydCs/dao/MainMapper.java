package com.znczQydCs.dao;

import org.apache.ibatis.annotations.Param;

public interface MainMapper {

	Object getQyColValByYfwColVal(@Param("qyColName") String qyColName, @Param("yfwColVal") String yfwColVal, @Param("yfwColName") String yfwColName, @Param("tabName") String tabName);

}
