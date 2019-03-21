package com.hedian.platform.annotation;

/**
 * @ClassName: OperationType
 * @Description: 日志操作类型
 * @Auther: SuperWang
 * @Date: 2019/3/21 09:16
 * @Vsersion: 1.0
 */
public enum OperationType {

	/**
	 * 行为日志
	 */
	DEFAULT(-1),
	/**
	 * 行为日志
	 */
	BEHAVIOR(0),

	/**
	 * 数据操作日志
	 */
	DATA(1),

	/**
	 * 异常日志
	 */
	EXCEPTION(2),

	/**
	 * 登录日志
	 */
	LOGIN(3),

	/**
	 * 访问日志
	 */
	VISIT(4);

	private Integer type;

	OperationType(Integer type){
		this.type = type;
	}
	public Integer val(){
		return type;
	}
}
