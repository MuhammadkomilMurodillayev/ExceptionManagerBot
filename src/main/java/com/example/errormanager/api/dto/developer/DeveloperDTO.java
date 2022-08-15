package com.example.errormanager.api.dto.developer;

import com.example.errormanager.api.domain.Project;
import com.example.errormanager.api.dto.BaseGenericDTO;
import com.example.errormanager.api.enums.DeveloperRole;
import com.example.errormanager.api.enums.Language;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Muhammadkomil Murodillayev, ср 11:30. 8/10/22
 */

@Getter
@Setter
public class DeveloperDTO extends BaseGenericDTO {

    private String fullName;

    private String username;

    private String password;

    private DeveloperRole role;

//    private Set<Project> projects;

    private String chatId;

    private short status;

    private Language language;

}
