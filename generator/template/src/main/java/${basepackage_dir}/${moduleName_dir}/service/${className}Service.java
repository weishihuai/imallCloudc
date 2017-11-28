<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${moduleName}.service;


import ${basepackage}.${moduleName}.entity.${className};
import org.springframework.validation.annotation.Validated;

/**
 * ${table.remarks}(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface ${className}Service{

}
