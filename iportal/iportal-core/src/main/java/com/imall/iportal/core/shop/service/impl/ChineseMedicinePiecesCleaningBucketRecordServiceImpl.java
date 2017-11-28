package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.entity.ChineseMedicinePiecesCleaningBucketRecord;
import com.imall.iportal.core.shop.repository.ChineseMedicinePiecesCleaningBucketRecordRepository;
import com.imall.iportal.core.shop.service.ChineseMedicinePiecesCleaningBucketRecordService;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesCleaningBucketRecordPageVo;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesCleaningBucketRecordSearchParam;
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
public class ChineseMedicinePiecesCleaningBucketRecordServiceImpl extends AbstractBaseService<ChineseMedicinePiecesCleaningBucketRecord, Long> implements ChineseMedicinePiecesCleaningBucketRecordService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ChineseMedicinePiecesCleaningBucketRecordRepository getChineseMedicinePiecesCleaningBucketRecordRepository() {
		return (ChineseMedicinePiecesCleaningBucketRecordRepository) baseRepository;
	}

	@Override
	public Page<ChineseMedicinePiecesCleaningBucketRecordPageVo> queryChineseMedicinePiecesCleaningBucketRecord(Pageable pageable, ChineseMedicinePiecesCleaningBucketRecordSearchParam chineseMedicinePiecesCleaningBucketRecordSearchParam) throws ParseException {
		Page<ChineseMedicinePiecesCleaningBucketRecord> chineseMedicinePiecesCleaningBucketRecord = getChineseMedicinePiecesCleaningBucketRecordRepository().query(pageable, chineseMedicinePiecesCleaningBucketRecordSearchParam);
		List<ChineseMedicinePiecesCleaningBucketRecordPageVo> chineseMedicinePiecesCleaningBucketRecordVos = new ArrayList<>();
		for (ChineseMedicinePiecesCleaningBucketRecord chineseMedicinePiecesCleaningBucketRecord1 : chineseMedicinePiecesCleaningBucketRecord.getContent()) {
			ChineseMedicinePiecesCleaningBucketRecordPageVo chineseMedicinePiecesCleaningBucketRecordVo = CommonUtil.copyProperties(chineseMedicinePiecesCleaningBucketRecord1, new ChineseMedicinePiecesCleaningBucketRecordPageVo());

			chineseMedicinePiecesCleaningBucketRecordVo.setCleaningBucketDateString(chineseMedicinePiecesCleaningBucketRecordVo.getCleaningBucketDateString());
			chineseMedicinePiecesCleaningBucketRecordVo.setProduceTimeString(chineseMedicinePiecesCleaningBucketRecordVo.getProduceTimeString());
			SysUser sysUser = ServiceManager.sysUserService.findOne(chineseMedicinePiecesCleaningBucketRecord1.getApproveManId());
			chineseMedicinePiecesCleaningBucketRecordVo.setApproveManName(sysUser.getRealName());

			SysDictItem drugSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(chineseMedicinePiecesCleaningBucketRecord1.getDosageForm());
			chineseMedicinePiecesCleaningBucketRecordVo.setDosageForm(drugSysDictItem.getDictItemNm());
			chineseMedicinePiecesCleaningBucketRecordVo.setValidDateString(chineseMedicinePiecesCleaningBucketRecord1.getValidDateString());
			chineseMedicinePiecesCleaningBucketRecordVos.add(chineseMedicinePiecesCleaningBucketRecordVo);

		}
		return new PageImpl<ChineseMedicinePiecesCleaningBucketRecordPageVo>(chineseMedicinePiecesCleaningBucketRecordVos, pageable, chineseMedicinePiecesCleaningBucketRecord.getTotalElements());
	}

	@Override
	public Long saveChineseMedicinePiecesCleaningBucketRecord(ChineseMedicinePiecesCleaningBucketRecord chineseMedicinePiecesCleaningBucketRecord) throws ParseException {
		return save(chineseMedicinePiecesCleaningBucketRecord).getId();
	}
}