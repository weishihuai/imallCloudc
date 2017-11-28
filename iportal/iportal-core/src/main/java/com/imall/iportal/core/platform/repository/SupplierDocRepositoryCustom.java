
package com.imall.iportal.core.platform.repository;

import com.imall.iportal.core.platform.entity.SupplierDoc;
import com.imall.iportal.core.platform.vo.SupplierDocSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * 供应商 档案(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SupplierDocRepositoryCustom{

    Page<SupplierDoc> query(Pageable pageable, SupplierDocSearchParam supplierSearchParam) throws ParseException;

    /**
     * 供应商组件 分页查询
     * @param pageable
     * @param supplierNm 供应商名
     * @param certificatesType 执照类型
     * @param certificatesNum 营业执照号
     * @return
     */
    Page<SupplierDoc> queryPage(Pageable pageable, String supplierNm, String certificatesType,String certificatesNum) ;

}

