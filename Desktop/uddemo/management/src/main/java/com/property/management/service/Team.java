package com.property.management.service;

import com.property.management.entity.TeamEntity;

import java.util.List;
import java.util.Optional;

public interface Team {
    List<TeamEntity> getAllTeam() throws Exception;

    Optional <TeamEntity> findByDepartmentIdAndTeamId(long departmentId, Long teamId) throws Exception;

    TeamEntity saveTeam(TeamEntity teamEntity);
     void deleteById(Long id);
    List<TeamEntity> getAllTeamsInDepartment(Long depId) throws Exception;

    Optional <TeamEntity> findByName(String name) throws Exception;
}
