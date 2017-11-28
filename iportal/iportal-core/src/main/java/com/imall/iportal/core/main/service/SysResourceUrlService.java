package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysResourceUrl;
import com.imall.iportal.core.main.valid.SysResourceUrlSaveValid;
import com.imall.iportal.core.main.valid.SysResourceUrlUpdateValid;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_RESOUCE_URL【资源URL】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysResourceUrlService{

    /**
     * 分页获取资源URL
     * @param pageable
     * @param resourceId
     * @param url  有传入值时，根据url过滤
     * @return
     */
    Page<SysResourceUrl> query(@NotNull Pageable pageable, Long resourceId , String url);

    /**
     * 查询资源URL信息
     * @param resourceId 资源ID
     * @return
     */
    List<SysResourceUrl> findByResourceId(@NotNull Long resourceId);

    /**
     * 删除资源Url
     * @param ids
     */
    void delete(@NotEmpty List<Long> ids);

    /**
     * 新增资源Url
     * @param sysResourceUrlSaveValid 资源Url对象
     */
    SysResourceUrl save(@NotNull @Valid SysResourceUrlSaveValid sysResourceUrlSaveValid);

    /**
     * 修改资源Url
     * @param sysResourceUrlUpdateValid 资源Url对象
     */
    SysResourceUrl update(@NotNull @Valid SysResourceUrlUpdateValid sysResourceUrlUpdateValid);
}
