package com.imall.iportal.core.elasticsearch.provider;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.IIndexProvider;
import com.imall.iportal.core.elasticsearch.entity.WeShopEntity;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.WeShop;
import org.springframework.stereotype.Component;

/**
 * Created by lxh on 2017/8/15.
 */
@Component
public class WeShopIndexProvider implements IIndexProvider<WeShopEntity> {

    @Override
    public WeShopEntity getEntity(Long id) {
        WeShop weShop = ServiceManager.weShopService.findOne(id);
        if (weShop == null){
            return null;
        }
        WeShopEntity entity = CommonUtil.copyProperties(weShop, new WeShopEntity());
        entity.setLocation(new WeShopEntity.Location(weShop.getShopLat(), weShop.getShopLng()));
        return entity;
    }

    @Override
    public IndexTypeCodeEnum actionType() {
        return IndexTypeCodeEnum.WE_SHOP;
    }
}
