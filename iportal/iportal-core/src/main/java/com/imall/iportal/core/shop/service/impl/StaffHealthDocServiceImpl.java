package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.StaffHealthDoc;
import com.imall.iportal.core.shop.repository.StaffHealthDocRepository;
import com.imall.iportal.core.shop.service.StaffHealthDocService;
import com.imall.iportal.core.shop.vo.StaffHealthDocDetailVo;
import com.imall.iportal.core.shop.vo.StaffHealthDocPageVo;
import com.imall.iportal.core.shop.vo.StaffHealthDocSearchParam;
import com.imall.iportal.dicts.UserSexCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class StaffHealthDocServiceImpl extends AbstractBaseService<StaffHealthDoc, Long> implements StaffHealthDocService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private StaffHealthDocRepository getStaffHealthDocRepository() {
		return (StaffHealthDocRepository) baseRepository;
	}

	@Override
	public StaffHealthDocDetailVo findById(Long id,Long shopId) {
		StaffHealthDoc staffHealthDoc = findOne(id);
		if (staffHealthDoc == null || !staffHealthDoc.getShopId().equals(shopId)) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}

		StaffHealthDocDetailVo staffHealthDocDetailVo = new StaffHealthDocDetailVo();
		staffHealthDocDetailVo.setStaffHealthDocInfDetailVoList(ServiceManager.staffHealthDocInfService.findByStaffHealthDocId(id));
		SysUser sysUser = ServiceManager.sysUserService.findOne(staffHealthDoc.getStaffId());
		staffHealthDocDetailVo.setId(id);
		staffHealthDocDetailVo.setUserName(sysUser.getUserName());
		staffHealthDocDetailVo.setRealName(sysUser.getRealName());
		staffHealthDocDetailVo.setSex(StringUtils.isNotBlank(sysUser.getSex())?UserSexCodeEnum.fromCode(sysUser.getSex()).toName():null);
		staffHealthDocDetailVo.setBirthdayString(sysUser.getBirthdayString());
		staffHealthDocDetailVo.setEntryJobTimeString(sysUser.getEntryJobTimeString());
		staffHealthDocDetailVo.setState(sysUser.getIsEnable());
		return staffHealthDocDetailVo;
	}

	@Override
	public Page<StaffHealthDocPageVo> queryStaffHealthDoc(Pageable pageable, StaffHealthDocSearchParam staffHealthDocSearchParam) throws ParseException {
		Page<StaffHealthDoc> staffHealthDocPage = getStaffHealthDocRepository().query(pageable, staffHealthDocSearchParam);
		List<StaffHealthDocPageVo> staffHealthDocPageVos = new ArrayList<>();
		for (StaffHealthDoc staffHealthDoc : staffHealthDocPage.getContent()) {
			StaffHealthDocPageVo staffHealthDocPageVo = CommonUtil.copyProperties(staffHealthDoc, new StaffHealthDocPageVo());
			SysUser sysUser = ServiceManager.sysUserService.findOne(staffHealthDoc.getStaffId());
			staffHealthDocPageVo.setUserName(sysUser.getUserName());
			staffHealthDocPageVo.setSex(StringUtils.isNotBlank(sysUser.getSex())?UserSexCodeEnum.fromCode(sysUser.getSex()).toName():null);
			staffHealthDocPageVo.setRealName(sysUser.getRealName());
			staffHealthDocPageVo.setBirthdayString(sysUser.getBirthdayString());
			staffHealthDocPageVo.setEntryJobTimeString(sysUser.getEntryJobTimeString());
			staffHealthDocPageVo.setIsEnable(sysUser.getIsEnable());
			staffHealthDocPageVos.add(staffHealthDocPageVo);
		}
		return new PageImpl<StaffHealthDocPageVo>(staffHealthDocPageVos, pageable, staffHealthDocPage.getTotalElements());
	}

	@Override
	@Transactional
	public void saveStaffHealthDoc(StaffHealthDoc staffHealthDoc) {
		save(staffHealthDoc);
	}
}