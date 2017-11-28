
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.Supplier;
import com.imall.iportal.core.shop.vo.SupplierSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SupplierRepositoryCustom{


    Page<Supplier> query(Pageable pageable, SupplierSearchParam supplierSearchParam) throws ParseException;

    /**
     * 供应商组件 分页查询
     * @param pageable
     * @param supplierNm 供应商名
     * @param certificatesType 执照类型
     * @param certificatesNum 营业执照号
     * @return
     */
    Page<Supplier> queryPage(Pageable pageable, String supplierNm, String certificatesType,String certificatesNum, Long shopId) ;
}

