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
 * 日志字典表
 * </p>
 *
 * @author SuperWang
 * @since 2019-03-21
 */
@TableName("t_log_meta_dict")
public class MetaDict extends Model<MetaDict> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "log_dict_id", type = IdType.UUID)
    private String logDictId;
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


    public String getLogDictId() {
        return logDictId;
    }

    public void setLogDictId(String logDictId) {
        this.logDictId = logDictId;
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

    public String getRequestWay() {
        return requestWay;
    }

    public void setRequestWay(String requestWay) {
        this.requestWay = requestWay;
    }

    public Integer getIsRecord() {
        return isRecord;
    }

    public void setIsRecord(Integer isRecord) {
        this.isRecord = isRecord;
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
        return this.logDictId;
    }

    @Override
    public String toString() {
        return "MetaDict{" +
        ", logDictId=" + logDictId +
        ", requestPath=" + requestPath +
        ", requestLocation=" + requestLocation +
        ", requestZh=" + requestZh +
        ", requestPackage=" + requestPackage +
        ", requestWay=" + requestWay +
        ", isRecord=" + isRecord +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetaDict dict = (MetaDict) o;

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
