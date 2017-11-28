package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.StaffHealthDocInfDetailVo;
import com.imall.iportal.core.shop.vo.StaffHealthDocInfSaveVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface StaffHealthDocInfService{

    /**
     * 批量保存信息
     */
    void saveStaffHealthDocInf(List<StaffHealthDocInfSaveVo> staffHealthDocInfSaveVos) throws ParseException;
    /**
     * 通过id获取供应商档案信息
     * @param id
     * @return
     */
    List<StaffHealthDocInfDetailVo> findByStaffHealthDocId(@NotNull Long id);
}
