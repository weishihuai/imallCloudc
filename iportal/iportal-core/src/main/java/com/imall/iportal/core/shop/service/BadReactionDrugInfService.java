package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.BadReactionDrugInf;
import com.imall.iportal.core.shop.vo.BadReactionRepDrugInfDetailVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface BadReactionDrugInfService {

    /**
     * 根据ID查询不良反应报告药品信息
     *
     * @param id 主键ID
     * @return
     */
    BadReactionDrugInf findOne(@NotNull Long id);

    /**
     * 保存不良反应报告 药品信息
     *
     * @param badReactionDrugInf 不良反应报告 药品信息
     * @return
     */
    BadReactionDrugInf save(@NotNull BadReactionDrugInf badReactionDrugInf);

    /**
     * 更新不良反应报告 药品信息
     *
     * @param badReactionDrugInf 不良反应报告 药品信息
     * @return
     */
    BadReactionDrugInf update(@NotNull BadReactionDrugInf badReactionDrugInf);

    /**
     * 根据不良反应报告ID查询相关的药品信息
     *
     * @param badReactionRepId 不良反应报告ID
     * @return
     */
    List<BadReactionDrugInf> listByBadReactionRepId(Long badReactionRepId, String drugInfTypeCode);

    /**
     * 根据不良反应报告ID 查询药品信息
     * @param id 不良反应报告ID
     * @return
     */
    BadReactionRepDrugInfDetailVo findByBadReactionRepId(Long id);
}
