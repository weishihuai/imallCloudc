package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysJob;
import com.imall.iportal.core.main.valid.SysJobSaveValid;
import com.imall.iportal.core.main.valid.SysJobUpdateValid;
import com.imall.iportal.core.main.vo.SysJobVo;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_JOB【岗位/职务】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysJobService{

    /**
     * 查询岗位列表，可按输入参数模糊匹配并按分页参数输出结果
     * @param pageable 当前页
     * @param pageable 每页条数
     * @param pageable 排序 如 jobName desc,isAvailable desc
     * @param orgId  机构ID
     * @outparam result  查询结果列表
     * @outparam total   结果总数
     */
    Page<SysJobVo> query(@NotNull Pageable pageable, Long orgId);

    /**
     * 保存岗位
     * @param sysJobSaveValid
     * @return
     */
    SysJob save(@NotNull @Valid SysJobSaveValid sysJobSaveValid);

    /**
     * 更新岗位
     * @param sysJobUpdateValid
     * @return
     */
    SysJob update(@NotNull @Valid SysJobUpdateValid sysJobUpdateValid);

    /**
     *  根据主键ID查询岗位信息
     * @param id 主键ID
     * @return
     */
    SysJob findOne(@NotNull Long id);

    /**
     * 删除岗位
     * @param ids  主键Ids
     */
    void deleteSysJob(@NotEmpty List<Long> ids);


    /**
     * 根据某机构ID查找所有岗位(非管理员)
     * @param orgId
     * @return
     */
    List<SysJob> findByOrgId(@NotNull Long orgId);



    /**
     *
     * 检查编码是否已经存在
     * @return
     */
    Boolean checkJobCodeIsExist(@NotBlank @Length(max = 32) String name, Long id);

}
