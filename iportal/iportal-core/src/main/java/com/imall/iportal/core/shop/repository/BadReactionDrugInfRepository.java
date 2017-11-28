
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.BadReactionDrugInf;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface BadReactionDrugInfRepository extends IBaseRepository<BadReactionDrugInf, Long>, BadReactionDrugInfRepositoryCustom {

    /**
     * 根据不良反应报告ID查询相关的药品信息
     *
     * @param badReactionRepId 不良反应报告ID
     * @return
     */
    @Query("SELECT a FROM BadReactionDrugInf a WHERE a.badReactionRepId =?1 AND a.drugInfTypeCode =?2")
    List<BadReactionDrugInf> listByBadReactionRepId(Long badReactionRepId, String drugInfTypeCode);

}

