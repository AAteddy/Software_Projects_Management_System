package com.spms.repository;


import com.spms.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IssueRepo extends JpaRepository<Issue, Long> {

}
