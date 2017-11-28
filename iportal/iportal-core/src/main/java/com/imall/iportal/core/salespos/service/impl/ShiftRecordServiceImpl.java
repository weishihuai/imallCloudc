package com.imall.iportal.core.salespos.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.PayWayTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.salespos.entity.ShiftRecord;
import com.imall.iportal.core.salespos.repository.ShiftRecordRepository;
import com.imall.iportal.core.salespos.service.ShiftRecordService;
import com.imall.iportal.core.salespos.vo.ShiftRecordVo;
import com.imall.iportal.core.shop.vo.ShiftRecordSearchParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 交班 记录(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class ShiftRecordServiceImpl extends AbstractBaseService<ShiftRecord, Long> implements ShiftRecordService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ShiftRecordRepository getShiftRecordRepository() {
		return (ShiftRecordRepository) baseRepository;
	}

	@Override
	public Page<ShiftRecordVo> findPage(Pageable pageable, ShiftRecordSearchParam searchParam) {
		Page<ShiftRecord> page = getShiftRecordRepository().query(pageable, searchParam.getShopId(), searchParam.getPosManName(), searchParam.getFormCreateDate(), searchParam.getToCreateDate());
		List<ShiftRecordVo> list = new ArrayList<>();
		for(ShiftRecord record: page.getContent()){
			ShiftRecordVo recordVo = new ShiftRecordVo();
			CommonUtil.copyProperties(record, recordVo);
			SysUser posManUser = ServiceManager.sysUserService.findOne(record.getPosMan());
			recordVo.setPosManName(posManUser.getRealName());
			if(record.getSucceedWhoId()!=null){
				SysUser succeedWhoUser = ServiceManager.sysUserService.findOne(record.getSucceedWhoId());
				if(succeedWhoUser!=null){
					recordVo.setSucceedWhoName(succeedWhoUser.getRealName());
				}
			}
			list.add(recordVo);
		}
		return new PageImpl<ShiftRecordVo>(list, pageable, page.getTotalElements());
	}

	@Transactional
	@Override
	public ShiftRecord saveRecord(Long shopId, Long posMan, Long succeedWhoId, Date succeedTime) {
		ShiftRecord dbRecord;
		//查找之前的交班记录
		if(succeedWhoId==null){
			dbRecord = findLastNotShift(shopId, posMan);
			if(dbRecord!=null){
				return dbRecord;
			}
		}
		else {
			dbRecord = getShiftRecordRepository().findByShopIdAndPosManSucceedWhoIdAndNotShift(shopId, posMan, succeedWhoId);
			if(dbRecord!=null){
				return dbRecord;
			}
		}

		ShiftRecord record = new ShiftRecord();
		record.setSucceedWhoId(succeedWhoId);
		record.setShopId(shopId);
		record.setPosMan(posMan);
		record.setSucceedTime(succeedTime);
		record.setShiftTime(null);
		record.setReceiptAmount(0d);
		record.setReturnedPurchaseAmount(0d);
		record.setAddUpAmount(0d);
		record.setCashAmount(0d);
		record.setBankCardAmount(0d);
		record.setWechatAmount(0d);
		record.setAlipayAmount(0d);
		record.setMedicalInsuranceAmount(0d);
		record.setOrderQuantity(0L);
		record.setSucceedReadyAmount(0d);
		record.setKeepReadyAmount(0d);
		record.setHandoverCashAmount(0d);
		record.setIsHasShift(BoolCodeEnum.NO.toCode());
		return save(record);
	}

	@Override
	public ShiftRecord findLastNotShift(Long shopId, Long posMan){
		List<ShiftRecord> list = getShiftRecordRepository().findByShopIdAndPosManAndNotShift(shopId, posMan);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public ShiftRecordVo findReadyShiftByShopIdAndPosMan(Long shopId, Long posMan) {
		ShiftRecord record = findLastNotShift(shopId, posMan);

		ShiftRecordVo recordVo = new ShiftRecordVo();
		CommonUtil.copyProperties(record, recordVo);
		SysUser posManUser = ServiceManager.sysUserService.findOne(posMan);
		recordVo.setPosManName(posManUser.getRealName());
		if(record.getSucceedWhoId()!=null){
			SysUser succeedWhoUser = ServiceManager.sysUserService.findOne(record.getSucceedWhoId());
			if(succeedWhoUser!=null){
				recordVo.setSucceedWhoName(succeedWhoUser.getRealName());
			}
		}
		recordVo.setShiftTime(new Date());
		//收款金额
		Double receiptAmount = ServiceManager.orderService.sumOrderTotalAmountByShopIdAndPosMan(shopId, posMan, recordVo.getSucceedTime(), recordVo.getShiftTime());
		//退货金额
		Double returnedPurchaseAmount = ServiceManager.orderService.sumOrderReturnedPurchaseAmountByShopIdAndPosMan(shopId, posMan, recordVo.getSucceedTime(), recordVo.getShiftTime());
		//现金收款
		Double cashTotalAmount = ServiceManager.orderService.sumOrderPayWayAmountByShopIdAndPosMan(shopId, posMan, recordVo.getSucceedTime(), recordVo.getShiftTime(), PayWayTypeCodeEnum.CASH_PAY);
		//找零
		Double returnSmallAmount = ServiceManager.orderService.sumOrderReturnSmallAmountByShopIdAndPosMan(shopId, posMan, recordVo.getSucceedTime(), recordVo.getShiftTime());
		//现金 = 现金收款 - 现金找零
		Double cashAmount = BigDecimalUtil.subtract(cashTotalAmount, returnSmallAmount);
		//银行卡
		Double bankCardAmount = ServiceManager.orderService.sumOrderPayWayAmountByShopIdAndPosMan(shopId, posMan, recordVo.getSucceedTime(), recordVo.getShiftTime(), PayWayTypeCodeEnum.BANK_CARD_PAY);
		//微信
		Double wechatAmount = ServiceManager.orderService.sumOrderPayWayAmountByShopIdAndPosMan(shopId, posMan, recordVo.getSucceedTime(), recordVo.getShiftTime(), PayWayTypeCodeEnum.WEBCHAT_PAY);
		//支付宝
		Double alipayAmount = ServiceManager.orderService.sumOrderPayWayAmountByShopIdAndPosMan(shopId, posMan, recordVo.getSucceedTime(), recordVo.getShiftTime(), PayWayTypeCodeEnum.ALIPAY_PAY);
		//医保
		Double medicalInsuranceAmount = ServiceManager.orderService.sumOrderMedicalInsuranceAmountByShopIdAndPosMan(shopId, posMan, recordVo.getSucceedTime(), recordVo.getShiftTime());
		//订单数量
		Long orderQuantity = ServiceManager.orderService.sumOrderTotalQuantityByShopIdAndPosMan(shopId, posMan, recordVo.getSucceedTime(), recordVo.getShiftTime());

		recordVo.setReceiptAmount(receiptAmount);
		recordVo.setReturnedPurchaseAmount(returnedPurchaseAmount);
		recordVo.setAddUpAmount(BigDecimalUtil.subtract(receiptAmount, returnedPurchaseAmount));
		recordVo.setCashAmount(cashAmount);
		recordVo.setBankCardAmount(bankCardAmount);
		recordVo.setWechatAmount(wechatAmount);
		recordVo.setAlipayAmount(alipayAmount);
		recordVo.setMedicalInsuranceAmount(medicalInsuranceAmount);
		recordVo.setOrderQuantity(orderQuantity);
		return recordVo;
	}

	@Transactional
	@Override
	public void updateRecord(Long id, Long shopId, Long posMan, Date shiftTime, Double succeedReadyAmount, Double keepReadyAmount, Double handoverCashAmount) {
		ShiftRecord dbRecord = getShiftRecordRepository().findByIdShopIdAndPosMan(id, shopId, posMan); //为了数据安全， 3个字段确认一条记录
        //收款金额
		Double receiptAmount = ServiceManager.orderService.sumOrderTotalAmountByShopIdAndPosMan(shopId, posMan, dbRecord.getSucceedTime(), shiftTime);
		//退货金额
		Double returnedPurchaseAmount = ServiceManager.orderService.sumOrderReturnedPurchaseAmountByShopIdAndPosMan(shopId, posMan, dbRecord.getSucceedTime(), shiftTime);
		//合计金额 = 收款金额 - 退货金额
		Double addUpAmount = BigDecimalUtil.subtract(receiptAmount, returnedPurchaseAmount);
		//现金收款
		Double cashTotalAmount = ServiceManager.orderService.sumOrderPayWayAmountByShopIdAndPosMan(shopId, posMan, dbRecord.getSucceedTime(), shiftTime, PayWayTypeCodeEnum.CASH_PAY);
		//找零
		Double returnSmallAmount = ServiceManager.orderService.sumOrderReturnSmallAmountByShopIdAndPosMan(shopId, posMan, dbRecord.getSucceedTime(), shiftTime);
		//现金 = 现金收款 - 现金找零 - 退货金额
		Double cashAmount = BigDecimalUtil.subtract(BigDecimalUtil.subtract(cashTotalAmount, returnSmallAmount), returnedPurchaseAmount);
		//银行卡
		Double bankCardAmount = ServiceManager.orderService.sumOrderPayWayAmountByShopIdAndPosMan(shopId, posMan, dbRecord.getSucceedTime(), shiftTime, PayWayTypeCodeEnum.BANK_CARD_PAY);
		//微信
		Double wechatAmount = ServiceManager.orderService.sumOrderPayWayAmountByShopIdAndPosMan(shopId, posMan, dbRecord.getSucceedTime(), shiftTime, PayWayTypeCodeEnum.WEBCHAT_PAY);
		//支付宝
		Double alipayAmount = ServiceManager.orderService.sumOrderPayWayAmountByShopIdAndPosMan(shopId, posMan, dbRecord.getSucceedTime(), shiftTime, PayWayTypeCodeEnum.ALIPAY_PAY);
		//医保
		Double medicalInsuranceAmount = ServiceManager.orderService.sumOrderMedicalInsuranceAmountByShopIdAndPosMan(shopId, posMan, dbRecord.getSucceedTime(), shiftTime);
		//订单数量
		Long orderQuantity = ServiceManager.orderService.sumOrderTotalQuantityByShopIdAndPosMan(shopId, posMan, dbRecord.getSucceedTime(), shiftTime);

		dbRecord.setReceiptAmount(receiptAmount);
		dbRecord.setReturnedPurchaseAmount(returnedPurchaseAmount);
		dbRecord.setAddUpAmount(addUpAmount);
		dbRecord.setCashAmount(cashAmount);
		dbRecord.setBankCardAmount(bankCardAmount);
		dbRecord.setWechatAmount(wechatAmount);
		dbRecord.setAlipayAmount(alipayAmount);
		dbRecord.setMedicalInsuranceAmount(medicalInsuranceAmount);
		dbRecord.setOrderQuantity(orderQuantity);
		dbRecord.setSucceedReadyAmount(succeedReadyAmount);
		dbRecord.setKeepReadyAmount(keepReadyAmount);
		dbRecord.setHandoverCashAmount(handoverCashAmount);
		dbRecord.setIsHasShift(BoolCodeEnum.YES.toCode());
		dbRecord.setShiftTime(shiftTime);
		save(dbRecord);
	}
}