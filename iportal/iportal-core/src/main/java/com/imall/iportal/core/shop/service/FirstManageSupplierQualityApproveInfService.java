package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.FirstManageSupplierQualityApproveInf;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveInfDetailVo;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveInfSaveVo;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveInfVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface FirstManageSupplierQualityApproveInfService{
    /**
     * 通过 首营审核对象id  获取审核信息
     * @param firstManageSupplierQualityApproveId
     * @return
     */
    List<FirstManageSupplierQualityApproveInfDetailVo> findByFirstManageSupplierQualityApproveId(@NotNull Long firstManageSupplierQualityApproveId);
    /**
     * 通过 首营审核对象id  获取审核信息(返回对象与上面不同而已)
     * @param firstManageSupplierQualityApproveId
     * @return
     */
    FirstManageSupplierQualityApproveInfVo findById(@NotNull Long firstManageSupplierQualityApproveId);

    /**
     * 保存
     * @param firstManageSupplierQualityApproveInfSaveVo
     * @throws ParseException
     */
    void saveFirstManageSupplierQualityApproveInf(@NotNull @Valid  FirstManageSupplierQualityApproveInfSaveVo firstManageSupplierQualityApproveInfSaveVo) throws ParseException;

    /**
     * 通过供应商id查询列表
     * @param supplierId
     * @return
     */
    List<FirstManageSupplierQualityApproveInf> findBySupplierId(@NotNull Long supplierId);

}
