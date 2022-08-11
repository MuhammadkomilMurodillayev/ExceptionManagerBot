package com.example.errormanager.api.service;

import com.example.errormanager.api.mapper.BaseMapper;
import com.example.errormanager.api.repository.BaseRepository;
import com.example.errormanager.api.validation.BaseValidation;

/**
 * @author Muhammadkomil Murodillayev, ср 11:42. 8/10/22
 */
public class AbstractService<
        R extends BaseRepository,
        M extends BaseMapper,
        V extends BaseValidation> {

    protected final R repository;
    protected final M mapper;
    protected final V validation;

    public AbstractService(R repository, M mapper, V validation) {
        this.repository = repository;
        this.mapper = mapper;
        this.validation = validation;
    }
}
