package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.DrugCheckRecord;
import com.imall.iportal.core.shop.repository.DrugCheckRecordRepository;
import com.imall.iportal.core.shop.service.DrugCheckRecordService;
import com.imall.iportal.core.shop.vo.DrugCheckRecordPageVo;
import com.imall.iportal.core.shop.vo.DrugCheckRecordSaveVo;
import com.imall.iportal.core.shop.vo.DrugCheckRecordSearchParam;
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
public class DrugCheckRecordServiceImpl extends AbstractBaseService<DrugCheckRecord, Long> implements DrugCheckRecordService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private DrugCheckRecordRepository getDrugCheckRecordRepository() {
		return (DrugCheckRecordRepository) baseRepository;
	}

	@Override
	public void save(DrugCheckRecordSaveVo drugCheckRecordSaveVo) {
		DrugCheckRecord drugCheckRecord = new DrugCheckRecord();
		CommonUtil.copyProperties(drugCheckRecordSaveVo, drugCheckRecord);
		this.save(drugCheckRecord);
	}

	@Override
	public Page<DrugCheckRecordPageVo> query(Pageable pageable, DrugCheckRecordSearchParam drugCheckRecordSearchParam) {
		return this.getDrugCheckRecordRepository().query(pageable, drugCheckRecordSearchParam);
	}
}