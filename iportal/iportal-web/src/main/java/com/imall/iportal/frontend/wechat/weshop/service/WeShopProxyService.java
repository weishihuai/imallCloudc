package com.imall.iportal.frontend.wechat.weshop.service;

import com.imall.commons.dicts.FileTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.FileMngSimpleVo;
import com.imall.iportal.core.shop.vo.WeShopDetailVo;
import com.imall.iportal.frontend.wechat.weshop.proxy.WeShopDetailProxy;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsh on 2017/8/18.
 * 微信端 微信用户相关Service方法
 */
@Service
@Transactional(readOnly = true)
public class WeShopProxyService {

    /**
     * 微门店详情
     *
     * @param id
     * @return
     */
    public WeShopDetailProxy findById(Long id) {
        WeShopDetailProxy weShopDetailProxy = new WeShopDetailProxy();
        WeShopDetailVo weShopDetailVo = ServiceManager.weShopService.getDetailById(id);
        weShopDetailProxy.setShopId(weShopDetailVo.getShopId());
        weShopDetailProxy.setShopNm(weShopDetailVo.getShopNm());
        weShopDetailProxy.setShopBrief(weShopDetailVo.getShopBrief());
        weShopDetailProxy.setShopLat(weShopDetailVo.getShopLat());
        weShopDetailProxy.setShopLng(weShopDetailVo.getShopLng());
        weShopDetailProxy.setDeliveryRange(weShopDetailVo.getDeliveryRange());
        weShopDetailProxy.setContactTel(weShopDetailVo.getContactTel());
        weShopDetailProxy.setPlacardInf(weShopDetailVo.getPlacardInf());
        weShopDetailProxy.setDetailLocation(weShopDetailVo.getDetailLocation());
        //拼装营业开始时间
        String[] startTimeArray = weShopDetailVo.getSellStartTime().split(":");
        String startHour = startTimeArray[0];
        String startMinute = startTimeArray[1];
        if (Integer.parseInt(startHour) >= 1 && Integer.parseInt(startHour) < 10) {
            startHour = "0" + startHour;
        }
        if (Integer.parseInt(startMinute) >= 1 && Integer.parseInt(startMinute) < 10) {
            startMinute = "0" + startMinute;
        }
        String sellStartTime = startHour + ":" + startMinute;
        weShopDetailProxy.setSellStartTime(sellStartTime);

        //拼装营业结束时间
        String[] endTimeArray = weShopDetailVo.getSellEndTime().split(":");
        String endHour = endTimeArray[0];
        String endMinute = endTimeArray[1];
        if (Integer.parseInt(endHour) >= 1 && Integer.parseInt(endHour) < 10) {
            endHour = "0" + endHour;
        }
        if (Integer.parseInt(endMinute) >= 1 && Integer.parseInt(endMinute) < 10) {
            endMinute = "0" + endMinute;
        }
        String sellEndTime = endHour + ":" + endMinute;
        weShopDetailProxy.setSellEndTime(sellEndTime);

        weShopDetailProxy.setIsNormalSales(weShopDetailVo.getIsNormalSales());
        weShopDetailProxy.setDeliveryAmount(weShopDetailVo.getDeliveryAmount());
        //门店图片
        List<FileMngSimpleVo> shopPictList = weShopDetailVo.getShopPictList();
        List<String> imgUrl = new ArrayList<>();
        if (!CollectionUtils.isEmpty(shopPictList)) {
            for (FileMngSimpleVo fileMng : shopPictList) {
                if (FileTypeCodeEnum.fromCode(fileMng.getFileTypeCode()) == FileTypeCodeEnum.IMAGE) {
                    imgUrl.add(fileMng.getSmallFileUrl());
                }
            }
        }
        weShopDetailProxy.setImgUrlList(imgUrl);

        //门店Logo
        FileMngSimpleVo shopLogoPict = weShopDetailVo.getShopLogoPict();
        if (shopLogoPict != null) {
            if (FileTypeCodeEnum.fromCode(shopLogoPict.getFileTypeCode()) == FileTypeCodeEnum.IMAGE) {
                weShopDetailProxy.setLogoUrl(shopLogoPict.getSmallFileUrl());
            }
        }

        //店长微信
        FileMngSimpleVo shopMgrPict = weShopDetailVo.getShopMgrWeChatPict();
        if (shopMgrPict != null) {
            if (FileTypeCodeEnum.fromCode(shopMgrPict.getFileTypeCode()) == FileTypeCodeEnum.IMAGE) {
                weShopDetailProxy.setShopMgrWeiXinUrl(shopMgrPict.getSmallFileUrl());
            }
        }
        return weShopDetailProxy;
    }

}
