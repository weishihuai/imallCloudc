package com.imall.iportal.core.shop.service;


import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.iportal.core.shop.entity.Supplier;
import com.imall.iportal.core.shop.vo.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SupplierService {

    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    Supplier findOne(@NotNull Long id);

    /**
     * 列表
     *
     * @param pageable
     * @param supplierSearchParam
     * @return
     */
    Page<SupplierPageVo> querySupplier(@NotNull Pageable pageable, @NotNull SupplierSearchParam supplierSearchParam) throws ParseException;

    /**
     * 保存
     *
     * @param supplierUpdateVo
     * @return
     */
    Long update(@NotNull @Valid SupplierUpdateVo supplierUpdateVo) throws ParseException;


    /**
     * 更新
     *
     * @param supplierSaveVo
     * @return
     */
    Long save(@NotNull @Valid SupplierSaveVo supplierSaveVo) throws ParseException;

    /**
     * 更新 是否首营状态
     * @param supplierId
     * @param isFirstCheck
     * @return
     */

    /**
     *
     * 检查供应商是否已经存在
     * @return
     */
    Boolean checkNameIsExist(@NotBlank @Length(max = 32) String name, Long userId,@NotNull Long shopId);

    /**
     * 改变首营状态
     * @param supplierId
     * @param isFirstCheck
     * @return
     */
    Long updateIsFirstCheck(@NotNull Long supplierId, @NotBlank String isFirstCheck);
    /**
     * 更新  审核状态
     * @param supplierId
     * @param approveStateCode
     * @return
     */

    Long updateApproveStateCode(@NotNull Long supplierId, @NotBlank String approveStateCode);


    /**
     * 详情
     *
     * @param id
     * @return
     */
    SupplierDetailVo findById(@NotNull Long id,@NotNull Long shopId);

    /**
     * 禁用/启用
     *
     * @param id
     * @param state 供应商是否启用
     * @return
     */

    Long updateSupplierState(@NotNull Long id, @NotBlank String state,@NotNull Long shopId);

    /**
     * 供应商组件 分页查询
     * @param pageable
     * @param supplierNm 供应商名称
     * @param certificatesNum   编号
     * @param certificatesType 资质 类型
     * @return
     */
    Page<SupplierComponentPageVo> queryPage(Pageable pageable, String supplierNm, String certificatesNum, String certificatesType,@NotNull Long shopId);

    /**
     * 导出所有符合搜索条件的数据
     * @param pageable
     * @param supplierSearchParam
     * @return
     */
    Boolean exportExcel(@NotNull String xlsTemplatePath, @NotNull String xlsOutputPath, @NotNull Pageable pageable, @NotNull SupplierSearchParam supplierSearchParam);

    /**
     * 导入
     * @param localFileId 物理文件地址
     * @return
     */
    List<ErrorLog> importData(@NotNull String localFileId, @NotNull Long shopId);
}
