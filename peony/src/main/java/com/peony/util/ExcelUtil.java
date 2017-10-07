package com.peony.util;

/**
 * Excel cell 自定义接口
 * @author Reverien9@gmail.com
 * @date 2017年10月7日
 */
public interface ExcelUtil {

	/**
	 * 自定义返回值
	 * @param value 字段值
	 * @param name 字段名
	 * @return 自定义值
	 */
	String fieldPprocessing(String value, String name);
	
}
