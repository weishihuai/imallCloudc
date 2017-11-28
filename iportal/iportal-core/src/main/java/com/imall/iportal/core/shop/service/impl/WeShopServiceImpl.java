package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.DeliveryTypeCodeEnum;
import com.imall.commons.dicts.FileObjectTypeCodeEnum;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.ESUtils;
import com.imall.iportal.core.elasticsearch.entity.WeShopEntity;
import com.imall.iportal.core.elasticsearch.utils.SearchUtils;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.main.entity.SysZone;
import com.imall.iportal.core.main.vo.FileMngSimpleVo;
import com.imall.iportal.core.main.vo.FileMngVo;
import com.imall.iportal.core.shop.entity.WeShop;
import com.imall.iportal.core.shop.repository.WeShopRepository;
import com.imall.iportal.core.shop.service.WeShopService;
import com.imall.iportal.core.shop.vo.WeShopDecorationVo;
import com.imall.iportal.core.shop.vo.WeShopDetailVo;
import com.imall.iportal.core.shop.vo.WeShopSaveVo;
import com.imall.iportal.core.shop.vo.WeShopUpdateVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 微门店(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class WeShopServiceImpl extends AbstractBaseService<WeShop, Long> implements WeShopService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private WeShopRepository getWeShopRepository() {
		return (WeShopRepository) baseRepository;
	}

	@Override
	public WeShop findByShopId(Long shopId) {
		return this.getWeShopRepository().findByShopId(shopId);
	}

	@Override
	@Transactional
	public Long saveWeShop(WeShopSaveVo saveVo) {
		WeShop dbWeShop = getWeShopRepository().findByShopId(saveVo.getShopId());
		if (dbWeShop != null){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "门店已开通微门店");
		}
		WeShop weShop = CommonUtil.copyProperties(saveVo, new WeShop());
		this.checkSellTime(saveVo.getSellStartTime(), saveVo.getSellEndTime());
		this.setDeliveryInfo(weShop, saveVo.getDeliveryAmount(), saveVo.getDeliveryMinOrderAmount(), weShop.getDeliveryTypeCode());
		SysZone sysZone = ServiceManager.sysZoneService.findOne(weShop.getShopZone());
		weShop.setShopZoneParent(sysZone.getParentId());
		weShop.setShopZoneParentName(ServiceManager.sysZoneService.findOne(sysZone.getParentId()).getZoneName());
        Long id = this.save(weShop).getId();
		ServiceManager.fileMngService.saveAndDeleteOld(FileObjectTypeCodeEnum.SHOP_MGR_WE_CHAT_PICT, id, saveVo.getShopMgrWeChatPict());
		ServiceManager.fileMngService.saveAndDeleteOld(FileObjectTypeCodeEnum.SHOP_LOGO, id, saveVo.getShopLogoPict());
		if (CollectionUtils.isNotEmpty(saveVo.getShopPictList())){
			ServiceManager.fileMngService.saveListAndDeleteOld(FileObjectTypeCodeEnum.SHOP_PICT, id, saveVo.getShopPictList());
		}
		return id;
	}

	private void checkSellTime(String start, String end){
		String[] startTimeArr = start.split(":");
		String[] endTimeArr = end.split(":");
		Integer startTimeHour = Integer.valueOf(startTimeArr[0]);
		Integer startTimeMinute = Integer.valueOf(startTimeArr[1]);
		Integer endTimeHour = Integer.valueOf(endTimeArr[0]);
		Integer endTimeMinute = Integer.valueOf(endTimeArr[1]);

		if (startTimeHour > endTimeHour){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "营业结束时间在营业开始时间之前");
		}
		if (startTimeHour.equals(endTimeHour) && endTimeMinute <= startTimeMinute){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "营业结束时间在营业开始时间之前");
		}
	}

	@Override
	@Transactional
	public Long updateWeSop(WeShopUpdateVo updateVo) {
		Long id = updateVo.getId();
		WeShop dbWeShop = getWeShopRepository().findByIdAndShopId(id, updateVo.getShopId());
		if (dbWeShop == null){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"微门店"}));
		}
		this.checkSellTime(updateVo.getSellStartTime(), updateVo.getSellEndTime());
		SysZone sysZone = ServiceManager.sysZoneService.findOne(updateVo.getShopZone());
		dbWeShop.setShopZoneParent(sysZone.getParentId());
		dbWeShop.setShopZoneParentName(ServiceManager.sysZoneService.findOne(sysZone.getParentId()).getZoneName());
		dbWeShop.setShopNm(updateVo.getShopNm());
		dbWeShop.setShopBrief(updateVo.getShopBrief());
		dbWeShop.setShopZone(updateVo.getShopZone());
		dbWeShop.setDetailLocation(updateVo.getDetailLocation());
		dbWeShop.setShopLat(updateVo.getShopLat());
		dbWeShop.setShopLng(updateVo.getShopLng());
		dbWeShop.setDeliveryRange(updateVo.getDeliveryRange());
		dbWeShop.setContactTel(updateVo.getContactTel());
		dbWeShop.setDeliveryTypeCode(updateVo.getDeliveryTypeCode());
		dbWeShop.setSellEndTime(updateVo.getSellEndTime());
		dbWeShop.setSellStartTime(updateVo.getSellStartTime());
		dbWeShop.setPlacardInf(updateVo.getPlacardInf());
		dbWeShop.setIsNormalSales(updateVo.getIsNormalSales());
		dbWeShop.setShopPromiseSendTime(updateVo.getShopPromiseSendTime());
		this.setDeliveryInfo(dbWeShop, updateVo.getDeliveryAmount(), updateVo.getDeliveryMinOrderAmount(), updateVo.getDeliveryTypeCode());
		this.save(dbWeShop);
		ServiceManager.fileMngService.saveAndDeleteOld(FileObjectTypeCodeEnum.SHOP_MGR_WE_CHAT_PICT, id, updateVo.getShopMgrWeChatPict());
		ServiceManager.fileMngService.saveAndDeleteOld(FileObjectTypeCodeEnum.SHOP_LOGO, id, updateVo.getShopLogoPict());
		if (CollectionUtils.isNotEmpty(updateVo.getShopPictList())){
			ServiceManager.fileMngService.saveListAndDeleteOld(FileObjectTypeCodeEnum.SHOP_PICT, id, updateVo.getShopPictList());
		}else {
			ServiceManager.fileMngService.delete(FileObjectTypeCodeEnum.SHOP_PICT, id);
		}
		return updateVo.getId();
	}

	@Override
	public WeShopDetailVo getDetailByShopId(Long shopId) {
		WeShop weShop = getWeShopRepository().findByShopId(shopId);
		if (weShop == null){
			return new WeShopDetailVo();
		}
		return this.buildWeShopDetailVo(weShop);
	}

	@Override
	public WeShopDetailVo getDetailById(Long id) {
		WeShop weShop = findOne(id);
		if (weShop == null){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "门店不存在");
		}
		return this.buildWeShopDetailVo(weShop);
	}

	private WeShopDetailVo buildWeShopDetailVo(WeShop weShop){
		Long id = weShop.getId();
		WeShopDetailVo vo = CommonUtil.copyProperties(weShop, new WeShopDetailVo());
		vo.setAreaId(weShop.getShopZone());
		SysZone shopZone = ServiceManager.sysZoneService.findOne(weShop.getShopZone());
		vo.setArea(ServiceManager.sysZoneService.findByParentId(shopZone.getParentId(), 0));

		SysZone cityZone = ServiceManager.sysZoneService.findOne(shopZone.getParentId());
		vo.setCityId(cityZone.getId());
		vo.setCity(ServiceManager.sysZoneService.findByParentId(cityZone.getParentId(), 0));

		SysZone provinceZone = ServiceManager.sysZoneService.findOne(cityZone.getParentId());
		vo.setProvinceId(provinceZone.getId());
		vo.setProvince(ServiceManager.sysZoneService.findByParentId(provinceZone.getParentId(), 0));

		//店长微信
		List<FileMng> list = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SHOP_MGR_WE_CHAT_PICT, id);
		if (CollectionUtils.isNotEmpty(list)){
			vo.setShopMgrWeChatPict(this.buildFileMngSimpleVo(list.get(0)));
		}
		//门店头像
		list = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SHOP_LOGO, id);
		if (CollectionUtils.isNotEmpty(list)){
			vo.setShopLogoPict(this.buildFileMngSimpleVo(list.get(0)));
		}
		//门店图片
		list = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SHOP_PICT, id);
		if (CollectionUtils.isNotEmpty(list)){
			List<FileMngSimpleVo> fileMngVos = new ArrayList<>();
			for (FileMng fileMng : list){
				fileMngVos.add(this.buildFileMngSimpleVo(fileMng));
			}
			vo.setShopPictList(fileMngVos);
		}
		return vo;
	}

	private FileMngSimpleVo buildFileMngSimpleVo(FileMng fileMng){
		FileMngSimpleVo vo = CommonUtil.copyProperties(fileMng, new FileMngSimpleVo());
		vo.setSmallFileUrl(FileSystemEngine.getFileSystem().getUrl(fileMng.getFileId(), "160X160"));
		return vo;
	}

	@Override
	public Integer queryWeShopToQueue() {
		return getWeShopRepository().queryWeShopToQueue();
	}

	@Override
	public List<WeShop> findByDistance(Double distance, Double lat, Double lng) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
				.filter(QueryBuilders.geoDistanceQuery(WeShopEntity.LOCATION).point(lat, lng).distance(distance, DistanceUnit.KILOMETERS));

		SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.WE_SHOP.toCode())
				.setTypes(IndexTypeCodeEnum.WE_SHOP.toCode())
				.setSearchType(SearchType.DEFAULT)
				.setQuery(queryBuilder)
				.addStoredField(WeShopEntity.ID)
				.setExplain(false);
		SearchResponse sr = searchRequestBuilder.get();
		List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
		List<WeShop> result = new ArrayList<>();
		for (Map<String, Object> map : list){
			Long id = (Long) map.get(WeShopEntity.ID);
			result.add(this.findOne(id));
		}
		return result;
	}

	@Override
	public List<WeShop> findDistinctWeShop() {
		List<Object> list = getWeShopRepository().findDistinctShopZoneParent();
		List<WeShop> shopList = new ArrayList<>();
		for (Object object : list){
			Object[] arr = (Object[]) object;
			shopList.add((WeShop) arr[0]);
		}
        return shopList;
	}

	@Override
	public List<WeShop> findByCityName(String cityName) {
		return getWeShopRepository().findByShopZoneParentName(cityName);
	}

	@Override
	public WeShopDecorationVo getDecorationShopMsg(Long shopId) {
		WeShop weShop = getWeShopRepository().findByShopId(shopId);
		if (weShop == null){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"微门店"}));
		}
		WeShopDecorationVo vo = new WeShopDecorationVo();
		vo.setShopNm(weShop.getShopNm());
		//门店头像
		List<FileMng> list = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SHOP_LOGO, weShop.getId());
		if (CollectionUtils.isNotEmpty(list)){
			vo.setLogoUrl(FileSystemEngine.getFileSystem().getUrl(list.get(0).getFileId()));
		}
		return vo;
	}

	private void setDeliveryInfo(WeShop dbWeShop, Double deliveryAmount, Double deliveryMinOrderAmount, String deliveryTypeCode){
		switch (DeliveryTypeCodeEnum.fromCode(deliveryTypeCode)){
			case NEVER_PAY:
				dbWeShop.setDeliveryAmount(0D);
				dbWeShop.setDeliveryMinOrderAmount(0D);
				break;
			case NEED_PAY:
				dbWeShop.setDeliveryAmount(deliveryAmount);
				dbWeShop.setDeliveryMinOrderAmount(0D);
				break;
			case FULL_AMOUNT_NOT_PAY:
				dbWeShop.setDeliveryAmount(deliveryAmount);
				dbWeShop.setDeliveryMinOrderAmount(deliveryMinOrderAmount);
				break;
		}
	}
}