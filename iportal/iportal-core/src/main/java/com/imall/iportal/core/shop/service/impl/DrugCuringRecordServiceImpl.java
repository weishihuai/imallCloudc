package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.DrugCuringRecord;
import com.imall.iportal.core.shop.repository.DrugCuringRecordRepository;
import com.imall.iportal.core.shop.service.DrugCuringRecordService;
import com.imall.iportal.core.shop.vo.DrugCuringRecordPageVo;
import com.imall.iportal.core.shop.vo.DrugCuringRecordSaveVo;
import com.imall.iportal.core.shop.vo.DrugCuringRecordSearchParam;
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
public class DrugCuringRecordServiceImpl extends AbstractBaseService<DrugCuringRecord, Long> implements DrugCuringRecordService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private DrugCuringRecordRepository getDrugCuringRecordRepository() {
		return (DrugCuringRecordRepository) baseRepository;
	}

	@Override
	public void save(DrugCuringRecordSaveVo drugCuringRecordSaveVo) {
		DrugCuringRecord drugCuringRecord = new DrugCuringRecord();
		CommonUtil.copyProperties(drugCuringRecordSaveVo, drugCuringRecord);
		this.save(drugCuringRecord);
	}

	@Override
	public Page<DrugCuringRecordPageVo> query(Pageable pageable, DrugCuringRecordSearchParam drugCuringRecordSearchParam) {
		return this.getDrugCuringRecordRepository().query(pageable, drugCuringRecordSearchParam);
	}
}