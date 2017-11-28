package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.MemberCardUseStatCodeEnum;
import com.imall.commons.dicts.MemberStateCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.Member;
import com.imall.iportal.core.shop.entity.WeShop;
import com.imall.iportal.core.shop.repository.MemberRepository;
import com.imall.iportal.core.shop.service.MemberService;
import com.imall.iportal.core.shop.vo.*;
import com.imall.iportal.core.weshop.entity.Fans;
import com.imall.iportal.dicts.UserSexCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl extends AbstractBaseService<Member, Long> implements MemberService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private MemberRepository getMemberRepository() {
        return (MemberRepository) baseRepository;
    }

    @Override
    public Page<MemberPageVo> query(Pageable pageable, MemberSearchParam memberSearchParam) {
        Page<Member> memberPage = getMemberRepository().query(pageable, memberSearchParam);
        List<MemberPageVo> memberPageVoArrayList = new ArrayList<>();
        for (Member member : memberPage.getContent()) {
            memberPageVoArrayList.add(buildMemberPageVo(member));
        }
        return new PageImpl<>(memberPageVoArrayList, new PageRequest(memberPage.getNumber(), memberPage.getSize()), memberPage.getTotalElements());
    }

    private MemberPageVo buildMemberPageVo(Member member) {
        MemberPageVo memberPageVo = new MemberPageVo();
        memberPageVo.setId(member.getId());
        memberPageVo.setName(member.getName());
        memberPageVo.setMobile(member.getMobile());
        memberPageVo.setSexCode(UserSexCodeEnum.fromCode(member.getSexCode()).toCode());
        memberPageVo.setHomeAddr(member.getHomeAddr());
        memberPageVo.setProfession(member.getProfession());
        memberPageVo.setBirthdayString(member.getBirthdayString());
        memberPageVo.setDiseaseHistory(member.getDiseaseHistory());
        memberPageVo.setCommonlyUsedDrugs(member.getCommonlyUsedDrugs());
        memberPageVo.setRemark(member.getRemark());
        if (StringUtils.isNotBlank(member.getIsGiveCard())) {
            memberPageVo.setIsGiveCard(BoolCodeEnum.fromCode(member.getIsGiveCard()).toCode());
        }
        memberPageVo.setMemberCardNum(member.getMemberCardNum());
        if (StringUtils.isNotBlank(member.getIsMedicalInsuranceCard())) {
            memberPageVo.setIsMedicalInsuranceCard(BoolCodeEnum.fromCode(member.getIsMedicalInsuranceCard()).toCode());
        }
        if (StringUtils.isNotBlank(member.getCardUseStateCode())) {
            memberPageVo.setCardUseStateCode(MemberCardUseStatCodeEnum.fromCode(member.getCardUseStateCode()).toCode());
        }
        memberPageVo.setGiveCardMan(member.getGiveCardMan());
        memberPageVo.setGiveCardTimeString(member.getGiveCardTimeString());
        memberPageVo.setEffectTimeString(member.getEffectTimeString());
        memberPageVo.setExpireTimeString(member.getExpireTimeString());
        if (StringUtils.isNotBlank(member.getMemberStateCode())) {
            memberPageVo.setMemberStateCode(MemberStateCodeEnum.fromCode(member.getMemberStateCode()).toCode());
        }
        memberPageVo.setCreateDateString(member.getCreateDateString());
        return memberPageVo;
    }

    @Override
    @Transactional
    public void save(MemberSaveVo memberSaveVo, CurrUserVo currUserVo) throws ParseException {
        Long memberId = save(build(memberSaveVo, currUserVo)).getId();
        //更新粉丝的信息
        List<Fans> fansList = ServiceManager.fansService.findByMobileAndIsMember(memberSaveVo.getMobile(), BoolCodeEnum.NO.toCode());
        WeShop weShop = ServiceManager.weShopService.findByShopId(memberSaveVo.getShopId());
        if (!CollectionUtils.isEmpty(fansList)) {
            for (Fans fans : fansList) {
                fans.setShopId(weShop.getShopId());
                fans.setMemberId(memberId);
                fans.setIsMember(BoolCodeEnum.YES.toCode());
                ServiceManager.fansService.update(fans);
            }
        }
    }

    /**
     * 构建保存会员档案信息的对象
     *
     * @param memberSaveVo
     * @return
     * @throws ParseException
     */
    private Member build(MemberSaveVo memberSaveVo, CurrUserVo currUserVo) throws ParseException {
        Member member = new Member();
        member.setShopId(memberSaveVo.getShopId());
        member.setName(memberSaveVo.getName());
        member.setMobile(memberSaveVo.getMobile());
        member.setMemberCardNum(memberSaveVo.getMobile());//会员卡号默认为手机号
        member.setMemberStateCode(MemberStateCodeEnum.NORMAL.toCode());
        member.setSexCode(UserSexCodeEnum.fromCode(memberSaveVo.getSexCode()).toCode());
        if (StringUtils.isNotBlank(memberSaveVo.getIsMedicalInsuranceCard())) {
            member.setIsMedicalInsuranceCard(BoolCodeEnum.fromCode(memberSaveVo.getIsMedicalInsuranceCard()).toCode());
        }
        if (StringUtils.isNotBlank(memberSaveVo.getProfession())) {
            member.setProfession(memberSaveVo.getProfession());
        }
        if (StringUtils.isNotBlank(memberSaveVo.getDiseaseHistory())) {
            member.setDiseaseHistory(memberSaveVo.getDiseaseHistory());
        }
        if (StringUtils.isNotBlank(memberSaveVo.getBirthdayString())) {
            member.setBirthday(DateTimeUtils.convertStringToDate(memberSaveVo.getBirthdayString()));
        }
        if (StringUtils.isNotBlank(memberSaveVo.getCommonlyUsedDrugs())) {
            member.setCommonlyUsedDrugs(memberSaveVo.getCommonlyUsedDrugs());
        }
        if (StringUtils.isNotBlank(memberSaveVo.getRemark())) {
            member.setRemark(memberSaveVo.getRemark());
        }
        if (StringUtils.isNotBlank(memberSaveVo.getHomeAddr())) {
            member.setHomeAddr(memberSaveVo.getHomeAddr());
        }

        member.setIsGiveCard(BoolCodeEnum.fromCode(memberSaveVo.getIsGiveCard()).toCode());
        if (BoolCodeEnum.YES == BoolCodeEnum.fromCode(memberSaveVo.getIsGiveCard())) {
            if (StringUtils.isNotBlank(memberSaveVo.getCardUseStateCode())) {
                member.setCardUseStateCode(MemberCardUseStatCodeEnum.fromCode(memberSaveVo.getCardUseStateCode()).toCode());
            }
            if (StringUtils.isNotBlank(memberSaveVo.getGiveCardMan())) {
                member.setGiveCardMan(memberSaveVo.getGiveCardMan());
            } else {
                member.setGiveCardMan(ServiceManager.sysUserService.findCurrentLoginUserMessage(currUserVo).getRealName());
            }
            if (StringUtils.isNotBlank(memberSaveVo.getGiveCardTimeString())) {
                member.setGiveCardTime(DateTimeUtils.convertStringToDate(memberSaveVo.getGiveCardTimeString()));
            } else {
                member.setGiveCardTime(new Date());
            }
            if (StringUtils.isNotBlank(memberSaveVo.getEffectTimeString())) {
                member.setEffectTime(DateTimeUtils.convertStringToDate(memberSaveVo.getEffectTimeString()));
            }
            if (StringUtils.isNotBlank(memberSaveVo.getExpireTimeString())) {
                member.setExpireTime(DateTimeUtils.convertStringToDate(memberSaveVo.getExpireTimeString()));
            }
        }
        return member;
    }

    @Override
    @Transactional
    public Long update(MemberUpdateVo memberUpdateVo) throws ParseException {
        Member member = this.findOne(memberUpdateVo.getId());
        if (member == null || !memberUpdateVo.getShopId().equals(member.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"会员"}));
        }
        buildMember(member, memberUpdateVo);
        Long memberId = update(member).getId();

        //更新粉丝信息
        List<Fans> fansList = ServiceManager.fansService.findByMobileAndIsMember(memberUpdateVo.getMobile(), BoolCodeEnum.NO.toCode());
        WeShop weShop = ServiceManager.weShopService.findByShopId(memberUpdateVo.getShopId());
        if (!CollectionUtils.isEmpty(fansList)) {
            for (Fans fans : fansList) {
                fans.setShopId(weShop.getShopId());
                fans.setIsMember(BoolCodeEnum.YES.toCode());
                fans.setMemberId(memberId);
                ServiceManager.fansService.update(fans);
            }
        }
        return memberId;
    }

    @Override
    public MemberDetailVo findById(Long id, Long shopId) {
        Member member = this.findOne(id);
        if (member == null || !shopId.equals(member.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"会员"}));
        }
        MemberDetailVo memberDetailVo = new MemberDetailVo();
        memberDetailVo.setName(member.getName());
        memberDetailVo.setMobile(member.getMobile());
        memberDetailVo.setSexCode(UserSexCodeEnum.fromCode(member.getSexCode()).toCode());
        memberDetailVo.setHomeAddr(member.getHomeAddr());
        memberDetailVo.setProfession(member.getProfession());
        memberDetailVo.setBirthdayString(member.getBirthdayString());
        memberDetailVo.setDiseaseHistory(member.getDiseaseHistory());
        memberDetailVo.setCommonlyUsedDrugs(member.getCommonlyUsedDrugs());
        memberDetailVo.setRemark(member.getRemark());
        if (StringUtils.isNotBlank(member.getIsGiveCard())) {
            memberDetailVo.setIsGiveCard(BoolCodeEnum.fromCode(member.getIsGiveCard()).toCode());
        }
        memberDetailVo.setMemberCardNum(member.getMemberCardNum());
        if (StringUtils.isNotBlank(member.getIsMedicalInsuranceCard())) {
            memberDetailVo.setIsMedicalInsuranceCard(BoolCodeEnum.fromCode(member.getIsMedicalInsuranceCard()).toCode());
        }
        if (StringUtils.isNotBlank(member.getCardUseStateCode())) {
            memberDetailVo.setCardUseStateCode(MemberCardUseStatCodeEnum.fromCode(member.getCardUseStateCode()).toCode());
        }
        memberDetailVo.setGiveCardMan(member.getGiveCardMan());
        memberDetailVo.setGiveCardTimeString(member.getGiveCardTimeString());
        memberDetailVo.setEffectTimeString(member.getEffectTimeString());
        memberDetailVo.setExpireTimeString(member.getExpireTimeString());
        if (StringUtils.isNotBlank(member.getMemberStateCode())) {
            memberDetailVo.setMemberStateCode(MemberStateCodeEnum.fromCode(member.getMemberStateCode()).toCode());
        }
        return memberDetailVo;
    }

    @Override
    @Transactional
    public Long saveSalesPosMember(SalesPosMemberSaveVo salesPosMemberSaveVo) {
        Member member = CommonUtil.copyProperties(salesPosMemberSaveVo, new Member());
        member.setShopId(salesPosMemberSaveVo.getShopId());
        member.setMemberCardNum(salesPosMemberSaveVo.getMobile());  //会员卡号默认为手机号
        member.setSexCode(UserSexCodeEnum.SECRET.toCode());
        member.setIsGiveCard(BoolCodeEnum.YES.toCode());
        member.setGiveCardMan(salesPosMemberSaveVo.getGiveCardMan());
        member.setCardUseStateCode(MemberCardUseStatCodeEnum.NORMAL.toCode());
        if (StringUtils.isNotBlank(salesPosMemberSaveVo.getMemberStateCode())) {
            member.setMemberStateCode(MemberStateCodeEnum.fromCode(salesPosMemberSaveVo.getMemberStateCode()).toCode());
        }
        member.setGiveCardTime(new Date());
        member.setEffectTime(new Date());
        member.setExpireTime(DateTimeUtils.addYears(new Date(), 5));
        return save(member).getId();
    }

    @Override
    public Member findMemberAndConvertCart(String searchText, String uniqueKey, Long shopId) {
        Member member = getMemberRepository().findByMobileOrCardNum(searchText, shopId);
        if (member == null) {
            return null;
        }

        if (StringUtils.isNotBlank(member.getMemberStateCode()) && MemberStateCodeEnum.DISABLED == MemberStateCodeEnum.fromCode(member.getMemberStateCode())) {
            return null;
        }

        //过滤禁用状态的会员
        if (BoolCodeEnum.YES == BoolCodeEnum.fromCode(member.getIsGiveCard())) {

            if (StringUtils.isNotBlank(member.getEffectTimeString()) && StringUtils.isNotBlank(member.getExpireTimeString())) {
                long now = new Date().getTime();
                if (now < member.getEffectTime().getTime() || now > member.getExpireTime().getTime()) {
                    return null;
                }
            }

            if (StringUtils.isNotBlank(member.getCardUseStateCode())) {
                if (MemberCardUseStatCodeEnum.DISABLED == MemberCardUseStatCodeEnum.fromCode(member.getCardUseStateCode())) {
                    return null;
                }
            }
        }
        ServiceManager.salesPosShoppingFlowService.convert(uniqueKey, member.getId());
        return member;
    }

    /**
     * 构建Member对象
     *
     * @param member
     * @param memberUpdateVo
     * @throws ParseException
     */
    private void buildMember(Member member, MemberUpdateVo memberUpdateVo) throws ParseException {
        member.setName(memberUpdateVo.getName());
        member.setMobile(memberUpdateVo.getMobile());
        member.setMemberCardNum(memberUpdateVo.getMobile());    //会员卡号默认为手机号
        member.setSexCode(UserSexCodeEnum.fromCode(memberUpdateVo.getSexCode()).toCode());
        if (StringUtils.isNotBlank(memberUpdateVo.getIsMedicalInsuranceCard())) {
            member.setIsMedicalInsuranceCard(BoolCodeEnum.fromCode(memberUpdateVo.getIsMedicalInsuranceCard()).toCode());
        }
        member.setProfession(memberUpdateVo.getProfession());
        if (StringUtils.isNotBlank(memberUpdateVo.getBirthdayString())) {
            member.setBirthday(DateTimeUtils.convertStringToDate(memberUpdateVo.getBirthdayString()));
        }
        member.setCommonlyUsedDrugs(memberUpdateVo.getCommonlyUsedDrugs());
        member.setDiseaseHistory(memberUpdateVo.getDiseaseHistory());
        if (StringUtils.isNotBlank(memberUpdateVo.getIsGiveCard())) {
            member.setIsGiveCard(BoolCodeEnum.fromCode(memberUpdateVo.getIsGiveCard()).toCode());
        }
        member.setRemark(memberUpdateVo.getRemark());
        member.setHomeAddr(memberUpdateVo.getHomeAddr());

        if (StringUtils.isNotBlank(memberUpdateVo.getIsGiveCard())) {
            member.setIsGiveCard(BoolCodeEnum.fromCode(memberUpdateVo.getIsGiveCard()).toCode());
        }
        if (BoolCodeEnum.YES == BoolCodeEnum.fromCode(memberUpdateVo.getIsGiveCard())) {
            if (StringUtils.isNotBlank(memberUpdateVo.getCardUseStateCode())) {
                member.setCardUseStateCode(MemberCardUseStatCodeEnum.fromCode(memberUpdateVo.getCardUseStateCode()).toCode());
            }
            if (StringUtils.isNotBlank(memberUpdateVo.getGiveCardMan())) {
                member.setGiveCardMan(memberUpdateVo.getGiveCardMan());
            }
            if (StringUtils.isNotBlank(memberUpdateVo.getEffectTimeString())) {
                member.setEffectTime(DateTimeUtils.convertStringToDate(memberUpdateVo.getEffectTimeString()));
            }
            if (StringUtils.isNotBlank(memberUpdateVo.getGiveCardTimeString())) {
                member.setGiveCardTime(DateTimeUtils.convertStringToDate(memberUpdateVo.getGiveCardTimeString()));
            }
            if (StringUtils.isNotBlank(memberUpdateVo.getExpireTimeString())) {
                member.setExpireTime(DateTimeUtils.convertStringToDate(memberUpdateVo.getExpireTimeString()));
            }
        }
    }

    @Override
    public Boolean checkMemberMobileIsExist(String mobile, Long id, Long shopId) {
        Member member = getMemberRepository().findByMobileAndShopId(mobile, shopId);
        return (member == null && id == null) || member == null || member.getId().equals(id);
    }

    @Override
    public Member findByMobileAndShopId(String mobile, Long shopId) {
        return getMemberRepository().findByMobileAndShopId(mobile, shopId);
    }

}