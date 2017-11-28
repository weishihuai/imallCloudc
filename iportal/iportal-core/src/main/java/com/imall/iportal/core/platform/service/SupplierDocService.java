package com.imall.iportal.core.platform.service;


import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.iportal.core.platform.vo.*;
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
 * 供应商 档案(服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SupplierDocService {


    /**
     * 列表
     *
     * @param pageable
     * @param supplierSearchParam
     * @return
     */
    Page<SupplierDocPageVo> querySupplierDoc(Pageable pageable, @NotNull SupplierDocSearchParam supplierSearchParam) throws ParseException;

    /**
     * 保存
     *
     * @param supplierUpdateVo
     * @return
     */
    Long update(@NotNull @Valid SupplierDocUpdateVo supplierUpdateVo) throws ParseException;

    /**
     *
     * 检查供应商是否已经存在
     * @return
     */
    Boolean checkNameIsExist(@NotBlank @Length(max = 32) String name, Long userId);


    /**
     * 更新
     *
     * @param supplierSaveVo
     * @return
     */
    Long save(@NotNull @Valid SupplierDocSaveVo supplierSaveVo) throws ParseException;


    /**
     * 详情
     *
     * @param id
     * @return
     */
    SupplierDocDetailVo findById(@NotNull Long id);

    /**
     * 禁用/启用
     *
     * @param id
     * @param state 供应商是否启用
     * @return
     */

    Long updateSupplierDocState(@NotNull Long id, @NotBlank String state);


    /**
     * 供应商档案组件 分页查询
     * @param pageable
     * @param supplierNm 供应商名称
     * @param certificatesNum   编号
     * @param certificatesType 资质 类型
     * @return
     */
    Page<SupplierDocComponentPageVo> queryPage(Pageable pageable, String supplierNm, String certificatesNum, String certificatesType);

    /**
     * 导出所有符合搜索条件的数据
     * @param pageable
     * @param supplierSearchParam
     * @return
     */
    Boolean exportExcel(@NotNull String xlsTemplatePath, @NotNull String xlsOutputPath, @NotNull Pageable pageable, @NotNull SupplierDocSearchParam supplierSearchParam);

    /**
     * 导入
     * @param localFileId 物理文件地址
     * @return
     */
    List<ErrorLog> importData(String localFileId);

}
