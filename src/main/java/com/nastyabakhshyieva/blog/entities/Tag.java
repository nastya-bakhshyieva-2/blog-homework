package com.nastyabakhshyieva.blog.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
