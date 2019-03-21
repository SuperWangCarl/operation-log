package com.hedian.platform.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Description: 获取当前运行的环境
 *
 * @param:
 * @return:
 * @auther: SuperWang
 * @date: 2018/8/15 16:33
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {


	private static ApplicationContext context = null;


	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}


	/**
	 * Description: 按照bean的名称获取
	 * @param: [beanName]
	 * @return: T
	 * @auther: SuperWang
	 * @date: 2019/3/19 17:07
	 */
	public static <T> T getBean(String beanName) {
		return (T) context.getBean(beanName);
	}
	/**
	 * Description: 按照bean的类型获取
	 * @param: [beanClass]
	 * @return: T
	 * @auther: SuperWang
	 * @date: 2019/3/19 17:07
	 */
	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}

	/**
	 * Description: 国际化使用
	 * @param: [key]
	 * @return: java.lang.String
	 * @auther: SuperWang
	 * @date: 2019/3/19 17:07
	 */
	public static String getMessage(String key) {
		return context.getMessage(key, null, Locale.getDefault());
	}


	/**
	 * Description: 获取当前的执行环境
	 * @param: []
	 * @return: java.lang.String
	 * @auther: SuperWang
	 * @date: 2019/3/19 17:07
	 */
	public static String getActiveProfile() {
		return context.getEnvironment().getActiveProfiles()[0];
	}

}