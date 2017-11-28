package com.imall.commons.demo.service.impl;

import com.imall.commons.demo.entity.Demo;
import com.imall.commons.demo.repository.IDemoRepository;
import com.imall.commons.demo.service.IDemoService;
import com.imall.commons.base.service.impl.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lxd on 2015/7/14.
 */
@Service
public class DemoService extends AbstractBaseService<Demo, Long> implements IDemoService{


    private IDemoRepository demoRepository;

    @Autowired
    public void setDemoRepository(IDemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }
}
