package com.imall.iportal.core.elasticsearch;


import com.imall.commons.dicts.IndexTypeCodeEnum;

public interface IIndexProvider<E> {
    E getEntity(Long id);
    IndexTypeCodeEnum actionType();
}
