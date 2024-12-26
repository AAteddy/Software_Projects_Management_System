package com.spms.repository;

import com.spms.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByIssueId(Long issueId);
}
