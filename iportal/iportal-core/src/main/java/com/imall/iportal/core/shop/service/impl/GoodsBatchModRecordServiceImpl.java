
package com.imall.iportal.core.shop.service.impl;



import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.shop.entity.GoodsBatchModRecord;
import com.imall.iportal.core.shop.repository.GoodsBatchModRecordRepository;
import com.imall.iportal.core.shop.service.GoodsBatchModRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsBatchModRecordServiceImpl extends AbstractBaseService<GoodsBatchModRecord, Long> implements GoodsBatchModRecordService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsBatchModRecordRepository getGoodsBatchModRecordRepository() {
		return (GoodsBatchModRecordRepository) baseRepository;
	}

}