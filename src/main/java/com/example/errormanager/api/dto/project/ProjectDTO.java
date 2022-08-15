package com.example.errormanager.api.dto.project;

import com.example.errormanager.api.dto.BaseGenericDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Muhammadkomil Murodillayev, ср 16:32. 8/10/22
 */

@Getter
@Setter
public class ProjectDTO extends BaseGenericDTO {
    private String name;

    @Override
    public String toString() {
        return this.name;
    }
}
