package com.znczQydCs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

public class CloudAPIUtil {

	public static final String CLOUD_SERVICE_URL="http://192.168.1.10:8080/ZnczXcx/main/";

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

	public static JSONObject updateZJJLToYtb(String qyh) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("qyh", qyh);  
	        resultJO = doHttp("updateZJJLToYtb",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
}
