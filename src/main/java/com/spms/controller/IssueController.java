package com.spms.controller;


import com.spms.dto.IssueDTO;
import com.spms.model.Issue;
import com.spms.model.User;
import com.spms.request.IssueRequest;
import com.spms.response.ApiMessageResponse;
import com.spms.response.AuthResponse;
import com.spms.service.IssueService;
import com.spms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<IssueDTO> createIssue(
            @RequestBody IssueRequest issueRequest,
            @RequestHeader("Authorization") String token
            ) throws Exception {

        User user = userService.findUserProfileByJwt(token);

        Issue createdIssue = issueService.createIssue(issueRequest, user);

        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setTitle(createdIssue.getTitle());
        issueDTO.setId(createdIssue.getId());
        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setStatus(createdIssue.getStatus());
        issueDTO.setPriority(createdIssue.getPriority());
        issueDTO.setProject(createdIssue.getProject());
        issueDTO.setProjectID(createdIssue.getProjectID());
        issueDTO.setDueDate(createdIssue.getDueDate());
        issueDTO.setAssignee(createdIssue.getAssignee());
        issueDTO.setTags(createdIssue.getTags());

        return ResponseEntity.ok(issueDTO);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<ApiMessageResponse> deleteIssue(
            @PathVariable Long issueId,
            @RequestHeader("Authorization") String token
    ) throws Exception {

        User user = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, user.getId());

        ApiMessageResponse response = new ApiMessageResponse();
        response.setMessage("Issue Deleted Successfully.");

        return ResponseEntity.ok(response);
    }

    @PutMapping("{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(
            @PathVariable Long issueId,
            @PathVariable Long userId
    ) throws Exception {

        Issue issue = issueService.addUserToIssue(issueId, userId);

        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(
            @PathVariable Long issueId,
            @PathVariable String status
    ) throws Exception {

        Issue issue = issueService.updateStatus(issueId, status);

        return ResponseEntity.ok(issue);
    }


}
