package com.example.spetsrestapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "docter")
public class Docter extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "status")
    private boolean status;

}
