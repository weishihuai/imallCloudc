
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.FirstManageDrugQualityApprove;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApproveSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface FirstManageDrugQualityApproveRepositoryCustom{


    /**
     * 商品首营审核列表
     * @param pageable
     * @param firstManageDrugQualityApproveSearchParam
     * @param shopId
     * @return
     */
    Page<FirstManageDrugQualityApprove> query(Pageable pageable, FirstManageDrugQualityApproveSearchParam firstManageDrugQualityApproveSearchParam, Long shopId) throws ParseException;
}

