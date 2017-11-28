package com.imall.iportal.core.main.service;


import com.imall.commons.base.util.Node;
import com.imall.iportal.core.main.entity.SysZone;
import com.imall.iportal.core.main.vo.ZoneTreeVo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * T_PT_SYS_ZONE【地区表】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysZoneService{

    /**
     * 构造省市区树
     * @return
     */
    List<ZoneTreeVo> findSysZoneTree();

    /**
     * 查询地区
     * @param zoneCode 地区节点编码
     * @return
     */
    SysZone findByZoneCode(@NotBlank String zoneCode);

    /**
     * id 区域id
     * @param
     * @result SysZoneMsg 区域对象
     */
    SysZone findOne(@NotNull Long id);

    /**
     * 查出子节点
     */
    List<SysZone> findByParentId(@NotNull Long parentId);

    /**
     * 查找子节点
     * @param parentId
     * @param subMaxLayer 返回的结果List<Node>里的Node子节点，最大有多少层子节点
     * @return
     */
    List<Node> findByParentId(@NotNull Long parentId, int subMaxLayer);

    /**
     * 需要根据id来拿到地区的全路径，比如根据天河区的id来拿到 广东-广州-天河区的数据
     * @param id
     * @return
     */
    List<Node> findByLastId(@NotNull Long id);

    /**
     * 根据末级地域Id获取其地区数据
     * @param zoneId
     * @return
     */
    Map<String, Object> findSysZoneTreeByZoneId(@NotNull Long zoneId);
}
