package com.imall.commons.base.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoRepositoryBean
public interface IBaseRepository<M, ID extends java.io.Serializable> extends org.springframework.data.jpa.repository.JpaRepository<M,ID> {

    void delete(List<ID> ids);

   // Page<M> findAll(Specification<M> spec, Pageable pageable);

}
