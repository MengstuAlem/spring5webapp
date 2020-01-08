package com.property.management.service;

import com.property.management.entity.TeamEntity;
import com.property.management.repository.DepartmentRepository;
import com.property.management.repository.TeamRepository;

import java.util.List;

public class TeamServiceImpl extends TeamService {
    public TeamServiceImpl(DepartmentRepository departmentRepository, TeamRepository teamRepository) {
        super (departmentRepository, teamRepository);
    }

}
