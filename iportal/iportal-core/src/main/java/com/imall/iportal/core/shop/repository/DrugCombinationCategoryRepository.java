
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.DrugCombinationCategory;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugCombinationCategoryRepository extends  IBaseRepository<DrugCombinationCategory, Long>,DrugCombinationCategoryRepositoryCustom {

    /**
     * 根据分类名称查询
     * @param categoryNm
     * @return
     */
    @Query("SELECT D FROM DrugCombinationCategory D WHERE D.orgId = ?1 AND D.categoryNm = ?2")
    List<DrugCombinationCategory> listByCategoryNm(Long orgId, String categoryNm);

    /**
     * 根据机构 ID 查询
     * @param orgId 机构 ID
     * @param adminDefaultOrgId 平台机构 ID
     * @return
     */
    @Query("SELECT D FROM DrugCombinationCategory D WHERE D.orgId = ?1 OR D.orgId = ?2 ORDER BY D.orgId DESC, D.id DESC")
    List<DrugCombinationCategory> listAllByOrgId(Long orgId, Long adminDefaultOrgId);

    @Query("SELECT D FROM DrugCombinationCategory D WHERE D.orgId = ?1 and D.id = ?2")
    DrugCombinationCategory findOne(Long orgId, Long id);

    @Query("SELECT D FROM DrugCombinationCategory D WHERE D.id = ?1")
    DrugCombinationCategory findOne(Long id);
}

