package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.ChineseMedicinePiecesLoadingBucketRecord;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesLoadingBucketRecordPageVo;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesLoadingBucketRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface ChineseMedicinePiecesLoadingBucketRecordService{


    /**
     * 列表
     *
     * @param pageable
     * @param medicinePiecesLoadingBucketRecordSearchParam
     * @return
     */
    Page<ChineseMedicinePiecesLoadingBucketRecordPageVo> queryChineseMedicinePiecesLoadingBucketRecord(@NotNull Pageable pageable, @NotNull ChineseMedicinePiecesLoadingBucketRecordSearchParam medicinePiecesLoadingBucketRecordSearchParam) throws ParseException;

    /**
     * 保存
     *
     * @param chineseMedicinePiecesLoadingBucketRecord
     * @return
     */
    Long saveChineseMedicinePiecesLoadingBucketRecord( ChineseMedicinePiecesLoadingBucketRecord chineseMedicinePiecesLoadingBucketRecord) throws ParseException;


}
