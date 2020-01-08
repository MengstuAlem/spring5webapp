package com.property.management.controller;

import com.property.management.entity.TeamEntity;
import com.property.management.service.DepartmentService;
import com.property.management.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@ResponseBody
public class TeamController {

    private TeamService teamService;
    @Autowired
    private DepartmentService departmentService;

    public TeamController(TeamService teamService) {

        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public String getAllTeams(Model model) throws Exception {
        model.addAttribute ("teams",teamService.getAllTeam ());
        return "teams";


    }
}
