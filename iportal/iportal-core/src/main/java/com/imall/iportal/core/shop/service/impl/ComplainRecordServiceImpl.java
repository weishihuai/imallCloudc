package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.Pinyin4jUtil;
import com.imall.iportal.core.shop.entity.ComplainRecord;
import com.imall.iportal.core.shop.repository.ComplainRecordRepository;
import com.imall.iportal.core.shop.service.ComplainRecordService;
import com.imall.iportal.core.shop.vo.ComplainRecordSearchParam;
import org.apache.commons.lang3.StringUtils;
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
public class ComplainRecordServiceImpl extends AbstractBaseService<ComplainRecord, Long> implements ComplainRecordService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ComplainRecordRepository getComplainRecordRepository() {
		return (ComplainRecordRepository) baseRepository;
	}

	@Override
	@Transactional
	public ComplainRecord saveComplainRecord(ComplainRecord complainRecord) {
		if (StringUtils.isNotBlank(complainRecord.getGoodsNm())) {
			complainRecord.setGoodsPinyin(Pinyin4jUtil.getPinYinHeadChar(complainRecord.getGoodsNm()));
		}
		return save(complainRecord);
	}

	@Override
	public Page<ComplainRecord> query(Pageable pageable, ComplainRecordSearchParam searchParam) {
		return getComplainRecordRepository().query(pageable, searchParam);
	}
}