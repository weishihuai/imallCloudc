package com.imall.commons.base.dao.commons;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 自动生成下一级节点的code
 */

@Component
public class TreeCodeGenerateBuilder {

    @PersistenceContext
    private EntityManager em;

    public String generateDefaultCode(Class table , Long id){
        return generateCode(table, id, "nodeCode", "0000");
    }

    public String generate5Code(Class table , Long id){
        return generateCode(table, id, "nodeCode", "00000");
    }

    public String generate6Code(Class table , Long id){
        return generateCode(table, id, "nodeCode", "000000");
    }

    public String generate7Code(Class table , Long id){
        return generateCode(table, id, "nodeCode", "0000000");
    }

    public String generate8Code(Class table , Long id){
        return generateCode(table, id, "nodeCode", "00000000");
    }

    public String generate9Code(Class table , Long id){
        return generateCode(table, id, "nodeCode", "000000000");
    }

    /**
     *
     * @param table 表
     * @param id 在哪个节点下新增节点，就传哪个节点的ID
     * @return
     */
    public String generateCode(Class table , Long id, String nodeCodeName, String pattern){
        java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
        if(id==null){
            return df.format(1);
        }

        String codeSql = "select "+ nodeCodeName +" from " + table.getSimpleName() +" where Id = "+ id;
        Query codeQuery = em.createQuery(codeSql);
        String code = (String)codeQuery.getSingleResult();

        String maxIdSql = "select max(id) from "+ table.getSimpleName()+" where parentId = "+id;
        Query maxIdQuery = em.createQuery(maxIdSql);
        Long maxId = (Long)maxIdQuery.getSingleResult();

        Long genCode = null;
        if(maxId!=null){
            String maxCodeSql = "select "+ nodeCodeName +" from " + table.getSimpleName() +" where Id = "+ maxId;
            Query maxCodeQuery = em.createQuery(maxCodeSql);
            String maxCode = (String)maxCodeQuery.getSingleResult();
            maxCode = maxCode.substring(maxCode.length() - pattern.length(), maxCode.length());
            genCode = Long.parseLong(maxCode);
        }
        return code + df.format((genCode==null?0:genCode )+ 1);
    }
}
