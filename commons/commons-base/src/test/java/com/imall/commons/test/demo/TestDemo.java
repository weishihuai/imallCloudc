package com.imall.commons.test.demo;

import com.imall.commons.demo.entity.Demo;
import com.imall.commons.demo.service.IDemoService;
import com.imall.test.test.SpringTransactionalTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxd on 2015/7/14.
 */
@ContextConfiguration(locations = { "/spring/applicationContext.xml" })
public class TestDemo extends SpringTransactionalTestCase {

    @Autowired
    private IDemoService demoService;

    private final static int initDataCount = 3; // imall-future\commons\commons-base\trunk\src\test\resources\data\h2\test-init-data.sql

//    @Test
//    public void testSave(){
//        Demo demo = new Demo();
//        demo.setEmail("zhangsan@163.com");
//        demo.setLoginName("zhangsan");
//        demo.setName("张三");
//        demo.setPassword("123456");
//        demoService.save(demo);
//        Assert.assertNotNull(demo.getId());
//
//        demo = new Demo();
//        demo.setEmail("lisi@163.com");
//        demo.setLoginName("lisi");
//        demo.setName("李四");
//        demo.setPassword("123456");
//        demoService.save(demo);
//
//        demo.setEmail("yang@163.com");
//        demo.setLoginName("yang");
//        demo.setName("杨杨杨");
//        demo.setPassword("123456");
//        demoService.save(demo);
//    }

    @Test
    public void testSaveAndFlush(){
        Demo demo = new Demo();
        demo.setEmail("zhangsan@163.com");
        demo.setLoginName("zhangsan");
        demo.setName("张三");
        demo.setPassword("123456");
        demoService.saveAndFlush(demo);
        Assert.assertNotNull(demo.getId());
    }

    @Test
    public void testDeleteByEntity(){
        List<Demo> list = demoService.findAll();
        Assert.assertTrue(list.size() > 0);
        Demo demo = demoService.findOne(list.get(0).getId());
        Assert.assertNotNull(demo);
        demoService.delete(demo);
        demo = demoService.findOne(demo.getId());
        Assert.assertNull(demo);
    }

    @Test
    public void testDeleteById(){
        List<Demo> list = demoService.findAll();
        Assert.assertTrue(list.size() > 0);
        Demo demo = demoService.findOne(list.get(0).getId());
        Assert.assertNotNull(demo);
        demoService.delete(demo.getId());
        demo = demoService.findOne(demo.getId());
        Assert.assertNull(demo);
    }

    @Test
    public void testDeleteByIds(){
        List<Demo> list = demoService.findAll();
        Assert.assertTrue(list.size()==initDataCount);
        List<Long> idList = new ArrayList<>();
        for(Demo demo:list){
            idList.add(demo.getId());
        }
        demoService.delete(idList);
        Assert.assertTrue(demoService.count()==0);
    }


    @Test
    public void testFindOne(){
        List<Demo> list = demoService.findAll();
        Assert.assertTrue(list.size() > 0);
        Demo demo = demoService.findOne(list.get(0).getId());
        Assert.assertNotNull(demo);
        System.out.println(demo.toString());
    }

    @Test
    public void testExists(){
        List<Demo> list = demoService.findAll();
        Assert.assertTrue(list.size() > 0);
        boolean exists = demoService.exists(list.get(0).getId());
        Assert.assertTrue(exists);
    }


    @Test
    public void testCount(){
        Long count = demoService.count();
        Assert.assertTrue(count==initDataCount);
    }


    @Test
    public void testFindAll(){
        List<Demo> list = demoService.findAll();
        for (Demo demo:list){
            System.out.println(demo.toString());
        }
        Assert.assertTrue(list.size()==initDataCount);
    }

    @Test
      public void testFindAllSort(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<Demo> list = demoService.findAll(sort);
        for (Demo demo:list){
            System.out.println(demo.toString());
        }
        Assert.assertTrue(list.size()>=initDataCount && list.get(0).getId() > list.get(1).getId());
    }

    @Test
    public void testFindPage(){
        Pageable pageable = new PageRequest(2, 1, new Sort(Sort.Direction.DESC,"id"));
        Page<Demo> page = demoService.findAll(pageable);
        for (Demo demo:page.getContent()){
            System.out.println(demo.toString());
        }
        Assert.assertTrue(page.getTotalPages()==initDataCount);
    }

}
