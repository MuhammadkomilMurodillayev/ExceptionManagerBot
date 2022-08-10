package com.example.errormanager.api.service;

import com.example.errormanager.api.criteria.BaseCriteria;
import com.example.errormanager.api.domain.BaseDomain;
import com.example.errormanager.api.dto.BaseDTO;
import com.example.errormanager.api.dto.BaseGenericDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @author Muhammadkomil Murodillayev, ср 12:00. 8/10/22
 */
public interface BaseCrudService<
        E extends BaseDomain,
        D extends BaseGenericDTO,
        CD extends BaseDTO,
        UD extends BaseGenericDTO,
        K extends Serializable,
        C extends BaseCriteria> {

    E create(CD dto);

    Boolean delete(K id);

    Boolean update(UD dto);

    D get(K id);

    List<D> getAll(C criteria);
}
