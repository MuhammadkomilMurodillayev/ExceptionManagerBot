package com.example.errormanager.api.service;

import com.example.errormanager.api.criteria.ProjectCriteria;
import com.example.errormanager.api.domain.Project;
import com.example.errormanager.api.dto.project.ProjectCreateDTO;
import com.example.errormanager.api.dto.project.ProjectDTO;
import com.example.errormanager.api.dto.project.ProjectUpdateDTO;
import com.example.errormanager.api.mapper.ProjectMapper;
import com.example.errormanager.api.repository.ProjectRepository;
import com.example.errormanager.api.validation.ProjectValidation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Muhammadkomil Murodillayev, ср 16:29. 8/10/22
 */

@Service
public class ProjectService extends AbstractService<
        ProjectRepository,
        ProjectMapper,
        ProjectValidation> implements BaseCrudService<
        Project,
        ProjectDTO,
        ProjectCreateDTO,
        ProjectUpdateDTO,
        Long,
        ProjectCriteria> {

    public ProjectService(ProjectRepository repository, ProjectMapper mapper, ProjectValidation validation) {
        super(repository, mapper, validation);
    }

    @Override
    public Project create(ProjectCreateDTO dto) {
        Project project = mapper.fromCreateDTO(dto);
        return repository.save(project);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Boolean update(ProjectUpdateDTO dto) {
        return null;
    }

    @Override
    public ProjectDTO get(Long id) {
        return null;
    }

    @Override
    public List<ProjectDTO> getAll(ProjectCriteria criteria) {
        List<Project> projectList = repository.findAllByDeleted(false);
        return mapper.toDTO(projectList);
    }

    public List<ProjectDTO> getAll(Long developerId) {
        List<Project> projectList = repository.findAllByDeveloperId(developerId);
        return mapper.toDTO(projectList);
    }
}
