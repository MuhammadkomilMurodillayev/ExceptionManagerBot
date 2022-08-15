package com.example.errormanager.api.service;

import com.example.errormanager.api.criteria.DeveloperCriteria;
import com.example.errormanager.api.domain.Developer;
import com.example.errormanager.api.domain.Project;
import com.example.errormanager.api.domain.ProjectDeveloper;
import com.example.errormanager.api.dto.developer.DeveloperCreateDTO;
import com.example.errormanager.api.dto.developer.DeveloperDTO;
import com.example.errormanager.api.dto.developer.DeveloperUpdateDTO;
import com.example.errormanager.api.exception.BadCredentials;
import com.example.errormanager.api.exception.ForbiddenException;
import com.example.errormanager.api.mapper.DeveloperMapper;
import com.example.errormanager.api.repository.DeveloperRepository;
import com.example.errormanager.api.repository.ProjectDeveloperRepository;
import com.example.errormanager.api.repository.ProjectRepository;
import com.example.errormanager.api.validation.DeveloperValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Muhammadkomil Murodillayev, ср 10:25. 8/10/22
 */

@Service
public class DeveloperService
        extends AbstractService<
        DeveloperRepository,
        DeveloperMapper,
        DeveloperValidation>
        implements BaseCrudService<
        Developer,
        DeveloperDTO,
        DeveloperCreateDTO,
        DeveloperUpdateDTO,
        Long,
        DeveloperCriteria> {

    private final ProjectDeveloperRepository projectDeveloperRepository;

    private final ProjectRepository projectRepository;

    public DeveloperService(DeveloperRepository repository, DeveloperMapper mapper, DeveloperValidation validation, ProjectDeveloperRepository projectDeveloperRepository, ProjectRepository projectRepository) {
        super(repository, mapper, validation);
        this.projectDeveloperRepository = projectDeveloperRepository;
        this.projectRepository = projectRepository;
    }

    public DeveloperDTO getByUsername(String username) {

        Optional<Developer> developerOptional = repository.findByUsernameAndDeletedFalse(username.toLowerCase());
        if (developerOptional.isPresent()) {
            return mapper.toDTO(developerOptional.get());
        }

        throw new BadCredentials();
    }

    @Override
    public Developer create(DeveloperCreateDTO dto) {
        Developer developer = mapper.fromCreateDTO(dto);
        repository.save(developer);
        return developer;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    public void deleteByUsername(String username) {
        repository.deleteByUsername(username.toLowerCase());
    }


    @Override
    public Boolean update(DeveloperUpdateDTO dto) {
        validation.checkUpdate(dto);
        Optional<Developer> developerOptional = repository.findByIdAndDeletedFalse(dto.getId());
        if (developerOptional.isPresent()) {
            repository.save(mapper.fromUpdateDTO(developerOptional.get(), dto));
            return true;
        }
        return false;
    }

    @Override
    public DeveloperDTO get(Long id) {
        return null;
    }

    @Override
    public List<DeveloperDTO> getAll(DeveloperCriteria criteria) {
        return null;
    }

    public int getDeveloperRole(String chatId) {

        return 0;
    }

    public DeveloperDTO getByChatId(String chatId) {

        Developer developerOptional = repository.findByChatIdAndDeletedFalse(chatId).orElseThrow(() -> {
            throw new ForbiddenException();
        });

        return mapper.toDTO(developerOptional);


    }

    public List<String> getDeveloperChatId(Long projectId) {

        return repository.findAllChatId(projectId);


    }

    @Transactional
    public void setProjects(String[] projectNames, String chatId) {

        DeveloperDTO developer = getByChatId(chatId);

        projectDeveloperRepository.deleteAllByDeveloperId(developer.getId());

        for (String projectName : projectNames) {

            Optional<Project> projectOptional = projectRepository.findByNameIsLikeIgnoreCase(projectName);
            projectOptional.ifPresent(project -> projectDeveloperRepository.save(new ProjectDeveloper(project.getId(), developer.getId())));

        }
    }

    public void activated(String username) {
        Optional<Developer> developerOptional = repository.findByUsernameAndDeletedFalse(username);
        if (developerOptional.isPresent()) {
            Developer developer = developerOptional.get();
            developer.setStatus((short) 0);
            repository.save(developer);
        }

    }
}
