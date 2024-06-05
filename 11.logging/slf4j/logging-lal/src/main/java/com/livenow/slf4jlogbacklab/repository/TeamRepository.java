package com.livenow.slf4jlogbacklab.repository;

import com.livenow.slf4jlogbacklab.domain.Team;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String name);
}
