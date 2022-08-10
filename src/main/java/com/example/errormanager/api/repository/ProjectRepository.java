package com.example.errormanager.api.repository;

import com.example.errormanager.api.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Muhammadkomil Murodillayev, ср 15:37. 8/10/22
 */

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, BaseRepository {

    List<Project> findAllByDeleted(boolean deleted);
}
