package cn.sdut.test.test08.util;

public class StringUtil {
	/*
	 * 判断字符串为空
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * 判断不是空串
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null || !"".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
}
