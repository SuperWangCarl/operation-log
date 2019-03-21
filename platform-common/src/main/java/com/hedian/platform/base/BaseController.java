package com.hedian.platform.base;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: BaseController
 * @Description: 基础控制类
 * @Auther: SuperWang
 * @Date: 2019/3/20 13:45
 * @Vsersion: 0.0.1
 */
public abstract class BaseController {

	private final static ThreadLocal<HttpServletRequest> REQUEST = new ThreadLocal<HttpServletRequest>();
	private final static ThreadLocal<HttpServletResponse> RESPONSE = new ThreadLocal<HttpServletResponse>();
	private final static ThreadLocal<ModelMap> MODEL = new ThreadLocal<ModelMap>();

	@ModelAttribute
	public final void init(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		REQUEST.set(request);
		RESPONSE.set(response);
		MODEL.set(model);
	}

	public final HttpServletRequest getRequest(){
		return REQUEST.get();
	}

	public final HttpServletResponse getResponse(){
		return RESPONSE.get();
	}

	public final ModelMap getModel(){
		return MODEL.get();
	}

	/*public <T> T getUser(T a){
		return getRequest().getAttribute("currentUser") == null ? new T() : (T) getRequest().getAttribute("currentUser");
	}*/
}
