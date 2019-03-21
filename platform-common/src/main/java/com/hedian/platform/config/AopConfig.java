package com.hedian.platform.config;

import com.hedian.platform.base.BaseResult;
import com.hedian.platform.utils.DateTimeUtil;
import com.hedian.platform.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Date;

/**

* @Description:    增加Controller切点日志用于监控接口入/出参数

* @Author:         noahw

* @CreateDate:     2019-01-15 9:37

* @Version:        1.0

*/

@Slf4j
@Aspect
@Component
public class AopConfig {

    //申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.hedian.platform.controller.*.*(..))")
    private void controllerAspect(){}


    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "ret",pointcut = "controllerAspect()")
    public void methodAfterReturn(Object ret){
        log.info("Response内容:{}", JsonUtil.Object2JsonString(ret));
        formatDate(ret);
        log.info("日期格式化后:{}", JsonUtil.Object2JsonString(ret));
    }

    private void formatDate(Object ret){
        //非分页时 统一日期转换
        try{
            BaseResult result = (BaseResult) ret;
            if(result.getData() != null && result.getData().getClass() != null){
                Class<?> source = result.getData().getClass();
                BeanInfo beanInfo = Introspector.getBeanInfo(source,source.getSuperclass());
                PropertyDescriptor[] property = beanInfo.getPropertyDescriptors();
                //添加筛选条件
                Date createTime = new Date();
                for (PropertyDescriptor pro : property) {
                    String proName = pro.getName();
                    //获取date类型
                    if("createTime".equals(proName)){
                        createTime = (Date) pro.getReadMethod().invoke(result.getData());
                    }
                }
                for (PropertyDescriptor pro : property) {
                    String proName = pro.getName();
                    //将date转为String
                    if("createTimeStr".equals(proName)){
                        pro.getWriteMethod().invoke(result.getData(), DateTimeUtil.formatDateTimetoString(createTime,"yyyy-MM-dd HH:mm:ss"));
                    }
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
