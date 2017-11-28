package com.imall.iportal.core.main.service;


import com.imall.commons.base.service.IBaseService;
import com.imall.iportal.core.main.entity.JsonObjectEngine;
import com.imall.iportal.dicts.JsonObjectTypeCodeEnum;

import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface JsonObjectEngineService extends IBaseService<JsonObjectEngine, Long> {

    JsonObjectEngine getDataAsObject(JsonObjectTypeCodeEnum objectType, Long objectId);

    List<JsonObjectEngine> getDataAsList(JsonObjectTypeCodeEnum objectType, Long objectId);

    void saveOrUpdateObject(JsonObjectTypeCodeEnum objectType, Long objectId, String jsonObjectValue);

    void delByObjectIdObjectTypeCode(JsonObjectTypeCodeEnum objectType, Long objectId);

}
