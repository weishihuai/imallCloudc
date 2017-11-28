package com.imall.iportal.core.main.service;


import com.imall.commons.base.util.Node;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_FILE_CATEGORY 【文件分类】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysFileCategoryService{

    /**
     * 根据父节点ID获取文件分类树
     * @param id 分类ID
     * @param orgId 组织ID
     * @result NodeMsgList 查询结果列表
     */
    List<Node> buildFileCategoryTree(Long id, Boolean isIncludeRoot, @NotNull Long orgId);

    /**
     * 新增文件分类
     * @param id 文件分类ID
     * @param categoryName 文件分类名称
     * @param orgId 组织ID
     */
    Long saveCategory(@NotNull Long id , @NotBlank @Length(max = 32) String categoryName, @NotNull Long orgId);

    /**
     * 修改文件分类
     * @param id 文件分类ID
     * @param categoryName 文件分类名称
     * @param orgId 组织ID
     */
    Long updateCategory(@NotNull Long id ,@NotBlank @Length(max = 32) String categoryName, @NotNull Long orgId);

    /**
     * 删除文件分类
     * @param id 文件分类ID
     * @param orgId 组织ID
     */
    void deleteFileCategory(@NotNull Long id, @NotNull Long orgId);
}
