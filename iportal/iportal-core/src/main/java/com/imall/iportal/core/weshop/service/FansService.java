package com.imall.iportal.core.weshop.service;


import com.imall.iportal.core.weshop.entity.Fans;
import com.imall.iportal.core.weshop.vo.FansPageVo;
import com.imall.iportal.core.weshop.vo.FansRemarkUpdateVo;
import com.imall.iportal.core.weshop.vo.FansSearchParam;
import com.imall.iportal.core.weshop.vo.FansTotalVo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface FansService {

    /**
     * 根据open ID查找
     *
     * @param openId
     * @return
     */
    List<Fans> findByOpenId(@NotBlank String openId);

    /**
     * 根据 open id和门店ID查找
     *
     * @param openId
     * @param shopId
     * @return
     */
    Fans findByOpenIdAndShopId(@NotBlank String openId, @NotNull Long shopId);

    Fans save(Fans fans);

    Fans update(Fans fans);

    Fans findOne(@NotNull Long id);

    /**
     * 粉丝列表
     *
     * @param pageable        分页对象
     * @param fansSearchParam 搜索参数
     * @return
     */
    Page<FansPageVo> query(@NotNull Pageable pageable, FansSearchParam fansSearchParam);

    /**
     * 更新粉丝备注
     *
     * @param fansRemarkUpdateVo
     */
    Long updateRemark(@NotNull @Valid FansRemarkUpdateVo fansRemarkUpdateVo);

    /**
     * 粉丝列表 数量统计
     *
     * @param shopId 门店ID
     * @return
     */
    FansTotalVo countFansTotal(@NotNull Long shopId);

    /**
     * 根据手机号码查询粉丝
     *
     * @param mobile   手机号码
     * @param isMember 是否会员
     * @return
     */
    List<Fans> findByMobileAndIsMember(String mobile, String isMember);

    /**
     * 根据微信用户ID查找
     *
     * @param weChatUserId
     * @return
     */
    List<Fans> findByWeChatUserId(@NotNull Long weChatUserId);

    /**
     * 根据微信用户 id和门店ID查找
     *
     * @param weChatUserId
     * @param shopId
     * @return
     */
    Fans findByWeChatUserIdAndShopId(@NotNull Long weChatUserId, @NotNull Long shopId);
}
