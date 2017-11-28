package com.imall.iportal.core.platform.service;


import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.iportal.core.platform.entity.GoodsDoc;
import com.imall.iportal.core.platform.vo.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsDocService {

    /**
     * 商品档案列表
     * @param pageable
     * @param goodsDocListSearchParam
     * @return
     */
    Page<GoodsDocListPageVo> query(Pageable pageable, GoodsDocListSearchParam goodsDocListSearchParam) throws ParseException;

    /**
     * 保存商品档案
     * @param goodsDocSaveVo
     * @return
     */
    Long saveGoodsDoc(@NotNull @Valid GoodsDocSaveVo goodsDocSaveVo);

    /**
     * 查找商品档案详细
     * @param id
     * @return
     */
    GoodsDocDetailVo findDetail(@NotNull Long id);

    /**
     * 编辑商品档案
     * @param goodsDocUpdateVo
     * @return
     */
    Long updateGoodsDoc(@NotNull @Valid GoodsDocUpdateVo goodsDocUpdateVo) throws ParseException;

    /**
     * 检查该商品编码是否可用
     * @param goodsCode
     * @param id
     * @return
     */
    Boolean findGoodsCodeExist(@NotBlank String goodsCode, Long id);

    /**
     *
     * @param goodsCode
     * @return
     */
    GoodsDoc findByGoodsCode(String goodsCode);

    /**
     * 审核拒绝
     * @param id
     * @return
     */
    Boolean updateDenyApprove(@NotNull Long id);

    /**
     * 审核通过
     * @param id
     * @return
     */
    Boolean updatePassApprove(@NotNull Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    Boolean updateDelete(@NotNull Long id);

    /**
     * 导出所有符合搜索条件的数据
     * @param pageable
     * @param goodsDocListSearchParam
     * @return
     */
    Boolean exportExcel(@NotNull String webRealPath, @NotNull String xlsTemplatePath, @NotNull String xlsOutputPath, @NotNull Pageable pageable, @NotNull GoodsDocListSearchParam goodsDocListSearchParam);

    /**
     * 导入
     * @param localFileId 物理文件地址
     * @return
     */
    List<ErrorLog> importData(String localFileId);
}
