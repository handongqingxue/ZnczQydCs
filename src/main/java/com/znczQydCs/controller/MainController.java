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
	private MainService mainService;
	@Autowired
	private YunShuShangService yunShuShangService;
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private DingDanService dingDanService;
	@Autowired
	private PaiDuiJiLuService paiDuiJiLuService;
	@Autowired
	private ZhiJianJiLuService zhiJianJiLuService;
	@Autowired
	private BangDanJiLuService bangDanJiLuService;
	@Autowired
	private GuoBangJiLuService guoBangJiLuService;
	
	static {
		StartServer ss=new StartServer();
		ss.start();
	}
	
	@RequestMapping(value="/goSyncWithYf")
	public String goSyncWithYf() {
		
		return "syncWithYf";
	}

	/**
	 * ��ת����¼ҳ
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
			String qyh = LoadProperties.getQyh();
			syncYss(qyh);
			syncDd(qyh);
			syncPDJL(qyh);
			syncZJJL(qyh);
			syncBDJL(qyh);
			syncGBJL(qyh);
			
			jsonMap.put("status", "ok");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
		
	}

	public void syncYss(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=yunShuShangService.checkIfWtbToYf();
			if(bool) {
				List<YunShuShang> yssList=yunShuShangService.selectListByYfwtb(Main.WEI_TONG_BU);
				yunShuShangService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.addYSSToYf(qyh,yssList);
				if("ok".equals(resultJO.getString("status"))) {
					yunShuShangService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncDd(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=dingDanService.checkIfWtbToYf();
			if(bool) {
				List<DingDan> ddList=dingDanService.selectListByYfwtb(Main.WEI_TONG_BU);
				dingDanService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.addDDToYf(qyh,ddList);
				if("ok".equals(resultJO.getString("status"))) {
					dingDanService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncBDJL(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=bangDanJiLuService.checkIfWtbToYf();
			if(bool) {
				List<BangDanJiLu> bdjlList=bangDanJiLuService.selectListByYfwtb(Main.WEI_TONG_BU);
				bangDanJiLuService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.addBDJLToYf(qyh,bdjlList);
				if("ok".equals(resultJO.getString("status"))) {
					bangDanJiLuService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncGBJL(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=guoBangJiLuService.checkIfWtbToYf();
			if(bool) {
				List<GuoBangJiLu> gbjlList=guoBangJiLuService.selectListByYfwtb(Main.WEI_TONG_BU);
				guoBangJiLuService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.addGBJLToYf(qyh,gbjlList);
				if("ok".equals(resultJO.getString("status"))) {
					guoBangJiLuService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void syncPDJL(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject resultJO = CloudAPIUtil.selectPDJLListByQytb(qyh, Main.WEI_TONG_BU);
			if("ok".equals(resultJO.getString("status"))) {
				int count=0;
				org.json.JSONArray pdjlJA = (org.json.JSONArray)resultJO.get("pdjlList");
				for (int i = 0; i < pdjlJA.length(); i++) {
					org.json.JSONObject zjjlJO = (org.json.JSONObject)pdjlJA.get(i);
					int id = zjjlJO.getInt("id");
					int yfwDdId = zjjlJO.getInt("ddId");
					String pdsj = zjjlJO.getString("pdsj");
					int dlh = zjjlJO.getInt("dlh");
					int pdh = zjjlJO.getInt("pdh");
					int zt = zjjlJO.getInt("zt");
					
					Object colValObj=mainService.getQyColValByYfwColVal("id",String.valueOf(yfwDdId),"yfwjlId","ding_dan");
					
					PaiDuiJiLu pdjl=new PaiDuiJiLu();
					pdjl.setYfwjlId(id);
					pdjl.setYfwDdId(yfwDdId);
					pdjl.setQyDdId(Integer.valueOf(colValObj.toString()));
					pdjl.setPdsj(pdsj);
					pdjl.setDlh(dlh);
					pdjl.setPdh(pdh);
					pdjl.setZt(zt);
					count+=paiDuiJiLuService.add(pdjl);
				}
				if(count==pdjlJA.length()) {
					CloudAPIUtil.updatePDJLToYtb(qyh);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncZJJL(String qyh) {
		JSONObject resultJO = CloudAPIUtil.selectZJJLListByQytb(qyh, Main.WEI_TONG_BU);
		if("ok".equals(resultJO.getString("status"))) {
			int count=0;
			org.json.JSONArray zjjlJA = (org.json.JSONArray)resultJO.get("zjjlList");
			for (int i = 0; i < zjjlJA.length(); i++) {
				org.json.JSONObject zjjlJO = (org.json.JSONObject)zjjlJA.get(i);
				int id = zjjlJO.getInt("id");
				int yfwDdId = zjjlJO.getInt("ddId");
				int zjyId = zjjlJO.getInt("zjyId");
				String zjsj = zjjlJO.getString("zjsj");
				int jg = zjjlJO.getInt("jg");
				String bz = zjjlJO.getString("bz");
				
				Object colValObj=mainService.getQyColValByYfwColVal("id",String.valueOf(yfwDdId),"yfwjlId","ding_dan");
				
				ZhiJianJiLu zjjl=new ZhiJianJiLu();
				zjjl.setYfwjlId(id);
				zjjl.setYfwDdId(yfwDdId);
				zjjl.setQyDdId(Integer.valueOf(colValObj.toString()));
				zjjl.setZjyId(zjyId);
				zjjl.setZjsj(zjsj);
				zjjl.setJg(jg);
				zjjl.setBz(bz);
				count+=zhiJianJiLuService.add(zjjl);
			}
			if(count==zjjlJA.length()) {
				CloudAPIUtil.updateZJJLToYtb(qyh);
			}
		}
	}
	
	/**
	 * ע����Ϣ����ӿ�
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
			plan.setMsg("ϵͳ��������ϵά����Ա");
			return JsonUtil.getJsonFromObject(plan);
		}else {
			plan.setStatus(0);
			plan.setMsg("ע��ɹ�");
			plan.setData(yh);
			plan.setUrl("/main/goLogin");
		}
		
		return JsonUtil.getJsonFromObject(plan);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST,produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String login(String yhm,String mm,
			String rememberMe,HttpServletRequest request) {
		System.out.println("===��¼�ӿ�===");
		//����ֵ��json
		PlanResult plan=new PlanResult();
		HttpSession session=request.getSession();
		
		//TODO���⸽����ӵ�¼������Ϣ���裬���û����˺��Լ����봢�浽shiro��ܵĹ������õ��з��������ѯ
		try {
			System.out.println("��֤��һ��");
			UsernamePasswordToken token = new UsernamePasswordToken(yhm,mm); 
			Subject currentUser = SecurityUtils.getSubject();  
			if (!currentUser.isAuthenticated()){
				//ʹ��shiro����֤  
				token.setRememberMe(true);  
				currentUser.login(token);//��֤��ɫ��Ȩ��  
			}
		}catch (Exception e) {
			e.printStackTrace();
			plan.setStatus(1);
			plan.setMsg("��½ʧ��");
			return JsonUtil.getJsonFromObject(plan);
		}
		YongHu yongHu=(YongHu)SecurityUtils.getSubject().getPrincipal();
		session.setAttribute("yongHu", yongHu);
		
		plan.setStatus(0);
		plan.setMsg("��֤ͨ��");
		plan.setUrl("/ddgl/zhcx/list");
		return JsonUtil.getJsonFromObject(plan);
	}

	@RequestMapping(value="/exit")
	public String exit(HttpSession session) {
		System.out.println("�˳��ӿ�");
		Subject currentUser = SecurityUtils.getSubject();       
	    currentUser.logout();    
		return "login";
	}
}
