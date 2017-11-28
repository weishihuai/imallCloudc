package com.imall.iportal.core.platform.service;


import com.imall.iportal.core.platform.entity.ShopCertificatesFile;
import com.imall.iportal.core.platform.vo.ShopCertificatesFileSaveVo;
import com.imall.iportal.core.platform.vo.ShopCertificatesFileUpdateVo;
import com.imall.iportal.core.platform.vo.ShopCertificatesFileVo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ShopCertificatesFileService{

    /**
     * 通过门店id获取资质文件列表
     * @param shopId
     * @return
     */
    List<ShopCertificatesFileVo> ListShopCertificatesFilesList(@NotNull Long shopId);

    /**
     * 修改
     * @param shopCertificatesFileUpdateVo
     * @return
     * @throws ParseException
     */
    Long updateByShopIdAndCertificatesType(@NotNull @Valid ShopCertificatesFileUpdateVo shopCertificatesFileUpdateVo) throws ParseException;

    /**
     * 保存
     * @param supplierDocCertificatesFileSaveVo
     * @return
     * @throws ParseException
     */
    Long saveShopCertificatesFile(@NotNull @Valid ShopCertificatesFileSaveVo supplierDocCertificatesFileSaveVo) throws ParseException;

    /**
     * 查询门店对应资质
     * @param shopId 门店id
     * @param certificatesType 资质类型
     * @return 返回资质对象
     */
    ShopCertificatesFile findByShopIdAndCertificatesType(Long shopId, String certificatesType);

}
