package com.spms.controller;


import com.spms.model.Issue;
import com.spms.service.IssueService;
import com.spms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;


    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(
            @PathVariable Long issueId
    ) throws Exception {

        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(
            @PathVariable Long projectId
    ) throws Exception {

        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }



}
