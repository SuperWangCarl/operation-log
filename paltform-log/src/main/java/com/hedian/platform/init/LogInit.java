package com.hedian.platform.init;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.platform.annotation.BaseLog;
import com.hedian.platform.annotation.OperationType;
import com.hedian.platform.persistence.po.MetaDict;
import com.hedian.platform.service.MetaDictService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: LogInit
 * @Description: 日志初始化
 * @Auther: SuperWang
 * @Date: 2019/3/21 09:59
 * @Vsersion: 0.0.1
 */
@Component
public class LogInit {

	public static final Map<String, MetaDict> dictMap = new HashMap<>();
	/**
	 * 获取所有controller
	 */
	@Autowired
	private RequestMappingHandlerMapping rmhm;

	/**
	 * 取出点service 用于和 注解进行对比
	 */
	@Autowired
	private MetaDictService metaDictService;

	@PostConstruct
	public void init() {
		initLogDict();
	}

	/**
	 * 刚启动初始化 日志字典表查看有无更新
	 */
	public void initLogDict() {
		List<MetaDict> dicts = metaDictService.selectList(new EntityWrapper<>());

		for (MetaDict dict : dicts) {
			dictMap.put(dict.getRequestPackage(), dict);
		}

		//1:获取controller中所有带有@RequestMapper标签的方法
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {

			//获取请求方式和请求路径
			RequestMappingInfo pathType = entry.getKey(); //{GET /dept/query/{id}}
			HandlerMethod method = entry.getValue();

			//获取方法的注解
			BaseLog baseLog = method.getMethodAnnotation(BaseLog.class);
			//如果存在api注解 折可以省略 baselog的描述
			ApiOperation apiOperation = method.getMethodAnnotation(ApiOperation.class);
			if (baseLog != null) {
				//获取controller
				Class<?> controller = method.getBeanType();
				BaseLog baseLogController = controller.getAnnotation(BaseLog.class);

				//封装 字典数据
				MetaDict dict = new MetaDict();

				//封装请求路径
				String pathTypeStr = pathType.toString();
				String[] s = pathTypeStr.split(" ");
				dict.setRequestPath(s[1].substring(0, s[1].length() - 1));
				//请求方式
				String requestWay = s[0].substring(1);
				dict.setRequestWay(requestWay);

				//判断baseLog.value()当前的请求中文描述是否为空如果为空并且 apiOperation存在值折使用apiOperation中的请求描述
				if("".equals(baseLog.value()) && apiOperation != null && apiOperation.value() != null ){
					//请求的页面位置和中文描述
					if (baseLogController != null && !"".equals(baseLogController.value())) {
						dict.setRequestZh(new StringBuilder().append(baseLogController.value()).append("-").append(apiOperation.value()).toString());
					} else {
						dict.setRequestZh(apiOperation.value());
					}
				}else{
					//请求的页面位置和中文描述
					if (baseLogController != null && !"".equals(baseLogController.value())) {
						dict.setRequestZh(new StringBuilder().append(baseLogController.value()).append("-").append(baseLog.value()).toString());
					} else {
						dict.setRequestZh(baseLog.value());
					}
				}

				//设置页面位置
				if (baseLogController != null && !"".equals(baseLogController.webLocal())) {
					dict.setRequestLocation(new StringBuilder().append(baseLogController.webLocal()).append("-").append(baseLog.webLocal()).toString());
				}else{
					dict.setRequestLocation(baseLog.webLocal());
				}


				//未指定 OperationType 按照请求方式区分
				if (baseLog.type().val().equals(OperationType.DEFAULT.val())) {
					if("GET".equals(requestWay)){
						dict.setRequestType(OperationType.VISIT.val());
					}
					if("DELETE".equals(requestWay) || "PUT".equals(requestWay) ||"POST".equals(requestWay)){
						dict.setRequestType(OperationType.DATA.val());
					}
				} else {
					//日志的类型 访问日志操作日志等...
					dict.setRequestType(baseLog.type().val());
				}

				//日志是否记录
				dict.setIsRecord(baseLog.isRecord() ? 0 : 1);

				//类和方法名
				String methodName = method.getBeanType().getName() + "." + method.getMethod().getName();
				dict.setRequestPackage(methodName);

				//当前数据不再日志字典表时 则添加进去 通过请求的包判断
				MetaDict dictSql = dictMap.get(methodName);
				if (dictSql == null) {
					//插入
					metaDictService.insert(dict);
					//更新map
					dictMap.put(dict.getRequestPackage(), dict);
				} else if (!dict.equals(dictSql)) {
					//更新
					dict.setLogDictId(dictSql.getLogDictId());
					metaDictService.updateById(dict);

					//更新map
					dictMap.put(dict.getRequestPackage(), dict);
				} else {
					//数据库中有该字段并且无更细 所以不需要操作
				}
			}
		}
	}

}
