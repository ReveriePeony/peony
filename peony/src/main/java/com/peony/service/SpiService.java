package com.peony.service;

import java.util.Map;

public interface SpiService {
	public String httpGet(String url, Map<String, Object> params) throws Exception;
	public String httpPost(String url, Map<String, Object> params) throws Exception;
}
