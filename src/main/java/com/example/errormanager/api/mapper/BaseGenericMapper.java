package com.example.errormanager.api.mapper;

import com.example.errormanager.api.domain.BaseDomain;
import com.example.errormanager.api.dto.BaseDTO;
import com.example.errormanager.api.dto.BaseGenericDTO;

import java.util.List;

/**
 * @author Muhammadkomil Murodillayev, ср 15:06. 8/10/22
 */
public interface BaseGenericMapper<
        D extends BaseGenericDTO,
        E extends BaseDomain,
        CD extends BaseDTO,
        UD extends BaseGenericDTO> extends BaseMapper{

    D toDTO(E domain);

    List<D> toDTO(List<E> domains);

    E fromCreateDTO(CD dto);

    E fromUpdateDTO(E domain, UD dto);

}
