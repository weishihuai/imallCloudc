package com.imall.iportal.core.platform.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.CertificatesFileCodeEnum;
import com.imall.iportal.core.platform.entity.SupplierDocCertificatesFile;
import com.imall.iportal.core.platform.repository.SupplierDocCertificatesFileRepository;
import com.imall.iportal.core.platform.service.SupplierDocCertificatesFileService;
import com.imall.iportal.core.platform.vo.SupplierDocCertificatesFileSaveVo;
import com.imall.iportal.core.platform.vo.SupplierDocCertificatesFileUpdateVo;
import com.imall.iportal.core.platform.vo.SupplierDocCertificatesFileVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
public class SupplierDocCertificatesFileServiceImpl extends AbstractBaseService<SupplierDocCertificatesFile, Long> implements SupplierDocCertificatesFileService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private SupplierDocCertificatesFileRepository getSupplierDocCertificatesFileRepository() {
        return (SupplierDocCertificatesFileRepository) baseRepository;
    }

    @Override
    public List<SupplierDocCertificatesFileVo> listSupplierDocCertificatesFilesList(Long supplierDocId) {
        List<SupplierDocCertificatesFile> supplierDocCertificatesFileServiceList = getSupplierDocCertificatesFileRepository().findBySupplierDocId(supplierDocId);
        List<SupplierDocCertificatesFileVo> supplierDocCertificatesFileVoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(supplierDocCertificatesFileServiceList)) {
            return supplierDocCertificatesFileVoList;
        }
        for (SupplierDocCertificatesFile supCerFil : supplierDocCertificatesFileServiceList) {
            SupplierDocCertificatesFileVo supplierDocCertificatesFileVo = CommonUtil.copyProperties(supCerFil, new SupplierDocCertificatesFileVo());
            supplierDocCertificatesFileVo.setCertificatesValidityString(DateTimeUtils.convertDateToString(supCerFil.getCertificatesValidity()));
            supplierDocCertificatesFileVoList.add(supplierDocCertificatesFileVo);
        }
        return supplierDocCertificatesFileVoList;
    }

    @Override
    @Transactional
    public Long updateBySupplierDocIdAndCertificatesType(SupplierDocCertificatesFileUpdateVo supplierDocCertificatesFileUpdateVo) throws ParseException {
        SupplierDocCertificatesFile supplierDocCertificatesFile = CommonUtil.copyProperties(supplierDocCertificatesFileUpdateVo, new SupplierDocCertificatesFile());
        supplierDocCertificatesFile.setCertificatesValidity(StringUtils.isNotBlank(supplierDocCertificatesFileUpdateVo.getCertificatesValidityString())?DateTimeUtils.convertStringToDate(supplierDocCertificatesFileUpdateVo.getCertificatesValidityString()):null);
        supplierDocCertificatesFile.setCertificatesType(CertificatesFileCodeEnum.fromCode(supplierDocCertificatesFileUpdateVo.getCertificatesType()).toCode());
        return save(supplierDocCertificatesFile).getId();
    }

    @Override
    @Transactional
    public Long saveSupplierDocCertificatesFile(SupplierDocCertificatesFileSaveVo supplierDocCertificatesFileSaveVo) throws ParseException {
        SupplierDocCertificatesFile supplierDocCertificatesFile = CommonUtil.copyProperties(supplierDocCertificatesFileSaveVo, new SupplierDocCertificatesFile());
        supplierDocCertificatesFile.setCertificatesValidity(StringUtils.isNotBlank(supplierDocCertificatesFileSaveVo.getCertificatesValidityString())?DateTimeUtils.convertStringToDate(supplierDocCertificatesFileSaveVo.getCertificatesValidityString()):null);
        supplierDocCertificatesFile.setCertificatesType(CertificatesFileCodeEnum.fromCode(supplierDocCertificatesFileSaveVo.getCertificatesType()).toCode());
        return save(supplierDocCertificatesFile).getId();
    }

    @Override
    public SupplierDocCertificatesFile findBySupplierDocIdAndCertificatesType(Long supplierDocId, String certificatesType) {
        SupplierDocCertificatesFile supplierDocCertificatesFileList = getSupplierDocCertificatesFileRepository().findBySupplierDocIdAndCertificatesType(supplierDocId, certificatesType);
       if(supplierDocCertificatesFileList==null){
           SupplierDocCertificatesFile supplierDocCertificatesFile = new SupplierDocCertificatesFile();
           supplierDocCertificatesFile.setCertificatesType(certificatesType);
           supplierDocCertificatesFile.setSupplierDocId(supplierDocId);
           return supplierDocCertificatesFile;
       }
        return supplierDocCertificatesFileList;
    }

    @Override
    public List<SupplierDocCertificatesFile> findBySupplierDocId(Long supplierDocId) {
        return getSupplierDocCertificatesFileRepository().findBySupplierDocId(supplierDocId);

    }
}