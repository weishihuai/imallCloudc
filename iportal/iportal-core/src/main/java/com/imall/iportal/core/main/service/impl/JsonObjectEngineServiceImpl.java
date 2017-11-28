package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.main.entity.JsonObjectEngine;
import com.imall.iportal.core.main.repository.JsonObjectEngineRepository;
import com.imall.iportal.core.main.service.JsonObjectEngineService;
import com.imall.iportal.dicts.JsonObjectTypeCodeEnum;
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
public class JsonObjectEngineServiceImpl extends AbstractBaseService<JsonObjectEngine, Long> implements JsonObjectEngineService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private JsonObjectEngineRepository getJsonObjectEngineRepository() {
		return (JsonObjectEngineRepository) baseRepository;
	}

	@Override
	public JsonObjectEngine getDataAsObject(JsonObjectTypeCodeEnum objectType, Long objectId) {
		return getJsonObjectEngineRepository().getDataAsObject(objectType.toCode(), objectId);
	}

	@Override
	public List<JsonObjectEngine> getDataAsList(JsonObjectTypeCodeEnum objectType, Long objectId) {
		return getJsonObjectEngineRepository().getDataAsList(objectType.toCode(), objectId);
	}

	@Transactional
	@Override
	public void saveOrUpdateObject(JsonObjectTypeCodeEnum objectType, Long objectId, String jsonObjectValue) {
		JsonObjectEngine jsonObject = getDataAsObject(objectType, objectId);
		if(jsonObject==null){
			jsonObject = new JsonObjectEngine();
			jsonObject.setJsonObjectId(objectId);
			jsonObject.setJsonObjectTypeCode(objectType.toCode());
		}
		jsonObject.setJsonObjectValueStr(jsonObjectValue);
		save(jsonObject);
	}

	@Transactional
	@Override
	public void delByObjectIdObjectTypeCode(JsonObjectTypeCodeEnum objectType, Long objectId) {
		getJsonObjectEngineRepository().delByObjectIdObjectTypeCode(objectType.toCode(), objectId);
	}
}