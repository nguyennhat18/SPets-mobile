package com.example.spetsrestapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "media")
public class Media extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name = "record_type")
    private String recordType;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "path")
    private String path;

    @Column(name = "status")
    private boolean status;

}
