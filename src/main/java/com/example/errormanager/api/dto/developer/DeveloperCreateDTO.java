package com.example.errormanager.api.dto.developer;

import com.example.errormanager.api.domain.Project;
import com.example.errormanager.api.dto.BaseDTO;
import com.example.errormanager.api.enums.DeveloperRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Muhammadkomil Murodillayev, ср 11:58. 8/10/22
 */

@Getter
@Setter
public class DeveloperCreateDTO implements BaseDTO {

    private String fullName;

    private String username;

    private String password;

    private DeveloperRole role;

    private Set<Project> projects;
    public DeveloperCreateDTO() {
        this.role = DeveloperRole.PROGRAMMER;
    }
}
