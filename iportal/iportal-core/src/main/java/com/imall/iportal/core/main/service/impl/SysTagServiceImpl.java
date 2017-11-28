package com.imall.iportal.core.main.service.impl;



import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.valid.SysTagSaveValid;
import com.imall.iportal.core.main.valid.SysTagUpdateValid;
import org.apache.shiro.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.main.entity.SysTag;
import com.imall.iportal.core.main.repository.SysTagRepository;
import com.imall.iportal.core.main.service.SysTagService;
import com.imall.commons.base.service.impl.AbstractBaseService;

import java.util.List;

/**
 * 标签(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysTagServiceImpl extends AbstractBaseService<SysTag, Long> implements SysTagService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysTagRepository getSysTagRepository() {
		return (SysTagRepository) baseRepository;
	}

	@Override
	public List<SysTag> findByTagTypeCodeSysOrgId(String tagTypeCode, Long sysOrgId) {
		return getSysTagRepository().findByTagTypeCodeAndSysOrgId(tagTypeCode, sysOrgId);
	}

	@Override
	public SysTag findByTagTypeCodeTagValueSysOrgId(String tagTypeCode, String tagValue, Long sysOrgId) {
		List<SysTag> sysTagList = getSysTagRepository().findByTagTypeCodeAndTagValueAndSysOrgId(tagTypeCode, tagValue, sysOrgId);
		return (sysTagList == null || sysTagList.isEmpty()) ? null : sysTagList.get(0);
	}

	private SysTag saveSysTag(SysTag sysTag) {
		Assert.isTrue(!(sysTag == null || sysTag.getTagTypeCode() == null || sysTag.getTagValue() == null || sysTag.getSysOrgId() == null),"参数不正确");

		SysTag dbSysTag = findByTagTypeCodeTagValueSysOrgId(sysTag.getTagTypeCode(), sysTag.getTagValue(), sysTag.getSysOrgId());
		if (dbSysTag != null) {
			throw new BusinessException(ResGlobal.ERRORS_IDSF_ALL, sysTag.getTagTypeCode() +" "+ sysTag.getTagValue() +" 已存在.");
		}
		return save(sysTag);
	}

	@Transactional
	@Override
	public SysTag save(SysTagSaveValid sysTagSaveValid) {
		return saveSysTag(CommonUtil.copyProperties(sysTagSaveValid, new SysTag()));
	}

	@Transactional
	@Override
	public SysTag update(SysTagUpdateValid sysTagUpdateValid) {
		return saveSysTag(CommonUtil.copyProperties(sysTagUpdateValid, new SysTag()));
	}

}