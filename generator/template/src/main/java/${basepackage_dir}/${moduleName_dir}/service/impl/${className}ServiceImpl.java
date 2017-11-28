<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${moduleName}.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.${moduleName}.entity.${className};
import ${basepackage}.${moduleName}.repository.${className}Repository;
import ${basepackage}.${moduleName}.service.${className}Service;
import com.imall.commons.base.service.impl.AbstractBaseService;
/**
 * ${table.remarks}(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class ${className}ServiceImpl extends AbstractBaseService<${className}, Long> implements ${className}Service {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ${className}Repository get${className}Repository() {
		return (${className}Repository) baseRepository;
	}

}