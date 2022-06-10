package com.znczQydCs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import com.znczQydCs.entity.BangDanJiLu;
import com.znczQydCs.entity.DingDan;
import com.znczQydCs.entity.DingDanZhuangTai;
import com.znczQydCs.entity.FaHuoDanWei;
import com.znczQydCs.entity.GuoBangJiLu;
import com.znczQydCs.entity.Main;
import com.znczQydCs.entity.PaiDuiJiLu;
import com.znczQydCs.entity.ShenHeJiLu;
import com.znczQydCs.entity.ShouHuoBuMen;
import com.znczQydCs.entity.WuZi;
import com.znczQydCs.entity.WuZiLeiXing;
import com.znczQydCs.entity.YongHu;
import com.znczQydCs.entity.YunShuShang;
import com.znczQydCs.entity.ZhiJianJiLu;

import net.sf.json.JSONArray;

public class CloudAPIUtil {

	public static final String CLOUD_SERVICE_URL="http://192.168.1.5:8080/ZnczXcx/main/";

	//https://www.cnblogs.com/aeolian/p/7746158.html
	//https://www.cnblogs.com/bobc/p/8809761.html
	public static JSONObject doHttp(String method, Map<String, Object> params) throws IOException {
		// �����������  
        StringBuffer paramsSB = new StringBuffer();
		if (params != null) {  
            for (Entry<String, Object> e : params.entrySet()) {
            	paramsSB.append(e.getKey());  
            	paramsSB.append("=");  
            	paramsSB.append(e.getValue());  
            	paramsSB.append("&");  
            }  
            paramsSB.substring(0, paramsSB.length() - 1);  
        }  
		
		StringBuffer sbf = new StringBuffer(); 
		String strRead = null; 
		URL url = new URL(CLOUD_SERVICE_URL+method);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");//����post��ʽ
		connection.setDoInput(true); 
		connection.setDoOutput(true); 
		//header�ڵĵĲ���������set    
		//connection.setRequestProperty("key", "value");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect(); 
		
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8"); 
		//OutputStream writer = connection.getOutputStream(); 
		writer.write(paramsSB.toString());
		writer.flush();
		InputStream is = connection.getInputStream(); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		while ((strRead = reader.readLine()) != null) {
			sbf.append(strRead); 
			sbf.append("\r\n"); 
		}
		reader.close();
		
		connection.disconnect();
		String result = sbf.toString();
		System.out.println("result==="+result);
		JSONObject resultJO = new JSONObject(result);
		return resultJO;
	}

	public static JSONObject selectPDJLListByQytb(String qyh, Integer qytb) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("qyh", qyh);  
	        parames.put("qytb", qytb);
	        resultJO = doHttp("selectPDJLListByQytb",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject selectZJJLListByQytb(String qyh, Integer qytb) {
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("qyh", qyh);  
	        parames.put("qytb", qytb);
	        resultJO = doHttp("selectZJJLListByQytb",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncYHToYf(String qyh, List<YongHu> yhList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("qyh", qyh);
	        parames.put("yhJAStr", JSONArray.fromObject(yhList).toString());
	        resultJO = doHttp("syncYHToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncDDZTToYf(String qyh, List<DingDanZhuangTai> ddztList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("qyh", qyh);
	        parames.put("ddztJAStr", JSONArray.fromObject(ddztList).toString());
	        resultJO = doHttp("syncDDZTToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncDDToYf(String qyh, List<DingDan> ddList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", Main.DING_DAN);
	        parames.put("qyh", qyh);
	        parames.put("entityJAStr", JSONArray.fromObject(ddList).toString());
	        resultJO = doHttp("syncToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncSHJLToYf(String qyh, List<ShenHeJiLu> shjlList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", Main.SHEN_HE_JI_LU);
	        parames.put("qyh", qyh);
	        parames.put("entityJAStr", JSONArray.fromObject(shjlList).toString());
	        resultJO = doHttp("syncToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncBDJLToYf(String qyh, List<BangDanJiLu> bdjlList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", Main.BANG_DAN_JI_LU);
	        parames.put("qyh", qyh);
	        parames.put("entityJAStr", JSONArray.fromObject(bdjlList).toString());
	        resultJO = doHttp("syncToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncGBJLToYf(String qyh, List<GuoBangJiLu> gbjlList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", Main.GUO_BANG_JI_LU);
	        parames.put("qyh", qyh);
	        parames.put("entityJAStr", JSONArray.fromObject(gbjlList).toString());
	        resultJO = doHttp("syncToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncWZLXToYf(String qyh, List<WuZiLeiXing> wzlxList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", Main.WU_ZI_LEI_XING);
	        parames.put("qyh", qyh);
	        parames.put("entityJAStr", JSONArray.fromObject(wzlxList).toString());
	        resultJO = doHttp("syncToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncWZToYf(String qyh, List<WuZi> wzList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", Main.WU_ZI);
	        parames.put("qyh", qyh);
	        parames.put("entityJAStr", JSONArray.fromObject(wzList).toString());
	        resultJO = doHttp("syncToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncYSSToYf(String qyh, List<YunShuShang> yssList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", Main.YUN_SHU_SHANG);
	        parames.put("qyh", qyh);
	        parames.put("entityJAStr", JSONArray.fromObject(yssList).toString());
	        resultJO = doHttp("syncToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncFHDWToYf(String qyh, List<FaHuoDanWei> fhdwList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", Main.FA_HUO_DAN_WEI);
	        parames.put("qyh", qyh);
	        parames.put("entityJAStr", JSONArray.fromObject(fhdwList).toString());
	        resultJO = doHttp("syncToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncSHBMToYf(String qyh, List<ShouHuoBuMen> shbmList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", Main.SHOU_HUO_BU_MEN);
	        parames.put("qyh", qyh);
	        parames.put("entityJAStr", JSONArray.fromObject(shbmList).toString());
	        resultJO = doHttp("syncToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncPDJLToYf(String qyh, List<PaiDuiJiLu> pdjlList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", Main.PAI_DUI_JI_LU);
	        parames.put("qyh", qyh);
	        parames.put("entityJAStr", JSONArray.fromObject(pdjlList).toString());
	        resultJO = doHttp("syncToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject syncZJJLToYf(String qyh, List<ZhiJianJiLu> zjjlList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", Main.ZHI_JIAN_JI_LU);
	        parames.put("qyh", qyh);
	        parames.put("entityJAStr", JSONArray.fromObject(zjjlList).toString());
	        resultJO = doHttp("syncToYf",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject checkIfWtbToQy(String tab, String qyh) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", tab);
	        parames.put("qyh", qyh);
	        resultJO = doHttp("checkIfWtbToQy",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject selectListByQytb(String tab, Integer qytb, String qyh) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", tab);
	        parames.put("qytb", qytb);
	        parames.put("qyh", qyh);
	        resultJO = doHttp("selectListByQytb",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject updateTbZtByQytb(String tab, int qytb, int xtbzt, String qyh) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("tab", tab);
	        parames.put("qytb", qytb);
	        parames.put("xtbzt", xtbzt);
	        parames.put("qyh", qyh);
	        resultJO = doHttp("updateTbZtByQytb",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
}
