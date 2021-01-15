package com.nastyabakhshyieva.blog.entities;

import com.nastyabakhshyieva.blog.entities.status.UserStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String password;
    private String email;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
