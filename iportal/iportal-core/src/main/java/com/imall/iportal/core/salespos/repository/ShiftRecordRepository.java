
package com.imall.iportal.core.salespos.repository;

import com.imall.iportal.core.salespos.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 交班 记录(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ShiftRecordRepository extends  IBaseRepository<ShiftRecord, Long>,ShiftRecordRepositoryCustom {

    //shopId posMan 可能会返回2条，一条succeedWhoId为null的，另一条succeedWhoId不为null的
    @Query("select sr from ShiftRecord sr where sr.shopId=?1 and sr.posMan = ?2 and sr.isHasShift = 'N' order by sr.succeedTime desc")
    List<ShiftRecord> findByShopIdAndPosManAndNotShift(Long shopId, Long posMan);

    //shopId posMan succeedWhoId 返回唯一一条
    @Query("select sr from ShiftRecord sr where sr.shopId=?1 and sr.posMan = ?2 and sr.succeedWhoId = ?3 and sr.isHasShift = 'N' order by sr.succeedTime desc")
    ShiftRecord findByShopIdAndPosManSucceedWhoIdAndNotShift(Long shopId, Long posMan, Long succeedWhoId);

    @Query("select sr from ShiftRecord sr where sr.id=?1 and sr.shopId=?2 and sr.posMan = ?3")
    ShiftRecord findByIdShopIdAndPosMan(Long id, Long shopId, Long posMan);


}

