package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.SupplierCertificatesFile;
import com.imall.iportal.core.shop.vo.SupplierCertificatesFileSaveVo;
import com.imall.iportal.core.shop.vo.SupplierCertificatesFileUpdateVo;
import com.imall.iportal.core.shop.vo.SupplierCertificatesFileVo;
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
public interface SupplierCertificatesFileService {

    SupplierCertificatesFile save(SupplierCertificatesFile supplierCertificatesFile);

    void delete(SupplierCertificatesFile supplierCertificatesFile);

    /**
     * 通过供应商id获取资质文件列表
     * @param supplierId
     * @return
     */
    List<SupplierCertificatesFileVo> listSupplierCertificatesFilesList(@NotNull Long supplierId);

    /**
     * 修改
     * @param supplierCertificatesFileUpdateVo
     * @return
     * @throws ParseException
     */
    Long updateBySupplierIdAndCertificatesType(@NotNull @Valid SupplierCertificatesFileUpdateVo supplierCertificatesFileUpdateVo) throws ParseException;

    /**
     * 保存
     * @param supplierCertificatesFileSaveVo
     * @return
     * @throws ParseException
     */
    Long saveSupplierCertificatesFile(@NotNull @Valid SupplierCertificatesFileSaveVo supplierCertificatesFileSaveVo) throws ParseException;

    /**
     * 查询供应商对应资质
     * @param supplierId 供应商id
     * @param certificatesType 资质类型
     * @return 返回资质对象
     */
    SupplierCertificatesFile findBySupplierIdAndCertificatesType(Long supplierId, String certificatesType);

    /**
     * 通过供应商id获取资质文件列表
     * @param supplierId
     * @return
     */
    List<SupplierCertificatesFile> findBySupplierId(Long supplierId);
}
