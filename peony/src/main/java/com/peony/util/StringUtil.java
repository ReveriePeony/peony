package com.peony.util;

public class StringUtil {
	
	/**
	 * null = true
	 * @param val
	 * @return
	 */
	public static boolean empty(String val){
		if(val == null){
			return true;
		}else if(val.trim().length() == 0){
			return true;
		}else{
			return false;
		}
	}
	
}
