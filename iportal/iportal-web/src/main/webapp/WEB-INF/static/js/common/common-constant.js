/**
 * Created by wsh on 2017/7/17.
 */

export const REGEXP_PHONE = /^1[23456789]\d{9}$/;                                          //手机号码
export const REGEXP_INT = /^[1-9]\d*$/;                                                   //正整数
export const REGEXP_INT_AND_LETTER = /^[A-Za-z0-9]{4,40}$/;                               //英文和数字
export const REGEXP_INT_ZERO = /^[0-9]\d*$/;                                              //正整数(包含0)
export const REGEXP_DOUBLE = /^[1-9]\d*\.[0-9]\d*|0\.\d*[0-9]\d*|^[0]$|^[1-9]\d*$/;    //浮点数
export const REGEXP_ID_CARD = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;          //身份证号码
export const REGEXP_DOUBLE_2 = /^\d+(\.\d{1,2})?$/;                                         //非负实数（可带两位小数）
export const REGEXP_DOUBLE_1 = /^\d+(\.\d{1})?$/;                                         //非负实数（可带一位小数）
export const REGEXP_NOT_CHINESE = /[^\u4e00-\u9fa5]/;                                         //非中文
export const REGEXP_CHINESE = /[^\u4e00-\u9fa5]/;                                         //中文
export const REGEXP_PHONE_All = /^1[23456789]\d{9}$|^\d{3}-\d{3}-\d{4}$|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;


export const DOUBLE_REG = /(^0\.[1-9]\d?$)|(^0\.\d[1-9]$)|(^[1-9]\d{0,17}?\.\d{1,2}$)|(^[1-9]\d{0,17}$)/;
export const LONG_REG = /^[1-9]\d{0,18}$/;
export const REGEXP_EMAIL = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;            //邮箱
export const REGEXP_LOGIN_PASSWORD = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;    //登录密码
export const REGEXP_UNSIGNED_INT = /^(0|[1-9]\d*)$/;                                    //大于或等于0的整数
export const REGEXP_SIGNLESS_INT = /^([1-9]\d*)$/;                                      //大于0的整数
export const REGEXP_PRICE = /^(0|[1-9]\d*)(\.\d{1,2})?$/;                               //价格（可带两位小数）
export const REGEXP_NUM = /^[0-9]*$/;                                                    //数字
export const REGEXP_ZIPCODE = /^[1-9]\d{5}(?!\d)$/;                                         //邮政编码格式
export const REGEXP_URL =/(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;  //url
export const REGEXP_PERCENT =/^(0|[1-9])(\.\d{0,1})?$/;//0.0到9.9折扣

export const ERROR_PHONE_FORMAT = '手机格式不正确';
export const REGEXP_PHONE_All_FORMAT = '电话格式不正确';
export const ERROR_ID_CARD = '身份证号码格式不正确';
export const ERROR_URL = '请输入完整的url';
export const ERROR_NUM = '请输入正确的数字格式';
export const ERROR_REGEXP_DOUBLE_1 = '最多1位小数';
export const ERROR_REGEXP_PERCENT='请输入0.1到9.9';
export const ERROR_REGEXP_PRICE_DOUBLE = '输入的格式不正确,最多两个小数';
export const ERROR_NAME_INT_AND_LETTER = '用户名请输入字母或者数字';
export const ERROR_EMAIL_FORMAT = '邮箱格式不正确';
export const ERROR_PASSWORD_FORMAT = '密码须字母+数字组合，长度为6-20位';
export const ERROR_REGEXP_DOUBLE = '输入的格式不正确';
export const ERROR_REGEXP_DOUBLE_2 = '最多2位小数';
export const ERROR_PRICE_RANGE = '价格范围：0~999999';
export const ERROR_ZIPCODE = '邮政编码不正确';
export const ERROR_INT = '请输入大于0的整数';
export const ERROR_REGEXP_INT = "输入的格式不正确";
export const ERROR_UNSIGNED_INT = '请输入大于或等于0的整数';
export const ERROR_REG_CAPITAL_RANGE = '注册资本范围：0~999999万元';
export const ERROR_CONTAIN_BLANK = '请不要输入空格';
export const QQ_MAP_KEY = "IMSBZ-M7ZWU-HCVV7-4FRXE-ESSDS-3OFKL";
export const QQ_MAP_PROJECT_NM = "imall";
export const ERROR_REGEXP_PRICE_DOUBLE_COMMON = '价格输入的格式不正确,最多两个小数';
export const WEB_NAME = "智慧药店-";