


<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package ${basepackage}.${moduleName}.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.math.BigDecimal;
/**
 * ${table.remarks}实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "${table.sqlName}" )
public class ${className} extends BaseEntity<Long>{

	<#list table.columns as column><#if column.pk || column.columnNameLower == "createBy" || column.columnNameLower == "createDate" || column.columnNameLower == "lastModifiedBy" || column.columnNameLower == "lastModifiedDate" || column.columnNameLower == "version" ><#else>
	public static final String ${column.constantName} = "${column.columnNameLower}";
    </#if></#list>

	<#list table.columns as column><#if column.pk || column.columnNameLower == "createBy" || column.columnNameLower == "createDate" || column.columnNameLower == "lastModifiedBy" || column.columnNameLower == "lastModifiedDate" || column.columnNameLower == "version" ><#else>
    /** ${column.sqlName} - ${column.columnAlias} */
    @Column(name = "${column.sqlName}", unique = ${column.unique?string}, nullable = ${column.nullable?string}, insertable = true, updatable = true, length = ${column.size})
    private ${column.javaType} ${column.columnNameLower};
    </#if></#list>
<@generateJavaColumns/>
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
		<#list table.columns as column>
			.append("${column.constantName}",get${column.columnName}())
		</#list>
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
		<#list table.pkColumns as column>
			.append(get${column.columnName}())
		</#list>
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		<#list table.columns as column>
		<#if column.pk>
		if(this.get${column.columnName}() == null){
			return false;
		}
		</#if>
		</#list>
		if(!(obj instanceof ${className})){
			return false;
		}
		if(this == obj) {
			return true;
		}
		${className} other = (${className})obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
			.append(get${column.columnName}(),other.get${column.columnName}())
			</#list>
			.isEquals();
	}
}

<#macro generateJavaColumns><#list table.columns as column><#if column.pk || column.columnNameLower == "createBy" || column.columnNameLower == "createDate" || column.columnNameLower == "lastModifiedBy" || column.columnNameLower == "lastModifiedDate" || column.columnNameLower == "version" ><#else>

    public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}

    public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
</#if></#list>

</#macro>


