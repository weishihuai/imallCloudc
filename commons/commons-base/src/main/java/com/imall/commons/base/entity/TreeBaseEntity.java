package com.imall.commons.base.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by yang on 2015-10-27.
 */
@MappedSuperclass
public abstract class TreeBaseEntity<ID extends Serializable> extends  BaseEntity<ID>{


    public static final String PARENT_ID = "parentId";
    public static final String NODE_CODE = "nodeCode";

    /** PARENT_ID - 父ID */
    @Column(name = "PARENT_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long parentId;

    @Column(name = "NODE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 1024)
    private java.lang.String nodeCode;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }
}
