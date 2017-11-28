<#assign className = table.className>

package ${basepackage}.${moduleName}.repository.impl;

import ${basepackage}.${moduleName}.repository.${className}RepositoryCustom;
import javax.persistence.EntityManager;
import javax.annotation.Resource;

/**
 * ${table.remarks}(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class ${className}RepositoryImpl implements ${className}RepositoryCustom{

    @Resource
    private EntityManager entityManager;

}
