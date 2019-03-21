package com.hedian.platform.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Description: 获取当前的执行环境等
 *
 * @param:
 * @return:
 * @auther: SuperWang
 * @date: 2019/3/14 10:20
 */
public class ClassUtils {

	/**
	 * 获取调用的类名
	 *
	 * @return String
	 */
	public static String getClassName() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		String className = e.getClassName();
		return className;
	}

	/**
	 * 获取调用的方法名
	 *
	 * @return String
	 */
	public static String getMethodName() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		String methodName = e.getMethodName();
		return methodName;
	}

	/**
	 * Description: 获取文件名
	 *
	 * @param: []
	 * @return: java.lang.String
	 * @auther: SuperWang
	 * @date: 2019/3/14 10:21
	 */
	public static String getFileName() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		String fileName = e.getFileName();
		return fileName;
	}

	/**
	 * Description: 获取当前行
	 *
	 * @param: []
	 * @return: int
	 * @auther: SuperWang
	 * @date: 2019/3/14 10:21
	 */
	public static int getLineNumber() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		int line = e.getLineNumber();
		return line;
	}

	public static String detailInfo() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		String className = e.getClassName();
		String methodName = e.getMethodName();
		String fileName = e.getFileName();
		int line = e.getLineNumber();
		return new StringBuilder().append(className).append(":").append(methodName).append(":").append(line).toString();
	}

	/**
	 * 获取某一个字段上面的泛型参数数组,典型的就是获取List对象里面是啥参数
	 *
	 * @param f
	 * @return
	 */
	public static Class<?>[] getParameterizedType(Field f) {
		// 获取f字段的通用类型
		Type fc = f.getGenericType(); // 关键的地方得到其Generic的类型
		// 如果不为空并且是泛型参数的类型
		if (fc != null && fc instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) fc;
			Type[] types = pt.getActualTypeArguments();
			if (types != null && types.length > 0) {
				Class<?>[] classes = new Class<?>[types.length];
				for (int i = 0; i < classes.length; i++) {
					classes[i] = (Class<?>) types[i];
				}
				return classes;
			}
		}
		return null;
	}
}