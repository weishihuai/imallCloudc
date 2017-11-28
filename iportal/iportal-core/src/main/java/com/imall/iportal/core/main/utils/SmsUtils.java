package com.imall.iportal.core.main.utils;

import com.imall.iportal.core.main.entity.SmsQueue;
import com.imall.iportal.core.main.vo.SmsAccount;
import com.imall.iportal.dicts.SmsInterfaceTypeCodeEnum;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxh on 2017/2/16.
 */
@Component
public class SmsUtils {
    private static Map<String, String> ymErrorCodeMap = null;

    static {
        //TODO 可以考虑把错误码配置在spring上
        /**
         亿美直连2015-09月最新版本接口-资料说明：
         返回值 0  代表成功
         重要：同一个手机号10分钟内只能收到3条的
         */
        ymErrorCodeMap = new HashMap<>();
        ymErrorCodeMap.put("-9000", "数据格式错误,数据超出数据库允许范围");
        ymErrorCodeMap.put("-9001", "序列号格式错误");
        ymErrorCodeMap.put("-9002", "密码格式错误");
        ymErrorCodeMap.put("-9003", "客户端Key格式错误");
        ymErrorCodeMap.put("-9004", "设置转发格式错误");
        ymErrorCodeMap.put("-9006", "企业中文名格式错误");
        ymErrorCodeMap.put("-9007", "企业中文名简称格式错误");
        ymErrorCodeMap.put("-9008", "邮件地址格式错误");
        ymErrorCodeMap.put("-9009", "企业英文名格式错误");
        ymErrorCodeMap.put("-9010", "企业英文名简称格式错误");
        ymErrorCodeMap.put("-9011", "传真格式错误");
        ymErrorCodeMap.put("-9012", "联系人格式错误");
        ymErrorCodeMap.put("-9013", "联系电话");
        ymErrorCodeMap.put("-9014", "邮编格式错误");
        ymErrorCodeMap.put("-9015", "新密码格式错误");
        ymErrorCodeMap.put("-9016", "发送短信包大小超出范围");
        ymErrorCodeMap.put("-9017", "发送短信内容格式错误");
        ymErrorCodeMap.put("-9018", "发送短信扩展号格式错误");
        ymErrorCodeMap.put("-9019", "发送短信优先级格式错误");
        ymErrorCodeMap.put("-9020", "发送短信手机号格式错误");
        ymErrorCodeMap.put("-9021", "发送短信定时时间格式错误");
        ymErrorCodeMap.put("-9022", "发送短信唯一序列值错误");
        ymErrorCodeMap.put("-9023", "充值卡号格式错误");
        ymErrorCodeMap.put("-9024", "充值密码格式错误");
        ymErrorCodeMap.put("-1", "系统异常");
        ymErrorCodeMap.put("-101", "命令不被支持");
        ymErrorCodeMap.put("-102", "RegistryTransInfo删除信息失败");
        ymErrorCodeMap.put("-103", "RegistryInfo更新信息失败");
        ymErrorCodeMap.put("-104", "请求超过限制");
        ymErrorCodeMap.put("-111", "企业注册失败");
        ymErrorCodeMap.put("-117", "发送短信失败");
        ymErrorCodeMap.put("-118", "接收MO失败");
        ymErrorCodeMap.put("-119", "接收Report失败");
        ymErrorCodeMap.put("-120", "修改密码失败");
        ymErrorCodeMap.put("-122", "号码注销激活失败");
        ymErrorCodeMap.put("-110", "号码注册激活失败");
        ymErrorCodeMap.put("-123", "查询单价失败");
        ymErrorCodeMap.put("-124", "查询余额失败");
        ymErrorCodeMap.put("-125", "设置MO转发失败");
        ymErrorCodeMap.put("-126", "路由信息失败");
        ymErrorCodeMap.put("-127", "计费失败0余额");
        ymErrorCodeMap.put("-128", "计费失败余额不足");
        ymErrorCodeMap.put("-1100", "序列号错误,序列号不存在内存中,或尝试攻击的用户");
        ymErrorCodeMap.put("-1103", "序列号Key错误");
        ymErrorCodeMap.put("-1102", "序列号密码错误");
        ymErrorCodeMap.put("-1104", "路由失败，请联系系统管理员");
        ymErrorCodeMap.put("-1105", "注册号状态异常, 未用 1");
        ymErrorCodeMap.put("-1107", "注册号状态异常, 停用 3");
        ymErrorCodeMap.put("-1108", "注册号状态异常, 停止 5");
        ymErrorCodeMap.put("-113", "充值失败");
        ymErrorCodeMap.put("-1131", "充值卡无效");
        ymErrorCodeMap.put("-1132", "充值密码无效");
        ymErrorCodeMap.put("-1133", "充值卡绑定异常");
        ymErrorCodeMap.put("-1134", "充值状态无效");
        ymErrorCodeMap.put("-1135", "充值金额无效");
        ymErrorCodeMap.put("-190", "数据操作失败");
        ymErrorCodeMap.put("-1901", "数据库插入操作失败");
        ymErrorCodeMap.put("-1902", "数据库更新操作失败");
        ymErrorCodeMap.put("-1903", "数据库删除操作失败");
    }

    private static String ymAddress;
    private static String mwAddress;

    public void setYmAddress(String ymAddress) {
        this.ymAddress = ymAddress;
    }

    public void setMwAddress(String mwAddress) {
        this.mwAddress = mwAddress;
    }

    public static double getLeftQuantity(SmsAccount smsAccount) throws IOException {
        switch (SmsInterfaceTypeCodeEnum.fromCode(smsAccount.getSmsInterfaceType())) {
            case EMAY:
                return getYmQuantity(smsAccount);
            case MONTNETS:
                return getMwQuantity(smsAccount);
            default:
                throw new IllegalArgumentException("短信接口类型不存在");
        }
    }

    public static void sendSms(SmsAccount smsAccount, SmsQueue smsQueue) throws IOException {
        switch (SmsInterfaceTypeCodeEnum.fromCode(smsAccount.getSmsInterfaceType())) {
            case EMAY:
                ymSendSms(smsAccount, smsQueue);
                break;
            case MONTNETS:
                mwSendSms(smsAccount, smsQueue);
                break;
            default:
                break;
        }
    }

    private static double getYmQuantity(SmsAccount smsAccount) {
        if (StringUtils.isBlank(ymAddress) || smsAccount == null) {
            return 0;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cdkey", smsAccount.getAccount());
        params.put("password", smsAccount.getPassword());
        try {
            String resultString = HttpClientUtils.doPost(ymAddress + "querybalance.action", params);
            String respCode = ymXmlResponse(resultString, "error");
            if (StringUtils.isNotBlank(respCode) && "0".equals(respCode)) {
                String balance = ymXmlResponse(resultString, "message");
                return Double.valueOf(balance);
            } else {
                throw new RuntimeException("短信平台错误,错误信息：" + ymErrorCodeMap.get(respCode));
            }
        } catch (IOException e) {
            return 0;
        }
    }

    private static double getMwQuantity(SmsAccount smsAccount) throws IOException {
        if (StringUtils.isBlank(mwAddress) || smsAccount == null) {
            return 0;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", smsAccount.getAccount());//帐号
        params.put("password", smsAccount.getPassword());//密码
        String resultString = HttpClientUtils.doPost(mwAddress + "MongateQueryBalance", params);
        String result = mwXmlResponse(resultString);
        if (StringUtils.isNotBlank(result) && "-1".equals(result)) {
            throw new RuntimeException("短信平台错误,错误代码：" + result + ",原因：参数为空。信息、电话号码等有空指针，登陆失败");
        }
        if (StringUtils.isNotBlank(result) && "-12".equals(result)) {
            throw new RuntimeException("短信平台错误,错误代码：" + result + ",原因：有异常电话号码");
        }
        if (StringUtils.isNotBlank(result) && "-14".equals(result)) {
            throw new RuntimeException("短信平台错误,错误代码：" + result + ",原因：实际号码个数超过100");
        }
        if (StringUtils.isNotBlank(result) && "-14".equals(result)) {
            throw new RuntimeException("短信平台错误,错误代码：" + result + ",原因：web服务器内部错误");
        }
        return Double.valueOf(result);
    }

    private static void mwSendSms(SmsAccount smsAccount, SmsQueue queue) throws IOException {
        if (StringUtils.isBlank(mwAddress) || smsAccount == null) {
            throw new RuntimeException("短信帐号未开通");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", smsAccount.getAccount());//帐号
        params.put("password", smsAccount.getPassword());//密码
        params.put("pszMobis", queue.getReceiverMobile());//电话号码，群发需要以逗号隔开电话号码
        //   String smsContent ="同事您好，感谢您对此次测试的配合。[123456]";   //测试要求发送消息
        params.put("pszMsg", queue.getCont());
        params.put("iMobiCount", "1");//发送电话号码个数
        params.put("pszSubPort", "*");//子端口号码，不带请填星号{*}

        String resultString = HttpClientUtils.doPost(mwAddress + "MongateCsSpSendSmsNew", params);
        String result = mwXmlResponse(resultString);
        if (StringUtils.isNotBlank(result) && "-1".equals(result)) {
            throw new RuntimeException("短信平台错误,错误代码：" + result + ",原因：参数为空。信息、电话号码等有空指针，登陆失败");
        }
        if (StringUtils.isNotBlank(result) && "-12".equals(result)) {
            throw new RuntimeException("短信平台错误,错误代码：" + result + ",原因：有异常电话号码");
        }
        if (StringUtils.isNotBlank(result) && "-14".equals(result)) {
            throw new RuntimeException("短信平台错误,错误代码：" + result + ",原因：实际号码个数超过100");
        }
        if (StringUtils.isNotBlank(result) && "-14".equals(result)) {
            throw new RuntimeException("短信平台错误,错误代码：" + result + ",原因：web服务器内部错误");
        }
    }

    private static void ymSendSms(SmsAccount smsAccount, SmsQueue queue) throws IOException {
        if (StringUtils.isBlank(ymAddress) || smsAccount == null) {
            throw new RuntimeException("短信帐号未开通");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cdkey", smsAccount.getAccount());
        params.put("password", smsAccount.getPassword());
        params.put("phone", queue.getReceiverMobile());
        params.put("message", queue.getCont());
        String resultString = HttpClientUtils.doPost(ymAddress + "sendsms.action", params);
        String respCode = ymXmlResponse(resultString, "error");
        if (StringUtils.isNotBlank(respCode) && !"0".equals(respCode)) {
            throw new RuntimeException("短信平台错误,错误信息：" + ymErrorCodeMap.get(respCode));
        }
    }

    private static String ymXmlResponse(String response, String element) {
        String result = "";
        Document document = null;
        try {
            document = DocumentHelper.parseText(response.trim());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        result = root.elementText(element);
        return result;
    }

    private static String mwXmlResponse(String response) {
        String result = "";
        Document document = null;
        try {
            document = DocumentHelper.parseText(response.trim());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        result = root.getText();
        return result;
    }
}
