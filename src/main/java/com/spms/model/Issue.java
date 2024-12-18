

package com.spms.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long issueId;

    @ManyToOne
    private User assignee;


}
