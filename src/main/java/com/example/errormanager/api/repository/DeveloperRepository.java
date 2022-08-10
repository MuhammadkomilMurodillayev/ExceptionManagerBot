package com.example.errormanager.api.repository;

import com.example.errormanager.api.domain.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Muhammadkomil Murodillayev, ср 11:39. 8/10/22
 */
@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> , BaseRepository{

    Optional<Developer> findByUsernameAndDeletedFalse(String username);
}
