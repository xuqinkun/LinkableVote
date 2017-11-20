package com.xqk.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NetState {
	private Log log = LogFactory.getLog(NetState.class);
	
	public boolean isConnect() {
		boolean connect = false;
		Runtime runtime = Runtime.getRuntime();
		Process process;
		try {
			String url = "www.baidu.com";
			log.info("========== ping " + url + " ============");
			process = runtime.exec("ping " + url);
			InputStream is = process.getInputStream();
			BufferedReader br = 
					new BufferedReader(new InputStreamReader(is, "GBK"));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			log.info(sb.toString());
			if (sb != null && !sb.toString().equals("")) {
				connect = sb.toString().indexOf("TTL") > 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return connect;
	}
	
	public static void main(String[] args) {
		System.out.println(new NetState().isConnect());
	}
}
