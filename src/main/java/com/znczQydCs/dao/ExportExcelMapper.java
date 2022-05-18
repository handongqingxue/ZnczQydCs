package com.znczQydCs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczQydCs.entity.*;

public interface ExportExcelMapper {

	List<GuoBangJiLu> queryGBJList(@Param("ddh") String ddh, @Param("cph") String cph, @Param("gbsjks") String gbsjks, @Param("gbsjjs") String gbsjjs);
}
