package com.spms.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private LocalDateTime createdDataTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Issue issue;
}
