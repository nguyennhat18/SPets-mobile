package com.example.spetsrestapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "account")
public class Account extends BaseEntity implements Serializable {

    @NotEmpty(message = "Fullname is required!")
    @Column(name = "full_name")
    private String fullName;

    @NotEmpty(message = "Username is required!")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must not contain special characters or spaces!")
    @Column(name = "username", unique = true)
    private String username;

    @JsonIgnore
    @NotEmpty(message = "Password is required!")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "Email is required!")
    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private boolean status;

}
