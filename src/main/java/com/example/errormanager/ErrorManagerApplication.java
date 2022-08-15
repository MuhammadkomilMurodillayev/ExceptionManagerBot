package com.example.errormanager;

import com.example.errormanager.api.domain.Project;
import com.example.errormanager.api.dto.developer.DeveloperCreateDTO;
import com.example.errormanager.api.dto.project.ProjectCreateDTO;
import com.example.errormanager.api.enums.DeveloperRole;
import com.example.errormanager.api.service.DeveloperService;
import com.example.errormanager.api.service.ProjectService;
import com.example.errormanager.bot.config.BotInitializer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.telegram.telegrambots.meta.ApiConstants;

import java.util.HashSet;
import java.util.Set;

@OpenAPIDefinition
@SpringBootApplication
public class ErrorManagerApplication /*implements CommandLineRunner*/ {

    private final DeveloperService developerService;
    private final ProjectService projectService;

    public ErrorManagerApplication(DeveloperService developerService, ProjectService projectService) {
        this.developerService = developerService;
        this.projectService = projectService;
    }

    public static void main(String[] args) {

        PasswordEncoder encoder = new BCryptPasswordEncoder(8);

        System.out.printf(encoder.encode("muhammad123"));

        SpringApplication.run(ErrorManagerApplication.class, args);

    }

    public void run(String... args) throws Exception {

        ProjectCreateDTO projectCreateDTO = new ProjectCreateDTO();
        projectCreateDTO.setName("UZAGROIN");
        Project project = projectService.create(projectCreateDTO);

        Set<Project> projectSet = new HashSet<>();
        projectSet.add(project);

        DeveloperCreateDTO dto = new DeveloperCreateDTO();
        dto.setFullName("Muhammadkomil Murodillayev");
        dto.setUsername("muhammad");
        dto.setPassword("muhammad123");
        dto.setRole(DeveloperRole.TEAM_LEAD);

        developerService.create(dto);
    }
}
