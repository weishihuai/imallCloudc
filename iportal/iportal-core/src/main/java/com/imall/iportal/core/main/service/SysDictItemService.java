package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.valid.SysDictItemSaveValid;
import com.imall.iportal.core.main.valid.SysDictItemUpdateValid;
import com.imall.iportal.core.shop.vo.SysDictItemSimpleVo;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_DICT_ITEM【数据字典项】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysDictItemService{

    /**
     * 查询字典项列表，可按输入参数模糊匹配并按分页参数输出结果
     * @param pageable 当前页
     * @param pageable 每页条数
     * @param pageable 排序 如 dictNm desc,dictType desc
     * @param dictItemNm  字典 项 名称
     * @param dictItemCode 字典 项 编码
     * @param dataDictId 数据 字典 ID
     * @outparam result  查询结果列表
     * @outparam total   结果总数
     */
    Page<SysDictItem> query(@NotNull Pageable pageable, String dictItemNm, String dictItemCode, Long dataDictId);
    /**
     * 保存字典项
     * @param sysDictItemSaveValid
     * @return
     */
    SysDictItem save(@NotNull @Valid SysDictItemSaveValid sysDictItemSaveValid);

    /**
     * 更新字典项
     * @param sysDictItemUpdateValid
     * @return
     */
    SysDictItem update(@NotNull @Valid SysDictItemUpdateValid sysDictItemUpdateValid);

    /**
     *  根据主键ID查询字典项信息
     * @param id 主键ID
     * @return
     */
    SysDictItem findOne(@NotNull Long id);

    /**
     *查询数据字典项名称
     * @param dataDictId 数据 字典 ID
     * @return
     */
    List<String> findDictItemNmByDataDictId(@NotNull Long dataDictId);


    List<SysDictItem> findByDataDictId(@NotNull Long dataDictId);

    /**
     * 根据字典项的代码取字典
     * @param dictItemCode
     * @return
     */
    SysDictItem findByDictItemCode(@NotBlank String dictItemCode);

    /**
     *  删除字典项
     * @param sysDictItemIds 主键ID集合
     * @return
     */
    void  delete(@NotEmpty List<Long> sysDictItemIds);

    /**
     *  根据DICT_TYPE查找
     * @param dictType
     * @return
     */
    List<SysDictItem> findByDictType(@NotBlank String dictType);

    /**
     * DictType  和 是否可用 查找
     * @param dictType
     * @return
     */
    List<SysDictItemSimpleVo> findByAvailableAndDictType(@NotBlank String dictType);
}
