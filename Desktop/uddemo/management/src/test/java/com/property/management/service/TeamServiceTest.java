package com.property.management.service;

import com.property.management.entity.TeamEntity;
import com.property.management.repository.TeamRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Service
class TeamServiceTest {
   @Mock
    private TeamRepository teamRepository;
   @InjectMocks
    private TeamService teamService;

   @BeforeEach
    public void setUp(){
       MockitoAnnotations.initMocks (this);
       teamService= new TeamService (teamRepository);
   }
   @Test
    public void getAllExistTeams() throws Exception {
       List<TeamEntity> teams= new ArrayList <> ();
       teams.add (new TeamEntity ("software"));
       teams.add (new TeamEntity ("dataBase"));
       given(teamRepository.findAll ()).willReturn (teams);
      assertEquals (2,teamService.getAllTeam().size());
      verify (teamRepository,times (1)).findAll ();
}

    @Test
    public void TryToGetNoneExistTeamsThrowErro() throws Exception {

        given(teamRepository.findAll ()).willReturn (null);

       assertThrows (Exception.class,()->{
           teamService.getAllTeam ();
       });

        verify (teamRepository,times (1)).findAll ();
    }

    @Test

    public void getExistTeamById() throws Exception {
        TeamEntity software= new TeamEntity ("software");
        given(teamRepository.findById (1L)).willReturn (java.util.Optional.ofNullable (software));
        assertEquals (software.getName (),teamService.getTeamById(1L).get ().getName ());
        verify (teamRepository,times (2)).findById (1L);
    }

    @Test

    public void getNonExistTeamById() throws Exception {

        given(teamRepository.findById (1L)).willReturn(java.util.Optional.ofNullable (null));

        assertThrows (Exception.class,()->{
            teamService.getTeamById (1l);
        });
        verify (teamRepository,times (1)).findById (1L);
    }

    @Test

    public void  saveTeamTodatabase(){
        TeamEntity software = new TeamEntity ("software");
        given (teamRepository.save (software)).willReturn (software);
       TeamEntity savedSoftware= teamService.saveTeam(software);
       assertThat(software.getName ()).isEqualTo (savedSoftware.getName ());
       verify (teamRepository,times (1)).save (software);
    }

    @Test
    public void getExistteamReturnTeam() throws Exception {
       given (teamRepository.findByName("software")).willReturn (java.util.Optional.of (new TeamEntity ("software")));
       Optional <TeamEntity> software= teamService.findByName ("software");
       assertThat (software.get ().getName ()).isEqualTo ("software");
       verify (teamRepository,times (2)).findByName ("software");

    }

    @Test
    public void getNonExistTeamReturnNull() throws Exception {
       given (teamRepository.findByName (anyString ())).willReturn (java.util.Optional.ofNullable(null));

        assertThrows (Exception.class,()->{
            teamService.findByName (anyString ());
        });
       verify (teamRepository,times (1)).findByName (anyString ());
    }

}