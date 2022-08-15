package com.example.errormanager.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProjectDeveloper implements BaseDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "developer_id")
    private Long developerId;



    public ProjectDeveloper(Long projectId,Long developerId){
        this.developerId = developerId;
        this.projectId = projectId;
    }
}
