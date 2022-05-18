package com.znczQydCs.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.znczQydCs.service.*;
import com.znczQydCs.util.*;

import net.sf.json.JSONObject;

import com.znczQydCs.entity.*;
import com.znczQydCs.controller.DDGLController;

/**
 * 订单状态：待审核-一检排队中-一检上磅-一检待审核-待入库-二检排队中-二检上磅-二检待审核-已完成
 * */
@Controller
@RequestMapping("/"+DDGLController.MODULE_NAME)
public class DDGLController {

	//@Autowired
	//private PublicService publicService;
	@Autowired
	private DingDanService dingDanService;
	@Autowired
	private ShenHeJiLuService shenHeJiLuService;
	@Autowired
	private DingDanZhuangTaiService dingDanZhuangTaiService;
	@Autowired
	private RglrCphJiLuService rglrCphJiLuService;
	@Autowired
	private BangDanJiLuService bangDanJiLuService;
	@Autowired
	private GuoBangJiLuService guoBangJiLuService;
	public static final String MODULE_NAME="ddgl";
	
	@RequestMapping(value="/ddzt/new")
	public String goDdztNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/ddzt/new";
	}
	
	@RequestMapping(value="/ddzt/edit")
	public String goDdztEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		DingDanZhuangTai ddzt=dingDanZhuangTaiService.selectById(id);
		request.setAttribute("ddzt", ddzt);
		
		return MODULE_NAME+"/ddzt/edit";
	}

	/**
	 * 跳转到订单管理-订单状态-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ddzt/list")
	public String goDdztList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/ddzt/list";
	}
	
	@RequestMapping(value="/ddzt/detail")
	public String goDdztDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		DingDanZhuangTai ddzt=dingDanZhuangTaiService.selectById(id);
		request.setAttribute("ddzt", ddzt);
		
		return MODULE_NAME+"/ddzt/detail";
	}

	/**
	 * 跳转到订单管理-待审核-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/dsh/list")
	public String goDshList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		request.setAttribute("ddztMc", DingDanZhuangTai.DAI_SHEN_HE_TEXT);
		request.setAttribute("checkDdztMc", DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
		request.setAttribute("shlx", ShenHeJiLu.XIA_DAN_SHEN_HE);
		
		return MODULE_NAME+"/dsh/list";
	}

	/**
	 * 跳转到订单管理-待入库-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/drk/list")
	public String goDrkList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		request.setAttribute("ddztMc", DingDanZhuangTai.DAI_RU_KU_TEXT);
		request.setAttribute("checkDdztMc", DingDanZhuangTai.ER_JIAN_PAI_DUI_ZHONG_TEXT);
		request.setAttribute("shlx", ShenHeJiLu.RU_KU_SHEN_HE);
		
		return MODULE_NAME+"/drk/list";
	}
	
	@RequestMapping(value="/zhcx/new")
	public String goDdglZhcxNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String ddh=dingDanService.createDdhByDateYMD();
		request.setAttribute("ddh", ddh);
		
		return MODULE_NAME+"/zhcx/new";
	}
	
	@RequestMapping(value="/zhcx/edit")
	public String goDdglZhcxEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		DingDan dd=dingDanService.selectById(id);
		request.setAttribute("dd", dd);
		
		YongHu yongHu = (YongHu)request.getSession().getAttribute("yongHu");
		if(dd.getDdztId()==9) {
			if((yongHu.getQxIds()+",").contains("1,"))
				request.setAttribute("ddztMc", DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
		}
		
		return MODULE_NAME+"/zhcx/edit";
	}

	/**
	 * 跳转到订单管理-综合查询-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/zhcx/list")
	public String goDdglZhcxList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		request.setAttribute("dshDdztMc", DingDanZhuangTai.DAI_SHEN_HE_TEXT);
		request.setAttribute("yshDdztMc", DingDanZhuangTai.YI_SHEN_HE_TEXT);
		request.setAttribute("yjpdzDdztMc", DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
		request.setAttribute("yjsbDdztMc", DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
		request.setAttribute("ejpdzDdztMc", DingDanZhuangTai.ER_JIAN_PAI_DUI_ZHONG_TEXT);
		request.setAttribute("ejsbDdztMc", DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
		request.setAttribute("shlx", ShenHeJiLu.XIA_DAN_SHEN_HE);
		
		return MODULE_NAME+"/zhcx/list";
	}
	
	@RequestMapping(value="/zhcx/detail")
	public String goZhcxDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		DingDan dd=dingDanService.selectById(id);
		request.setAttribute("dd", dd);
		request.setAttribute("yshDdztMc", DingDanZhuangTai.YI_SHEN_HE_TEXT);
		request.setAttribute("yjpdzDdztMc", DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
		request.setAttribute("shlx", ShenHeJiLu.XIA_DAN_SHEN_HE);
		
		return MODULE_NAME+"/zhcx/detail";
	}

	/**
	 * 跳转到订单管理-审核记录-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/shjl/list")
	public String goShjlList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/shjl/list";
	}
	
	@RequestMapping(value="/newDingDanZhuangTai")
	@ResponseBody
	public Map<String, Object> newDingDanZhuangTai(DingDanZhuangTai ddzt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=dingDanZhuangTaiService.add(ddzt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建订单状态成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建订单状态失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/editDingDanZhuangTai")
	@ResponseBody
	public Map<String, Object> editDingDanZhuangTai(DingDanZhuangTai ddzt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=dingDanZhuangTaiService.edit(ddzt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑订单状态成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑订单状态失败！");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryDDZTList")
	@ResponseBody
	public Map<String, Object> queryDDZTList(String mc,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = dingDanZhuangTaiService.queryForInt(mc);
			List<DingDanZhuangTai> ddztList=dingDanZhuangTaiService.queryList(mc, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", ddztList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/newDingDanZongHeChaXun")
	@ResponseBody
	public Map<String, Object> newDingDanZongHeChaXun(DingDan dd,
			HttpServletRequest request) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			int count=dingDanService.add(dd);
			if(count>0) {
				String ddh = dd.getDdh();//因为新订单之前添加到订单表前没有id，添加完成后才生成id，这里在添加完成后，要根据订单号获取订单id
				int ddId=dingDanService.getIdByDdh(ddh);
				
				RglrCphJiLu rglrCphJiLu=new RglrCphJiLu();
				rglrCphJiLu.setCph(dd.getCph());
				rglrCphJiLu.setDdId(ddId);//获取订单id后，生成人工录入车牌号记录，与订单id关联。这里的车牌号只是车牌号记录，后面业务部人员在订单里录入的车牌号可能会根据运输商情况变更
				rglrCphJiLuService.add(rglrCphJiLu);
				
				jsonMap.put("message", "ok");
				jsonMap.put("info", "创建订单成功！");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "创建订单失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonMap;
	}

	@RequestMapping(value="/checkDingDanByIds",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String checkDingDanByIds(String ids, String ddztMc, Integer jyFlag, ShenHeJiLu shjl) {
		//TODO 针对分类的动态进行实时调整更新
		int count=dingDanService.checkByIds(ids,ddztMc,jyFlag,shjl);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("审核订单失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("审核订单成功");
			json=JsonUtil.getJsonFromObject(plan);
			
			if(!shjl.getShjg()) {//这块代码是在一检审核或二检审核不通过情况下，把订单状态还原到之前的排队中。与下单审核、入库审核无关
				List<String> idList = Arrays.asList(ids.split(","));
				for (String idStr : idList) {
					Integer ddId = Integer.valueOf(idStr);
					DingDan dd=new DingDan();
					dd.setId(ddId);
					if(shjl.getShlx()==ShenHeJiLu.YI_JIAN_SHEN_HE) {
						dd.setDdztMc(DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
						dd.setYjzt(DingDan.DAI_SHANG_BANG);
					}
					else if(shjl.getShlx()==ShenHeJiLu.ER_JIAN_SHEN_HE) {
						dd.setDdztMc(DingDanZhuangTai.ER_JIAN_PAI_DUI_ZHONG_TEXT);
						dd.setEjzt(DingDan.DAI_SHANG_BANG);
					}
					dingDanService.edit(dd);
				}
			}
		}
		return json;
	}

	@RequestMapping(value="/checkDdhIfExist",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String checkDdhIfExist(String ddh) {
		boolean exist=dingDanService.checkDdhIfExist(ddh);
		PlanResult plan=new PlanResult();
		String json;
		if(exist) {
			plan.setStatus(0);
			plan.setMsg("订单号已存在");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}

	@RequestMapping(value="/deleteDingDan",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteDingDan(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=dingDanService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除订单失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除订单成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/editDingDanZongHeChaXun")
	@ResponseBody
	public Map<String, Object> editDingDanZongHeChaXun(DingDan dd,
			HttpServletRequest request) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			Integer ddId = dd.getId();
			String cph = dd.getCph();
			boolean exist=dingDanService.checkIfExistByIdCph(ddId,cph);//在修改订单信息前根据订单id和现在的车牌号验证订单表里是否存在订单，一定要在修改订单之前验证，因为这时车牌号就算待变更也还没变成新的
			int count=dingDanService.edit(dd);
			if(count>0) {
				if(!exist) {//订单id都是一个id，若不存在，说明编辑订单信息时车牌号也变更了，则需要给该订单再加条录入车牌号记录
					RglrCphJiLu rglrCphJiLu=new RglrCphJiLu();
					rglrCphJiLu.setCph(cph);
					rglrCphJiLu.setDdId(ddId);
					rglrCphJiLuService.add(rglrCphJiLu);
				}
				
				jsonMap.put("message", "ok");
				jsonMap.put("info", "编辑订单成功！");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "编辑订单失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryZHCXList")
	@ResponseBody
	public Map<String, Object> queryZHCXList(String ddh,Integer ddztId,String ddztMc,String cph,String yssMc,String wzMc,
			String fhdwMc,String shbmMc,String sjxm,String sjsfzh,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = dingDanService.queryZHCXForInt(ddh,ddztId,ddztMc,cph,yssMc,wzMc,fhdwMc,shbmMc,sjxm,sjsfzh);
			List<DingDan> zhglList=dingDanService.queryZHCXList(ddh,ddztId,ddztMc,cph,yssMc,wzMc,fhdwMc,shbmMc,sjxm,sjsfzh, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", zhglList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}

	@RequestMapping(value="/deleteShenHeJiLu",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteShenHeJiLu(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=shenHeJiLuService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除审核记录失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除审核记录成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}

	@RequestMapping(value="/fwddById",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String fwddById(DingDan dd,int jyFlag) {

		PlanResult plan=new PlanResult();
		String json;
		boolean bool=false;
		int count=dingDanService.edit(dd);
		if(jyFlag==GuoBangJiLu.RU_CHANG_GUO_BANG) {
			bool=bangDanJiLuService.checkIfExistByDdId(dd.getId());
			if(bool)
				bangDanJiLuService.deleteByDdId(dd.getId());
		}
		bool=guoBangJiLuService.checkIfExistByDdId(jyFlag,dd.getId());
		if(bool)
			guoBangJiLuService.deleteByDdId(jyFlag,dd.getId());
			
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("订单复位失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("订单复位成功，请车辆重新排队上磅");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/querySHJLList")
	@ResponseBody
	public Map<String, Object> querySHJLList(String ddh,Integer shlx,String shsjks,String shsjjs,String cph,String shrYhm,
			String yssMc,String wzMc,String fhdwMc,String shbmMc,String sjxm,String sjsfzh,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = shenHeJiLuService.queryForInt(ddh,shlx,shsjks,shsjjs,cph,shrYhm,yssMc,wzMc,fhdwMc,shbmMc,sjxm,sjsfzh);
			List<ShenHeJiLu> shjlList=shenHeJiLuService.queryList(ddh,shlx,shsjks,shsjjs,cph,shrYhm,yssMc,wzMc,fhdwMc,shbmMc,sjxm,sjsfzh, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", shjlList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryDingDanZhuangTaiCBBList")
	@ResponseBody
	public Map<String, Object> queryDingDanZhuangTaiCBBList() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<DingDanZhuangTai> ddztList=dingDanZhuangTaiService.queryCBBList();
		
		jsonMap.put("rows", ddztList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryXzCphCBBList")
	@ResponseBody
	public Map<String, Object> queryXzCphCBBList(int page,int rows,String sort,String order) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<String> cphList=rglrCphJiLuService.queryXzCphCBBList(page, rows, sort, order);
		
		jsonMap.put("rows", cphList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryLrSjcCBBList")
	@ResponseBody
	public List<RglrCphJiLu> queryLrSjcCBBList(String q,int page,int rows,String sort,String order) {

		List<RglrCphJiLu> sjcList=rglrCphJiLuService.queryLrSjcCBBList(q, page, rows, sort, order);
		
		return sjcList;
	}
	
	@RequestMapping(value="/queryLrWscphCBBList")
	@ResponseBody
	public List<RglrCphJiLu> queryLrWscphCBBList(String sjc,String q,int page,int rows,String sort,String order) {

		List<RglrCphJiLu> cphList=rglrCphJiLuService.queryLrWscphCBBList(sjc, q, page, rows, sort, order);
		
		return cphList;
	}

	@RequestMapping(value="/getDingDanByCphJL",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String getDingDanByCphJL(String cph) {

		PlanResult plan=new PlanResult();
		String json;
		DingDan dd = dingDanService.getByCphJL(cph);
		if(dd==null) {
			plan.setStatus(0);
			plan.setMsg("没有找到相关订单");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setData(dd);
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
}
