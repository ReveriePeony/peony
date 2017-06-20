package com.peony.test;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peony.service.SpiService;
import com.peony.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);  
	
//  private ApplicationContext ac = null;  
	
    @Resource  
    private TestService testService = null;  
  
    @Resource  
    private SpiService spiService = null;
    
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }
  
//    @Test  
//    public void test1() {  
//        Map<String, Object> a = testService.getById(32);
//        logger.info(a.toString());  
//        System.out.println(a.toString());
//    }
    
    @Test  
    public void spiTest() {
    	try {
    		String a = spiService.httpGet("https://share.dmhy.org/topics/list?keyword=%5B%E6%A1%9C%E9%83%BD%E5%AD%97%E5%B9%95%E7%BB%84%5D+%5BBang+Dream%21%5D", null);
    		System.out.println(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
}
