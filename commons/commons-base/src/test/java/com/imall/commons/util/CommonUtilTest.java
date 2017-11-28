package com.imall.commons.util;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.Node;
import org.junit.Test;

/**
 * Created by ygw on 2016/10/21.
 */
public class CommonUtilTest {

    //@Test //如果源对象为null，会报错
    public void testCopyProperties(){
        Node oNode = new Node();
        oNode.setName("test");
        Node nNode = CommonUtil.copyProperties(null, oNode, oNode);
        System.out.println(nNode.getName());
    }
}
