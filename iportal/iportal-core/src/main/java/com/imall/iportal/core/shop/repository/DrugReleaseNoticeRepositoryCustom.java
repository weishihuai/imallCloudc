
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.DrugReleaseNotice;
import com.imall.iportal.core.shop.vo.ReleaseNoticeSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugReleaseNoticeRepositoryCustom{


    /**
     * 解停单列表
     * @param pageable
     * @param releaseNoticeSearchParam
     * @param shopId
     * @return
     */
    Page<DrugReleaseNotice> query(Pageable pageable, ReleaseNoticeSearchParam releaseNoticeSearchParam, Long shopId) throws ParseException;
}

