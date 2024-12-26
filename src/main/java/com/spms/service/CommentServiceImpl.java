package com.spms.service;


import com.spms.model.Comment;
import com.spms.model.Issue;
import com.spms.model.User;
import com.spms.repository.CommentRepo;
import com.spms.repository.IssueRepo;
import com.spms.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private IssueRepo issueRepo;


    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepo.findById(issueId);
        Optional<User> userOptional = userRepo.findById(userId);

        if (issueOptional.isEmpty())
            throw new Exception("Issue not found with Issue Id : " + issueId);

        if (userOptional.isEmpty())
            throw new Exception("User not found with User Id : " + userId);

        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setIssue(issue);
        comment.setContent(content);
        comment.setCreatedDataTime(LocalDateTime.now());

        Comment savedComment = commentRepo.save(comment);

        issue.getComments().add(savedComment);

        return savedComment;
    }

}
