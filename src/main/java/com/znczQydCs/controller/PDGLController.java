package com.znczQydCs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;

@Controller
@RequestMapping("/"+PDGLController.MODULE_NAME)
public class PDGLController {

	@Autowired
	private PaiDuiJiLuService paiDuiJiLuService;
	public static final String MODULE_NAME="pdgl";
	
	@RequestMapping(value="/pdjl/list")
	public String goPdjlList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/pdjl/list";
	}
	
	@RequestMapping(value="/pdjl/detail")
	public String goPdjlDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		PaiDuiJiLu pdjl=paiDuiJiLuService.selectById(id);
		request.setAttribute("pdjl", pdjl);
		
		return MODULE_NAME+"/pdjl/detail";
	}
	
	@RequestMapping(value="/queryPDJLList")
	@ResponseBody
	public Map<String, Object> queryPDJLList(String ddh,String cph,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = paiDuiJiLuService.queryForInt(ddh, cph);
			List<PaiDuiJiLu> pdjlList=paiDuiJiLuService.queryList(ddh, cph, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", pdjlList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
}
