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
import java.util.List;
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

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepo.findById(commentId);
        Optional<User> userOptional = userRepo.findById(userId);

        if (commentOptional.isEmpty())
            throw new Exception("Comment not found with comment Id : " + commentId);

        if (userOptional.isEmpty())
            throw new Exception("User not found with user id : " + userId);

        Comment comment = commentOptional.get();
        User user = userOptional.get();

        if (comment.getUser().equals(user)) {
            commentRepo.delete(comment);
        } else {
            throw new Exception("User does not have permission to delete this comment");
        }
    }

    @Override
    public List<Comment> findCommentsByIssueId(Long issueId) {
        return commentRepo.findByIssueId(issueId);
    }


}
