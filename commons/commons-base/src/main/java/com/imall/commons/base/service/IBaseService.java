package com.imall.commons.base.service;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lxd on 2015/7/14.
 */
public interface IBaseService<M extends BaseEntity, ID extends Serializable>
{
    public abstract void setBaseRepository(IBaseRepository<M, ID> paramBaseRepository);

    public abstract M save(M paramM);

    public abstract M saveAndFlush(M paramM);

    public abstract void delete(ID paramID);

    public abstract void delete(M paramM);

    public abstract void delete(List<ID> ids);

    public abstract M findOne(ID paramID);

    public abstract boolean exists(ID paramID);

    public abstract long count();

    public abstract List<M> findAll();

    public abstract List<M> findAll(Sort paramSort);

    public abstract Page<M> findAll(Pageable paramPageable);

   /* public abstract Page<M> findAll(Specification<M> spec, Pageable pageable);*/

}
