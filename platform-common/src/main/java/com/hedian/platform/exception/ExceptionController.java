package com.hedian.platform.exception;

import com.hedian.platform.base.BaseResult;
import com.hedian.platform.constants.ResultConstant;
import com.hedian.platform.i18n.LocaleMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;

/**
 * @Description: 包含统一异常处理的异常捕捉器
 * @Author: noahw
 * @CreateDate: 2019-01-11 9:38
 * @Version: 1.0.1
 */

@Slf4j
@RestControllerAdvice
public class ExceptionController {

	@Resource
	private LocaleMessage localeMessage;

	// 捕捉shiro的异常
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(ShiroException.class)
	public BaseResult handle401(ShiroException e) {
		return commonResult(ResultConstant.ACCESS_DENIED, e);
	}

	// 捕捉HttpRequestMethodNotSupportedException
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public BaseResult handleHttpMethodException(Exception e) {
		return commonResult(ResultConstant.HTTP_METHOD_NOT_SUPPORT, e);
	}

	// 捕捉UnauthorizedException
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(UnauthorizedException.class)
	public BaseResult handle401(Exception e) {
		return commonResult(ResultConstant.ACCESS_DENIED, e);
	}

	//捕捉参数缺失异常MissingServletRequestParameterException
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public BaseResult handleMethodParamMissingException(Exception e) {
		return commonResult(ResultConstant.PARAM_ERROR, e);
	}

	//捕捉参数校验异常MethodArgumentNotValidException
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({UnexpectedTypeException.class, MethodArgumentNotValidException.class})
	public BaseResult handleMethodParamNotValidException(MethodArgumentNotValidException e) {
		//获取 bean字段上的message错误  springmvc有默认的
		if(e.getBindingResult().hasErrors()){
			String message = e.getBindingResult().getFieldError().getDefaultMessage();
			if(!"".equals(message)){
				return initResult(message);
			}
		}
		return commonResult(ResultConstant.PARAM_ERROR, e);
	}

	// 捕捉其他所有异常
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public BaseResult globalException(Exception e) {
		return commonResult(ResultConstant.INTERNAL_SERVER_ERROR, e);
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}

	/**
	 * Description: 常用错误
	 *
	 * @param: [constant, e]
	 * @return: com.hedian.platform.base.BaseResult
	 * @auther: SuperWang
	 * @date: 2019/3/16 10:08
	 */
	private BaseResult commonResult(ResultConstant constant, Exception e) {
		//打印异常日志,用于排查
		log.info(e.getMessage(), e);
		BaseResult result = new BaseResult();
		result.setResultCode(constant.getResultCode());
		result.setDesc(localeMessage.getMessage(constant.getMessage()));
		return result;
	}

	/**
	 * Description: 其他错误
	 *
	 * @param: [constant, e]
	 * @return: com.hedian.platform.base.BaseResult
	 * @auther: SuperWang
	 * @date: 2019/3/16 10:17
	 */
	public BaseResult initResult(String message) {
		//打印异常日志,用于排查
		log.info("其他所有错误" + message);
		return new BaseResult(localeMessage.getMessage(message));
	}


}
