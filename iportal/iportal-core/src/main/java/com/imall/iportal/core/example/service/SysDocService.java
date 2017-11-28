package com.imall.iportal.core.example.service;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.iportal.core.example.entity.SysDoc;
import com.imall.iportal.core.example.valid.SysDocSaveValid;
import com.imall.iportal.core.example.valid.SysDocUpdateValid;
import com.imall.iportal.core.example.vo.DocParamVo;
import com.imall.iportal.core.example.vo.SysDocVo;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface SysDocService{

    /**
     * 根据 ID 取文档信息
     * @param paramID
     * @return
     * @throws BusinessException
     */
    SysDoc findOne(@NotNull Long paramID);

    /**
     * 保存文档信息
     * @param sysDocSaveValid
     * @throws BusinessException
     */
    SysDoc save(@NotNull @Valid SysDocSaveValid sysDocSaveValid);

    /**
     * 更新文档信息
     * @param sysDocUpdateValid
     * @throws BusinessException
     */
    SysDoc update(@NotNull @Valid SysDocUpdateValid sysDocUpdateValid);

    /**
     * 删除文档
     * @param docId
     * @return
     * @throws BusinessException
     */
    void delete(@NotNull Long docId);

    /**
     * 批量删除文档
     * @param docIdList
     * @return
     * @throws BusinessException
     */
    void delete(@NotEmpty List<Long> docIdList);

    /**
     * 查询文档信息
     * @param page 当前页
     * @param size 每页条数
     * @param orders 排序 id,
     * @param title  标题
     * @param summary  摘要
     * @param contStr  内容
     * @param isAvailable  是否可用
     * @outparam result  查询结果列表
     * @outparam total   结果总数
     * @throws BusinessException
     */

    Page<SysDocVo> query(@NotNull Pageable pageable, DocParamVo paramsVo);

    SysDoc saveDoc(@NotNull SysDoc sysDoc) throws BusinessException;

    void deleteByDocIds(@NotEmpty List<Long> docIdList);

    Integer queryDocToQueue();

}
