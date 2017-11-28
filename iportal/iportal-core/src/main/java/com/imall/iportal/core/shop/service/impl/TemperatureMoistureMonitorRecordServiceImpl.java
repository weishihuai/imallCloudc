package com.imall.iportal.core.shop.service.impl;



import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.TemperatureMoistureMonitorRecord;
import com.imall.iportal.core.shop.repository.TemperatureMoistureMonitorRecordRepository;
import com.imall.iportal.core.shop.service.TemperatureMoistureMonitorRecordService;
import com.imall.iportal.core.shop.vo.TemperatureMoistureMonitorRecordPageVo;
import com.imall.iportal.core.shop.vo.TemperatureMoistureMonitorRecordSaveVo;
import com.imall.iportal.core.shop.vo.TemperatureMoistureMonitorRecordSearchParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class TemperatureMoistureMonitorRecordServiceImpl extends AbstractBaseService<TemperatureMoistureMonitorRecord, Long> implements TemperatureMoistureMonitorRecordService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private TemperatureMoistureMonitorRecordRepository getTemperatureMoistureMonitorRecordRepository() {
		return (TemperatureMoistureMonitorRecordRepository) baseRepository;
	}

	@Override
	@Transactional
	public TemperatureMoistureMonitorRecord save(TemperatureMoistureMonitorRecordSaveVo temperatureMoistureMonitorRecordSaveVo) {
		TemperatureMoistureMonitorRecord temperatureMoistureMonitorRecord = new TemperatureMoistureMonitorRecord();
		CommonUtil.copyProperties(temperatureMoistureMonitorRecordSaveVo, temperatureMoistureMonitorRecord);
		return this.save(temperatureMoistureMonitorRecord);
	}

	@Override
	public Page<TemperatureMoistureMonitorRecordPageVo> query(Pageable pageable, TemperatureMoistureMonitorRecordSearchParam temperatureMoistureMonitorRecordSearchParam) {
		return getTemperatureMoistureMonitorRecordRepository().query(pageable, temperatureMoistureMonitorRecordSearchParam);
	}
}