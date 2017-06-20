package com.peony.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.peony.service.TestService;

@Controller
@RequestMapping("test")
public class TestController {
	
	@Resource
	@Qualifier("testService")
	private TestService testService;
	
	@RequestMapping(value = "frist/{id}")
	public String frist(Model model, @PathVariable Integer id){
		Map<String, Object> a = testService.getById(id);
		model.addAttribute("test", JSONObject.toJSON(a));
		return "fristTest";
	}
	
	public static void main(String[] args) {
		System.out.println(84.0 / 12.0);
	}
}
