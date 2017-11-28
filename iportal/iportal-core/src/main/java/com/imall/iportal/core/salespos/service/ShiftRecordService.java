package com.imall.iportal.core.salespos.service;


import com.imall.iportal.core.salespos.entity.ShiftRecord;
import com.imall.iportal.core.salespos.vo.ShiftRecordVo;
import com.imall.iportal.core.shop.vo.ShiftRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 交班 记录(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface ShiftRecordService{

    /**
     * 分页查询交班记录信息
     * @param pageable
     * @param searchParam
     * @return
     */
    Page<ShiftRecordVo> findPage(@NotNull Pageable pageable, @NotNull @Valid ShiftRecordSearchParam searchParam);

    /**
     * 登录后，初始化一条交班记录
     * @param shopId 门店 ID
     * @param posMan 收款 员 ID
     * @param succeedWhoId 接谁的班
     * @param succeedTime 接班时间
      * @return
     */
    ShiftRecord saveRecord(@NotNull Long shopId, @NotNull Long posMan, Long succeedWhoId, @NotNull Date succeedTime);

    /**
     * 查找准备交班操作的记录（根据某收款员查找最后一条未交班的记录）
     * @param shopId
     * @param posMan
     * @return
     */
    ShiftRecordVo findReadyShiftByShopIdAndPosMan(@NotNull Long shopId, @NotNull Long posMan);

    /**
     * 查找准备交班操作的记录（根据某收款员查找最后一条未交班的记录）
     * @param shopId
     * @param posMan
     * @return
     */
    ShiftRecord findLastNotShift(@NotNull Long shopId, @NotNull Long posMan);

    void updateRecord(@NotNull Long id, @NotNull Long shopId, @NotNull Long posMan, @NotNull Date shiftTime, @NotNull Double succeedReadyAmount,  @NotNull Double keepReadyAmount, @NotNull Double handoverCashAmount);

}
