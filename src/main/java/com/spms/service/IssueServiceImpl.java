package com.spms.service;

import com.spms.model.Issue;
import com.spms.repository.IssueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class IssueServiceImpl implements IssueService {


    @Autowired
    private IssueRepo issueRepo;



}
