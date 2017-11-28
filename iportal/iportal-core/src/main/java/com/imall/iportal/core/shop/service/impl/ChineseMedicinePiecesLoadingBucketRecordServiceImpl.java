package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.entity.ChineseMedicinePiecesLoadingBucketRecord;
import com.imall.iportal.core.shop.repository.ChineseMedicinePiecesLoadingBucketRecordRepository;
import com.imall.iportal.core.shop.service.ChineseMedicinePiecesLoadingBucketRecordService;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesLoadingBucketRecordPageVo;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesLoadingBucketRecordSearchParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class ChineseMedicinePiecesLoadingBucketRecordServiceImpl extends AbstractBaseService<ChineseMedicinePiecesLoadingBucketRecord, Long> implements ChineseMedicinePiecesLoadingBucketRecordService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ChineseMedicinePiecesLoadingBucketRecordRepository getChineseMedicinePiecesLoadingBucketRecordRepository() {
		return (ChineseMedicinePiecesLoadingBucketRecordRepository) baseRepository;
	}

	@Override
	public Page<ChineseMedicinePiecesLoadingBucketRecordPageVo> queryChineseMedicinePiecesLoadingBucketRecord(Pageable pageable, ChineseMedicinePiecesLoadingBucketRecordSearchParam chineseMedicinePiecesLoadingBucketRecordSearchParam) throws ParseException {
		Page<ChineseMedicinePiecesLoadingBucketRecord> chineseMedicinePiecesLoadingBucketRecord = getChineseMedicinePiecesLoadingBucketRecordRepository().query(pageable, chineseMedicinePiecesLoadingBucketRecordSearchParam);
		List<ChineseMedicinePiecesLoadingBucketRecordPageVo> chineseMedicinePiecesLoadingBucketRecordVos = new ArrayList<>();
		for (ChineseMedicinePiecesLoadingBucketRecord chineseMedicinePiecesLoadingBucketRecord1 : chineseMedicinePiecesLoadingBucketRecord.getContent()) {
			ChineseMedicinePiecesLoadingBucketRecordPageVo chineseMedicinePiecesLoadingBucketRecordVo = CommonUtil.copyProperties(chineseMedicinePiecesLoadingBucketRecord1, new ChineseMedicinePiecesLoadingBucketRecordPageVo());
			chineseMedicinePiecesLoadingBucketRecordVo.setLoadingBucketDateString(chineseMedicinePiecesLoadingBucketRecord1.getLoadingBucketDateString());
			SysUser sysUser = ServiceManager.sysUserService.findOne(chineseMedicinePiecesLoadingBucketRecord1.getApproveManId());
			chineseMedicinePiecesLoadingBucketRecordVo.setApproveManName(sysUser.getRealName());
			chineseMedicinePiecesLoadingBucketRecordVo.setProduceTimeString(chineseMedicinePiecesLoadingBucketRecord1.getProduceDateString());
			chineseMedicinePiecesLoadingBucketRecordVo.setValidDateString(chineseMedicinePiecesLoadingBucketRecordVo.getValidDateString());
			chineseMedicinePiecesLoadingBucketRecordVos.add(chineseMedicinePiecesLoadingBucketRecordVo);

		}
		return new PageImpl<ChineseMedicinePiecesLoadingBucketRecordPageVo>(chineseMedicinePiecesLoadingBucketRecordVos, pageable, chineseMedicinePiecesLoadingBucketRecord.getTotalElements());
	}

	@Override
	public Long saveChineseMedicinePiecesLoadingBucketRecord(ChineseMedicinePiecesLoadingBucketRecord chineseMedicinePiecesLoadingBucketRecord) throws ParseException {
		return save(chineseMedicinePiecesLoadingBucketRecord).getId();
	}
}