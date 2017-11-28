package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.DocInfDetailVo;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface DocInfService{
    /**
     * 根据文档类型，获取文档信息
     * @param docType
     * @return
     */
    List<DocInfDetailVo> searchByDocType(@NotEmpty String docType);

    /**
     * 文件下载
     * @param id
     * @return
     */
    void downloadDoc(@NotNull Long id, @NotNull HttpServletResponse response);
}
