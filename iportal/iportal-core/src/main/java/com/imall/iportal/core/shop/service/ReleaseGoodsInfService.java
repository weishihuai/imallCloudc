package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.ReleaseGoodsInf;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface ReleaseGoodsInfService{

    /**
     * 通过解停单id查找解停单商品批次信息
     * @param id
     * @return
     */
    List<ReleaseGoodsInf> findByDrugReleaseNoticeId(@NotNull Long id);

    ReleaseGoodsInf save(ReleaseGoodsInf releaseGoodsInf);
}
