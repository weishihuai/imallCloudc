package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.*;
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
public interface BadReactionRepService {

    /**
     * 不良反应报告列表
     *
     * @param pageable                  分页对象
     * @param badReactionRegSearchParam 搜索参数
     * @return
     */
    Page<BadReactionRepPageVo> query(Pageable pageable, @NotNull @Valid BadReactionRegSearchParam badReactionRegSearchParam) throws ParseException;

    /**
     * 根据不良反应报告ID查询详情
     *
     * @param id     不良反应报告主键ID
     * @param shopId 门店id
     * @return
     */
    BadReactionRepDetailVo findById(@NotNull Long id, @NotNull Long shopId);

    /**
     * 保存不良反应报告 信息
     *
     * @param badReactionRepSaveVo 不良反应报告保存对象
     * @throws ParseException
     */
    void saveBadReactionRep(@NotNull @Valid BadReactionRepSaveVo badReactionRepSaveVo) throws ParseException;

    /**
     * 更新不良反应报告 信息
     *
     * @param badReactionRepUpdateVo 不良反应报告更新对象
     * @throws ParseException
     */
    void updateBadReactionRep(@NotNull @Valid BadReactionRepUpdateVo badReactionRepUpdateVo) throws ParseException;

}
