package com.property.management.service;

import com.property.management.entity.TeamEntity;
import com.property.management.repository.DepartmentRepository;
import com.property.management.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TeamService implements Team{
    private DepartmentRepository departmentRepository;
    private TeamRepository teamRepository;

    public TeamService(DepartmentRepository departmentRepository, TeamRepository teamRepository) {
        this.departmentRepository = departmentRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List <TeamEntity> getAllTeam() throws Exception {

        return (List <TeamEntity>) teamRepository.findAll ();
    }

    @Override
    public List <TeamEntity> getAllTeamsInDepartment(Long depId) throws Exception {
        if (departmentRepository.findById (depId)==null){
            throw new Exception ("no team");
        }

        return departmentRepository.findById (depId).get ().getTeams ();
    }



    @Override
    public Optional <TeamEntity> findByDepartmentIdAndTeamId(long departmentId, Long teamId) throws Exception {
       return   departmentRepository.findById (departmentId).get ().getTeams ().stream ()
                 .filter (teamEntity -> teamEntity.getId ()==teamId).findAny ();

    }

    @Override
    public TeamEntity saveTeam(TeamEntity teamEntity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }


    @Override
    public Optional <TeamEntity> findByName(String name) throws Exception {
        return Optional.empty ();
    }
}
