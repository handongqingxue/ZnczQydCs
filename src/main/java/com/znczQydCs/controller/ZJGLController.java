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
@RequestMapping("/"+ZJGLController.MODULE_NAME)
public class ZJGLController {

	@Autowired
	private DingDanService dingDanService;
	@Autowired
	private ZhiJianJiLuService zhiJianJiLuService;
	public static final String MODULE_NAME="zjgl";
	
	@RequestMapping(value="/dzj/list")
	public String goDzjList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		request.setAttribute("ddztMc", DingDanZhuangTai.ZHI_JIAN_PAI_DUI_ZHONG_TEXT);
		
		return MODULE_NAME+"/dzj/list";
	}
	
	@RequestMapping(value="/dzj/detail")
	public String goDzjDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		DingDan dd=dingDanService.selectById(id);
		request.setAttribute("dd", dd);
		
		return MODULE_NAME+"/dzj/detail";
	}
	
	@RequestMapping(value="/zjjl/list")
	public String goZjjlList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/zjjl/list";
	}
	
	@RequestMapping(value="/zjjl/detail")
	public String goZjjlDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		ZhiJianJiLu zjjl=zhiJianJiLuService.selectById(id);
		request.setAttribute("zjjl", zjjl);
		
		return MODULE_NAME+"/zjjl/detail";
	}
	
	@RequestMapping(value="/queryZhiJianJiLuList")
	@ResponseBody
	public Map<String, Object> queryZhiJianJiLuList(Integer jg,String ddh,Integer ddztId,String zjyZsxm,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = zhiJianJiLuService.queryForInt(jg, ddh, ddztId, zjyZsxm);
		List<ZhiJianJiLu> zjjlList=zhiJianJiLuService.queryList(jg, ddh, ddztId, zjyZsxm, page, rows, sort, order);
		
		jsonMap.put("total", count);
		jsonMap.put("rows", zjjlList);
		
		return jsonMap;
	}
}
