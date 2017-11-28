package com.imall.iportal.frontend.wechat.member.service;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.OrderSourceCodeEnum;
import com.imall.commons.dicts.QRCodeTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.ValidateCodeLog;
import com.imall.iportal.core.shop.entity.Order;
import com.imall.iportal.core.shop.entity.ReceiverAddr;
import com.imall.iportal.core.shop.vo.WeChatUserDetailVo;
import com.imall.iportal.core.shop.vo.WeChatUserNickNameUpdateVo;
import com.imall.iportal.core.weshop.entity.WeChatUser;
import com.imall.iportal.dicts.ValidateCodeTypeEnum;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.frontend.wechat.member.proxy.WeChatUserInfoProxy;
import com.imall.iportal.frontend.wechat.member.proxy.WeChatUserProxy;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by wsh on 2017/8/18.
 * 微信端 微信用户相关Service方法
 */
@Service
@Transactional(readOnly = true)
public class WeChatUserProxyService {

    /**
     * 查询微信用户信息
     *
     * @return
     */
    public WeChatUserInfoProxy findWeChatUserInfo() {
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        Long weChatUserId = weChatUserProxy.getId();
        List<ReceiverAddr> receiverAddressList = ServiceManager.receiverAddrService.findByWeChatUserId(weChatUserId);
        WeChatUser weChatUser = ServiceManager.weChatUserService.findOne(weChatUserId);
        List<Order> orderList = ServiceManager.orderService.listOrderByWeChatUserId(weChatUserId, OrderSourceCodeEnum.WEIXIN.toCode());
        WeChatUserInfoProxy weChatUserInfoProxy = new WeChatUserInfoProxy();
        weChatUserInfoProxy.setId(weChatUserId);
        weChatUserInfoProxy.setMobile(weChatUser.getMobile());
        weChatUserInfoProxy.setNickName(weChatUser.getNickName());
        weChatUserInfoProxy.setOpenId(weChatUser.getOpenId());
        weChatUserInfoProxy.setDemandTotal((long) orderList.size());
        weChatUserInfoProxy.setReceiveAddressTotal((long) receiverAddressList.size());
        //从文件系统中查询微信用户头像信息
        if (StringUtils.isNotBlank(weChatUser.getUserHeadPict())) {
            weChatUserInfoProxy.setImgUrl(FileSystemEngine.getFileSystem().getUrl(weChatUser.getUserHeadPict(), "100X100"));
        }

        return weChatUserInfoProxy;
    }

    /**
     * 微信用户 昵称修改
     *
     * @param weChatUserNickNameUpdateVo 昵称UpdateVo
     */
    @Transactional
    public void updateNickName(WeChatUserNickNameUpdateVo weChatUserNickNameUpdateVo) {
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        weChatUserNickNameUpdateVo.setId(weChatUserProxy.getId());
        ServiceManager.weChatUserService.updateNickName(weChatUserNickNameUpdateVo);
    }

    /**
     * 根据微信用户ID查询用户详情信息
     *
     * @return
     */
    public WeChatUserDetailVo findById() {
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        Long weChatUserId = weChatUserProxy.getId();
        WeChatUser weChatUser = ServiceManager.weChatUserService.findOne(weChatUserId);
        WeChatUserDetailVo weChatUserDetailVo = new WeChatUserDetailVo();
        weChatUserDetailVo.setId(weChatUser.getId());
        weChatUserDetailVo.setOpenId(weChatUser.getOpenId());
        weChatUserDetailVo.setNickName(weChatUser.getNickName());
        weChatUserDetailVo.setMobile(weChatUser.getMobile());
        //头像信息
        if (StringUtils.isNotBlank(weChatUser.getUserHeadPict())) {
            weChatUserDetailVo.setImgUrl(FileSystemEngine.getFileSystem().getUrl(weChatUser.getUserHeadPict(), "100X100"));
        }
        return weChatUserDetailVo;
    }

    @Transactional
    public void bindMobile(String mobile, String validateCode){
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        ValidateCodeLog log = ServiceManager.validateCodeLogService.findValidLog(validateCode, mobile, ValidateCodeTypeEnum.NORMAL);
        if (log == null){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "验证码错误");
        }
        ServiceManager.weChatUserService.updateMobile(weChatUserProxy.getId(), mobile);
    }

    public WeChatUserProxy getLoginUserInfo(){
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        if (weChatUserProxy == null){
            return new WeChatUserProxy();
        }
        WeChatUser weChatUser = ServiceManager.weChatUserService.findOne(weChatUserProxy.getId());
        weChatUserProxy.setIsSubscribe(weChatUser.getIsSubscribe());
        weChatUserProxy.setMobile(weChatUser.getMobile());
        weChatUserProxy.setNickName(weChatUser.getNickName());
        weChatUserProxy.setUserName(weChatUser.getUserName());
        if (BoolCodeEnum.NO == BoolCodeEnum.fromCode(weChatUserProxy.getIsSubscribe()) && StringUtils.isBlank(weChatUserProxy.getQrCodeTicket())) {
            try {
                weChatUserProxy.setQrCodeTicket(ServiceManager.weChatApiService.getShopPromoteQRCodeTicket(QRCodeTypeCodeEnum.QR_STR_SCENE, "", null));
            } catch (IOException e) {
                weChatUserProxy.setQrCodeTicket("");
            }
        }
        return weChatUserProxy;
    }

    @Transactional
    public String downloadWeChatFile(String mediaId) throws IOException {
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        String folder = WebContextFactory.getWebRealPath() + File.separatorChar + Global.UPLOAD_DIR + File.separatorChar + "temp" + File.separatorChar;
        String downloadFilePath = ServiceManager.weChatApiService.downloadImgFromWeiXin(folder, mediaId);
        File file = new File(downloadFilePath);

        //本地图片插入到文件系统
        String fileId = FileSystemEngine.saveLocalFile(file, Global.ADMIN_DEFAULT_ORG_ID);
        FileSystemEngine.genPicsReturnFileId(fileId, "100X100",false,false);

        WeChatUser weChatUser = ServiceManager.weChatUserService.findOne(weChatUserProxy.getId());
        String oldFile = weChatUser.getUserHeadPict();
        weChatUser.setUserHeadPict(fileId);
        ServiceManager.weChatUserService.save(weChatUser);
        FileSystemEngine.getFileSystem().delete(oldFile);

        //删除临时文件
        file.delete();
        return FileSystemEngine.getFileSystem().getUrl(fileId, "100X100");
    }
}
