package com.imall.iportal.core.elasticsearch.provider;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.example.entity.SysDoc;
import com.imall.iportal.core.example.service.SysDocService;
import com.imall.iportal.core.elasticsearch.IIndexProvider;
import com.imall.iportal.core.elasticsearch.entity.SysDocEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SysDocIndexProvider implements IIndexProvider<SysDocEntity> {

    @Autowired
    private SysDocService sysDocService;

    @Override
    public SysDocEntity getEntity(Long id) {
        SysDoc sysDoc = sysDocService.findOne(id);
        if (sysDoc == null) {
            return null;
        }

        SysDocEntity entity = CommonUtil.copyProperties(sysDoc, new SysDocEntity());
        return entity;
    }

    @Override
    public IndexTypeCodeEnum actionType() {
        return IndexTypeCodeEnum.SYS_DOC;
    }
}
