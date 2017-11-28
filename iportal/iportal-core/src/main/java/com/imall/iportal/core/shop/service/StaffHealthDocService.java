package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.StaffHealthDoc;
import com.imall.iportal.core.shop.vo.StaffHealthDocDetailVo;
import com.imall.iportal.core.shop.vo.StaffHealthDocPageVo;
import com.imall.iportal.core.shop.vo.StaffHealthDocSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface StaffHealthDocService{
    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    StaffHealthDocDetailVo findById(@NotNull Long id,Long shopId);
    /**
     * 列表
     *
     * @param pageable
     * @param staffHealthDocSearchParam
     * @return
     */
    Page<StaffHealthDocPageVo> queryStaffHealthDoc(@NotNull Pageable pageable, @NotNull StaffHealthDocSearchParam staffHealthDocSearchParam) throws ParseException;

    /**
     * 保存
     * @param staffHealthDoc
     */
    void saveStaffHealthDoc(StaffHealthDoc staffHealthDoc);
}
