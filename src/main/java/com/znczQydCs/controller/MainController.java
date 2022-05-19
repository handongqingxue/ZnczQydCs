package com.znczQydCs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znczQydCs.entity.*;
import com.znczQydCs.util.*;
import com.znczQydCs.service.*;

import net.sf.json.JSONArray;

import com.znczQydCs.service.*;
import com.znczQydCs.socket.*;

@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private ZhiJianJiLuService zhiJianJiLuService;
	
	static {
		StartServer ss=new StartServer();
		ss.start();
	}
	
	@RequestMapping(value="/goSyncWithYf")
	public String goSyncWithYf() {
		
		return "syncWithYf";
	}

	/**
	 * 跳转到登录页
	 * @return
	 */
	@RequestMapping(value="/goLogin")
	public String goLogin() {
		
		return "login";
	}
	
	@RequestMapping(value="/goRegist")
	public String goRegist(HttpServletRequest request) {
		
		return "regist";
	}

	@RequestMapping(value="/syncWithYf")
	@ResponseBody
	public Map<String, Object> syncWithYf() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			JSONObject resultJO = CloudAPIUtil.selectZJJLListByQytb("yuejiazhuang", ZhiJianJiLu.WEI_TONG_BU);
			if("ok".equals(resultJO.getString("status"))) {
				int count=0;
				org.json.JSONArray zjjlJA = (org.json.JSONArray)resultJO.get("zjjlList");
				for (int i = 0; i < zjjlJA.length(); i++) {
					org.json.JSONObject zjjlJO = (org.json.JSONObject)zjjlJA.get(i);
					int ddId = zjjlJO.getInt("ddId");
					int zjyId = zjjlJO.getInt("zjyId");
					String zjsj = zjjlJO.getString("zjsj");
					int jg = zjjlJO.getInt("jg");
					String bz = zjjlJO.getString("bz");
					
					ZhiJianJiLu zjjl=new ZhiJianJiLu();
					zjjl.setDdId(ddId);
					zjjl.setZjyId(zjyId);
					zjjl.setZjsj(zjsj);
					zjjl.setJg(jg);
					zjjl.setBz(bz);
					count+=zhiJianJiLuService.add(zjjl);
				}
				if(count==zjjlJA.length()) {
					JSONObject resultJO1 = CloudAPIUtil.updateZJJLToYtb("yuejiazhuang");
				}
			}
			
			jsonMap.put("status", "ok");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
		
	}
	
	/**
	 * 注册信息处理接口
	 * @param yh
	 * @return
	 */
	@RequestMapping(value = "/regist" , method = RequestMethod.POST,produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String regist(YongHu yh) {
		
		PlanResult plan=new PlanResult();
		int count=yongHuService.add(yh);
		if(count==0) {
			plan.setStatus(count);
			plan.setMsg("系统错误，请联系维护人员");
			return JsonUtil.getJsonFromObject(plan);
		}else {
			plan.setStatus(0);
			plan.setMsg("注册成功");
			plan.setData(yh);
			plan.setUrl("/main/goLogin");
		}
		
		return JsonUtil.getJsonFromObject(plan);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST,produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String login(String yhm,String mm,
			String rememberMe,HttpServletRequest request) {
		System.out.println("===登录接口===");
		//返回值的json
		PlanResult plan=new PlanResult();
		HttpSession session=request.getSession();
		
		//TODO在这附近添加登录储存信息步骤，将用户的账号以及密码储存到shiro框架的管理配置当中方便后续查询
		try {
			System.out.println("验证码一致");
			UsernamePasswordToken token = new UsernamePasswordToken(yhm,mm); 
			Subject currentUser = SecurityUtils.getSubject();  
			if (!currentUser.isAuthenticated()){
				//使用shiro来验证  
				token.setRememberMe(true);  
				currentUser.login(token);//验证角色和权限  
			}
		}catch (Exception e) {
			e.printStackTrace();
			plan.setStatus(1);
			plan.setMsg("登陆失败");
			return JsonUtil.getJsonFromObject(plan);
		}
		YongHu yongHu=(YongHu)SecurityUtils.getSubject().getPrincipal();
		session.setAttribute("yongHu", yongHu);
		
		plan.setStatus(0);
		plan.setMsg("验证通过");
		plan.setUrl("/ddgl/zhcx/list");
		return JsonUtil.getJsonFromObject(plan);
	}

	@RequestMapping(value="/exit")
	public String exit(HttpSession session) {
		System.out.println("退出接口");
		Subject currentUser = SecurityUtils.getSubject();       
	    currentUser.logout();    
		return "login";
	}
}
