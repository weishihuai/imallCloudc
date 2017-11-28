package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by HWJ on 2017/8/10.
 */
public class SellReturnedPurchaseOrderSearchParam {

    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;

    /**
     * 退货单号
     */
    private String orderNum;

    /**
     * 收银员sysUserId
     */
    private Long cashierId;

    /**
     * 退货时间查询开始时间
     */
    private String fromDateString;

    /**
     * 退货时间查询结束时间
     */
    private String toDateString;

    /**
     * 拼音/名称/编码/条形码
     */
    private String goodsSearchFields;

    /**
     * 会员卡号/手机号/姓名
     */
    private String memberSearchFields;

    public String getGoodsSearchFields() {
        return goodsSearchFields;
    }

    public void setGoodsSearchFields(String goodsSearchFields) {
        this.goodsSearchFields = goodsSearchFields;
    }

    public String getMemberSearchFields() {
        return memberSearchFields;
    }

    public void setMemberSearchFields(String memberSearchFields) {
        this.memberSearchFields = memberSearchFields;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
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

}
