package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.ReleaseNoticeDetailVo;
import com.imall.iportal.core.shop.vo.ReleaseNoticePageVo;
import com.imall.iportal.core.shop.vo.ReleaseNoticeSearchParam;
import com.imall.iportal.core.shop.vo.ReleaseSaleNoticeSaveVo;
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
public interface DrugReleaseNoticeService {

    /**
     * 解停单详情
     *
     * @param id
     * @param shopId
     * @return
     */
    ReleaseNoticeDetailVo findDetail(@NotNull Long id, @NotNull Long shopId);

    Long saveReleaseNotice(@NotNull @Valid ReleaseSaleNoticeSaveVo releaseSaleNoticeSaveVo);

    /**
     * 解停单列表
     *
     * @param pageable
     * @param releaseNoticeSearchParam
     * @param shopId
     * @return
     */
    Page<ReleaseNoticePageVo> query(Pageable pageable, ReleaseNoticeSearchParam releaseNoticeSearchParam, @NotNull Long shopId) throws ParseException;

}
