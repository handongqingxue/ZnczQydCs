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
	private DingDanZhuangTaiService dingDanZhuangTaiService;
	@Autowired
	private WuZiLeiXingService wuZiLeiXingService;
	@Autowired
	private WuZiService wuZiService;
	@Autowired
	private YunShuShangService yunShuShangService;
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private FaHuoDanWeiService faHuoDanWeiService;
	@Autowired
	private ShouHuoBuMenService shouHuoBuMenService;
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
	public Map<String, Object> syncWithYf(String tabArrStr) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String qyh = LoadProperties.getQyh();
			String[] tabArr = tabArrStr.split(",");
			for (int i = 0; i < tabArr.length; i++) {
				String tab = tabArr[i];
				if(Main.YONG_HU.equals(tab))
					syncYH(qyh);
				else if(Main.DING_DAN_ZHUANG_TAI.equals(tab))
					syncDDZT(qyh);
				else if(Main.WU_ZI_LEI_XING.equals(tab))
					syncWZLX(qyh);
				else if(Main.WU_ZI.equals(tab))
					syncWZ(qyh);
				else if(Main.YUN_SHU_SHANG.equals(tab))
					syncYSS(qyh);
				else if(Main.FA_HUO_DAN_WEI.equals(tab))
					syncFHDW(qyh);
				else if(Main.SHOU_HUO_BU_MEN.equals(tab))
					syncSHBM(qyh);
				else if(Main.DING_DAN.equals(tab))
					syncDd(qyh);
				else if(Main.PAI_DUI_JI_LU.equals(tab))
					syncPDJL(qyh);
				else if(Main.ZHI_JIAN_JI_LU.equals(tab))
					syncZJJL(qyh);
				else if(Main.BANG_DAN_JI_LU.equals(tab))
					syncBDJL(qyh);
				else if(Main.GUO_BANG_JI_LU.equals(tab))
					syncGBJL(qyh);
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

	public void syncYH(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=yongHuService.checkIfWtbToYf();
			if(bool) {
				List<YongHu> yhList=yongHuService.selectListByYfwtb(Main.WEI_TONG_BU);
				yongHuService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.syncYHToYf(qyh,yhList);
				if("ok".equals(resultJO.getString("status"))) {
					yongHuService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncDDZT(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=dingDanZhuangTaiService.checkIfWtbToYf();
			if(bool) {
				List<DingDanZhuangTai> ddztList=dingDanZhuangTaiService.selectListByYfwtb(Main.WEI_TONG_BU);
				dingDanZhuangTaiService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.syncDDZTToYf(qyh,ddztList);
				if("ok".equals(resultJO.getString("status"))) {
					dingDanZhuangTaiService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncWZLX(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=wuZiLeiXingService.checkIfWtbToYf();
			if(bool) {
				List<WuZiLeiXing> wzlxList=wuZiLeiXingService.selectListByYfwtb(Main.WEI_TONG_BU);
				wuZiLeiXingService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.syncWZLXToYf(qyh,wzlxList);
				if("ok".equals(resultJO.getString("status"))) {
					wuZiLeiXingService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncWZ(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=wuZiService.checkIfWtbToYf();
			if(bool) {
				List<WuZi> wzList=wuZiService.selectListByYfwtb(Main.WEI_TONG_BU);
				wuZiService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.syncWZToYf(qyh,wzList);
				if("ok".equals(resultJO.getString("status"))) {
					wuZiService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncYSS(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=yunShuShangService.checkIfWtbToYf();
			if(bool) {
				List<YunShuShang> yssList=yunShuShangService.selectListByYfwtb(Main.WEI_TONG_BU);
				yunShuShangService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.syncYSSToYf(qyh,yssList);
				if("ok".equals(resultJO.getString("status"))) {
					yunShuShangService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncFHDW(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=faHuoDanWeiService.checkIfWtbToYf();
			if(bool) {
				List<FaHuoDanWei> fhdwList=faHuoDanWeiService.selectListByYfwtb(Main.WEI_TONG_BU);
				faHuoDanWeiService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.syncFHDWToYf(qyh,fhdwList);
				if("ok".equals(resultJO.getString("status"))) {
					faHuoDanWeiService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncSHBM(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=shouHuoBuMenService.checkIfWtbToYf();
			if(bool) {
				List<ShouHuoBuMen> shbmList=shouHuoBuMenService.selectListByYfwtb(Main.WEI_TONG_BU);
				shouHuoBuMenService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.syncSHBMToYf(qyh,shbmList);
				if("ok".equals(resultJO.getString("status"))) {
					shouHuoBuMenService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
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
				JSONObject resultJO = CloudAPIUtil.syncDDToYf(qyh,ddList);
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
				JSONObject resultJO = CloudAPIUtil.syncBDJLToYf(qyh,bdjlList);
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
				JSONObject resultJO = CloudAPIUtil.syncGBJLToYf(qyh,gbjlList);
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
