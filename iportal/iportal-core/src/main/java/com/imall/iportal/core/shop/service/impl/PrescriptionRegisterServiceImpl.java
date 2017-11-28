package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.FileObjectTypeCodeEnum;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.PrescriptionDrugsTypeCodeEnum;
import com.imall.commons.dicts.PrescriptionRegisterStateEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysFileLib;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.valid.FileMngSaveValid;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.PrescriptionRegisterRepository;
import com.imall.iportal.core.shop.service.PrescriptionRegisterService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class PrescriptionRegisterServiceImpl extends AbstractBaseService<PrescriptionRegister, Long> implements PrescriptionRegisterService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private PrescriptionRegisterRepository getPrescriptionRegisterRepository() {
		return (PrescriptionRegisterRepository) baseRepository;
	}

	@Transactional
	@Override
	public PrescriptionRegister save(PrescriptionRegisterSaveVo prescriptionRegisterSaveVo) {
		PrescriptionRegister prescriptionRegister = new PrescriptionRegister();
		CommonUtil.copyProperties(prescriptionRegisterSaveVo, prescriptionRegister);
		prescriptionRegister.setPrescriptionRegisterState(PrescriptionRegisterStateEnum.WAIT_DISPENSING.toCode());
		this.save(prescriptionRegister);

		if(!CollectionUtils.isEmpty(prescriptionRegisterSaveVo.getFileVoList())){
			for(FileVo fileVo : prescriptionRegisterSaveVo.getFileVoList()){
				FileMngSaveValid fileMngSaveValid = new FileMngSaveValid();
				fileMngSaveValid.setObjectTypeCode(FileObjectTypeCodeEnum.PRESCRIPTION_REGISTER_ANNEX.toCode());
				fileMngSaveValid.setObjectId(prescriptionRegister.getId());
				fileMngSaveValid.setSysFileLibId(fileVo.getSysFileLibId());
				fileMngSaveValid.setFileId(fileVo.getFileId());
				ServiceManager.fileMngService.save(FileObjectTypeCodeEnum.PRESCRIPTION_REGISTER_ANNEX, prescriptionRegister.getId(), fileMngSaveValid);
			}
		}

		return prescriptionRegister;
	}

	@Override
	public PrescriptionRegisterDetailVo findById(Long shopId, Long id) {
		PrescriptionRegister prescriptionRegister = this.getPrescriptionRegisterRepository().findOne(shopId, id);
		if(prescriptionRegister==null){
			logger.error("处方登记详情不存在，shopId:" + shopId + ", id:" + id);
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"处方登记"}));
		}else{
			PrescriptionRegisterDetailVo prescriptionRegisterDetailVo = new PrescriptionRegisterDetailVo();
			CommonUtil.copyProperties(prescriptionRegister, prescriptionRegisterDetailVo);
			SysUser approveMan = ServiceManager.sysUserService.findOne(prescriptionRegister.getApproveManId());
			prescriptionRegisterDetailVo.setApproveManRealName(approveMan.getRealName());
			if(PrescriptionRegisterStateEnum.HAD_DISPENSING==PrescriptionRegisterStateEnum.fromCode(prescriptionRegister.getPrescriptionRegisterState())){
				SysUser repeatApproveMan = ServiceManager.sysUserService.findOne(prescriptionRegister.getRepeatApproveManId());
				prescriptionRegisterDetailVo.setRepeatApproveManRealName(repeatApproveMan.getRealName());
			}
			prescriptionRegisterDetailVo.setItemList(this.buildItem(shopId, prescriptionRegister.getOrderId()));

			//获取文件信息
			List<FileMng> fileMngList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.PRESCRIPTION_REGISTER_ANNEX, prescriptionRegister.getId());
			List<FileVo> fileVoList = new ArrayList<>();
 			for(FileMng fileMng : fileMngList){
				FileVo fileVo = new FileVo();
				fileVo.setFileId(fileMng.getFileId());
				fileVo.setSysFileLibId(fileMng.getSysFileLibId());
				fileVo.setFileUrl(FileSystemEngine.getFileSystem().getUrl(fileMng.getFileId()));
				fileVo.setFileTypeCode(fileMng.getFileTypeCode());

				SysFileLib fileLib = ServiceManager.sysFileLibService.findOne(fileMng.getSysFileLibId());
				fileVo.setFileNm(fileLib !=null ? fileLib.getFileNm():"");
				fileVoList.add(fileVo);
			}
			prescriptionRegisterDetailVo.setFileVoList(fileVoList);

			return prescriptionRegisterDetailVo;
		}
	}

	/**
	 * 获取订单项详情
	 * @param orderId
	 * @return
	 */
	public List<PrescriptionRegisterItemVo> buildItem(Long shopId, Long orderId){
		List<PrescriptionRegisterItemVo> itemList = new ArrayList<>();
		List<OrderItem> orderItemList = ServiceManager.orderItemService.findByOrderId(orderId);
		for(OrderItem orderItem : orderItemList){
			Goods goods = ServiceManager.goodsService.findOne(orderItem.getGoodsId());
			Long goodsId = goods.getId();
			switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())){
				case DRUG:	//药品
					GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsId);
					if(PrescriptionDrugsTypeCodeEnum.OTC!=PrescriptionDrugsTypeCodeEnum.fromCode(goodsDrug.getPrescriptionDrugsTypeCode())) {//处方药
						PrescriptionRegisterItemVo prescriptionRegisterItemVo = new PrescriptionRegisterItemVo();
						CommonUtil.copyProperties(orderItem, prescriptionRegisterItemVo);
						CommonUtil.copyProperties(goods, prescriptionRegisterItemVo);
						SysDictItem drugSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
						prescriptionRegisterItemVo.setDosageForm(drugSysDictItem.getDictItemNm());
						prescriptionRegisterItemVo.setApprovalNumber(goodsDrug.getApprovalNumber());
						itemList.add(prescriptionRegisterItemVo);
					}
					break;
				case OTHER:	//其他
					break;
				case CHINESE_MEDICINE_PIECES:	//中药饮片
					GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsId);
					if(PrescriptionDrugsTypeCodeEnum.OTC!=PrescriptionDrugsTypeCodeEnum.fromCode(goodsChineseMedicinePieces.getPrescriptionDrugsTypeCode())) {//处方药
						PrescriptionRegisterItemVo prescriptionRegisterItemVo = new PrescriptionRegisterItemVo();
						CommonUtil.copyProperties(orderItem, prescriptionRegisterItemVo);
						CommonUtil.copyProperties(goods, prescriptionRegisterItemVo);
						SysDictItem cmpSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
						prescriptionRegisterItemVo.setDosageForm(cmpSysDictItem.getDictItemNm());
						prescriptionRegisterItemVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
						prescriptionRegisterItemVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
						itemList.add(prescriptionRegisterItemVo);
					}
					break;
				case FOOD_HEALTH:	//食品保健品
					break;
				case DAILY_NECESSITIES:	//日用品
					break;
				case MEDICAL_INSTRUMENTS:	//医疗器械
					break;
				case COSMETIC:	//化妆品
					break;
			}
		}

		return itemList;
	}

	@Override
	@Transactional
	public PrescriptionRegister update(PrescriptionRegisterUpdateVo prescriptionRegisterUpdateVo) throws ParseException {
		PrescriptionRegister prescriptionRegister = this.getPrescriptionRegisterRepository().findOne(prescriptionRegisterUpdateVo.getShopId(), prescriptionRegisterUpdateVo.getId());
		if(prescriptionRegister==null){
			logger.error("处方登记不存在，shopId:" + prescriptionRegisterUpdateVo.getShopId() + ", id:" + prescriptionRegisterUpdateVo.getId());
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"处方登记"}));
		}else{
			CommonUtil.copyProperties(prescriptionRegisterUpdateVo, prescriptionRegister);
			this.update(prescriptionRegister);

			//添加新文件
			if(!CollectionUtils.isEmpty(prescriptionRegisterUpdateVo.getAddFileList())){
				for(FileVo fileVo : prescriptionRegisterUpdateVo.getAddFileList()){
					FileMngSaveValid fileMngSaveValid = new FileMngSaveValid();
					fileMngSaveValid.setObjectTypeCode(FileObjectTypeCodeEnum.PRESCRIPTION_REGISTER_ANNEX.toCode());
					fileMngSaveValid.setObjectId(prescriptionRegister.getId());
					fileMngSaveValid.setSysFileLibId(fileVo.getSysFileLibId());
					fileMngSaveValid.setFileId(fileVo.getFileId());
					ServiceManager.fileMngService.save(FileObjectTypeCodeEnum.PRESCRIPTION_REGISTER_ANNEX, prescriptionRegister.getId(), fileMngSaveValid);
				}
			}

			//删除旧文件
			if(!CollectionUtils.isEmpty(prescriptionRegisterUpdateVo.getDelFileList())){
				for(FileVo fileVo : prescriptionRegisterUpdateVo.getDelFileList()){
					ServiceManager.fileMngService.deleteBySysFileLibId(FileObjectTypeCodeEnum.PRESCRIPTION_REGISTER_ANNEX, fileVo.getSysFileLibId());
				}
			}

			return prescriptionRegister;
		}
	}

	@Override
	public Page<PrescriptionRegisterPageVo> query(Pageable pageable, PrescriptionRegisterSearchParam prescriptionRegisterSearchParam) {
		return this.getPrescriptionRegisterRepository().query(pageable, prescriptionRegisterSearchParam);
	}

	@Override
	@Transactional
	public PrescriptionRegister dispensingPrescription(PrescriptionDispensingVo prescriptionDispensingVo) {
		PrescriptionRegister prescriptionRegister = this.getPrescriptionRegisterRepository().findOne(prescriptionDispensingVo.getShopId(), prescriptionDispensingVo.getId());
		if(prescriptionRegister==null){
			logger.error("处方登记不存在，shopId:" + prescriptionDispensingVo.getShopId() + ", id:" + prescriptionDispensingVo.getId());
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"处方登记"}));
		}else{
			prescriptionRegister.setDispensingManName(prescriptionDispensingVo.getDispensingManName());
			prescriptionRegister.setRepeatApproveManId(prescriptionDispensingVo.getRepeatApproveManId());
			prescriptionRegister.setGrantDrugManName(prescriptionDispensingVo.getGrantDrugManName());
			prescriptionRegister.setDispensingDate(new Date());
			prescriptionRegister.setPrescriptionRegisterState(PrescriptionRegisterStateEnum.HAD_DISPENSING.toCode());
			this.update(prescriptionRegister);
			return prescriptionRegister;
		}
	}

	@Override
	public List<PrescriptionRegisterItemVo> listItemByOrderId(Long shopId, Long orderId) {
		return this.buildItem(shopId, orderId);
	}
}