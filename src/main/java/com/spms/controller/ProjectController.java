package com.spms.controller;


import com.spms.model.Chat;
import com.spms.model.Project;
import com.spms.model.User;
import com.spms.response.ApiMessageResponse;
import com.spms.service.ProjectService;
import com.spms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<Project>> getProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user, category, tag);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        Project project = projectService.getProjectById(projectId);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestBody Project newProject,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        Project createdProject = projectService.createProject(newProject, user);

        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long projectId,
            @RequestBody Project project,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        Project updateProject = projectService.updateProject(project, projectId);

        return new ResponseEntity<>(updateProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId")
    public ResponseEntity<ApiMessageResponse> deleteProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId, user.getId());
        ApiMessageResponse response = new ApiMessageResponse("Project Deleted Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjects(
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        List<Project> searchedProjects = projectService.searchProjects(keyword, user);

        return new ResponseEntity<>(searchedProjects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        Chat chat = projectService.getChatByProjectId(projectId);

        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

}
