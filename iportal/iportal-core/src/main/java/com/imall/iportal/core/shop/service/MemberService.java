package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.entity.Member;
import com.imall.iportal.core.shop.vo.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
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
public interface MemberService {

    /**
     * 根据ID查询会员信息
     *
     * @param id 会员ID
     * @return
     */
    Member findOne(@NotNull Long id);

    /**
     * 会员列表
     *
     * @param pageable          分页对象
     * @param memberSearchParam 搜索参数
     * @return
     */
    Page<MemberPageVo> query(@NotNull Pageable pageable, MemberSearchParam memberSearchParam);

    /**
     * 保存会员信息
     *
     * @param memberSaveVo 会员新增对象
     * @return
     */
    void save(@NotNull @Valid MemberSaveVo memberSaveVo,CurrUserVo currUserVo) throws ParseException;

    /**
     * 更新会员信息
     *
     * @param memberUpdateVo 会员更新对象
     * @return
     */
    Long update(@NotNull @Valid MemberUpdateVo memberUpdateVo) throws ParseException;

    /**
     * 根据ID查询会员信息
     *
     * @param id     会员ID
     * @param shopId 门店ID
     * @return
     */
    MemberDetailVo findById(@NotNull Long id, @NotNull Long shopId);

    /**
     * 前端销售pos新增会员信息
     *
     * @param salesPosMemberSaveVo 会员信息
     * @return
     */
    Long saveSalesPosMember(@NotNull @Valid SalesPosMemberSaveVo salesPosMemberSaveVo);

    /**
     * pos根据会员手机或会员卡号查询会员信息并更新购物车
     *
     * @param searchText 手机或会员卡号
     * @param uniqueKey  购物车uniqueKey
     * @return
     */
    Member findMemberAndConvertCart(String searchText, String uniqueKey, Long shopId);

    /**
     * 检查会员卡号、手机号是否已经存在
     *
     * @param mobile 会员卡号、手机号
     * @param id     会员ID
     * @param shopId 门店ID
     * @return
     */
    Boolean checkMemberMobileIsExist(@NotBlank @Length(max = 32) String mobile, Long id, Long shopId);

    /**
     * 根据手机查找
     * @param mobile
     * @return
     */
    Member findByMobileAndShopId(@NotBlank @Length(max = 32) String mobile, @NotNull Long shopId);
}
