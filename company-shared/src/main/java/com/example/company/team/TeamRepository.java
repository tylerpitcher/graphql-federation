package com.example.company.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
interface TeamRepository extends JpaRepository<Team, UUID> {

    Set<Team> findAllByMembers_Id(UUID employeeId);
}
