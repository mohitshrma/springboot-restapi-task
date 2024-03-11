package com.task.theeducationalinstitute.repository;

import com.task.theeducationalinstitute.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, String> {

    boolean existsByUsername(String username);

    Optional<AppUser> findByUsername(String username);
}
