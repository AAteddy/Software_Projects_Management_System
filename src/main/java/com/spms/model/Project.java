package com.spms.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;

    private String name;

    private String description;

    private String category;

    private List<String> tags = new ArrayList<>();


    @ManyToOne
    private User owner;
}
