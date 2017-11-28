package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDict;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.repository.SysDictItemRepository;
import com.imall.iportal.core.main.service.SysDictItemService;
import com.imall.iportal.core.main.valid.SysDictItemSaveValid;
import com.imall.iportal.core.main.valid.SysDictItemUpdateValid;
import com.imall.iportal.core.shop.vo.SysDictItemSimpleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * T_PT_SYS_DICT_ITEM【数据字典项】(服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysDictItemServiceImpl extends AbstractBaseService<SysDictItem, Long> implements SysDictItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private SysDictItemRepository getSysDictItemRepository() {
        return (SysDictItemRepository) baseRepository;
    }

    @Override
    public Page<SysDictItem> query(Pageable pageable, String dictItemNm, String dictItemCode, Long dataDictId) {
        return getSysDictItemRepository().query(pageable, dictItemNm, dictItemCode, dataDictId);
    }

    @Transactional
    @Override
    public SysDictItem save(SysDictItemSaveValid sysDictItemSaveValid) {
        return save(CommonUtil.copyProperties(sysDictItemSaveValid, new SysDictItem()));
    }

    @Transactional
    @Override
    public SysDictItem update(SysDictItemUpdateValid sysDictItemUpdateValid) {
        return save(CommonUtil.copyProperties(sysDictItemUpdateValid, new SysDictItem()));
    }

    @Override
    public List<String> findDictItemNmByDataDictId(Long dataDictId) {
        return getSysDictItemRepository().findDictItemNmByDataDictId(dataDictId);
    }

    @Override
    public List<SysDictItem> findByDataDictId(Long dataDictId) {
        return getSysDictItemRepository().findByDataDictId(dataDictId);
    }

    @Override
    public SysDictItem findByDictItemCode(String dictItemCode) {
        return getSysDictItemRepository().findByDictItemCode(dictItemCode);
    }

    @Override
    public List<SysDictItem> findByDictType(String dictType) {
        SysDict sysDict = ServiceManager.sysDictService.findByDictType(dictType);
        List<SysDictItem> list = new ArrayList<>();
        if (sysDict == null) {
            return list;
        }
        return findByDataDictId(sysDict.getId());
    }

    @Override
    public List<SysDictItemSimpleVo> findByAvailableAndDictType(String dictType) {
        SysDict sysDict = ServiceManager.sysDictService.findByDictType(dictType);
        List<SysDictItemSimpleVo> list = new ArrayList<>();
        if (sysDict == null) {
            return list;
        }
        List<SysDictItem> sysDictItems = getSysDictItemRepository().findByIsAvailableAndDataDictId(BoolCodeEnum.YES.toCode(), sysDict.getId());
        if (sysDictItems != null) {
            for (SysDictItem sysDictItem : sysDictItems) {
                list.add(CommonUtil.copyProperties(sysDictItem, new SysDictItemSimpleVo()));
            }
        }
        return list;
    }


}