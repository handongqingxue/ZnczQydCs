package com.znczQydCs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;
import com.znczQydCs.util.*;

@Controller
@RequestMapping("/"+XTGLController.MODULE_NAME)
public class XTGLController {

	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private QuanXianService quanXianService;
	public static final String MODULE_NAME="xtgl";

	@RequestMapping(value="/yhcx/edit")
	public String goYhcxEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		YongHu yh=yongHuService.selectById(id);
		request.setAttribute("yh", yh);
		
		return MODULE_NAME+"/yhcx/edit";
	}
	
	@RequestMapping(value="/yhcx/list")
	public String goYhcxList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/yhcx/list";
	}

	@RequestMapping(value="/yhcx/detail")
	public String goYhcxDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		YongHu yh=yongHuService.selectById(id);
		request.setAttribute("yh", yh);
		
		return MODULE_NAME+"/yhcx/detail";
	}
	
	@RequestMapping(value="/dshyh/list")
	public String goDshyhList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		request.setAttribute("check", YongHu.WEI_SHEN_HE_TEXT);
		
		return MODULE_NAME+"/dshyh/list";
	}

	@RequestMapping(value="/dshyh/detail")
	public String goDshyhDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		YongHu yh=yongHuService.selectById(id);
		request.setAttribute("yh", yh);
		
		return MODULE_NAME+"/dshyh/detail";
	}
	
	@RequestMapping(value="/qxcx/new")
	public String goQxcxNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/qxcx/new";
	}

	@RequestMapping(value="/qxcx/edit")
	public String goQxcxEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		QuanXian qx=quanXianService.selectById(id);
		request.setAttribute("qx", qx);
		
		return MODULE_NAME+"/qxcx/edit";
	}
	
	@RequestMapping(value="/qxcx/list")
	public String goQxcxList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/qxcx/list";
	}
	
	@RequestMapping(value="/editYongHu")
	@ResponseBody
	public Map<String, Object> editYongHu(YongHu yh) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=yongHuService.edit(yh);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑用户信息成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑用户信息失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryYongHuList")
	@ResponseBody
	public Map<String, Object> queryYongHuList(String yhm,Boolean check,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = yongHuService.queryForInt(yhm,check);
		List<YongHu> yhList=yongHuService.queryList(yhm, check, page, rows, sort, order);
		
		jsonMap.put("total", count);
		jsonMap.put("rows", yhList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/newQuanXian")
	@ResponseBody
	public Map<String, Object> newQuanXian(QuanXian qx) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=quanXianService.add(qx);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建权限成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建权限失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/editQuanXian")
	@ResponseBody
	public Map<String, Object> editQuanXian(QuanXian qx) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=quanXianService.edit(qx);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑权限成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑权限失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryQuanXianList")
	@ResponseBody
	public Map<String, Object> queryQuanXianList(String mc,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = quanXianService.queryForInt(mc);
		List<QuanXian> qxList=quanXianService.queryList(mc, page, rows, sort, order);
		
		jsonMap.put("total", count);
		jsonMap.put("rows", qxList);
		
		return jsonMap;
	}

	@RequestMapping(value="/checkYHByIds",method=RequestMethod.POST,produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String checkYHByIds(Boolean check, String ids) {

		PlanResult plan=new PlanResult();
		int count=yongHuService.checkByIds(check,ids);
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("审核失败");
		}
		else {
			plan.setStatus(1);
			plan.setMsg("审核成功");
		}
		return JsonUtil.getJsonFromObject(plan);
	}
	
	@RequestMapping(value="/queryQuanXianCBBList")
	@ResponseBody
	public Map<String, Object> queryQuanXianCBBList() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<QuanXian> qxList=quanXianService.queryCBBList();
		
		jsonMap.put("rows", qxList);
		
		return jsonMap;
	}
}
