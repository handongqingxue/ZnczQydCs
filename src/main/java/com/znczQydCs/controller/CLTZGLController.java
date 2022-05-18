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
import com.znczQydCs.util.*;

@Controller
@RequestMapping("/"+CLTZGLController.MODULE_NAME)
public class CLTZGLController {

	@Autowired
	private CheLiangTaiZhangService cheLiangTaiZhangService;
	public static final String MODULE_NAME="cltzgl";
	
	@RequestMapping(value="/zhcx/new")
	public String goZhcxNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/zhcx/new";
	}
	
	@RequestMapping(value="/zhcx/edit")
	public String goZhcxEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		CheLiangTaiZhang cltz=cheLiangTaiZhangService.selectById(id);
		request.setAttribute("cltz", cltz);
		
		return MODULE_NAME+"/zhcx/edit";
	}

	/**
	 * ��ת������̨�˹���-�ۺϲ�ѯ-�б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/zhcx/list")
	public String goZhcxList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/zhcx/list";
	}
	
	@RequestMapping(value="/zhcx/detail")
	public String goZhcxDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		CheLiangTaiZhang cltz=cheLiangTaiZhangService.selectById(id);
		request.setAttribute("cltz", cltz);
		
		return MODULE_NAME+"/zhcx/detail";
	}
	
	@RequestMapping(value="/newCheLiangTaiZhang")
	@ResponseBody
	public Map<String, Object> newCheLiangTaiZhang(CheLiangTaiZhang cltz) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=cheLiangTaiZhangService.add(cltz);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "��������̨�˳ɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "��������̨��ʧ�ܣ�");
		}
		return jsonMap;
	}

	@RequestMapping(value="/deleteCheLiangTaiZhang",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteCheLiangTaiZhang(String ids) {
		//TODO ��Է���Ķ�̬����ʵʱ��������
		int count=cheLiangTaiZhangService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("ɾ������̨��ʧ��");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("ɾ������̨�˳ɹ�");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/editCheLiangTaiZhang")
	@ResponseBody
	public Map<String, Object> editCheLiangTaiZhang(CheLiangTaiZhang cltz) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=cheLiangTaiZhangService.edit(cltz);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "�༭����̨�˳ɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "�༭����̨��ʧ�ܣ�");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryZHCXList")
	@ResponseBody
	public Map<String, Object> queryZHCXList(String ddh,String cph,String jcsjks,String jcsjjs,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = cheLiangTaiZhangService.queryZHCXForInt(ddh,cph,jcsjks,jcsjjs);
			List<CheLiangTaiZhang> zhglList=cheLiangTaiZhangService.queryZHCXList(ddh,cph,jcsjks,jcsjjs, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", zhglList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
}
