package com.znczQydCs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ���������ļ���
 * 
 * @author lhb
 *
 */
public class LoadProperties {
	static Logger logger = LoggerFactory.getLogger(LoadProperties.class);
	private static Properties prop = null;

	static {
		prop = Method2();
	}

	private static synchronized Properties Method2() {
		Properties prop = null;
		try {
			InputStream inputStream = LoadProperties.class.getResourceAsStream("/db.properties");
			prop = new Properties();
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * ��ȡ��ҵ�ŵ�ַ
	 * 
	 * @return
	 */
	public static String getQyh() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("qyh").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ���������ҵ�ŵ�ַ");
			return null;
		}

		return trim;
	}
}
