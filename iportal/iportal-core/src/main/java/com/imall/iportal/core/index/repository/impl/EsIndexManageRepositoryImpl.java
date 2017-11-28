
package com.imall.iportal.core.index.repository.impl;

import com.imall.iportal.core.index.entity.EsIndexManage;
import com.imall.iportal.core.index.repository.EsIndexManageRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * T_PT_ES_INDEX_MANAGE【索引管理】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class EsIndexManageRepositoryImpl implements EsIndexManageRepositoryCustom{

    @Resource
    private JdbcTemplate jdbcTemplate;


    public Page<EsIndexManage> query(Pageable pageable)  {
        StringBuffer sql = new StringBuffer();
        sql.append(" from t_pt_es_index_manage a where 1=1 ");
        Map<String, Object> paramMap = new HashMap<String, Object>();

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        Long total = new NamedParameterJdbcTemplate(this.jdbcTemplate).queryForObject("select count(*)"+ sql, paramMap, Long.class);
        List<EsIndexManage> indexManageList = new NamedParameterJdbcTemplate(this.jdbcTemplate)
                .query("select a.* " + QueryUtils.applySorting(sql.toString(),pageable.getSort(),"a") + " limit " + firstIdx + "," + pageSize, paramMap, new BeanPropertyRowMapper(EsIndexManage.class));
        return new PageImpl<EsIndexManage>(indexManageList, pageable, total);
    }
}
