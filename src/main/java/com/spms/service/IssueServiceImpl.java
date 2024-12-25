package com.spms.service;

import com.spms.model.Issue;
import com.spms.repository.IssueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class IssueServiceImpl implements IssueService {


    @Autowired
    private IssueRepo issueRepo;


    @Override
    public Optional<Issue> getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepo.findById(issueId);
        if (issue.isPresent())
            return issue;

        throw new Exception("Issue not found with Issue Id = " + issueId);
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        return issueRepo.findByProjectID(projectId);
    }


}
