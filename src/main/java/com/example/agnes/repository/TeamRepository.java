package com.example.agnes.repository;

import com.example.agnes.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findById(UUID id);
    void deleteById(UUID id);
}
