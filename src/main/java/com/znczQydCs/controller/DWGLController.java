package com.znczQydCs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znczQydCs.service.*;
import com.znczQydCs.util.*;
import com.znczQydCs.entity.*;

@Controller
@RequestMapping("/"+DWGLController.MODULE_NAME)
public class DWGLController {

	@Autowired
	private YunShuShangService yunShuShangService;
	@Autowired
	private FaHuoDanWeiService faHuoDanWeiService;
	@Autowired
	private ShouHuoBuMenService shouHuoBuMenService;
	public static final String MODULE_NAME="dwgl";

	@RequestMapping(value="/yss/new")
	public String goYssNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/yss/new";
	}

	@RequestMapping(value="/yss/edit")
	public String goYssEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		YunShuShang yss=yunShuShangService.selectById(id);
		request.setAttribute("yss", yss);
		
		return MODULE_NAME+"/yss/edit";
	}
	
	/**
	 * 跳转到单位管理-运输商-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/yss/list")
	public String goYssList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/yss/list";
	}

	@RequestMapping(value="/yss/detail")
	public String goYssDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		YunShuShang yss=yunShuShangService.selectById(id);
		request.setAttribute("yss", yss);
		
		return MODULE_NAME+"/yss/detail";
	}

	/**
	 * 跳转到单位管理-发货单位-添加页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fhdw/new")
	public String goFhdwNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/fhdw/new";
	}

	/**
	 * 跳转到单位管理-发货单位-编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fhdw/edit")
	public String goFhdwEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		FaHuoDanWei fhdw=faHuoDanWeiService.selectById(id);
		request.setAttribute("fhdw", fhdw);
		
		return MODULE_NAME+"/fhdw/edit";
	}
	
	/**
	 * 跳转到单位管理-发货单位-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fhdw/list")
	public String goFhdwList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/fhdw/list";
	}

	/**
	 * 跳转到单位管理-发货单位-详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fhdw/detail")
	public String goFhdwDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		FaHuoDanWei fhdw=faHuoDanWeiService.selectById(id);
		request.setAttribute("fhdw", fhdw);
		
		return MODULE_NAME+"/fhdw/detail";
	}

	/**
	 * 跳转到单位管理-收货部门-添加页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/shbm/new")
	public String goShbmNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/shbm/new";
	}

	/**
	 * 跳转到单位管理-发货单位-编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/shbm/edit")
	public String goShbmEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		ShouHuoBuMen shbm=shouHuoBuMenService.selectById(id);
		request.setAttribute("shbm", shbm);
		
		return MODULE_NAME+"/shbm/edit";
	}
	
	/**
	 * 跳转到单位管理-收货部门-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/shbm/list")
	public String goShbmList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/shbm/list";
	}

	/**
	 * 跳转到单位管理-收货部门-详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/shbm/detail")
	public String goShbmDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		ShouHuoBuMen shbm=shouHuoBuMenService.selectById(id);
		request.setAttribute("shbm", shbm);
		
		return MODULE_NAME+"/shbm/detail";
	}

	@RequestMapping(value="/newYunShuShang")
	@ResponseBody
	public Map<String, Object> newYunShuShang(YunShuShang yss) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=yunShuShangService.add(yss);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建运输商成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建运输商失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/deleteYunShuShang",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteYunShuShang(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=yunShuShangService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除运输商失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除运输商成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}

	@RequestMapping(value="/editYunShuShang")
	@ResponseBody
	public Map<String, Object> editYunShuShang(YunShuShang yss) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=yunShuShangService.edit(yss);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑运输商成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑运输商失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/queryYunShuShangList")
	@ResponseBody
	public Map<String, Object> queryYunShuShangList(String mc,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = yunShuShangService.queryForInt(mc);
		List<YunShuShang> yssList=yunShuShangService.queryList(mc, page, rows, sort, order);
		
		jsonMap.put("total", count);
		jsonMap.put("rows", yssList);
		
		return jsonMap;
	}

	@RequestMapping(value="/newFaHuoDanWei")
	@ResponseBody
	public Map<String, Object> newFaHuoDanWei(FaHuoDanWei fhdw) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=faHuoDanWeiService.add(fhdw);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建发货单位成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建发货单位失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/deleteFaHuoDanWei",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteFaHuoDanWei(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=faHuoDanWeiService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除发货单位失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除发货单位成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}

	@RequestMapping(value="/editFaHuoDanWei")
	@ResponseBody
	public Map<String, Object> editFaHuoDanWei(FaHuoDanWei fhdw) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=faHuoDanWeiService.edit(fhdw);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑发货单位成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑发货单位失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/queryFaHuoDanWeiList")
	@ResponseBody
	public Map<String, Object> queryFaHuoDanWeiList(String mc,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = faHuoDanWeiService.queryForInt(mc);
		List<FaHuoDanWei> fhdwList=faHuoDanWeiService.queryList(mc, page, rows, sort, order);
		
		jsonMap.put("total", count);
		jsonMap.put("rows", fhdwList);
		
		return jsonMap;
	}

	@RequestMapping(value="/newShouHuoBuMen")
	@ResponseBody
	public Map<String, Object> newShouHuoBuMen(ShouHuoBuMen shbm) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=shouHuoBuMenService.add(shbm);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建收货部门成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建收货部门失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/deleteShouHuoBuMen",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteShouHuoBuMen(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=shouHuoBuMenService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除收货部门失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除收货部门成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}

	@RequestMapping(value="/editShouHuoBuMen")
	@ResponseBody
	public Map<String, Object> editShouHuoBuMen(ShouHuoBuMen shbm) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=shouHuoBuMenService.edit(shbm);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑收货部门成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑收货部门失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/queryShouHuoBuMenList")
	@ResponseBody
	public Map<String, Object> queryShouHuoBuMenList(String mc,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = shouHuoBuMenService.queryForInt(mc);
		List<ShouHuoBuMen> shbmList=shouHuoBuMenService.queryList(mc, page, rows, sort, order);
		
		jsonMap.put("total", count);
		jsonMap.put("rows", shbmList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryYunShuShangCBBList")
	@ResponseBody
	public Map<String, Object> queryYunShuShangCBBList() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<YunShuShang> yssList=yunShuShangService.queryCBBList();
		
		jsonMap.put("rows", yssList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryFaHuoDanWeiCBBList")
	@ResponseBody
	public Map<String, Object> queryFaHuoDanWeiCBBList() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<FaHuoDanWei> fhdwList=faHuoDanWeiService.queryCBBList();
		
		jsonMap.put("rows", fhdwList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryShouHuoBuMenCBBList")
	@ResponseBody
	public Map<String, Object> queryShouHuoBuMenCBBList() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<ShouHuoBuMen> shbmList=shouHuoBuMenService.queryCBBList();
		
		jsonMap.put("rows", shbmList);
		
		return jsonMap;
	}
}
