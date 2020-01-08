package com.property.management.repository;

import com.property.management.entity.TeamEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TeamRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private TeamRepository teamRepository;

    @Test

    public void findBynameReturnTeam(){
        TeamEntity savedTeam= testEntityManager.persistFlushFind (new TeamEntity ("software"));
        Optional <TeamEntity> team= teamRepository.findByName ("software");
        assertThat(team.get ().getName ()).isEqualTo (savedTeam.getName ());
       
    }


}