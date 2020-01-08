package com.property.management.controller;

import com.property.management.entity.TeamEntity;
import com.property.management.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TeamControllerTest {

    @Mock
    private TeamService teamService;
    @Mock
    private Model model;


    private TeamController teamController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks (this);
        teamController= new TeamController (teamService);
        mockMvc=MockMvcBuilders.standaloneSetup (teamController).build ();
    }

    @Test
    public void callAllteam() throws Exception {
        mockMvc.perform (get("/teams")).andExpect (status().isOk ());

    }

    @Test
    public void listExistingTeamReturnTeams() throws Exception {
        List<TeamEntity> teams= new ArrayList <> ();
         teams.add (new TeamEntity ("software"));
         teams.add (new TeamEntity ("database"));
         given(teamService.getAllTeam ()).willReturn (teams);
        teamController.getAllTeams (model);
         ArgumentCaptor<List<TeamEntity>> listArgumentCaptor=ArgumentCaptor.forClass (List.class);
         verify (teamService,times (1)).getAllTeam ();
         verify (model,times (1)).addAttribute (eq ("teams"),listArgumentCaptor.capture ());

    }

}