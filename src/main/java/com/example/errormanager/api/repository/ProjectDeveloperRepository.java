package com.example.errormanager.api.repository;

import com.example.errormanager.api.domain.ProjectDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDeveloperRepository extends JpaRepository<ProjectDeveloper, Long>, BaseRepository {

    void deleteAllByDeveloperId(Long developerId);
}
