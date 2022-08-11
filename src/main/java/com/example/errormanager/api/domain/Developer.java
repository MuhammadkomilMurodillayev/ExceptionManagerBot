package com.example.errormanager.api.domain;

import com.example.errormanager.api.enums.DeveloperRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Muhammadkomil Murodillayev, ср 10:59. 8/10/22
 */

@Entity
@Getter
@Setter
public class Developer extends Auditable {

    @Column(name = "full_name")
    private String fullName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private DeveloperRole role;

    @Column(name = "chat_id")
    private String chatId;

    @ManyToMany
    @JoinTable(
            name = "developer_project",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects;

}
