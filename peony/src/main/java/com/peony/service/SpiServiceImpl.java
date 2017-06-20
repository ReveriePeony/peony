package com.peony.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.peony.util.StringUtil;

@Service("spiService")
public class SpiServiceImpl implements SpiService {
	
	private static final CloseableHttpClient httpClient;
	public static final String CHARSET = "UTF-8";
	
	static {
	    RequestConfig config = RequestConfig.custom()
	    		.setConnectTimeout(60000).setSocketTimeout(15000).build();
	    httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}
	
	public String httpGet(String url, Map<String, Object> params) throws Exception{
		if(StringUtil.empty(url)) return "";
		try {
			List<NameValuePair> pairs = map2list_NameValuePair(params);
			if(pairs != null) 
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET));
			
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
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String httpPost(String url, Map<String, Object> params) throws Exception{
		if(StringUtil.empty(url)) return "";
		try {
			List<NameValuePair> pairs = map2list_NameValuePair(params);
			HttpPost httpPost = new HttpPost(url);
			if (pairs != null && pairs.size() > 0)
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
			
			CloseableHttpResponse response = httpClient.execute(httpPost);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private List<NameValuePair> map2list_NameValuePair(Map<String, Object> params){
		List<NameValuePair> pairs = null;
		if (params != null && !params.isEmpty()) {
			pairs = new ArrayList<NameValuePair>(params.size());
			for (String key : params.keySet()) {
				pairs.add(new BasicNameValuePair(key, params.get(key).toString()));
			}
		}
		return pairs;
	}
	
}
