package com.imall.iportal.core.example.repository;

import com.imall.iportal.core.example.vo.DocParamVo;
import com.imall.iportal.core.example.vo.SysDocVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by ygw on 2015/12/31.
 */
public interface SysDocRepositoryCustom {

    Page<SysDocVo> query(Pageable pageable, DocParamVo paramsVo);

    Integer queryDocToQueue();
}
