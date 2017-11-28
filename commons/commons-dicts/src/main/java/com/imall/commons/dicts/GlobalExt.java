package com.imall.commons.dicts;

public class GlobalExt {

    public static final String ENUM_FROM_CODE_ERROR = "未匹配对应枚举项";  //未匹配枚举提示
    public static final String ENUM_FROM_CODE_NULL = "code为null";  //code为null错误提示
    public static final String ENUM_FROM_CODE_BLANK = "code为空字符串";  //code为空字符串错误提示
    private final static String BUSINESS_RANGE = "BUSINESS_RANGE";      //经营范围
    private final static String BUSINESS_CATEGORY = "BUSINESS_CATEGORY";      //经营品种
    public static final Long GOODS_CATEGORY_FIRST_NODE_ID = 1L; //  商品分类根节点id
    public static final Integer PRODUCT_SALES_CATEGORY_SUB_MAX_LAYERS = 2; //  商品分类 显示3级 取根节点的2层

    public static final Long SHOP_DEFAULT_ADMIN_ROLE_ID = 2L;//子公司默认角色id
    public static final Long SHOP_DEFAULT_ORG_ID = 3L;     //默认的子公司组织ID，用于预置数据读取

    public static final Long SMS_ACCOUNT_DEFAULT_OBJECT_ID = 0L;
}
