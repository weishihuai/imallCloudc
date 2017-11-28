package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.OutInStockLog;
import com.imall.iportal.core.shop.vo.OutInStockLogPageVo;
import com.imall.iportal.core.shop.vo.OutInStockLogSearchParam;
import com.imall.iportal.core.shop.vo.OutInStockLogStatisticsPageVo;
import com.imall.iportal.core.shop.vo.OutInStockStatisticsSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface OutInStockLogService {

    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    OutInStockLog findOne(@NotNull Long id);

    /**
     * 出入库明细列表查询
     *
     * @param pageable    分页
     * @param searchParam 查询条件
     * @return Page<OutInStockLogPageVo>
     */
    Page<OutInStockLogPageVo> query(Pageable pageable, @Valid @NotNull OutInStockLogSearchParam searchParam);

    /**
     * 根据时间段查询出入库台帐
     *
     * @param pageable    分页
     * @param searchParam 分页
     * @return Page<OutInStockLogStatisticsPageVo>
     */
    Page<OutInStockLogStatisticsPageVo> queryOutInStockStatistics(Pageable pageable, @Valid @NotNull OutInStockStatisticsSearchParam searchParam);

    /**
     * 查找出入库日志数据插入到索引队列
     *
     * @return 插入索引队列出入库日志数量
     */
    Integer queryOutInStockLogToQueue();

    /**
     * 保存出入库日志对象
     * @param outInStockLog
     * @return
     */
    OutInStockLog save(OutInStockLog outInStockLog);

}
