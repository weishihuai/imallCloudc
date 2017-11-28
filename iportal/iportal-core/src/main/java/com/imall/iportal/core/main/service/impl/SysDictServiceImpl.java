package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDict;
import com.imall.iportal.core.main.repository.SysDictRepository;
import com.imall.iportal.core.main.service.SysDictService;
import com.imall.iportal.core.main.valid.SysDictSaveValid;
import com.imall.iportal.core.main.valid.SysDictUpdateValid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T_PT_SYS_DICT【数据字典】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysDictServiceImpl extends AbstractBaseService<SysDict, Long> implements SysDictService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysDictRepository getSysDictRepository() {
		return (SysDictRepository) baseRepository;
	}

	@Override
	public Page<SysDict> query(Pageable pageable, String dictNm, String dictType, String isAvailable) {
		BoolCodeEnum isAvailableEnum = StringUtils.isBlank(isAvailable) ? null : BoolCodeEnum.fromCode(isAvailable);
		return getSysDictRepository().query(pageable,dictNm,dictType,isAvailableEnum==null ? null : isAvailableEnum.toCode());
	}

	@Transactional
	@Override
	public SysDict save(SysDictSaveValid sysDictSaveValid) {
		return save(CommonUtil.copyProperties(sysDictSaveValid, new SysDict()));
	}

	@Transactional
	@Override
	public SysDict update(SysDictUpdateValid sysDictUpdateValid) {
		return save(CommonUtil.copyProperties(sysDictUpdateValid, new SysDict()));
	}

	@Transactional
	@Override
	public void deleteSysDict(List<Long> sysDictIds) {
		for (Long id:sysDictIds){
			List<String> existsDictItem = ServiceManager.sysDictItemService.findDictItemNmByDataDictId(id);
			if(!existsDictItem.isEmpty()){
				SysDict sysDict = findOne(id);
				String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.DICT_DELETE_RELATE_ERROR);
				throw new BusinessException(ResGlobal.DICT_DELETE_RELATE_ERROR,String.format(message,new Object[]{sysDict.getDictNm(), StringUtils.join(existsDictItem.toArray(),",")}));
			}
		}
		delete(sysDictIds);
	}

	@Override
	public SysDict findByDictType(String dictType) {
		List<SysDict> sysDictList = getSysDictRepository().findByDictType(dictType);
		return sysDictList.isEmpty() ? null : sysDictList.get(0);
	}
}