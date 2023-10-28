package com.example.spetsrestapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "hospital")
public class Hospital extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @JsonIgnore
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private boolean status;

}
