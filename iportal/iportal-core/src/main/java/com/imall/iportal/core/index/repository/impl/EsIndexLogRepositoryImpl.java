
package com.imall.iportal.core.index.repository.impl;

import com.imall.iportal.core.index.repository.EsIndexLogRepositoryCustom;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.annotation.Resource;

/**
 * T_PT_ES_INDEX_LOG【索引日志】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class EsIndexLogRepositoryImpl implements EsIndexLogRepositoryCustom{

    @Resource
    private JdbcTemplate jdbcTemplate;

}
