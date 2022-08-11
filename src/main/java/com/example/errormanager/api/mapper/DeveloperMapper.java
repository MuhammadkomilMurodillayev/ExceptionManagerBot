package com.example.errormanager.api.mapper;

import com.example.errormanager.api.domain.Developer;
import com.example.errormanager.api.domain.Project;
import com.example.errormanager.api.dto.developer.DeveloperCreateDTO;
import com.example.errormanager.api.dto.developer.DeveloperDTO;
import com.example.errormanager.api.dto.developer.DeveloperUpdateDTO;
import com.example.errormanager.api.repository.ProjectRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * @author Muhammadkomil Murodillayev, ср 11:40. 8/10/22
 */
@Component
public class DeveloperMapper implements BaseMapper<DeveloperDTO, Developer, DeveloperCreateDTO, DeveloperUpdateDTO> {

    private final ProjectRepository projectRepository;

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder(8);

    public DeveloperMapper(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public DeveloperDTO toDTO(Developer domain) {

        DeveloperDTO developer = new DeveloperDTO();
        List<Project> projectList = projectRepository.findAllByDeleted(false);
        Set<Project> projects = new HashSet<>(projectList);

        developer.setChatId(domain.getChatId());
        developer.setRole(domain.getRole());
        developer.setFullName(domain.getFullName());
        developer.setId(domain.getId());
        developer.setPassword(domain.getPassword());
        developer.setUsername(domain.getUsername());
        developer.setProjects(projects);

        return developer;
    }

    @Override
    public List<DeveloperDTO> toDTO(List<Developer> domains) {

        List<DeveloperDTO> developers = new ArrayList<>();

        domains.forEach(developer -> developers.add(toDTO(developer)));

        return developers;
    }

    @Override
    public Developer fromCreateDTO(DeveloperCreateDTO dto) {

        Developer developer = new Developer();

        developer.setFullName(developer.getFullName());
        developer.setUsername(developer.getUsername());
        developer.setPassword(ENCODER.encode(dto.getPassword()));
        developer.setUpdatedAt(developer.getCreatedAt());
        developer.setRole(dto.getRole());

        if (Objects.nonNull(dto.getProjects())) developer.setProjects(dto.getProjects());

        return developer;
    }

    @Override
    public Developer fromUpdateDTO(Developer developer, DeveloperUpdateDTO dto) {
        if (dto == null) return developer;

        if (dto.getFullName() != null) developer.setFullName(dto.getFullName());
        if (dto.getUsername() != null) developer.setUsername(dto.getUsername());
        if (dto.getPassword() != null) developer.setPassword(ENCODER.encode(dto.getPassword()));
        if (dto.getPassword() != null) developer.setRole(dto.getRole());
        if (dto.getProjects() != null) developer.setProjects(dto.getProjects());
        if (dto.getChatId() != null) developer.setChatId(dto.getChatId());

        return developer;

    }
}
