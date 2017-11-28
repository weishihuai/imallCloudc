package com.imall.iportal.core.elasticsearch.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by HWJ on 2017/7/22
 */
public class OrderEntity {

    /**
     * 订单ID
     */
    public static final String ID = "id";

    /**
     * 微门店ID
     */
    public static final String WE_SHOP_ID = "weShopId";

    /**
     * 门店ID
     */
    public static final String SHOP_ID = "shopId";

    /**
     * 订单 来源 代码
     */
    public static final String ORDER_SOURCE_CODE = "orderSourceCode";

    /**
     * 订单 编号
     */
    public static final String ORDER_NUM = "orderNum";

    /**
     * 开单 人 ID
     */
    public static final String OPEN_ORDER_MAN_ID = "openOrderManId";

    /**
     * 会员 卡 号码
     */
    public static final String MEMBER_CARD_NUM = "memberCardNum";

    /**
     * 会员名称
     */
    public static final String NAME = "name";

    /**
     * 会员 ID
     */
    public static final String MEMBER_ID = "memberId";

    /**
     * 支付 方式 代码
     */
    public static final String PAYMENT_WAY_CODE = "paymentWayCode";

    /**
     * 订单 状态 代码
     */
    public static final String ORDER_STATE_CODE = "orderStateCode";

    /**
     * 是否 已支付
     */
    public static final String IS_PAYED = "isPayed";

    /**
     * 是否 货到 付款
     */
    public static final String IS_COD = "isCod";

    /**
     * 订单完成时间
     */
    public static final String FINISH_TIME = "finishTime";

    /**
     * 销售时间
     */
    public static final String CREATE_DATE = "createDate";

    /**
     * 是否 麻黄碱 订单
     */
    public static final String IS_EPHEDRINE_ORDER = "isEphedrineOrder";

    /**
     * 是否 处方药 订单
     */
    public static final String IS_PRESCRIPTION_ORDER = "isPrescriptionOrder";

    /**
     * 是否 已退货订单
     */
    public static final String IS_HAS_RETURNED_PURCHASE = "isHasReturnedPurchase";

    /**
     * 订单总金额，用于报表计算汇总
     */
    public static final String ORDER_TOTAL_AMOUNT = "orderTotalAmount";

    /**
     *  实收现金金额，用于收银交班计算
     */
    public static final String REAL_GEVE_CASH_AMOUNT = "realGeveCashAmount";

    /**
     *  找零，用于收银交班计算
     */
    public static final String RETURN_SMALL = "returnSmall";

    /**
     *  医保支付金额，用于收银交班计算
     */
    public static final String MEDICAL_INSURANCE_PAYMENT_AMOUNT = "medicalInsurancePaymentAmount";

    /**
     * 收货人 姓名
     */
    public static final String RECEIVER_NAME = "receiverName";

    /**
     *联系 电话
     */
    public static final String CONTACT_TEL = "contactTel";

    /**
     *预约 送达 时间 开始
     */
    public static final String BOOK_DELIVERY_TIME_START = "bookDeliveryTimeStart";

    /**
     *预约 送达 时间 结束
     */
    public static final String BOOK_DELIVERY_TIME_END = "bookDeliveryTimeEnd";

    /**
     * 微信 用户 ID
     */
    public static final String WECHAT_USER_ID = "wechatUserId";

    private Long id;
    private Long shopId;
    private Long weShopId;
    private String orderSourceCode;
    private String orderNum;
    private Long openOrderManId;
    private String memberCardNum;
    private Long memberId;
    private String paymentWayCode;
    private String orderStateCode;
    private String isPayed;
    private String isCod;
    private String isEphedrineOrder;
    private String isPrescriptionOrder;
    private Date finishTime;
    private Date createDate;
    private String isHasReturnedPurchase;
    private Double orderTotalAmount;
    private Double realGeveCashAmount;
    private Double returnSmall;
    private Double medicalInsurancePaymentAmount;
    private String receiverName;
    private String contactTel;
    private Date bookDeliveryTimeStart;
    private Date bookDeliveryTimeEnd;
    private Long wechatUserId;
    private List<OrderItemEntity> itemList;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getWeShopId() {
        return weShopId;
    }

    public void setWeShopId(Long weShopId) {
        this.weShopId = weShopId;
    }

    public String getOrderSourceCode() {
        return orderSourceCode;
    }

    public void setOrderSourceCode(String orderSourceCode) {
        this.orderSourceCode = orderSourceCode;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getOpenOrderManId() {
        return openOrderManId;
    }

    public void setOpenOrderManId(Long openOrderManId) {
        this.openOrderManId = openOrderManId;
    }

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getPaymentWayCode() {
        return paymentWayCode;
    }

    public void setPaymentWayCode(String paymentWayCode) {
        this.paymentWayCode = paymentWayCode;
    }

    public String getOrderStateCode() {
        return orderStateCode;
    }

    public void setOrderStateCode(String orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

    public String getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(String isPayed) {
        this.isPayed = isPayed;
    }

    public String getIsCod() {
        return isCod;
    }

    public void setIsCod(String isCod) {
        this.isCod = isCod;
    }

    public String getIsEphedrineOrder() {
        return isEphedrineOrder;
    }

    public void setIsEphedrineOrder(String isEphedrineOrder) {
        this.isEphedrineOrder = isEphedrineOrder;
    }

    public String getIsPrescriptionOrder() {
        return isPrescriptionOrder;
    }

    public void setIsPrescriptionOrder(String isPrescriptionOrder) {
        this.isPrescriptionOrder = isPrescriptionOrder;
    }

    public String getIsHasReturnedPurchase() {
        return isHasReturnedPurchase;
    }

    public void setIsHasReturnedPurchase(String isHasReturnedPurchase) {
        this.isHasReturnedPurchase = isHasReturnedPurchase;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public Double getRealGeveCashAmount() {
        return realGeveCashAmount;
    }

    public void setRealGeveCashAmount(Double realGeveCashAmount) {
        this.realGeveCashAmount = realGeveCashAmount;
    }

    public Double getReturnSmall() {
        return returnSmall;
    }

    public void setReturnSmall(Double returnSmall) {
        this.returnSmall = returnSmall;
    }

    public Double getMedicalInsurancePaymentAmount() {
        return medicalInsurancePaymentAmount;
    }

    public void setMedicalInsurancePaymentAmount(Double medicalInsurancePaymentAmount) {
        this.medicalInsurancePaymentAmount = medicalInsurancePaymentAmount;
    }

    public List<OrderItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemEntity> itemList) {
        this.itemList = itemList;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public Date getBookDeliveryTimeStart() {
        return bookDeliveryTimeStart;
    }

    public void setBookDeliveryTimeStart(Date bookDeliveryTimeStart) {
        this.bookDeliveryTimeStart = bookDeliveryTimeStart;
    }

    public Date getBookDeliveryTimeEnd() {
        return bookDeliveryTimeEnd;
    }

    public void setBookDeliveryTimeEnd(Date bookDeliveryTimeEnd) {
        this.bookDeliveryTimeEnd = bookDeliveryTimeEnd;
    }

    public Long getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(Long wechatUserId) {
        this.wechatUserId = wechatUserId;
    }
}
