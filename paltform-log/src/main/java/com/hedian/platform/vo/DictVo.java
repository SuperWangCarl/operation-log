package com.hedian.platform.vo;

import lombok.Data;

/**
 * @ClassName: DictVo
 * @Description: 字典表
 * @Auther: SuperWang
 * @Date: 2019/3/21 14:39
 * @Vsersion: 0.0.1
 */
@Data
public class DictVo {
	/**
	 * 请求路径
	 */
	private String requestPath;
	/**
	 * 路径的前台位置 如 xx页面xx按钮
	 */
	private String requestLocation;
	/**
	 * 请求的中文描述
	 */
	private String requestZh;
	/**
	 * 请求的方法名
	 */
	private String requestPackage;
	/**
	 * 请求方式 PUT GET POST等
	 */
	private String requestWay;
	/**
	 * 是否记录 0记录 1不记录
	 */
	private Integer isRecord;
	/**
	 * 请求类型 0:用户行为日志 1:数据操作日志 2:异常日志 3:用户登录日志 4:用户访问日志
	 */
	private Integer requestType;
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DictVo dict = (DictVo) o;

		if (requestPath != null ? !requestPath.equals(dict.requestPath) : dict.requestPath != null) return false;
		if (requestLocation != null ? !requestLocation.equals(dict.requestLocation) : dict.requestLocation != null)
			return false;
		if (requestZh != null ? !requestZh.equals(dict.requestZh) : dict.requestZh != null) return false;
		if (requestPackage != null ? !requestPackage.equals(dict.requestPackage) : dict.requestPackage != null)
			return false;
		if (requestWay != null ? !requestWay.equals(dict.requestWay) : dict.requestWay != null) return false;
		if (isRecord != null ? !isRecord.equals(dict.isRecord) : dict.isRecord != null) return false;
		return requestType != null ? requestType.equals(dict.requestType) : dict.requestType == null;
	}

	@Override
	public int hashCode() {
		int result = requestPath != null ? requestPath.hashCode() : 0;
		result = 31 * result + (requestLocation != null ? requestLocation.hashCode() : 0);
		result = 31 * result + (requestZh != null ? requestZh.hashCode() : 0);
		result = 31 * result + (requestPackage != null ? requestPackage.hashCode() : 0);
		result = 31 * result + (requestWay != null ? requestWay.hashCode() : 0);
		result = 31 * result + (isRecord != null ? isRecord.hashCode() : 0);
		result = 31 * result + (requestType != null ? requestType.hashCode() : 0);
		return result;
	}
}
