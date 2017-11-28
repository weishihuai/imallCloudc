package com.imall.iportal.core.platform.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.Pinyin4jUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.BusinessWayCodeEnum;
import com.imall.commons.dicts.EntTypeCodeEnum;
import com.imall.commons.dicts.GlobalExt;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.OrgUserParamsVoSaveValid;
import com.imall.iportal.core.main.vo.OrgUserParamsVo;
import com.imall.iportal.core.main.vo.SysUserShopVo;
import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.core.platform.repository.ShopRepository;
import com.imall.iportal.core.platform.service.ShopService;
import com.imall.iportal.core.platform.vo.*;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.service.impl.DateSerialGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
public class ShopServiceImpl extends AbstractBaseService<Shop, Long> implements ShopService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ShopRepository getShopRepository() {
		return (ShopRepository) baseRepository;
	}

	@Override
	public Page<ShopPageVo> queryShop(Pageable pageable, ShopSearchParam shopSearchParam) throws ParseException {
		Page<Shop> query = getShopRepository().query(pageable, shopSearchParam);
		List<ShopPageVo> shopPageVos = new ArrayList<>();
		for (Shop shop : query.getContent()) {
			ShopPageVo shopPageVo = CommonUtil.copyProperties(shop, new ShopPageVo());
			shopPageVo.setCreateTimeString(DateTimeUtils.convertDateToString(shop.getCreateDate()));
            //获取用户电话
            SysUserShopVo sysUserShopVo=ServiceManager.sysUserService.findByShopIdAndIsDefaultAdminAndOrgId(shop.getId(),BoolCodeEnum.YES.toCode(),shop.getOrgId());
            shopPageVo.setMobile(sysUserShopVo.getMobile());
			shopPageVos.add(shopPageVo);
		}
		return new PageImpl<ShopPageVo>(shopPageVos, new PageRequest(query.getNumber(), query.getSize()), query.getTotalElements());
	}

	@Override
	@Transactional
	public Long update(ShopUpdateVo shopUpdateVo) throws ParseException {
		Shop shop = findOne(shopUpdateVo.getId());
		if ( shop== null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		//保存门店
		Long id = save(buildShop(shopUpdateVo)).getId();

		//保存账号
		ServiceManager.sysUserService.updateMobileAndEmail(shopUpdateVo.getUserId(),shopUpdateVo.getMobile(),shopUpdateVo.getEmail());
		//保存资质文件
		for (ShopCertificatesFileUpdateVo supCerFileUpdVo : shopUpdateVo.getShopCertificatesFileVoList()) {
			supCerFileUpdVo.setShopId(id);
			ServiceManager.shopCertificatesFileService.updateByShopIdAndCertificatesType(supCerFileUpdVo);
		}
		return save(shop).getId();
	}



	private Shop buildShop(ShopUpdateVo shopUpdateVo) throws ParseException {
		Shop shop = findOne(shopUpdateVo.getId());
		shop.setEntNm(shopUpdateVo.getEntNm());
		shop.setPinyin(Pinyin4jUtil.getPinYinHeadChar(shopUpdateVo.getEntNm()));
		shop.setLegalRepresentativeMan(shopUpdateVo.getLegalRepresentativeMan());
		shop.setEntResponseMan(shopUpdateVo.getEntResponseMan());
		shop.setTaxRegisterCertNum(shopUpdateVo.getTaxRegisterCertNum());
		shop.setTaxRegisterCertNum(shopUpdateVo.getTaxRegisterCertNum());


		shop.setEntTypeCode(StringUtils.isNoneBlank(shopUpdateVo.getEntTypeCode())?EntTypeCodeEnum.fromCode(shopUpdateVo.getEntTypeCode()).toCode():null);
		shop.setIsMedicalInsuranceShop(StringUtils.isNoneBlank(shopUpdateVo.getIsMedicalInsuranceShop())?BoolCodeEnum.fromCode(shopUpdateVo.getIsMedicalInsuranceShop()).toCode():null);
		shop.setBusinessWay(StringUtils.isNoneBlank(shopUpdateVo.getBusinessWayCode()) ? BusinessWayCodeEnum.fromCode(shopUpdateVo.getBusinessWayCode()).toCode() : null);

		shop.setRegCapital(shopUpdateVo.getRegCapital());
		shop.setRegAddr(shopUpdateVo.getRegAddr());
		shop.setCompanyAddr(shopUpdateVo.getCompanyAddr());
		shop.setStorageAddr(shopUpdateVo.getStorageAddr());
		shop.setCompanyFax(shopUpdateVo.getCompanyFax());
		shop.setAnnualInspectionDate(StringUtils.isNotBlank(shopUpdateVo.getAnnualInspectionDateString())?DateTimeUtils.convertStringToDate(shopUpdateVo.getAnnualInspectionDateString()):null);
		shop.setCompanyBrief(shopUpdateVo.getCompanyBrief());
		shop.setBusinessRange(shopUpdateVo.getBusinessRange());
		shop.setCompanyTel(shopUpdateVo.getCompanyTel());
		shop.setIsEnable(BoolCodeEnum.fromCode(shopUpdateVo.getIsEnable()).toCode());
		return shop;
	}

	@Override
	@Transactional
	public Long save(ShopSaveVo shopSaveVo) throws ParseException {

		Shop shop = CommonUtil.copyProperties(shopSaveVo, new Shop());

		String orgAndShopCode=DateSerialGenerator.genSerialCode(DateSerialGenerator.SHOP_PREFIX);
		String orgName=shopSaveVo.getEntNm();
		//保存机构
		OrgUserParamsVo orgUserParamsVo = new OrgUserParamsVo();
		orgUserParamsVo.setOrgCode(orgAndShopCode);
		orgUserParamsVo.setOrgName(orgName);
		Long orgId = ServiceManager.sysOrgService.saveOrgAdminUserAuth(orgUserParamsVo);
		//保存门店
		shop.setOrgId(orgId);
		shop.setPinyin(Pinyin4jUtil.getPinYinHeadChar(shopSaveVo.getEntNm()));
		shop.setShopCode(orgAndShopCode);
		shop.setEntTypeCode(StringUtils.isNotBlank(shopSaveVo.getEntTypeCode())?EntTypeCodeEnum.fromCode(shopSaveVo.getEntTypeCode()).toCode():null);
		shop.setIsMedicalInsuranceShop(StringUtils.isNotBlank(shopSaveVo.getIsMedicalInsuranceShop())?BoolCodeEnum.fromCode(shopSaveVo.getIsMedicalInsuranceShop()).toCode():null);
		shop.setBusinessWay(StringUtils.isNotBlank(shopSaveVo.getBusinessWay())?BusinessWayCodeEnum.fromCode(shopSaveVo.getBusinessWay()).toCode():null);
		shop.setAnnualInspectionDate(StringUtils.isNotBlank(shopSaveVo.getAnnualInspectionDateString())?DateTimeUtils.convertStringToDate(shopSaveVo.getAnnualInspectionDateString()):null);
		shop.setIsEnable(BoolCodeEnum.YES.toCode());
		Long shopId = save(shop).getId();

		//保存账号
		OrgUserParamsVoSaveValid orgUserParamsVoSaveValid = new OrgUserParamsVoSaveValid();
		orgUserParamsVoSaveValid.setOrgCode(orgAndShopCode);
		orgUserParamsVoSaveValid.setOrgName(orgName);
		orgUserParamsVoSaveValid.setRealName(shopSaveVo.getEntNm());
		orgUserParamsVoSaveValid.setUserName(shopSaveVo.getUserName());
		orgUserParamsVoSaveValid.setSalt(shopSaveVo.getSalt());
		orgUserParamsVoSaveValid.setPassword(shopSaveVo.getPassword());
		orgUserParamsVoSaveValid.setEmail(shopSaveVo.getEmail());
		orgUserParamsVoSaveValid.setMobile(shopSaveVo.getMobile());
		orgUserParamsVoSaveValid.setShopId(shopId);
		orgUserParamsVoSaveValid.setSysMenuIdList(ServiceManager.sysRoleMenuService.findMenuIdByRoleId(GlobalExt.SHOP_DEFAULT_ADMIN_ROLE_ID));
		orgUserParamsVoSaveValid.setSysResourceIdList(ServiceManager.sysRolePermissionService.findResourceIdByRoleId(GlobalExt.SHOP_DEFAULT_ADMIN_ROLE_ID));

		orgUserParamsVoSaveValid.setOrgId(orgId);
		ServiceManager.sysOrgService.createOrgAdminUserAuth(orgUserParamsVoSaveValid);


		//保存资质文件
		for (ShopCertificatesFileSaveVo supCerFilSaveVo : shopSaveVo.getShopCertificatesFileVoList()) {
			supCerFilSaveVo.setShopId(shopId);
			ServiceManager.shopCertificatesFileService.saveShopCertificatesFile(supCerFilSaveVo);
		}

		//初始化销售分类数据
		ServiceManager.salesCategoryService.saveSalesCategoryForShopCreate(shopId);

		//初始化货位管理
		ServiceManager.storageSpaceService.saveForShopCreate(shopId);

		return shopId;
	}

	@Override
	public ShopDetailVo findById(Long id,Long orgId) {
		Shop shop = findOne(id);
		if (shop == null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		ShopDetailVo shopDetailVo = CommonUtil.copyProperties(shop, new ShopDetailVo());

		shopDetailVo.setEntType(StringUtils.isNotBlank(shop.getEntTypeCode())?EntTypeCodeEnum.fromCode(shop.getEntTypeCode()).toName():"");
		shopDetailVo.setAnnualInspectionDateString(DateTimeUtils.convertDateToString(shop.getAnnualInspectionDate()));
		shopDetailVo.setBusinessWay(StringUtils.isNotBlank(shop.getBusinessWay())?BusinessWayCodeEnum.fromCode(shop.getBusinessWay()).toName():"");
		shopDetailVo.setBusinessWayCode(shop.getBusinessWay());
		//获取资质文件
		shopDetailVo.setShopCertificatesFileVoList(ServiceManager.shopCertificatesFileService.ListShopCertificatesFilesList(shop.getId()));
		//获取用户信息
		SysUserShopVo sysUserShopVo=ServiceManager.sysUserService.findByShopIdAndIsDefaultAdminAndOrgId(shop.getId(),BoolCodeEnum.YES.toCode(),orgId);
		shopDetailVo.setUserId(sysUserShopVo.getId());
		shopDetailVo.setUserName(sysUserShopVo.getUserName());
		shopDetailVo.setEmail(sysUserShopVo.getEmail());
		shopDetailVo.setMobile(sysUserShopVo.getMobile());

		return shopDetailVo;
	}

	@Override
	@Transactional
	public Long updateShopState(Long id, String state) {
		Shop supplierDoc = findOne(id);
		if (supplierDoc == null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		supplierDoc.setIsEnable(BoolCodeEnum.fromCode(state).toCode());
		return save(supplierDoc).getId();
	}

	@Override
	public Shop findByOrgId(Long orgId) {
		return getShopRepository().findByOrgId(orgId);
	}
}