package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.StorageSpace;
import com.imall.iportal.core.shop.vo.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface StorageSpaceService {

    /**
     * 创建门店，生成货位初始化数据
     * @param shopId
     */
    void saveForShopCreate(Long shopId);

    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    StorageSpace findOne(@NotNull Long id);

    /**
     * 保存商品货位信息
     *
     * @param storageSpaceSaveVo
     * @return
     */
    Long save(@NotNull @Valid StorageSpaceSaveVo storageSpaceSaveVo);

    /**
     * 修改商品货位信息
     *
     * @param storageSpaceUpdateVo
     * @return
     */
    Long update(@NotNull @Valid StorageSpaceUpdateVo storageSpaceUpdateVo);

    /**
     * 分页查询商品的货位信息
     *
     * @param pageable                分页对象
     * @param storageSpaceSearchParam 搜索参数
     * @return
     */
    Page<StorageSpaceVo> query(@NotNull Pageable pageable, @Valid @NotNull StorageSpaceSearchParam storageSpaceSearchParam);

    /**
     * 根据商品货位ID查询货位的信息
     *
     * @param shopId 门店ID
     * @param id     商品货位ID
     * @return
     */
    StorageSpaceVo findById(@NotNull Long shopId, @NotNull Long id);

    /**
     * 用于商品管理列表搜索
     *
     * @return
     */
    List<StorageSpaceSimpleVo> listForGoodsList(@NotNull Long shopId);

    /**
     * 检查货位名称是否已经存在
     *
     * @param storageSpaceNm 货位名称
     * @param id             货位ID
     * @param shopId         门店ID
     * @return
     */
    Boolean checkStorageSpaceNmIsExist(@NotBlank @Length(max = 32) String storageSpaceNm, Long id, @NotNull Long shopId);

    /**
     * 根据门店ID查询所有已启用状态的货位信息
     *
     * @param shopId 门店ID
     * @return
     */
    List<StorageSpaceVo> listAllEnableStorageSpace(@NotNull Long shopId);
    /**
     * 根据门店ID和其他条件查询所有的货位
     *
     * @param shopId 门店ID
     * @return
     */
    List<StorageSpace> findByShopIdAndOthers(@NotNull Long shopId,@NotBlank String isVirtualWarehouse,@NotBlank String storageSpaceType, @NotBlank String isEnable);
}
