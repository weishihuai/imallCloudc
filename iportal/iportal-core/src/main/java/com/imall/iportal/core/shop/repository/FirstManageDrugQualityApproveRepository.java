
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.FirstManageDrugQualityApprove;
/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface FirstManageDrugQualityApproveRepository extends  IBaseRepository<FirstManageDrugQualityApprove, Long>,FirstManageDrugQualityApproveRepositoryCustom {

    FirstManageDrugQualityApprove findByGoodsId(Long goodsId);
}

