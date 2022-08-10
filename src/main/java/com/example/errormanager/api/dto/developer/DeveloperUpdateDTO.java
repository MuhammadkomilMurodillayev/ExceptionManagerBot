package com.example.errormanager.api.dto.developer;

import com.example.errormanager.api.domain.Project;
import com.example.errormanager.api.dto.BaseGenericDTO;
import com.example.errormanager.api.enums.DeveloperRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Muhammadkomil Murodillayev, ср 12:06. 8/10/22
 */

@Setter
@Getter
public class DeveloperUpdateDTO extends BaseGenericDTO {

    private String fullName;

    private String username;

    private String password;

    private DeveloperRole role;

    private Set<Project> projects;

    private String chatId;
}
