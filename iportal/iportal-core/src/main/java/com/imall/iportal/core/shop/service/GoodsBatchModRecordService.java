package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.GoodsBatchModRecord;
import org.springframework.validation.annotation.Validated;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsBatchModRecordService {
    /**
     * 保存对象
     *
     * @param goodsBatchModRecord
     * @return
     */
    GoodsBatchModRecord save(GoodsBatchModRecord goodsBatchModRecord);
}
