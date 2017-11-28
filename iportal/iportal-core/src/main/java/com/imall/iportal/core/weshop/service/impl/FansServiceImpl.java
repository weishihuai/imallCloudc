package com.imall.iportal.core.weshop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.FansSourceCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.Member;
import com.imall.iportal.core.shop.entity.WeShop;
import com.imall.iportal.core.weshop.entity.Fans;
import com.imall.iportal.core.weshop.repository.FansRepository;
import com.imall.iportal.core.weshop.service.FansService;
import com.imall.iportal.core.weshop.vo.FansPageVo;
import com.imall.iportal.core.weshop.vo.FansRemarkUpdateVo;
import com.imall.iportal.core.weshop.vo.FansSearchParam;
import com.imall.iportal.core.weshop.vo.FansTotalVo;
import com.imall.iportal.dicts.UserSexCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class FansServiceImpl extends AbstractBaseService<Fans, Long> implements FansService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private FansRepository getFansRepository() {
        return (FansRepository) baseRepository;
    }

    @Override
    public List<Fans> findByOpenId(String openId) {
        return getFansRepository().findByOpenIdOrderByCreateDateAsc(openId);
    }

    @Override
    public Fans findByOpenIdAndShopId(String openId, Long shopId) {
        return getFansRepository().findByOpenIdAndShopId(openId, shopId);
    }

    @Override
    public Page<FansPageVo> query(Pageable pageable, FansSearchParam fansSearchParam) {
        Page<Fans> fansPage = getFansRepository().query(pageable, fansSearchParam);
        List<FansPageVo> fansPageVoList = new ArrayList<>();
        for (Fans fans : fansPage.getContent()) {
            fansPageVoList.add(buildFansPageVo(fans));
        }
        return new PageImpl<>(fansPageVoList, pageable, fansPage.getTotalElements());
    }

    @Override
    @Transactional
    public Long updateRemark(FansRemarkUpdateVo fansRemarkUpdateVo) {
        Fans fans = this.findOne(fansRemarkUpdateVo.getId());
        if (fans == null || !fans.getShopId().equals(fansRemarkUpdateVo.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"粉丝"}));
        }
        fans.setRemark(fansRemarkUpdateVo.getRemark());
        return this.update(fans).getId();
    }

    @Override
    public FansTotalVo countFansTotal(Long shopId) {
        Long isMemberFansTotal = 0L;
        Long isNotMemberFansTotal = 0L;
        WeShop weShop = ServiceManager.weShopService.findByShopId(shopId);
       if (weShop == null) {
           FansTotalVo fansTotalVo = new FansTotalVo();
           //粉丝总数
           fansTotalVo.setFansTotalCount(0L);
           //会员粉丝数
           fansTotalVo.setFansIsMemberTotalCount(isMemberFansTotal);
           //非会员粉丝数
           fansTotalVo.setFansIsNotMemberTotalCount(isNotMemberFansTotal);
           return fansTotalVo;
       }

        List<Fans> fansList = getFansRepository().listFansByShopId(shopId);
        for (Fans fans : fansList) {
            if (BoolCodeEnum.YES == BoolCodeEnum.fromCode(fans.getIsMember())) {
                isMemberFansTotal += 1;
            } else {
                isNotMemberFansTotal += 1;
            }
        }
        FansTotalVo fansTotalVo = new FansTotalVo();
        //粉丝总数
        fansTotalVo.setFansTotalCount((long) fansList.size());
        //会员粉丝数
        fansTotalVo.setFansIsMemberTotalCount(isMemberFansTotal);
        //非会员粉丝数
        fansTotalVo.setFansIsNotMemberTotalCount(isNotMemberFansTotal);
        return fansTotalVo;
    }

    @Override
    public List<Fans> findByMobileAndIsMember(String mobile,String isMember) {
        return getFansRepository().findByMobileAndIsMember(mobile,isMember);
    }

    @Override
    public List<Fans> findByWeChatUserId(Long weChatUserId) {
        return getFansRepository().findByWeChatUserId(weChatUserId);
    }

    @Override
    public Fans findByWeChatUserIdAndShopId(Long weChatUserId, Long shopId) {
        return getFansRepository().findByWeChatUserIdAndShopId(weChatUserId, shopId);
    }

    private FansPageVo buildFansPageVo(Fans fans) {
        FansPageVo fansPageVo = new FansPageVo();
        fansPageVo.setId(fans.getId());
        fansPageVo.setShopId(fans.getShopId());
        fansPageVo.setOpenId(fans.getOpenId());
        fansPageVo.setFansName(fans.getFansName());
        fansPageVo.setMobile(fans.getMobile());
        fansPageVo.setNickName(fans.getNickName());
        fansPageVo.setFansSourceCode(FansSourceCodeEnum.fromCode(fans.getFansSourceCode()).toCode());
        fansPageVo.setIsMember(BoolCodeEnum.fromCode(fans.getIsMember()).toCode());
        fansPageVo.setMemberId(fans.getMemberId());
        fansPageVo.setBuyTimes(fans.getBuyTimes());
        fansPageVo.setRemark(fans.getRemark());
        fansPageVo.setWeChatUserId(fans.getWeChatUserId());
        if (fans.getMemberId() != null) {
            Member member = ServiceManager.memberService.findOne(fans.getMemberId());
            if (member == null) {
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"会员"}));
            }
            fansPageVo.setSexCode(UserSexCodeEnum.fromCode(member.getSexCode()).toCode());
            fansPageVo.setMemberCardNum(member.getMemberCardNum());
            fansPageVo.setHomeAddr(member.getHomeAddr());
        }
        return fansPageVo;
    }
}