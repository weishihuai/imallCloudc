package com.imall.iportal.core.platform.service;


import com.imall.iportal.core.platform.entity.SupplierDocCertificatesFile;
import com.imall.iportal.core.platform.vo.SupplierDocCertificatesFileSaveVo;
import com.imall.iportal.core.platform.vo.SupplierDocCertificatesFileUpdateVo;
import com.imall.iportal.core.platform.vo.SupplierDocCertificatesFileVo;
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
public interface SupplierDocCertificatesFileService{
    /**
     * 通过供应商档案 id获取资质文件列表
     * @param supplierDocId
     * @return
     */
    List<SupplierDocCertificatesFileVo> listSupplierDocCertificatesFilesList(@NotNull Long supplierDocId);

    /**
     * 修改
     * @param supplierDocCertificatesFileUpdateVo
     * @return
     * @throws ParseException
     */
    Long updateBySupplierDocIdAndCertificatesType(@NotNull @Valid SupplierDocCertificatesFileUpdateVo supplierDocCertificatesFileUpdateVo) throws ParseException;

    /**
     * 保存
     * @param supplierDocCertificatesFileSaveVo
     * @return
     * @throws ParseException
     */
    Long saveSupplierDocCertificatesFile(@NotNull @Valid SupplierDocCertificatesFileSaveVo supplierDocCertificatesFileSaveVo) throws ParseException;

    /**
     * 查询供应商对应资质
     * @param supplierDocId 供应商id
     * @param certificatesType 资质类型
     * @return 返回资质对象
     */
    SupplierDocCertificatesFile findBySupplierDocIdAndCertificatesType(Long supplierDocId, String certificatesType);
    /**
     * 通过供应商id获取资质文件列表
     * @param supplierId
     * @return
     */
    List<SupplierDocCertificatesFile> findBySupplierDocId(Long supplierId);

    void delete(SupplierDocCertificatesFile supplierDocCertificatesFile);

}
