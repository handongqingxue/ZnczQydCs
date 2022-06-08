package com.znczQydCs.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znczQydCs.entity.*;
import com.znczQydCs.util.*;

import com.alibaba.fastjson.*;

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
	
	@RequestMapping(value="/goSyncToYf")
	public String goSyncToYf() {
		
		return "syncToYf";
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

	@RequestMapping(value="/syncToYf")
	@ResponseBody
	public Map<String, Object> syncToYf(String tabArrStr) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String qyh = LoadProperties.getQyh();
			String[] tabArr = tabArrStr.split(",");
			for (int i = 0; i < tabArr.length; i++) {
				String tab = tabArr[i];
				if(Main.YONG_HU.equals(tab))
					syncYHToYf(qyh);
				else if(Main.DING_DAN_ZHUANG_TAI.equals(tab))
					syncDDZTToYf(qyh);
				else if(Main.WU_ZI_LEI_XING.equals(tab))
					syncWZLXToYf(qyh);
				else if(Main.WU_ZI.equals(tab))
					syncWZToYf(qyh);
				else if(Main.YUN_SHU_SHANG.equals(tab))
					syncYSSToYf(qyh);
				else if(Main.FA_HUO_DAN_WEI.equals(tab))
					syncFHDWToYf(qyh);
				else if(Main.SHOU_HUO_BU_MEN.equals(tab))
					syncSHBMToYf(qyh);
				else if(Main.DING_DAN.equals(tab))
					syncDdToYf(qyh);
				else if(Main.PAI_DUI_JI_LU.equals(tab))
					syncPDJLToYf(qyh);
				else if(Main.ZHI_JIAN_JI_LU.equals(tab))
					syncZJJLToYf(qyh);
				else if(Main.BANG_DAN_JI_LU.equals(tab))
					syncBDJLToYf(qyh);
				else if(Main.GUO_BANG_JI_LU.equals(tab))
					syncGBJLToYf(qyh);
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

	@RequestMapping(value="/syncToQy")
	@ResponseBody
	public Map<String, Object> syncToQy(String tabArrStr) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String qyh = LoadProperties.getQyh();
			String[] tabArr = tabArrStr.split(",");
			for (int i = 0; i < tabArr.length; i++) {
				String tab = tabArr[i];
				if(Main.YONG_HU.equals(tab))
					syncYHToQy(qyh);
				else if(Main.DING_DAN_ZHUANG_TAI.equals(tab))
					syncDDZTToQy(qyh);
				else if(Main.WU_ZI_LEI_XING.equals(tab))
					syncWZLXToQy(qyh);
				else if(Main.WU_ZI.equals(tab))
					syncWZToQy(qyh);
				else if(Main.YUN_SHU_SHANG.equals(tab))
					syncYSSToQy(qyh);
				else if(Main.FA_HUO_DAN_WEI.equals(tab))
					syncFHDWToQy(qyh);
				else if(Main.SHOU_HUO_BU_MEN.equals(tab))
					syncSHBMToQy(qyh);
				else if(Main.DING_DAN.equals(tab))
					syncDdToQy(qyh);
				else if(Main.PAI_DUI_JI_LU.equals(tab))
					syncPDJLToQy(qyh);
				else if(Main.ZHI_JIAN_JI_LU.equals(tab))
					syncZJJLToQy(qyh);
				else if(Main.BANG_DAN_JI_LU.equals(tab))
					syncBDJLToQy(qyh);
				else if(Main.GUO_BANG_JI_LU.equals(tab))
					syncGBJLToQy(qyh);
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

	public void syncYHToYf(String qyh) {
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

	public void syncYHToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.YONG_HU,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.YONG_HU,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.YONG_HU,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String yhListStr = resultJO.get("yhList").toString();
					System.out.println("yhListStr==="+yhListStr);
					List<YongHu> yhList=com.alibaba.fastjson.JSONObject.parseArray(yhListStr, YongHu.class);
					int syncCount=yongHuService.syncToQy(yhList);
					if(syncCount==yhList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.YONG_HU,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncDDZTToYf(String qyh) {
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

	public void syncDDZTToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.DING_DAN_ZHUANG_TAI,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.DING_DAN_ZHUANG_TAI,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.DING_DAN_ZHUANG_TAI,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String ddztListStr = resultJO.get("ddztList").toString();
					System.out.println("ddztListStr==="+ddztListStr);
					List<DingDanZhuangTai> ddztList=com.alibaba.fastjson.JSONObject.parseArray(ddztListStr, DingDanZhuangTai.class);
					int syncCount=dingDanZhuangTaiService.syncToQy(ddztList);
					if(syncCount==ddztList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.DING_DAN_ZHUANG_TAI,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncWZLXToYf(String qyh) {
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

	public void syncWZLXToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.WU_ZI_LEI_XING,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.WU_ZI_LEI_XING,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.WU_ZI_LEI_XING,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String wzlxListStr = resultJO.get("wzlxList").toString();
					System.out.println("wzlxListStr==="+wzlxListStr);
					List<WuZiLeiXing> wzlxList=com.alibaba.fastjson.JSONObject.parseArray(wzlxListStr, WuZiLeiXing.class);
					int syncCount=wuZiLeiXingService.syncToQy(wzlxList);
					if(syncCount==wzlxList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.WU_ZI_LEI_XING,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncWZToYf(String qyh) {
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

	public void syncWZToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.WU_ZI,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.WU_ZI,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.WU_ZI,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String wzListStr = resultJO.get("wzList").toString();
					System.out.println("wzListStr==="+wzListStr);
					List<WuZi> wzList=com.alibaba.fastjson.JSONObject.parseArray(wzListStr, WuZi.class);
					int syncCount=wuZiService.syncToQy(wzList);
					if(syncCount==wzList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.WU_ZI,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncYSSToYf(String qyh) {
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

	public void syncYSSToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.YUN_SHU_SHANG,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.YUN_SHU_SHANG,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.YUN_SHU_SHANG,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String yssListStr = resultJO.get("yssList").toString();
					System.out.println("yssListStr==="+yssListStr);
					List<YunShuShang> yssList=com.alibaba.fastjson.JSONObject.parseArray(yssListStr, YunShuShang.class);
					int syncCount=yunShuShangService.syncToQy(yssList);
					if(syncCount==yssList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.YUN_SHU_SHANG,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncFHDWToYf(String qyh) {
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

	public void syncFHDWToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.FA_HUO_DAN_WEI,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.FA_HUO_DAN_WEI,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.FA_HUO_DAN_WEI,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String fhdwListStr = resultJO.get("fhdwList").toString();
					System.out.println("fhdwListStr==="+fhdwListStr);
					List<FaHuoDanWei> fhdwList=com.alibaba.fastjson.JSONObject.parseArray(fhdwListStr, FaHuoDanWei.class);
					int syncCount=faHuoDanWeiService.syncToQy(fhdwList);
					if(syncCount==fhdwList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.FA_HUO_DAN_WEI,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncSHBMToYf(String qyh) {
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

	public void syncSHBMToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.SHOU_HUO_BU_MEN,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.SHOU_HUO_BU_MEN,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.SHOU_HUO_BU_MEN,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String shbmListStr = resultJO.get("shbmList").toString();
					System.out.println("shbmListStr==="+shbmListStr);
					List<ShouHuoBuMen> shbmList=com.alibaba.fastjson.JSONObject.parseArray(shbmListStr, ShouHuoBuMen.class);
					int syncCount=shouHuoBuMenService.syncToQy(shbmList);
					if(syncCount==shbmList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.SHOU_HUO_BU_MEN,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncDdToYf(String qyh) {
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
	
	public void syncDdToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.DING_DAN,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.DING_DAN,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.DING_DAN,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String ddListStr = resultJO.get("ddList").toString();
					System.out.println("ddListStr==="+ddListStr);
					List<DingDan> ddList=com.alibaba.fastjson.JSONObject.parseArray(ddListStr, DingDan.class);
					int syncCount=dingDanService.syncToQy(ddList);
					if(syncCount==ddList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.DING_DAN,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncBDJLToYf(String qyh) {
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
	
	public void syncBDJLToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.BANG_DAN_JI_LU,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.BANG_DAN_JI_LU,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.BANG_DAN_JI_LU,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String bdjlListStr = resultJO.get("bdjlList").toString();
					System.out.println("bdjlListStr==="+bdjlListStr);
					List<BangDanJiLu> bdjlList=com.alibaba.fastjson.JSONObject.parseArray(bdjlListStr, BangDanJiLu.class);
					int syncCount=bangDanJiLuService.syncToQy(bdjlList);
					if(syncCount==bdjlList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.BANG_DAN_JI_LU,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncGBJLToYf(String qyh) {
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
	
	public void syncGBJLToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.GUO_BANG_JI_LU,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.GUO_BANG_JI_LU,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.GUO_BANG_JI_LU,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String gbjlListStr = resultJO.get("gbjlList").toString();
					System.out.println("gbjlListStr==="+gbjlListStr);
					List<GuoBangJiLu> gbjlList=com.alibaba.fastjson.JSONObject.parseArray(gbjlListStr, GuoBangJiLu.class);
					int syncCount=guoBangJiLuService.syncToQy(gbjlList);
					if(syncCount==gbjlList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.GUO_BANG_JI_LU,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void syncPDJLToYf(String qyh) {
		// TODO Auto-generated method stub
		try {
			boolean bool=paiDuiJiLuService.checkIfWtbToYf();
			if(bool) {
				List<PaiDuiJiLu> pdjlList=paiDuiJiLuService.selectListByYfwtb(Main.WEI_TONG_BU);
				paiDuiJiLuService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.syncPDJLToYf(qyh,pdjlList);
				if("ok".equals(resultJO.getString("status"))) {
					paiDuiJiLuService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void syncPDJLToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.PAI_DUI_JI_LU,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.PAI_DUI_JI_LU,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.PAI_DUI_JI_LU,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String pdjlListStr = resultJO.get("pdjlList").toString();
					System.out.println("pdjlListStr==="+pdjlListStr);
					List<PaiDuiJiLu> pdjlList=com.alibaba.fastjson.JSONObject.parseArray(pdjlListStr, PaiDuiJiLu.class);
					int syncCount=paiDuiJiLuService.syncToQy(pdjlList);
					if(syncCount==pdjlList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.PAI_DUI_JI_LU,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void syncZJJLToYf(String qyh) {
		try {
			boolean bool=zhiJianJiLuService.checkIfWtbToYf();
			if(bool) {
				List<ZhiJianJiLu> zjjlList=zhiJianJiLuService.selectListByYfwtb(Main.WEI_TONG_BU);
				zhiJianJiLuService.updateTbZtByYfwtb(Main.WEI_TONG_BU,Main.TONG_BU_ZHONG);
				JSONObject resultJO = CloudAPIUtil.syncZJJLToYf(qyh,zjjlList);
				if("ok".equals(resultJO.getString("status"))) {
					zhiJianJiLuService.updateTbZtByYfwtb(Main.TONG_BU_ZHONG, Main.YI_TONG_BU);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void syncZJJLToQy(String qyh) {
		// TODO Auto-generated method stub
		try {
			JSONObject wtbResultJO=CloudAPIUtil.checkIfWtbToQy(Main.ZHI_JIAN_JI_LU,qyh);
			String wtbStatus = wtbResultJO.getString("status");
			if("ok".equals(wtbStatus)) {
				JSONObject resultJO=CloudAPIUtil.selectListByQytb(Main.ZHI_JIAN_JI_LU,Main.WEI_TONG_BU,qyh);
				String status=resultJO.getString("status");
				if("ok".equals(status)) {
					CloudAPIUtil.updateTbZtByQytb(Main.ZHI_JIAN_JI_LU,Main.WEI_TONG_BU,Main.TONG_BU_ZHONG,qyh);
					
					String zjjlListStr = resultJO.get("zjjlList").toString();
					System.out.println("zjjlListStr==="+zjjlListStr);
					List<ZhiJianJiLu> zjjlList=com.alibaba.fastjson.JSONObject.parseArray(zjjlListStr, ZhiJianJiLu.class);
					int syncCount=zhiJianJiLuService.syncToQy(zjjlList);
					if(syncCount==zjjlList.size()) {
						CloudAPIUtil.updateTbZtByQytb(Main.ZHI_JIAN_JI_LU,Main.TONG_BU_ZHONG,Main.YI_TONG_BU,qyh);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
