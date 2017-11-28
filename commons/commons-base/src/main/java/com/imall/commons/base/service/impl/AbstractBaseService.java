package com.imall.commons.base.service.impl;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.dao.IBaseRepository;
import com.imall.commons.base.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lxd on 2015/7/14.
 */
public abstract class AbstractBaseService<M extends BaseEntity, ID extends Serializable> implements IBaseService<M, ID> {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected IBaseRepository<M, ID> baseRepository;

    @Autowired
    public void setBaseRepository(IBaseRepository<M, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Transactional
    @Override
    public M save(M m) {
        if(m.getId()!=null){
            M dbM = findOne((ID)m.getId());
            m.setCreateBy(dbM.getCreateBy());
            m.setCreateDate(dbM.getCreateDate());
            m.setLastModifiedBy(dbM.getLastModifiedBy());
            m.setLastModifiedDate(dbM.getLastModifiedDate());
            m.setVersion(dbM.getVersion());
        }
        return this.baseRepository.save(m);
    }

    @Transactional
    @Override
    public M saveAndFlush(M m) {
        m = save(m);
        this.baseRepository.flush();
        return m;
    }

    @Transactional
    public M update(M m) {
        return save(m);
    }

    @Transactional
    @Override
    public void delete(ID id) {
        this.baseRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(M m) {
        this.baseRepository.delete(m);
    }

    @Transactional
    @Override
    public void delete(List<ID> ids) {
        this.baseRepository.delete(ids);
    }

    public M findOne(ID id) {
        return this.baseRepository.findOne(id);
    }

    public boolean exists(ID id) {
        return this.baseRepository.exists(id);
    }

    public long count() {
        return this.baseRepository.count();
    }

    public List<M> findAll() {
        return this.baseRepository.findAll();
    }

    public List<M> findAll(Sort sort) {
        return this.baseRepository.findAll(sort);
    }

    public Page<M> findAll(Pageable pageable) {
        return this.baseRepository.findAll(pageable);
    }

 /*   public Page<M> findAll(Specification<M> spec, Pageable pageable){
        return this.baseRepository.findAll(spec, pageable);
    }*/

}