
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.ChineseMedicinePiecesCleaningBucketRecord;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesCleaningBucketRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ChineseMedicinePiecesCleaningBucketRecordRepositoryCustom{


    Page<ChineseMedicinePiecesCleaningBucketRecord> query(Pageable pageable, ChineseMedicinePiecesCleaningBucketRecordSearchParam chineseMedicinePiecesCleaningBucketRecordSearchParam) throws ParseException;

}

