package com.znczQydCs.dao;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface DaYinJiLuMapper {

	int add(DaYinJiLu dyjl);

	DaYinJiLu selectByTime(@Param("time") String time);

}
