package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.ReceiverAddr;
import com.imall.iportal.core.shop.vo.ReceiverAddrSaveVo;
import com.imall.iportal.core.shop.vo.ReceiverAddrUpdateVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 收货 地址(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface ReceiverAddrService{

    ReceiverAddr findOne(@NotNull Long id);

    /**
     * 保存收货地址
     * @param saveVo
     * @return
     */
    Long saveAddr(@Valid @NotNull ReceiverAddrSaveVo saveVo);

    /**
     * 更新收货地址
     * @param updateVo
     * @return
     */
    Long updateAddr(@Valid @NotNull ReceiverAddrUpdateVo updateVo);

    /**
     * 根据微信用户ID找出收货地址
     * @param weChatUserId
     * @return
     */
    List<ReceiverAddr> findByWeChatUserId(@NotNull Long weChatUserId);

    /**
     * 根据主键ID和粉丝ID删除收货地址
     * @param id
     * @param weChatUserId
     */
    void deleteByIdAndWeChatUserId(@NotNull Long id, @NotNull Long weChatUserId);

    /**
     * 根据主键和粉丝ID查找收货地址
     * @param id
     * @param weChatUserId
     * @return
     */
    ReceiverAddr findByIdAndWeChatUserId(@NotNull Long id, @NotNull Long weChatUserId);

    /**
     * 取默认的收货地址
     * @param weChatUserId
     * @return
     */
    ReceiverAddr findDefaultByWeChatUserId(@NotNull Long weChatUserId);
}
