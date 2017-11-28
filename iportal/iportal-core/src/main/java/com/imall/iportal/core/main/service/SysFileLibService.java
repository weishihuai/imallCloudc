package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysFileLib;
import com.imall.iportal.core.main.vo.SysFileLibSearchParamVo;
import com.imall.iportal.core.main.vo.SysFileLibVo;
import com.imall.iportal.core.main.vo.UploadFileVo;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_FILE_LIB【系统 文件 库】(服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysFileLibService {

    SysFileLib findOne(Long id);

    /**
     * 返回文件ID集合  SysFileId
     *
     * @return
     */
    List<String> saveFiles(@NotEmpty List<UploadFileVo> fileList, @NotNull Long categoryId, @NotNull Long orgId);

    /**
     * 返回文件ID集合
     *
     * @return
     */
    Page<SysFileLibVo> findFileLib(@NotNull Pageable pageable, SysFileLibSearchParamVo paramVo, @NotNull Long orgId);

    /**
     * 重命名文件
     */
    void renameFileName(@NotNull Long id, @NotBlank @Length(max = 32) String fileNm, @NotNull Long orgId);

    /**
     * 删除文件
     *
     * @param fileLibIds 主键ID集合
     */
    void delete(@NotEmpty List<Long> fileLibIds, @NotNull Long orgId);

    /**
     * 下载文件
     *
     * @param fileLibIds 主键ID集合
     * @return 文件下载路径
     */
    String downloadFiles(@NotEmpty List<Long> fileLibIds, @NotNull Long orgId);

    /**
     * 移动文件分类
     *
     * @param fileLibIds 主键ID集合
     */
    void changeFileCategory(@NotEmpty List<Long> fileLibIds, @NotNull Long fileCategoryId, @NotNull Long orgId);

    /**
     * 文件下载（按原文件名的形式下载）
     * @param id 主键ID
     * @param response
     */
    void downloadFile(@NotNull Long id, @NotNull HttpServletResponse response);

    SysFileLib save(@NotNull SysFileLib sysFileLib);
}
