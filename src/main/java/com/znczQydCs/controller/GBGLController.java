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
@RequestMapping("/"+GBGLController.MODULE_NAME)
public class GBGLController {

	@Autowired
	private GuoBangJiLuService guoBangJiLuService;
	@Autowired
	private BangDanJiLuService bangDanJiLuService;
	@Autowired
	private DingDanService dingDanService;
	@Autowired
	private DaYinJiLuService daYinJiLuService;
	public static final String MODULE_NAME="gbgl";
	
	@RequestMapping(value="/bdjl/new")
	public String goBdjlNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/bdjl/new";
	}

	/**
	 * 跳转到过磅管理-磅单记录-编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/bdjl/edit")
	public String goBdjlEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		BangDanJiLu bdjl=bangDanJiLuService.selectById(id);
		request.setAttribute("bdjl", bdjl);
		
		DingDan dd=dingDanService.selectById(bdjl.getDdId().toString());
		request.setAttribute("dd", dd);
		
		return MODULE_NAME+"/bdjl/edit";
	}
	
	/**
	 * 跳转到过磅管理-磅单记录-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/bdjl/list")
	public String goBdjlList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/bdjl/list";
	}

	/**
	 * 跳转到过磅管理-磅单记录-详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/bdjl/detail")
	public String goBdjlDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		BangDanJiLu bdjl=bangDanJiLuService.selectById(id);
		request.setAttribute("bdjl", bdjl);
		
		DingDan dd=dingDanService.selectById(bdjl.getDdId().toString());
		request.setAttribute("dd", dd);
		
		return MODULE_NAME+"/bdjl/detail";
	}

	@RequestMapping(value="/bdjl/print")
	public String goBdjlPreview(HttpServletRequest request) {

		String time = request.getParameter("time");
		DaYinJiLu dyjl=daYinJiLuService.selectByTime(time);
		request.setAttribute("dyjl", dyjl);
		
		return MODULE_NAME+"/bdjl/print";
	}

	@RequestMapping(value="/newDaYinJiLu")
	@ResponseBody
	public Map<String, Object> newDaYinJiLu(DaYinJiLu dyjl) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=daYinJiLuService.add(dyjl);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建打印记录成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建打印记录失败！");
		}
			
		return jsonMap;
	}
	
	@RequestMapping(value="/gbjl/new")
	public String goGbjlNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/gbjl/new";
	}

	/**
	 * 跳转到过磅管理-过磅记录-编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/gbjl/edit")
	public String goGbjlEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		GuoBangJiLu gbjl=guoBangJiLuService.selectById(id);
		request.setAttribute("gbjl", gbjl);
		
		return MODULE_NAME+"/gbjl/edit";
	}
	
	/**
	 * 跳转到过磅管理-过磅记录-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/gbjl/list")
	public String goGbjlList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/gbjl/list";
	}

	/**
	 * 跳转到过磅管理-过磅记录-详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/gbjl/detail")
	public String goGbjlDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		GuoBangJiLu gbjl=guoBangJiLuService.selectById(id);
		request.setAttribute("gbjl", gbjl);
		
		return MODULE_NAME+"/gbjl/detail";
	}
	
	/**
	 * 跳转到过磅管理-一检待审核-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/yjdsh/list")
	public String goYjdshList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		request.setAttribute("yjdshDdztMc", DingDanZhuangTai.YI_JIAN_DAI_SHEN_HE_TEXT);
		request.setAttribute("drkDdztMc", DingDanZhuangTai.DAI_RU_KU_TEXT);
		request.setAttribute("shlx", ShenHeJiLu.YI_JIAN_SHEN_HE);
		request.setAttribute("gblx", GuoBangJiLu.RU_CHANG_GUO_BANG);
		
		return MODULE_NAME+"/yjdsh/list";
	}
	
	/**
	 * 跳转到过磅管理-二检待审核-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ejdsh/list")
	public String goEjdshList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		request.setAttribute("ejdshDdztMc", DingDanZhuangTai.ER_JIAN_DAI_SHEN_HE_TEXT);
		request.setAttribute("ywcDdztMc", DingDanZhuangTai.YI_WAN_CHENG_TEXT);
		request.setAttribute("shlx", ShenHeJiLu.ER_JIAN_SHEN_HE);
		request.setAttribute("gblx", GuoBangJiLu.CHU_CHANG_GUO_BANG);
		
		return MODULE_NAME+"/ejdsh/list";
	}

	@RequestMapping(value="/newBangDanJiLu")
	@ResponseBody
	public Map<String, Object> newBangDanJiLu(BangDanJiLu bdjl) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=bangDanJiLuService.add(bdjl);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建磅单信息成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建磅单信息失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/editBangDanJiLu")
	@ResponseBody
	public Map<String, Object> editBangDanJiLu(BangDanJiLu bdjl) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=bangDanJiLuService.edit(bdjl);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑磅单信息成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑磅单信息失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/queryBDJLList")
	@ResponseBody
	public Map<String, Object> queryBDJLList(String ddh,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = bangDanJiLuService.queryForInt(ddh);
		List<BangDanJiLu> bdjlList=bangDanJiLuService.queryList(ddh, page, rows, sort, order);
		
		jsonMap.put("total", count);
		jsonMap.put("rows", bdjlList);
		
		return jsonMap;
	}

	@RequestMapping(value="/newGuoBangJiLu")
	@ResponseBody
	public Map<String, Object> newGuoBangJiLu(GuoBangJiLu gbjl) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=guoBangJiLuService.add(gbjl);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建过磅信息成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建过磅信息失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/editGuoBangJiLu")
	@ResponseBody
	public Map<String, Object> editGuoBangJiLu(GuoBangJiLu gbjl) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=guoBangJiLuService.edit(gbjl);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑过磅信息成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑过磅信息失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/queryGBJLList")
	@ResponseBody
	public Map<String, Object> queryGBJLList(String ddh,String cph,String gbsjks,String gbsjjs,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = guoBangJiLuService.queryForInt(ddh,cph,gbsjks,gbsjjs);
		List<GuoBangJiLu> gbjlList=guoBangJiLuService.queryList(ddh, cph, gbsjks, gbsjjs, page, rows, sort, order);
		
		jsonMap.put("total", count);
		jsonMap.put("rows", gbjlList);
		
		return jsonMap;
	}

	@RequestMapping(value="/queryDJYList")
	@ResponseBody
	public Map<String, Object> queryDJYList(String ddh,String ddztMc,String sjxm,String sjsfzh,String cph,String yssMc,String fhdwMc,
			String shbmMc,String gbsjks,String gbsjjs,Integer gblx,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = guoBangJiLuService.queryDJYForInt(ddh,ddztMc,sjxm,sjsfzh,cph,yssMc,fhdwMc,shbmMc,gbsjks,gbsjjs,gblx);
		List<GuoBangJiLu> jyjlList=guoBangJiLuService.queryDJYList(ddh, ddztMc, sjxm, sjsfzh, cph, yssMc, fhdwMc, shbmMc, gbsjks, gbsjjs, gblx, page, rows, sort, order);
		
		jsonMap.put("total", count);
		jsonMap.put("rows", jyjlList);
		
		return jsonMap;
	}
}
