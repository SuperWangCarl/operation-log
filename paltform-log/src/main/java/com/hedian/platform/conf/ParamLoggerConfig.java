package com.hedian.platform.conf;

import com.hedian.platform.annotation.BaseLog;
import com.hedian.platform.annotation.OperationType;
import com.hedian.platform.init.LogInit;
import com.hedian.platform.persistence.po.MetaDict;
import com.hedian.platform.persistence.po.RecordData;
import com.hedian.platform.service.RecordDataService;
import com.hedian.platform.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 增加Controller切点日志用于监控接口入/出参数  文本类日志记录
 * @Author: noahw
 * @CreateDate: 2019-01-15 9:37
 * @Version: 1.0
 */

@Slf4j
@Aspect
@Component
public class ParamLoggerConfig {

	@Autowired
	private RecordDataService recordDataService;

	/**
	 * 获取request对象
	 * @return
	 */
	public HttpServletRequest getRequest(){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return requestAttributes.getRequest();
	}
	/**
	 * 申明一个切点 里面是 execution表达式
	 */
	@Pointcut("execution(public * com.hedian.platform.controller.*.*(..))")
	private void controllerAspect() {
	}


	/**
	 * 请求method前打印内容
	 *
	 * @param joinPoint
	 */
	@Before(value = "controllerAspect()")
	public void methodBefore(JoinPoint joinPoint) {
		HttpServletRequest request = getRequest();

		String controllerMethod = joinPoint.getSignature().toString();

		//打印请求内容
		log.info("===============请求内容===============");
		log.info("请求IP:{}", request.getRemoteAddr());
		log.info("请求地址:{}", request.getRequestURL().toString());
		log.info("请求方式:{}", request.getMethod());

		//用户登陆接口,不打印Authorization信息
		if (!controllerMethod.contains("UserController.login")) {
			log.info("请求消息头Authorization:{}", request.getHeader("Authorization"));
		}

		log.info("请求类方法:{}", controllerMethod);
		log.info("请求类方法参数:{}", Arrays.toString(joinPoint.getArgs()));
		log.info("===============请求内容===============");

	}


	/**
	 * 在方法执行完结后打印返回内容
	 *
	 * @param ret
	 */
	@AfterReturning(returning = "ret", pointcut = "controllerAspect()")
	public void methodAfterReturn(JoinPoint joinPoint,Object ret) {
		log.info("===============返回内容===============");
		log.info("Response内容:{}", JsonUtil.Object2JsonString(ret));
		log.info("===============返回内容===============");

		log.info("向数据库 记录日志",recordLogToSql(getRequest(),joinPoint,ret));
	}

	/**
	 * 出异常后打印
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "controllerAspect()" ,throwing="e")
	public void myAfterThrowing(JoinPoint joinPoint,Throwable e){
		log.info("向数据库 记录日志",recordLogToSql(getRequest(),joinPoint,e));
		log.error(e.getMessage());
	}


	/**
	 * 记录数据库日志
	 */

	public String recordLogToSql(HttpServletRequest request, JoinPoint joinPoint, Object ret){
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		Method method = methodSignature.getMethod();
		String methodName = method.getDeclaringClass().getName() + "." + method.getName();
		MetaDict metaDict = LogInit.dictMap.get(methodName) == null ? new MetaDict() : LogInit.dictMap.get(methodName);
		BaseLog baseLog = method.getAnnotation(BaseLog.class);
		//符合条件记录日志
		if(baseLog != null && baseLog.isRecord()){
			RecordData recordData = new RecordData();
			//获取请求的包名

			Object currentUser = request.getAttribute("currentUser");
			if(currentUser != null){
				//获取当前操作者的信息
				try{
					Class<?> userClass = currentUser.getClass();
					BeanInfo beanInfo = Introspector.getBeanInfo(userClass, userClass.getSuperclass());
					PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
					for (PropertyDescriptor pro : propertyDescriptors) {
						String name = pro.getName();
						if("userName".equals(name)){
							String userName = (String) pro.getReadMethod().invoke(currentUser);
							recordData.setOperationName(userName);
						}
						if("userId".equals(name)){
							String userId = (String) pro.getReadMethod().invoke(currentUser);
							recordData.setOperationId(userId);
						}
					}
				}catch (Exception e){
					log.error(e.getMessage());
				}

				//获取操作信息
				recordData.setIpAddress(request.getRemoteAddr());
				//com.hedian.platform.controller.UserController.login
				//获取请求路径
				recordData.setRequestPath(metaDict.getRequestPath());
				//获取路径的前台位置
				recordData.setRequestLocation(metaDict.getRequestLocation());
				//获取请求的中文描述
				recordData.setRequestZh(metaDict.getRequestZh());
				//获取请求的包名
				recordData.setRequestPackage(metaDict.getRequestPackage());
				//获取请求的方式
				recordData.setRequestWay(metaDict.getRequestWay());
				//获取请求的英文参数
				Object[] args = joinPoint.getArgs();
				List<String> paramList = new ArrayList<>();
				for (Object arg : args) {
					String str = JsonUtil.Object2JsonString(arg);
					paramList.add(str);
				}
				String reqStr = paramList.toString();
				if(reqStr.length() > 500){
					reqStr = reqStr.substring(0,400);
				}
				recordData.setRequestParamEn(reqStr);

				//获取请求的类型
				//出异常 设置为异常记录类型
				if(ret instanceof  Throwable){
					recordData.setRequestType(OperationType.EXCEPTION.val());
				}else{
					//正常
					recordData.setRequestType(metaDict.getRequestType());
				}
				//设置响应的中文参数
				String retStr = JsonUtil.Object2JsonString(ret);
				//过长时需要截断
				if(retStr.length()>500){
					retStr = retStr.substring(0,400);
				}
				recordData.setResponseParamEn(retStr);

			}
			//记录日志
			recordDataService.insert(recordData);
		}
		return "true";
	}
}
