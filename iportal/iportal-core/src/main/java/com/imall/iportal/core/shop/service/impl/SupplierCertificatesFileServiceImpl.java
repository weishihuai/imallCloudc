package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.CertificatesFileCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.SupplierCertificatesFile;
import com.imall.iportal.core.shop.repository.SupplierCertificatesFileRepository;
import com.imall.iportal.core.shop.service.SupplierCertificatesFileService;
import com.imall.iportal.core.shop.vo.SupplierCertificatesFileSaveVo;
import com.imall.iportal.core.shop.vo.SupplierCertificatesFileUpdateVo;
import com.imall.iportal.core.shop.vo.SupplierCertificatesFileVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SupplierCertificatesFileServiceImpl extends AbstractBaseService<SupplierCertificatesFile, Long> implements SupplierCertificatesFileService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private SupplierCertificatesFileRepository getSupplierCertificatesFileRepository() {
        return (SupplierCertificatesFileRepository) baseRepository;
    }

    @Override
    public List<SupplierCertificatesFileVo> listSupplierCertificatesFilesList(Long supplierId) {
        List<SupplierCertificatesFile> supplierCertificatesFileServiceList = getSupplierCertificatesFileRepository().findBySupplierId(supplierId);
        List<SupplierCertificatesFileVo> supplierCertificatesFileVoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(supplierCertificatesFileServiceList)) {
            return supplierCertificatesFileVoList;
        }
        for (SupplierCertificatesFile supCerFil : supplierCertificatesFileServiceList) {
            SupplierCertificatesFileVo supplierCertificatesFileVo = CommonUtil.copyProperties(supCerFil, new SupplierCertificatesFileVo());
            supplierCertificatesFileVo.setCertificatesValidityString(DateTimeUtils.convertDateToString(supCerFil.getCertificatesValidity()));
            supplierCertificatesFileVoList.add(supplierCertificatesFileVo);
        }

        return supplierCertificatesFileVoList;
    }

    @Override
    @Transactional
    public Long updateBySupplierIdAndCertificatesType(SupplierCertificatesFileUpdateVo supplierCertificatesFileUpdateVo) throws ParseException {
        SupplierCertificatesFile supplierCertificatesFile = CommonUtil.copyProperties(supplierCertificatesFileUpdateVo, new SupplierCertificatesFile());
        supplierCertificatesFile.setCertificatesValidity(StringUtils.isNotBlank(supplierCertificatesFileUpdateVo.getCertificatesValidityString())?DateTimeUtils.convertStringToDate(supplierCertificatesFileUpdateVo.getCertificatesValidityString()):null);
        supplierCertificatesFile.setCertificatesType(StringUtils.isNotBlank(supplierCertificatesFileUpdateVo.getCertificatesType())?CertificatesFileCodeEnum.fromCode(supplierCertificatesFileUpdateVo.getCertificatesType()).toCode():null);
        return save(supplierCertificatesFile).getId();
    }

    @Override
    @Transactional
    public Long saveSupplierCertificatesFile(SupplierCertificatesFileSaveVo supplierCertificatesFileSaveVo) throws ParseException {
        SupplierCertificatesFile supplierCertificatesFile = CommonUtil.copyProperties(supplierCertificatesFileSaveVo, new SupplierCertificatesFile());
        supplierCertificatesFile.setCertificatesValidity(StringUtils.isNotBlank(supplierCertificatesFileSaveVo.getCertificatesValidityString())?DateTimeUtils.convertStringToDate(supplierCertificatesFileSaveVo.getCertificatesValidityString()):null);
        supplierCertificatesFile.setCertificatesType(StringUtils.isNotBlank(supplierCertificatesFileSaveVo.getCertificatesType())?CertificatesFileCodeEnum.fromCode(supplierCertificatesFileSaveVo.getCertificatesType()).toCode():null);
        return save(supplierCertificatesFile).getId();
    }

    @Override
    public SupplierCertificatesFile findBySupplierIdAndCertificatesType(Long supplierId, String certificatesType) {
        List<SupplierCertificatesFile> supplierCertificatesFileList = getSupplierCertificatesFileRepository().findBySupplierIdAndCertificatesType(supplierId, certificatesType);
        if (CollectionUtils.isEmpty(supplierCertificatesFileList)) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        return supplierCertificatesFileList.get(0);
    }

    @Override
    public List<SupplierCertificatesFile> findBySupplierId(Long supplierId) {
        return getSupplierCertificatesFileRepository().findBySupplierId(supplierId);
    }

}