package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.MarriageStateCodeEnum;
import com.imall.iportal.core.main.entity.SysLogData;
import com.imall.iportal.core.main.log.LogDataVo;
import com.imall.iportal.core.main.log.LoggerUtils;
import com.imall.iportal.core.main.repository.SysLogDataRepository;
import com.imall.iportal.core.main.service.SysLogDataService;
import com.imall.iportal.core.main.vo.SysLogDataVo;
import com.imall.iportal.dicts.UserSexCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统 日志 数据(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysLogDataServiceImpl extends AbstractBaseService<SysLogData, Long> implements SysLogDataService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysLogDataRepository getSysLogDataRepository() {
		return (SysLogDataRepository) baseRepository;
	}

	@Override
	public List<LogDataVo> listByLogInnerCode(String logInnerCode) throws BusinessException {
		List<LogDataVo> resultList = new ArrayList<LogDataVo>();
		List<SysLogData> list = getSysLogDataRepository().findByLogInnerCode(logInnerCode);
		for(SysLogData logData:list){
			resultList.addAll(LoggerUtils.getBySysLogData(logData));
		}
		return resultList;
	}

	@Override
	public List<LogDataVo> findById(Long id) throws BusinessException, ParseException {
		List<LogDataVo> logDataVos = LoggerUtils.getBySysLogData(getSysLogDataRepository().findOne(id));
		List<LogDataVo> logDataVoArrayList = new ArrayList<>();
		for (LogDataVo logDataVo : logDataVos) {
			if((StringUtils.isBlank(logDataVo.getColumnAfterOperationValue()) && StringUtils.isBlank(logDataVo.getColumnBeforeOperationValue()))
					|| (StringUtils.isNotBlank(logDataVo.getColumnAfterOperationValue()) && logDataVo.getColumnAfterOperationValue().equals(logDataVo.getColumnBeforeOperationValue()))
					|| (StringUtils.isNotBlank(logDataVo.getColumnBeforeOperationValue()) && logDataVo.getColumnBeforeOperationValue().equals(logDataVo.getColumnAfterOperationValue()))) {
				continue;
			}
			LogDataVo dataVo = new LogDataVo();
			dataVo.setColumnName(logDataVo.getColumnName());
			dataVo.setColumnAfterOperationValue(formatValue(logDataVo.getColumnAfterOperationValue(),logDataVo.getColumnKey()));
			dataVo.setColumnBeforeOperationValue(formatValue(logDataVo.getColumnBeforeOperationValue(),logDataVo.getColumnKey()));
			logDataVoArrayList.add(dataVo);
		}
		return logDataVoArrayList;
	}

	@Override
	public Page<SysLogDataVo> query(Pageable pageable, String tableKey, Long objectId, String searchText) {
		return getSysLogDataRepository().query(pageable, tableKey, objectId, searchText);
	}

	private static String formatValue(String value, String columnKey) throws ParseException {

		switch (columnKey) {
			case "isEnable":
				value = BoolCodeEnum.fromCode(value)==BoolCodeEnum.YES?"启用":"停用";
				break;
			case "isDeleted":
				value = StringUtils.isNotBlank(value) ? BoolCodeEnum.fromCode(value).toName() : "";
				break;
			case "birthday":
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
				value = StringUtils.isNotBlank(value) ?DateTimeUtils.convertDateToString(sdf.parse(value)):"";
				break;
			case "graduationTime":
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
				value = StringUtils.isNotBlank(value) ?DateTimeUtils.convertDateToString(simpleDateFormat.parse(value)):"";
				break;
			case "joinWorkTime":
				SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
				value = StringUtils.isNotBlank(value) ?DateTimeUtils.convertDateToString(simpleDateFormat1.parse(value)):"";
				break;
			case "leaveTime":
				SimpleDateFormat leaveTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
				value = StringUtils.isNotBlank(value) ?DateTimeUtils.convertDateToString(leaveTime.parse(value)):"";
				break;
			case "entryJobTime":
				SimpleDateFormat entryJobTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
				value = StringUtils.isNotBlank(value) ?DateTimeUtils.convertDateToString(entryJobTime.parse(value)):"";
				break;
			case "marriageStatCode":
				value = StringUtils.isNotBlank(value) ? MarriageStateCodeEnum.fromCode(value).toName() : "";
				break;
			case "isPostsTrain":
				value = StringUtils.isNotBlank(value) ? BoolCodeEnum.fromCode(value).toName() : "";
				break;
			case "isDefaultAdmin":
				value = StringUtils.isNotBlank(value) ? BoolCodeEnum.fromCode(value).toName() : "";
				break;
			case "sex":
				value = StringUtils.isNotBlank(value) ? UserSexCodeEnum.fromCode(value).toName() : "";
				break;
			default:
				break;
		}
		return value;

	}


}