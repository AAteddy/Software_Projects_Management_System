package com.spms.service;


import com.spms.model.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueService {

    Optional<Issue> getIssueById(Long issueId) throws Exception;

//    List<Issue> getIssueByProjectId(Long projectId) throws Exception;
//
//    Issue createIssue(IssueRequest issue, Long userId) throws Exception;
//
//    String deleteIssue(Long issueId, Long userId) throws Exception;
//
//    Issue addUserToIssue(Long issueId, Long userId) throws Exception;
//
//    Issue updateStatus(Long issueId, String status) throws Exception;
}
