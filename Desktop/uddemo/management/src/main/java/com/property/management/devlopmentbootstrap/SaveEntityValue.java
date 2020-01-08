package com.property.management.devlopmentbootstrap;

import com.property.management.entity.DepartmentEntity;
import com.property.management.entity.TeamEntity;
import com.property.management.repository.DepartmentRepository;
import com.property.management.repository.TeamRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SaveEntityValue implements ApplicationListener <ContextRefreshedEvent> {

private TeamRepository teamRepository;
private DepartmentRepository departmentRepository;

    public SaveEntityValue(TeamRepository teamRepository, DepartmentRepository departmentRepository) {
        this.teamRepository = teamRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
     initData ();;
    }

    public void  initData()  {
        List<TeamEntity> teamIct= new ArrayList <> ();
        List<TeamEntity> teamPromotion= new ArrayList <> ();
        DepartmentEntity ict = new DepartmentEntity ("ict");
        DepartmentEntity promotion = new DepartmentEntity ("promotion");
        TeamEntity software= new TeamEntity ("software",ict);
        TeamEntity dataBasse= new TeamEntity ("database",ict);
        TeamEntity radio= new TeamEntity ("radio",promotion);
        TeamEntity tv= new TeamEntity ("tv",promotion);
        teamIct.add (software);
        teamIct.add (dataBasse);
        teamPromotion.add (radio);
        teamPromotion.add (tv);

         ict.setTeams (teamIct);
         promotion.setTeams (teamPromotion);
        departmentRepository.save (ict);
        departmentRepository.save (promotion);








    }
}
