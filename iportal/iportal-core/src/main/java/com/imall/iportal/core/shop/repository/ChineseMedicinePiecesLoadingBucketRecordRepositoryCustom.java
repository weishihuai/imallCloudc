
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.ChineseMedicinePiecesLoadingBucketRecord;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesLoadingBucketRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ChineseMedicinePiecesLoadingBucketRecordRepositoryCustom{

    Page<ChineseMedicinePiecesLoadingBucketRecord> query(Pageable pageable, ChineseMedicinePiecesLoadingBucketRecordSearchParam chineseMedicinePiecesLoadingBucketRecordSearchParam) throws ParseException;

}

