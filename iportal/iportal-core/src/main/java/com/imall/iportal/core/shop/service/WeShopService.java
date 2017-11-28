package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.WeShop;
import com.imall.iportal.core.shop.vo.WeShopDecorationVo;
import com.imall.iportal.core.shop.vo.WeShopDetailVo;
import com.imall.iportal.core.shop.vo.WeShopSaveVo;
import com.imall.iportal.core.shop.vo.WeShopUpdateVo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 微门店(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface WeShopService{

    WeShop findOne(@NotNull Long id);

    //根据门店ID找到微门店
    WeShop findByShopId(@NotNull Long shopId);

    /**
     * 保存微门店
     * @param saveVo
     * @return
     */
    Long saveWeShop(@Valid @NotNull WeShopSaveVo saveVo);

    /**
     * 更新微门店
     * @param updateVo
     * @return
     */
    Long updateWeSop(@Valid @NotNull WeShopUpdateVo updateVo);

    /**
     * 微门店详情
     * @param shopId
     * @return
     */
    WeShopDetailVo getDetailByShopId(@NotNull Long shopId);

    /**
     * 查找微门店数据插入到索引队列
     *
     * @return 插入索引队列订单数量
     */
    Integer queryWeShopToQueue();

    /**
     * 根据经纬度，找出半径为distance的微门店
     * @param distance
     * @param lat
     * @param lng
     * @return
     */
    List<WeShop> findByDistance(@NotNull Double distance, @NotNull Double lat, @NotNull Double lng);

    /**
     * 找出不同区域的门店
     * @return
     */
    List<WeShop> findDistinctWeShop();

    /**
     * 根据城市名称查找
     * @param cityName
     * @return
     */
    List<WeShop> findByCityName(@NotBlank String cityName);

    /**
     * 门店装修VO
     * @param shopId
     * @return
     */
    WeShopDecorationVo getDecorationShopMsg(@NotNull Long shopId);

    /**
     * 微门店详情
     * @param id
     * @return
     */
    WeShopDetailVo getDetailById(@NotNull Long id);
}
