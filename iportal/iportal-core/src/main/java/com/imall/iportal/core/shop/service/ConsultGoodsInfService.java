package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.ConsultGoodsInf;
import com.imall.iportal.core.shop.vo.ConsultGoodsInfDetailVo;

import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ConsultGoodsInfService{

    /**
     * 保存
     * @param consultGoodsInf
     * @return
     */
    ConsultGoodsInf save(ConsultGoodsInf consultGoodsInf);

    /**
     * 查找咨询服务的商品
     * @param consultServiceId 咨询服务主键ID
     * @return
     */
    List<ConsultGoodsInfDetailVo> findDetailByConsultServiceId(Long consultServiceId);

}
