package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysDict;
import com.imall.iportal.core.main.valid.SysDictSaveValid;
import com.imall.iportal.core.main.valid.SysDictUpdateValid;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_DICT【数据字典】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysDictService{

    /**
     * 查询字典列表，可按输入参数模糊匹配并按分页参数输出结果
     * @param pageable 当前页
     * @param pageable 每页条数
     * @param pageable 排序 如 dictNm desc,dictType desc
     * @param dictNm   字典 名称
     * @param dictType 字典 类型
     * @param isAvailable 是否可用
     * @outparam result  查询结果列表
     * @outparam total   结果总数
     */
    Page<SysDict> query(@NotNull Pageable pageable, String dictNm, String dictType, String isAvailable);
    /**
     * 保存字典
     * @param sysDictSaveValid
     * @return
     */
    SysDict save(@NotNull @Valid SysDictSaveValid sysDictSaveValid);

    /**
     * 更新字典
     * @param sysDictUpdateValid
     * @return
     */
    SysDict update(@NotNull @Valid SysDictUpdateValid sysDictUpdateValid);

    /**
     *  根据主键ID查询字典信息
     * @param id 主键ID
     * @return
     */
    SysDict findOne(@NotNull Long id);

    /**
     *  删除字典
     * @param sysDictIds 主键ID集合
     * @return
     */
    void  deleteSysDict(@NotEmpty List<Long> sysDictIds);

    /**
     *
     * @param dictType
     * @return
     */
    SysDict findByDictType(@NotBlank String dictType);


}
