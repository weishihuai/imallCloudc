package com.imall.iportal.core.weshop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.Member;
import com.imall.iportal.core.shop.vo.WeChatUserNickNameUpdateVo;
import com.imall.iportal.core.weshop.entity.Fans;
import com.imall.iportal.core.weshop.entity.WeChatUser;
import com.imall.iportal.core.weshop.repository.WeChatUserRepository;
import com.imall.iportal.core.weshop.service.WeChatUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class WeChatUserServiceImpl extends AbstractBaseService<WeChatUser, Long> implements WeChatUserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private WeChatUserRepository getWeChatUserRepository() {
		return (WeChatUserRepository) baseRepository;
	}

	@Override
	public WeChatUser findByOpenId(String openId) {
		return getWeChatUserRepository().findByOpenId(openId);
	}

	@Override
	@Transactional
	public void updateNickName(WeChatUserNickNameUpdateVo weChatUserNickNameUpdateVo) {
		WeChatUser weChatUser = this.findOne(weChatUserNickNameUpdateVo.getId());
		if (weChatUser == null){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		weChatUser.setNickName(weChatUserNickNameUpdateVo.getNickName());
	}

	@Override
	public WeChatUser findByMobile(String mobile) {
		return getWeChatUserRepository().findByMobile(mobile);
	}

	@Override
	@Transactional
	public void updateMobile(Long id, String mobile) {
		WeChatUser temp = ServiceManager.weChatUserService.findByMobile(mobile);
		if (temp != null){
			if (temp.getId().equals(id)){
				return;
			}
			throw new BusinessException(ResGlobal.COMMON_ERROR, "号码【"+ mobile +"】已被其他用户绑定");
		}
		WeChatUser weChatUser = this.findOne(id);
		if (weChatUser == null){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "用户不存在");
		}
		weChatUser.setMobile(mobile);
		this.save(weChatUser);
		List<Fans> fansList = ServiceManager.fansService.findByWeChatUserId(id);
		for (Fans fans : fansList){
			fans.setMobile(mobile);
			Member member = ServiceManager.memberService.findByMobileAndShopId(mobile, fans.getShopId());
			if (member != null){
				fans.setMemberId(member.getId());
				fans.setIsMember(BoolCodeEnum.YES.toCode());
			}
			ServiceManager.fansService.save(fans);
		}
	}
}