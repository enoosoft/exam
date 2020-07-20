package com.enoo.quiz;

public class Util {
	
	/**
	 * 비었거나 널인지 확인
	 */
	public static boolean isEmpty(String str) {
		if (null == str) {
			return true;
		}
		if (str.equals("")) {
			return true;
		}
		return false;
	}

	

}
