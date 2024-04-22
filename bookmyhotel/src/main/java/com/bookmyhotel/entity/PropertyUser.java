package com.bookmyhotel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class PropertyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private  String lastName;
    private String email;

    @Column(nullable = false,unique = true)
    private String username;
    @JsonIgnore //it will help us to prevent sending this data back to the client during fetch operation
    private String password;

    @JsonIgnore
    @Column(name = "user_role")
    private String userRole;
}
