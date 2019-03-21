package com.hedian.platform.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: BaseLog
 * @Description: 日志注解
 * @Auther: SuperWang
 * @Date: 2019/3/20 09:43
 * @Vsersion: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface BaseLog {

	/**
	 * 日志类型 通过 PUT GET等判断
	 */
	OperationType type() default OperationType.DEFAULT;

	/**
	 * 是否记录日志
	 */
	boolean isRecord() default true;

	/**
	 * 请求路径的中文描述
	 */
	String value() default  "";

	/**
	 * 该路径对应的前台位置 xx页面xx按钮
	 */
	String webLocal() default "";

}

