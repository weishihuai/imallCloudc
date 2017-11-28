<#assign className = table.className>

package ${basepackage}.${moduleName}.repository;

import ${basepackage}.${moduleName}.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
/**
 * ${table.remarks}(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ${className}Repository extends  IBaseRepository<${className}, Long>,${className}RepositoryCustom {


}

