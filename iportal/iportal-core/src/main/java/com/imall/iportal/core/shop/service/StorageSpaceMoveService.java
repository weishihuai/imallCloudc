package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface StorageSpaceMoveService {

    /**
     * 货位移动列表
     *
     * @param pageable                    分页对象
     * @param storageSpaceMoveSearchParam 搜索参数
     * @return
     */
    Page<StorageSpaceMovePageVo> query(@NotNull Pageable pageable, StorageSpaceMoveSearchParam storageSpaceMoveSearchParam);

    /**
     * 根据移动单号查询货位移动信息
     *
     * @param moveOrderNum 移动单号
     * @param shopId       门店id
     * @return
     */
    StorageSpaceMoveDetailVo findByMoveOrderNum(@NotBlank String moveOrderNum, @NotNull Long shopId);


    /**
     * 保存货位移动信息
     *
     * @param storageSpaceMoveSaveVo 货位移动Vo对象
     */
    void saveStorageSpaceMove(@NotNull @Valid StorageSpaceMoveSaveVo storageSpaceMoveSaveVo) throws ParseException;

    /**
     * 生成移动单号
     *
     * @return
     */
    StorageSpaceMoveNumVo generateMoveOrderNum();
}
