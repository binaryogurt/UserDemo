package com.user.util;

import java.util.UUID;

public class StringUtil {
	
	/**
	 * 生成随机id
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
