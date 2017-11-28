package com.imall.commons.base.util;

import java.util.List;

/**
 * Created by Administrator on 2015/9/23.
 */
public class Node {
    /*ID*/
    private Long id;
    /*节点名称*/
    private String name;
    /*节点代码*/
    private String nodeCode;
    /*是否父节点*/
    private boolean isParent;
    /*父ID*/
    private Long pId;
    /*是否打开*/
    private boolean open;
    /*是否选中*/
    private boolean checked;

    private Object nodeObject;

    /*子节点*/
    List<Node> children;

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public Long getPid() {
        return pId;
    }

    public void setPid(Long pId) {
        this.pId = pId;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public Object getNodeObject() {
        return nodeObject;
    }

    public void setNodeObject(Object nodeObject) {
        this.nodeObject = nodeObject;
    }
}
