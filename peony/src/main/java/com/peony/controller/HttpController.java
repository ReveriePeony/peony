package com.peony.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peony.service.SpiService;
import com.peony.test.HttpsConnection;
import com.peony.test.HttpsGetData;
import com.peony.util.StringUtil;

@Controller
@RequestMapping("HttpClient")
public class HttpController {
	
	@Resource
	@Qualifier("spiService")
	private SpiService spiService;
	
	private static String url = "http://tieba.baidu.com/f?kw=%E6%88%98%E6%96%97%E5%B0%91%E5%A5%B3%E9%AB%98%E6%A0%A1&fr=home&fp=0&ie=utf-8";
	
	@RequestMapping(value = "home")
	public String home(Model model, HttpServletRequest request){
		String spicode = request.getParameter("spicode");
		if(!StringUtil.empty(spicode)){
			model.addAttribute("", "");
		}
		return "httpclient/sakurato_bangdream";
	}
	
	public static void main(String[] args) {
		try {

			RequestConfig config = RequestConfig.custom()
		    		.setConnectTimeout(60000).setSocketTimeout(15000).build();
			CloseableHttpClient httpClient = 
					HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		    
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			response.close();
			
//			System.out.println(result);
			
			List<String> l = syori(result, "class=\" j_thread_list clearfix\"");
			System.out.println(l.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> syori(String content, String keyw){
		List<String> l = new ArrayList<String>();
		int i = 0;
		while(content.indexOf(keyw, i) != -1){
			int a = content.indexOf(keyw, i);
			l.add(String.valueOf(a));
			i = a + 1;
			System.out.println(a);
		}
		
		return l;
	}
	
	
	
	
	
	
	
}
