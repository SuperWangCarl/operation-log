package com.hedian.platform.base;

import com.hedian.platform.constants.ResultConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 错误案例:
 * 1. 返回格式不统一
 * 2. 没有考虑失败情况 失败统一在异常中处理
 * 3. 出现和业务无关的输入参数
 * 4. 出现复杂的输入参数
 * 5. 没有返回应该返回的数据
 */
@Data
@ApiModel(value="响应对象",description="基础响应对象")
public class BaseResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "响应结果 success表示成功")
	private String desc = "success";

	@ApiModelProperty(value = "响应码 10000表示成功")
	private String resultCode = "10000";

	private T data;

	public BaseResult() {
		super();
	}

	public BaseResult(T data) {
		super();
		this.data = data;
	}

	public BaseResult(String desc){
		super();
		this.resultCode = ResultConstant.OTHER_ERROE.getResultCode();
		this.desc = desc;
	}

}