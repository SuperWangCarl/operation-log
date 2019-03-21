package com.hedian.platform.persistence.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 日志记录表
 * </p>
 *
 * @author SuperWang
 * @since 2019-03-21
 */
@TableName("t_log_record_data")
public class RecordData extends Model<RecordData> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "log_data_id", type = IdType.UUID)
    private String logDataId;
    /**
     * 操作的用户名
     */
    private String operationName;
    /**
     * 操作的用户id
     */
    private String operationId;
    /**
     * 操作的ip地址
     */
    private String ipAddress;
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
     * 操作参数 英文名
     */
    private String requestParamEn;
    /**
     * 操作参数 中文名
     */
    private String requestParamZh;
    /**
     * 响应参数 英文名
     */
    private String responseParamEn;
    /**
     * 响应参数 中文名
     */
    private String responseParamZh;
    /**
     * 请求方式 PUT GET POST等
     */
    private String requestWay;
    /**
     * 请求类型 0:用户行为日志 1:数据操作日志 2:异常日志 3:用户登录日志 4:用户访问日志
     */
    private Integer requestType;
    /**
     * 创建者的用户ID
     */
    private String createUserId;
    /**
     * 修改者的用户ID
     */
    private String modifyUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 备注
     */
    private String comment;
    /**
     * 是否可以被删除，0不能被删除，1可被删除，默认为1
     */
    private Integer delFlag;
    /**
     * 是否有效数据，0无效（被删除），1有效
     */
    @TableLogic
    private Integer useFlag;


    public String getLogDataId() {
        return logDataId;
    }

    public void setLogDataId(String logDataId) {
        this.logDataId = logDataId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getRequestLocation() {
        return requestLocation;
    }

    public void setRequestLocation(String requestLocation) {
        this.requestLocation = requestLocation;
    }

    public String getRequestZh() {
        return requestZh;
    }

    public void setRequestZh(String requestZh) {
        this.requestZh = requestZh;
    }

    public String getRequestPackage() {
        return requestPackage;
    }

    public void setRequestPackage(String requestPackage) {
        this.requestPackage = requestPackage;
    }

    public String getRequestParamEn() {
        return requestParamEn;
    }

    public void setRequestParamEn(String requestParamEn) {
        this.requestParamEn = requestParamEn;
    }

    public String getRequestParamZh() {
        return requestParamZh;
    }

    public void setRequestParamZh(String requestParamZh) {
        this.requestParamZh = requestParamZh;
    }

    public String getResponseParamEn() {
        return responseParamEn;
    }

    public void setResponseParamEn(String responseParamEn) {
        this.responseParamEn = responseParamEn;
    }

    public String getResponseParamZh() {
        return responseParamZh;
    }

    public void setResponseParamZh(String responseParamZh) {
        this.responseParamZh = responseParamZh;
    }

    public String getRequestWay() {
        return requestWay;
    }

    public void setRequestWay(String requestWay) {
        this.requestWay = requestWay;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
    }

    @Override
    protected Serializable pkVal() {
        return this.logDataId;
    }

    @Override
    public String toString() {
        return "RecordData{" +
        ", logDataId=" + logDataId +
        ", operationName=" + operationName +
        ", operationId=" + operationId +
        ", ipAddress=" + ipAddress +
        ", requestPath=" + requestPath +
        ", requestLocation=" + requestLocation +
        ", requestZh=" + requestZh +
        ", requestPackage=" + requestPackage +
        ", requestParamEn=" + requestParamEn +
        ", requestParamZh=" + requestParamZh +
        ", responseParamEn=" + responseParamEn +
        ", responseParamZh=" + responseParamZh +
        ", requestWay=" + requestWay +
        ", requestType=" + requestType +
        ", createUserId=" + createUserId +
        ", modifyUserId=" + modifyUserId +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        ", comment=" + comment +
        ", delFlag=" + delFlag +
        ", useFlag=" + useFlag +
        "}";
    }
}
