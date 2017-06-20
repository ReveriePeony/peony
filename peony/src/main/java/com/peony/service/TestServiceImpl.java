package com.peony.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.dao.TestDAO;

@Service("testService")
public class TestServiceImpl implements TestService{
	
	@Resource
	private TestDAO testDAO;

	@Override
	public Map<String, Object> getById(int id) {
		return testDAO.getById(id);
	}
	
}
