package com.example.errormanager.api.repository;

import com.example.errormanager.api.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Muhammadkomil Murodillayev, ср 15:37. 8/10/22
 */

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, BaseRepository {

    List<Project> findAllByDeleted(boolean deleted);

    @Query(value = "select p.* from project p " +
            "left join project_developer pd on p.id = pd.project_id where not deleted and pd.developer_id = :developerId",
    nativeQuery = true)
    List<Project> findAllByDeveloperId(Long developerId);

    Optional<Project>  findByNameIsLikeIgnoreCase(String name);
}
