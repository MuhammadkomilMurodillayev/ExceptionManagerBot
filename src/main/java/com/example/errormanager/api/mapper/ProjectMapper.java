package com.example.errormanager.api.mapper;

import com.example.errormanager.api.domain.Project;
import com.example.errormanager.api.dto.project.ProjectCreateDTO;
import com.example.errormanager.api.dto.project.ProjectDTO;
import com.example.errormanager.api.dto.project.ProjectUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammadkomil Murodillayev, ср 16:30. 8/10/22
 */

@Component
public class ProjectMapper implements BaseGenericMapper<
        ProjectDTO,
        Project,
        ProjectCreateDTO,
        ProjectUpdateDTO> {

    @Override
    public ProjectDTO toDTO(Project domain) {

        ProjectDTO project = new ProjectDTO();
        project.setId(domain.getId());
        project.setName(domain.getName());
        return project;

    }

    @Override
    public List<ProjectDTO> toDTO(List<Project> domains) {
        List<ProjectDTO> projects = new ArrayList<>();

        domains.forEach((domain)->{
            projects.add(toDTO(domain));
        });

        return projects;
    }

    @Override
    public Project fromCreateDTO(ProjectCreateDTO dto) {
        Project project = new Project();

        project.setName(dto.getName());

        return project;
    }

    @Override
    public Project fromUpdateDTO(Project domain, ProjectUpdateDTO dto) {
        return null;
    }
}
