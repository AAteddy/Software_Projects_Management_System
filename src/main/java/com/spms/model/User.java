

package com.spms.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String fullName;

    private String email;

    private String password;

    private Integer projectSize;

    @JsonIgnore
    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Issue> assignedIssues = new ArrayList<>();

    public Long getUseId() {
        return userId;
    }

    public void setUseId(Long useId) {
        this.userId = useId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getProjectSize() {
        return projectSize;
    }

    public void setProjectSize(Integer projectSize) {
        this.projectSize = projectSize;
    }

    public List<Issue> getAssignedIssues() {
        return assignedIssues;
    }

    public void setAssignedIssues(List<Issue> assignedIssues) {
        this.assignedIssues = assignedIssues;
    }
}
