package com.znczQydCs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znczQydCs.entity.*;
import com.znczQydCs.service.*;
import com.znczQydCs.socket.ProxySet;
import com.znczQydCs.socket.SocketProxy;
import com.znczQydCs.util.CloudAPIUtil;
import com.znczQydCs.util.LoadProperties;

@Controller
@RequestMapping("/gkj")
public class GkjController {
	
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private DingDanService dingDanService;
	@Autowired
	private DingDanZhuangTaiService dingDanZhuangTaiService;
	@Autowired
	private BangDanJiLuService bangDanJiLuService;
	@Autowired
	private GuoBangJiLuService guoBangJiLuService; 
	@Autowired
	private RglrCphJiLuService rglrCphJiLuService; 
	@Autowired
	private WuZiLeiXingService wuZiLeiXingService;
	@Autowired
	private WuZiService wuZiService;
	@Autowired
	private YunShuShangService yunShuShangService;
	@Autowired
	private FaHuoDanWeiService faHuoDanWeiService;
	@Autowired
	private ShouHuoBuMenService shouHuoBuMenService;
	@Autowired
	private PaiDuiJiLuService paiDuiJiLuService;
	@Autowired
	private ZhiJianJiLuService zhiJianJiLuService;

	@RequestMapping(value="/getDingDan")
	@ResponseBody
	public Map<String, Object> getDingDan(String cph,String ddztMc) {
		
		System.out.println("cph==="+cph);
		System.out.println("ddztMc==="+ddztMc);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		DingDan dd = dingDanService.getDingDan(cph, ddztMc);
		
		if(dd==null) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "没找到相关订单");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("dingDan", dd);
		}
		
		return jsonMap;
	}

	@RequestMapping(value="/getDingDanByZt")
	@ResponseBody
	public Map<String, Object> getDingDanByZt(Integer yjbfh,Integer ejbfh,String ddztMc,Integer yjzt,Integer ejzt) {
		
		System.out.println("yjbfh==="+yjbfh);
		System.out.println("ejbfh==="+ejbfh);
		System.out.println("ddztMc==="+ddztMc);
		System.out.println("yjzt==="+yjzt);
		System.out.println("ejzt==="+ejzt);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		DingDan dd = dingDanService.getByZt(yjbfh,ejbfh,ddztMc,yjzt,ejzt);
		
		if(dd==null) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "没找到相关订单");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("dingDan", dd);
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/editDingDan")
	@ResponseBody
	public Map<String, Object> editDingDan(DingDan dd,
			HttpServletRequest request) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			int count=dingDanService.edit(dd);
			if(count>0) {
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
	
	@RequestMapping(value="/editDingDanByZt")
	@ResponseBody
	public Map<String, Object> editDingDanByZt(DingDan dd,
			HttpServletRequest request) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			int count=dingDanService.editByZt(dd);
			if(count>0) {
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

	@RequestMapping(value="/newBangDanJiLu")
	@ResponseBody
	public Map<String, Object> newBangDanJiLu(BangDanJiLu bdjl) {

		System.out.println("qyDdId==="+bdjl.getQyDdId());
		System.out.println("mz==="+bdjl.getMz());
		System.out.println("pz==="+bdjl.getPz());
		System.out.println("jz==="+bdjl.getJz());
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = bangDanJiLuService.add(bdjl);
		
		if(count==0) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "创建磅单信息失败！");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("message", "创建磅单信息成功！");
		}
		
		return jsonMap;
	}

	@RequestMapping(value="/editBangDanJiLu")
	@ResponseBody
	public Map<String, Object> editBangDanJiLu(BangDanJiLu bdjl) {

		System.out.println("qyDdId==="+bdjl.getQyDdId());
		System.out.println("mz==="+bdjl.getMz());
		System.out.println("pz==="+bdjl.getPz());
		System.out.println("jz==="+bdjl.getJz());
		
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

	@RequestMapping(value="/selectBangDanJiLuByDdId")
	@ResponseBody
	public Map<String, Object> selectBangDanJiLuByDdId(Integer ddId) {

		System.out.println("ddId==="+ddId);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			BangDanJiLu bdjl = bangDanJiLuService.selectByDdId(ddId);
			if(bdjl==null) {
				jsonMap.put("status", "no");
				jsonMap.put("message", "找不到相关磅单！");
			}
			else {
				jsonMap.put("status", "ok");
				jsonMap.put("bdjl", bdjl);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}

	@RequestMapping(value="/newGuoBangJiLu")
	@ResponseBody
	public Map<String, Object> newGuoBangJiLu(GuoBangJiLu gbjl) {

		Map<String, Object> jsonMap=new HashMap<String, Object>();
		try {
			int count=guoBangJiLuService.add(gbjl);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "创建过磅信息成功！");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "创建过磅信息失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}

	@RequestMapping(value="/selectGuoBangJiLuByDdId")
	@ResponseBody
	public Map<String, Object> selectGuoBangJiLuByDdId(Integer ddId, Integer gblx) {

		System.out.println("ddId==="+ddId);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		GuoBangJiLu gbjl = guoBangJiLuService.selectPrintInfo(ddId,gblx);
		if(gbjl==null) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "找不到相关过磅记录！");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("gbjl", gbjl);
		}
		return jsonMap;
	}

	@RequestMapping(value="/checkDingDanIfExistByZt")
	@ResponseBody
	public Map<String, Object> checkDingDanIfExistByZt(Integer yjbfh,Integer ejbfh,String ddztMc, Integer yjzt, Integer ejzt) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		boolean bool=dingDanService.checkIfExistByZt(yjbfh,ejbfh,ddztMc, yjzt, ejzt);
		if(bool) {
			jsonMap.put("status", "ok");
			jsonMap.put("message", "订单已存在！");
		}
		else {
			jsonMap.put("status", "no");
		}
		return jsonMap;
	}

	@RequestMapping(value="/sendCphToClient")
	@ResponseBody
	public Map<String, Object> sendCphToClient(Integer ddId,String cph,Integer bfNoFlag,Integer jyFlag) {
		
		System.out.println("sendCphToClient.bfNoFlag==="+bfNoFlag);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		//ProxySet.sayToClient("鲁A9031", SocketProxy.YI_JIAN);
		String mesJO="{\"action\":\"pushCph\",jyFlag:"+jyFlag+",\"cph\":\" "+cph+"\"}";
		ProxySet.sayToClient(mesJO, bfNoFlag==1?SocketProxy.YI_HAO_BANG_FANG:SocketProxy.ER_HAO_BANG_FANG);
		
		boolean exist=rglrCphJiLuService.checkIfExistByDdIdCph(ddId,cph);//验证同一个订单是否存在该车牌号，存在则说明之前录入过了，不需要再生成车牌号记录了，反之则需要生成
		if(!exist) {
			RglrCphJiLu rglrCphJiLu=new RglrCphJiLu();
			rglrCphJiLu.setCph(cph);
			rglrCphJiLu.setDdId(ddId);
			rglrCphJiLuService.add(rglrCphJiLu);
		}
		
		jsonMap.put("status", "ok");
		
		return jsonMap;
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
	
	public static void main(String[] args) {
		
	}
}
