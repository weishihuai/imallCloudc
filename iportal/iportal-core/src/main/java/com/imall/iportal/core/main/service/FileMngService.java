package com.imall.iportal.core.main.service;


import com.imall.commons.dicts.FileObjectTypeCodeEnum;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.main.valid.FileMngSaveValid;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface FileMngService{

    /**
     * 批量保存关联文件管理
     * @param fileObjectTypeCode 文件对象类型
     * @param objectId 对象主键ID
     * @param fileMngList 关联文件
     */
    void saveList(@NotNull FileObjectTypeCodeEnum fileObjectTypeCode, @NotNull Long objectId, @NotEmpty List<FileMng> fileMngList);

    /**
     * 保存
     * @param fileObjectTypeCode 文件对象类型
     * @param objectId 对象主键ID
     * @param fileMngSaveValid 关联文件
     * @return 保存后文件管理
     */
    FileMng save(@NotNull FileObjectTypeCodeEnum fileObjectTypeCode, @NotNull Long objectId, @NotNull @Valid FileMngSaveValid fileMngSaveValid);

    /**
     * 批量保存文件管理并删除对应旧文件管理
     * @param fileObjectTypeCode 文件对象类型
     * @param objectId 对象主键ID
     * @param fileMngList 关联文件
     */
    void saveListAndDeleteOld(@NotNull FileObjectTypeCodeEnum fileObjectTypeCode, @NotNull Long objectId, @NotNull List<FileMng> fileMngList);

    /**
     * 保存文件管理并删除对应旧文件管理
     * @param fileObjectTypeCode 文件对象类型
     * @param objectId 对象主键ID
     * @param fileMng 关联文件
     */
    void saveAndDeleteOld(@NotNull FileObjectTypeCodeEnum fileObjectTypeCode, @NotNull Long objectId, @NotNull FileMng fileMng);

    /**
     * 保存文件管理并删除对应旧文件管理(微信头像上传使用)
     * @param fileObjectTypeCode 文件对象类型
     * @param objectId 对象主键ID
     * @param fileMng 关联文件
     */
    void saveNewFileAndDeleteOld(@NotNull FileObjectTypeCodeEnum fileObjectTypeCode, @NotNull Long objectId, @NotNull FileMng fileMng);

    /**
     * 删除文件管理
     * @param fileObjectTypeCode 文件对象类型
     * @param objectId 对象主键ID
     */
    void delete(@NotNull FileObjectTypeCodeEnum fileObjectTypeCode, @NotNull Long objectId);

    /**
     * 获取特定对象关联的文件
     * @param fileObjectTypeCode 文件对象类型
     * @param objectId 对象主键ID
     * @return 关联的文件
     */
    List<FileMng> getList(@NotNull FileObjectTypeCodeEnum fileObjectTypeCode, @NotNull Long objectId);

    FileMng findOne(@NotNull Long id);

    FileMng update(@NotNull FileMng fileMng);

    /**
     * 文件删除（特定文件）
     * @param fileObjectTypeCode
     * @param sysFileLibId
     */
    void deleteBySysFileLibId(@NotNull FileObjectTypeCodeEnum fileObjectTypeCode, @NotNull Long sysFileLibId);
}
