package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by caidapao on 2017/7/6.
 * 商品公共组件列表搜索参数
 */
public class GoodsListSearchParam {

    /**
     * 货位id
     */
    private Long storageSpaceId;

    /**
     * 是否启用
     */
    private String isEnable;

    /**
     * 拼音/商品编码/商品名称
     */
    private String keyWords;

    /**
     * 创建时间
     */
    private String fromDateString;
    private String toDateString;

    /**
     * 审核状态代码
     */
    private String approveStateCode;

    public Date getFromDate() {
        if(StringUtils.isNotBlank(getFromDateString())) {
            try {
                return DateTimeUtils.convertStringToDate(getFromDateString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public Date getToDate() {
        if(StringUtils.isNotBlank(getToDateString())) {
            try {
                Date toDate = DateTimeUtils.convertStringToDate(getToDateString());
                return DateTimeUtils.getDayEndTime(toDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getApproveStateCode() {
        return approveStateCode;
    }

    public void setApproveStateCode(String approveStateCode) {
        this.approveStateCode = approveStateCode;
    }

    public Long getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getFromDateString() {
        return fromDateString;
    }

    public void setFromDateString(String fromDateString) {
        this.fromDateString = fromDateString;
    }

    public String getToDateString() {
        return toDateString;
    }

    public void setToDateString(String toDateString) {
        this.toDateString = toDateString;
    }
}
