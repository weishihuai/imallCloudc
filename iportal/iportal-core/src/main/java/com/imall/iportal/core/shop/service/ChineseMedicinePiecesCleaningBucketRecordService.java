package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.ChineseMedicinePiecesCleaningBucketRecord;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesCleaningBucketRecordPageVo;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesCleaningBucketRecordSearchParam;
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
public interface ChineseMedicinePiecesCleaningBucketRecordService{

    /**
     * 列表
     *
     * @param pageable
     * @param chineseMedicinePiecesCleaningBucketRecordSearchParam
     * @return
     */
    Page<ChineseMedicinePiecesCleaningBucketRecordPageVo> queryChineseMedicinePiecesCleaningBucketRecord(@NotNull Pageable pageable, @NotNull ChineseMedicinePiecesCleaningBucketRecordSearchParam chineseMedicinePiecesCleaningBucketRecordSearchParam) throws ParseException;

    /**
     * 保存
     *
     * @param chineseMedicinePiecesCleaningBucketRecord
     * @return
     */
    Long saveChineseMedicinePiecesCleaningBucketRecord( ChineseMedicinePiecesCleaningBucketRecord chineseMedicinePiecesCleaningBucketRecord) throws ParseException;


}
