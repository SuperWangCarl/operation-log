package com.hedian.platform.utils;

import java.util.UUID;

/**
 * @ClassName: CommonUtils
 * @Description: 常用工具类
 * @Auther: SuperWang
 * @Date: 2019/3/18 09:23
 * @Vsersion: 0.0.1
 */
public class CommonUtils {

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
}
